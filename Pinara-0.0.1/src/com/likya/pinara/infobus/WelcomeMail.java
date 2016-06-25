package com.likya.pinara.infobus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.Pinara;
import com.likya.pinara.utils.MailContentHelper;

public class WelcomeMail extends MultipartMail {

	public WelcomeMail(HashMap<String, JobImpl> jobQueue) {
		try {
			setMailSubject(Pinara.getMessage("WelcomeMail.0") + Pinara.getInstance().getConfigurationManager().getPinaraConfig().getInstanceName() + Pinara.getMessage("WelcomeMail.1"));
			setMultipart(prepareWelcomeMail(jobQueue));
			setMAIL_TYPE(PinaraMail.WELCOME);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public Multipart prepareWelcomeMailOld(HashMap<String, JobImpl> jobQueue) throws MessagingException, URISyntaxException {

		// Create an "Alternative" Multipart message
		Multipart multipart = new MimeMultipart("alternative");

		MimeBodyPart mimeBodyPartPlain = new MimeBodyPart();
		// bp.setDataHandler(new DataHandler(new FileDataSource(filename)));

		// Düz metin
		String mailText = Pinara.getMessage("WelcomeMail.3");
		mailText += Pinara.getMessage("WelcomeMail.4");
		mailText += MailContentHelper.getSimpleFormattedJobProperties(jobQueue);
		mailText += Pinara.getMessage("WelcomeMail.5");
		mailText += Pinara.getMessage("WelcomeMail.6");

		mimeBodyPartPlain.setDataHandler(new DataHandler(mailText, "text/plain; charset=UTF-8"));
		multipart.addBodyPart(mimeBodyPartPlain);
		// Düz metin sonu

		MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
		String mailHtml = MailContentHelper.getHTMLFormattedJobProperties(jobQueue);

		mimeBodyPartHtml.setContent(mailHtml, "text/html; charset=UTF-8 ");

		multipart.addBodyPart(mimeBodyPartHtml);
		
		MimeBodyPart mimeBodyPartImage = new MimeBodyPart();
		String imgUrl = "/com/likya/pinara/resources/likya_mail.jpg";
		URL url = this.getClass().getResource(imgUrl);
		// new
		// URL("jar:file:/D:/tmp/LikyaTlosOrj.jar!/webroot/img/likya_mail.jpg")
		if (url == null) {
			Pinara.getLogger().warn(Pinara.getMessage("WelcomeMail.10") + imgUrl);
		} else {
			try {
				mimeBodyPartImage.setDataHandler(new DataHandler(new ByteArrayDataSource(url.openStream(), "image/jpg")));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//
			// String urlString = url.toString();
			// URI uri = new URI(urlString.replace("jar:file:/",
			// "jar:file://"));
			// try {
			// FileDataSource fds = new FileDataSource(new File(uri));
			// mimeBodyPartImage.setDataHandler(new DataHandler(fds));
			// mimeBodyPartImage.setFileName(fds.getName());
			// } catch(IllegalArgumentException il) {
			//			
			// }

			// FileDataSource fds = new FileDataSource(new
			// File(getClass().getResource("/webroot/img/likya_mail.jpg").getFile()));

			mimeBodyPartImage.setHeader("Content-ID", "<likyajpg10976@likyateknoloji.com>");
			multipart.addBodyPart(mimeBodyPartImage);
		}


		return multipart;

	}

	private Multipart prepareWelcomeMail(HashMap<String, JobImpl> jobQueue) throws MessagingException, URISyntaxException {
		
		MimeMultipart multipart = new MimeMultipart("related");
	
		// HTML part
		MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
		String mailHtml = MailContentHelper.getHTMLFormattedJobProperties(jobQueue);
		
//		Test için
//		try {
//			FileOutputStream outputStream = new FileOutputStream("serkan.xml");
//			outputStream.write(mailHtml.getBytes());
//			outputStream.close();
//		} catch(Throwable t) {
//			t.printStackTrace();
//		}
		
		
		mimeBodyPartHtml.setText(mailHtml, "utf-8", "html");
		
		// Image part
		MimeBodyPart imagePart = new MimeBodyPart();
		try {
			imagePart.attachFile("src/com/likya/pinara/resources/likya_mail.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imagePart.setHeader("Content-ID", "<likyajpg10976@likyateknoloji.com>");
		imagePart.setDisposition(MimeBodyPart.INLINE);

		
		multipart.addBodyPart(mimeBodyPartHtml);
		multipart.addBodyPart(imagePart);
		
		return multipart;
		
	}
}
