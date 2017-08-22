package com.likya.myra.test.helpers;


public class GlobalStateGenerator {
/*
	public static GlobalStateDefinitionDocument generate() throws Exception {

		GlobalStateDefinitionDocument globalStateDefinitionDocument = GlobalStateDefinitionDocument.Factory.newInstance();
		GlobalStateDefinition globalStateDefinition = globalStateDefinitionDocument.addNewGlobalStateDefinition();
		State state = globalStateDefinition.addNewGlobalState();
		state.setCode(1);
		state.setDesc("bir");
		state.setStateName(StateName.PENDING);
		state.setStId("1");
		
		Substate substate = state.addNewSubstate();
		substate.setCode(1);
		substate.setDesc("bir");
		substate.setSubstateName(SubstateName.COMPLETED);
		
		Status status = substate.addNewSubStateStatuses();
		status.setDesc("bir");
		status.setStatusName(StatusName.BYTIME);
		
		ReturnCodeList returnCodeList = status.addNewReturnCodeList();
		returnCodeList.setOsType(OsType.MACOS);
		ReturnCode returnCode = returnCodeList.addNewReturnCode();
		returnCode.setCode(1);
		returnCode.setDesc("Bir");
		
		XmlObject xmlObject = XmlObject.Factory.parse(globalStateDefinitionDocument.toString());

		GlobalStateDefinitionDocument globalStateDefinitionDocumentNew = GlobalStateDefinitionDocument.Factory.parse(xmlObject.toString());

		System.err.println(globalStateDefinitionDocumentNew.toString());

		System.err.println("Valid = " + globalStateDefinitionDocumentNew.validate());

		return globalStateDefinitionDocumentNew;

	}
	*/
}
