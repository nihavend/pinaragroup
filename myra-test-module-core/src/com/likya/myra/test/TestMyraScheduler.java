package com.likya.myra.test;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.utils.timeschedules.TimeScheduler;
import com.likya.myra.test.helpers.PeriodTesterDataGenerator;
import com.likya.myra.test.helpers.SimplePropsGenerator;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestMyraScheduler {

	public static void main(String[] args) throws Exception {
		
		
		CoreFactory.setLogger(Logger.getLogger("MyraConsole"));

		testSchedulingAlgorythmWithPeriod();
		
		// testSchedulingAlgorythmWithoutPeriod();

	}
	
	public static void mainOld(String[] args) throws Exception {
		
		
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

		testSchedulingAlgorythmOld(senaryoDosya, zaman, loglevel);

	}

	public static StringBuffer getData(String senaryo) throws Exception {

		StringBuffer xmlString = FileUtils.readFile(senaryo);

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		
		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}
		
		return xmlString;
	}

	public static void testSchedulingAlgorythmWithoutPeriod() throws Exception {

		AbstractJobType abstractJobType;
		try {
			abstractJobType = AbstractJobType.Factory.parse(SimplePropsGenerator.generateAbstractJobType(false).xmlText());

			// Without period object
			
			if (TimeScheduler.scheduleForNextExecution(abstractJobType)) {
				String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
				CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);			
			} else {
				System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
			}
			
			System.out.println(abstractJobType.getStateInfos().getLiveStateInfos());
			
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public static void testSchedulingAlgorythmWithPeriod() throws Exception {

		AbstractJobType abstractJobType;
		
		try {
			abstractJobType = PeriodTesterDataGenerator.generate();

			// With period object
			
			if (TimeScheduler.scheduleForNextExecution(abstractJobType)) {
				String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
				CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);			
			} else {
				System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
			}
			
			System.out.println(abstractJobType.getStateInfos().getLiveStateInfos());

			abstractJobType = PeriodTesterDataGenerator.generateMaxCountExceeded();

			if (TimeScheduler.scheduleForNextExecution(abstractJobType)) {
				String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
				CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);			
			} else {
				System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
			}

			System.out.println(abstractJobType.getStateInfos().getLiveStateInfos());


			abstractJobType = PeriodTesterDataGenerator.generateOutOfTimeFrame();

			if (TimeScheduler.scheduleForNextExecution(abstractJobType)) {
				String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
				CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);			
			} else {
				System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
			}

			System.out.println(abstractJobType.getStateInfos().getLiveStateInfos());

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public static void testSchedulingAlgorythmOld(String senaryo, String ayar, String loglevel) throws Exception {

		StringBuffer xmlString = getData(senaryo);

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		
		AbstractJobType abstractJobType =jobListDocument.getJobList().getGenericJobArray(0);
		
		if (TimeScheduler.scheduleForNextExecution(abstractJobType)) {
			String startTime = MyraDateUtils.getDate(abstractJobType.getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
			CoreFactory.getLogger().info("Job [" + abstractJobType.getId() + "] bir sonraki zamana kuruldu : " + startTime);
		} else {
			System.err.println("Zaman koşullarını sağlamadığından bir daha çalışmayacak !");
		}
		
	}

}
