package com.likya.myra.test.helpers;

import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.xsd.myra.model.generics.EmailListDocument.EmailList;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;
import com.likya.xsd.myra.model.stateinfo.StateInfosDocument.StateInfos;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;
import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument.PinaraConfig;

public class PinaraConfigGenerator {

	public static PinaraConfigDocument generate() throws Exception {

		PinaraConfigDocument pinaraConfigDocument = PinaraConfigDocument.Factory.newInstance();

		PinaraConfig pinaraConfig = pinaraConfigDocument.addNewPinaraConfig();
		
		// Attributes
		pinaraConfig.setVersion("0.0.1");
		pinaraConfig.setInstanceName("Myra Deployment Manager");
		
		// Elements
		
		pinaraConfig.setSenaryoDosyasi("/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/1.xml");
		// pinaraConfig.setServerIpAddress("127.0.0.1");
		pinaraConfig.setServerHostName("localhost");
		
		pinaraConfig.setHttpPort(3001);
		pinaraConfig.setHttpsPort(3002);
		pinaraConfig.setPersistent(false);
		
		MailInfo mailInfo = pinaraConfig.addNewMailInfo();
		
		StateInfos stateInfos = StateInfos.Factory.newInstance();
		LiveStateInfosType liveStateInfosType = stateInfos.addNewLiveStateInfos();
		LiveStateInfo liveStateInfo = liveStateInfosType.addNewLiveStateInfo();
		liveStateInfo.set(LiveStateInfoUtils.generateLiveStateInfo(StateName.INT_PENDING, SubstateName.INT_IDLED));
		
		mailInfo.setStateInfos(stateInfos);
		
		EmailList emailList = EmailList.Factory.newInstance();
		emailList.addEmail("myra-watchers@likyateknoloji.com");
		
		mailInfo.setEmailList(emailList);
		mailInfo.setUseEncryption(false);
		mailInfo.setUserName("myra@likyateknoloji.com");
		mailInfo.setUserPassword("dddddd");
		mailInfo.setSmtpServerHostName("mail.likyateknoloji.com");
		mailInfo.setPort(587);
		
		/*
		<xs:element ref="tcpInfo" minOccurs="0" maxOccurs="1" />
		<xs:element ref="mcInfo" minOccurs="0" maxOccurs="1" />
		<xs:element ref="smsInfo" minOccurs="0" maxOccurs="1" />
		*/
		
		pinaraConfig.setLogFile("myra.log");
		pinaraConfig.setLogPath("./");
		
		XmlObject xmlObject = XmlObject.Factory.parse(pinaraConfigDocument.toString());

		PinaraConfigDocument pinaraConfigDocumentNew = PinaraConfigDocument.Factory.parse(xmlObject.toString());

		System.err.println(pinaraConfigDocumentNew.toString());

		System.err.println("Valid = " + pinaraConfigDocumentNew.validate());

		return pinaraConfigDocumentNew;

	}
}
