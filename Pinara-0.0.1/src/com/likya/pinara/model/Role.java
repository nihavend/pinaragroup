/*
 * TlosFaz_V2.0_Model
 * com.likya.tlos.model.auth : Role.java
 * @author Serkan Tas
 * Tarih : Jul 20, 2009 11:23:45 AM
 */

package com.likya.pinara.model;

import java.io.Serializable;

public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Role(String roleId) {
		super();
		this.roleId = roleId;
	}

	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
