package com.test.servlet;

//Import required java libraries
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Extend HttpServlet class
public class ShowErrorServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

// Method to handle GET method request.
public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	
	System.out.println("ShowErrorServlet...INITIATE ERROR AT RESPONSE!");
   
   // Set error code and reason.
  // response.sendError(407, "Need authentication!!!" );
	new Integer("sdsadsada");
}

// Method to handle POST method request.
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   doGet(request, response);
}
}
