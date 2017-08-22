package com.likya.myra.test.modules.odi.xmlsamples;

import org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil;

import com.likya.xsd.myra.ext.odi.OdiExtParamsDocument;

public class OdiExtSample {

	public static void main(String[] args) {
		
		String odiExtParamsDocument = SampleXmlUtil.createSampleForType(OdiExtParamsDocument.type);
		
		System.err.println(odiExtParamsDocument);
		System.err.println();
	
	}

}
