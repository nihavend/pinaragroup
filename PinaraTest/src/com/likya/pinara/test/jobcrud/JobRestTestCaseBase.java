package com.likya.pinara.test.jobcrud;

import java.math.BigInteger;
import java.util.Calendar;

import org.apache.xmlbeans.GDuration;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.pinara.test.authcrud.RestTestCaseBase;
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
import com.likya.xsd.myra.model.wlagen.BornedPlannedTimeDocument.BornedPlannedTime;
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

public abstract class JobRestTestCaseBase extends RestTestCaseBase {

	public static SimpleProperties generate(boolean hasDep) throws Exception {
		
		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();
		
		AbstractJobType abstractJobType = jobList.addNewGenericJob();
		SimpleProperties simpleProperties = (SimpleProperties) abstractJobType.changeType(SimpleProperties.type);

		simpleProperties.setHandlerURI("com.likya.myra.jef.jobs.ExecuteInShell");

		simpleProperties.setGroupId("grp_1");
		simpleProperties.setId("0");
		simpleProperties.setAgentId(1);

		ScheduleInfo scheduleInfo = simpleProperties.addNewScheduleInfo();

		int days[] = { 1, 3, 5 };
		DaysOfMonth daysOfMonth = DaysOfMonth.Factory.newInstance();
		
		daysOfMonth.setDaysArray(days);
		
		scheduleInfo.setDaysOfMonth(daysOfMonth);

		int daysOfWeek[] = { 1, 3, 5 };
		scheduleInfo.setDaysOfWeekIntTypeArray(daysOfWeek);

		Management management = simpleProperties.addNewManagement();
		management.setTrigger(Trigger.TIME);
		TimeManagement timeManagement = management.addNewTimeManagement();
		BornedPlannedTime bornedPlannedTime = timeManagement.addNewBornedPlannedTime();
		bornedPlannedTime.setStartTime(Calendar.getInstance());
		bornedPlannedTime.setStopTime(Calendar.getInstance());
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
		
		JobTypeDetails jobTypeDetails = baseJobInfos.addNewJobTypeDetails();
		jobTypeDetails.setJobCommandType(JobCommandType.BATCH_PROCESS);
		jobTypeDetails.setJobWorkDir("");
		
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

		if (hasDep) {
			DependencyList dependencyList = simpleProperties.addNewDependencyList();
			dependencyList.setDependencyExpression("mydep");
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
		}
		// simpleProperties.addNewJobDescription();

		simpleProperties.addNewGraphInfo();
		
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
		liveStateInfo.setSubstateName(SubstateName.DEACTIVATED);
		// liveStateInfo.setStatusName(StatusName.BYTIME);

		
		
		return simpleProperties;
	}
}
