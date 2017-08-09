package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCookiesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      Cookie cookie = null;
		  Cookie[] cookies = null;
	      // Get an array of Cookies associated with this domain
	      cookies = request.getCookies();
	      
		  // Set response content type
	      response.setContentType("text/html");
	      
	      String empCreatePage = "http://localhost:8080/ServletExamplesApp/cookieForm.html";
	 
	      PrintWriter out = response.getWriter();
	      String title = "Delete Cookies Example";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" );
	       if( cookies != null ){
	         out.println("<h2> Cookies Name and Value</h2>");
	         for (int i = 0; i < cookies.length; i++){
	            cookie = cookies[i];
	            if((cookie.getName( )).compareTo("emp_name") == 0 ){
	                 cookie.setMaxAge(0);
	                 response.addCookie(cookie);
	                 out.print("Deleted cookie : " + 
	                              cookie.getName( ) + "<br/>");
	            }
	            out.print("Name : " + cookie.getName( ) + ",  ");
	            out.print("Value: " + cookie.getValue( )+" <br/>");
	         }
	      }else{
	          out.println(
	            "<h2>No cookies founds</h2>");
	      }
		  out.println("<a href='" + empCreatePage + "'>Create Cookies</a>");
	      out.println("</body>");
	      out.println("</html>");
	   }
	}