package com.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardAndRedirectServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = request.getParameter("page"); 
		
		if("forward".equals(pageName)) {
		
			RequestDispatcher rd = request.getRequestDispatcher("/WelcomeToForwardServlet?pageName="+pageName);
			rd.forward(request, response);
			
		} else if ("redirect".equals(pageName)) {
		
			response.sendRedirect(request.getContextPath() + "/WelcomeToRedirectServlet?pageName="+pageName);  
			
		} else if ("redirectToGoogle".equals(request.getParameter("page"))) {
		
			String name=request.getParameter("page");  
			response.sendRedirect("https://www.google.co.in/#q="+name);  
		}
	}

}
