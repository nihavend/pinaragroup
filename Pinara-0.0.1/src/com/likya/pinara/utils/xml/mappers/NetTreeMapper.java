package com.likya.pinara.utils.xml.mappers;

import java.util.Collection;
import java.util.HashMap;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class NetTreeMapper {
	
	public static XmlCursor addJobGroup(XmlCursor xmlCursor, String groupId, String title) {
		
		xmlCursor.beginElement("jobGroup");
		xmlCursor.insertAttributeWithValue("title", title);
		xmlCursor.insertAttributeWithValue("id", groupId);
		
		return xmlCursor;
	}
	
	public static XmlCursor addJobProperty(XmlCursor xmlCursor, String jobId) {
		
		HashMap<String, JobImpl> jobQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
		AbstractJobType abstractJobType = jobQueue.get(jobId).getAbstractJobType();
		
		xmlCursor.beginElement("jobProperty");
		xmlCursor.insertAttributeWithValue("title", abstractJobType.getBaseJobInfos().getJsName());
		xmlCursor.insertAttributeWithValue("id", abstractJobType.getId());
		
		int statu = LSIToStatusMapper.getMapped(abstractJobType);
		
		xmlCursor.insertAttributeWithValue("statu", "" + statu);
		xmlCursor.insertAttributeWithValue("port", "1");
		
		xmlCursor.toNextToken(); // exit from jobProperty
		
		return xmlCursor;
	}
	
	public static String getMapped() throws PinaraAuthenticationException, InstanceNotFoundException {
		
		String retValue = "";
		
		HashMap<String, NetTree> netTreeMap = PinaraAppManagerImpl.getInstance().getNetTreeMap();
		HashMap<String, String> freeJobs = PinaraAppManagerImpl.getInstance().getFreeJobs();
		
		XmlObject xmlObject = XmlObject.Factory.newInstance();
		
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();
		
		xmlCursor.beginElement("list");
		xmlCursor.insertAttributeWithValue("title", "Grup Listesi");
		xmlCursor.insertAttributeWithValue("id", "0");
		
		xmlCursor = addJobGroup(xmlCursor, "-1", "Independents");
		// add free jobs to list
		
		for(String jobId : freeJobs.values()) {
			xmlCursor = addJobProperty(xmlCursor, jobId);
		}
		
		xmlCursor.toNextToken(); // exit from jobGroup
		
		for(NetTree netTree : netTreeMap.values()) {
			
			xmlCursor = addJobGroup(xmlCursor, netTree.getVirtualId(), "Dep Group");
			
			for(String jobId : netTree.getMembers()) {
				xmlCursor = addJobProperty(xmlCursor, jobId);
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
	
	public static String getMappedOld(Collection<AbstractJobType> abstractJobTypes) {
	
		String retValue = "<list title=\"Grup Listesi\" id=\"0\">\n";
		
		retValue += "\t<jobGroup title=\"" + ((AbstractJobType)abstractJobTypes.toArray()[0]).getGroupId() + "\" id=\"" +  "22" + "\">\n";
		
		for(AbstractJobType abstractJobType : abstractJobTypes) {
			
			String jsId = abstractJobType.getId();
			String jsName = abstractJobType.getBaseJobInfos().getJsName();
			
			int statu = LSIToStatusMapper.getMapped(abstractJobType);
			
			retValue += "\t\t<jobProperty title=\"" + jsName + "\" id=\"" + jsId + "\" statu=\"" + statu + "\" port=\"1\" />\n";
		}
		
		
		retValue += "\t</jobGroup>\n";
		retValue += "</list>\n";
		
		
		return retValue;
	}
}
/*

<jobGroup title="group_1" id="1">
	<jobProperty title="Dosyayı Yükle" id="1" ip="1" port="1" />
	<jobProperty title="Diske yaz" id="2" ip="1" port="1" />
	<jobGroup title="group_2" id="21">
		<jobGroup title="group_2" id="1">
			<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
			<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
			<jobGroup title="group_2" id="22">
				<jobProperty title="ETL_LOAD_SUBSCDATA" id="1" ip="1" port="1" />
				<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
			</jobGroup>
		</jobGroup>
		<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
		<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
	</jobGroup>
</jobGroup>
*/