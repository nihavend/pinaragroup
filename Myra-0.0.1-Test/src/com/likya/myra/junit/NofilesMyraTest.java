package com.likya.myra.junit;

import org.junit.Before;
import org.junit.Test;

import com.likya.myra.jef.core.CoreFactory;
import com.likya.myra.jef.core.ManagementOperations;
import com.likya.myra.test.helpers.TestOutput;

public class NofilesMyraTest {

	@Before
	public void setUp() {
		final TestOutput testOutput = new TestOutput();

		CoreFactory coreFactory = (CoreFactory) CoreFactory.getInstance(testOutput);

		ManagementOperations managementOperations = coreFactory.getManagementOperations();
		try {
			managementOperations.start();
		} catch (Throwable e) {
			e.printStackTrace();
			return;
		}

		
	}

	@Test
    public void addJobToQueue() {
		// coreFactory.getJobOperations().addJob(abstractJobType);
    }
}
