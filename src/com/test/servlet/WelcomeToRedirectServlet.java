package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeToRedirectServlet extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
		    throws ServletException, IOException {  
		  
		    response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		      
		    out.print("<h1>" + "Welcome to redirect servlet!" + "<h1>");  
		    
			String empCreatePage = "http://localhost:8080/ServletExamplesApp/forwardAndRedirect.html";
			out.print("<a href='" + empCreatePage + "'>Forward And Redirect</a>");

		    out.close();  
    }  
  
}  
