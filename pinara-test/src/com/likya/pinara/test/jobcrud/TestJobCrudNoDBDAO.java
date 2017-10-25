package com.likya.pinara.test.jobcrud;

import java.util.Random;

import com.likya.myra.test.helpers.SimplePropsGenerator;

import junit.framework.TestCase;

public class TestJobCrudNoDBDAO extends TestCase {

	String dataPath = "D:\\dev\\git\\pinaragroup\\pinara-test\\data\\";
	
	public void testAddJob() {
		try {
			String jobXml = SimplePropsGenerator.generateAbstractJobType().xmlText();
			int i = 0;
			while(i ++ < 100) {
				Random random = new Random();
				int randomNumber = random.nextInt(100000);
				new JobCrudNoDBDAO().saveJob(dataPath, ""+ randomNumber, jobXml);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
