package com.likya.pinara.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.likya.myra.jef.OutputStrategy;
import com.likya.myra.jef.model.OutputData;

public class PinaraOutput implements OutputStrategy, Serializable {
	
	private static final long serialVersionUID = -8303275985668148812L;
	
	private static PinaraOutput pinaraOutput;

	private ArrayList<OutputData> outputDataQueue = new ArrayList<OutputData>();

	private PinaraOutput() {
		super();
	}
	
	public static PinaraOutput getInstance() {
		if (pinaraOutput == null) {
			pinaraOutput = new PinaraOutput();
		}
		return pinaraOutput;
	}
	
	public synchronized void sendDataObject(Object dataObject) {
		
		OutputData outputData = (OutputData) dataObject;
		
		outputDataQueue.add(outputData);
		
	}

	public ArrayList<OutputData> getOutputList() {
		return outputDataQueue;
	}

}
