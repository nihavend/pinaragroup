package com.likya.pinara.test.jobcrud;


import org.junit.Assert;

import com.likya.commons.utils.FileUtils;
import com.likya.pinara.gui.rest.RestParser;



public class JobCrudOnRest extends JobRestTestCaseBase {
	
	public final static String RESTJOBROPS_CTX = "http://127.0.0.1:4000" + "/flex/restsrvc/"; // pinaraUrl + "/flex/restsrvc/";
	
	protected void setUp() {
		httpGet(RESTJOBROPS_CTX + RestParser.CMD_NORECOVER, "");
    }

//	public void testCreateDependencedRecords() {
//		
//		String xmlJob;
//		
//		try {
//			
//			int treeCount = 10;
//			int depAmount = 3;
//			
//		
//			long startTime;
//			int depCount = 0;
//			for (int c = 0; c < treeCount * depAmount; c++) {
//				
//				startTime = System.currentTimeMillis();
//				
//				int maxId = latestJobId();
//				
//				AbstractJobType abstractJobType;
//				
//				if(++ depCount > depAmount) {
//					depCount = 1;
//					abstractJobType = SimplePropertiesGenerator.generate(false);
//				} else {
//					abstractJobType = SimplePropertiesGenerator.generate(maxId == 0 ? false:true);
//					if(maxId > 1) {
//						abstractJobType.getDependencyList().getItemArray(0).setJsId("" + maxId);
//					}
//				}
//				
//				JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
//				JobList jobList = jobListDocument.addNewJobList();
//				jobList.addNewGenericJob().set(abstractJobType);
//				
//				abstractJobType = jobListDocument.getJobList().getGenericJobArray(0);
//				
//				abstractJobType.getBaseJobInfos().setJsName("job" + (maxId + 1));
//				abstractJobType.getBaseJobInfos().getJobTypeDetails().setJobCommand(abstractJobType.getBaseJobInfos().getJsName() + ".bat");
//				
//				if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
//					throw new Exception("JobList.xml is null or damaged !");
//				}
//
//				/******/
//				/**
//				 * /Myra-0.0.1-Test/src/com/likya/myra/test/helpers/SimplePropsGenerator.java dan örneklendi
//				 */
//				
//				XmlOptions xmlOptions = new XmlOptions();
//				//xmlOptions.setUseDefaultNamespace();
//				
//				HashMap<String, String> ns = new HashMap<>(); 
//				ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
//				ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
//				
//				xmlOptions.setSaveSuggestedPrefixes(ns);
//					
//				xmlOptions.setSaveAggressiveNamespaces();
//				xmlOptions.setSavePrettyPrint();
//				
//				// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
//				JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
//				
//				/*****/
//				
//				xmlJob = jobListDocumentNew.toString();
//				
//				xmlJob = "<data><serialize>true</serialize><datamess>" + xmlJob + "</datamess></data>";
//				String retString = httpPost(RESTJOBROPS_CTX + RestParser.CMD_JOBADD, xmlJob);
//
//				long duration = System.currentTimeMillis() - startTime;
//				System.err.println("added record in  " + duration + " ms");
//				
//				Assert.assertNotNull(retString);
//			
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void atestCreateGroupedRecords() {
//
//		String xmlJob;
//		
//		try {
//			
//			int testCount = 30;
//			int groupAmount = 3;
//			int groupId = 1;
//			int groupCount = 0;
//		
//			long startTime;
//			
//			System.out.println(">> Group Id : " + groupId);
//			
//			for (int c = 0; c < testCount; c++) {
//				
//				startTime = System.currentTimeMillis();
//				
//				int maxId = latestJobId();
//				
//				AbstractJobType abstractJobType = SimplePropertiesGenerator.generate(maxId == 0 ? false:true);
//				
//				if(++ groupCount > groupAmount) {
//					groupCount = 1;
//					++ groupId;
//					System.out.println(">> Group Id : " + groupId);
//				}
//				
//				System.out.println("\t>> Group Count : " + groupCount);
//				
//				abstractJobType.setGroupId("grp_" + groupId);
//				
//				JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
//				JobList jobList = jobListDocument.addNewJobList();
//				jobList.addNewGenericJob().set(abstractJobType);
//				
//				abstractJobType = jobListDocument.getJobList().getGenericJobArray(0);
//				
//				abstractJobType.getBaseJobInfos().setJsName("job" + (maxId + 1));
//				abstractJobType.getBaseJobInfos().getJobTypeDetails().setJobCommand(abstractJobType.getBaseJobInfos().getJsName() + ".bat");
//				
//				if(maxId > 1) {
//					abstractJobType.getDependencyList().getItemArray(0).setJsId("" + maxId);
//				}
//								
//				if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
//					throw new Exception("JobList.xml is null or damaged !");
//				}
//
//				/******/
//				/**
//				 * /Myra-0.0.1-Test/src/com/likya/myra/test/helpers/SimplePropsGenerator.java dan örneklendi
//				 */
//				
//				XmlOptions xmlOptions = new XmlOptions();
//				//xmlOptions.setUseDefaultNamespace();
//				
//				HashMap<String, String> ns = new HashMap<>(); 
//				ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
//				ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
//				
//				xmlOptions.setSaveSuggestedPrefixes(ns);
//					
//				xmlOptions.setSaveAggressiveNamespaces();
//				xmlOptions.setSavePrettyPrint();
//				
//				// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
//				JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
//				
//				/*****/
//				
//				xmlJob = jobListDocumentNew.toString();
//				
//				xmlJob = "<data><serialize>true</serialize><datamess>" + xmlJob + "</datamess></data>";
//				String retString = httpPost(RESTJOBROPS_CTX + RestParser.CMD_JOBADD, xmlJob);
//
//				long duration = System.currentTimeMillis() - startTime;
//				System.err.println("added record in  " + duration + " ms");
//				
//				Assert.assertNotNull(retString);
//			
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	private int latestJobId() {
//
//		int maxId = 0;
//
//		String retString = httpGet(RESTJOBROPS_CTX + RestParser.JOBSUMMARYLIST_XML_CMD, "");
//
//		if(retString.equals("")) return maxId;
//		
//		String jobList = retString.split("<joblist>")[1];
//		if(jobList.split("</joblist>").length > 0) {
//			jobList = jobList.split("</joblist>")[0];
//			for(String jobdetails : jobList.split("<job>")) {
//				if(jobdetails.equals("")) continue;
//				String jobIdTag = jobdetails.split("</job>")[0].split("<jobid>")[1].split("</jobid>")[0];
//				int jobId = Integer.parseInt(jobIdTag);
//				if(maxId < jobId) maxId = jobId;
//			}
//		}
//		
//		return maxId;
//	}
//	
//	public void testSimpleReadWithId() {
//		User tmpUser = pinaraAuthorization.readUser(recordId);
//		Assert.assertNotNull(tmpUser);
//	}

//	public void testSimpleReadWithUsername() {
//		User tmpUser = pinaraAuthorization.readUser(sampleUser.getUsername());
//		Assert.assertNotNull(tmpUser);
//	}
//
	public void testSimpleUpdate() {
		StringBuffer xmlString = FileUtils.readFile("/Users/serkan/git/pinaragroup/PinaraTest/testData/job.xml");
		String retString = httpPost(RESTJOBROPS_CTX + RestParser.CMD_JOBUPDATE, xmlString.toString());
		Assert.assertTrue(retString.contains("<result>OK</result>"));
	}

	public void testAddJob() {
		// TODO Auto-generated method stub
		
	}

}
