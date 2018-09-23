package com.likya.pinara.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.ManagementOperationsImpl;
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
			
			TrxInfo pnrInfo = readData(client, objectInputStream);		
			
			int commandTypeValue = pnrInfo.getCommandType().getId();
			logger.info(Pinara.getMessage("ServerSocketHandler.0") + pnrInfo.getCommandType().getDescription());
			logger.info(Pinara.getMessage("ServerSocketHandler.1") + pnrInfo.getClientId());
			
			switch (commandTypeValue) {
			
			case TrxInfo.NORMAL_TERMINATE:
				logger.info(Pinara.getMessage("ServerSocketHandler.2"));
				
				pnrInfo.setErrCode(0);
				writeData(client, pnrInfo);
				cleanUp();
				
				pinaraManager.gracefulShutDown();
//				loopPermit = false;
				
				break;
			
			case TrxInfo.FORCED_TERMINATE:
				logger.info(Pinara.getMessage("ServerSocketHandler.3"));
				
				pnrInfo.setErrCode(0);
				writeData(client, pnrInfo);
				cleanUp();
				
				pinaraManager.forceFullShutDown();
//				loopPermit = false;
				
				break;

			case TrxInfo.RESUME_JOB:
				
				String jobName = pnrInfo.getJobId();
				
				// JobImpl tmpJob = jobQueue.get(jobName);
				boolean isResumable = true;
				
				// if(jobQueue.containsKey(jobName) && ((jobQueue.get(jobName).getJobProperties().getStatus() == JobProperties.FAIL) || (TlosServer.getJobQueue().get(jobName).getJobProperties().getStatus() == JobProperties.STOP))) {
				if(isResumable) {
					logger.info(Pinara.getMessage("ServerSocketHandler.4") + jobName + " READY !"); //$NON-NLS-2$
					pinaraManager.retryExecution(jobName);
				} else {
					logger.info(Pinara.getMessage("ServerSocketHandler.6") + jobName);
					pnrInfo.setErrCode(1);
				}
				pnrInfo.setErrCode(0);
				
				writeData(client, pnrInfo);
				cleanUp();
				
				break;
			
			case TrxInfo.TLOS_STATUS:					
				
				boolean isThreshold = CoreFactory.getInstance().getMonitoringOperations().isThresholdOverflow();
				
				if (isThreshold) {
					pnrInfo.setTlosStatus(TrxInfo.STATE_JOBOVERFLOW);
					pnrInfo.setErrCode(0);
				} else if (ManagementOperationsImpl.getExecutionState() == CoreStateInfo.STATE_SUSPENDED) {
					pnrInfo.setTlosStatus(TrxInfo.STATE_SUSPENDED);
					pnrInfo.setErrCode(0);
				} else {
					pnrInfo.setErrCode(1);
					pnrInfo.setErrDesc(Pinara.getMessage("ServerSocketHandler.7"));
				}
				writeData(client, pnrInfo);
				cleanUp();
				
				break;
			
			case TrxInfo.JOB_STATUSLIST:

				jobName = pnrInfo.getJobId();
				if(jobQueue.containsKey(jobName)) {
					ArrayList<Integer> statusList = new ArrayList<Integer>();
//					statusList.add(jobQueue.get(jobName).getJobProperties().getStatus());
//					
//					for (int i = jobQueue.get(jobName).getJobProperties().getPreviousStatusList().size() - 1; i >= 0; i--){
//						statusList.add(jobQueue.get(jobName).getJobProperties().getPreviousStatusList().get(i));
//					}
					
					pnrInfo.setJobStatusHistory(statusList);
					
					pnrInfo.setExecutionDate(jobQueue.get(jobName).getAbstractJobType().getManagement().getTimeManagement().getJsRecordedTime().getStartTime().getTime());
					pnrInfo.setNextExecutionDate(jobQueue.get(jobName).getAbstractJobType().getManagement().getTimeManagement().getJsActualTime().getStartTime().getTime());
					pnrInfo.setErrCode(0);
				} else {
					pnrInfo.setErrCode(1);
					pnrInfo.setErrDesc(jobName + Pinara.getMessage("ServerSocketHandler.8"));
				}
				writeData(client, pnrInfo);
				cleanUp();
				
				break;
				
			case TrxInfo.DUMP_JOB_LIST:
				
				// JobQueueOperations.dumpJobQueue(jobQueue, true);
				pnrInfo.setErrCode(0);
				
				writeData(client, pnrInfo);
				cleanUp();

				break;
				
			case TrxInfo.CHANGE_CLOCK:
				
				TemporalUnit temporalUnit = pnrInfo.getTemporalUnit();
				long amountToAdd = pnrInfo.getAmountToAdd();
				
				if(amountToAdd != 0) {
					Pinara.adjustPnrClock(amountToAdd, temporalUnit);
				}

				pnrInfo.setErrCode(0);
				
				writeData(client, pnrInfo);
				cleanUp();
				
				break;
			
			default:
				writeData(client, pnrInfo);
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
