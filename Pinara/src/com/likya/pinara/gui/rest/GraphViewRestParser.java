package com.likya.pinara.gui.rest;

import com.likya.myra.jef.model.InstanceNotFoundException;
import com.likya.myra.jef.utils.JobQueueOperations;
import com.likya.pinara.Pinara;
import com.likya.pinara.mng.PinaraAppManagerImpl;
import com.likya.pinara.model.PinaraAuthenticationException;
import com.likya.pinara.utils.xml.mappers.NetTreeGmlMapper;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class GraphViewRestParser extends GenericRestParser {

	private static final String NETTREE_GML_CMD = "nettreegml";
	
	public static byte[] parse(String uriTxt) throws InstanceNotFoundException {

		StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING};
		
		// String graph = "/Users/serkan/programlar/dev/workspace/Pinara-0.0.1/xmls/graph.xml";
		// String levelgraph = "/Users/serkan/programlar/dev/workspace/Pinara-0.0.1/xmls/levelgraph.xml";
		
		String retStr = "";

		String restCommArr[] = uriTxt.split("/");

		byte responseBytes[] = new byte[0];

		if (restCommArr.length == 0) {
			return responseBytes;
		}

		switch (restCommArr[0]) {

		case NETTREE_GML_CMD:
			
			// retStr = FileUtils.readFile(graph).toString();
			
			if (restCommArr.length != 2 || !isInteger(restCommArr[1])) {
				retStr = "NetTree id not defined or invalid : " + uriTxt;
//				responseBytes = retStr.getBytes();
				break;
			}

			String netTreeId = restCommArr[1];
			// System.err.println("Job Id : " + netTreeId);
			
			try {
				
				if(PinaraAppManagerImpl.getInstance().getNetTreeMap().containsKey(netTreeId)) {
					retStr = NetTreeGmlMapper.getMapped(JobQueueOperations.getJobList(PinaraAppManagerImpl.getInstance().getNetTreeMap().get(netTreeId).getMembers(), filterStates));
				} else {
					retStr = "<message><result>NOK</result><desc>" + "NetTree id is not mapped to a nettree : " + netTreeId + "</desc></message>"; 
				}
				
			} catch (PinaraAuthenticationException e1) {
				e1.printStackTrace();
			}
			
			responseBytes = retStr.getBytes();
			break;

		default:
			retStr = "<message><result>NOK</result><desc>" + "Command not found : " + restCommArr[0] + "</desc></message>"; 
			
			break;
		}

		Pinara.getLogger().debug(uriTxt + " command received and response forwarded !");

		responseBytes = retStr.getBytes();
		
		return responseBytes;

	}

}
