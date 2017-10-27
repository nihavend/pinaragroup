package com.likya.myra.test.helpers;

import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.MyraDateUtils;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosDocument;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class LiveStateInfosGenerator {

	public static XmlObject generate(int quantity) throws Exception {

		boolean debug = true;

		LiveStateInfosDocument liveStateInfosDocument = LiveStateInfosDocument.Factory.newInstance();
		LiveStateInfosType liveStateInfosType = liveStateInfosDocument.addNewLiveStateInfos();

		int counter = 0;
		// quantity <= 0 ? 1:quantity;
		
		if(quantity < 0) quantity = 0;
		while (counter ++ < quantity) {

			LiveStateInfo liveStateInfo = liveStateInfosType.addNewLiveStateInfo();

			liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
			liveStateInfo.setStateName(StateName.PENDING);
			liveStateInfo.setSubstateName(SubstateName.READY);
			liveStateInfo.setStatusName(StatusName.BYTIME);

		}

		if (debug) {
			System.err.println(liveStateInfosType.toString());

			System.err.println("Valid = " + liveStateInfosType.validate());

		}

		return liveStateInfosType;
	}

}
