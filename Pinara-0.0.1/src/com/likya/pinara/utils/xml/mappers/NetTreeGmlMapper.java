package com.likya.pinara.utils.xml.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class NetTreeGmlMapper {
	
	private static XmlCursor addNode(XmlCursor xmlCursor, String id, String name, String nodeLabel, String color) {
		
		xmlCursor.beginElement("Node");
		xmlCursor.insertAttributeWithValue("id", id);
		xmlCursor.insertAttributeWithValue("name", name);
		// xmlCursor.insertAttributeWithValue("nodeLabel", nodeLabel);
		xmlCursor.insertAttributeWithValue("desc", nodeLabel);
		xmlCursor.insertAttributeWithValue("nodeColor", color);
		xmlCursor.insertAttributeWithValue("nodeSize", "3");
		xmlCursor.insertAttributeWithValue("nodeClass", "leaf");
		xmlCursor.insertAttributeWithValue("nodeIcon", "5");
		xmlCursor.insertAttributeWithValue("x", "10");
		xmlCursor.insertAttributeWithValue("y", "10");
		
		xmlCursor.toNextToken(); // exit from Node
		
		return xmlCursor;
	}
	
	private static XmlCursor addEdge(XmlCursor xmlCursor, String fromId, String toId, String color) {
		
		xmlCursor.beginElement("Edge");
		xmlCursor.insertAttributeWithValue("fromID", fromId);
		xmlCursor.insertAttributeWithValue("toID", toId);
		xmlCursor.insertAttributeWithValue("color", color);
		xmlCursor.insertAttributeWithValue("flow", "50");
		
		xmlCursor.toNextToken(); // exit from Node
		
		return xmlCursor;
	}
	
	private static XmlCursor addMeta(XmlCursor xmlCursor, String rootNodeId) {
		
		xmlCursor.toStartDoc(); // To the start of document
		xmlCursor.toNextToken(); // To graphinfo part
		xmlCursor.toNextToken(); // In To graphinfo part
		
		xmlCursor.beginElement("Meta");
		xmlCursor.beginElement("rootnodeid");
		xmlCursor.insertChars(rootNodeId);
		xmlCursor.toNextToken(); // exit from Meta
		
		xmlCursor.toNextToken(); // to graph token
		xmlCursor.toNextToken(); // In to graph token
		
		return xmlCursor;
	}
	
	private static String resolveColor(AbstractJobType abstractJobType) {

		LiveStateInfo liveStateInfo = abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);

		if (LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.COMPLETED, StatusName.SUCCESS)) {
			return "0x088A08";
		} else if (LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.COMPLETED, StatusName.FAILED)) {
			return "0xFF0040";
		} else if (LiveStateInfoUtils.equalStates(liveStateInfo, StateName.RUNNING)) {
			return "0x0101DF";
		} else {
			return "0xD8D8D8";
		}

		/*
		if (myFlexJob.getState() == Job.READY || myFlexJob.getState() == Job.WAITING || myFlexJob.getState() == Job.PAUSE) {
			nodeIcon = "mavi";
			colorIndicator = "0xD8D8D8";
		} else if (myFlexJob.getState() == Job.WORKING) {
			nodeIcon = "yesil2";
			colorIndicator = "0x0101DF";
		} else if (myFlexJob.getState() == Job.FAIL) {
			nodeIcon = "kirmizi";
			colorIndicator = "0xFF0040";
		} else if (myFlexJob.getState() == Job.SUCCESS) {
			nodeIcon = "yesil";
			colorIndicator = "0x088A08";
		}
		*/

	}
	
	public static String getMapped(Collection<AbstractJobType> abstractJobTypes) {
		
		final String rootNodeId = "Vid:0";
		
		String retValue = "";
		
		XmlObject xmlObject = XmlObject.Factory.newInstance();
		
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();
		
		xmlCursor.beginElement("graphInfo");
		
		xmlCursor.beginElement("Graph");

		ArrayList<AbstractJobType> rootNodes = new ArrayList<AbstractJobType>(); 
		
		for(AbstractJobType abstractJobType : abstractJobTypes) {
			if(abstractJobType.getDependencyList() == null || abstractJobType.getDependencyList().sizeOfItemArray() == 0) {
				rootNodes.add(abstractJobType);
			} else {
				xmlCursor = addNode(xmlCursor, abstractJobType.getId(), abstractJobType.getBaseJobInfos().getJsName(), abstractJobType.getBaseJobInfos().getJsName(), resolveColor(abstractJobType));
				for(Item depItem : abstractJobType.getDependencyList().getItemArray()) {
					xmlCursor = addEdge(xmlCursor, depItem.getJsId(), abstractJobType.getId(), resolveColor(abstractJobType));
				}
			}
		}
		
		
		
		if(rootNodes.size() == 1) {
			addMeta(xmlCursor, rootNodes.get(0).getId());
			xmlCursor = addNode(xmlCursor, rootNodes.get(0).getId(), "Root Node" + rootNodes.get(0).getBaseJobInfos().getJsName(), rootNodes.get(0).getBaseJobInfos().getJsName(), resolveColor(rootNodes.get(0)));
		} else {
			addMeta(xmlCursor, rootNodeId);
			
			xmlCursor = addNode(xmlCursor, rootNodeId, "Virtual Root Node", "Virtual Root Node", resolveColor(rootNodes.get(0)));
			
			for(AbstractJobType tmpAbstractJobType : rootNodes) {
				xmlCursor = addNode(xmlCursor, tmpAbstractJobType.getId(), tmpAbstractJobType.getBaseJobInfos().getJsName(), tmpAbstractJobType.getBaseJobInfos().getJsName(), resolveColor(tmpAbstractJobType));
				xmlCursor = addEdge(xmlCursor, rootNodeId, tmpAbstractJobType.getId(), resolveColor(tmpAbstractJobType));
			}
			
		}
		
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
		
		
		
		
		/**
		String retValue = "<list title=\"Grup Listesi\" id=\"0\">";
		
		retValue += "<jobGroup title=\"" + ((AbstractJobType)abstractJobTypes.toArray()[0]).getGroupId() + "\" id=\"" +  "22" + "\">";
		
		for(AbstractJobType abstractJobType : abstractJobTypes) {
			
			String jsId = abstractJobType.getId();
			String jsName = abstractJobType.getBaseJobInfos().getJsName();
			
			int statu = LSIToStatusMapper.getMapped(abstractJobType);
			
			retValue += "<jobProperty title=\"" + jsName + "\" id=\"" + jsId + "\" statu=\"" + statu + "\" port=\"1\" />";
		}
		
		
		retValue += "</jobGroup>";
		retValue += "</list>";
		
		return retValue;
		**/
	}

	/**
	private function convertGML():XML {
		graphML = "<Graph>\n";
		
		var rootKey:String = "Job_List";
		
		graphML += "<Node id=\"" + rootKey + "\" name=\"" + rootKey + "\" nodeLabel=\"" + "label" + "\" nodeColor=\"" + "0xFFFFFF" + "\" nodeSize=\"" + "200" + "\" nodeClass=\"" + "" + "\" nodeIcon=\"" + "gri" + "\" />\n";
		
		var edgesML:String = "";
		
		var colorIndicator:String = "0xFFFFFF"; // 0x8F8FFF;
		var nodeIcon:String = "gri";
		
		var job:Job;
		
		for each (var myFlexJob:Job in jobs) {
			if (myFlexJob.getState() == Job.READY || myFlexJob.getState() == Job.WAITING || myFlexJob.getState() == Job.PAUSE) {
				nodeIcon = "mavi";
				colorIndicator = "0xD8D8D8";
			} else if (myFlexJob.getState() == Job.WORKING) {
				nodeIcon = "yesil2";
				colorIndicator = "0x0101DF";
			} else if (myFlexJob.getState() == Job.FAIL) {
				nodeIcon = "kirmizi";
				colorIndicator = "0xFF0040";
			} else if (myFlexJob.getState() == Job.SUCCESS) {
				nodeIcon = "yesil";
				colorIndicator = "0x088A08";
			}
			
			if (myFlexJob.isStandart()) {
				var dependencyList:ArrayCollection = myFlexJob.getJobDependencyArrayList();
				
				for each (var currentToken:String in dependencyList) {
					if (currentToken == "yok") {
						edgesML += "<Edge fromID=\"" + rootKey + "\" toID=\"" + myFlexJob.getKey() + "\" color=\"" + colorIndicator + "\" flow=\"50\"  />\n";
					} else {
						var tmp:String = currentToken;
						edgesML += "<Edge fromID=\"" + StringUtil.trim(tmp) + "\" toID=\"" + myFlexJob.getKey() + "\" color=\"" + colorIndicator + "\" flow=\"50\"  />\n";
					}
				}
			} else {
				edgesML += "<Edge fromID=\"" + rootKey + "\" toID=\"" + myFlexJob.getKey() + "\" color=\"0x8F8FFF\" flow=\"50\"  />\n";
			}
			
			graphML += "<Node id=\"" + myFlexJob.getKey() + "\" name=\"" + myFlexJob.getKey() + "\" desc=\"" + myFlexJob.getKey() + "\" nodeColor=\"" + "" + "\" nodeSize=\""
				+ "32" + "\" nodeClass=\"" + "earth" + "\" nodeIcon=\"" + nodeIcon + "\" />\n";
		}
		
		graphML += edgesML;
		
		graphML += "</Graph>";
		
		return XML(graphML);
	}
	**/
}
