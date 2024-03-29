package com.likya.myra.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.DateUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class NetTreeResolverV0 {

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

	public NetTreeResolverV0() {
		super();
	}
	
	public static synchronized String generateVirtualId() {
		return "" + System.currentTimeMillis();
	}

	public static StringBuilder runAlgorythm(XmlObject[] objectArray, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) throws Exception {
		
		// NetTreeResolver.netTreeMap = netTreeMap;
		// NetTreeResolver.freeJobs = freeJobs;

		StringBuilder outputStr = new StringBuilder();

		long started = System.currentTimeMillis();

		ArrayList<String> jobListIdx = createIdx((AbstractJobType[]) objectArray);

		HashMap<String, AbstractJobType> jobMap = toMap((AbstractJobType[]) objectArray);

		long ended = System.currentTimeMillis();

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

			started = System.currentTimeMillis();
			mainScan(idKey, netTree, jobMap, netTreeMap, freeJobs);
			ended = System.currentTimeMillis();
			outputStr.append("mainScan total duration for [" + idKey + "] >> " + DateUtils.getFormattedElapsedTimeMS((ended - started)) + "\n");

		}
		
		return outputStr;

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

	private static void mainScan(String idKey, NetTree netTree, HashMap<String, AbstractJobType> jobMap, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) {

		AbstractJobType abstractJobType = jobMap.get(idKey);

		boolean isUppable = abstractJobType.getDependencyList() != null && abstractJobType.getDependencyList().sizeOfItemArray() != 0;

		//		long started = System.currentTimeMillis();
		boolean isDownable = findMeInDeps(abstractJobType, jobMap);
		//		long ended = System.currentTimeMillis();
		// System.err.println("findMeInDeps Total Duration for " + idKey + " : " + DateUtils.getFormattedElapsedTimeMS((ended - started)));

		// System.err.println("Working for : " + idKey + " " + abstractJobType.getBaseJobInfos().getJsName() + " [isUppable : " + isUppable + "][isDownable : " + isDownable + "]");

		if (!isUppable && !isDownable) {
			freeJobs.put(idKey, abstractJobType);
			jobMap.remove(idKey);
		} else {
			netTree.members.add(abstractJobType);
			jobMap.remove(idKey);
			if (isUppable) {
				//				started = System.currentTimeMillis();
				upScan(abstractJobType, netTree, jobMap, netTreeMap, freeJobs);
				//				ended = System.currentTimeMillis();
				// System.err.println("upScan recursive Total Duration for job : " + idKey + " : " + DateUtils.getFormattedElapsedTimeMS((ended - started)));
			}
			if (isDownable) {
				//				started = System.currentTimeMillis();
				downScan(abstractJobType, netTree, jobMap, netTreeMap, freeJobs);
				//				ended = System.currentTimeMillis();
				// System.err.println("downScan recursive Total Duration for job : " + idKey + " : " + DateUtils.getFormattedElapsedTimeMS((ended - started)));
			} else {
				// This job is one of the last jobs of branch
				abstractJobType.getGraphInfo().setLastNodeOfBranch(true);
			}

			netTreeMap.put(netTree.virtualId, netTree);
		}
	}

	private static void upScan(AbstractJobType abstractJobType, NetTree netTree, HashMap<String, AbstractJobType> jobMap, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) {

		DependencyList dependencyList = abstractJobType.getDependencyList();

		if (dependencyList == null || dependencyList.sizeOfItemArray() == 0) {
			return;
		}

		Item[] depItems = dependencyList.getItemArray();

		for (Item item : depItems) {
			if (jobMap.containsKey(item.getJsId())) {
				// AbstractJobType innerJob = jobMap.get(item.getJsId());
				// netTree.members.add(innerJob);
				// upScan(innerJob, members, jobMap);
				mainScan(item.getJsId(), netTree, jobMap, netTreeMap, freeJobs);
			}
		}

	}

	private static void downScan(AbstractJobType me, NetTree netTree, HashMap<String, AbstractJobType> jobMap, HashMap<String, NetTree> netTreeMap, HashMap<String, AbstractJobType> freeJobs) {

		Iterator<String> jobMapIterator = new ArrayList<String>(jobMap.keySet()).iterator();

		while (jobMapIterator.hasNext()) {
			String tmpKey = (String) jobMapIterator.next();
			if (!jobMap.containsKey(tmpKey)) {
				continue;
			}
			AbstractJobType innerJob = (AbstractJobType) jobMap.get(tmpKey);
			// System.err.println("	> Working for inner Job: " + innerJob.getId() + " [" + innerJob.getBaseJobInfos().getJsName() + "]");
			if (innerJob.getDependencyList() != null && innerJob.getDependencyList().sizeOfItemArray() > 0) {
				DependencyList dependencyList = innerJob.getDependencyList();
				Item[] depItems = dependencyList.getItemArray();
				for (Item item : depItems) {
					// System.err.println("		> Working for inner depJob: " + item.getJsId() + " " + item.getJsName());
					if (jobMap.containsKey(innerJob.getId()) && me.getId().equals(item.getJsId())) {
						// netTree.members.add(innerJob);
						// AbstractJobType tmpJob = jobMap.remove(innerJob.getId());
						// downScan(tmpJob, members, jobMap);
						mainScan(innerJob.getId(), netTree, jobMap, netTreeMap, freeJobs);
					}
				}
			}
		}

	}

	private static boolean findMeInDeps(AbstractJobType me, HashMap<String, AbstractJobType> jobMap) {

		Iterator<AbstractJobType> jobMapIterator = jobMap.values().iterator();

		while (jobMapIterator.hasNext()) {
			AbstractJobType abstractJobType = (AbstractJobType) jobMapIterator.next();
			if (abstractJobType.getDependencyList() != null && abstractJobType.getDependencyList().sizeOfItemArray() > 0) {
				DependencyList dependencyList = abstractJobType.getDependencyList();
				Item[] depItems = dependencyList.getItemArray();
				for (Item item : depItems) {
					if (me.getId().equals(item.getJsId())) {
						return true;
					}
				}
			}
		}

		return false;

	}

}
