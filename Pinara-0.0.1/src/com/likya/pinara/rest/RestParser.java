package com.likya.pinara.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.google.gson.Gson;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.utils.xml.mappers.JobDetailMapper;
import com.likya.pinara.utils.xml.mappers.JobGridListMapper;
import com.likya.pinara.utils.xml.mappers.NetTreeMapper;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class RestParser extends GenericRestParser {

	private static final String NETTREE_XML_CMD = "nettreexml";
	private static final String JOBLIST_XML_CMD = "joblistxml";
	private static final String JOBDETAIL_XML_CMD = "jobdetailxml";

	public static final String CMD_JOBLIST = "jobList";
	
	public static final String CMD_STOPJOB = "stopjob";
	public static final String CMD_STARTJOB = "startjob";
	public static final String CMD_SETSUCCESSJOB = "setsuccessjob";
	public static final String CMD_SKIPJOB = "skipjob";
	public static final String CMD_PAUSEJOB = "pausejob";
	public static final String CMD_RESUMEJOB = "resumejob";
	public static final String CMD_RETRYJOB = "retryjob";
	public static final String CMD_ENABLEJOB = "enablejob";
	public static final String CMD_DISABLEJOB = "disablejob";

	public static final String CMD_STOPAPP = "stopapp";
	public static final String CMD_FSTOPAPP = "fstopapp";
	public static final String CMD_SUSPENDAPP = "suspendapp";
	public static final String CMD_RESUMEAPP = "resumeapp";
	
	public static final String CMD_ADDJOB = "addjob";
	
	public static byte[] parse(String uriTxt) {

		// String xmlPath = "/Users/serkan/programlar/dev/workspace/Pinara-0.0.1/xmls/";

		StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING};
		
		String retStr = "";

		String restCommArr[] = uriTxt.split("/");

		byte responseBytes[] = new byte[0];

		if (restCommArr.length == 0) {
			return responseBytes;
		}

		switch (restCommArr[0]) {

		case NETTREE_XML_CMD:
			// retStr = FileUtils.readFile(xmlPath + "netTree.xml").toString();
			try {
				// retStr = NetTreeMapper.getMapped(Pinara.getInstance().getConfigurationManager().getPinaraAppManager().getJobList(filterStates));
				retStr = NetTreeMapper.getMapped();
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;

		case JOBLIST_XML_CMD:

			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				retStr = "NetTree id not defined or invalid : " + uriTxt;
//				responseBytes = retStr.getBytes();
				break;
			}

			String netTreeId = restCommArr[1];
			// System.err.println("Job Id : " + netTreeId);
			//retStr = FileUtils.readFile(xmlPath + "cleanList.xml").toString();
			
			try {
				
				if(netTreeId.equals("-1")) {
					retStr = JobGridListMapper.getMapped(JobQueueOperations.getJobList(PinaraAppManagerImpl.getInstance().getFreeJobs(), filterStates));					
				} else if(PinaraAppManagerImpl.getInstance().getNetTreeMap().containsKey(netTreeId)) {
					retStr = JobGridListMapper.getMapped(JobQueueOperations.getJobList(PinaraAppManagerImpl.getInstance().getNetTreeMap().get(netTreeId).getMembers(), filterStates));
				} else {
					retStr = "NetTree id is not mapped to a nettree : " + netTreeId;
				}
				
				// retStr = NetTreeMapper.getMapped(Pinara.getInstance().getConfigurationManager().getPinaraAppManager().getJobList(filterStates));
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;

		case JOBDETAIL_XML_CMD:

			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				retStr = "Job id not defined or invalid : " + uriTxt;
//				responseBytes = retStr.getBytes();
				break;
			}

			int jobId = Integer.parseInt(restCommArr[1]);
			// System.err.println("Job Id : " + jobId);
			// retStr = FileUtils.readFile(xmlPath + "jobDetail.xml").toString();

			try {
				retStr = JobDetailMapper.getMapped(PinaraAppManagerImpl.getInstance().getJob("" + jobId).getAbstractJobType());
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBLIST:

			Collection<AbstractJobType> jobList = null;
			try {
				jobList = PinaraAppManagerImpl.getInstance().getJobList(filterStates);
			} catch (PinaraAuthenticationException e) {
				e.printStackTrace();
			}

			retStr = new Gson().toJson(JobQueueOperations.getKeyList(jobList));
//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_STOPJOB:
			try {
				PinaraAppManagerImpl.getInstance().stopJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_STARTJOB:
			try {
				PinaraAppManagerImpl.getInstance().startJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_SETSUCCESSJOB:
			try {
				PinaraAppManagerImpl.getInstance().setSuccess(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_SKIPJOB:
			try {
				PinaraAppManagerImpl.getInstance().skipJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_PAUSEJOB:
			try {
				PinaraAppManagerImpl.getInstance().pauseJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_RESUMEJOB:
			try {
				PinaraAppManagerImpl.getInstance().resumeJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_RETRYJOB:
			try {
				PinaraAppManagerImpl.getInstance().retryExecution(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_ENABLEJOB:
			try {
				PinaraAppManagerImpl.getInstance().enableJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;


		case RestParser.CMD_DISABLEJOB:
			try {
				PinaraAppManagerImpl.getInstance().disableJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		case RestParser.CMD_STOPAPP:
			try {
				PinaraAppManagerImpl.getInstance().gracefulShutDown();
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;
			
		case RestParser.CMD_FSTOPAPP:
			try {
				PinaraAppManagerImpl.getInstance().forceFullShutDown();
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_SUSPENDAPP:
			try {
				PinaraAppManagerImpl.getInstance().suspendApp();
				CoreStateInfo coreStateInfo = PinaraAppManagerImpl.getInstance().getExecutionState();
				if(coreStateInfo.equals(CoreStateInfo.STATE_SUSPENDED)) {
					retStr = "<result>OK</result>";
				} else {
					retStr = "<result>NOK</result>";
				}
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_RESUMEAPP:
			try {
				PinaraAppManagerImpl.getInstance().resumeApp();
				CoreStateInfo coreStateInfo = PinaraAppManagerImpl.getInstance().getExecutionState();
				if(coreStateInfo.equals(CoreStateInfo.STATE_WORKING)) {
					retStr = "<result>OK</result>";
				} else {
					retStr = "<result>NOK</result>";
				}
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;
			
		default:
			retStr = "<result>NOK : " + "Command not found : " + restCommArr[0] + "</result>"; 
			
			break;
		}

		Pinara.getLogger().debug(uriTxt + " command received and response forwarded !");
		// System.err.println(uriTxt + " command received and response forwarded !");

		responseBytes = retStr.getBytes();
		
		return responseBytes;

	}
	
	
	public static byte[] parsePost(String uriTxt, InputStream inputStream) throws IOException {
		
		String bufferString = "";
		int i;
		while ((i = inputStream.read()) != -1) {
			bufferString = bufferString + (char) i;
		}

		String retStr = "";

		String restCommArr[] = uriTxt.split("/");

		byte responseBytes[] = new byte[0];

		if (restCommArr.length == 0) {
			return responseBytes;
		}

		switch (restCommArr[0]) {
		
		case RestParser.CMD_ADDJOB:
			try {
				PinaraAppManagerImpl.getInstance().addJob(bufferString, false);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
//			responseBytes = retStr.getBytes();
			break;
			
		default:
			retStr = "<result>NOK : " + "Command not found : " + restCommArr[0] + "</result>"; 
			
			break;
		}
		
		responseBytes = retStr.getBytes();
		
		return responseBytes;
		
	}
}

