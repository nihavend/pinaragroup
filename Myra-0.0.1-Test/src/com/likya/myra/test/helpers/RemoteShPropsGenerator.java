package com.likya.myra.test.helpers;

import java.math.BigInteger;
import java.util.Calendar;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.model.generics.JobCommandTypeDocument.JobCommandType;
import com.likya.xsd.myra.model.generics.JobTypeDetailsDocument.JobTypeDetails;
import com.likya.xsd.myra.model.generics.UnitDocument.Unit;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.joblist.RemoteSchProperties;
import com.likya.xsd.myra.model.jobprops.BaseJobInfosDocument.BaseJobInfos;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.jobprops.ManagementDocument.Management;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;
import com.likya.xsd.myra.model.rs.ExecuteRShellParamsDocument.ExecuteRShellParams;
import com.likya.xsd.myra.model.stateinfo.JsDependencyRuleDocument.JsDependencyRule;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeListDocument.ReturnCodeList.OsType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
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

public class RemoteShPropsGenerator {

	public static XmlObject generate() throws Exception {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();

		RemoteSchProperties remoteSchProperties = (RemoteSchProperties) abstractJobType.changeType(RemoteSchProperties.type);
		
		remoteSchProperties.setHandlerURI("com.likya.myra.jef.jobs.ExecuteSchComponent");

		remoteSchProperties.setId("123245");
		remoteSchProperties.setAgentId(1);

		ExecuteRShellParams executeRShellParams = remoteSchProperties.addNewExecuteRShellParams();
		executeRShellParams.setHostName("192.168.1.39");
		executeRShellParams.setPort(22);
		executeRShellParams.setUserName("likya");
		executeRShellParams.setRshellPassword("likya");
		executeRShellParams.setFileSeperator("/");

		Management management = remoteSchProperties.addNewManagement();
		
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
		
		BaseJobInfos baseJobInfos = remoteSchProperties.addNewBaseJobInfos();
		baseJobInfos.setUserId(1);
		baseJobInfos.setJsName("My name is");
		baseJobInfos.setJobLogFile("demo.log");
		baseJobInfos.setJobLogPath("./");
		baseJobInfos.setOSystem(OsType.MACOS);
		baseJobInfos.setJobPriority(JobPriority.X_1);
		baseJobInfos.setJsIsActive(true);
		
		JobTypeDetails jobTypeDetails = baseJobInfos.addNewJobTypeDetails();
		jobTypeDetails.setJobCommand("uzaktan_calisan_job.sh");
		jobTypeDetails.setJobCommandType(JobCommandType.REMOTE_SHELL);
		jobTypeDetails.setJobPath("/home/likya/kursat");

		management.setTrigger(Trigger.TIME);
		
		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setStep(new GDuration());

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);
		
		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);
		jobAutoRetryInfo.setStep(new GDuration("PT0H30M0S"));
		jobAutoRetryInfo.setMaxCount(BigInteger.valueOf(3));
		
		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);

		DependencyList dependencyList = remoteSchProperties.addNewDependencyList();
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

		LiveStateInfo liveStateInfo = remoteSchProperties.addNewStateInfos().addNewLiveStateInfos().addNewLiveStateInfo();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.FINISHED);
		liveStateInfo.setSubstateName(SubstateName.COMPLETED);
		liveStateInfo.setStatusName(StatusName.SUCCESS);

		JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());

		System.err.println(jobListDocumentNew.toString());

		System.err.println("Valid = " + jobListDocumentNew.validate());
		
		return jobListDocumentNew;

	}

}
