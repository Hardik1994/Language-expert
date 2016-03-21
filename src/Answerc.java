



import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Answerc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count=0;
response.setContentType("text/html");
ServletContext context=getServletContext();  
String email=(String)context.getAttribute("Email");
PrintWriter ps=response.getWriter(); 
try{
Cookie ck[]=request.getCookies();  
System.out.print("Hello "+ck[0].getValue());
	
		if(ck[0]!=null || ck[0].getValue()!=""){
			try{
				//For ques 1
				String ans=request.getParameter("Ques1");
				 
				
				if(ans.equals("1"))
				{
					++count;
				}
				//for ques 2
				String ans2=request.getParameter("Ques2");
				if(ans2.equals("3"))
				{
					++count;
				}
				//for ques 3
				String ans3=request.getParameter("Ques3");
				if(ans3.equals("1"))
				{
					++count;
				}
				//for ques 4
				String ans4=request.getParameter("Ques4");
				if(ans4.equals("4"))
				{
					++count;
				}
				//for ques 5
				String ans5=request.getParameter("Ques5");
				if(ans5.equals("1"))
				{
					++count;
				}
				//for ques 6
				String ans6=request.getParameter("Ques6");
				if(ans6.equals("3"))
				{
					++count;
				}
			
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","username","password");
					String sql = "update quiz "
							  + "set Marks=?"
							  +" where Email=?";
					PreparedStatement p=con.prepareStatement(sql);
					p.setInt(1,count);
					p.setString(2,email);
					p.executeUpdate();
					
					
					String s="";
					ck[0].setMaxAge(0);
					
					response.addCookie(ck[0]);
					ps.println("<font size='10' color='red'>Congratulation "+ck[0].getValue()+"</font>");
					ps.print("<html><body><p><font color='red' size='16'>you scored "+count+" marks out of 6</font></p><table width='2289' height='500' border='0'><tr><td width='2283' bgcolor='#000000'><div align='left'><a href='answerc.html'><img src='answerimg.jpg' width='540' height='295' float :'top'/></a></div></td></tr></table></body></html>");
					
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		            response.setDateHeader("Expires", 0); // Proxies.
		            
				}
				catch(Exception ex){
					System.out.print(ex);
				}
		
		
		
}
catch(Exception e)
{
	
	
ps.println("<font size='10' color='red'>Do not leave any question</font>");
	
	request.getRequestDispatcher("qinc.html").include(request,response);
	
}
}
		else{
			request.getRequestDispatcher("loginagain.html").include(request,response);
		}}
			catch(Exception e){
	
	
	request.getRequestDispatcher("loginagain.html").include(request,response);
}
}}
