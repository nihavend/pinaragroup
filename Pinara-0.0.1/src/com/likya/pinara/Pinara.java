package com.likya.pinara;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.ValidPlatforms;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.InputStrategy;
import com.likya.myra.jef.InputStrategyImpl;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraOutput;
import com.likya.pinara.utils.license.LicenseManager;
import com.likya.pinara.utils.license.ValidateLicense;
import com.likya.xsd.myra.model.config.MyraConfigDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument;

public class Pinara extends PinaraBase {

	private Pinara() {
		super();
	}

	private static Pinara pinara;

	public static void main(String[] args) {

		registerMessageBundle();

		parseCmdArgs(args);

		checkStartUp();

		ConfigurationManager configurationManager = new ConfigurationManagerImpl();

		PinaraConfigDocument pinaraConfigDocument = null;

		String configFile = "pinaraConfig.xml";

		StringBuffer xmlString = FileUtils.readFile(configFile);

		try {
			pinaraConfigDocument = PinaraConfigDocument.Factory.parse(xmlString.toString());

			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), pinaraConfigDocument)) {
				throw new XmlException(configFile + " is null or damaged !");
			}

			configurationManager.setPinaraConfig(pinaraConfigDocument.getPinaraConfig());
		} catch (XmlException e) {
			e.printStackTrace();
			return;
		}

		try {
			initPinara(configurationManager);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private static void initPinara(ConfigurationManager configurationManager) throws Exception, Throwable {

		
		// PinaraLogManager.setLogLevelMin(PinaraAppender.PINARA_CONSOLE, Level.INFO);
		// PinaraLogManager.setLogLevelMin(PinaraAppender.MYRA_CONSOLE, Level.INFO);
		
		pinara = new Pinara();

		pinara.setConfigurationManager(configurationManager);

		pinara.loadAuthenticationInfo();

		pinara.startInfoServers();

		pinara.startCommServer();

		pinara.startWebManager();

		if (!pinara.initMyra()) {
			throw new Exception();
		}
		
		PinaraAppManagerImpl.initialize();

		pinara.sendStartUpInfos();
	}

	private static void checkStartUp() {

		println(Pinara.getMessage("Pinara.4"));
		println(Pinara.getMessage("Pinara.5"));
		println(Pinara.getMessage("Pinara.6") + Pinara.getVersion() + Pinara.getMessage("Pinara.7"));
		println(Pinara.getMessage("Pinara.8"));
		println(Pinara.getMessage("Pinara.4"));
		println(Pinara.getMessage("Pinara.9") + " => " + Pinara.class.getProtectionDomain().getCodeSource().getLocation().getFile());
		println();

		println(Pinara.getMessage("Pinara.10"));

		try {
			Pinara.setLicensed(LicenseManager.isLicenseValid(Pinara.getLicenseInfo()));
		} catch (Exception e) {
			// e.printStackTrace();
			println(Pinara.getMessage("Pinara.11"));
		}

		if (LicenseManager.isExpired()) {
			println(Pinara.getMessage("Pinara.4"));
			println(Pinara.getMessage("Pinara.13"));
			println(Pinara.getMessage("Pinara.14"));
			println(Pinara.getMessage("Pinara.15"));
			println(Pinara.getMessage("Pinara.4"));
		}

		if (!ValidPlatforms.isOSValid()) {
			println(Pinara.getMessage("Pinara.17") + System.getProperty("os.name"));
		}

	}

	private boolean initMyra() throws Throwable {

		StringBuffer xmlString = getMyraData();

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());

		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}

		InputStrategy inputStrategy = new InputStrategyImpl();

		MyraConfigDocument myraConfigDocument = null;
		
		// String myraConfigFile = "/Users/serkan/git/localgit/TL-2.0.0-Test/conf/myraConfig.xml";

		String myraConfigFile = getConfigurationManager().getPinaraConfig().getMyraConfigFile();

		xmlString = FileUtils.readFile(myraConfigFile);
		
		com.likya.myra.jef.ConfigurationManager configurationManager;

		try {
			myraConfigDocument = MyraConfigDocument.Factory.parse(xmlString.toString());
			
			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), myraConfigDocument)) {
				throw new Exception("myraConfigDocument is null or damaged !");
			}

			configurationManager = new com.likya.myra.jef.ConfigurationManagerImpl(myraConfigDocument);
		} catch (XmlException e) {
			e.printStackTrace();
			return false;
		}

		inputStrategy.setConfigurationManager(configurationManager);
		inputStrategy.setJobListDocument(jobListDocument);

		PinaraOutput testOutput = PinaraOutput.getInstance();

		CoreFactory coreFactory = (CoreFactory) CoreFactory.getInstance(inputStrategy, testOutput);

		if (!validateJobList(jobListDocument)) {
			throw new Exception("Invalid jobListDocument !");
		}

		ManagementOperations managementOperations = coreFactory.getManagementOperations();

		try {
			managementOperations.start();
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static StringBuffer getMyraData() throws Exception {

		StringBuffer xmlString = null;

		xmlString = FileUtils.readFile(pinara.getConfigurationManager().getPinaraConfig().getSenaryoDosyasi());

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());

		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}

		return xmlString;
	}

	public static void parseCmdArgs(String[] args) {

		String arg;
		int i = 0;

		while (i < args.length && args[i].startsWith("-")) {

			arg = args[i++];

			if (arg.equals("-loadTest")) {
				println(Pinara.getMessage("Pinara.2"));
				// Pinara.setLoadTest(true);
			} else if (arg.equals("-licensepath")) {
				if (i < args.length) {
					ValidateLicense.setLicensePath(args[i++]);
				}
			}
		}
	}

	public static Pinara getInstance() {
		return pinara;
	}

}