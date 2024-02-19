package utilities;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment {

	public static void main(String[] args) 
	{
		SendMailSSLWithAttachment mailService= new SendMailSSLWithAttachment();
		mailService.m1();
	}
	public void m1() {
		
	
		// Create object of Property file
		Properties props = new Properties();
		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

				//	return new PasswordAuthentication("add your email", "add your password");
					return new PasswordAuthentication("premkumar@tautmore.com", "tautmore@125");

					}	

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("premkumar@tautmore.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("pkumarmk125@gmail.com"));
            
                        // Add the subject link
			message.setSubject("Testing Subject: BrainGym Automation Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("This is message body: Automation test report for Braingym Test");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = "C:\\Users\\user\\Documents\\selenium_Course\\workspace\\TautmoreDotCom\\test-output\\emailable-report.html";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent =====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

}