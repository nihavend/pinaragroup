package com.likya.myra.junit;

import com.likya.myra.test.TestDependencyChain;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllMyraTest {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	
	public static Test suite() {
		
		TestSuite testSuite = new TestSuite("Myra Tests");
		testSuite.addTestSuite(TestDependencyChain.class);
		
		return testSuite;
	}
}
