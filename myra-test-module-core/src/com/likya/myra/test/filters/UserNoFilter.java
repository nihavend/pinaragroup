package com.likya.myra.test.filters;

import com.likya.xsd.myra.model.joblist.AbstractJobType;

class UserNoFilter extends BaseFilter {
	
	int userNo;

	public UserNoFilter(int userNo) {
		super();
		this.userNo = userNo;
	}
	
	protected boolean helper(AbstractJobType abstractJobType) {
		int userId = abstractJobType.getBaseJobInfos().getUserId();
		return userNo == userId;
	}

}