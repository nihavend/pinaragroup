package com.likya.pinara.test.startup;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.xmlbeans.XmlOptions;
import org.junit.Ignore;
import org.junit.Test;

import com.likya.pinara.dao.nodb.JobCrudNoDBDAO;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestPinaraStartup {

	String dataPath = "D:\\dev\\git\\pinaragroup\\pinara-test\\data\\";
	
	@Test
	public void startWithoutPersist() {
		try {

			Path path = FileSystems.getDefault().getPath(dataPath);

			JobListDocument jobListDocument = new JobCrudNoDBDAO().readJobs(path);

			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			System.out.println(jobListDocument.xmlText(xmlOptions));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Ignore
	public void startWithPersist() {
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
