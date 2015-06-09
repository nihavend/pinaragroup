package com.likya.pinara.utils.xml.mappers;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.pinara.model.User;

public class UserMapper {

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

		xmlCursor.beginElement("roleinfo");
		xmlCursor.insertChars("" + user.getRoleInfo());
		xmlCursor.toNextToken();

	}
}
