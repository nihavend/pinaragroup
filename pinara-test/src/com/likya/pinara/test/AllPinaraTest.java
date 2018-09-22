package com.likya.pinara.test;


import junit.framework.Test;
import junit.framework.TestSuite;

import com.likya.pinara.test.authcrud.AuthCrud;
import com.likya.pinara.test.authcrud.AuthCrudOnRest;

public class AllPinaraTest {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	
	public static Test suite() {
		
		TestSuite testSuite = new TestSuite("Pinara Tests");
		testSuite.addTestSuite(AuthCrud.class);
		testSuite.addTestSuite(AuthCrudOnRest.class);
		
		return testSuite;
	}
}
