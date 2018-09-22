package com.likya.myra.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.DateUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class NetTreeResolverV2 {

	// static HashMap<String, NetTree> netTreeMap;
 
	// static HashMap<String, AbstractJobType> freeJobs;

	public static class NetTree {
		
		protected String virtualId;
		protected ArrayList<AbstractJobType> members = new ArrayList<AbstractJobType>();
		
		public String getVirtualId() {
			return virtualId;
		}
		public ArrayList<AbstractJobType> getMembers() {
			return members;
		}
	}

	public NetTreeResolverV2() {
		super();
	}
	
	public static synchronized String generateVirtualId() {
		return "" + DateUtils.getCurrentTimeMilliseconds();
	}

	public static StringBuilder runAlgorythm(XmlObject[] objectArray, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) throws Exception {
		
		// NetTreeResolver.netTreeMap = netTreeMap;
		// NetTreeResolver.freeJobs = freeJobs;

		StringBuilder outputStr = new StringBuilder();

		long started = DateUtils.getCurrentTimeMilliseconds();

		ArrayList<String> jobListIdx = createIdx((AbstractJobType[]) objectArray);

		HashMap<String, AbstractJobType> jobMap = toMap((AbstractJobType[]) objectArray);

		long ended = DateUtils.getCurrentTimeMilliseconds();

		outputStr.append("header >> " + DateUtils.getFormattedElapsedTimeMS((ended - started)) + "\n");

		Iterator<String> idxIterator = jobListIdx.iterator();
		while (idxIterator.hasNext()) {

			String idKey = (String) idxIterator.next();

			if (!jobMap.containsKey(idKey)) {
				// may be removed by iterator 
				continue;
			}

			NetTree netTree = new NetTree();
			netTree.virtualId = generateVirtualId();

			started = DateUtils.getCurrentTimeMilliseconds();
			// mainScan(idKey, netTree, jobMap, netTreeMap, freeJobs);
			
			scan(jobMap.get(idKey), netTree, jobMap, netTreeMap, freeJobs);
			
			ended = DateUtils.getCurrentTimeMilliseconds();
			outputStr.append("mainScan total duration for [" + idKey + "] >> " + DateUtils.getFormattedElapsedTimeMS((ended - started)) + "\n");

			netTreeMap.put(netTree.virtualId, netTree);
		}
		
		return outputStr;

	}
	
							
	private static void scan(AbstractJobType abstractJobType, NetTree netTree, HashMap<String, AbstractJobType> jobMap, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) {
		
		ArrayList<String> edgeList = getEdges(abstractJobType, jobMap);
		
		String idKey = abstractJobType.getId();
		
		if (edgeList.size() == 0) {
			freeJobs.put(idKey, abstractJobType);
			jobMap.remove(idKey);
		} else {
			netTree.members.add(abstractJobType);
			jobMap.remove(idKey);
			
			innerscan(edgeList, abstractJobType, netTree, jobMap);
		}
	}
	
	private static void innerscan(ArrayList<String> edgeList, AbstractJobType abstractJobType, NetTree netTree, HashMap<String, AbstractJobType> jobMap) {
		
		for(String jobId : edgeList) {
			if (!jobMap.containsKey(jobId)) {
				// may be removed by iterator 
				continue;
			}
			abstractJobType = jobMap.get(jobId);
			netTree.members.add(abstractJobType);
			jobMap.remove(jobId);
			ArrayList<String> innerEdgeList = getEdges(abstractJobType, jobMap);
			innerscan(innerEdgeList, abstractJobType, netTree, jobMap);
		}
		
	}
	
	private static HashMap<String, AbstractJobType> toMap(AbstractJobType[] objectArray) {

		HashMap<String, AbstractJobType> tmpMap = new HashMap<String, AbstractJobType>();

		for (AbstractJobType abstractJobType : objectArray) {
			tmpMap.put(abstractJobType.getId(), abstractJobType);
		}

		return tmpMap;
	}

	private static ArrayList<String> createIdx(AbstractJobType[] objectArray) {

		ArrayList<String> jobListIdx = new ArrayList<String>();

		for (AbstractJobType abstractJobType : objectArray) {
			jobListIdx.add(abstractJobType.getId());
		}

		return jobListIdx;
	}

	private static ArrayList<String> findMeInDeps(AbstractJobType me, HashMap<String, AbstractJobType> jobMap) {

		ArrayList<String> edgeList = new ArrayList<String>();
		
		Iterator<AbstractJobType> jobMapIterator = jobMap.values().iterator();

		while (jobMapIterator.hasNext()) {
			AbstractJobType abstractJobType = (AbstractJobType) jobMapIterator.next();
			if (abstractJobType.getDependencyList() != null) {
				for (Item item : abstractJobType.getDependencyList().getItemArray()) {
					if (me.getId().equals(item.getJsId())) {
						edgeList.add(abstractJobType.getId());
					}
				}
			}
		}

		return edgeList;

	}

	private static ArrayList<String> getEdges(AbstractJobType abstractJobType, HashMap<String, AbstractJobType> jobMap) {
		
		ArrayList<String> edgeList = findMeInDeps(abstractJobType, jobMap);
		
		boolean isUppable = abstractJobType.getDependencyList() != null && abstractJobType.getDependencyList().sizeOfItemArray() != 0;
		
		if(isUppable) {
			for(Item item : abstractJobType.getDependencyList().getItemArray()) {
				edgeList.add(item.getJsId());
			}
		}
		
		return edgeList;
		
	}
}
