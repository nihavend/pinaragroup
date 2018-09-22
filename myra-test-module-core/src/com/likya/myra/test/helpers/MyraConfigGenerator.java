package com.likya.myra.test.helpers;

import org.apache.xmlbeans.XmlObject;

import com.likya.xsd.myra.model.config.MyraConfigDocument;
import com.likya.xsd.myra.model.config.MyraConfigDocument.MyraConfig;
import com.likya.xsd.myra.model.config.UsejobnamesforlogDocument.Usejobnamesforlog;

public class MyraConfigGenerator {

	public static MyraConfigDocument generate() throws Exception {

		MyraConfigDocument myraConfigDocument = MyraConfigDocument.Factory.newInstance();

		MyraConfig myraConfig = myraConfigDocument.addNewMyraConfig();
		myraConfig.setNormalize(false);
		myraConfig.setPersistent(false);
		myraConfig.setFrequency((short)1);
		myraConfig.setHigherThreshold((short)3);
		myraConfig.setLowerThreshold((short)1);
		
//		MailInfo mailInfo = myraConfig.addNewMailInfo();
//		mailInfo.setUseMail(UseMail.NO);
//		
//		StateInfos stateInfos = StateInfos.Factory.newInstance();
//		LiveStateInfosType liveStateInfosType = stateInfos.addNewLiveStateInfos();
//		LiveStateInfo liveStateInfo = liveStateInfosType.addNewLiveStateInfo();
//		liveStateInfo.set(LiveStateInfoUtils.generateLiveStateInfo(StateName.INT_PENDING, SubstateName.INT_IDLED));
//		
//		mailInfo.setStateInfos(stateInfos);
//		
//		EmailList emailList = EmailList.Factory.newInstance();
//		emailList.addEmail("myra-watchers@likyateknoloji.com");
//		
//		mailInfo.setEmailList(emailList);
//		mailInfo.setUseEncryption(UseEncryption.NO);
//		mailInfo.setUserName("myra@likyateknoloji.com");
//		mailInfo.setUserPassword("dddddd");
//		mailInfo.setSmtpServerHostName("mail.likyateknoloji.com");
//		mailInfo.setPort(587);
		
		myraConfig.setLogPath("./");
		myraConfig.setUsejobnamesforlog(Usejobnamesforlog.NO);
		myraConfig.setLogFileExt(".log");
		myraConfig.setGlobalLogPath("./");
		myraConfig.setLogbuffersize((short)800);
		myraConfig.setLogpagesize((short)10);
		
		XmlObject xmlObject = XmlObject.Factory.parse(myraConfigDocument.toString());

		MyraConfigDocument myraConfigDocumentNew = MyraConfigDocument.Factory.parse(xmlObject.toString());

		System.err.println(myraConfigDocumentNew.toString());

		System.err.println("Valid = " + myraConfigDocumentNew.validate());

		return myraConfigDocumentNew;

	}
}
