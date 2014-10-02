package com.likya.myra.test;

import org.apache.log4j.Logger;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.utils.Scheduler;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestMyraScheduler {

	public static void main(String[] args) throws Exception {
		
		
		CoreFactory.setLogger(Logger.getLogger("MyraConsole"));
		
		String senaryoDosya = null;
		String zaman = null;
		String loglevel = null;

		String arg;
		int i = 0;

		while (i < args.length && args[i].startsWith("-")) {

			arg = args[i++];

			if (arg.equals("-senaryo")) {
				senaryoDosya = args[i++];
			} else if (arg.equals("-zaman")) {
				zaman = args[i++];
			} /*else if (arg.equals("-loglevel")) {
				loglevel = args[i++];
			}*/
		}

		if (senaryoDosya == null) {
			System.err.println("Kullanım : -senaryo senaryo.xml");
			return;
		}

		testSchedulingAlgorythm(senaryoDosya, zaman, loglevel);

	}

	public static StringBuffer getData(String senaryo) throws Exception {

		StringBuffer xmlString = FileUtils.readFile(senaryo);

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		
		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}
		
		return xmlString;
	}

	public static void testSchedulingAlgorythm(String senaryo, String ayar, String loglevel) throws Exception {

		StringBuffer xmlString = getData(senaryo);

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		
		AbstractJobType abstractJobType =jobListDocument.getJobList().getGenericJobArray(0);
		
		if (Scheduler.scheduleForNextExecution(abstractJobType)) {
			String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsPlannedTime().getStartTime().getTime());
			CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);
		} else {
			System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
		}

		
	}

}
