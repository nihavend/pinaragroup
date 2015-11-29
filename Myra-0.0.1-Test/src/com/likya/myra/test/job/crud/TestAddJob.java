package com.likya.myra.test.job.crud;

import org.apache.log4j.Logger;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.JobOperations;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.jef.jobs.ChangeLSI;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class TestAddJob {

	public static void main(String[] args) {

		try {
			
			final TestOutput testOutput = new TestOutput();
			
			CoreFactory coreFactory = (CoreFactory) CoreFactory.getInstance(testOutput);
			
			ManagementOperations managementOperations = coreFactory.getManagementOperations();
			try {
				managementOperations.start();
			} catch (Throwable e) {
				e.printStackTrace();
				return;
			}
			
			StringBuffer xmlString = getData(null);

			JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());

			AbstractJobType abstractJobType = jobListDocument.getJobList().getGenericJobArray(1);

			// MonitoringOperations monitoringOperations = CoreFactory.getInstance().getMonitoringOperations();
			JobOperations jobOperations = coreFactory.getJobOperations();
			
			if (!LiveStateInfoUtils.equalStatesPD(abstractJobType)) {
				ChangeLSI.forValue(abstractJobType, LiveStateInfoUtils.generateLiveStateInfo(StateName.INT_PENDING, SubstateName.INT_DEACTIVATED));
			}

			jobOperations.addJob(abstractJobType, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static StringBuffer getData(String senaryo) throws Exception {

		StringBuffer xmlString = null;

		if (senaryo == null) {

			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/JobList.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/JobList02.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeSch.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeInShell.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeInShellBulk.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/8dep.xml");

			xmlString = FileUtils.readFile("/Users/serkan/git/pinaragroup/Myra-0.0.1-Test/data/3dep.xml");
		} else {
			xmlString = FileUtils.readFile(senaryo);
		}

		// 

		// System.err.println(jobListDocument.toString());

		// GlobalStateDefinitionDocument globalStateDefinitionDocument = null;

		//		globalStateDefinitionDocument = GlobalStateGenerator.generate();
		//		
		//		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), globalStateDefinitionDocument)) {
		//			throw new Exception("JobList.xml is null or damaged !");
		//		}

		// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/globalStates.xml");

		// globalStateDefinitionDocument = GlobalStateDefinitionDocument.Factory.parse(xmlString.toString());

		// configurationManager.getTemporaryConfig().setGlobalStateDefinition(globalStateDefinitionDocument.getGlobalStateDefinition());

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());

		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}

		return xmlString;
	}
}
