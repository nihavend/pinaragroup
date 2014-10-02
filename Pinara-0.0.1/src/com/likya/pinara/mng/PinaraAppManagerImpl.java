package com.likya.pinara.mng;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.likya.myra.commons.utils.JobListFilter;
import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.commons.utils.StateFilter;
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
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

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

	public HashMap<String, AbstractJobType> getFreeJobs() throws PinaraAuthenticationException {
		
		if(!authorize()) {
			throw new PinaraAuthenticationException();
		}

		return CoreFactory.getInstance().getNetTreeManagerInterface().getFreeJobs();
	}
	
}
