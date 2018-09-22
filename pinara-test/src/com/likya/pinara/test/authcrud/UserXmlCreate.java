package com.likya.pinara.test.authcrud;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.likya.pinara.model.User;
import com.likya.pinara.model.User.RoleInfo;
import com.likya.pinara.model.User.StatuInfo;
import com.likya.pinara.utils.xml.mappers.UserMapper;


public class UserXmlCreate extends TestCase {
	
	private User sampleUser;
	
	protected void setUp() {
		try {
			sampleUser = new User(RoleInfo.ADMIN, StatuInfo.ACTIVE, "pinara", "pinara");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void testCreateUserXml() {
		
		String xmlStrUser = UserMapper.getMapped(sampleUser);
		
		Assert.assertNotNull(xmlStrUser);
		
	}

}
