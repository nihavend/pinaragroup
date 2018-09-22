package com.likya.pinara.utils;

import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.Pinara;
import com.likya.pinara.dao.nodb.JobCrudNoDBDAO;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;

public class PersistDBApi {
	
	private static Path dataPath = FileSystems.getDefault().getPath(Pinara.DATA_PATH);

	public static JobListDocument readJobs() {
		JobListDocument jobListDocument = new JobCrudNoDBDAO().readJobs(dataPath);
		return jobListDocument;
	}
	
	public static boolean saveJob(AbstractJobType abstractJobType) {
		return new JobCrudNoDBDAO().saveJob(dataPath, abstractJobType.getId(), abstractJobType.xmlText());
	}
	
	public static boolean deleteJob(String jobId) {
		
		boolean retValue = false;
		
		retValue = new JobCrudNoDBDAO().deleteJob(dataPath, jobId);
		
		if(retValue) {
			new JobCrudNoDBDAO().deleteJobHist(dataPath, jobId);
		}
		
		return retValue;
	}
	
	public static boolean updateJobHist(String jobId, LiveStateInfosType liveStateInfosType) {
		
		BigInteger tmpValue = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getMaxHistoryLen();
		
		int maxHistoryLen = (tmpValue == null) ? 20 : tmpValue.intValue();
		
		if(liveStateInfosType.getLiveStateInfoArray().length > maxHistoryLen) {
			
			/** 
			 * NOT : Aşağıdaki Açıklamaya Bknz : 
			 * LiveStateInfosDocument historicLiveStateInfosDocument = new JobCrudNoDBDAO().readJobHist(dataPath, jobId);
			 */
			// System.out.println(liveStateInfosDocument);
			while(liveStateInfosType.getLiveStateInfoArray().length > maxHistoryLen) {
				/**
				 *  NOT : Aşağıdaki Açıklamaya Bknz : 
				 *  LiveStateInfo liveStateInfo = liveStateInfosType.getLiveStateInfoArray(liveStateInfosType.getLiveStateInfoArray().length - 1);
				 *  historicLiveStateInfosDocument.getLiveStateInfos().insertNewLiveStateInfo(0).set(liveStateInfo);
				 */
				liveStateInfosType.removeLiveStateInfo(liveStateInfosType.getLiveStateInfoArray().length - 1);
			}
			// System.out.println(liveStateInfosDocument);
			/**
			 * AÇIKLAMA :
			 * 
			 * Serkan Taş 18.01.2018 23:40
			 * 
			 * Job içinde config'de tanımlı MAX 20 hareket historic olarak tutulmaktadır.
			 * Bu metod, tanımlı max değeri aşan kısmın job xml den silinip diske yazılmasını amaçlamaktadır.
			 * Ancak bu oluşan dosyanın sınırı da zamanla büyüdüğünden, yeni bir akış tasarlanana dek 
			 * artan bilgilerin diske yazma işlemi iptal edilmiştir.
			 * 
			 * new JobCrudNoDBDAO().saveJobHist(dataPath, jobId, liveStateInfosDocument.getLiveStateInfos().xmlText());
			 */

		}
		return true;
	}
	
	public static void handleJobDataChangeEvent(String jobId) {
		// Yeni db yapısı sonrası eklendi
		// İlk olarak değişen işi diske yansıtalım
		// sonrasında da history bilgisi limiti aştıysa onu da diske yazalım
		
		JobImpl jobImpl = CoreFactory.getInstance().getMonitoringOperations().getJobQueue().get(jobId);
		PersistDBApi.saveJob(jobImpl.getAbstractJobType());
		LiveStateInfosType liveStateInfosType = jobImpl.getAbstractJobType().getStateInfos().getLiveStateInfos();
		PersistDBApi.updateJobHist(jobId, liveStateInfosType);
		
	}
	
	public static void checkDbFolder() {
		JobCrudNoDBDAO.checkDataPath(dataPath);
	}
}
