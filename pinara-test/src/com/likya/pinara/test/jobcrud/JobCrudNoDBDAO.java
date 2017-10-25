package com.likya.pinara.test.jobcrud;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.XMLValidations;
import com.likya.pinara.utils.PersistApi;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;

public class JobCrudNoDBDAO extends JobCrudDAO {

	private static final String dbFolderName = "pnr";
	private static final String jobFileExt = ".pjf";

	private boolean validateXml(XmlObject objectXml) {

		ArrayList<String> errMsgLst = new ArrayList<String>();

		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), objectXml, errMsgLst)) {
			System.err.println("JobList.xml is null or damaged !");

			String[] strArr = errMsgLst.toArray(new String[errMsgLst.size()]);

			for (String msg : strArr) {
				System.out.println(msg);
			}

			return false;
		}
		return true;
	}

	private void readJobFiles(JobList jobList, String fileName) {

		System.out.println(fileName);

		String fileContent = "";

		try {

			fileContent = PersistApi.deserializeAsFlat(fileName);

			AbstractJobType abstractJobType = AbstractJobType.Factory.parse(fileContent);
			if (!validateXml(abstractJobType)) {
				System.out.println("Not validated => " + abstractJobType.toString());
			} else {
				jobList.addNewGenericJob().set(abstractJobType);
			}

			// System.out.println(fileContent);
		} catch (Exception e) {
			System.err.println(fileName + " is corrupted => " + e.getStackTrace()[0]);
		}
	}

	public JobListDocument readJobs(String dbPath) {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();
		jobList.setVersion("getVersion()");
		
		try {

			dbPath = dbPath + dbFolderName;

			try (Stream<Path> paths = Files.walk(Paths.get(dbPath))) {
				paths.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(jobFileExt))
						.forEach(path -> readJobFiles(jobList, path.toString()));
				// .forEach(System.out::println);
			}
			if (jobList.getGenericJobArray().length > 0) {
				validateXml(jobListDocument);
			} else {
				// May be to reason :
				// 1. All files are corrupted
				// 2. Fresh new install and no job defined yet
				System.out.println("No valid job file found !");
			}

			// Read file system data path
			// Get all the job files
			// Decrypte encrypted files
			// Construct JobListDocument from files
			// Validate JobListDocument object

			// Pinara.java L:197 - 232

			//
			// JobListDocument jobListDocument = PersistApi.deserialize();

			// 	if (jobListDocument == null) { 
			//		jobListDocument = JobListDocument.Factory.newInstance();
			// 		jobListDocument.addNewJobList().setVersion(getVersion());
			// 		PersistApi.serialize(jobListDocument); 
			// 	} else { 
			// 		String dataFileVersion = jobListDocument.getJobList().getVersion();
			// 		if(!getVersion().equals(dataFileVersion)) { 
			//			jobListDocument = migrateDataFile(jobListDocument, dataFileVersion); 
			//			if(jobListDocument == null) { 
			//				return false; 
			//			} 
			//		} 
			//	}

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return jobListDocument;
	}

	public void saveJob(String dbPath, String jobId, String jobXml) {

		try {
			dbPath = dbPath + File.separator + dbFolderName;
			String fileName = jobId;
			PersistApi.serialize(dbPath + File.separator + fileName + jobFileExt, jobXml);
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
