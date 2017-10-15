package com.likya.myra.commons.utils;

import java.util.ArrayList;

import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.joblist.SimpleProperties;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;

public class XMLValidations {
	
	public static void validateWithCode(JobList jobList, Logger logger) {
		validatePeriodInfo(jobList, logger);
	}
	
	private static void validatePeriodInfo(JobList jobList, Logger logger) {
		
		ArrayIterator jobListIterator = new ArrayIterator(jobList.getGenericJobArray());
		
		JobList validJobList = JobList.Factory.newInstance();
		
		while (jobListIterator.hasNext()) {
			
			SimpleProperties simpleProperties = (SimpleProperties) (jobListIterator.next());

			PeriodInfo periodInfo = simpleProperties.getManagement().getPeriodInfo();

			if ((periodInfo != null) && (periodInfo.getMaxCount() == null || (periodInfo.getCounter().intValue() + 1) >= periodInfo.getMaxCount().intValue())) {
				
				XMLValidations.errprintln("*************************************************************");
				XMLValidations.errprintln("Validation error : periodInfo.getMaxCount() is null or period counter exceeds maxcount value, job is removed from execution queue !");
				XMLValidations.errprintln("Removed job id : " + simpleProperties.getId());
				XMLValidations.errprintln("*************************************************************");
				
				continue;
			}
			
			validJobList.addNewGenericJob().set(simpleProperties);
			
		}
		
		jobList.setGenericJobArray(validJobList.getGenericJobArray());
		
		return;
	}
	
	public static boolean validateWithXSDAndLog(Logger logger, XmlObject xmlObject, ArrayList<String> errMsgList) {
		
		/**
		 * @reference http://xmlbeans.apache.org/docs/1.0.4/reference/org/apache/xmlbeans/XmlObject.html#validate%28%29
		 */

		// Create an XmlOptions instance and set the error listener.
		XmlOptions validateOptions = new XmlOptions();
		ArrayList<XmlError> errorList = new ArrayList<XmlError>();
		validateOptions.setErrorListener(errorList);

		// Validate the XML.
		boolean isValid = xmlObject.validate(validateOptions);

		// If the XML isn't valid, loop through the listener's contents,
		// printing contained messages.
		
		if (!isValid) {
			
			XMLValidations.errprintln("*************************************************************");
			logger.error("*************************************************************");
			logger.error("Validating xmlObject class : " + xmlObject.getClass().getName());
			XMLValidations.errprintln("xmlObject : " + xmlObject.getClass().getName());
			logger.error("Found " + errorList.size() + " validation errors !");
			XMLValidations.errprintln("Found " + errorList.size() + " validation errors !");
			
			for (int i = 0; i < errorList.size(); i++) {
				XmlError error = (XmlError) errorList.get(i);
				
				
				String str1 = "Validation error : " + (i + 1);
				String str2 = "	> Message: " + error.getMessage();
				String str3 = "	> Location of invalid XML : ";
				String str4 = "		" + error.getCursorLocation().xmlText();
				String str5 = "	";
						
				logger.error(str1);
				logger.error(str2);
				logger.error(str3);
				logger.error(str4);
				logger.error(str5);
				
				XMLValidations.errprintln(str1);
				XMLValidations.errprintln(str2);
				XMLValidations.errprintln(str3);
				XMLValidations.errprintln(str4);
				XMLValidations.errprintln(str5);
				
				if(errMsgList != null) {
					errMsgList.add(str1);
					errMsgList.add(str2);
					errMsgList.add(str3);
					errMsgList.add(str4);
					errMsgList.add(str5);
				}

			}
			
			XMLValidations.errprintln("*************************************************************");
			logger.error("*************************************************************");
		}

		return isValid;
	}
	
	public static boolean validateWithXSDAndLog(Logger logger, XmlObject xmlObject) {
		return validateWithXSDAndLog(logger, xmlObject, null);
	}
	
	public static void errprintln(String message) {
		System.err.println(message);
	}
}
