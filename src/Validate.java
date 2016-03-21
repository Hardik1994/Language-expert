

import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hashtext;
		
		response.setContentType("text/html");
		PrintWriter ps=response.getWriter();
		String Email=request.getParameter("email");
		String pass=request.getParameter("password");
		if(Email!="" && pass!="" ){
		try{
			MessageDigest md= MessageDigest.getInstance("MD5");
			byte[] messageDigest=md.digest(pass.getBytes());
			BigInteger number=new BigInteger(1,messageDigest);
			 hashtext =number.toString(16);
			while (hashtext.length()<32){
				hashtext="0" +hashtext;
			}
			
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","username","password");
			PreparedStatement stmt=con.prepareStatement("select * from quiz where Email= ?");
			stmt.setString(1,Email);
			ResultSet rs=stmt.executeQuery();
			if (!rs.next() ) {
			    ps.print("not registered");
			   request.getRequestDispatcher("loginagain.html").include(request, response);   
			}
			else{
			String password=rs.getString("Password");
			String mail=rs.getString("Email");
			String n=rs.getString("Name");
			if(hashtext.equals(password))
			{
				
				Cookie ck=new Cookie("name",n);
				response.addCookie(ck); 
				ps.print("Welcome "+n+"<br>");
				ServletContext context=getServletContext();  
				context.setAttribute("Email",mail);
				
				request.getRequestDispatcher("Rules.html").include(request, response);
				
			}
			else
			{
				ps.print("Password incorrect");
				response.sendRedirect("http://localhost:8087/Quizport/loginagain.html");
			}
			
		}}
		catch(Exception ex)
		{
			System.out.print(ex);
		}
		
	}
		else{
			ps.print("<body><p>Invalide input</p></body><br>");
			request.getRequestDispatcher("index.html").include(request,response);
		}
			

}}
