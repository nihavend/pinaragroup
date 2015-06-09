package com.likya.pinara.gui.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.google.gson.Gson;
import com.likya.myra.commons.utils.IdFilter;
import com.likya.myra.commons.utils.SSSFilter;
import com.likya.myra.jef.core.ManagementOperationsImpl;
import com.likya.myra.jef.model.CoreStateInfo;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.model.PinaraXMLValidationException;
import com.likya.pinara.utils.xml.mappers.GroupTreeMapper;
import com.likya.pinara.utils.xml.mappers.JobDetailMapper;
import com.likya.pinara.utils.xml.mappers.JobGridListMapper;
import com.likya.pinara.utils.xml.mappers.NetTreeMapper;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class RestParser extends GenericRestParser {

	private static final String NETTREE_XML_CMD = "nettreexml";
	private static final String GROUPTREE_XML_CMD = "grouptreexml";
	private static final String JOBLIST_XML_CMD = "joblistxml";
	private static final String JOBSUMMARYLIST_XML_CMD = "jobsummarylistxml";
	private static final String JOBDETAIL_XML_CMD = "jobdetailxml";

	public static final String CMD_JOBLIST = "jobList";

	public static final String CMD_JOBSTOP = "stopjob";
	public static final String CMD_JOBSTART = "startjob";
	public static final String CMD_JOBSETSUCCESS = "setsuccessjob";
	public static final String CMD_JOBSKIP = "skipjob";
	public static final String CMD_JOBPAUSE = "pausejob";
	public static final String CMD_JOBRESUME = "resumejob";
	public static final String CMD_JOBRETRY = "retryjob";
	public static final String CMD_JOBENABLE = "enablejob";
	public static final String CMD_JOBDISABLE = "disablejob";

	public static final String CMD_APPSTOP = "stopapp";
	public static final String CMD_APPFSTOP = "fstopapp";
	public static final String CMD_APPSUSPEND = "suspendapp";
	public static final String CMD_APPRESUME = "resumeapp";

	public static final String CMD_JOBADD = "addjob";
	public static final String CMD_JOBUPDATE = "updatejob";
	public static final String CMD_JOBDELETE = "deletejob";

	public static final String CMD_AUTH = "authanticate";

	public static final String CMD_RECOVER = "recover";
	public static final String CMD_NORECOVER = "norecover";

	public static final String CMD_APPSTATE = "appstate";

	public static final String CMD_GRPENABLE = "enablegrp";
	public static final String CMD_GRPDISABLE = "disablegrp";
	
	public static byte[] parse(String uriTxt) {

		// String xmlPath = "/Users/serkan/programlar/dev/workspace/Pinara-0.0.1/xmls/";

		StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING };

		String retStr = "";

		String restCommArr[] = uriTxt.split("/");

		byte responseBytes[] = new byte[0];

		if (restCommArr.length == 0) {
			return responseBytes;
		}

		switch (restCommArr[0]) {

		case NETTREE_XML_CMD:
			CoreStateInfo coreStateInfo;
			try {
				System.out.print("Checking if application is ready...");
				while (PinaraAppManagerImpl.getExecutionState().equals(CoreStateInfo.STATE_STARTING)) {
					Thread.sleep(1000);
				}
			} catch (PinaraAuthenticationException | InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			System.out.println("OK !");
			// retStr = FileUtils.readFile(xmlPath + "netTree.xml").toString();
			try {
				// retStr = NetTreeMapper.getMapped(Pinara.getInstance().getConfigurationManager().getPinaraAppManager().getJobList(filterStates));
				retStr = NetTreeMapper.getMapped();
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case GROUPTREE_XML_CMD:
			try {
				retStr = GroupTreeMapper.getMapped();
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
			break;

		case JOBLIST_XML_CMD:

			if (restCommArr.length != 2) {
				retStr = "NetTree/Group id not defined or invalid : " + uriTxt;
				retStr = "<result>NOK : " + retStr + "</result>";
				//				responseBytes = retStr.getBytes();
				break;
			}
			
			if(isInteger(restCommArr[1])) {
				
				String netTreeId = restCommArr[1];
				
				try {

					if (netTreeId.equals("-1")) {
						retStr = JobGridListMapper.getMapped(JobQueueOperations.getJobList(PinaraAppManagerImpl.getInstance().getFreeJobs(), filterStates));
					} else if (PinaraAppManagerImpl.getInstance().getNetTreeMap().containsKey(netTreeId)) {
						retStr = JobGridListMapper.getMapped(JobQueueOperations.getJobList(PinaraAppManagerImpl.getInstance().getNetTreeMap().get(netTreeId).getMembers(), filterStates));
					} else {
						retStr = "<result>NOK : " + "NetTree id is not mapped to a nettree : " + netTreeId + "</result>";
					}

				} catch (PinaraAuthenticationException e1) {
					e1.printStackTrace();
				}
				
			} else {

				String groupId = restCommArr[1];
				if(GroupTreeMapper.resolveGroups().containsKey(groupId)) {
					retStr = JobGridListMapper.getMapped(JobQueueOperations.getJobList(GroupTreeMapper.resolveGroups().get(groupId), filterStates));
				} else {
					retStr = "<result>NOK : " + "Group id is not mapped to a group : " + groupId + "</result>";
				}
				
			}

			break;

		case JOBDETAIL_XML_CMD:

			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				// retStr = "Job id not defined or invalid : " + uriTxt;
				retStr = "<result>NOK : " + "Job id not defined or invalid : " + uriTxt + "</result>";
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

		case RestParser.JOBSUMMARYLIST_XML_CMD:

			Collection<AbstractJobType> jobSummaryList = null;
			try {
				// jobSummaryList = PinaraAppManagerImpl.getInstance().getJobList(filterStates);
				jobSummaryList = SSSFilter.noneFilter(JobQueueOperations.toAbstractJobTypeList(PinaraAppManagerImpl.getInstance().getJobQueue()).values(), StateName.PENDING, SubstateName.DEACTIVATED);
				if (restCommArr.length == 2) { // means that list should be filter against jobId
					String idArray[] = { restCommArr[1] };
					jobSummaryList = IdFilter.noneFilter(jobSummaryList, idArray);
				}
			} catch (PinaraAuthenticationException e) {
				e.printStackTrace();
			}

			retStr = "<joblist>";
			for (Object abstractJobType : jobSummaryList.toArray()) {
				retStr += "<job>";
				retStr += "<jobid>" + ((AbstractJobType) abstractJobType).getId() + "</jobid>";
				retStr += "<jobname>" + ((AbstractJobType) abstractJobType).getBaseJobInfos().getJsName() + "</jobname>";
				retStr += "</job>";
			}
			retStr += "</joblist>";

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

		case RestParser.CMD_JOBSTOP:
			try {
				PinaraAppManagerImpl.getInstance().stopJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBSTART:
			try {
				PinaraAppManagerImpl.getInstance().startJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBSETSUCCESS:
			try {
				PinaraAppManagerImpl.getInstance().setSuccess(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBSKIP:
			try {
				PinaraAppManagerImpl.getInstance().skipJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBPAUSE:
			try {
				PinaraAppManagerImpl.getInstance().pauseJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBRESUME:
			try {
				PinaraAppManagerImpl.getInstance().resumeJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBRETRY:
			try {
				PinaraAppManagerImpl.getInstance().retryExecution(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBENABLE:
			try {
				PinaraAppManagerImpl.getInstance().enableJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBDISABLE:
			try {
				PinaraAppManagerImpl.getInstance().disableJob(restCommArr[1]);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_APPSTOP:
			try {
				PinaraAppManagerImpl.getInstance().gracefulShutDown();
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_APPFSTOP:
			try {
				PinaraAppManagerImpl.getInstance().forceFullShutDown();
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_APPSUSPEND:
			try {
				PinaraAppManagerImpl.getInstance().suspendApp();
				coreStateInfo = PinaraAppManagerImpl.getExecutionState();
				if (coreStateInfo.equals(CoreStateInfo.STATE_SUSPENDED)) {
					retStr = "<result>OK</result>";
				} else {
					retStr = "<result>NOK</result>";
				}
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_APPRESUME:
			try {
				PinaraAppManagerImpl.getInstance().resumeApp();
				coreStateInfo = PinaraAppManagerImpl.getExecutionState();
				if (coreStateInfo.equals(CoreStateInfo.STATE_WORKING)) {
					retStr = "<result>OK</result>";
				} else {
					retStr = "<result>NOK</result>";
				}
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_RECOVER:
			if (Pinara.suspendFlag.equals("locked")) {
				Pinara.forceToRecover = true;
				synchronized (Pinara.suspendFlag) {
					Pinara.suspendFlag.notifyAll();
				}
			}
			retStr = "<result>OK</result>";
			break;

		case RestParser.CMD_NORECOVER:
			try {
				System.out.println(PinaraAppManagerImpl.getExecutionState());
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
			if (Pinara.suspendFlag.equals("locked")) {
				synchronized (Pinara.suspendFlag) {
					Pinara.suspendFlag.notifyAll();
				}
			}
			retStr = "<result>OK</result>";
			break;

		case RestParser.CMD_APPSTATE:
			retStr = "<result>" + ManagementOperationsImpl.getExecutionState() + "</result>";
			break;

		case RestParser.CMD_GRPENABLE:
			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				retStr = "Group id not defined or invalid : " + uriTxt;
				retStr = "<result>NOK : " + retStr + "</result>";
				break;
			}

			String grpId = restCommArr[1];

			try {
				PinaraAppManagerImpl.getInstance().enableGroup(grpId);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}

			// retStr = "<result>NOK : " + "Service not implemented !" + "</result>";
			break;

		case RestParser.CMD_GRPDISABLE:
			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				retStr = "Group id not defined or invalid : " + uriTxt;
				retStr = "<result>NOK : " + retStr + "</result>";
				break;
			}

			grpId = restCommArr[1];

			try {
				PinaraAppManagerImpl.getInstance().disableGroup(grpId);
				retStr = "<result>OK</result>";
			} catch (PinaraAuthenticationException e) {
				retStr = "<result>NOK : " + e.getLocalizedMessage() + "</result>";
				e.printStackTrace();
			}

			// retStr = "<result>NOK : " + "Service not implemented !" + "</result>";
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

		
		byte responseBytes[] = new byte[0];

		StringBuffer paramBuff = new StringBuffer();
		
		String switchId = getSwitchId(paramBuff, uriTxt, inputStream);
		
		if (switchId == null) {
			return responseBytes;
		}

		String bufferString = paramBuff.toString();
		
		String retStr = "";
		
		switch (switchId) {

		case RestParser.CMD_JOBADD:
			try {
				extractPostInfo(bufferString, (byte) 0x01);
				retStr = "<message><result>OK</result></message>";
			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
				e.printStackTrace();
			}
			//			responseBytes = retStr.getBytes();
			break;

		case RestParser.CMD_JOBUPDATE:
			try {
				extractPostInfo(bufferString, (byte) 0x02);
				retStr = "<message><result>OK</result></message>";
			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_JOBDELETE:
			try {
				extractPostInfo(bufferString, (byte) 0x03);
				retStr = "<message><result>OK</result></message>";
			} catch (PinaraAuthenticationException | PinaraXMLValidationException e) {
				retStr = "<message><result>NOK</result><desc>" + e.getLocalizedMessage() + "</desc></message>";
				e.printStackTrace();
			}
			break;

		case RestParser.CMD_AUTH:
			if (Pinara.suspendFlag.equals("locked")) {
				retStr = "<message><result>RECOVER_CONFIRM</result></message>";
			} else {
				retStr = "<message><result>OK</result></message>";
			}

			break;

		default:
			retStr = "<result>NOK : " + "Command not found : " + switchId + "</result>";

			break;
		}

		responseBytes = retStr.getBytes();

		return responseBytes;

	}

	private static void extractPostInfo(String bufferString, byte command) throws PinaraAuthenticationException, PinaraXMLValidationException {

		//<data><serialize></serialize><datamess>" + myraGenericJob + "</datamess></data>
		String myraGenericJob = bufferString.split("<datamess>")[1].split("</datamess>")[0];
		String serializeInfo = bufferString.split("<serialize>")[1].split("</serialize>")[0];

		switch (command) {

		case 0x01:
			PinaraAppManagerImpl.getInstance().addJob(myraGenericJob, Boolean.parseBoolean(serializeInfo));
			break;
		case 0x02:
			PinaraAppManagerImpl.getInstance().updateJob(myraGenericJob, Boolean.parseBoolean(serializeInfo));
			break;
		case 0x03:
			PinaraAppManagerImpl.getInstance().deleteJob(myraGenericJob, Boolean.parseBoolean(serializeInfo));
			break;
		default:
			try {
				throw new Exception("Undefined command value : " + command);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

	}
}
