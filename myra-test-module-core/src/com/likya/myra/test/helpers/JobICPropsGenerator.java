package com.likya.myra.test.helpers;

import java.math.BigInteger;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.model.generics.JobCommandTypeDocument.JobCommandType;
import com.likya.xsd.myra.model.generics.JobTypeDetailsDocument.JobTypeDetails;
import com.likya.xsd.myra.model.generics.UnitDocument.Unit;
import com.likya.xsd.myra.model.intcomm.AppStatusInfoType;
import com.likya.xsd.myra.model.intcomm.CommandTypeDocument.CommandType;
import com.likya.xsd.myra.model.intcomm.ICParamsDocument.ICParams;
import com.likya.xsd.myra.model.intcomm.JobICProps;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.jobprops.BaseJobInfosDocument.BaseJobInfos;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.jobprops.ManagementDocument.Management;
import com.likya.xsd.myra.model.jobprops.PeriodInfoDocument.PeriodInfo;
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
import com.likya.xsd.myra.model.wlagen.JsActualTimeDocument.JsActualTime;
import com.likya.xsd.myra.model.wlagen.JsRecordedTimeDocument.JsRecordedTime;
import com.likya.xsd.myra.model.wlagen.JsTimeOutDocument.JsTimeOut;
import com.likya.xsd.myra.model.wlagen.JsTypeDocument.JsType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;
import com.likya.xsd.myra.model.wlagen.TriggerDocument.Trigger;

public class JobICPropsGenerator {

	public static JobListDocument generate() throws Exception {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();

		JobICProps jobICProps = (JobICProps) abstractJobType.changeType(JobICProps.type);

		jobICProps.setHandlerURI("com.likya.myra.jef.jobs.TLInterComm");

		jobICProps.setId("123245");
		jobICProps.setAgentId(1);

		ICParams icParams = jobICProps.addNewICParams();
		icParams.setCommandType(CommandType.JOB_STATUSLIST);
		icParams.setAppStatusInfo(AppStatusInfoType.STATE_SUSPENDED);
		icParams.setHostName("hostName");
		icParams.setClientId("client1");
		icParams.setPort(0);
		icParams.setUserName("userName");
		icParams.setUserPassword("userPass");
		icParams.setPollperiod(new GDuration("P1M"));

		Management management = jobICProps.addNewManagement();
		
		TimeManagement timeManagement = management.addNewTimeManagement();
		
		JsActualTime jsPlannedTime = timeManagement.addNewJsActualTime();
		jsPlannedTime.setStartTime(DateUtils.getCalendarInstance());
		
		JsTimeOut jsTimeOut = timeManagement.addNewJsTimeOut();
		jsTimeOut.setUnit(Unit.SECONDS);
		jsTimeOut.setValueInteger(BigInteger.valueOf(100));
		
		JsRecordedTime jsRecordedTime = timeManagement.addNewJsRecordedTime();
		jsRecordedTime.setStartTime(DateUtils.getCalendarInstance());
		jsRecordedTime.setStopTime(DateUtils.getCalendarInstance());
		
		ExpectedTime expectedTime = timeManagement.addNewExpectedTime();
		expectedTime.setUnit(Unit.SECONDS);
		expectedTime.setValueInteger(BigInteger.valueOf(100));
		
		BaseJobInfos baseJobInfos = jobICProps.addNewBaseJobInfos();
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
		
		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setStep(new GDuration());

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);
		
		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);
		
		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);

		DependencyList dependencyList = jobICProps.addNewDependencyList();
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

		LiveStateInfo liveStateInfo = jobICProps.addNewStateInfos().addNewLiveStateInfos().addNewLiveStateInfo();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.IDLED);
		liveStateInfo.setStatusName(StatusName.BYTIME);

		XmlObject xmlObject = XmlObject.Factory.parse(jobICProps.toString());

		XmlObject genericObject = jobList.addNewGenericJob();
		genericObject.set(xmlObject);

		JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());

		System.err.println(jobListDocumentNew.toString());

		System.err.println("Valid = " + jobListDocumentNew.validate());

		return jobListDocumentNew;

	}
}
