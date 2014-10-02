package com.likya.pinara.infobus;

import com.likya.myra.jef.model.OutputData;
import com.likya.pinara.Pinara;
import com.likya.pinara.model.PinaraOutput;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

/**
 * @author vista
 * 
 */
public class PinaraOutputManager implements Runnable {

	private final int timeout = 10000;
	private boolean executePermission = true;
	private PinaraOutput pinaraOutput = PinaraOutput.getInstance();

	public PinaraOutputManager() {
	}

	public void terminate(boolean forcedTerminate) {
		synchronized (this) {
			if (forcedTerminate) {
				pinaraOutput.getOutputList().clear();
			}
			this.executePermission = false;
		}
	}

	public void run() {
		Thread.currentThread().setName("PinaraOutputManager");
		while (executePermission || pinaraOutput.getOutputList().size() > 0) {

			while (pinaraOutput.getOutputList().size() > 0) {

				synchronized (pinaraOutput) {

					OutputData outputData = (OutputData) pinaraOutput.getOutputList().get(0);
					Pinara.getLogger().debug("OutputManager işliyor ...");
					// System.err.println(outputData.getLiveStateInfo());
					// Pinara.getLogger().debug(outputData.getLiveStateInfo());
					LiveStateInfo liveStateInfo = outputData.getLiveStateInfo();
					String dump = "	Live State : " + (liveStateInfo.getStateName() == null ? "" : liveStateInfo.getStateName().toString());
					dump += (liveStateInfo.getSubstateName() == null ? "" : ("-" + liveStateInfo.getSubstateName().toString()));
					dump += (liveStateInfo.getStatusName() == null ? "" : ("-" + liveStateInfo.getStatusName().toString()));
					Pinara.getLogger().debug(dump);
					if(StateName.FINISHED.equals(outputData.getLiveStateInfo().getStateName())) {
						Pinara.getLogger().debug(outputData.getJobId() + " is finished !!!!");
						// System.err.println(outputData.getJobId() + " is finished !!!!");
					}
					
					// TODO Resolve broadcasting type
					// email, db, sms, etc

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
		Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.7"));
		Pinara.getLogger().info(Pinara.getMessage("PinaraMailServer.8") + pinaraOutput.getOutputList().size());
	}

	public int getQueueSize() {
		return pinaraOutput.getOutputList().size();
	}

	public PinaraOutput getPinaraOutput() {
		return pinaraOutput;
	}
}
