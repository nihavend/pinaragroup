package com.likya.pinara;

import java.net.SocketException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.LocaleMessages;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.myra.jef.model.JobRuntimeInterface;
import com.likya.myra.jef.model.JobRuntimeProperties;
import com.likya.pinara.comm.JmxManagementConsole;
import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.gui.WebManager;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSHandler;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.infobus.SMSType;
import com.likya.pinara.infobus.WelcomeMail;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.LicenseInfo;
import com.likya.pinara.model.PinaraAuthorization;
import com.likya.pinara.model.User;
import com.likya.pinara.model.User.RoleInfo;
import com.likya.pinara.model.User.StatuInfo;
import com.likya.pinara.utils.AuthorizationLoader;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;
import com.likya.xsd.pinara.model.config.McInfoDocument.McInfo;
import com.likya.xsd.pinara.model.config.SmsInfoDocument.SmsInfo;
import com.likya.xsd.pinara.model.config.TcpInfoDocument.TcpInfo;

public abstract class PinaraBase {

	private final static Logger logger = Logger.getLogger(Pinara.class);

	public static final String DEF_0_9_2 = "0.9.2";

	public static final String DEF_0_9_1 = "0.9.1";
	
	private static final String version = DEF_0_9_2;

	private static LicenseInfo licenseInfo = new LicenseInfo();

	protected static boolean isLicensed = false;

	protected final static String localePath = "com.likya.pinara.resources.messages";

	private ConfigurationManager configurationManager;

	public final static String authTxt = "pinara";
	
	public PinaraBase() {
		super();
	}

	protected static void registerMessageBundle() {
		LocaleMessages.registerBundle(localePath);
		logger.info(Pinara.getMessage("Pinara.37") + localePath);
	}

	protected void loadAuthenticationInfo() {

		logger.info(Pinara.getMessage("PinaraServer.1"));

		PinaraAuthorization pinaraAuthorization = null;

		try {
			pinaraAuthorization = AuthorizationLoader.readAuthorization();
		} catch (Exception fnf) {
			try {
				pinaraAuthorization = new PinaraAuthorization();
				pinaraAuthorization.addUser(new User(RoleInfo.ADMIN, StatuInfo.ACTIVE, authTxt, authTxt));
			} catch (Exception e) {
				logger.fatal(Pinara.getMessage("PinaraServer.3") + AuthorizationLoader.fileToPersist + Pinara.getMessage("PinaraServer.2"));
				logger.fatal(Pinara.getMessage("PinaraServer.7"));
				e.printStackTrace();
				System.exit(-1);
			}
			AuthorizationLoader.persistAuthorization(pinaraAuthorization);
		}

		configurationManager.setPinaraAuthorization(pinaraAuthorization);

		logger.info(Pinara.getMessage("PinaraServer.9"));

	}

	protected void startInfoServers() {
		
		PinaraOutputManager pinaraOutputManager = new PinaraOutputManager();
		Thread pinaraOutputManagerThread = new Thread(pinaraOutputManager);
		pinaraOutputManagerThread.start();

		configurationManager.setPinaraOutputManager(pinaraOutputManager);

		MailInfo mailInfo = configurationManager.getPinaraConfig().getMailInfo();

		if (mailInfo != null) {

			println(Pinara.getMessage("Pinara.24"));
			PinaraMailServer pinaraMailServer = new PinaraMailServer(mailInfo);
			Thread pinaraMailServerThread = new Thread(pinaraMailServer);
			pinaraMailServerThread.start();

			println(getMessage("Pinara.25"));
			configurationManager.setPinaraMailServer(pinaraMailServer);

		}

		SmsInfo smsInfo = configurationManager.getPinaraConfig().getSmsInfo();

		if (smsInfo != null) {

			PinaraSMSHandler pinaraSMSHandler = loadSMSLib(smsInfo.getSmsHandlerURI());

			if (pinaraSMSHandler != null) {
				println(getMessage("Pinara.26"));
				PinaraSMSServer pinaraSmsServer = new PinaraSMSServer(smsInfo, pinaraSMSHandler);
				Thread pinaraSmsServerThread = new Thread(pinaraSmsServer);
				pinaraSmsServerThread.start();
				println(getMessage("Pinara.27"));
				configurationManager.setPinaraSmsServer(pinaraSmsServer);
			} else {
				println(getMessage("Pinara.28"));
				// tlosParameters.setSms(false);
			}

			if (pinaraSMSHandler.getMsisdnList().size() <= 0) {
				println(Pinara.getMessage("Pinara.29"));
				// TlosServer.getTlosParameters().setSms(false);
			}
		}

		/*
		 * println(Pinara.getMessage("Pinara.30"));
		 * TlosServer tlosServer = new TlosServer();
		 * 
		 * Thread tlosServerThread = new Thread(tlosServer);
		 * // likyaSchedulerThread.setDaemon(true);
		 * tlosServerThread.start();
		 */

		// println(Pinara.getMessage("Pinara.31"));
		// println(Pinara.getMessage("Pinara.4"));
	}

	protected static PinaraSMSHandler loadSMSLib(String libName) {

		PinaraSMSHandler pinaraSMSHandler = null;

		try {
			logger.info(Pinara.getMessage("Tlos.33") + libName);
			@SuppressWarnings("rawtypes")
			Class handlerClass = Class.forName(libName);

			Object myObject = handlerClass.newInstance();

			if (myObject instanceof PinaraSMSHandler) {
				pinaraSMSHandler = (PinaraSMSHandler) (myObject);
			}
			logger.info(Pinara.getMessage("Tlos.34"));
		} catch (Exception e) {
			System.out.println(Pinara.getMessage("Tlos.35") + libName);
			System.out.println(Pinara.getMessage("Tlos.36"));
			e.printStackTrace();
		}

		return pinaraSMSHandler;
	}

	protected void startCommServer() throws InstanceNotFoundException {

		TcpInfo tcpInfo = getConfigurationManager().getPinaraConfig().getTcpInfo();

		if (tcpInfo != null) {
			
			logger.info(getMessage("PinaraServer.18"));
			
			try {
				TcpManagementConsole managementConsoleHandler = TcpManagementConsole.initComm(PinaraAppManagerImpl.getInstance(), tcpInfo.getTcpPort(), getConfigurationManager().getPinaraConfig().getServerIpAddress());
				configurationManager.setTcpManagementConsole(managementConsoleHandler);
				new Thread(managementConsoleHandler).start();
			} catch (SocketException e) {
				logger.fatal(getMessage("PinaraServer.19"));
				logger.fatal(getMessage("PinaraServer.20"));
				e.printStackTrace();
				System.exit(-1);
			}
			logger.info(getMessage("PinaraServer.21"));
			
		}

		McInfo mcInfo = getConfigurationManager().getPinaraConfig().getMcInfo();

		if (mcInfo != null) {

			boolean isMc = Pinara.getLicenseInfo().isUseManagementConsole();
			// TODO lisans kontrolü geçici olarak kaldırıldı
			isMc = true;

			if (isMc) {
				logger.info(getMessage("PinaraServer.22"));
				try {
					JmxManagementConsole.initialize(mcInfo.getJmxPort(), getConfigurationManager().getPinaraConfig().getServerIpAddress());
				} catch (Exception e) {
					logger.fatal(getMessage("PinaraServer.23"));
					logger.fatal(getMessage("PinaraServer.24"));
					e.printStackTrace();
					System.exit(-1);
				}
				logger.info(getMessage("PinaraServer.25"));
			} else {
				logger.info(getMessage("PinaraServer.4"));
			}

		}

	}

	protected void startWebManager() {

		logger.info(Pinara.getMessage("PinaraServer.11"));
		WebManager webManager = new WebManager();
		logger.info(Pinara.getMessage("PinaraServer.12") + webManager.getHostName() + " " + Pinara.getMessage("PinaraServer.14") + webManager.getHttpPort());
		try {
			webManager.initServer();
		} catch (Exception e) {
			logger.fatal(Pinara.getMessage("PinaraServer.15"));
			logger.fatal(Pinara.getMessage("PinaraServer.16"));
			e.printStackTrace();
			System.exit(-1);
		}
		logger.info(Pinara.getMessage("PinaraServer.17"));
	}

	protected boolean validateJobList(JobListDocument jobListDocument) {

		XmlObject[] objectArray = jobListDocument.getJobList().getGenericJobArray();

		JobRuntimeInterface jobRuntimeInterface = new JobRuntimeProperties();
		
		for(Object loopObject : objectArray) {
			
			AbstractJobType abstractJobType = (AbstractJobType) loopObject;
			
			Class<?> abstractClass;
			
			try {
				abstractClass = Class.forName(abstractJobType.getHandlerURI());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}

			try {
				JobImpl jobImpl = (JobImpl) abstractClass.getDeclaredConstructor(new Class[] { AbstractJobType.class, JobRuntimeInterface.class }).newInstance(abstractJobType, jobRuntimeInterface);
				jobImpl.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	
	public void sendStartUpInfos() {
		
		MailInfo mailInfo = configurationManager.getPinaraConfig().getMailInfo();
		
		if (mailInfo != null) {
			logger.info(getMessage("PinaraServer.26"));

			try {
				HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
				configurationManager.getPinaraMailServer().sendMail(new WelcomeMail(jobQueue));
			} catch (Exception e) {
				logger.fatal(getMessage("PinaraServer.27"));
				logger.fatal(getMessage("PinaraServer.28"));
				e.printStackTrace();
				System.exit(-1);
			}
			logger.info(getMessage("PinaraServer.29"));
		}
		
		SmsInfo smsInfo = configurationManager.getPinaraConfig().getSmsInfo();
		
		if (smsInfo != null) {
			logger.info(getMessage("PinaraServer.30"));

			try {
				String smsText = getMessage("PinaraServer.31") + configurationManager.getPinaraConfig().getInstanceName() + getMessage("PinaraServer.32") + getMessage("PinaraServer.33");
				configurationManager.getPinaraSmsServer().sendSMS(new SMSType(smsText));
			} catch (Exception e) {
				logger.fatal(getMessage("PinaraServer.34"));
				logger.fatal(getMessage("PinaraServer.35"));
				e.printStackTrace();
				System.exit(-1);
			}
			logger.info(getMessage("PinaraServer.36"));
		}
		
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static String getMessage(String key) {
		return LocaleMessages.getString(localePath, key);
	}

	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}

	protected void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

	public static void print(String message) {
		System.out.print(message);
	}

	public static void println(String message) {
		System.out.println(message);
	}

	public static void println() {
		System.err.println();
	}

	public static void printerr(String message) {
		System.err.print(message);
	}

	public static void printlnerr(String message) {
		System.err.println(message);
	}

	public static void printlnerr() {
		System.err.println();
	}

	public static String getVersion() {
		return version;
	}

	public static boolean isLicensed() {
		return isLicensed;
	}

	public static void setLicensed(boolean isLicensed) {
		PinaraBase.isLicensed = isLicensed;
	}

	public static LicenseInfo getLicenseInfo() {
		return licenseInfo;
	}
}
