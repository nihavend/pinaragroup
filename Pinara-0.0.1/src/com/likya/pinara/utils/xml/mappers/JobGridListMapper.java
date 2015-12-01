package com.likya.pinara.utils.xml.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.commons.utils.DependencyOperations;
import com.likya.myra.jef.core.Commandability;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.utils.PinaraFileUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;

public class JobGridListMapper {

	public static String getMapped(String netTreeId, Collection<AbstractJobType> abstractJobTypes) {

		String retValue = ""; //"<list title=\"ana liste\" id=\"0\">";

		JobListDocument jobListDocument = JobListDocument.Factory.newInstance();

		JobList jobList = jobListDocument.addNewJobList();
		
		addNetTreeParams(netTreeId, jobList.newCursor());
		
		for (AbstractJobType abstractJobType : abstractJobTypes) {

			XmlObject xmlObject = abstractJobType.copy();
			
			XmlCursor xmlCursor = xmlObject.newCursor();

			xmlCursor.toEndToken();

			addRuntimeParams(xmlCursor, abstractJobType);
			addVisualParams(xmlCursor, abstractJobType);
			
			if (xmlCursor != null) {
				xmlCursor.dispose();
			}
			jobList.addNewGenericJob().set(xmlObject);
		}

		try {
			XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);
			retValue = removeNameSpaces(jobListDocument.xmlText(saveOptions));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	protected static void addRuntimeParams(XmlCursor xmlCursor, AbstractJobType abstractJobType) {

		HashMap<String, JobImpl> jobQueue;
		JobImpl jobImpl = null;
		
		try {
			jobQueue = PinaraAppManagerImpl.getInstance().getJobQueue(); 
			jobImpl = jobQueue.get(abstractJobType.getId());
		} catch (PinaraAuthenticationException e) {
			e.printStackTrace();
			return;
		}
		
		xmlCursor.beginElement("runtimeParams");
		xmlCursor.beginElement("realizedDuration");
		xmlCursor.insertChars(jobImpl.getJobRuntimeProperties().getWorkDuration());
		xmlCursor.toNextToken(); // exit from realizedDuration
		
		xmlCursor.beginElement("previousDuration");
		xmlCursor.insertChars(jobImpl.getJobRuntimeProperties().getRecentWorkDuration());
		xmlCursor.toNextToken(); // exit from previousDuration

		HashMap<String, AbstractJobType> abstractJobTypeList = JobQueueOperations.toAbstractJobTypeList(jobQueue);
		ArrayList<AbstractJobType> dependencyList = DependencyOperations.getDependencyList(abstractJobTypeList, abstractJobType.getId());
		if (dependencyList != null && dependencyList.size() > 0) {
			xmlCursor.beginElement("waitingMeJobs");
			for (AbstractJobType abstractJobType2 : dependencyList) {
				xmlCursor.beginElement("jobId");
				xmlCursor.insertChars(abstractJobType2.getId());
				xmlCursor.toNextToken();
			}
			xmlCursor.toNextToken();
		}
		
		xmlCursor.beginElement("hasJobLog");
		xmlCursor.insertChars("" + PinaraFileUtils.checkLogFile(abstractJobType));
		xmlCursor.toNextToken(); // exit from hasJobLog
		
		xmlCursor.beginElement("hasJobContent");
		xmlCursor.insertChars("" + PinaraFileUtils.checkJobFile(abstractJobType));
		xmlCursor.toNextToken(); // exit from hasJobContent
		
		xmlCursor.toNextToken(); // exit from runtimeParams
		
	}
	
	protected static void addNetTreeParams(String netTreeId, XmlCursor xmlCursorNetTree) {
	
		boolean isNetTreeEnablable = false;
		boolean isNetTreeDisablable = false;
		
		if(netTreeId != null && !netTreeId.equals("-1")) {
			isNetTreeEnablable = Commandability.isNetTreeEnablable(netTreeId);
			isNetTreeDisablable = Commandability.isNetTreeDisablable(netTreeId);
		}
		
		xmlCursorNetTree.toEndToken();
		xmlCursorNetTree.insertAttributeWithValue("netTreeEnablable", isNetTreeEnablable + "");
		xmlCursorNetTree.insertAttributeWithValue("netTreeDisablable", isNetTreeDisablable + "");
		
	}
	
	protected static void addVisualParams(XmlCursor xmlCursor, AbstractJobType abstractJobType) {
		
		// We could use the convenient xobj.selectPath() or cur.selectPath()
		// to position the cursor on the <person> element, but let's use the
		// cursor's toChild() instead.
		// xmlCursor.toChild("genericJob");
		//cur.toChild("person");
		// Move to </person> end element.
		// Move past the </work> end element
		// cur.toNextToken();
		// Or insert a new element the easy way in one step...
		// cur.insertElementWithText("home", "555-555-5555");

		xmlCursor.beginElement("visualParams");
		
		xmlCursor.beginElement("statu");
		int statu = LSIToStatusMapper.getMapped(abstractJobType);
		xmlCursor.insertChars("" + statu);
		xmlCursor.toNextToken();
		
		xmlCursor.beginElement("commandabilityParams");

		xmlCursor.beginElement("isPausable");
		xmlCursor.insertChars("" + Commandability.isPausable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isResumable");
		xmlCursor.insertChars("" + Commandability.isResumable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isRetryable");
		xmlCursor.insertChars("" + Commandability.isRetryable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isSkipable");
		xmlCursor.insertChars("" + Commandability.isSkipable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isStartable");
		xmlCursor.insertChars("" + Commandability.isStartable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isStopable");
		xmlCursor.insertChars("" + Commandability.isStopable(abstractJobType));
		xmlCursor.toNextToken();
		
		
		xmlCursor.beginElement("isSuccessable");
		xmlCursor.insertChars("" + Commandability.isSuccessable(abstractJobType));
		xmlCursor.toNextToken();
		
		xmlCursor.beginElement("isDisablable");
		xmlCursor.insertChars("" + Commandability.isDisablableForFree(abstractJobType));
		xmlCursor.toNextToken();
		
		xmlCursor.beginElement("isEnablable");
		xmlCursor.insertChars("" + Commandability.isEnablableForFree(abstractJobType));
		xmlCursor.toNextToken();
	}

	protected static String removeNameSpaces(String inXmlTxt) throws Exception {

		String lastRetString = inXmlTxt; // jobListDocument.xmlText();

		lastRetString = lastRetString.replace("xmlns:myra=\"http://www.likyateknoloji.com/myra-joblist\"", "");
		lastRetString = lastRetString.replace("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
		lastRetString = lastRetString.replace("xmlns:myra-jobprops=\"http://www.likyateknoloji.com/myra-jobprops\"", "");
		lastRetString = lastRetString.replace("xmlns:wla=\"http://www.likyateknoloji.com/wla-gen\"", "");
		lastRetString = lastRetString.replace("xmlns:lik=\"http://www.likyateknoloji.com/likya-gen\"", "");
		lastRetString = lastRetString.replace("xmlns:myra-stateinfo=\"http://www.likyateknoloji.com/myra-stateinfo\"", "");
		lastRetString = lastRetString.replace("xmlns:rs=\"http://www.likyateknoloji.com/rs\"", "");
				
		lastRetString = lastRetString.replace("xsi:type=\"myra:simpleProperties\"", "");
		
		lastRetString = lastRetString.replace("xsi:type=\"myra:remoteSchProperties\"", "");

		lastRetString = lastRetString.replace("<myra:", "<");
		lastRetString = lastRetString.replace("</myra:", "</");

		lastRetString = lastRetString.replace("<myra-jobprops:", "<");
		lastRetString = lastRetString.replace("</myra-jobprops:", "</");

		lastRetString = lastRetString.replace("<wla:", "<");
		lastRetString = lastRetString.replace("</wla:", "</");

		lastRetString = lastRetString.replace("<lik:", "<");
		lastRetString = lastRetString.replace("</lik:", "</");

		lastRetString = lastRetString.replace("<myra-stateinfo:", "<");
		lastRetString = lastRetString.replace("</myra-stateinfo:", "</");

		lastRetString = lastRetString.replace("<rs:", "<");
		lastRetString = lastRetString.replace("</rs:", "</");
		return lastRetString;
	}
	
}
