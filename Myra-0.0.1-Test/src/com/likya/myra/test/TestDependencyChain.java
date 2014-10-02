package com.likya.myra.test;

import java.util.HashMap;

import org.apache.xmlbeans.XmlObject;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.commons.utils.NetTreeResolver;
import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestDependencyChain {

	public static void main(String[] args) throws Exception {
		
		HashMap<String, NetTree> netTreeMap = new HashMap<String, NetTree>();

		HashMap<String, AbstractJobType> freeJobs = new HashMap<String, AbstractJobType>();
		
		long started = System.currentTimeMillis();
		
		StringBuffer xmlString = TestMyra.getData("/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/8down.xml");

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());

		jobListDocument.getJobList().sizeOfGenericJobArray();

		XmlObject[] objectArray = jobListDocument.getJobList().getGenericJobArray();
		
		StringBuilder str = null;
		
		//for(int i = 0 ; i < 200; i++ ) {
			//netTreeMap.clear();
			//freeJobs.clear();
			str = NetTreeResolver.runAlgorythm(objectArray, netTreeMap, freeJobs);
		//}
		
		long ended = System.currentTimeMillis();
		
		System.err.println(str.toString());
		
		System.err.println("runAlgorythm Total Duration : " + DateUtils.getFormattedElapsedTimeMS((ended - started)));
		
		System.err.println("netTreeMap.size() : " + netTreeMap.size());
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			System.err.println("netTree.virtualId : " + netTree.getVirtualId());
			System.err.println("netTree.members.size : " + netTree.getMembers().size());
		}
		
		System.err.println("freeJobs.size() : " + freeJobs.size());
		
	}


	
//	private static void upScan(AbstractJobType abstractJobType, ArrayList<AbstractJobType> members, HashMap<String, AbstractJobType> jobMap) {
//
//		DependencyList dependencyList = abstractJobType.getDependencyList();
//		
//		if(dependencyList == null || dependencyList.sizeOfItemArray() == 0) {
//			return;
//		}
//		
//		Item[] depItems = dependencyList.getItemArray();
//
//		for (Item item : depItems) {
//			if (jobMap.containsKey(item.getJsId())) {
//				AbstractJobType innerJob = jobMap.remove(item.getJsId());
//				members.add(innerJob);
//				upScan(innerJob, members, jobMap);
//			}
//		}
//
//	}
//
//	private static void downScan(AbstractJobType me, ArrayList<AbstractJobType> members, HashMap<String, AbstractJobType> jobMap) {
//
//		Iterator<String> jobMapIterator = new ArrayList<String>(jobMap.keySet()).iterator();
//
//		while (jobMapIterator.hasNext()) {
//			String tmpKey = (String) jobMapIterator.next();
//			if(!jobMap.containsKey(tmpKey)) {
//				continue;
//			}
//			AbstractJobType innerJob = (AbstractJobType) jobMap.get(tmpKey);
//			System.err.println("	> Working for inner Job: " + innerJob.getId() + " [" + innerJob.getBaseJobInfos().getJsName() + "]");
//			if (innerJob.getDependencyList() != null && innerJob.getDependencyList().sizeOfItemArray() > 0) {
//				DependencyList dependencyList = innerJob.getDependencyList();
//				Item[] depItems = dependencyList.getItemArray();
//				for (Item item : depItems) {
//					System.err.println("		> Working for inner depJob: " + item.getJsId() + " " + item.getJsName());
//					if (me.getId().equals(item.getJsId())) {
//						members.add(innerJob);
//						AbstractJobType tmpJob = jobMap.remove(innerJob.getId());
//						downScan(tmpJob, members, jobMap);
//					}
//				}
//			}
//		}
//
//	}

}
