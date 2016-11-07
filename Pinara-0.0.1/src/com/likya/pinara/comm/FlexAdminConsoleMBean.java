package com.likya.pinara.comm;

import com.likya.pinara.model.PinaraConnection;
import com.likya.pinara.model.PinaraData;
import com.likya.pinara.model.User;


public interface FlexAdminConsoleMBean {

	public void checkPinaraConnection();

	public PinaraData showLogFile(PinaraConnection pinaraConnection, String jobId, Integer command);

	//public TlosData filterByGroupId(PinaraConnection pinaraConnection, String command);

	public PinaraData refresh(PinaraConnection pinaraConnection, String command);

	public PinaraData changePassword(User user, String newPassword);

	public PinaraData init(PinaraConnection pinaraConnection, String command);

	//public TlosData view(PinaraConnection pinaraConnection, String command);

	public void skipJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void setSuccess(PinaraConnection pinaraConnection, String jobName, String command);

	public void retryJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void resumeJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void pauseJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void stopJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void startJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void enableJob(PinaraConnection pinaraConnection, String jobName, String command);

	public void disableJob(PinaraConnection pinaraConnection, String jobName, String command);

	public PinaraData resumeTlos(User user, String command);

	public PinaraData suspendTlos(User user, String command);

	public PinaraData forcefulShutDown(User user);

	public PinaraData gracefulShutDown(User user);

	public PinaraData setJobInputParam(PinaraConnection pinaraConnection, String jobName, String parameterList, String command);

	/**
	 * Getter: set the "State" attribute of the "Test" standard MBean.
	 * 
	 * @return the current value of the "State" attribute.
	 */
	public String getState();

	/**
	 * Setter: set the "State" attribute of the "Test" standard MBean.
	 * 
	 * @param <VAR>s</VAR> the new value of the "State" attribute.
	 */
	public void setState(String s);

	/**
	 * Getter: get the "NbChanges" attribute of the "Test" standard MBean.
	 * 
	 * @return the current value of the "NbChanges" attribute.
	 */
	public int getNbChanges();

	/**
	 * Operation: reset to their initial values the "State" and "NbChanges"
	 * attributes of the "Test" standard MBean.
	 */
	public void reset();
}
