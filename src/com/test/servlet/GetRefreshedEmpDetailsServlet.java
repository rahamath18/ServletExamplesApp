package com.test.servlet;

//Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

//Extend HttpServlet class
public class GetRefreshedEmpDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

// Method to handle GET method request.
public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
	
	Date date = new Date();
	System.out.println("GetRefreshedEmpDetailsServlet....." + date);

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
      "<p style='font-size:25px;'>Current Time is: " + date + "</p>\n" + 
      "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
      "<tr bgcolor=\"#949494\">\n" +
      "<th>Emp ID</th><th>Emp Name</th>\n"+
      "</tr>\n"
   );
	
	try {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/servletdb";
		String username = "root";
		String password = "password"; // Change it to your Password
		System.setProperty(driver, "");
		Connection conn = DriverManager.getConnection(url, username,password);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from servletdb.employee");
		
		PreparedStatement s = conn.prepareStatement(sb.toString());
		ResultSet rs = s.executeQuery();
		while(rs.next()) {
			
			out.print("<tr><td>" + rs.getString("id") + "</td>\n");
	        out.println("<td> " + rs.getString("empname") + "</td></tr>\n");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
    out.println("</table>\n</body></html>");
}

// Method to handle POST method request.
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   doGet(request, response);
}
}
