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
import com.likya.pinara.utils.MailContentHelper;

public class EndOfCycleMail extends MultipartMail {

	public EndOfCycleMail(String virtualId, HashMap<String, JobImpl> jobQueue) {
		try {
			// Instance name yanlış, artık tek bir senaryo yok !
			setMailSubject(Pinara.getMessage("EndOfCycleMail.0") + virtualId /*Pinara.getInstance().getConfigurationManager().getPinaraConfig().getInstanceName()*/ + Pinara.getMessage("EndOfCycleMail.1"));
			setMultipart(prepareEndOfCycleMail(jobQueue));
			setMAIL_TYPE(PinaraMail.MULTIPART);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private Multipart prepareEndOfCycleMail(HashMap<String, JobImpl> jobQueue) throws MessagingException, URISyntaxException {
		
		MimeMultipart multipart = new MimeMultipart("related");
	
		// HTML part
		MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
		// String localizedMessage = Pinara.getMessage("EndOfCycleMail.8"); //$NON-NLS-1$
		String mailHtml = MailContentHelper.getHTMLFormattedJobProperties(jobQueue);
		
		mimeBodyPartHtml.setText(mailHtml, "utf-8", "html");
		
		// Image part
		MimeBodyPart imagePart = new MimeBodyPart();
		try {
			String imgUrl = "/com/likya/pinara/resources/likya_mail.jpg";
			URL url = this.getClass().getResource(imgUrl);
			imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(url.openStream(), "image/jpg")));
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