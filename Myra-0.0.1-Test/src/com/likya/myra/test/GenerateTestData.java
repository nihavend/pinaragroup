package com.likya.myra.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.GDuration;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.xsd.myra.model.generics.UnitDocument.Unit;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.joblist.SimpleProperties;
import com.likya.xsd.myra.model.jobprops.BaseJobInfosDocument.BaseJobInfos;
import com.likya.xsd.myra.model.jobprops.DaysOfMonthDocument.DaysOfMonth;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.jobprops.ManagementDocument.Management;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;
import com.likya.xsd.myra.model.jobprops.ScheduleInfoDocument.ScheduleInfo;
import com.likya.xsd.myra.model.stateinfo.JobStatusListDocument.JobStatusList;
import com.likya.xsd.myra.model.stateinfo.JsDependencyRuleDocument.JsDependencyRule;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeDocument.ReturnCode;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeListDocument.ReturnCodeList;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeListDocument.ReturnCodeList.OsType;
import com.likya.xsd.myra.model.stateinfo.StateInfosDocument.StateInfos;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.Status;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;
import com.likya.xsd.myra.model.wlagen.CascadingConditionsDocument.CascadingConditions;
import com.likya.xsd.myra.model.wlagen.ExpectedTimeDocument.ExpectedTime;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;
import com.likya.xsd.myra.model.wlagen.JobAutoRetryInfoDocument.JobAutoRetryInfo;
import com.likya.xsd.myra.model.wlagen.JobPriorityDocument.JobPriority;
import com.likya.xsd.myra.model.wlagen.JsPlannedTimeDocument.JsPlannedTime;
import com.likya.xsd.myra.model.wlagen.JsRealTimeDocument.JsRealTime;
import com.likya.xsd.myra.model.wlagen.JsTimeOutDocument.JsTimeOut;
import com.likya.xsd.myra.model.wlagen.JsTypeDocument.JsType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;
import com.likya.xsd.myra.model.wlagen.TriggerDocument.Trigger;

public class GenerateTestData {

	public static void main(String[] args) {
		
		int testCount = 2;
		
		File file = new File("/Users/serkan/git/localgit/TL-2.0.0-Test/" + testCount + ".xml");
		
		// oneTimeWriter(file, testCount);
		appendTofile(file, testCount);
	}
	
	public static void appendTofile(File file, int testCount) {
		
		
		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		    
		    String header = "<jobList xmlns=\"http://www.likyateknoloji.com/myra-joblist\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
		    				"xmlns:jsdl=\"http://schemas.ggf.org/jsdl/2005/11/jsdl\" xmlns:myra=\"http://www.likyateknoloji.com/myra-jobprops\" " +
		    				"xmlns:wla=\"http://www.likyateknoloji.com/wla-gen\" xmlns:lik=\"http://www.likyateknoloji.com/likya-gen\" " +
		    				"xmlns:myra1=\"http://www.likyateknoloji.com/myra-stateinfo\">";
		    out.println(header);
		    
		    // XmlOptions xmlOptions = (new XmlOptions()).setSavePrettyPrint();
		    // xmlOptions.setSaveInner();
			// Node node = jobListDocument.getJobList().getDomNode().getFirstChild(); 
			// System.err.println(jobListDocument.getJobList().xmlText());

			for (int c = 0; c < testCount; c++) {
				AbstractJobType abstractJobType = generate(jobList);
				abstractJobType.setId("" + c);
				abstractJobType.getDependencyList().getItemArray(0).setJsId("" + (c + 1));
				JobListDocument jLDocument = JobListDocument.Factory.newInstance();
				JobList jL = jLDocument.addNewJobList();
				jL.addNewGenericJob().set(abstractJobType);
				if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jL)) {
					out.close();
					throw new Exception("JobList.xml is null or damaged !");
				}
				// String str = jL.addNewGenericJob().set(abstractJobType).toString();
				// String str = AbstractJobType.Factory.parse(abstractJobType.toString(), xmlOptions).toString();
				// System.err.println(jLDocument.getJobList().toString());
				String str = jLDocument.getJobList().toString();
				out.println(str);
			}
			jobList.getGenericJobArray(jobList.getGenericJobArray().length - 1).getDependencyList().getItemArray(0).setJsId("0");
		    String footer = "</jobList>";
		    out.println(footer);
		    out.close();
		    System.err.println("completed !");
		} catch (Exception e) {
			
		    //oh noes!
			e.printStackTrace();
		}
		
	}
	
	public static void oneTimeWriter(File file, int testCount) {
		
		try {
			
			boolean debug = false;

			JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
			
			JobList jobList = jobListDocument.addNewJobList();


			for (int c = 0; c < testCount; c++) {
				AbstractJobType abstractJobType = generate(jobList);
				abstractJobType.setId("" + c);
			}
			
			JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
			jobListDocumentNew.save(file);

			if (debug) {
				System.err.println(jobListDocumentNew.toString());
				// System.err.println("Valid = " + jobListDocumentNew.validate());
			}
			System.err.println("completed !");
//			System.err.println("-------------------------------------------------------------------");
//			System.err.println(jobList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SimpleProperties generate(JobList jobList) throws Exception {
	
		AbstractJobType abstractJobType = jobList.addNewGenericJob();
		SimpleProperties simpleProperties = (SimpleProperties) abstractJobType.changeType(SimpleProperties.type);

		simpleProperties.setHandlerURI("com.likya.myra.jef.jobs.ExecuteInShell");

		simpleProperties.setGroupId("1");
		simpleProperties.setId("1");
		simpleProperties.setAgentId(1);

		ScheduleInfo scheduleInfo = simpleProperties.addNewScheduleInfo();

		int days[] = { 1, 3, 5 };
		DaysOfMonth daysOfMonth = DaysOfMonth.Factory.newInstance();
		
		daysOfMonth.setDaysArray(days);
		
		scheduleInfo.setDaysOfMonth(daysOfMonth);

		int daysOfWeek[] = { 1, 3, 5 };
		scheduleInfo.setDaysOfWeekIntTypeArray(daysOfWeek);

		Management management = simpleProperties.addNewManagement();
		TimeManagement timeManagement = management.addNewTimeManagement();
		JsPlannedTime jsPlannedTime = timeManagement.addNewJsPlannedTime();
		jsPlannedTime.setStartTime(Calendar.getInstance());
		jsPlannedTime.setStopTime(Calendar.getInstance());
		JsTimeOut jsTimeOut = timeManagement.addNewJsTimeOut();
		jsTimeOut.setUnit(Unit.SECONDS);
		jsTimeOut.setValueInteger(BigInteger.valueOf(100));
		JsRealTime jsRealTime = timeManagement.addNewJsRealTime();
		jsRealTime.setStartTime(Calendar.getInstance());
		jsRealTime.setStopTime(Calendar.getInstance());
		ExpectedTime expectedTime = timeManagement.addNewExpectedTime();
		expectedTime.setUnit(Unit.SECONDS);
		expectedTime.setValueInteger(BigInteger.valueOf(100));

		BaseJobInfos baseJobInfos = simpleProperties.addNewBaseJobInfos();
		baseJobInfos.setUserId(1);
		baseJobInfos.setJsName("My name is");
		baseJobInfos.setJobLogFile("demo.log");
		baseJobInfos.setJobLogPath("./");
		baseJobInfos.setOSystem(OsType.MACOS);
		baseJobInfos.setJobPriority(JobPriority.X_1);
		baseJobInfos.setJsIsActive(true);

		management.setTrigger(Trigger.TIME);

		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setMaxCount(BigInteger.valueOf(3));
		periodInfo.setCounter(BigInteger.valueOf(0));
		periodInfo.setStep(new GDuration("PT240S"));

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);

		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);
		jobAutoRetryInfo.setStep(new GDuration("PT0H30M0S"));

		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);

		DependencyList dependencyList = simpleProperties.addNewDependencyList();
		dependencyList.setDependencyExpression("I can not express my deps !");
		Item item = dependencyList.addNewItem();
		item.setJsId("1");
		item.setJsType(JsType.JOB);
		item.setJsName("depJsName");
		item.setDependencyID("mydep");
		item.setComment("no comment");

		JsDependencyRule jsDependencyRule = item.addNewJsDependencyRule();
		jsDependencyRule.setStateName(StateName.PENDING);
		jsDependencyRule.setSubstateName(SubstateName.READY);
		jsDependencyRule.setStatusName(StatusName.BYTIME);

		// simpleProperties.addNewJobDescription();

		StateInfos stateInfos = simpleProperties.addNewStateInfos();
		
		LiveStateInfosType liveStateInfosType = stateInfos.addNewLiveStateInfos();
		LiveStateInfo liveStateInfo = liveStateInfosType.addNewLiveStateInfo();
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.IDLED);
		liveStateInfo.setStatusName(StatusName.BYTIME);
		

		JobStatusList jobStatusList = stateInfos.addNewJobStatusList();
		Status status = jobStatusList.addNewJobStatus();
		status.setStatusName(StatusName.BYTIME);
		status.setDesc("desc");
		
		ReturnCodeList returnCodeList = status.addNewReturnCodeList();
		returnCodeList.setOsType(OsType.WINDOWS);

		ReturnCode returnCode = returnCodeList.addNewReturnCode();
		returnCode.setDesc("desc");
		returnCode.setCode(0);
		
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.READY);
		liveStateInfo.setStatusName(StatusName.BYTIME);

		return simpleProperties;
	}
}
