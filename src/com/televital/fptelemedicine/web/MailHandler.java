package com.televital.fptelemedicine.web;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailHandler {
	public static final Logger log4log = Logger.getLogger(MailHandler.class);
	private  String SMTP_AUTH_USER;
	private  String SMTP_AUTH_PWD;
	  // Add List of Email address to who email needs to be sent to
	  private static final String[] emailList = {"sud.tech@gmail.com","rdpkst@hotmail.com","pkbhatkumta@hotmail.com","juliechinna@gmail.com","sateesh@televital.com","bhatsateesh@gmail.com","juliet@televital.com","tabrezsajjad69@rediffmail.com","dilip641@hotmail.com","tabrez.t@gmail.com"};

	  public void postMail(String recipients[ ],String subject,String message,String from,int portNo,String hostName,String user,String password) 
	  {
	    boolean debug = false;
	   
        try
        {
        	SMTP_AUTH_USER=user;
        	SMTP_AUTH_PWD=password;
	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("mail.smtp.host", hostName);
	     props.put("mail.smtp.auth", "true");
	     //props.put("mail.smtp.starttls.enable", "true");

	     props.put("mail.debug", "true");

	    Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);

	    session.setDebug(debug);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length];
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);


	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");

	    Transport transport = session.getTransport("smtp");
	    transport.connect(hostName,portNo,user,password);


	    Transport.send(msg);
	    transport.close();
	   
        }
        catch (Exception e)
        {
        	log4log.error("Exception Due To : "+e);
        }
	 }


	/**
	* SimpleAuthenticator is used to do simple authentication
	* when the SMTP server requires it.
	*/
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}

	}



