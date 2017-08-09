package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1. Download and run the fakesmtp.jar
 * 2. Download URL: http://nilhcem.com/FakeSMTP/download.html
 * 3. jar name: fakeSMTP-2.0.jar
 * 4. Start this fake SMTP server at port: 2525
 */

public class SendEmailInHTMLServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fromEmailId = request.getParameter("fromEmailId");
		String toEmailId = request.getParameter("toEmailId");
		String ccEmailId = request.getParameter("ccEmailId");
		String btoEmailId = request.getParameter("btoEmailId");
		String emailSubject = request.getParameter("emailSubject");
		String emailBody = request.getParameter("emailBody");

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "2525");


		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmailId));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId));

			// Set Subject: header field
			message.setSubject(emailSubject);

			// Now set the actual message
			//message.setText(emailBody);
			
			 // Send the actual HTML message, as big as you like
	         message.setContent("<h1>Dear Sir/Madam, "
	         		+ "<br>Please read actual message as below</h1>"
	         		+ "<br>"
	         		+ "<p style='font-size:20px ;color:green;'>" + emailBody + "</p>", "text/html" );
			
			// Send message
			Transport.send(message);
			
			String title = "Send Email";
			String res = "Sent message successfully....";
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

			out.println(docType +
					"<html>\n" +
					"<head><title>" + title + "</title></head>\n" +
					"<body bgcolor = \"#f0f0f0\">\n" +
					"<h1 align = \"center\">" + title + "</h1>\n" +
					"<p align = \"center\">" + res + "</p>\n" +
					"</body></html>"
					);
			
		} catch (MessagingException mex) {
			
			mex.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} 