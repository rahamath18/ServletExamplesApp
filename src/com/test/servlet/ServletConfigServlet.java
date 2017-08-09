package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfigServlet extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  
		    throws ServletException, IOException {  
		  
		    response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		      
		    ServletConfig config = getServletConfig();  
		    String driver = config.getInitParameter("driver");  
		    String user = config.getInitParameter("user-name");  
		    String pwd = config.getInitParameter("password");  
		    out.print("<br>" + "Driver is: " + driver);  
		    out.print("<br>" + "User is: " + user);  
		    out.print("<br>" + "PWD is: " + pwd);  
		          
		    out.close();  
    }  
  
}  
