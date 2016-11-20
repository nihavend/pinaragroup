/*******************************************************************************
 * Copyright 2013 Likya Teknoloji
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.likya.myra.ext.odi;

import java.util.Calendar;
import java.util.List;

import oracle.odi.core.OdiInstance;
import oracle.odi.core.config.MasterRepositoryDbInfo;
import oracle.odi.core.config.OdiInstanceConfig;
import oracle.odi.core.config.PoolingAttributes;
import oracle.odi.core.config.WorkRepositoryDbInfo;
import oracle.odi.core.security.Authentication;
import oracle.odi.domain.runtime.scenario.OdiScenario;
import oracle.odi.domain.runtime.scenario.OdiScenarioReport;
import oracle.odi.domain.runtime.scenario.OdiStepReport;
import oracle.odi.domain.runtime.scenario.finder.IOdiScenarioFinder;
import oracle.odi.runtime.agent.invocation.InvocationException;
import oracle.odi.runtime.agent.invocation.RemoteRuntimeAgentInvoker;
import oracle.odi.runtime.agent.invocation.StartupParams;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.GenericInnerJob;
import com.likya.myra.jef.jobs.JobHelper;
import com.likya.myra.jef.model.JobRuntimeInterface;
import com.likya.xsd.myra.ext.odi.OdiExtParamsDocument.OdiExtParams;
import com.likya.xsd.myra.ext.odi.OdiExtProperties;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;

public class OdiExtJob extends GenericInnerJob {

	private static final long serialVersionUID = 7931558555995487881L;

	private final String logLabel = " OdiExtJob ";

	public OdiExtJob(AbstractJobType abstractJobType, JobRuntimeInterface jobRuntimeProperties) {
		super(abstractJobType, jobRuntimeProperties);
	}

	private OdiInstance initConnection(OdiExtParams odiExtParams) {

		MasterRepositoryDbInfo masterInfo = new MasterRepositoryDbInfo(odiExtParams.getPJdbcUrl(), odiExtParams.getPJdbcDriver(), odiExtParams.getPJdbcUsername(), odiExtParams.getPJdbcPassword().toCharArray(), new PoolingAttributes());
		WorkRepositoryDbInfo workInfo = new WorkRepositoryDbInfo(odiExtParams.getPWorkName(), new PoolingAttributes());
		OdiInstance odiInstance = OdiInstance.createInstance(new OdiInstanceConfig(masterInfo, workInfo));
		Authentication auth = odiInstance.getSecurityManager().createAuthentication(odiExtParams.getPUsername(), odiExtParams.getPPassword().toCharArray());
		odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth);

		return odiInstance;
	}

	private OdiScenario checkScenario(OdiInstance odiInstance, String pScenName) {

		// Retrieve the scenario we are looking for
		OdiScenario odiScenario = ((IOdiScenarioFinder) odiInstance.getTransactionalEntityManager().getFinder(OdiScenario.class)).findLatestByName(pScenName);

		if (odiScenario == null) {
			myLogger.error("Error: cannot find scenario " + pScenName);
			throw new RuntimeException("Error: cannot find scenario " + pScenName);
		}

		return odiScenario;
	}

	private void executeScenario(OdiExtParams odiExtParams) throws InvocationException {
		RemoteRuntimeAgentInvoker agent = new RemoteRuntimeAgentInvoker(odiExtParams.getPAgentUrl(), odiExtParams.getPUsername(), odiExtParams.getPPassword().toCharArray());
		StartupParams startupParams = new StartupParams();
		agent.invokeStartScenario(odiExtParams.getPScenName(), odiExtParams.getPScenVersion(), startupParams, odiExtParams.getPKeywords(), odiExtParams.getPContextCode(), odiExtParams.getPLogLevel().intValue(), odiExtParams.getPSessionName(), odiExtParams.getPSynchronous(), odiExtParams.getPWorkName());
	}

	private void getReportDetails(OdiScenario odiScenario) {

		OdiExtProperties odiExtProperties = (OdiExtProperties) getAbstractJobType();

		OdiExtParams odiExtParams = odiExtProperties.getOdiExtParams();
		
		String jobId = odiExtProperties.getId();
		
		String scenarioName = odiExtParams.getPScenName();
		
		String logClassName = this.getClass().getName();
		
		//--------------------------------------
		// Retrieve all reports for the scenario 
		List<OdiScenarioReport> odiScenarioReportsList = odiScenario.getScenarioReports();

		myLogger.info("*** Listing all reports for Scenario \"" + scenarioName + "\" Size : " + odiScenarioReportsList.size());

		//--------------------------------------
		// For each report, print the folowing:
		// - start time
		// - duration
		// - status
		// - step reports: selection of details

		StringBuffer descStr = new StringBuffer();
		StringBuilder stringBufferForOUTPUT = new StringBuilder();
		StringBuilder stringBufferForERROR = new StringBuilder();
		
		StringBuilder reportLog = new StringBuilder();
		StatusName.Enum statusName = StatusName.SUCCESS;
		int processExitValue = 0;
		
		for (OdiScenarioReport s : odiScenarioReportsList) {
			reportLog.append("\tStart time: " + s.getSessionStartTime());
			reportLog.append("\tDuration: " + s.getSessionDuration());
			reportLog.append("\tStatus: " + s.getSessionStatus());
			
			processExitValue = s.getSessionStatus().ordinal();
			
			myLogger.info(" >>" + logLabel + jobId + " islemi sonlandi, islem bitis degeri : " + s.getSessionStatus());
			statusName = JobHelper.searchReturnCodeInStates(odiExtProperties, processExitValue, descStr);
			
			List<OdiStepReport> odiScenarioStepReportsList = s.getStepReports();
			for (OdiStepReport st : odiScenarioStepReportsList) {
				reportLog.append("\t\tStep Name: " + st.getStepName());
				reportLog.append("\t\tStep Resource Name: " + st.getStepResourceName());
				reportLog.append("\t\tStep Start time: " + st.getStepStartTime());
				reportLog.append("\t\tStep Duration: " + st.getStepDuration());
				reportLog.append("\t\tStep Status: " + st.getStepStatus());
				reportLog.append("\t\tStep Error Message: " + st.getStepErrorMessage());
				reportLog.append("\t\tStep # of inserts: " + st.getStepInsertCount());
				reportLog.append("\t\tStep # of updates: " + st.getStepUpdateCount() + '\n');
			}
			reportLog.append("\t");
		}
		
		stringBufferForERROR.append(reportLog);
		JobHelper.updateDescStr(descStr, stringBufferForOUTPUT, stringBufferForERROR);
		JobHelper.writeErrorLogFromOutputs(myLogger, logClassName, stringBufferForOUTPUT, stringBufferForERROR);
		setOfCodeMessage(odiExtProperties, statusName, processExitValue, descStr.toString());
	}

	protected void localRun() {

		Thread.currentThread().setName("OdiExtJob");

		startTime = Calendar.getInstance();

		OdiExtProperties odiExtProperties = (OdiExtProperties) getAbstractJobType();

		try {

			startWathcDogTimer();

			setRunning(odiExtProperties);

			startOdiProcess();

		} catch (Exception err) {
			handleException(err, CoreFactory.getLogger());
		}

	}

	public void startOdiProcess() throws Exception {
		
		OdiExtProperties odiExtProperties = (OdiExtProperties) getAbstractJobType();

		OdiExtParams odiExtParams = odiExtProperties.getOdiExtParams();
		
		// String logClassName = this.getClass().getName();

		//try {
			
			OdiInstance odiInstance = initConnection(odiExtParams);

			OdiScenario odiScenario = checkScenario(odiInstance, odiExtParams.getPScenName());

			executeScenario(odiExtParams);
			
			Calendar endTime = Calendar.getInstance();
			
			JobHelper.setJsRecordedTimeForStop(odiExtProperties, endTime);

			getReportDetails(odiScenario);
			
			odiInstance.close();
			
		//} catch (InterruptedException e) {

		//	myLogger.warn(" >>" + logLabel + " >> " + logClassName + " Job timed-out terminating " + odiExtProperties.getBaseJobInfos().getJsName());

		// }
	}

	protected void cleanUp() {

		AbstractJobType abstractJobType = getAbstractJobType();

		CoreFactory.getLogger().debug(" >>" + logLabel + ">> " + "Terminating Error for " + abstractJobType.getId());
		CoreFactory.getLogger().debug(" >>" + logLabel + ">> " + "Terminating Output for " + abstractJobType.getId());

		if (watchDogTimer != null) {
			CoreFactory.getLogger().debug(" >>" + logLabel + ">> " + "Terminating Watchdog for " + abstractJobType.getBaseJobInfos().getJsName());
			stopMyDogBarking();
		}

		setMyExecuter(null);

		CoreFactory.getLogger().info(" >>" + logLabel + ">> ExecuterThread:" + Thread.currentThread().getName() + " is over");
	}

}
