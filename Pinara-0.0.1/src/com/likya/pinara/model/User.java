package com.likya.pinara.model;

import java.io.Serializable;

import com.likya.pinara.utils.PasswordService;

public class User implements Serializable {
	
	private static final long serialVersionUID = -5429269806185912759L;
	
	private int id;
	private RoleInfo roleInfo;
	
	private String username;
	private String password;
	
	// private String viewRoleId;

	public enum RoleInfo {

		ADMIN(10), OPERATION(20), VIEW(20);

		public int value;

		private RoleInfo(int value) {
			this.value = value;
		}

	}
	
	public User(RoleInfo roleInfo, String username, String password) throws Exception {
		super();
		this.roleInfo = roleInfo;
		this.username = username;
		this.password = PasswordService.encrypt(password);
		// this.viewRoleId = viewRoleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = PasswordService.encrypt(password);
	}

//	public String getViewRoleId() {
//		return viewRoleId;
//	}
//
//	public void setViewRoleId(String viewRoleId) {
//		this.viewRoleId = viewRoleId;
//	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}
}
