package com.likya.pinara.mng;

import java.net.UnknownServiceException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.likya.commons.utils.DateUtils;
import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.model.UnresolvedDependencyException;
import com.likya.myra.commons.utils.JobDependencyResolver;
import com.likya.myra.commons.utils.JobListFilter;
import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.commons.utils.StateFilter;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.JobOperations;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.jef.core.ManagementOperationsImpl;
import com.likya.myra.jef.core.MonitoringOperations;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.Pinara;
import com.likya.pinara.PinaraBase.EventTypeInfo;
import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.model.PinaraXMLValidationException;
import com.likya.pinara.utils.PersistApi;
import com.likya.pinara.utils.PersistDBApi;
import com.likya.pinara.utils.xml.mappers.ConfigurationMapper;
import com.likya.pinara.utils.xml.mappers.NetTreeMapper;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.wlagen.OperatingSystemTypeEnumeration;
import com.likya.xsd.pinara.model.config.MailInfoDocument;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument;

public final class PinaraAppManagerImpl implements PinaraAppManager {
	
	private PinaraAppManagerImpl() {
		super();
	}

	private static PinaraAppManager pinaraManager;
	
	private static ManagementOperations managementOperations;
	private static MonitoringOperations monitoringOperations;
	private static JobOperations jobOperations;

	public static PinaraAppManager initialize() throws InstanceNotFoundException {
		return getInstance();
	}
	
	public static PinaraAppManager getInstance() throws InstanceNotFoundException {
		if (pinaraManager == null) {
			if(CoreFactory.getInstance() == null) {
				throw new InstanceNotFoundException();
			}
				
			pinaraManager = new PinaraAppManagerImpl();
			monitoringOperations = CoreFactory.getInstance().getMonitoringOperations();
			jobOperations = CoreFactory.getInstance().getJobOperations();
			managementOperations = CoreFactory.getInstance().getManagementOperations();
		}
		return pinaraManager;
	}
	
	private static boolean authorize() {
		return true; //AuthanticationImpl.authorize(Pinara.getInstance().getConfigurationManager().getSessionUser());
	}
	
	public void retryExecution(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.retryExecution(jobName);
	}

	
	public void setSuccess(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.setSuccess(jobName);
	}

	
	public void skipJob(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.skipJob(jobName);
	}

	
	public void skipJob(boolean isForced, String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

	}

	
	public void stopJob(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.stopJob(jobName);
	}

	
	public void pauseJob(String jobName) throws PinaraAuthenticationException{
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.pauseJob(jobName);
	}

	
	public void resumeJob(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.resumeJob(jobName);

	}

	
	public void startJob(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

		jobOperations.startJob(jobName);
		
	}
	
	public void disableJob(String jobName) throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		jobOperations.disableJob(jobName);
	}

	public void enableJob(String jobName) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		jobOperations.enableJob(jobName);
	}
	
	public void enableGroup(String grpId) throws PinaraAuthenticationException {

		if (!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		jobOperations.enableGroup(grpId);

	}
	
	public void disableGroup(String grpId) throws PinaraAuthenticationException {

		if (!authorize()) {
			throw new PinaraAuthenticationException();
		}

		jobOperations.disableGroup(grpId);
	}
	
	public void updateStartConditions(String jobName, Date myDate) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

	}

	
	public void suspendApp() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		managementOperations.suspend();
	}

	
	public void resumeApp() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		managementOperations.resume();
	}

	
	public void gracefulShutDown() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		shutDown(false);
	}

	
	public void forceFullShutDown() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		shutDown(true);
	}

	private void shutDown(boolean isForced) throws PinaraAuthenticationException {
		
		ManagementOperationsImpl.setExecutionState(CoreStateInfo.STATE_STOP);
		
		PinaraMailServer pinaraMailServer = Pinara.getInstance().getConfigurationManager().getPinaraMailServer();
		PinaraOutputManager pinaraOutputManager = Pinara.getInstance().getConfigurationManager().getPinaraOutputManager();
		PinaraSMSServer pinaraSMSServer = Pinara.getInstance().getConfigurationManager().getPinaraSmsServer();
		TcpManagementConsole tcpManagementConsole = Pinara.getInstance().getConfigurationManager().getTcpManagementConsole();
		
		long startTime = DateUtils.getCurrentTimeMilliseconds();
		Pinara.getInstance().sendInfos(EventTypeInfo.ShutDown);
		long duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
		System.err.println("pinara.sendShutDownInfos()" + " in " + duration + " ms");
		System.out.println();
		
		if(isForced) {
			managementOperations.forceFullShutDown();
		} else {
			managementOperations.gracefulShutDown();
		}
		
		if(pinaraMailServer != null) {
			pinaraMailServer.terminate(isForced);
		}

		if(pinaraOutputManager != null) {
			pinaraOutputManager.terminate(isForced);
		}

		if(pinaraSMSServer != null) {
			pinaraSMSServer.terminate(isForced);
		}
		
		if(tcpManagementConsole != null) {
			tcpManagementConsole.terminate(isForced);
		}
		
		new Thread(new Runnable() {
			boolean isLoop = true;
			public void run() {
				while (isLoop) {
					try {
						// Wait until sending response to web clients
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// || pinaraSMSServer.getExecutorThread().isAlive();
					isLoop = 
					((pinaraMailServer == null || pinaraMailServer.getExecutorThread() == null) ? false : pinaraMailServer.getExecutorThread().isAlive()) || 
					((pinaraOutputManager == null || pinaraOutputManager.getExecutorThread() == null) ? false : pinaraOutputManager.getExecutorThread().isAlive()); 
				}
				System.exit(0);				
			}
		}).start();
		
	}
	
	public static CoreStateInfo getExecutionState() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		return ManagementOperationsImpl.getExecutionState();
	}
	
	public static void setExecutionState(CoreStateInfo coreStateInfo) throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		ManagementOperationsImpl.setExecutionState(coreStateInfo);
		return;
	}
	
	public void cleanUpRepeatatives() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
	}

	
	public String setJobInputParam(String jobName, String parameterList) throws PinaraAuthenticationException  {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		return null;
	}

	public Collection<AbstractJobType> getJobList(StateName.Enum filterStates[]) throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		// StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING};
		JobListFilter jobListFilter = new StateFilter(filterStates);
		Collection<AbstractJobType> retList = null;
		
		try {
			retList = monitoringOperations.getJobList(jobListFilter.anyPredicate());
		} catch(Throwable t) {
			t.printStackTrace();
		}
		
		return retList;
	}
	
	public HashMap<String, JobImpl> getJobQueue() throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		try {
			return monitoringOperations.getJobQueue();
		} catch(Throwable t) {
			t.printStackTrace();
		}
		
		return null;
	}

	public JobImpl getJob(String jobId) throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		try {
			return monitoringOperations.getJobQueue().get(jobId);
		} catch(Throwable t) {
			t.printStackTrace();
		}
		
		return null;
	}

	public HashMap<String, NetTree> getNetTreeMap() throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		return CoreFactory.getInstance().getNetTreeManagerInterface().getNetTreeMap();
	}

	public HashMap<String, String> getFreeJobs() throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

		return CoreFactory.getInstance().getNetTreeManagerInterface().getFreeJobs();
	}
	
	public void deleteJob(String jobId, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {
		try {
			// DB yapısı öncesi böyleydi
			// jobOperations.deleteJob(jobId, persist);
			// PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
			
			// DB yapısı sonrası			// DB yapısı sonrası -- ilk olarak RT'ye ekliyoruz çünkü jobid orada oluşuyor.
			jobOperations.deleteJob(jobId, persist);
			scanTreeForChange(null, true);
			PersistDBApi.deleteJob(jobId);
		} catch (UnknownServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			throw new PinaraXMLValidationException(e.getMessage());
		}
	}
	
	public String updateJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {
		AbstractJobType abstractJobType = validateJob(jobXml, persist);
		try {
			// DB yapısı öncesi böyleydi
			// jobOperations.updateJob(abstractJobType, persist);
			// PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
			
			// DB yapısı sonrası -- ilk olarak RT'ye ekliyoruz çünkü jobid orada oluşuyor.
			jobOperations.updateJob(abstractJobType, persist);
			scanTreeForChange(abstractJobType, false);
			PersistDBApi.saveJob(abstractJobType);
			return abstractJobType.getId();
		} catch (UnknownServiceException e) {
			// e.printStackTrace();
			throw new PinaraXMLValidationException(e);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new PinaraXMLValidationException(e);
		}
	}

	public String addJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {
		
		AbstractJobType abstractJobType = validateJob(jobXml, persist);
		try {
			// DB yapısı öncesi böyleydi
			// jobOperations.addJob(abstractJobType, persist);
			// PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
			
			// DB yapısı sonrası -- ilk olarak RT'ye ekliyoruz çünkü jobid orada oluşuyor.
			jobOperations.addJob(abstractJobType, persist);
			scanTreeForChange(abstractJobType, false);
			PersistDBApi.saveJob(abstractJobType);
			return abstractJobType.getId();
		} catch (UnknownServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private AbstractJobType validateJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {

		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

		JobListDocument jobListDocument;

		try {
			
			// jobXml = header + jobXml + footer;
			
			ArrayList<String> errMsgLst = new ArrayList<String>();
			
			jobListDocument = JobListDocument.Factory.parse(jobXml.toString());
	
			// version
			jobListDocument.getJobList().setVersion(Pinara.getVersion());
			// LSIDateTime
			jobListDocument.getJobList().getGenericJobArray(0).setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
			// setting default not important
			jobListDocument.getJobList().getGenericJobArray(0).getBaseJobInfos().setOSystem(OperatingSystemTypeEnumeration.WINDOWS);
			
			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument, errMsgLst)) {
				System.err.println("JobList.xml is null or damaged !");
				
				String[] strArr = errMsgLst.toArray(new String[errMsgLst.size()]);
				
				throw new PinaraXMLValidationException(strArr);
				// throw new PinaraAuthenticationException();
			}
			
			AbstractJobType abstractJobType = jobListDocument.getJobList().getGenericJobArray()[0];
			
			DependencyList dependencyList = abstractJobType.getDependencyList();
			if (dependencyList != null && dependencyList.getItemArray().length != 0) {
				try {
					JobDependencyResolver.validateDepExp(null, dependencyList.getDependencyExpression(), abstractJobType.getBaseJobInfos().getJsName());
				} catch (UnresolvedDependencyException e) {
					String errMsg = "Dependency expression is not defined or invalid !";
					System.err.println(errMsg);
					throw new PinaraXMLValidationException(errMsg);
				}
			}
			
			return abstractJobType;
			
		} catch (XmlException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public void changeGroupName(String netTreeGrpId, String newGrpName) throws PinaraAuthenticationException {
		
		if (!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		NetTree netTree;
		ArrayList<AbstractJobType> abstractJobTypeList = null;
		HashMap<String, String> freeJobs = null;
		ArrayList<String> jobIdList = new ArrayList<>();
		
		try {
			//Bagimsiz joblar icin changeGrpName ekrandan simdilik yapilmiyor.
			if(NetTreeMapper.getFreeJobsGrpId().equals(netTreeGrpId)) {
				freeJobs = PinaraAppManagerImpl.getInstance().getFreeJobs();
				for(String jobId : freeJobs.values()) {
					jobIdList.add(jobId);
				}
			} else {
				netTree = PinaraAppManagerImpl.getInstance().getNetTreeMap().get(netTreeGrpId);
				for (String jobId : netTree.getMembers()) {
					jobIdList.add(jobId);
				}
			}
			
			abstractJobTypeList = jobOperations.changeGrpName(jobIdList, newGrpName);
			
			for(AbstractJobType job : abstractJobTypeList) {
				PersistDBApi.saveJob(job);
			}
			
		}  catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	
	/*
	 * a.the job we are working on is updatedJob
	 * b.affectedNetTree is the node which updatedJob is inside
	 * c.firstJobInNetTree is the first level job in the tree diagram
	 * So we find firstJobInNetTree in affectedNetTree to use its scenarioId to name other jobs scenarioId's
	 * which doesn't have same name with. These are the cases which can generate new dependent group: 
	 * 1.Two independent jobs can generate new dependent group
	 * 2.One job (can be from dependent or independent group) or group can join at down or middle level of dependent group tree
	 * 3.One job (can be from dependent or independent group) or group can join at first level of dependent group tree
	 * 4.updatedJob can move to another group so it should take this group name
	 * 5.one dependent group can become two pieces with same group name 
	 */
	private void scanTreeForChange(AbstractJobType updatedJob, boolean isDeleteOperation) {
		
		HashMap<String, AbstractJobType> jobMap;
		HashMap<String, NetTree> netTreeMap = null;
		HashMap<String, String> freeJobs = null;
		NetTree affectedNetTree;
		AbstractJobType firstJobInNetTree;
		ArrayList<AbstractJobType> jobListForUpdate = new ArrayList<>();
		ArrayList<Object> affectedNode = new ArrayList<>();
		
		try {
			netTreeMap = PinaraAppManagerImpl.getInstance().getNetTreeMap();
			freeJobs = PinaraAppManagerImpl.getInstance().getFreeJobs();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (PinaraAuthenticationException e) {
			e.printStackTrace();
		}
		jobMap = JobQueueOperations.toAbstractJobTypeList(CoreFactory.getInstance().getMonitoringOperations().getJobQueue());
		
		//freeNode scan for add, update, delete
		for(String jobId : freeJobs.values()) {
			if(jobMap.get(jobId) != null && !NetTreeMapper.freeGrpDefaultName.equals(jobMap.get(jobId).getScenarioId())) {
				jobListForUpdate.add(jobMap.get(jobId));
			}
		}
		
		if(jobListForUpdate.size() > 0) {
			updateTreeNodes(jobListForUpdate, NetTreeMapper.freeGrpDefaultName);
		}
		
		//depNode scan for add, update
		if(!isDeleteOperation) {
			jobListForUpdate = new ArrayList<>();
			boolean nodeFound = findAffectedNodeInTree(affectedNode, updatedJob, jobMap, netTreeMap);
			
			if(nodeFound) {
				affectedNetTree = (NetTree) affectedNode.get(0);
				firstJobInNetTree = (AbstractJobType) affectedNode.get(1);
				
				for(String jobId : affectedNetTree.getMembers()) {
					if(firstJobInNetTree.getScenarioId() != null && jobMap.get(jobId) != null) {
						if(firstJobInNetTree.getScenarioId().equals(NetTreeMapper.freeGrpDefaultName)) {
							jobListForUpdate.add(jobMap.get(jobId));
						} else {
							if(!firstJobInNetTree.getScenarioId().equals(jobMap.get(jobId).getScenarioId())) {
								jobListForUpdate.add(jobMap.get(jobId));
							}
						}
					}
				}
				
				if(jobListForUpdate.size() > 0) {
					updateTreeNodes(jobListForUpdate, firstJobInNetTree.getScenarioId().equals(NetTreeMapper.freeGrpDefaultName) ? NetTreeMapper.depGrpDefaultName : firstJobInNetTree.getScenarioId());
				}
			}
		}
		
	}
	
	private boolean findAffectedNodeInTree(ArrayList<Object> affectedNode, AbstractJobType updatedJob, HashMap<String, AbstractJobType> jobMap, HashMap<String, NetTree> netTreeMap) {
		
		boolean affectedNetTreeFound = false;
		boolean firstJobInNetTreeFound = false;
		NetTree affectedNetTree = null;
		AbstractJobType firstJobInNetTree = null;
		
		for(NetTree netTree : netTreeMap.values()) {
			if(!affectedNetTreeFound) {
				for(String jobId : netTree.getMembers()) {
					if(updatedJob.getId().equals(jobId)) {
						affectedNetTree = netTree;
						affectedNode.add(0, netTree);
						affectedNetTreeFound = true;
					}
				}
			}
		}
		
		if(affectedNetTreeFound) {
			for(String jobId : affectedNetTree.getMembers()) {
				if(jobMap.get(jobId).getDependencyList() == null || jobMap.get(jobId).getDependencyList().sizeOfItemArray() == 0) {
					
					firstJobInNetTree = jobMap.get(jobId);
					
					if(!firstJobInNetTreeFound) {
						affectedNode.add(1, firstJobInNetTree);
						firstJobInNetTreeFound = true;
					} else {
						if((!NetTreeMapper.freeGrpDefaultName.equals(firstJobInNetTree.getScenarioId()) && !updatedJob.getScenarioId().equals(firstJobInNetTree.getScenarioId()))
								|| NetTreeMapper.freeGrpDefaultName.equals(((AbstractJobType) affectedNode.get(1)).getScenarioId()) ) {
							affectedNode.add(1, firstJobInNetTree);
						}
					}
					
				}
			}
		}
		
		return (affectedNetTreeFound && firstJobInNetTreeFound) ? true : false;
	}
	
	private void updateTreeNodes(ArrayList<AbstractJobType> jobList, String scenarioId) {
		String tmpScenarioId = "";
		for(AbstractJobType job : jobList) {
			tmpScenarioId = job.getScenarioId();
			job.setScenarioId(scenarioId);
			PersistDBApi.saveJob(job);
			Pinara.getLogger().debug("CHANGEDINTREE-AND-UPDATED:---->" + "jobId: " + job.getId() + "--" + "jobName: " + job.getBaseJobInfos().getJsName() + "--"  + "ScenarioOLD: " + tmpScenarioId + "--" + "ScenarioNEW: " + job.getScenarioId() + "--" + "GroupId: " + job.getGroupId());
		}
	}

	public String readMailInfo() throws PinaraAuthenticationException, PinaraXMLValidationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

		String mailInfo;
		PinaraConfigDocument pinaraConfigDocument = null;
		
		Path configFile = Paths.get(Pinara.CONFIG_FILE_PATH);
		StringBuffer configFileXmlStr = FileUtils.readFile(configFile.toString());
		try {
			pinaraConfigDocument = PinaraConfigDocument.Factory.parse(configFileXmlStr.toString());
		} catch (XmlException e) {
			String errMsg = "Pinara Configuration file couldn't been read !";
			System.err.println(errMsg);
			throw new PinaraXMLValidationException(errMsg);
		}
		
		//mailInfo = ConfigurationMapper.getMappedMail(Pinara.getInstance().getConfigurationManager().getPinaraConfig().getMailInfo());
		mailInfo = ConfigurationMapper.getMappedMail(pinaraConfigDocument.getPinaraConfig().getMailInfo());
		
		return mailInfo;
	}

	public void writeMailInfo(String mailInfoXml) throws PinaraAuthenticationException, PinaraXMLValidationException, Exception {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		Path configFile = Paths.get(Pinara.CONFIG_FILE_PATH);
		PinaraConfigDocument pinaraConfigDocument = null;
		MailInfoDocument mailInfoDocument = validateMailInfo(mailInfoXml);
		boolean requestMailInfoEnabled = mailInfoDocument.getMailInfo().getEnabled(); 
		StringBuffer configFileXmlStr = FileUtils.readFile(configFile.toString());
		
		try {
			pinaraConfigDocument = PinaraConfigDocument.Factory.parse(configFileXmlStr.toString());
			boolean configFileMailInfoEnabled = pinaraConfigDocument.getPinaraConfig().getMailInfo().getEnabled();
			pinaraConfigDocument.getPinaraConfig().setMailInfo(mailInfoDocument.getMailInfo());
			
			if(!requestMailInfoEnabled) {
				Pinara.getInstance().getConfigurationManager().getPinaraMailServer().terminate(false);
				Pinara.getInstance().getConfigurationManager().getPinaraConfig().setMailInfo(mailInfoDocument.getMailInfo());
			} else {
				if(!configFileMailInfoEnabled) {
					PinaraMailServer.engage(mailInfoDocument.getMailInfo());
					Pinara.getInstance().getConfigurationManager().getPinaraConfig().setMailInfo(mailInfoDocument.getMailInfo());
				} else {
					Pinara.getInstance().getConfigurationManager().getPinaraMailServer().setMailInfoStr(mailInfoXml);
					Pinara.getInstance().getConfigurationManager().getPinaraMailServer().setReLoadParamsFlag(true);
				}
			}
			
			PersistApi.serializeAsFlat(configFile.toString(), pinaraConfigDocument.toString());
		} catch (Exception e) {
			String errMsg = "Pinara Configuration file couldn't been saved for new Mail Configuration !";
			System.err.println(errMsg);
			throw new PinaraXMLValidationException(errMsg);
		} catch (Throwable e) {
			String errMsg = "Pinara Configuration file couldn't been saved, PinaraMailServer is still running !";
			System.err.println(errMsg);
			throw new Exception(errMsg);
		}
				
	}
	
	private MailInfoDocument validateMailInfo(String mailInfoXml) throws PinaraAuthenticationException, PinaraXMLValidationException {
		MailInfoDocument mailInfoDocument = null;
		
		try {
			ArrayList<String> errMsgLst = new ArrayList<String>();
			mailInfoDocument = MailInfoDocument.Factory.parse(mailInfoXml);
			
			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), mailInfoDocument, errMsgLst)) {
				System.err.println("JobList.xml is null or damaged !");
				
				String[] strArr = errMsgLst.toArray(new String[errMsgLst.size()]);
				
				throw new PinaraXMLValidationException(strArr);
			}
			
			return mailInfoDocument;
		} catch (XmlException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateJobArgs(String jobId, String args) throws PinaraAuthenticationException, PinaraXMLValidationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		AbstractJobType[] abstractJobTypes = new AbstractJobType[1];
		AbstractJobType job = getJob(jobId).getAbstractJobType();
		if(args != null) {
			job.getBaseJobInfos().getJobTypeDetails().setArgValues(args);
		} else {
			job.getBaseJobInfos().getJobTypeDetails().unsetArgValues();;
		}
		abstractJobTypes[0] = job;
		
		JobListDocument jobList = JobListDocument.Factory.newInstance();
		jobList.addNewJobList();
		jobList.getJobList().setGenericJobArray(abstractJobTypes);
		try {
			updateJob(jobList.toString(), true);
		} catch (PinaraXMLValidationException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
