package com.likya.myra.test.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyraFileRealm extends SimpleAccountRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.err.println("");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		String username = upToken.getUsername();

		// Null username is invalid  
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		AuthenticationInfo authenticationInfo = null;
		
		String password = "password";

		authenticationInfo = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());

		return authenticationInfo;
	}

}
