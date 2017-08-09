package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetHTTPHeaderDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
	      response.setContentType("text/html");
	 
	      PrintWriter out = response.getWriter();
	      String title = "HTTP Header Request Example";
			
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
	         "transitional//en\">\n";
				
	      out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n"+
	         "<body bgcolor=\"#f0f0f0\">\n" +
	         "<h1 align=\"center\">" + title + "</h1>\n" +
	         "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
	         "<tr bgcolor=\"#949494\">\n" +
	         "<th>Header Name</th><th>Header Value(s)</th>\n"+
	         "</tr>\n");
	 
	      Enumeration<String> headerNames = request.getHeaderNames();
	      
	      while(headerNames.hasMoreElements()) {
	         String paramName = (String)headerNames.nextElement();
	         out.print("<tr><td>" + paramName + "</td>\n");
	         String paramValue = request.getHeader(paramName);
	         out.println("<td> " + paramValue + "</td></tr>\n");
	      }
			
	      out.println("</table>\n</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
