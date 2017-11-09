package com.likya.myra.test.helpers;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.model.generics.JobCommandTypeDocument.JobCommandType;
import com.likya.xsd.myra.model.generics.JobTypeDetailsDocument.JobTypeDetails;
import com.likya.xsd.myra.model.generics.UnitDocument.Unit;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.joblist.SimpleProperties;
import com.likya.xsd.myra.model.jobprops.BaseJobInfosDocument.BaseJobInfos;
import com.likya.xsd.myra.model.jobprops.DaysOfMonthDocument.DaysOfMonth;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.jobprops.GraphInfoDocument.GraphInfo;
import com.likya.xsd.myra.model.jobprops.ManagementDocument.Management;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;
import com.likya.xsd.myra.model.jobprops.ScheduleInfoDocument.ScheduleInfo;
import com.likya.xsd.myra.model.stateinfo.JobStatusListDocument.JobStatusList;
import com.likya.xsd.myra.model.stateinfo.JsDependencyRuleDocument.JsDependencyRule;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
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
import com.likya.xsd.myra.model.wlagen.JsActualTimeDocument.JsActualTime;
import com.likya.xsd.myra.model.wlagen.JsRecordedTimeDocument.JsRecordedTime;
import com.likya.xsd.myra.model.wlagen.JsScheduledTimeDocument.JsScheduledTime;
import com.likya.xsd.myra.model.wlagen.JsTimeOutDocument.JsTimeOut;
import com.likya.xsd.myra.model.wlagen.JsTypeDocument.JsType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;
import com.likya.xsd.myra.model.wlagen.TriggerDocument.Trigger;

public class SimplePropsGenerator {

	public static XmlObject generate() throws Exception {

		boolean debug = true;

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();
		SimpleProperties simpleProperties = (SimpleProperties) abstractJobType.changeType(SimpleProperties.type);

		simpleProperties.setHandlerURI("com.likya.myra.jef.jobs.ExecuteInShell");

		simpleProperties.setId("123245");
		simpleProperties.setGroupId("my_group");
		simpleProperties.setAgentId(1);

		ScheduleInfo scheduleInfo = simpleProperties.addNewScheduleInfo();
		
		int days[] = { 1, 3, 5 };
		DaysOfMonth daysOfMonth = DaysOfMonth.Factory.newInstance();
		daysOfMonth.setFirstDayOfMonth("");
		daysOfMonth.setLastDayOfMonth("");
		
		daysOfMonth.setDaysArray(days);
		
		scheduleInfo.setDaysOfMonth(daysOfMonth);

		int daysOfWeek[] = { 1, 3, 5 };
		scheduleInfo.setDaysOfWeekIntTypeArray(daysOfWeek);

		Management management = simpleProperties.addNewManagement();
		
		TimeManagement timeManagement = management.addNewTimeManagement();
		
		JsScheduledTime jsScheduledTime = timeManagement.addNewJsScheduledTime();
		jsScheduledTime.setStartTime(Calendar.getInstance());
		
		JsActualTime jsActualTime = timeManagement.addNewJsActualTime();
		jsActualTime.setStartTime(Calendar.getInstance());
		
		JsTimeOut jsTimeOut = timeManagement.addNewJsTimeOut();
		jsTimeOut.setUnit(Unit.SECONDS);
		jsTimeOut.setValueInteger(BigInteger.valueOf(100));
		
		JsRecordedTime jsRecordedTime = timeManagement.addNewJsRecordedTime();
		jsRecordedTime.setStartTime(Calendar.getInstance());
		jsRecordedTime.setStopTime(Calendar.getInstance());
		
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

		JobTypeDetails jobTypeDetails = baseJobInfos.addNewJobTypeDetails();
		jobTypeDetails.setJobCommand("/Users/serkan/Desktop/tlos2.0.0/job2.sh");
		jobTypeDetails.setJobCommandType(JobCommandType.BATCH_PROCESS);
		
		management.setTrigger(Trigger.TIME);
		
//		PeriodInfo periodInfo = management.addNewPeriodInfo();
//		periodInfo.setStep(new GDuration());

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
		jsDependencyRule.setSubstateName(SubstateName.IDLED);
		jsDependencyRule.setStatusName(StatusName.BYTIME);

		StateInfos stateInfos = simpleProperties.addNewStateInfos();

		JobStatusList jobStatusList = stateInfos.addNewJobStatusList();
		Status status = jobStatusList.addNewJobStatus();
		status.setDesc("");
		status.setStatusName(StatusName.SUCCESS);
		ReturnCodeList returnCodeList = status.addNewReturnCodeList();
		returnCodeList.setOsType(OsType.MACOS);
		ReturnCode returnCode = returnCodeList.addNewReturnCode();
		returnCode.setCode(0);
		returnCode.setDesc("Açıklama");
		
		LiveStateInfo liveStateInfo = stateInfos.addNewLiveStateInfos().addNewLiveStateInfo();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.READY);
		liveStateInfo.setStatusName(StatusName.BYTIME);

		XmlOptions xmlOptions = new XmlOptions();
		//xmlOptions.setUseDefaultNamespace();
		
		HashMap<String, String> ns = new HashMap<>(); 
		ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
		ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
		
		xmlOptions.setSaveSuggestedPrefixes(ns);
			
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSavePrettyPrint();
		
		// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
		JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
		
		// System.err.println(jobListDocument.xmlText(xmlOptions));
		// System.exit(0);
		
		
		if (debug) {
			System.err.println(jobListDocumentNew.toString());

			System.err.println("Valid = " + jobListDocumentNew.validate());

		}

		return jobListDocumentNew;
	}

	public static XmlObject generateAbstractJobType() throws Exception {
		boolean debug = true;
		return generateAbstractJobType(debug);
	}
	
	public static XmlObject generateAbstractJobType(boolean debug) throws Exception {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();
		SimpleProperties simpleProperties = (SimpleProperties) abstractJobType.changeType(SimpleProperties.type);

		simpleProperties.setHandlerURI("com.likya.myra.jef.jobs.ExecuteInShell");

		simpleProperties.setId("123245");
		simpleProperties.setGroupId("my_group");
		simpleProperties.setAgentId(1);
		
		simpleProperties.setLSIDateTime("2017-10-08T17:06:54.710+0300");

		ScheduleInfo scheduleInfo = simpleProperties.addNewScheduleInfo();
		
		int days[] = { 1, 3, 5 };
		DaysOfMonth daysOfMonth = DaysOfMonth.Factory.newInstance();
		daysOfMonth.setFirstDayOfMonth("");
		daysOfMonth.setLastDayOfMonth("");
		
		daysOfMonth.setDaysArray(days);
		
		scheduleInfo.setDaysOfMonth(daysOfMonth);

		int daysOfWeek[] = { 1, 3, 5 };
		scheduleInfo.setDaysOfWeekIntTypeArray(daysOfWeek);

		Management management = simpleProperties.addNewManagement();
		
		TimeManagement timeManagement = management.addNewTimeManagement();
		
		JsScheduledTime jsScheduledTime = timeManagement.addNewJsScheduledTime();
		jsScheduledTime.setStartTime(Calendar.getInstance());
		
		JsActualTime jsActualTime = timeManagement.addNewJsActualTime();
		jsActualTime.setStartTime(Calendar.getInstance());
		
		JsTimeOut jsTimeOut = timeManagement.addNewJsTimeOut();
		jsTimeOut.setUnit(Unit.SECONDS);
		jsTimeOut.setValueInteger(BigInteger.valueOf(100));
		
		JsRecordedTime jsRecordedTime = timeManagement.addNewJsRecordedTime();
		jsRecordedTime.setStartTime(Calendar.getInstance());
		jsRecordedTime.setStopTime(Calendar.getInstance());
		
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

		JobTypeDetails jobTypeDetails = baseJobInfos.addNewJobTypeDetails();
		jobTypeDetails.setJobCommand("/Users/serkan/Desktop/tlos2.0.0/job2.sh");
		jobTypeDetails.setJobCommandType(JobCommandType.BATCH_PROCESS);
		
		management.setTrigger(Trigger.TIME);
		
//		PeriodInfo periodInfo = management.addNewPeriodInfo();
//		periodInfo.setStep(new GDuration());

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);

		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);
		jobAutoRetryInfo.setStep(new GDuration("PT0H30M0S"));

		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);
		
		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setStep(new GDuration("PT5M"));
		periodInfo.setRelativeStart(false);

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
		jsDependencyRule.setSubstateName(SubstateName.IDLED);
		jsDependencyRule.setStatusName(StatusName.BYTIME);

		GraphInfo graphInfo = simpleProperties.addNewGraphInfo();
		graphInfo.setDeadBranch(false);
		
		StateInfos stateInfos = simpleProperties.addNewStateInfos();

		JobStatusList jobStatusList = stateInfos.addNewJobStatusList();
		Status status = jobStatusList.addNewJobStatus();
		status.setDesc("");
		status.setStatusName(StatusName.SUCCESS);
		ReturnCodeList returnCodeList = status.addNewReturnCodeList();
		returnCodeList.setOsType(OsType.MACOS);
		ReturnCode returnCode = returnCodeList.addNewReturnCode();
		returnCode.setCode(0);
		returnCode.setDesc("Açıklama");
		
		LiveStateInfo liveStateInfo = stateInfos.addNewLiveStateInfos().addNewLiveStateInfo();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.READY);
		liveStateInfo.setStatusName(StatusName.BYTIME);

		XmlOptions xmlOptions = new XmlOptions();
		//xmlOptions.setUseDefaultNamespace();
		
		HashMap<String, String> ns = new HashMap<>(); 
		ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
		ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
		
		xmlOptions.setSaveSuggestedPrefixes(ns);
			
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSavePrettyPrint();
		
		// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
		JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
		
		// System.err.println(jobListDocument.xmlText(xmlOptions));
		// System.exit(0);
		
		
		if (debug) {
			System.err.println(jobListDocumentNew.toString());

			System.err.println("Valid = " + jobListDocumentNew.validate());

		}

		return simpleProperties;
	}

}
