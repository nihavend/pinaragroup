package com.likya.pinara.test.jobcrud;


import java.util.HashMap;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;

import com.likya.myra.commons.utils.XMLValidations;
import com.likya.pinara.gui.rest.RestParser;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;


public class JobCrudOnRest extends JobRestTestCaseBase implements JobCrudInterface {
	
	public final static String RESTJOBROPS_CTX = pinaraUrl + "/flex/restsrvc/";
	
	protected void setUp() {
		httpGet(RESTJOBROPS_CTX + RestParser.CMD_NORECOVER, "");
    }

	public void testCreateDependencedRecords() {
		
		String xmlJob;
		
		try {
			
			int testCount = 10;
			int depAmount = 20;
			int depCount = 0;
		
			long startTime;
			
			for (int c = 0; c < testCount; c++) {
				
				startTime = System.currentTimeMillis();
				
				int maxId = latestJobId();
				
				AbstractJobType abstractJobType;
				
				if(++ depCount > depAmount) {
					depCount = 1;
					abstractJobType = generate(false);
				} else {
					abstractJobType = generate(maxId == 0 ? false:true);
					if(maxId > 1) {
						abstractJobType.getDependencyList().getItemArray(0).setJsId("" + maxId);
					}
				}
				
				JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
				JobList jobList = jobListDocument.addNewJobList();
				jobList.addNewGenericJob().set(abstractJobType);
				
				abstractJobType = jobListDocument.getJobList().getGenericJobArray(0);
				
				abstractJobType.getBaseJobInfos().setJsName("job" + (maxId + 1));
				abstractJobType.getBaseJobInfos().getJobTypeDetails().setJobCommand(abstractJobType.getBaseJobInfos().getJsName() + ".bat");
				
				if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
					throw new Exception("JobList.xml is null or damaged !");
				}

				/******/
				/**
				 * /Myra-0.0.1-Test/src/com/likya/myra/test/helpers/SimplePropsGenerator.java dan örneklendi
				 */
				
				XmlOptions xmlOptions = new XmlOptions();
				//xmlOptions.setUseDefaultNamespace();
				
				HashMap<String, String> ns = new HashMap<>(); 
				ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
				ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
				
				xmlOptions.setSaveSuggestedPrefixes(ns);
					
				xmlOptions.setSaveAggressiveNamespaces();
				xmlOptions.setSavePrettyPrint();
				
				// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
				JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
				
				/*****/
				
				xmlJob = jobListDocumentNew.toString();
				
				xmlJob = "<data><serialize>true</serialize><datamess>" + xmlJob + "</datamess></data>";
				String retString = httpPost(RESTJOBROPS_CTX + RestParser.CMD_JOBADD, xmlJob);

				long duration = System.currentTimeMillis() - startTime;
				System.err.println("added record in  " + duration + " ms");
				
				Assert.assertNotNull(retString);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atestCreateGroupedRecords() {

		String xmlJob;
		
		try {
			
			int testCount = 30;
			int groupAmount = 3;
			int groupId = 1;
			int groupCount = 0;
		
			long startTime;
			
			System.out.println(">> Group Id : " + groupId);
			
			for (int c = 0; c < testCount; c++) {
				
				startTime = System.currentTimeMillis();
				
				int maxId = latestJobId();
				
				AbstractJobType abstractJobType = generate(maxId == 0 ? false:true);
				
				if(++ groupCount > groupAmount) {
					groupCount = 1;
					++ groupId;
					System.out.println(">> Group Id : " + groupId);
				}
				
				System.out.println("\t>> Group Count : " + groupCount);
				
				abstractJobType.setGroupId("grp_" + groupId);
				
				JobListDocument jobListDocument = JobListDocument.Factory.newInstance();
				JobList jobList = jobListDocument.addNewJobList();
				jobList.addNewGenericJob().set(abstractJobType);
				
				abstractJobType = jobListDocument.getJobList().getGenericJobArray(0);
				
				abstractJobType.getBaseJobInfos().setJsName("job" + (maxId + 1));
				abstractJobType.getBaseJobInfos().getJobTypeDetails().setJobCommand(abstractJobType.getBaseJobInfos().getJsName() + ".bat");
				
				if(maxId > 1) {
					abstractJobType.getDependencyList().getItemArray(0).setJsId("" + maxId);
				}
								
				if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
					throw new Exception("JobList.xml is null or damaged !");
				}

				/******/
				/**
				 * /Myra-0.0.1-Test/src/com/likya/myra/test/helpers/SimplePropsGenerator.java dan örneklendi
				 */
				
				XmlOptions xmlOptions = new XmlOptions();
				//xmlOptions.setUseDefaultNamespace();
				
				HashMap<String, String> ns = new HashMap<>(); 
				ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
				ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
				
				xmlOptions.setSaveSuggestedPrefixes(ns);
					
				xmlOptions.setSaveAggressiveNamespaces();
				xmlOptions.setSavePrettyPrint();
				
				// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
				JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
				
				/*****/
				
				xmlJob = jobListDocumentNew.toString();
				
				xmlJob = "<data><serialize>true</serialize><datamess>" + xmlJob + "</datamess></data>";
				String retString = httpPost(RESTJOBROPS_CTX + RestParser.CMD_JOBADD, xmlJob);

				long duration = System.currentTimeMillis() - startTime;
				System.err.println("added record in  " + duration + " ms");
				
				Assert.assertNotNull(retString);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int latestJobId() {

		int maxId = 0;

		String retString = httpGet(RESTJOBROPS_CTX + RestParser.JOBSUMMARYLIST_XML_CMD, "");

		if(retString.equals("")) return maxId;
		
		String jobList = retString.split("<joblist>")[1];
		if(jobList.split("</joblist>").length > 0) {
			jobList = jobList.split("</joblist>")[0];
			for(String jobdetails : jobList.split("<job>")) {
				if(jobdetails.equals("")) continue;
				String jobIdTag = jobdetails.split("</job>")[0].split("<jobid>")[1].split("</jobid>")[0];
				int jobId = Integer.parseInt(jobIdTag);
				if(maxId < jobId) maxId = jobId;
			}
		}
		
		return maxId;
	}
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
//	public void testSimpleUpdate() {
//		User tmpUser = pinaraAuthorization.updateUser(sampleUser);
//		Assert.assertNotNull(tmpUser);
//	}
//	
//	public void testSimpleDelete() {
//		User tmpUser = pinaraAuthorization.deleteUser(sampleUser);
//		Assert.assertNotNull(tmpUser);
//	}
//	
//	public void testSimpleDeleteWithId() {
//		User tmpUser = pinaraAuthorization.deleteUser(recordId);
//		Assert.assertNotNull(tmpUser);
//	}
//
//	public void testChangePasswordWithId() {
//		User tmpUser = pinaraAuthorization.changePassword(recordId, "pinara", "dummy");
//		Assert.assertNotNull(tmpUser);
//	}
//	
//	public void testChangePasswordWithUsername() {
//		User tmpUser = pinaraAuthorization.changePassword(sampleUser.getUsername(), "pinara", "dummy");
//		Assert.assertNotNull(tmpUser);
//	}
//	
//	public void testChangePasswordAdmWithId() {
//		User tmpUser = pinaraAuthorization.changePasswordAdm(recordId, "pinara");
//		Assert.assertNotNull(tmpUser);
//	}
//	
//	public void testChangePasswordAdmWithUsername() {
//		User tmpUser = pinaraAuthorization.changePasswordAdm(sampleUser.getUsername(), "pinara");
//		Assert.assertNotNull(tmpUser);
//	}


}
