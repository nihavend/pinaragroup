package com.likya.myra.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.likya.myra.test.deps.TestBase;
import com.likya.myra.test.shiro.JobPermission.Permissions;

public class CustomPermissionTest extends TestBase {

	static Logger log = LoggerFactory.getLogger("test");

	public static void main(String[] args) {

		// initShiro();

		// testCustomPermiisionObject();

		testMyraPerm();
	}

	public static void initShiro(String confFile, UsernamePasswordToken token) {

		log.info("My First Apache Shiro Application");

		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(confFile);
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

		SecurityUtils.setSecurityManager(securityManager);

		// get the currently executing user:
		Subject currentUser = SecurityUtils.getSubject();

		// let's login the current user so we can check against roles and permissions:
		if (!currentUser.isAuthenticated()) {
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				log.info("There is no user with username of " + token.getPrincipal());
			} catch (IncorrectCredentialsException ice) {
				log.info("Password for account " + token.getPrincipal() + " was incorrect!");
			} catch (LockedAccountException lae) {
				log.info("The account for username " + token.getPrincipal() + " is locked.  " + "Please contact your administrator to unlock it.");
			}
			// ... catch more exceptions here (maybe custom ones specific to your application?
			catch (AuthenticationException ae) {
				ae.printStackTrace();
				System.exit(-1);
			}
		}

	}

	private static void testCustomPermiisionObject() {

		Subject currentUser = SecurityUtils.getSubject();

		JobPermission jobPermission = new JobPermission("0", Permissions.MONITOR);

		if (currentUser.isPermitted(jobPermission)) {
			log.info("You may use a lightsaber ring.  Use it wisely.");
		} else {
			log.info("Sorry, lightsaber rings are for schwartz masters only.");
		}
	}

	private static void testMyraPerm() {

		String iniFile = "file:/Users/serkan/git/localgit/TL-2.0.0-Test/conf/mshiro.ini"; 
		UsernamePasswordToken token = new UsernamePasswordToken("myra", "myra");
		
		initShiro(iniFile, token);
		
		Subject currentUser = SecurityUtils.getSubject();

		String permDomain = "jobgroup";
		
		String jobGroupName = "backup";
		
		String action = Permissions.MONITOR.toString();
		
		String permStr = permDomain + ":" + jobGroupName + ":" + action;

		// Checking for permission to monitor the specific job group
		if (currentUser.isPermitted(permStr)) {
			log.info("You are permitted to '" + action + "' the domain " + permDomain + " with job group '" + jobGroupName + "'.  " + "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to '" + action + "' the domain " + permDomain + " with job group '" + jobGroupName + "'.");
		}
		
		
		permStr = "mine:*";

		// Checking for permission for own job
		if (currentUser.isPermitted(permStr)) {
			log.info("You are permitted to do anything on the items you own ! " + "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to do anything on the items you own !.");
		}
		
		//all done - log out!
		currentUser.logout();

		System.exit(0);

	}
}
