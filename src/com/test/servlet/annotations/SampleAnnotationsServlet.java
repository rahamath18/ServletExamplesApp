package com.test.servlet.annotations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
<servlet>
	<servlet-name>SampleAnnotationsServlet</servlet-name>
	<servlet-class>com.test.servlet.annotations.SampleAnnotationsServlet</servlet-class>
	<init-param>  
	<param-name>driver</param-name>  
	<param-value>com.mysql.jdbc.Driver</param-value>  
</init-param>
	<init-param>  
	<param-name>user-name</param-name>  
	<param-value>root</param-value>  
</init-param>
	<init-param>  
	<param-name>password</param-name>  
	<param-value>password</param-value>  
</init-param>
</servlet>
<servlet-mapping>
	<servlet-name>SampleAnnotationsServlet</servlet-name>
	<url-pattern>/SampleAnnotationsServlet</url-pattern>
</servlet-mapping>

*/

//@WebServlet(value = "/SampleAnnotationsServlet") 
@WebServlet(value = "/SampleAnnotationsServlet", initParams = { 
		   @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"), 
		   @WebInitParam(name = "user-name", value = "root") 
		}) 
public class SampleAnnotationsServlet extends HttpServlet {

   private static final long serialVersionUID = 1L; 

   protected void doGet(HttpServletRequest request, HttpServletResponse response)  
      throws ServletException, IOException { 
   
      response.setContentType("text/html");   
      PrintWriter out = response.getWriter();   
      out.print("<html><body>");   
      out.print("<h3>Hello Servlet</h3>");   
      out.println(getInitParameter("driver") + "<br>"); 
      out.println(getInitParameter("user-name")); 
      out.print("</body></html>");      
   }   
}
