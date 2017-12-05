package com.likya.pinara.infobus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.likya.pinara.Pinara;

public class ContentMail extends MultipartMail {

	public ContentMail(String mailType, String content) {
		try {
			// Instance name yanlış, artık tek bir senaryo yok !
			setMailSubject(Pinara.getMessage("EndOfCycleMail.0") + mailType /*Pinara.getInstance().getConfigurationManager().getPinaraConfig().getInstanceName()*/ + Pinara.getMessage(""));
			setMultipart(prepare(content));
			setMAIL_TYPE(PinaraMail.MULTIPART);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private Multipart prepare(String content) throws MessagingException, URISyntaxException {
		
		MimeMultipart multipart = new MimeMultipart("related");
	
		// HTML part
		MimeBodyPart mimeBodyPartHtml = new MimeBodyPart();
		// String localizedMessage = Pinara.getMessage("EndOfCycleMail.8"); //$NON-NLS-1$
		
		mimeBodyPartHtml.setText(content, "utf-8", "html");
		
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
