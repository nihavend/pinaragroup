package com.likya.pinara.test.authcrud;

import org.junit.Assert;

import com.likya.pinara.gui.rest.RestUserOps;
import com.likya.pinara.model.User;
import com.likya.pinara.model.User.RoleInfo;

public class AuthCrudOnRest extends RestTestCaseBase implements AuthCrudInterface {

	public final static String RESTUSEROPS_CTX = pinaraUrl + "/flex/restsrv/userops/";

	private User sampleUser;
	private int recordId = 0;

	protected void setUp() {
		try {
			sampleUser = new User(RoleInfo.ADMIN, "pinara", "pinara");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSimpleGetUserList() {

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERLIST, "");

		Assert.assertNotNull(retString);

	}

	public void testSimpleReadWithId() {

		recordId = 2;

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERREAD, "id=" + recordId);

		Assert.assertNotNull(retString);

	}

	public void testSimpleReadWithUsername() {

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERREAD, "username=" + sampleUser.getUsername());

		Assert.assertNotNull(retString);
	}

	public void testSimpleAdd() {

		String xmlUser = "<userInfo><username>serkan</username><password>serkan</password><roleinfo>" + RoleInfo.ADMIN + "</roleinfo></userInfo>";

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERADD, xmlUser);

		Assert.assertNotNull(retString);

	}

	public void testSimpleUpdate() {

		recordId = 3;

		String xmlUser = "<userInfo><id>" + recordId + "</id><username>serkan</username><password>serkan</password><roleinfo>" + RoleInfo.OPERATION + "</roleinfo></userInfo>";

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERUPDATE, xmlUser);

		Assert.assertNotNull(retString);
	}

	public void testSimpleDeleteWithId() {

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_USERDELETE, "id=" + recordId);

		Assert.assertNotNull(retString);
	}

	@Override
	public void testSimpleDelete() {
		// TODO Auto-generated method stub

	}

	public void testChangePasswordWithId() {

		recordId = 3;

		String xmlData = "<xmldata><id>" + recordId + "</id><oldpass>serkan</oldpass><newpass>serkan1973</newpass></xmldata>";

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_CHANGEPASS, xmlData);

		Assert.assertNotNull(retString);
	}

	public void testChangePasswordWithUsername() {

		String xmlData = "<xmldata><username>" + sampleUser.getUsername() + "</username><oldpass>serkan</oldpass><newpass>serkan1973</newpass></xmldata>";

		String retString = httpPost(RESTUSEROPS_CTX + RestUserOps.CMD_CHANGEPASS, xmlData);

		Assert.assertNotNull(retString);
	}
}
