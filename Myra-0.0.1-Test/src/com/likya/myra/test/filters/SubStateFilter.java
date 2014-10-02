package com.likya.myra.test.filters;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class SubStateFilter extends BaseFilter {

	SubstateName.Enum tmpSubStateName;
	
	public SubStateFilter(SubstateName.Enum tmpSubStateName) {
		super();
		this.tmpSubStateName = tmpSubStateName;
	}
	
	protected boolean helper(AbstractJobType abstractJobType) {
		LiveStateInfo liveStateInfo = abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
		SubstateName.Enum innerSubStateName = liveStateInfo.getSubstateName();
		return tmpSubStateName.intValue() == innerSubStateName.intValue();
	}

}
