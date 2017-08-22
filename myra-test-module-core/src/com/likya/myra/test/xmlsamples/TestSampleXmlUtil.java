package com.likya.myra.test.xmlsamples;

import org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil;

import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestSampleXmlUtil {

	public static void main(String[] args) {
		
//		String myraConfigDocument = SampleXmlUtil.createSampleForType(MyraConfigDocument.type);
//		System.err.println(myraConfigDocument);
//		System.err.println();
//		
//		String pinaraConfigDocument = SampleXmlUtil.createSampleForType(PinaraConfigDocument.type);
//		System.err.println(pinaraConfigDocument);
//		System.err.println();
		
		String jobListDocument = SampleXmlUtil.createSampleForType(JobListDocument.type);
		System.err.println(jobListDocument);
		System.err.println();
		
//		String simpleProperties = SampleXmlUtil.createSampleForType(SimpleProperties.type);
//		System.err.println(simpleProperties);
//		System.err.println();
//		
//		String remoteSchProperties = SampleXmlUtil.createSampleForType(RemoteSchProperties.type);
//		System.err.println(remoteSchProperties);
	
	}

}
