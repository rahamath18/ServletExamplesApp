package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFormServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // Create cookies for first and last names.      
	      Cookie firstName = new Cookie("emp_name", request.getParameter("name"));
	      Cookie lastName = new Cookie("emp_salary", request.getParameter("salary"));

	      // Set expiry date after 24 Hrs for both the cookies.
	      firstName.setMaxAge(60*60*24); 
	      lastName.setMaxAge(60*60*24); 

	      // Add both the cookies in the response header.
	      response.addCookie( firstName );
	      response.addCookie( lastName );

	      // Set response content type
	      response.setContentType("text/html");
	      
	      String empCreatePage = "http://localhost:8080/ServletExamplesApp/ReadCookiesServlet";
	 
	      PrintWriter out = response.getWriter();
	      String title = "Setting Cookies Example";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<ul>\n" +
	                "  <li><b>Emp Name</b>: "
	                + request.getParameter("name") + "\n" +
	                "  <li><b>Salary</b>: "
	                + request.getParameter("salary") + "\n" +
	                "</ul>\n" +
	                "<a href='" + empCreatePage + "'>Read Cookies</a>" +
	                "</body></html>");
	  }
	  
	  @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doGet(request, response);
	  }
}
