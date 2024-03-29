package com.likya.myra.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.NetTreeResolver;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestDependencyChain extends TestCase {

	private String dataPath = "";
	
	HashMap<String, NetTreeResolver.NetTree> netTreeMap;
	
	protected void setUp() throws IOException {
		getPropValues();
	}

	@SuppressWarnings("unchecked")
	public void test3depA() throws Exception {

		String fileName = dataPath + "3depA.xml";
		Object[] retValue = evaluate(fileName);

		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>) retValue[0];

		assertEquals("Expected size of map is 1 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 1);
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 3 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 3);
		}

	}
	
	@SuppressWarnings("unchecked")
	public void test3depV() throws Exception {

		String fileName = dataPath + "3depV.xml";
		Object[] retValue = evaluate(fileName);

		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>) retValue[0];

		assertEquals("Expected size of map is 1 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 1);
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 3 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 3);
		}

	}
	
	@SuppressWarnings("unchecked")
	public void test3dep1tree() throws Exception {

		String fileName = dataPath + "3dep1tree.xml";
		Object[] retValue = evaluate(fileName);

		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>) retValue[0];

		assertEquals("Expected size of map is 10 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 1);
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 3 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 3);
		}

	}
	
	@SuppressWarnings("unchecked")
	public void test2dep1tree() throws Exception {

		String fileName = dataPath + "2dep1tree.xml";
		Object[] retValue = evaluate(fileName);

		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>) retValue[0];
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 2 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 2);
		}

		assertEquals("Expected size of map is 10 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 1);

	}
	
	@SuppressWarnings("unchecked")
	public void test10dep1tree() throws Exception {
		
		String fileName = dataPath + "10dep.xml";
		Object[] retValue = evaluate(fileName);
		
		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>)retValue[0];

		assertEquals("Expected size of map is 1 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 1);

		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 10 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 10);
		}
		
	}
	
	public void test4dep2tree() throws Exception {

		String fileName = dataPath + "4dep2tree.xml";
		
		for(int i = 0; i < 1; i ++) {
			
			System.err.println("Count : + " + i);
			Object[] retValue = evaluate(fileName);
				
			@SuppressWarnings("unchecked")
			HashMap<String, NetTreeResolver.NetTree> netTreeMap = (HashMap<String, NetTreeResolver.NetTree>)retValue[0];
	
			assertEquals("Expected size of map is 2 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 2);
			
			for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
				assertEquals("Expected size of netTree.getMembers() is 2 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 2);
			}
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public void test10dep2tree() throws Exception {

		String fileName = dataPath + "10dep2tree.xml";
		Object[] retValue = evaluate(fileName);
		
		
		netTreeMap = (HashMap<String, NetTreeResolver.NetTree>)retValue[0];

		assertEquals("Expected size of map is 2 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 2);
		
		for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
			assertEquals("Expected size of netTree.getMembers() is 5 but the actual value is " + netTree.getMembers().size(), true, netTree.getMembers().size() == 5);
		}

	}

	@SuppressWarnings("unchecked")
	public void test6pinara() throws Exception {

		String fileName = dataPath + "6pinara.xml";

		for(int i = 0; i < 1; i ++) {
		
			System.err.println("Count : + " + i);
			Object[] retValue = evaluate(fileName);
			
			netTreeMap = (HashMap<String, NetTreeResolver.NetTree>) retValue[0];
	
			assertEquals("Expected size of map is 2 but the actual value is " + netTreeMap.size(), true, netTreeMap.size() == 2);
		}
		
	}
	
	//	public static void main(String[] args) throws Exception {
	//		
	//		HashMap<String, String> freeJobs = new HashMap<String, String>();
	//		
	//		long started = System.currentTimeMillis();
	//		
	//		// StringBuffer xmlString = TestMyra.getData(dataPath + "3depV.xml");
	//		StringBuffer xmlString = TestMyra.getData(dataPath + "10dep2tree.xml");
	//		
	//
	//		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
	//
	//		jobListDocument.getJobList().sizeOfGenericJobArray();
	//
	//		XmlObject[] objectArray = jobListDocument.getJobList().getGenericJobArray();
	//		
	//		StringBuilder str = null;
	//		for(int i = 0 ; i < 1; i++ ) {
	//			//for(int i = 0 ; i < 200; i++ ) {
	//				//netTreeMap.clear();
	//				//freeJobs.clear();
	//				// str = NetTreeResolver.runAlgorythm(objectArray, netTreeMap, freeJobs);
	//				// str = NewNetTreeResolver.runAlgorythm(objectArray, netTreeMap, freeJobs);
	//				HashMap<String, NetTreeResolver.NetTree> netTreeMap = new HashMap<String, NetTreeResolver.NetTree>();
	//				str = NetTreeResolver.runAlgorythm(objectArray, netTreeMap, freeJobs);
	//			//}
	//			
	//			long ended = System.currentTimeMillis();
	//			
	//			System.err.println(str.toString());
	//			
	//			System.err.println("runAlgorythm Total Duration : " + DateUtils.getFormattedElapsedTimeMS((ended - started)));
	//			
	//			System.err.println("netTreeMap.size() : " + netTreeMap.size());
	//			
	//			if(netTreeMap.size() != 2) {
	//				System.err.println("alooo");
	//			}
	//			
	//			for (NetTreeResolver.NetTree netTree : netTreeMap.values()) {
	//				System.err.println("netTree.virtualId : " + netTree.getVirtualId());
	//				System.err.println("netTree.members.size : " + netTree.getMembers().size());
	//			}
	//		}
	//		System.err.println("freeJobs.size() : " + freeJobs.size());
	//		
	//	}
	
	private Object[] evaluate(String fileName) throws Exception {
		
		Object[] retValue = new Object[2];
		
		StringBuffer xmlString = TestMyra.getData(fileName);
		
		HashMap<String, String> freeJobs = new HashMap<String, String>();
		HashMap<String, NetTreeResolver.NetTree> netTreeMap = new HashMap<String, NetTreeResolver.NetTree>();
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
		jobListDocument.getJobList().sizeOfGenericJobArray();
		XmlObject[] objectArray = jobListDocument.getJobList().getGenericJobArray();
		
		// StringBuilder str = 
		NetTreeResolver.runAlgorythm(objectArray, netTreeMap, freeJobs);
		// System.err.println(str.toString());
		
		retValue[0] = netTreeMap;
		retValue[1] = freeJobs;
		
		return retValue;
				
	}
	
	public String getPropValues() throws IOException {

		String result = "";
		FileInputStream fileInputStream = null;

		try {
			Properties prop = new Properties();
			String propFileName = "testconfig.properties";

			fileInputStream = new FileInputStream(propFileName);
			
			prop.load(fileInputStream);

			// Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			// String user = prop.getProperty("user");
			// String company1 = prop.getProperty("company1");
			// String company2 = prop.getProperty("company2");
			// String company3 = prop.getProperty("company3");

			// result = "Company List = " + company1 + ", " + company2 + ", " + company3;
			// System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
			
			dataPath = prop.getProperty("data.path");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			fileInputStream.close();
		}
		return result;
	}

}
