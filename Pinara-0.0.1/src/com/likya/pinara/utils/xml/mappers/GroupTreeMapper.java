package com.likya.pinara.utils.xml.mappers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class GroupTreeMapper {
	
	public static String getMapped() throws PinaraAuthenticationException {
		
		String retValue = "";
		
		HashMap<String, ArrayList<String>> groupMap = resolveGroups();
		
		XmlObject xmlObject = XmlObject.Factory.newInstance();
		
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();
		
		xmlCursor.beginElement("list");
		xmlCursor.insertAttributeWithValue("title", "Grup Listesi");
		xmlCursor.insertAttributeWithValue("id", "0");
		
		for(String groupId : groupMap.keySet()) {
			
			xmlCursor = NetTreeMapper.addJobGroup(xmlCursor, groupId, groupId);
			
			for(String jobId : groupMap.get(groupId)) {
				xmlCursor = NetTreeMapper.addJobProperty(xmlCursor, jobId);
			}
			
			xmlCursor.toNextToken(); // exit from jobGroup
			
		}
		
		xmlCursor.toNextToken(); // exit from list
		
		if (xmlCursor != null) {
			xmlCursor.dispose();
		}
		
		try {
			 XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);
			 retValue = xmlObject.xmlText(saveOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	public static HashMap<String, ArrayList<String>> resolveGroups() {
		
		HashMap<String, ArrayList<String>> jobGroups = new HashMap<String, ArrayList<String>>();
	
		HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
		
		for(String jobId : jobQueue.keySet()) {
			
			AbstractJobType abstractJobType = jobQueue.get(jobId).getAbstractJobType();
			String tmpGrpId = abstractJobType.getGroupId();
			
			if(tmpGrpId == null || tmpGrpId.equals("")) {
				tmpGrpId = "No Group";
			}
			
			if(!jobGroups.containsKey(tmpGrpId)) {
				jobGroups.put(tmpGrpId, new ArrayList<String>());
			}
			jobGroups.get(tmpGrpId).add(jobId);
		}
		
		return jobGroups;
	}
}
