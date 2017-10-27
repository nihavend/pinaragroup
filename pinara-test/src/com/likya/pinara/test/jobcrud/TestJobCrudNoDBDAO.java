package com.likya.pinara.test.jobcrud;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

import org.apache.xmlbeans.XmlOptions;
import org.junit.Ignore;
import org.junit.Test;

import com.likya.myra.test.helpers.LiveStateInfosGenerator;
import com.likya.myra.test.helpers.SimplePropsGenerator;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosDocument;

public class TestJobCrudNoDBDAO {

	String dataPath = "D:\\dev\\git\\pinaragroup\\pinara-test\\data\\";
	
	private static int jobQuantity = 10;
	
	private static String [] jobIds = randomJobIds(jobQuantity);

	private static String [] randomJobIds(int jQ) {
		String [] jobIds = new String[jQ];
		for(int i = 0; i < jQ; i ++) {
			Random random = new Random();
			int randomNumber = random.nextInt(100000);
			jobIds[i] = "" + randomNumber;
		}
		return jobIds;
	}
		
	
	@Ignore
	public void saveJob() {
		try {

			Path path = FileSystems.getDefault().getPath(dataPath);

			String jobXml = SimplePropsGenerator.generateAbstractJobType().xmlText();
			
			int i = 0;
			while (i < jobQuantity) {
				new JobCrudNoDBDAO().saveJob(path, jobIds[i++], jobXml);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	@Ignore
	public void deleteJob() {
		Path path = FileSystems.getDefault().getPath(dataPath);
		String jobId = "74791";
		new JobCrudNoDBDAO().deleteJob(path, jobId);
	}

	@Ignore
	public void deleteJobHist() {
		Path path = FileSystems.getDefault().getPath(dataPath);
		String jobId = "74791";
		new JobCrudNoDBDAO().deleteJobHist(path, jobId);
	}
	
	@Ignore
	public void saveJobHist() {
		try {

			Path path = FileSystems.getDefault().getPath(dataPath);

			String jobHistXml = LiveStateInfosGenerator.generate(5).xmlText();
			int i = 0;
			while (i < jobQuantity) {
				new JobCrudNoDBDAO().saveJobHist(path, jobIds[i++], jobHistXml);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
	
	@Test
	public void readJobHist() {
		try {

			Path path = FileSystems.getDefault().getPath(dataPath);
			
			String jobId = "23032";
			
			LiveStateInfosDocument liveStateInfosDocument = new JobCrudNoDBDAO().readJobHist(path, jobId);

			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			System.out.println(liveStateInfosDocument.xmlText(xmlOptions));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
