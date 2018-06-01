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
import com.likya.myra.jef.core.CoreFactoryInterface;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.myra.jef.utils.Starter;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraOutput;
import com.likya.pinara.utils.DataMigration;
import com.likya.pinara.utils.PersistApi;
import com.likya.pinara.utils.PersistDBApi;
import com.likya.pinara.utils.RecoveryHelper;
import com.likya.pinara.utils.license.LicenseClientUtil;
import com.likya.pinara.utils.license.LicenseManager;
import com.likya.pinara.utils.license.ValidateLicense;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument;

public class Pinara extends PinaraBase {

	public static String CONFIG_FILE_PATH = "conf" + File.separator + "pinaraConfig.xml";
	
	public static String DATA_PATH = "data";
	
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
		
		parseCmdArgs(args);
		
		checkDataPath();

		ConfigurationManager configurationManager = loadConfig();

		registerMessageBundle();

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

		Path configFile = Paths.get(CONFIG_FILE_PATH);

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
		
		pinara.startCommServer();
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.startCommServer()" + " in " + duration + " ms");
		
		if(PinaraAppManagerImpl.getExecutionState().equals(CoreStateInfo.STATE_STARTING)) {
			PinaraAppManagerImpl.setExecutionState(CoreStateInfo.STATE_WORKING);
		}

		pinara.sendInfos(EventTypeInfo.StartUp);
		duration = System.currentTimeMillis() - startTime;
		System.err.println("pinara.sendStartUpInfos()" + " in " + duration + " ms");
		System.out.println();

	}

	private static void checkStartUp() {

		println(Pinara.getMessage("Pinara.4"));
		println(Pinara.getMessage("Pinara.5"));
		loadVersionInfo();
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
			CoreFactoryInterface coreFactoryInterface = Starter.startRecover(testOutput);
			
			if(coreFactoryInterface == null) {
				return false;
			}
		} else {
		
			// DB yapısı öncesi
			// JobListDocument jobListDocument = PersistApi.deserialize();
	
			// if (jobListDocument == null) {
			//	jobListDocument = JobListDocument.Factory.newInstance();
			//	jobListDocument.addNewJobList().setVersion(getVersion());
			//	
			//	PersistApi.serialize(jobListDocument);
			// } else {
			//	String dataFileVersion = jobListDocument.getJobList().getVersion();
			//	if(!getVersion().equals(dataFileVersion)) {
			//		jobListDocument = migrateDataFile(jobListDocument, dataFileVersion);
			//		if(jobListDocument == null) {
			//			return false;
			//		}
			//	}
			// }
			
			PersistDBApi.checkDbFolder();
			
			JobListDocument jobListDocument = null;
			
			// DB Yapısı Gelince : 
			// V1.0 itibarı ile pinara config içinde senaryo dosyası da kalkmalı, migration da !
			JobListDocument jobListDocumentRecent = PersistApi.deserialize();
			if (jobListDocumentRecent != null) {
				String dataFileVersion = jobListDocumentRecent.getJobList().getVersion();
				jobListDocument = migrateDataFile(jobListDocumentRecent, dataFileVersion);
			} else { 
				// else : Fresh install, no need for migration
				jobListDocument = PersistDBApi.readJobs();
			}
	
			/**
			 * Generate runtime structure of data from persisted file.
			 */
			if(Starter.start(jobListDocument, testOutput) == null) {
				return false;
			}
		}

		return true;

	}
	
	private JobListDocument migrateDataFile(JobListDocument jobListDocument, String dataFileVersion) throws Exception {
		
		getLogger().warn("Data file belongs to " + dataFileVersion + " version of application, trying to migrate to " + getVersion() + " ...");
		
		PersistApi.backupScenario();

		if(dataFileVersion == null) dataFileVersion = "";
		
		switch (dataFileVersion) {
		case "":
			/** migrate from null to 0.9.1 */
			String jobListDocumentStr = PersistApi.deserializeAsFlat();
			jobListDocument = DataMigration.migrate_null_to_0_9_1(jobListDocumentStr);
			break;

		case DEF_0_9_1:
			/** migrate from 0.9.1 to 0.9.2 */
			jobListDocumentStr = PersistApi.deserializeAsFlat();
			jobListDocument = DataMigration.migrate_null_to_0_9_2(jobListDocumentStr);
			PersistApi.deleteScenario();
		default:
			break;
		}
		
		if (!XMLValidations.validateWithXSDAndLog(getLogger(), jobListDocument)) {
			getLogger().error("Migration is not successfull due to an internal error, contact to vendor !");
			return null;
		}
		
		return jobListDocument;
		
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

//	public static StringBuffer getMyraData() throws Exception {
//
//		StringBuffer xmlString = null;
//
//		xmlString = FileUtils.readFile(pinara.getConfigurationManager().getPinaraConfig().getSenaryoDosyasi());
//
//		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
//
//		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
//			throw new Exception("JobList.xml is null or damaged !");
//		}
//
//		return xmlString;
//	}

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
			} else if (arg.equals("-cfg")) {
				if (i < args.length)
					CONFIG_FILE_PATH = args[i++];			
			} else if (arg.equals("-dpath")) {
				if (i < args.length)
					DATA_PATH = args[i++];
			}
		}
	}

	public static Pinara getInstance() {
		return pinara;
	}

}
