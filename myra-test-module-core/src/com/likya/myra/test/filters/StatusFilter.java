package com.likya.myra.test.filters;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;

public class StatusFilter extends BaseFilter {

	StatusName.Enum tmpStatusName;
	
	public StatusFilter(StatusName.Enum tmpStatusName) {
		super();
		this.tmpStatusName = tmpStatusName;
	}

	protected boolean helper(AbstractJobType abstractJobType) {
		LiveStateInfo liveStateInfo = abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
		StatusName.Enum innerStatusName = liveStateInfo.getStatusName();
		return tmpStatusName.intValue() == innerStatusName.intValue();
	}

}
