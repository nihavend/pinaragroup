package com.likya.myra.test.modules.shiro;

import java.util.Locale;

import org.apache.shiro.authz.Permission;

public class JobPermission implements Permission {

	public enum Permissions {
		MONITOR("monitor"), MANAGE("manage"), MODIFY("modify");
		
		private String value;

		private Permissions(String value) {
			this.value = value;
		}

		public String getValue() {
			return value.toLowerCase();
		}
		
		public String toString() {
			return value.toString().toLowerCase(Locale.ENGLISH);
		}
	}

	String jobId;
	
	public JobPermission(String jobId, Permissions permissions) {
		super();
		this.jobId = jobId;
	}
	
	@Override
	public boolean implies(Permission p) {
//		if (currentUser.isPermitted(printPermission)) {
//		    //show the Print button
//		} else {
//		    //don't show the button?  Grey it out?
//		}
		return false;
	}



}
