package com.likya.myra.test.filters;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class StateFilter extends BaseFilter {

	StateName.Enum tmpStateName;
	
	public StateFilter(StateName.Enum tmpStateName) {
		super();
		this.tmpStateName = tmpStateName;
	}
	
	protected boolean helper(AbstractJobType abstractJobType) {
		LiveStateInfo liveStateInfo = abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
		StateName.Enum innerStateName = liveStateInfo.getStateName();
		return tmpStateName.intValue() == innerStateName.intValue();
	}

}
