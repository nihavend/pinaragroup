package com.likya.myra.test.job.crud;

import java.io.IOException;
import java.util.HashMap;

import junit.framework.TestCase;

import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.myra.commons.utils.NetTreeResolver.NetTree;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.JobOperations;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.jef.jobs.ChangeLSI;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.test.model.generators.SimplePropertiesGenerator;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class TestJobOperations extends TestCase {

	final TestOutput testOutput = new TestOutput();

	CoreFactory coreFactory;
	JobOperations jobOperations;

	protected void setUp() throws IOException {
		coreFactory = (CoreFactory) CoreFactory.getInstance(testOutput);
		jobOperations = coreFactory.getJobOperations();
		
		ManagementOperations managementOperations = coreFactory.getManagementOperations();
		try {
			managementOperations.start();
			
			createDependencedRecords();
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}
	}

	public void testEnableGroup() throws Exception {

		HashMap<String, NetTree> netTreeMap = coreFactory.getNetTreeManagerInterface().getNetTreeMap();

		String grpId = (String) netTreeMap.keySet().toArray()[0];
		
		if (netTreeMap.containsKey(grpId)) {

			jobOperations.enableGroup(grpId);

			NetTree netTree = netTreeMap.get(grpId);
			for (String jobId : netTree.getMembers()) {
				AbstractJobType abstractJobType = coreFactory.getMonitoringOperations().getJobQueue().get(jobId).getAbstractJobType();
				System.out.println("Job Id : " + jobId +  " " + LiveStateInfoUtils.getLastStateInfo(abstractJobType));
				if (LiveStateInfoUtils.equalStates(LiveStateInfoUtils.getLastStateInfo(abstractJobType), StateName.PENDING, SubstateName.DEACTIVATED)) {
					assertTrue("group with id " + grpId + " contains a job with id " + jobId + " that is not enabled !", false);
				}
			}

		} else {
			assertTrue("netTreeMap does not contain a group with id " + grpId, false);
		}
	}

	public void testDisableGroup() throws Exception {

		HashMap<String, NetTree> netTreeMap = coreFactory.getNetTreeManagerInterface().getNetTreeMap();

		String grpId = (String) netTreeMap.keySet().toArray()[0];
		
		if (netTreeMap.containsKey(grpId)) {

			jobOperations.disableGroup(grpId);

			NetTree netTree = netTreeMap.get(grpId);
			for (String jobId : netTree.getMembers()) {
				AbstractJobType abstractJobType = coreFactory.getMonitoringOperations().getJobQueue().get(jobId).getAbstractJobType();
				if (!LiveStateInfoUtils.equalStates(LiveStateInfoUtils.getLastStateInfo(abstractJobType), StateName.PENDING, SubstateName.DEACTIVATED)) {
					assertTrue("group with id " + grpId + " contains a job with id " + jobId + " that is not disabled !", false);
				}
			}

		} else {
			assertTrue("netTreeMap does not contain a group with id " + grpId, false);
		}
	}

	private void createDependencedRecords() {

		try {

			int treeCount = 10;
			int depAmount = 10;

			long startTime;

			int depCount = 0;
			for (int c = 0; c < treeCount * depAmount; c++) {

				startTime = System.currentTimeMillis();

				int maxId = latestJobId();

				AbstractJobType abstractJobType;

				if (++depCount > depAmount) {
					depCount = 1;
					abstractJobType = SimplePropertiesGenerator.generate(false);
				} else {
					abstractJobType = SimplePropertiesGenerator.generate(maxId == 0 ? false : true);
					if (maxId > 1) {
						abstractJobType.getDependencyList().getItemArray(0).setJsId("" + maxId);
					}
				}

				JobOperations jobOperations = coreFactory.getJobOperations();
				
				if (!LiveStateInfoUtils.equalStatesPD(abstractJobType)) {
					ChangeLSI.forValue(abstractJobType, LiveStateInfoUtils.generateLiveStateInfo(StateName.INT_PENDING, SubstateName.INT_DEACTIVATED));
				}
				
				abstractJobType.getBaseJobInfos().setJsName("job" + (maxId + 1));
				abstractJobType.getBaseJobInfos().getJobTypeDetails().setJobCommand(abstractJobType.getBaseJobInfos().getJsName() + ".sh");
				
				jobOperations.addJob(abstractJobType, false);

				long duration = System.currentTimeMillis() - startTime;
				System.err.println("added record in  " + duration + " ms");

				// System.out.println(coreFactory.getMonitoringOperations().getJobQueue().get((maxId + 1) + "").getAbstractJobType());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int latestJobId() {
		
		int maxId = 0;
		
		for(JobImpl jobImpl : coreFactory.getMonitoringOperations().getJobQueue().values()) {
			int jobId = Integer.parseInt(jobImpl.getAbstractJobType().getId());
			if(maxId < jobId) maxId = jobId;
		}
		
		return maxId;
	}

}
