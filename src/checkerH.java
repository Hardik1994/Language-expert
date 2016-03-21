

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkerH
 */
@WebServlet("/checkerH")
public class checkerH extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try{
			
			Cookie ck[]=request.getCookies();
			if(ck[0]!=null || ck[0].getValue()!=""){
				System.out.print(ck[0].getValue());
				request.getRequestDispatcher("qinht.html").include(request, response);	
		}
			else{
				request.getRequestDispatcher("loginagain.html").include(request, response);
			}
	}
		catch(Exception e){
			request.getRequestDispatcher("loginagain.html").include(request, response);
		}
	}

}
