package com.likya.pinara.utils.xml.mappers;

import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;

public class ConfigurationMapper {
	
	public static String getMappedMail(MailInfo mailInfo) {
		
		String mailInfoResult = "";
		
		try {
			mailInfoResult = removeNameSpaces(mailInfo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mailInfoResult;
	}
	
	protected static String removeNameSpaces(String inXmlTxt) throws Exception {
		
		String lastRetString = JobGridListMapper.removeNameSpaces(inXmlTxt);
		
		lastRetString = lastRetString.replace("xmlns:pin=\"http://www.likyateknoloji.com/pinara-config\"", "");
		lastRetString = lastRetString.replace("xmlns:myra=\"http://www.likyateknoloji.com/myra-stateinfo\"", "");
		
		lastRetString = lastRetString.replace("<pin:", "<");
		lastRetString = lastRetString.replace("</pin:", "</");
		
		return lastRetString;
	}

}
