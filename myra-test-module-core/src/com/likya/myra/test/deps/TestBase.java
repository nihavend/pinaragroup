package com.likya.myra.test.deps;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.DateUtils;
import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public abstract class TestBase {

	public static JobListDocument getJobList(String path, String fName, boolean validate) throws Exception {

		StringBuffer xmlString;

		System.err.print(fName + " loading...");

		long startTime = DateUtils.getCurrentTimeMilliseconds();
		xmlString = FileUtils.readFile(path + fName);
		long duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
		System.err.print("	>> is loaded in " + duration + " ms	>> parsing...");

		startTime = DateUtils.getCurrentTimeMilliseconds();
		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
		System.err.print("	>> is parsed in " + duration + " ms");

		if(validate) {
			System.err.print("	>> validating...");
			startTime = DateUtils.getCurrentTimeMilliseconds();
			validator(jobListDocument);
			duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
			System.err.print("	>> is validated in " + duration + " ms");
		}
		
		return jobListDocument;
	}

	public static void validator(XmlObject xmlObject) throws Exception {
		
		long startTime = DateUtils.getCurrentTimeMilliseconds();
		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), xmlObject)) {
			throw new Exception("JobList.xml is null or damaged !");
		}
		long duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
		System.err.println("	>> is validate in " + duration + " ms");
		
	}
	
	public static HashMap<String, AbstractJobType> toAbstractJobMap(JobListDocument jobListDocument) {

		HashMap<String, AbstractJobType> jobQueue = new HashMap<>();

		AbstractJobType[] objectArray = jobListDocument.getJobList().getGenericJobArray();

		// int counter = 0;
		for (AbstractJobType abstractJobType : objectArray) {
			jobQueue.put(abstractJobType.getId(), abstractJobType);
			// System.out.println(counter++ + ". kayıt job id : " + abstractJobType.getId());
		}

		return jobQueue;
	}
}
