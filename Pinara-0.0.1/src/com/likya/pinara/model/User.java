package com.likya.pinara.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -5429269806185912759L;
	
	private int id;
	private Role role;
	
	private String username;
	private String password;
	
	private String viewRoleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getViewRoleId() {
		return viewRoleId;
	}

	public void setViewRoleId(String viewRoleId) {
		this.viewRoleId = viewRoleId;
	}
}
