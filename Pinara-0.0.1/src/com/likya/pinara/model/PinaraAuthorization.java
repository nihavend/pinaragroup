package com.likya.pinara.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.likya.commons.utils.SortUtils;
import com.likya.pinara.utils.AuthorizationLoader;
import com.likya.pinara.utils.PasswordService;

public class PinaraAuthorization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, User> userMap = new HashMap<String, User>();
	
	/**
	 * @return user list
	 */
	public synchronized ArrayList<User> getUserList() {
		
		ArrayList<User> userList = new ArrayList<User>(userMap.values());
		
		return userList;
	}
	
	/**
	 * @param id
	 * @return user, null on error
	 */
	public synchronized User readUser(int id) {
		
		String idStr = "" + id;
		
		if(!userMap.containsKey(idStr)) {
			return null;
		}
		
		return userMap.get(idStr);
	}
	
	/**
	 * @param userName
	 * @return user, null on error
	 */
	public synchronized User readUser(String userName) {
		
		for(User user : userMap.values()) {
			if(user.getUsername().equals(userName)) {
				return user;
			}
		}
		
		return null;
	}
	
	/**
	 * @param user
	 * @return added user, null on error
	 */
	public synchronized User addUser(User user) {
		
		if(userMap.containsKey(user.getId()) || readUser(user.getUsername()) != null) {
			return null;
		}

		int maxId = 1;
		if(userMap.size() > 0) {
			String [] idArray = SortUtils.sortKeys(userMap.keySet());
			maxId = Integer.parseInt(idArray[idArray.length - 1]);
		}
		
		user.setId(maxId + 1);
		
		userMap.put("" + user.getId(), user);
		
		AuthorizationLoader.persistAuthorization(this);
		
		return user;
	}
	
	/**
	 * @param user
	 * @return updated user, null on error
	 */
	public synchronized User updateUser(User user) {
		
		String idStr = "" + user.getId();
		
		if(!userMap.containsKey(idStr)) {
			return null;
		}
		
		userMap.put(idStr, user);
		
		AuthorizationLoader.persistAuthorization(this);
		
		return user;
	}
	
	/**
	 * @param user
	 * @return deleted user, null on error
	 */
	public synchronized User deleteUser(User user) {
		
		String idStr = "" + user.getId();
		
		if(!userMap.containsKey(idStr)) {
			return null;
		}
		
		userMap.remove(idStr);
		
		AuthorizationLoader.persistAuthorization(this);
		
		return user;
	}
	
	/**
	 * @param id
	 * @return deleted user, null on error
	 */
	public synchronized User deleteUser(int id) {
		
		String idStr = "" + id;
		
		if(!userMap.containsKey(idStr)) {
			return null;
		}
		
		User user = userMap.remove(idStr);
		
		AuthorizationLoader.persistAuthorization(this);
		
		return user;
	}
	
	/**
	 * 
	 * @param id
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	
	public synchronized User changePassword(int id, String oldPass, String newPass) {

		String idStr = "" + id;

		if(!userMap.containsKey(idStr)) {
			return null;
		}
		
		User user = userMap.get(idStr);
		
		try {
			if(user.getPassword().equals(PasswordService.encrypt(oldPass))) {
				user.setPassword(newPass);
				updateUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return user; 
	}
	
	/**
	 * 
	 * @param userName
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public synchronized User changePassword(String userName, String oldPass, String newPass) {
		
		User user = readUser(userName);
	
		return changePassword(user.getId(), oldPass, newPass);
	}

	
}
