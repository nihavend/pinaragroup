package com.likya.myra.test.helpers;

import java.math.BigInteger;
import java.util.Calendar;

import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams;
import com.likya.xsd.myra.ext.odi.OdiExtProperties;
import com.likya.xsd.myra.model.generics.JobCommandTypeDocument.JobCommandType;
import com.likya.xsd.myra.model.generics.JobTypeDetailsDocument.JobTypeDetails;
import com.likya.xsd.myra.model.generics.UnitDocument.Unit;
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
import com.likya.xsd.myra.model.wlagen.JsScheduledTimeDocument.JsScheduledTime;
import com.likya.xsd.myra.model.wlagen.JsTimeOutDocument.JsTimeOut;
import com.likya.xsd.myra.model.wlagen.JsTypeDocument.JsType;
import com.likya.xsd.myra.model.wlagen.TimeManagementDocument.TimeManagement;
import com.likya.xsd.myra.model.wlagen.TriggerDocument.Trigger;

public class OdiExtPropsGenerator {

	public static XmlObject generate() throws Exception {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();

		OdiExtProperties odiExtProperties = (OdiExtProperties) abstractJobType.changeType(OdiExtProperties.type);

		odiExtProperties.setHandlerURI("com.likya.myra.ext.odi.OdiExtJob");

		odiExtProperties.setGroupId("grp-1");

		odiExtProperties.setId("11");
		odiExtProperties.setAgentId(1);

		OdiExtParams odiExtParams = odiExtProperties.addNewOdiExtParams();

		odiExtParams.setPJdbcUrl("jdbc:oracle:thin:@192.168.1.75:1521:orcl");
		odiExtParams.setPJdbcDriver("oracle.jdbc.OracleDriver");
		odiExtParams.setPJdbcUsername("DEV_ODI_REPO");
		odiExtParams.setPJdbcPassword("ad0215");

		odiExtParams.setPWorkName("WORKREP");

		odiExtParams.setPUsername("SUPERVISOR");
		odiExtParams.setPPassword("SUNOPSIS");
		odiExtParams.setPAgentUrl("http://192.168.1.73:20910/oraclediagent");
		odiExtParams.setPScenName("LKYA_ODI_INTERFACE2");
		odiExtParams.setPScenVersion("001");
		odiExtParams.setPKeywords("");
		odiExtParams.setPContextCode("GLOBAL");
		odiExtParams.setPLogLevel(BigInteger.valueOf(5));
		odiExtParams.setPSessionName("TLOSSW");
		odiExtParams.setPSynchronous(true);

		Management management = odiExtProperties.addNewManagement();

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

		BaseJobInfos baseJobInfos = odiExtProperties.addNewBaseJobInfos();
		baseJobInfos.setUserId(1);
		baseJobInfos.setJsName("My name is");
		baseJobInfos.setJobLogFile("demo.log");
		baseJobInfos.setJobLogPath("./");
		baseJobInfos.setOSystem(OsType.MACOS);
		baseJobInfos.setJobPriority(JobPriority.X_1);
		baseJobInfos.setJsIsActive(true);

		JobTypeDetails jobTypeDetails = baseJobInfos.addNewJobTypeDetails();
		jobTypeDetails.setJobCommand("/home/likya/kursat/uzaktan_calisan_job.sh");
		jobTypeDetails.setJobCommandType(JobCommandType.REMOTE_SHELL);

		management.setTrigger(Trigger.TIME);

		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setStep(new GDuration("PT0H30M0S"));

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);

		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);
		jobAutoRetryInfo.setStep(new GDuration("PT0H30M0S"));
		jobAutoRetryInfo.setMaxCount(BigInteger.valueOf(3));

		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);

		DependencyList dependencyList = odiExtProperties.addNewDependencyList();
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

		LiveStateInfo liveStateInfo = odiExtProperties.addNewStateInfos().addNewLiveStateInfos().addNewLiveStateInfo();
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
