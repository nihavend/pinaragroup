package com.likya.pinara.infobus;

import java.io.IOException;
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

public class GoodByeMail extends MultipartMail {

	public GoodByeMail(HashMap<String, JobImpl> jobQueue) {
		try {
			setMailSubject(Pinara.getMessage("GoodByeMail.0") + Pinara.getInstance().getConfigurationManager().getPinaraConfig().getInstanceName() + Pinara.getMessage("GoodByeMail.1"));
			setMultipart(prepareWelcomeMail(jobQueue));
			setMAIL_TYPE(PinaraMail.MULTIPART);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private Multipart prepareWelcomeMail(HashMap<String, JobImpl> jobQueue) throws MessagingException, URISyntaxException {
		
		MimeMultipart multipart = new MimeMultipart("related");
	
		// HTML part
		MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
		String mailHtml = "Pınara kapatıldı..."; // MailContentHelper.getHTMLFormattedJobProperties(jobQueue);
		
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
			String imgUrl = "/com/likya/pinara/resources/likya_mail.jpg";
			URL url = this.getClass().getResource(imgUrl);
			imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(url.openStream(), "image/jpg")));
			// imagePart.attachFile("/com/likya/pinara/resources/likya_mail.jpg");
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
