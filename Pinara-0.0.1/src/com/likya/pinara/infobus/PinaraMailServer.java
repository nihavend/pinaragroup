package com.likya.pinara.infobus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.likya.pinara.Pinara;
import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;

/**
 * @author vista
 * 
 */
public class PinaraMailServer implements Runnable {

	private final int timeout = 10000;
	private boolean executePermission = true;
	private ArrayList<PinaraMail> mailQueue = new ArrayList<PinaraMail>();

	private Properties props;
	private Authenticator authenticator;

	private String userName;
	private String password;
	private String from;
	
	private MailInfo mailInfo;

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}

	}

	public PinaraMailServer(MailInfo mailInfo) {

		this.mailInfo = mailInfo;
		
		this.props = System.getProperties();
		authenticator = new SMTPAuthenticator();

		this.userName = mailInfo.getUserName();
		this.password = mailInfo.getUserPassword();

		this.from = this.userName;
				
		if(mailInfo.getSmtpServerHostName() == null) {
			props.put("mail.smtp.host", mailInfo.getSmtpServerIpAddress());
		} else {
			props.put("mail.smtp.host", mailInfo.getSmtpServerHostName());
		}
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", Integer.toString(mailInfo.getPort()));
		 
	}

	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			if (forcedTerminate) {
				mailQueue.clear();
			}
			this.executePermission = false;
		}
	}

	public void run() {
		
		Thread.currentThread().setName("PinaraMailServer");
		
		while (executePermission || mailQueue.size() > 0) {
			
			while (mailQueue.size() > 0 && mailInfo.getEmailList().sizeOfEmailArray() > 0) {
				
				PinaraMail pinaraMail = (PinaraMail) mailQueue.get(0);
				Pinara.getLogger().debug(Pinara.getMessage("PinaraMailServer.4")); 

				for(String emailAddress : mailInfo.getEmailList().getEmailArray()) {
					
					try {
						switch (pinaraMail.getMAIL_TYPE()) {

						case PinaraMail.SIMPLE:
							postMail(emailAddress, ((SimpleMail) pinaraMail).getMailSubject(), ((SimpleMail) pinaraMail).getMailText());
							break;
						case PinaraMail.WELCOME:
							postMultiPartMail(emailAddress, ((MultipartMail) pinaraMail).getMailSubject(), ((MultipartMail) pinaraMail).getMultipart());
							break;
						case PinaraMail.ENDOFCYCLE:
							postMultiPartMail(emailAddress, ((MultipartMail) pinaraMail).getMailSubject(), ((MultipartMail) pinaraMail).getMultipart());
							break;
						}
					} catch (Exception e) {
						Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + e.getMessage() + Pinara.getMessage("PinaraMailServer.6")); //$NON-NLS-1$ //$NON-NLS-2$
						e.printStackTrace();
					}
					
				}
				

				if(!mailQueue.isEmpty()) {
					mailQueue.remove(0);
				}
				
			}
			
			try {
				// TlosServer.getLogger().debug("Mail server sleeping !");
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.7"));
		Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.8") + mailQueue.size());
	}

	private void postMultiPartMail(String to, String subject, Multipart multipart) throws MessagingException {

		// Get session
		Session session = Session.getDefaultInstance(props, authenticator);

		// Define message
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);

		message.setSentDate(new Date());

		// Set the content for the message and transmit
		message.setContent(multipart);

		try {
		// Send message
			Transport.send(message);
		} catch(AuthenticationFailedException a) {
			Pinara.getLogger().info("Hatalı kullanıcı adı veya şifre nedeni ile e-posta sunucusuna bağlanamadı !");
			Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + Pinara.getMessage("PinaraMailServer.6")); 
		} catch(MessagingException me) {
			Pinara.getLogger().info(me.getLocalizedMessage());
			Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + Pinara.getMessage("PinaraMailServer.6")); 
		}
	}

	private void postMail(String to, String subject, String messageText) throws AddressException, MessagingException {

		// Get session
		Session session = Session.getDefaultInstance(props, authenticator);

		// Define message
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(messageText);

		// Send message
		Transport.send(message);
	}

	private synchronized void addMail(PinaraMail tlosMail) {
		mailQueue.add(tlosMail);
	}

	public void sendMail(PinaraMail tlosMail) {
		addMail(tlosMail);
	}

	public int getQueueSize() {
		return mailQueue.size();
	}
}
