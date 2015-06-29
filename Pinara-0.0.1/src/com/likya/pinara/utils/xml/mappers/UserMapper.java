package com.likya.pinara.utils.xml.mappers;

import java.util.ArrayList;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.pinara.model.User;
import com.likya.pinara.model.User.RoleInfo;

public class UserMapper {

	public static String getMapped(ArrayList<User> userList) {
	
		String retValue = "";
		
		XmlObject xmlObject = XmlObject.Factory.newInstance();
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();
		
		xmlCursor.beginElement("userList");
		
		for(User user : userList) {
			addUserInfo(xmlCursor, user);
		}
		
		try {
			XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);
			retValue = xmlObject.xmlText(saveOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retValue;
		
	}
	
	public static String getMapped(User user) {

		String retValue = "";

		XmlObject xmlObject = XmlObject.Factory.newInstance();
		XmlCursor xmlCursor = xmlObject.newCursor();
		xmlCursor.toEndToken();

		addUserInfo(xmlCursor, user);
		
		if (xmlCursor != null) {
			xmlCursor.dispose();
		}

		try {
			XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);
			retValue = xmlObject.xmlText(saveOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retValue;
	}

	protected static void addUserInfo(XmlCursor xmlCursor, User user) {

		xmlCursor.beginElement("userInfo");

		xmlCursor.beginElement("id");
		xmlCursor.insertChars("" + user.getId());
		xmlCursor.toNextToken();

		xmlCursor.beginElement("username");
		xmlCursor.insertChars("" + user.getUsername());
		xmlCursor.toNextToken();
		
		xmlCursor.beginElement("password");
		xmlCursor.insertChars("" + user.getPassword());
		xmlCursor.toNextToken();

		xmlCursor.beginElement("roleinfo");
		xmlCursor.insertChars("" + user.getRoleInfo());
		xmlCursor.toNextToken();

	}
	
	public static User parseUser(String userXml) {
		
		String myraGenericJob = userXml.split("<userInfo>")[1].split("</userInfo>")[0];
		
		String username = myraGenericJob.split("<username>")[1].split("</username>")[0];
		String password = "";
		if(myraGenericJob.contains("<password>")) {
			password =  myraGenericJob.split("<password>")[1].split("</password>")[0];
		}
		String roleinfo = myraGenericJob.split("<roleinfo>")[1].split("</roleinfo>")[0];
		
		User testUser = null;
		try {
			testUser = new User(RoleInfo.valueOf(roleinfo), username, password);
			String id = "";
			if(myraGenericJob.contains("<id>")) {
				id =  myraGenericJob.split("<id>")[1].split("</id>")[0];
				testUser.setId(Integer.parseInt(id));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return testUser;
		
	}
	
	public static String [] parsePassChangeDataWithId(String xmlData) {
		
		String passData = xmlData.split("<xmldata>")[1].split("</xmldata>")[0];
		
		String id = passData.split("<id>")[1].split("</id>")[0];
		String oldpass = passData.split("<oldpass>")[1].split("</oldpass>")[0];
		String newpass = passData.split("<newpass>")[1].split("</newpass>")[0];

		String retArray [] = new String[3];
		
		retArray[0] = id;
		retArray[1] = oldpass;
		retArray[2] = newpass;
		
		return retArray;
		
	}
	
	public static String [] parsePassChangeDataWithUsername(String xmlData) {
		
		String passData = xmlData.split("<xmldata>")[1].split("</xmldata>")[0];
		
		String id = passData.split("<username>")[1].split("</username>")[0];
		String oldpass = passData.split("<oldpass>")[1].split("</oldpass>")[0];
		String newpass = passData.split("<newpass>")[1].split("</newpass>")[0];

		String retArray [] = new String[3];
		
		retArray[0] = id;
		retArray[1] = oldpass;
		retArray[2] = newpass;
		
		return retArray;
		
	}
}
