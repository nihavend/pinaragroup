package com.likya.pinara.infobus;

import java.util.HashMap;

import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.myra.commons.utils.NetTreeResolver;
import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.myra.jef.model.OutputData;
import com.likya.pinara.Pinara;
import com.likya.pinara.model.PinaraOutput;
import com.likya.pinara.utils.PersistDBApi;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.pinara.model.config.MailInfoDocument.MailInfo;
import com.likya.xsd.pinara.model.config.PinaraConfigDocument.PinaraConfig;

/**
 * @author vista
 * 
 */
public class PinaraOutputManager implements Runnable {

	private final int timeout = 500;
	private boolean executePermission = true;
	private PinaraOutput pinaraOutput = PinaraOutput.getInstance();
	private boolean setForced = false;
	
	private Thread executorThread;

	public PinaraOutputManager() {
	}

	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			if (forcedTerminate) {
				// pinaraOutput.getOutputList().clear();
				// artık hemen kapatmayacak çünkü eventler var.
				// sadece mailleri göndermeyecek
				/**
				 *  @author serkan taş 29.10.2017 15:37
				 */
				setForced = true;
			}
			this.executePermission = false;
		}
	}

	private void handle_JOBSTATECHANGE(OutputData outputData) {
		
		PersistDBApi.handleJobDataChangeEvent(outputData.getJobId());
		
		if(!setForced) {
			// System.err.println(outputData.getLiveStateInfo());
			// Pinara.getLogger().debug(outputData.getLiveStateInfo());
			LiveStateInfo liveStateInfo = outputData.getLiveStateInfo();
			String dump = "	Live State : " + (liveStateInfo.getStateName() == null ? "" : liveStateInfo.getStateName().toString());
			dump += (liveStateInfo.getSubstateName() == null ? "" : ("-" + liveStateInfo.getSubstateName().toString()));
			dump += (liveStateInfo.getStatusName() == null ? "" : ("-" + liveStateInfo.getStatusName().toString()));
			Pinara.getLogger().debug(dump);
			if(StateName.FINISHED.equals(outputData.getLiveStateInfo().getStateName())) {
				Pinara.getLogger().debug("Job >> " + outputData.getJobId() + ":" + outputData.getJobName() + " is finished !!!!");
				// System.err.println(outputData.getJobId() + " is finished !!!!");
			}
			
			// TODO Resolve broadcasting type
			// email, db, sms, etc
			
			PinaraConfig pinaraConfig = Pinara.getInstance().getConfigurationManager().getPinaraConfig();
			if(pinaraConfig.isSetMailInfo()) {
				MailInfo mailInfo = pinaraConfig.getMailInfo();
				if(mailInfo.getEnabled()) {
					LiveStateInfosType liveStateInfosType = mailInfo.getStateInfos().getLiveStateInfos();
					if(LiveStateInfoUtils.containsAny(liveStateInfosType, liveStateInfo)) {
						SimpleMail simpleMail = new SimpleMail("Job Durum değişikliği Job >> " + outputData.getJobId() + ":" + outputData.getJobName(), "Belirtilen iş şu duruma geçti : " + dump);
						Pinara.getInstance().getConfigurationManager().getPinaraMailServer().sendMail(simpleMail);
					}
				}
			}
		}
	}

	private void handle_LOGANALYZER(OutputData outputData) {
		PinaraConfig pinaraConfig = Pinara.getInstance().getConfigurationManager().getPinaraConfig();
		if(pinaraConfig.isSetMailInfo()) {
			MailInfo mailInfo = pinaraConfig.getMailInfo();
			if(mailInfo.getEnabled()) {
				Pinara.getInstance().getConfigurationManager().getPinaraMailServer().sendMail(new ContentMail("LOGANALYZER", outputData.getOutputContent().toString()));
			}
		}
	}

	private void handle_DEFAULT(OutputData outputData) {
		Pinara.getLogger().error("handle_DEFAULT");
	}

	private void handle_ENDOFCYCLE(OutputData outputData) {
		NetTreeResolver.NetTree netTree = (NetTreeResolver.NetTree) outputData.getOutputContent();
		Pinara.getLogger().debug("Dependency group with virtualId  " + netTree.getVirtualId() + " is finished !!!!");
		PinaraConfig pinaraConfig = Pinara.getInstance().getConfigurationManager().getPinaraConfig();
		if(pinaraConfig.isSetMailInfo()) {
			MailInfo mailInfo = pinaraConfig.getMailInfo();
			if(mailInfo.getEnabled()) {
				HashMap<String, JobImpl> jobMainQueue = CoreFactory.getInstance().getMonitoringOperations().getJobQueue();
				HashMap<String, JobImpl> jobNetTreeQueue = new HashMap<String, JobImpl>();
				for (String jobId : netTree.getMembers()) {
					jobNetTreeQueue.put(jobId, jobMainQueue.get(jobId));
				}
				Pinara.getInstance().getConfigurationManager().getPinaraMailServer().sendMail(new EndOfCycleMail(netTree.getVirtualId(), jobNetTreeQueue));
			}
		}
	}

	public void run() {
		Thread.currentThread().setName("PinaraOutputManager");
		while (!setForced && (executePermission || pinaraOutput.getOutputList().size() > 0)) {

			while (pinaraOutput.getOutputList().size() > 0) {

				synchronized (pinaraOutput) {

					OutputData outputData = (OutputData) pinaraOutput.getOutputList().get(0);
					Pinara.getLogger().debug("OutputManager işliyor ...");
					
					switch (outputData.getOutputType()) {
					case JOBSTATECHANGE:
						handle_JOBSTATECHANGE(outputData);
						break;
					case LOGANALYZER:
						handle_LOGANALYZER(outputData);
						break;
					case BASIC:
						handle_DEFAULT(outputData);
						break;
					case ENDOFCYCLE:
						handle_ENDOFCYCLE(outputData);
						break;
					default:
						Pinara.getLogger().error("Invalid output type :" + outputData.getOutputType());
						break;
					}			

					if (!pinaraOutput.getOutputList().isEmpty()) {
						pinaraOutput.getOutputList().remove(0);
					}
				}

			}

			try {
				// TlosServer.getLogger().debug("Mail server sleeping !");
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Pinara.getLogger().info(Pinara.getMessage("PinaraOutputManager kapatıldı"));
		Pinara.getLogger().info("PinaraOutputManager : " + Pinara.getMessage("PinaraMailServer.8") + pinaraOutput.getOutputList().size());
	}

	public int getQueueSize() {
		return pinaraOutput.getOutputList().size();
	}

	public PinaraOutput getPinaraOutput() {
		return pinaraOutput;
	}

	public Thread getExecutorThread() {
		return executorThread;
	}

	public void setExecutorThread(Thread executorThread) {
		this.executorThread = executorThread;
	}
}
