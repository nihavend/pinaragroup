package com.likya.myra.test.modules.shiro;

import java.util.Collection;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.likya.myra.test.deps.TestBase;
import com.likya.myra.test.modules.shiro.JobPermission.Permissions;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestAuthPredicateUtils extends TestBase {

	public static void main(String[] args) {

		initShiro();

		try {

			String pathName = "/Users/serkan/git/localgit/TL-2.0.0-Test/src/com/likya/myra/test/deps/";

			String fileNames[] = { "5.xml", "10.xml", "50.xml", "100.xml", "200.xml", "400.xml", "500.xml", "800.xml", "1600.xml", "3200.xml", "5000.xml" };

			for (String fileName : fileNames) {

				JobListDocument jobListDocument = getJobList(pathName, fileName, true);

				//				System.out.println(">> Checking" + fileName + "...");
				//				long startTime = System.currentTimeMillis();
				//				boolean hasCycle = DependencyOperations.checkCyclicDependency(null, toAbstractJobMap(jobListDocument));
				//				long duration = System.currentTimeMillis() - startTime;
				//				System.out.println(">> checked in " + duration + " ms");
				//				
				//				if(hasCycle) { 
				//					System.out.println("Has Cycle !!!");
				//				}

				Predicate authorized[] = authorizedViewPredicate();

				// Predicate predicate = PredicateUtils.anyPredicate(authorized);
				Predicate predicate = PredicateUtils.allPredicate(authorized);

				Collection<AbstractJobType> collection = toAbstractJobMap(jobListDocument).values();

				System.err.println("Size of list : " + collection.size());

				@SuppressWarnings("unchecked")
				Collection<AbstractJobType> filteredList = CollectionUtils.select(collection, predicate);

				System.err.println("Size of filtered list : " + filteredList.size());

				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Predicate[] authorizedViewPredicate() {

		Predicate[] predicateAuthorized = new Predicate[] { new Predicate() {
			public boolean evaluate(Object abtsractJobType) {
				Subject currentUser = SecurityUtils.getSubject();
				// JobPermission jobPermission = new JobPermission(((AbstractJobType)abtsractJobType).getId(), Permissions.MONITOR);
				String permTxt = "job" + ":" + ((AbstractJobType) abtsractJobType).getId() + ":" + Permissions.MONITOR.toString().toLowerCase(Locale.ENGLISH);
				System.err.println("permText : " + permTxt);
				if (currentUser.isPermitted(permTxt /*jobPermission*/)) {
					return true;
				} else {
					return false;
				}
			}
		},

		new Predicate() {
			public boolean evaluate(Object abtsractJobType) {
				// Subject currentUser = SecurityUtils.getSubject();
				// JobPermission jobPermission = new JobPermission(((AbstractJobType)abtsractJobType).getId(), Permissions.MONITOR);
				String ownerId = "" + ((AbstractJobType) abtsractJobType).getBaseJobInfos().getUserId();
				// currentUser.getPrincipal().getUserId();
				if (ownerId.equals("1")) {
					return true;
				} else {
					return false;
				}
			}
		} };

		return predicateAuthorized;

	}

	public static void initShiro() {

		Logger log = LoggerFactory.getLogger("test");

		log.info("My First Apache Shiro Application");

		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// get the currently executing user:
		Subject currentUser = SecurityUtils.getSubject();

		// Do some stuff with a Session (no need for a web or EJB container!!!)
		Session session = currentUser.getSession();
		session.setAttribute("someKey", "aValue");
		String value = (String) session.getAttribute("someKey");
		if (value.equals("aValue")) {
			log.info("Retrieved the correct value! [" + value + "]");
		}

		// let's login the current user so we can check against roles and permissions:
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken("serkan", "serkan");
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
				//unexpected condition?  error?
			}
		}

		//say who they are:
		//print their identifying principal (in this case, a username):
		log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

		//test a role:
		if (currentUser.hasRole("schwartz")) {
			log.info("May the Schwartz be with you!");
		} else {
			log.info("Hello, mere mortal.");
		}

		//test a typed permission (not instance-level)
		if (currentUser.isPermitted("lightsaber:weild")) {
			log.info("You may use a lightsaber ring.  Use it wisely.");
		} else {
			log.info("Sorry, lightsaber rings are for schwartz masters only.");
		}

		//a (very powerful) Instance Level permission:
		if (currentUser.isPermitted("winnebago:drive:eagle5")) {
			log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " + "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}

		//a (very powerful) Instance Level permission:

		if (currentUser.isPermitted("job:0:monitor")) {
			log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " + "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
		}

		JobPermission jobPermission = new JobPermission("0", Permissions.MONITOR);
		// String permTxt = SecurityUtils.getSubject().getPrincipal() + ":" + jobPermission.jobId + ":" + Permissions.MONITOR.toString().toLowerCase(Locale.ENGLISH);
		// System.err.println("permText : " + permTxt);

		if (currentUser.isPermitted(jobPermission)) {
			log.info("You are permitted to '" + Permissions.MONITOR.toString().toLowerCase(Locale.ENGLISH) + "' the " + jobPermission.getClass().getName() + " with (id) '" + jobPermission.jobId + "'.  " + "Here are the keys - have fun!");
		} else {
			log.info("Sorry, you aren't allowed to '" + Permissions.MONITOR.toString().toLowerCase(Locale.ENGLISH) + "' the " + jobPermission.getClass().getName() + " with (id) '" + jobPermission.jobId + "'.");
		}

		//all done - log out!
		// currentUser.logout();

		// System.exit(0);
	}
}
