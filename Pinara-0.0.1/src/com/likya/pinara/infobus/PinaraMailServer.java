package com.likya.pinara.infobus;

import java.net.UnknownHostException;
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

import org.apache.xmlbeans.XmlException;

import com.likya.pinara.Pinara;
import com.likya.xsd.pinara.model.config.MailInfoDocument;
import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;

/**
 * @author vista
 * 
 */
public class PinaraMailServer implements Runnable {

	private final int timeout = 10000;
	private boolean executePermission = true;
	private ArrayList<PinaraMail> mailQueue;
	private final int MAX_THRESHOLD_VALUE = 300;

	private Properties props;
	private Authenticator authenticator;

	private MailInfo mailInfo;
	
	private String userName;
	private String password;
	private String from;
	
	private Thread executorThread;
	
	private String mailInfoStr;
	private boolean reLoadParamsFlag = false;
	
	private boolean mailSent;
	private int mailSendingAttemptCount;
	private int mailSendingAttemptInterval;
	
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

		this.from = mailInfo.getFrom();
		
		this.executePermission = true;
		this.reLoadParamsFlag = false;
		
		this.mailQueue = new ArrayList<PinaraMail>();
			
		/*
		if(mailInfo.getSmtpServerHostName() == null) {
			props.put("mail.smtp.host", mailInfo.getSmtpServerIpAddress());
		} else {
			props.put("mail.smtp.host", mailInfo.getSmtpServerHostName());
		}
		
		props.put("mail.smtp.auth", mailInfo.getUseEncryption());
		props.put("mail.smtp.port", Integer.toString(mailInfo.getPort()));
		*/
		
		for(String prop : mailInfo.getMailProps().getPropArray()) {
			// System.out.println(prop.split(",")[0] + " " + prop.split(",")[1]);
			props.put(prop.split(",")[0], prop.split(",")[1]);
		}
		
	}
	
	private void reLoadParams() {
		
		for(String prop : mailInfo.getMailProps().getPropArray()) {
			props.remove(prop.split(",")[0], prop.split(",")[1]);
		}

		try {
			this.mailInfo = (MailInfoDocument.Factory.parse(mailInfoStr)).getMailInfo();
			Pinara.getInstance().getConfigurationManager().getPinaraConfig().setMailInfo(mailInfo);
		} catch (XmlException e) {
			e.printStackTrace();
		}
		
		authenticator = new SMTPAuthenticator();

		this.userName = mailInfo.getUserName();
		this.password = mailInfo.getUserPassword();

		this.from = mailInfo.getFrom();
			
		for(String prop : mailInfo.getMailProps().getPropArray()) {
			props.put(prop.split(",")[0], prop.split(",")[1]);
		}
		
		reLoadParamsFlag = false;
	}

	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			if (forcedTerminate) {
				mailQueue.clear();
			}
			this.executePermission = false;
		}
	}
	
	private void waitNextSendingAttempt() {
		if(mailSendingAttemptCount == 1) {
			mailSendingAttemptInterval = 1 * 60 * 1000;
		} else if(mailSendingAttemptCount == 2) {
			mailSendingAttemptInterval = 5 * 60 * 1000;
		} else if(mailSendingAttemptCount == 3) {
			mailSendingAttemptInterval = 15 * 60 * 1000;
		} else {
			mailSendingAttemptInterval = 30 * 60 * 1000;
		}
		
		Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.13") + mailSendingAttemptInterval/(60 * 1000));
		mailSendingAttemptCount += 1;
		
		try {
			Thread.sleep(mailSendingAttemptInterval);
			Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.12"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void engage(MailInfo mailInfo) throws Throwable{
		synchronized (PinaraMailServer.class) {
			PinaraMailServer pinaraMailServer = Pinara.getInstance().getConfigurationManager().getPinaraMailServer();
			
			if (pinaraMailServer == null || pinaraMailServer.getExecutorThread() == null || pinaraMailServer.getExecutorThread().getState().equals(Thread.State.TERMINATED)) {
				PinaraMailServer newPinaraMailServer = new PinaraMailServer(mailInfo);
				Thread pinaraMailServerThread = new Thread(newPinaraMailServer);
				newPinaraMailServer.setExecutorThread(pinaraMailServerThread);
				pinaraMailServerThread.start();	
				
				Pinara.getInstance().getConfigurationManager().setPinaraMailServer(newPinaraMailServer);
				Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.10"));
			} else {
				throw new Exception("PinaraMailServer is still running!");
			}
		}
	}

	public void run() {
		
		Thread.currentThread().setName("PinaraMailServer");
		
		while (executePermission || mailQueue.size() > 0) {
			
			while (mailQueue.size() > 0 && mailInfo.getEmailList().sizeOfEmailArray() > 0) {
				
				if(reLoadParamsFlag) {
					reLoadParams();
				}
				
				PinaraMail pinaraMail = (PinaraMail) mailQueue.get(0);
				Pinara.getLogger().debug(Pinara.getMessage("PinaraMailServer.4")); 

				for(String emailAddress : mailInfo.getEmailList().getEmailArray()) {
					
					mailSent = false;
					mailSendingAttemptCount = 1;
					while(!mailSent) {
						try {
							switch (pinaraMail.getMAIL_TYPE()) {
	
							case PinaraMail.SIMPLE:
								postMail(emailAddress, ((SimpleMail) pinaraMail).getMailSubject(), ((SimpleMail) pinaraMail).getMailText());
								break;
							case PinaraMail.MULTIPART:
								postMultiPartMail(emailAddress, ((MultipartMail) pinaraMail).getMailSubject(), ((MultipartMail) pinaraMail).getMultipart());
								break;
							}
							
							mailSent = true;
							Pinara.getLogger().debug("E-posta gönderdildi !"); 
						//Exception bazında ayrım istenirse (tekrar deneme için), tekrar istemeyen exceptionlara 'mailSent = true;' eklemek yeterli 	
						} catch(AuthenticationFailedException a) {
							Pinara.getLogger().error(Pinara.getMessage("PinaraMailServer.9"));
							Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + Pinara.getMessage("PinaraMailServer.11") + mailSendingAttemptCount); 
							//a.printStackTrace();
						} catch(MessagingException me) {
							Pinara.getLogger().error(me.toString());
							Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + Pinara.getMessage("PinaraMailServer.11") + mailSendingAttemptCount);
							//me.printStackTrace();
						}  catch(UnknownHostException ue) {
							Pinara.getLogger().error(ue.toString());
							Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + Pinara.getMessage("PinaraMailServer.11") + mailSendingAttemptCount);
							//ue.printStackTrace();
						}  catch (Exception e) {
							Pinara.getLogger().error(e.toString());
							Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.5") + e.getMessage() + Pinara.getMessage("PinaraMailServer.11") + mailSendingAttemptCount); //$NON-NLS-1$ //$NON-NLS-2$
							//e.printStackTrace();
						}
						
						if(!mailSent) {
							waitNextSendingAttempt(); 
						}
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

	private void postMultiPartMail(String to, String subject, Multipart multipart) throws MessagingException, AuthenticationFailedException, UnknownHostException {

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
		
		// Send message
		Transport.send(message);
	}

	private void postMail(String to, String subject, String messageText) throws AddressException, MessagingException, UnknownHostException {

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
		if(mailQueue.size() < MAX_THRESHOLD_VALUE) {
			addMail(tlosMail);
		} else {
			Pinara.getLogger().warn(Pinara.getMessage("PinaraMailServer.14"));
		}
	}

	public int getQueueSize() {
		return mailQueue.size();
	}

	public Thread getExecutorThread() {
		return executorThread;
	}

	private void setExecutorThread(Thread executorThread) {
		this.executorThread = executorThread;
	}

	public void setReLoadParamsFlag(boolean reLoadParamsFlag) {
		this.reLoadParamsFlag = reLoadParamsFlag;
	}

	public void setMailInfoStr(String mailInfoStr) {
		this.mailInfoStr = mailInfoStr;
	}
	
}
