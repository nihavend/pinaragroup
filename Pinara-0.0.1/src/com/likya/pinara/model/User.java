package com.likya.pinara.model;

import java.io.Serializable;

import com.likya.pinara.utils.PasswordService;

public class User implements Serializable {
	
	private static final long serialVersionUID = -5429269806185912759L;
	
	private int id;
	private RoleInfo roleInfo;
	private StatuInfo statuInfo;
	
	private String username;
	private String password;
	
	public enum RoleInfo {

		ADMIN(10), OPERATION(20), VIEW(30);

		public int value;

		private RoleInfo(int value) {
			this.value = value;
		}

	}

	public enum StatuInfo {

		ACTIVE(10), DEACTIVE(20);

		public int value;

		private StatuInfo(int value) {
			this.value = value;
		}

	}
	
	public User(RoleInfo roleInfo, StatuInfo statuInfo, String username, String password) throws Exception {
		super();
		this.roleInfo = roleInfo;
		this.statuInfo = statuInfo;
		this.username = username;
		this.password = PasswordService.encrypt(password);
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

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public StatuInfo getStatuInfo() {
		return statuInfo;
	}

	public void setStatuInfo(StatuInfo statuInfo) {
		this.statuInfo = statuInfo;
	}
}
