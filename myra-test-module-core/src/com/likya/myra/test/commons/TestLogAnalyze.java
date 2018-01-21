package com.likya.myra.test.commons;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.likya.myra.commons.utils.LogAnalyser;
import com.likya.myra.test.model.generators.LogAnalysisGenerator;
import com.likya.myra.test.model.generators.SimplePropertiesGenerator;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.wlagen.LogAnalysisDocument.LogAnalysis;

public class TestLogAnalyze {

	@Test
	public void evaluate() {
		try {


			AbstractJobType abstractJobType = SimplePropertiesGenerator.generate(false);
			
			abstractJobType.getBaseJobInfos().setJobLogFile("analiz.log");
			abstractJobType.getBaseJobInfos().setJobLogPath("D:\\dev\\git\\pinaragroup\\Pinara-0.0.1\\logs\\");
			
			LogAnalysis logAnalysis = LogAnalysisGenerator.generate();
			
			abstractJobType.addNewLogAnalysis().set(logAnalysis);
			
			StringBuffer logContent = new StringBuffer();

			LiveStateInfo liveStateInfo = new LogAnalyser().evaluate(abstractJobType, logContent);
			
			assertNotNull(liveStateInfo);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
