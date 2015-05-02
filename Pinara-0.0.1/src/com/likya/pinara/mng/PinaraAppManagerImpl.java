package com.likya.pinara.mng;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.likya.myra.commons.model.UnresolvedDependencyException;
import com.likya.myra.commons.utils.JobDependencyResolver;
import com.likya.myra.commons.utils.JobListFilter;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.commons.utils.StateFilter;
import com.likya.myra.jef.core.Commandability;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.JobOperations;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.jef.core.MonitoringOperations;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.pinara.Pinara;
import com.likya.pinara.comm.TcpManagementConsole;
import com.likya.pinara.infobus.PinaraMailServer;
import com.likya.pinara.infobus.PinaraOutputManager;
import com.likya.pinara.infobus.PinaraSMSServer;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.model.PinaraXMLValidationException;
import com.likya.pinara.utils.PersistApi;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.wlagen.OperatingSystemTypeEnumeration;

public final class PinaraAppManagerImpl implements PinaraAppManager {
	
	private PinaraAppManagerImpl() {
		super();
	}

	private static PinaraAppManager pinaraManager;
	
	private static ManagementOperations managementOperations;
	private static MonitoringOperations monitoringOperations;
	private static JobOperations jobOperations;

	public static PinaraAppManager initialize() {
		return getInstance();
	}
	
	public static PinaraAppManager getInstance() {
		if (pinaraManager == null) {
			pinaraManager = new PinaraAppManagerImpl();
			monitoringOperations = CoreFactory.getInstance().getMonitoringOperations();
			if(monitoringOperations == null) {
				throw new NullArgumentException("monitoringOperations");
			}
			jobOperations = CoreFactory.getInstance().getJobOperations();
			managementOperations = CoreFactory.getInstance().getManagementOperations();
		}
		return pinaraManager;
	}
	
	private boolean authorize() {
		return AuthanticationImpl.authorize(Pinara.getInstance().getConfigurationManager().getUser());
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
		// TODO Auto-generated method stub

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
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		HashMap<String, NetTree> netTreeMap = PinaraAppManagerImpl.getInstance().getNetTreeMap();
		HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
		
		if(netTreeMap.containsKey(grpId)) {
			NetTree netTree = netTreeMap.get(grpId);
			for(String jobId : netTree.getMembers()) {
				AbstractJobType abstractJobType = jobQueue.get(jobId).getAbstractJobType();
				if(Commandability.isEnablable(abstractJobType)) {
					jobOperations.enableJob(jobId);
				}
			}
		}
		
	}
	
	public void disableGroup(String grpId) throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		
		HashMap<String, NetTree> netTreeMap = PinaraAppManagerImpl.getInstance().getNetTreeMap();
		HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
		
		if(netTreeMap.containsKey(grpId)) {
			NetTree netTree = netTreeMap.get(grpId);
			for(String jobId : netTree.getMembers()) {
				AbstractJobType abstractJobType = jobQueue.get(jobId).getAbstractJobType();
				if(Commandability.isDisablable(abstractJobType)) {
					jobOperations.disableJob(jobId);
				}
			}
		}
		
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
		
		managementOperations.setExecutionState(CoreStateInfo.STATE_STOP);
		
		PinaraMailServer pinaraMailServer = Pinara.getInstance().getConfigurationManager().getPinaraMailServer();
		PinaraOutputManager pinaraOutputManager = Pinara.getInstance().getConfigurationManager().getPinaraOutputManager();
		PinaraSMSServer pinaraSMSServer = Pinara.getInstance().getConfigurationManager().getPinaraSmsServer();
		TcpManagementConsole tcpManagementConsole = Pinara.getInstance().getConfigurationManager().getTcpManagementConsole();
		
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
			
			public void run() {
				try {
					// Wait until sending response to web clients
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);				
			}
		}).start();
		
	}
	
	public CoreStateInfo getExecutionState() throws PinaraAuthenticationException {
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}
		return managementOperations.getExecutionState();
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
			jobOperations.deleteJob(jobId, persist);
			PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
		} catch (UnknownServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			throw new PinaraXMLValidationException(e.getMessage());
		}
	}
	
	public void updateJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {
		AbstractJobType abstractJobType = validateJob(jobXml, persist);
		try {
			jobOperations.updateJob(abstractJobType, persist);
			PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
		} catch (UnknownServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException {
		
		AbstractJobType abstractJobType = validateJob(jobXml, persist);
		try {
			jobOperations.addJob(abstractJobType, persist);
			PersistApi.serialize(CoreFactory.getInstance().getJobListDocument());
		} catch (UnknownServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}
