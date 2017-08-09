package com.test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/*
 * 1. Download and run the fakesmtp.jar
 * 2. Download URL: http://nilhcem.com/FakeSMTP/download.html
 * 3. jar name: fakeSMTP-2.0.jar
 * 4. Start this fake SMTP server at port: 2525
 */

public class SendEmailWithAttachmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String filePath;
	
	public void init( ){
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload"); 
		System.out.println(filePath);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		   
		PrintWriter out = response.getWriter();
		
		if( !ServletFileUpload.isMultipartContent(request) ) {
			Date date = new Date();

		   // Set response content type
		   String title = "Error at submitting multipart/form-data form!";
		   String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		   String sendEmailWithAttachmentPage = "http://localhost:8080/ServletExamplesApp/sendEmailWithAttachment.html";
		   
		   out.println(docType +
		      "<html>\n" +
		      "<head><title>" + title + "</title></head>\n"+
		      "<body bgcolor = \"#f0f0f0\">\n" +
		      "<h1 align = \"center\">" + title + "</h1>\n" +
		      "<p style='font-size:25px;'>Current Time is: " + date + "</p>\n" + 
		      "<p style='font-size:25px;'>Submitted for is not multipart/form-data!</p>\n" + 
		      "<a href='" + sendEmailWithAttachmentPage + "'>Goto send Email With Attachment Page...</a>"
		   );
		    out.println("</body></html>");
			return;
		}
		List<File> attachedFileList = new ArrayList<File>();
		Map<String, String> formFieldMap = new HashMap<String, String>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5000 * 1024);  // maximum size that will be stored in memory
		factory.setRepository(new File("c:\\temp")); // Location to save data that is larger than maxMemSize.
		ServletFileUpload upload = new ServletFileUpload(factory);  // Create a new file upload handler

		upload.setSizeMax( 5000 * 1024 );  // maximum file size to be uploaded.
		try { 
			List fileItems = upload.parseRequest(request);  // Parse the request to get file items.
			Iterator i = fileItems.iterator(); // Process the uploaded file items
			while ( i.hasNext () ) {
				FileItem fi = (FileItem)i.next(); // contains contentType, isInMemory, size etc..
				if ( !fi.isFormField () ) {
					
					String fileName = fi.getName(); // Get the uploaded file parameters

					// Write the file
					File file;
					if( fileName.lastIndexOf("\\") >= 0 ) {
						file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
					} else {
						file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					fi.write( file ) ;
					
					attachedFileList.add(file);
					
				}  else if (fi.isFormField()) {
                    formFieldMap.put(fi.getFieldName(), fi.getString());
                }  
			}
			System.out.println("attachedFileList = " + attachedFileList);
			System.out.println("Form Fields = " + formFieldMap);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//Map<String, String> formFieldMap = getFormFields(request, response);
		File filename = attachedFileList.get(0);
		String fromEmailId = (formFieldMap.containsKey(new String("fromEmailId"))) ? formFieldMap.get("fromEmailId") : null;
		String toEmailId = (formFieldMap.containsKey(new String("toEmailId"))) ? formFieldMap.get("toEmailId") : null;
		String ccEmailId = (formFieldMap.containsKey(new String("ccEmailId"))) ? formFieldMap.get("ccEmailId") : null;
		String btoEmailId = (formFieldMap.containsKey(new String("btoEmailId"))) ? formFieldMap.get("btoEmailId") : null;
		String emailSubject = (formFieldMap.containsKey(new String("emailSubject"))) ? formFieldMap.get("emailSubject") : null;
		String emailBody = (formFieldMap.containsKey(new String("emailBody"))) ? formFieldMap.get("emailBody") : null;

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "2525");


		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			  MimeMessage message = new MimeMessage(session); // Create a default MimeMessage object.

			  message.setFrom(new InternetAddress(fromEmailId)); // Set From: header field of the header.

			  message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailId)); // Set To: header field of the header.

			  message.setSubject(emailSubject); // Set Subject: header field
			
		      BodyPart messageBodyPart = new MimeBodyPart(); // Create the message part 

		      // messageBodyPart.setText("This is message body"); // Fill the message
		      messageBodyPart.setContent("<h1>Please read actual message as below!</h1>"
		         		+ "<br>"
		         		+ "<p style='font-size:20px ;color:green;'>" + emailBody + "</p>", "text/html" );
		      
		      Multipart multipart = new MimeMultipart(); // Create a multipart message

		      multipart.addBodyPart(messageBodyPart); // Set text message part

		      messageBodyPart = new MimeBodyPart(); // Part two is attachment
		      
		      DataSource source = new FileDataSource(filename);
		      
		      messageBodyPart.setDataHandler(new DataHandler(source));
		      messageBodyPart.setFileName(filename.getName());
		      multipart.addBodyPart(messageBodyPart);

		      message.setContent(multipart ); // Send the complete message parts
			
			 // Send the actual HTML message, as big as you like
	         message.setContent("<h1>Dear Sir/Madam, "
	         		+ "<br>Please read actual message as below</h1>"
	         		+ "<br>"
	         		+ "<p style='font-size:20px ;color:green;'>" + emailBody + "</p>", "text/html" );
			
			Transport.send(message); // Send message
			
			String title = "Send Email";
			String res = "Sent message successfully....";
			String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
			String sendEmailWithAttachmentPage = "http://localhost:8080/ServletExamplesApp/sendEmailWithAttachment.html";
			out.println(docType +
					"<html>\n" +
					"<head><title>" + title + "</title></head>\n" +
					"<body bgcolor = \"#f0f0f0\">\n" +
					"<h1 align = \"center\">" + title + "</h1>\n" +
					"<p align = \"center\">" + res + "</p>\n" + 
				    "<a href='" + sendEmailWithAttachmentPage + "'>Goto send Email With Attachment Page...</a>" +
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