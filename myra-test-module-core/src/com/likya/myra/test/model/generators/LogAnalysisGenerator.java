package com.likya.myra.test.model.generators;

import java.math.BigInteger;

import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;
import com.likya.xsd.myra.model.wlagen.ActionDocument.Action;
import com.likya.xsd.myra.model.wlagen.CodeType;
import com.likya.xsd.myra.model.wlagen.ContentDocument.Content;
import com.likya.xsd.myra.model.wlagen.DirectionType;
import com.likya.xsd.myra.model.wlagen.EventDocument.Event;
import com.likya.xsd.myra.model.wlagen.FindWhatDocument.FindWhat;
import com.likya.xsd.myra.model.wlagen.ForcedResultDocument.ForcedResult;
import com.likya.xsd.myra.model.wlagen.LogAnalysisDocument;
import com.likya.xsd.myra.model.wlagen.LogAnalysisDocument.LogAnalysis;
import com.likya.xsd.myra.model.wlagen.ModeType;
import com.likya.xsd.myra.model.wlagen.ThencaseDocument.Thencase;

public class LogAnalysisGenerator {
	
	public static LogAnalysis generate() throws Exception {
		
		LogAnalysisDocument logAnalysisDocument = LogAnalysisDocument.Factory.newInstance();
		
		LogAnalysis logAnalysis = logAnalysisDocument.addNewLogAnalysis();
		
		logAnalysis.setId(new BigInteger("100"));
		logAnalysis.setActive(true);
		
		FindWhat findWhat = logAnalysis.addNewFindWhat();
		
		findWhat.setDirection(DirectionType.UP);
		findWhat.setMatchCase(false);
		findWhat.setMatchWholeWordOnly(false);
		findWhat.setMode(ModeType.NORMAL);
		findWhat.setStringValue("Bekleme");
		
		Action action = logAnalysis.addNewAction();
		
		Thencase thencase = action.addNewThencase();
		
		Event event = thencase.addNewEvent();
		
		event.setCode(CodeType.EMAIL);
		event.setId(new BigInteger("100"));
		
		Content content = event.addNewContent();
		
		content.setStringValue("");
		
		ForcedResult forcedResult = thencase.addNewForcedResult();
		
		forcedResult.setActive(true);

		LiveStateInfo liveStateInfo = forcedResult.addNewLiveStateInfo();
		liveStateInfo.setStateName(StateName.FINISHED);
		liveStateInfo.setSubstateName(SubstateName.COMPLETED);
		liveStateInfo.setStatusName(StatusName.FAILED);


		return logAnalysis;
	}
}
