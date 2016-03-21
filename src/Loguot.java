



import java.io.IOException;  
import java.io.PrintWriter;  
  

import javax.servlet.ServletException;  
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class Loguot extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            try{
            Cookie ck[]=request.getCookies();
    		ck[0].setMaxAge(0);
    		
    		response.addCookie(ck[0]);
    		
    		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
            System.out.println(ck[0].getValue());
            request.getRequestDispatcher("index.html").include(request, response);
            
            out.close();  
    }  catch(Exception ex)
            {
    			request.getRequestDispatcher("index.html").include(request, response);
    	}
}}  