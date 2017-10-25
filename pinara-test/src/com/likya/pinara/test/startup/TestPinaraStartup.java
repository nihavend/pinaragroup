package com.likya.pinara.test.startup;

import org.apache.xmlbeans.XmlOptions;

import com.likya.pinara.test.jobcrud.JobCrudNoDBDAO;
import com.likya.xsd.myra.model.joblist.JobListDocument;

import junit.framework.TestCase;

public class TestPinaraStartup extends TestCase {

	public void testStartWithoutPersist() {
		try {
			JobListDocument jobListDocument = new JobCrudNoDBDAO().readJobs("D:\\dev\\git\\pinaragroup\\pinara-test\\data\\");
			
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			System.out.println(jobListDocument.xmlText(xmlOptions));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void testStartWithPersist() {
		try {
			
			// Pinara .java 225-230
			
			// Bu kısım çalışılmamış. Bu nedenle yeni yapıya uygun olarak tasarlanacak.
			
			/*
			 * 
			 		if(forceToRecover) {
			CoreFactoryInterface coreFactoryInterface = Starter.startRecover(testOutput);
			
			if(coreFactoryInterface == null) {
				return false;
			}
			 */
			
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
