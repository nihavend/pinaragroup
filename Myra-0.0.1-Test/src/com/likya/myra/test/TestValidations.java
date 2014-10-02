package com.likya.myra.test;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.test.helpers.OdiExtPropsGenerator;
import com.likya.xsd.myra.model.intcomm.ICParamsDocument.ICParams;
import com.likya.xsd.myra.model.intcomm.JobICProps;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.joblist.RemoteSchProperties;
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
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;
import com.likya.xsd.myra.model.wlagen.JobAutoRetryInfoDocument.JobAutoRetryInfo;
import com.likya.xsd.myra.model.wlagen.JobPriorityDocument.JobPriority;
import com.likya.xsd.myra.model.wlagen.JsTypeDocument.JsType;
import com.likya.xsd.myra.model.wlagen.TriggerDocument.Trigger;

public class TestValidations {

	public static void main(String[] args) {
		try {
			validateGen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void validateGen() throws Exception {

		XmlObject xmlObject = null;

		// xmlObject = SimplePropsGenerator.generate();
		// xmlObject = RemoteShPropsGenerator.generate();
		//		xmlObject = JobICPropsGenerator.generate();

		// xmlObject = ConfigGenerator.generate();

		// xmlObject = PinaraConfigGenerator.generate();

		// xmlObject = buildDocument();
		
		xmlObject = OdiExtPropsGenerator.generate();

		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), xmlObject)) {
			throw new Exception("JobList.xml is null or damaged !");
		}

	}

	public static XmlObject buildDocument() {

//		XmlOptions opt = (new XmlOptions()).setSavePrettyPrint();
//
//		JobListTestDocument jobListTestDocument = JobListTestDocument.Factory.newInstance();
//		JobListTestDocument.JobListTest jobList = jobListTestDocument.addNewJobListTest();
//
//		System.out.println("Empty document:\n" + jobListTestDocument.xmlText(opt) + "\n");
//
//		MyAbstractType abstractJobType1 = jobList.addNewGenericJob();
//		abstractJobType1.setAbstractId("1");
//
//		MyAbstractType abstractJobType2 = jobList.addNewGenericJob();
//		abstractJobType2.setAbstractId("2");
//
//		System.out.println("Document containing the abstract types:\n" + jobListTestDocument.xmlText(opt));
//		System.out.println("Valid = " + jobListTestDocument.validate() + "\n");
//
//		WhoProperties whoProperties = (WhoProperties) abstractJobType1.changeType(WhoProperties.type);
//		whoProperties.setWho("who");
//
//		SerkanProperties serkanProperties = (SerkanProperties) abstractJobType2.changeType(SerkanProperties.type);
//		serkanProperties.setTas("taş");
//
//		System.out.println("Final document:\n" + jobListTestDocument.xmlText(opt));
//		System.out.println("Vald = " + jobListTestDocument.validate());
//
		return null; //jobListTestDocument;
	}

	public static JobListDocument generateAbstractXMLDoc() throws XmlException {

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
		JobList jobList = jobListDocument.addNewJobList();

		AbstractJobType abstractJobType = jobList.addNewGenericJob();

		abstractJobType.changeType(RemoteSchProperties.type);

		abstractJobType.setHandlerURI("com.likya.myra.jef.jobs.ExecuteInShell");

		abstractJobType.setGroupId("deneme");
		abstractJobType.setId("123245");
		abstractJobType.setAgentId(1);

		Management management = abstractJobType.addNewManagement();

		BaseJobInfos baseJobInfos = abstractJobType.addNewBaseJobInfos();
		baseJobInfos.setUserId(1);
		baseJobInfos.setJsName("My name is");
		baseJobInfos.setJobLogFile("demo.log");
		baseJobInfos.setJobLogPath("./");
		baseJobInfos.setOSystem(OsType.MACOS);
		baseJobInfos.setJobPriority(JobPriority.X_1);
		baseJobInfos.setJsIsActive(true);

		management.setTrigger(Trigger.TIME);

		PeriodInfo periodInfo = management.addNewPeriodInfo();
		periodInfo.setStep(new GDuration());

		CascadingConditions cascadingConditions = management.addNewCascadingConditions();
		cascadingConditions.setRunEvenIfFailed(true);
		cascadingConditions.setJobSafeToRestart(true);

		JobAutoRetryInfo jobAutoRetryInfo = JobAutoRetryInfo.Factory.newInstance();
		jobAutoRetryInfo.setJobAutoRetry(true);

		cascadingConditions.setJobAutoRetryInfo(jobAutoRetryInfo);

		DependencyList dependencyList = abstractJobType.addNewDependencyList();
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

		LiveStateInfo liveStateInfo = abstractJobType.addNewStateInfos().addNewLiveStateInfos().addNewLiveStateInfo();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(StateName.PENDING);
		liveStateInfo.setSubstateName(SubstateName.IDLED);
		liveStateInfo.setStatusName(StatusName.BYTIME);

		abstractJobType.addNewManagement().addNewTimeManagement();

		//		JobICProps jobICProps = (JobICProps) abstractJobType.changeType(JobICProps.type);

		JobICProps jobICProps = (JobICProps) abstractJobType;

		jobICProps.setId("");

		ICParams icParams = jobICProps.addNewICParams();
		//tlosInterCommParams.setHostName("hostName");
		icParams.setIpAddress("192.168.1.1");
		icParams.setPort(0);
		icParams.setUserName("userName");
		icParams.setUserPassword("userPass");

		JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());

		System.err.println(jobListDocumentNew.toString());

		return jobListDocumentNew;
	}
}
