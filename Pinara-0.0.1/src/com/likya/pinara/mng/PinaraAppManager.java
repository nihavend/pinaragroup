package com.likya.pinara.mng;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.model.PinaraXMLValidationException;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public interface PinaraAppManager {

	public void retryExecution(String jobName) throws PinaraAuthenticationException;

	public void setSuccess(String jobName) throws PinaraAuthenticationException;

	public void skipJob(String jobName) throws PinaraAuthenticationException;

	public void skipJob(boolean isForced, String jobName) throws PinaraAuthenticationException;

	public void stopJob(String jobName) throws PinaraAuthenticationException;

	public void pauseJob(String jobName) throws PinaraAuthenticationException;

	public void resumeJob(String jobName) throws PinaraAuthenticationException;

	public void startJob(String jobName) throws PinaraAuthenticationException;

	public void disableJob(String jobName) throws PinaraAuthenticationException;

	public void enableJob(String jobName) throws PinaraAuthenticationException;

	public void updateStartConditions(String jobName, Date myDate) throws PinaraAuthenticationException;

	public void suspendApp() throws PinaraAuthenticationException;

	public void resumeApp() throws PinaraAuthenticationException;

	public void gracefulShutDown() throws PinaraAuthenticationException;

	public void forceFullShutDown() throws PinaraAuthenticationException;
	
	public void cleanUpRepeatatives() throws PinaraAuthenticationException;

	public String setJobInputParam(String jobName, String parameterList) throws PinaraAuthenticationException;

	public Collection<AbstractJobType> getJobList(StateName.Enum filterStates[]) throws PinaraAuthenticationException;

	public HashMap<String, JobImpl> getJobQueue() throws PinaraAuthenticationException ;
	
	public JobImpl getJob(String jobId) throws PinaraAuthenticationException;
	
	public HashMap<String, NetTree> getNetTreeMap() throws PinaraAuthenticationException;

	public HashMap<String, String> getFreeJobs() throws PinaraAuthenticationException;
	
	public String addJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException;
	
	public String updateJob(String jobXml, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException;
	
	public void deleteJob(String jobId, boolean persist) throws PinaraAuthenticationException, PinaraXMLValidationException;
	
	public void enableGroup(String grpId) throws PinaraAuthenticationException;

	public void disableGroup(String grpId) throws PinaraAuthenticationException;
	
	public void changeGroupName(String netTreeGrpId, String newGrpName) throws PinaraAuthenticationException;
	
	public String readMailInfo() throws PinaraAuthenticationException, PinaraXMLValidationException;
	
	public void writeMailInfo(String mailInfoXml) throws PinaraAuthenticationException, PinaraXMLValidationException, Exception;
	
	public void updateJobArgs(String jobId, String args) throws PinaraAuthenticationException, PinaraXMLValidationException;
}
