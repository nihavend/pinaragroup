package com.likya.pinara.utils.xml.mappers;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;

public class JobDetailMapper extends JobGridListMapper {

	public static String getMapped(AbstractJobType abstractJobType) {

		String retValue = ""; 

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();

		JobList jobList = jobListDocument.addNewJobList();

		XmlObject xmlObject = abstractJobType.copy();
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();
		JobGridListMapper.addRuntimeParams(xmlCursor, abstractJobType);
		
		if (xmlCursor != null) {
			xmlCursor.dispose();
		}
		
		jobList.addNewGenericJob().set(xmlObject);

		try {
			 XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);
			retValue = removeNameSpaces(jobListDocument.getJobList().xmlText(saveOptions));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retValue;
	}
}
