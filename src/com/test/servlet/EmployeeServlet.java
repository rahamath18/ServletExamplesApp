package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		System.out.println("EmployeeServlet....." + date);

	   // Set response content type
	   response.setContentType("text/html");
	   
	   PrintWriter out = response.getWriter();
	   String title = "Employee Details";
	   String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

	   String empCreatePage = "http://localhost:8080/ServletExamplesApp/employee.html";
	   
	   out.println(docType +
	      "<html>\n" +
	      "<head><title>" + title + "</title></head>\n"+
	      "<body bgcolor = \"#f0f0f0\">\n" +
	      "<h1 align = \"center\">" + title + "</h1>\n" +
	      "<p style='font-size:25px;'>Current Time is: " + date + "</p>\n" + 
	      "<p style='font-size:25px;'>Employee record created successfully!</p>\n" + 
	      "<a href='" + empCreatePage + "'>Create Employee Details</a>"
	   );
	    out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EmployeeServlet.....");
		// Method 1: different method to get form details
		Map<String, String[]> paraMap = request.getParameterMap();
		System.out.println(paraMap);
		Set<String> nameSet = paraMap.keySet();
		for (String paraName : nameSet) {
			System.out.print(paraName + " == ");
			String[] values = paraMap.get(paraName);
			for (String value : values) {
				System.out.print(value);
			}
			System.out.println();
		}
		
		// Method 2: different method to get form details
		Enumeration<String> en = request.getParameterNames();
		while(en.hasMoreElements()) {
			String paraName = en.nextElement();
			System.out.println(paraName + " = " + request.getParameter(paraName));
		}
		
		// Method 3: different method to get form details

		String name = request.getParameter("name");
		String salary = request.getParameter("salary");
		System.out.println(name + ", " + salary);
		
		// insert into db
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/servletdb";
			String username = "root";
			String password = "password"; // Change it to your Password
			System.setProperty(driver, "");
			Connection conn = DriverManager.getConnection(url, username,password);
			
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO `servletdb`.`EMPLOYEE`(`ID`,`EMPNAME`,`SALARY`) ")
			.append("VALUES(?, ?, ?);");
			
			PreparedStatement s = conn.prepareStatement(sb.toString());
			s.setInt(1, new Integer(0));
			s.setString(2, name);
			s.setDouble(3, Double.valueOf(salary));

			s.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
