package com.likya.pinara.dao.nodb;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.XMLValidations;
import com.likya.pinara.Pinara;
import com.likya.pinara.dao.JobCrudDAO;
import com.likya.pinara.utils.PersistApi;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosDocument;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;

public class JobCrudNoDBDAO extends JobCrudDAO {

	private static final String dbFolderName = "pnr";
	private static final String jobFileExt = ".pjf";
	private static final String jobHistFileExt = ".phf";

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

	// Read file system data path
	// Get all the job files
	// Decrypte encrypted files
	// Construct JobListDocument from files
	// Validate JobListDocument object

	// Pinara.java L:197 - 232

	//
	// JobListDocument jobListDocument = PersistApi.deserialize();
	// if (jobListDocument == null) {
	//	jobListDocument = JobListDocument.Factory.newInstance();
	//	jobListDocument.addNewJobList().setVersion(getVersion());
	//	PersistApi.serialize(jobListDocument);
	//} else {
	//	String dataFileVersion = jobListDocument.getJobList().getVersion();
	//	if (!getVersion().equals(dataFileVersion)) {
	//		jobListDocument = migrateDataFile(jobListDocument, dataFileVersion);
	//		if (jobListDocument == null) {
	//			return false;
	//		}
	//	}
	//}
	
	public JobListDocument readJobs(Path dbPath) {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();
		jobList.setVersion(Pinara.getVersion());

		try {

			dbPath = dbPath.resolve(dbFolderName);

			try (Stream<Path> paths = Files.walk(dbPath)) {
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
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return jobListDocument;
	}

	public boolean saveJob(Path dbPath, String jobId, String jobXml) {

		try {
			String fileName = jobId;
			dbPath = dbPath.resolve(dbFolderName).resolve(fileName + jobFileExt);
			PersistApi.serialize(dbPath.toString(), jobXml);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteJob(Path dbPath, String jobId) {
		try {
			dbPath = dbPath.resolve(dbFolderName).resolve(jobId + jobFileExt);
			Files.deleteIfExists(dbPath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void readJob() {
		// Bu metod özellikle boş bırakıldı. Zira uygulama içinde yapılacak 
		// Değişiklikler memorydeki veri yapısı üzerinde yapılacağından
		// diskten okunmayacak. 
		// diskten job okuma sadece en başta ve bir kere yapılacak
		// Serkan Taş : 28.10.2017 00:43 İstanbul
		return;
	}
	
	@Override
	public LiveStateInfosDocument readJobHist(Path dbPath, String jobId) {

		LiveStateInfosDocument liveStateInfosDocument = LiveStateInfosDocument.Factory.newInstance();
		LiveStateInfosType liveStateInfosType = liveStateInfosDocument.addNewLiveStateInfos();

		String fileName = jobId;
		dbPath = dbPath.resolve(dbFolderName).resolve(fileName + jobHistFileExt);

		String fileContent = "";

		try {

			fileContent = PersistApi.deserializeAsFlat(dbPath.toString());

			if(fileContent != null) {
				LiveStateInfosType liveStateInfosTypeTmp = LiveStateInfosType.Factory.parse(fileContent);
	
				if (!validateXml(liveStateInfosTypeTmp)) {
					System.out.println("Not validated => " + liveStateInfosType.toString());
				} else {
					liveStateInfosType.set(liveStateInfosTypeTmp);
				}
			}
		} catch (Exception e) {
			System.err.println(fileName + " is corrupted => " + e.getStackTrace()[0]);
		}

		return liveStateInfosDocument;
	}

	@Override
	public boolean saveJobHist(Path dbPath, String jobId, String jobXml) {
		try {
			String fileName = jobId;
			dbPath = dbPath.resolve(dbFolderName).resolve(fileName + jobHistFileExt);
			PersistApi.serialize(dbPath.toString(), jobXml);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean deleteJobHist(Path dbPath, String jobId) {
		try {
			dbPath = dbPath.resolve(dbFolderName).resolve(jobId + jobHistFileExt);
			Files.deleteIfExists(dbPath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
