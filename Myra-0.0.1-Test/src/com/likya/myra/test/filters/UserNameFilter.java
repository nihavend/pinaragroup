package com.likya.myra.test.filters;

import org.apache.commons.collections.Predicate;

class UserNameFilter implements Predicate {
	String userName;

	public UserNameFilter(String userName) {
		super();
		this.userName = userName;
	}

	public boolean evaluate(Object object) {
		if (userName.equals(object)) {
			return true;
		}
		return false;
	}
}