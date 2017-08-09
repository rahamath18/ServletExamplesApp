package com.test.servlet;

//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Extend HttpServlet class
public class RefreshServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

// Method to handle GET method request.
public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	
	Date date = new Date();
	System.out.println("RefreshServlet....." + date);

   // Set refresh, autoload time as 5 seconds
   response.setIntHeader("Refresh", 5);
   // Set response content type
   response.setContentType("text/html");
    
 
   PrintWriter out = response.getWriter();
   String title = "Auto Refresh Header Setting";
   String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

   out.println(docType +
      "<html>\n" +
      "<head><title>" + title + "</title></head>\n"+
      "<body bgcolor = \"#f0f0f0\">\n" +
      "<h1 align = \"center\">" + title + "</h1>\n" +
      "<p style='font-size:25px;'>Current Time is: " + date + "</p>\n"
   );
}

// Method to handle POST method request.
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   doGet(request, response);
}
}
