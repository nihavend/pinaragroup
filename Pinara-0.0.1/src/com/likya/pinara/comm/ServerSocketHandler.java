package com.likya.pinara.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManager;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.model.TrxInfo;

public class ServerSocketHandler implements Runnable {

	private Socket client;
	private PinaraAppManager pinaraManager;
	
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;
	
	public ServerSocketHandler(Socket client, PinaraAppManager pinaraManager) {
		super();
		this.client = client;
		this.pinaraManager = pinaraManager;
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName("ServerSocketHandler");
		try {	
		
			HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue(); 
			
			Logger logger = CoreFactory.getLogger();
			
			TrxInfo tlosInfo = readData(client, objectInputStream);		
			
			int commandTypeValue = tlosInfo.getCommandType().getId();
			logger.info(Pinara.getMessage("ServerSocketHandler.0") + tlosInfo.getCommandType().getDescription());
			logger.info(Pinara.getMessage("ServerSocketHandler.1") + tlosInfo.getClientId());
			
			switch (commandTypeValue) {
			
			case TrxInfo.NORMAL_TERMINATE:
				logger.info(Pinara.getMessage("ServerSocketHandler.2"));
				
				tlosInfo.setErrCode(0);
				writeData(client, tlosInfo);
				cleanUp();
				
				pinaraManager.gracefulShutDown();
//				loopPermit = false;
				
				break;
			
			case TrxInfo.FORCED_TERMINATE:
				logger.info(Pinara.getMessage("ServerSocketHandler.3"));
				
				tlosInfo.setErrCode(0);
				writeData(client, tlosInfo);
				cleanUp();
				
				pinaraManager.forceFullShutDown();
//				loopPermit = false;
				
				break;

			case TrxInfo.RESUME_JOB:
				
				String jobName = tlosInfo.getJobId();
				
				// JobImpl tmpJob = jobQueue.get(jobName);
				boolean isResumable = true;
				
				// if(jobQueue.containsKey(jobName) && ((jobQueue.get(jobName).getJobProperties().getStatus() == JobProperties.FAIL) || (TlosServer.getJobQueue().get(jobName).getJobProperties().getStatus() == JobProperties.STOP))) {
				if(isResumable) {
					logger.info(Pinara.getMessage("ServerSocketHandler.4") + jobName + " READY !"); //$NON-NLS-2$
					pinaraManager.retryExecution(jobName);
				} else {
					logger.info(Pinara.getMessage("ServerSocketHandler.6") + jobName);
					tlosInfo.setErrCode(1);
				}
				tlosInfo.setErrCode(0);
				
				writeData(client, tlosInfo);
				cleanUp();
				
				break;
			
			case TrxInfo.TLOS_STATUS:					
				
				boolean isThreshold = CoreFactory.getInstance().getMonitoringOperations().isThresholdOverflow();
				
				if (isThreshold) {
					tlosInfo.setTlosStatus(TrxInfo.STATE_JOBOVERFLOW);
					tlosInfo.setErrCode(0);
				} else if (CoreFactory.getInstance().getManagementOperations().getExecutionState() == CoreStateInfo.STATE_SUSPENDED/*TrxInfo.STATE_SUSPENDED*/) {
					tlosInfo.setTlosStatus(TrxInfo.STATE_SUSPENDED);
					tlosInfo.setErrCode(0);
				} else {
					tlosInfo.setErrCode(1);
					tlosInfo.setErrDesc(Pinara.getMessage("ServerSocketHandler.7"));
				}
				writeData(client, tlosInfo);
				cleanUp();
				
				break;
			
			case TrxInfo.JOB_STATUSLIST:

				jobName = tlosInfo.getJobId();
				if(jobQueue.containsKey(jobName)) {
					ArrayList<Integer> statusList = new ArrayList<Integer>();
//					statusList.add(jobQueue.get(jobName).getJobProperties().getStatus());
//					
//					for (int i = jobQueue.get(jobName).getJobProperties().getPreviousStatusList().size() - 1; i >= 0; i--){
//						statusList.add(jobQueue.get(jobName).getJobProperties().getPreviousStatusList().get(i));
//					}
					
					tlosInfo.setJobStatusHistory(statusList);
					
					tlosInfo.setExecutionDate(jobQueue.get(jobName).getAbstractJobType().getManagement().getTimeManagement().getJsRealTime().getStartTime().getTime());
					tlosInfo.setNextExecutionDate(jobQueue.get(jobName).getAbstractJobType().getManagement().getTimeManagement().getJsPlannedTime().getStartTime().getTime());
					tlosInfo.setErrCode(0);
				} else {
					tlosInfo.setErrCode(1);
					tlosInfo.setErrDesc(jobName + Pinara.getMessage("ServerSocketHandler.8"));
				}
				writeData(client, tlosInfo);
				cleanUp();
				
				break;
				
			case TrxInfo.DUMP_JOB_LIST:
				
				// JobQueueOperations.dumpJobQueue(jobQueue, true);
				tlosInfo.setErrCode(0);
				
				writeData(client, tlosInfo);
				cleanUp();

				break;

			default:
				writeData(client, tlosInfo);
				cleanUp();
				
				break;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PinaraAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void writeData(Socket client, TrxInfo tlosInfoTx) throws IOException {
		
		OutputStream os = client.getOutputStream();
		
		objectOutputStream = new ObjectOutputStream(os);
		
		objectOutputStream.writeObject(tlosInfoTx);
		
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	

	public TrxInfo readData(Socket client, ObjectInputStream ois) throws IOException, ClassNotFoundException {
		InputStream is = client.getInputStream();
		ois = new ObjectInputStream(is);
		TrxInfo tlosInfoRx = (TrxInfo) ois.readObject();
		return tlosInfoRx;
	}
	
	private void cleanUp() {
		
		try {
			if(objectOutputStream != null) objectOutputStream.close();
			if(objectInputStream != null) objectInputStream.close();
			if(client != null) client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
}
