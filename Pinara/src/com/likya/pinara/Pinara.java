package com.likya.pinara;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.ValidPlatforms;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.myra.jef.utils.Starter;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraOutput;
import com.likya.pinara.utils.PersistApi;
import com.likya.pinara.utils.RecoveryHelper;
import com.likya.pinara.utils.license.LicenseClientUtil;
import com.likya.pinara.utils.license.LicenseManager;
import com.likya.pinara.utils.license.ValidateLicense;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument;

public class Pinara extends PinaraBase {

	public static final String CONFIG_PATH = "conf";
	public static final String CONFIG_FILE = "pinaraConfig.xml";
	
	public static final String DATA_PATH = "data";
	
	public static String suspendFlag = "locked";
	public static boolean forceToRecover = false;
	
	public static final String ulicense = "unlicensed";
	public static final String license = "licensed";
	
	public static String licenseFlag = ulicense;
	
	private Pinara() {
		super();
	}

	private static Pinara pinara;

	public static void main(String[] args) {
		
		checkDataPath();

		ConfigurationManager configurationManager = loadConfig();

		registerMessageBundle();

		parseCmdArgs(args);

		checkStartUp();

		try {
			initPinara(configurationManager);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	private static void checkDataPath() {
		Path dataPath = Paths.get(DATA_PATH);
		if (Files.notExists(dataPath)) {
			try {
				Files.createDirectory(dataPath);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	private static ConfigurationManager loadConfig() {

		ConfigurationManager configurationManager = new ConfigurationManagerImpl();

		PinaraConfigDocument pinaraConfigDocument = null;

		Path configFile = Paths.get(CONFIG_PATH + File.separator + CONFIG_FILE);

		if (Files.notExists(configFile)) {
			printlnerr("Dosya belirtilen dizinde bulunamadı : " + configFile.toString());
			System.exit(-1);
		}

		StringBuffer xmlString = FileUtils.readFile(configFile.toString());

		try {
			pinaraConfigDocument = PinaraConfigDocument.Factory.parse(xmlString.toString());

			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), pinaraConfigDocument)) {
				throw new XmlException(configFile + " is null or damaged !");
			}

			configurationManager.setPinaraConfig(pinaraConfigDocument.getPinaraConfig());

		} catch (XmlException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		return configurationManager;
	}

	private static void initPinara(ConfigurationManager configurationManager) throws Exception, Throwable {

		// PinaraLogManager.setLogLevelMin(PinaraAppender.PINARA_CONSOLE, Level.INFO);
		// PinaraLogManager.setLogLevelMin(PinaraAppender.MYRA_CONSOLE, Level.INFO);

		long startTime = System.currentTimeMillis();
		pinara = new Pinara();
		long duration = System.currentTimeMillis() - startTime;
		System.err.println("new Pinara()" + " in " + duration + " ms");

		pinara.setConfigurationManager(configurationManager);

		pinara.loadAuthenticationInfo();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.loadAuthenticationInfo()" + " in " + duration + " ms");

		pinara.startInfoServers();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.startInfoServers()" + " in " + duration + " ms");

		pinara.startCommServer();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.startCommServer()" + " in " + duration + " ms");

		pinara.startWebManager();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.startWebManager()" + " in " + duration + " ms");

		if (!pinara.initMyra()) {
			throw new Exception();
		}
		
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.initMyra()" + " in " + duration + " ms");

		PinaraAppManagerImpl.initialize();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("PinaraAppManagerImpl.initialize()" + " in " + duration + " ms");

		if(PinaraAppManagerImpl.getExecutionState().equals(CoreStateInfo.STATE_STARTING)) {
			PinaraAppManagerImpl.setExecutionState(CoreStateInfo.STATE_WORKING);
		}

		pinara.sendStartUpInfos();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.sendStartUpInfos()" + " in " + duration + " ms");
		System.out.println();

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

		if (LicenseClientUtil.deserialize() == null) {
			licenseFlag = ulicense;
			synchronized (licenseFlag) {
				licenseFlag.wait();
				if(!isLicensed()) {
					System.err.println("Your license expired, contact to your vendor !");
					System.exit(-1);
				}
				licenseFlag = license;
			}
		} else {
			licenseFlag = license;
		}
		
		if(RecoveryHelper.isInRecoveryState()) {
			synchronized (suspendFlag) {
				getLogger().warn("Waiting user to decide what to do recover or go ahead !");
				suspendFlag.wait();
				suspendFlag = "unlocked";
			}
		} else {
			suspendFlag = "unlocked";
		}

		PinaraOutput testOutput = PinaraOutput.getInstance();
		
		if(forceToRecover) {
			Starter.startRecover(testOutput);
		} else {
		
			JobListDocument jobListDocument = PersistApi.deserialize();
	
			if (jobListDocument == null) {
				jobListDocument = JobListDocument.Factory.newInstance();
				jobListDocument.addNewJobList();
	
				PersistApi.serialize(jobListDocument);
			}
	
			Starter.start(jobListDocument, testOutput);
		}

		return true;

	}

	//	public boolean initMyraOld() throws Throwable {
	//
	//		StringBuffer xmlString = getMyraData();
	//
	//		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
	//
	//		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
	//			throw new Exception("JobList.xml is null or damaged !");
	//		}
	//
	//		InputStrategy inputStrategy = new InputStrategyImpl();
	//
	//		MyraConfigDocument myraConfigDocument = null;
	//		
	//		// String myraConfigFile = "/Users/serkan/git/localgit/TL-2.0.0-Test/conf/myraConfig.xml";
	//
	//		String myraConfigFile = getConfigurationManager().getPinaraConfig().getMyraConfigFile();
	//
	//		xmlString = FileUtils.readFile(myraConfigFile);
	//		
	//		com.likya.myra.jef.ConfigurationManager configurationManager;
	//
	//		try {
	//			myraConfigDocument = MyraConfigDocument.Factory.parse(xmlString.toString());
	//			
	//			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), myraConfigDocument)) {
	//				throw new Exception("myraConfigDocument is null or damaged !");
	//			}
	//
	//			configurationManager = new com.likya.myra.jef.ConfigurationManagerImpl(myraConfigDocument);
	//		} catch (XmlException e) {
	//			e.printStackTrace();
	//			return false;
	//		}
	//
	//		inputStrategy.setConfigurationManager(configurationManager);
	//		inputStrategy.setJobListDocument(jobListDocument);
	//
	//		PinaraOutput testOutput = PinaraOutput.getInstance();
	//
	//		CoreFactory coreFactory = (CoreFactory) CoreFactory.getInstance(inputStrategy, testOutput);
	//
	//		if (!validateJobList(jobListDocument)) {
	//			throw new Exception("Invalid jobListDocument !");
	//		}
	//
	//		ManagementOperations managementOperations = coreFactory.getManagementOperations();
	//
	//		try {
	//			managementOperations.start();
	//		} catch (Throwable e) {
	//			e.printStackTrace();
	//			return false;
	//		}
	//
	//		return true;
	//
	//	}

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
