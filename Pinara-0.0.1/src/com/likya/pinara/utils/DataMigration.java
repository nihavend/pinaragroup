package com.likya.pinara.utils;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.pinara.Pinara;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class DataMigration {

	public static JobListDocument migrate_null_to_0_9_2(String jobListDocumentStr) throws Exception {
		
		JobListDocument jobListDocumentRecent = JobListDocument.Factory.parse(jobListDocumentStr);
		
		for (AbstractJobType abstractJobType : jobListDocumentRecent.getJobList().getGenericJobArray()) {
			PersistDBApi.saveJob(abstractJobType);
		}
		
		JobListDocument jobListDocument = PersistDBApi.readJobs();

		return jobListDocument;
	}
		
	public static JobListDocument migrate_null_to_0_9_1(String jobListDocumentStr) throws Exception {

		final String version = Pinara.DEF_0_9_1;

		JobListDocument jobListDocument = JobListDocument.Factory.parse(jobListDocumentStr);

		String nsDesc = getNs();

		jobListDocumentStr = jobListDocumentStr.replace("bornedPlannedTime", "jsScheduledTime");

		jobListDocumentStr = jobListDocumentStr.replace("jsPlannedTime", "jsActualTime");

		XmlObject xmlObject = XmlObject.Factory.parse(jobListDocumentStr);

		String xPath = "$this//wla:jsScheduledTime/wla:stopTime";
		xmlObject = removeSelection(xmlObject, nsDesc, xPath);
		
		xPath = "$this//wla:jsActualTime/wla:stopTime";
		xmlObject = removeSelection(xmlObject, nsDesc, xPath);

		jobListDocument.set(xmlObject);

		jobListDocument.getJobList().setVersion(version);

		for (AbstractJobType abstractJobType : jobListDocument.getJobList().getGenericJobArray()) {
			if (abstractJobType.getLSIDateTime() == null) {
				abstractJobType.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
			}
		}

		return jobListDocument;
	}
	
	private static XmlObject removeSelection(XmlObject empDoc, String ns, String xPath) {
		
		// Insert a cursor and move it to the first element.
	    XmlCursor cursor = empDoc.newCursor();
	    // cursor.toNextToken();
	    
	    // cursor.getTextValue();
		
	    // Save the cursor's current location by pushing it
	    // onto a stack of saved locations.
	    cursor.push();

	    cursor.selectPath(ns + xPath);
	    
	    // Loop through the list of selections, getting the value of
	    // each element.
	    while (cursor.toNextSelection())
	    {
	        // System.out.println(cursor.getTextValue());
	        cursor.removeXml();
	    }
	    // Pop the saved location off the stack.
	    cursor.pop();
	    
		return cursor.getObject();
	}
	
	private static String getNs() {
		
		String nsDesc = "declare namespace myra-joblist='http://www.likyateknoloji.com/myra-joblist';";
		nsDesc += "declare namespace myra-stateinfo='http://www.likyateknoloji.com/myra-stateinfo';";
		nsDesc += "declare namespace myra='http://www.likyateknoloji.com/myra-joblist';";
		nsDesc += "declare namespace myra-jobprops='http://www.likyateknoloji.com/myra-jobprops';";
		nsDesc += "declare namespace wla='http://www.likyateknoloji.com/wla-gen';";
		nsDesc += "declare namespace xsi='http://www.likyateknoloji.com/http://www.w3.org/2001/XMLSchema-instance';";
		nsDesc += "declare namespace lik='http://www.likyateknoloji.com/likya-gen';";
		
		return nsDesc;
	}
}
