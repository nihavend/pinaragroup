package com.likya.pinara.test.jobcrud;

import com.likya.myra.test.helpers.SimplePropsGenerator;

import junit.framework.TestCase;

public class TestJobCrudNoDBDAO extends TestCase {

	String dataPath = "D:\\dev\\git\\pinaragroup\\pinara-test\\data\\";
	
	public void testAddJob() {
		try {
			String jobXml = SimplePropsGenerator.generateAbstractJobType().xmlText();
			new JobCrudNoDBDAO().saveJob(dataPath, "12343", jobXml);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
