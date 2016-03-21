
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hashtext;
		int h=0;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("userName");
		
		String e=request.getParameter("userEmail");
		String g=request.getParameter("gender");
		String p=request.getParameter("userPass");
		if(n!="" && p!="" && e!="" && g!="" ){
		try{
			MessageDigest md= MessageDigest.getInstance("MD5");
			byte[] messageDigest=md.digest(p.getBytes());
			BigInteger number=new BigInteger(1,messageDigest);
			 hashtext =number.toString(16);
			while (hashtext.length()<32){
				hashtext="0" +hashtext;
			}
			
			
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
		
		//validate email
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			      
			      if(e.matches(EMAIL_REGEX))
			    		  {
			    	  			 h=1;
			    		  }
			      else
			    	 h=0;
			if(h==1)
			{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","username","password");
		PreparedStatement ps=con.prepareStatement("insert into quiz values(?,?,?,?,?)");
		ps.setString(1,n);
		ps.setString(2,hashtext);
		ps.setString(3,e);
		ps.setString(4,g);
		ps.setInt(5,0);
		
		int i=ps.executeUpdate();
		if(i>0){
		ServletContext context=getServletContext();  
		context.setAttribute("Email",e);
		Cookie ck=new Cookie("name",n);
		response.addCookie(ck); 
		request.getRequestDispatcher("Rules.html").include(request, response);
		}
			
		}
		catch (Exception e2) 
		{
			out.print("Enter the blank parameter");
			request.getRequestDispatcher("index.html").include(request,response);
		}
		
	}
			else
			{
					out.print("Not a valide email address <br>");
					request.getRequestDispatcher("index.html").include(request, response);
					
			}
	}
	else
	{
		out.print("Enter the blank parameter");
		request.getRequestDispatcher("index.html").include(request,response);
	}

}}
