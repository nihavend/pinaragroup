package com.likya.myra.test;

import org.apache.log4j.Logger;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.ConfigurationManager;
import com.likya.myra.jef.ConfigurationManagerImpl;
import com.likya.myra.jef.InputStrategy;
import com.likya.myra.jef.InputStrategyImpl;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.test.helpers.TestOutput;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class FirstMyra {

	public static void main(String[] args) throws Exception {
		
		StringBuffer xmlString = getData("/Users/serkan/git/pinaragroup/Myra-0.0.1-Test/data/2dep.xml");
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		
		// String configFile = "/Users/serkan/git/localgit/TL-2.0.0-Test/conf/myraConfig.xml";
		
		// xmlString = FileUtils.readFile(configFile);
		
		InputStrategy inputStrategy = new InputStrategyImpl();
		
		ConfigurationManager configurationManager;

		configurationManager = new ConfigurationManagerImpl();

		inputStrategy.setConfigurationManager(configurationManager);
		inputStrategy.setJobListDocument(jobListDocument);
		
		final TestOutput testOutput = new TestOutput();
		
		CoreFactory coreFactory = (CoreFactory) CoreFactory.getInstance(inputStrategy, testOutput);
		
		ManagementOperations managementOperations = coreFactory.getManagementOperations();
		try {
			managementOperations.start();
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}
		

	}

	public static StringBuffer getData(String senaryo) throws Exception {

		StringBuffer xmlString;

		if (senaryo == null) {

			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/JobList.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/JobList02.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeSch.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeInShell.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/executeInShellBulk.xml");
			// xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/8dep.xml");

			xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/1.xml");
		}

		xmlString = FileUtils.readFile(senaryo);

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
