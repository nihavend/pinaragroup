package com.likya.myra.test.filters;

import org.apache.log4j.Logger;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;

public abstract class TestFilterBase {

	public static JobList getJobList() {
		return getJobList("/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/10.xml");
	}
	
	public static JobList getJobList(String fileName) {
		
		StringBuffer xmlString = FileUtils.readFile(fileName);
		
		JobListDocument jobListDocument = null;
		try {
			jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
			
			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
				throw new Exception("JobList.xml is null or damaged !");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jobListDocument.getJobList();
	}
}
