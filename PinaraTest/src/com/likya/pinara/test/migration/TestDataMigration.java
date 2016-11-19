package com.likya.pinara.test.migration;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.junit.Assert;

import com.likya.myra.commons.utils.XMLValidations;
import com.likya.pinara.utils.DataMigration;
import com.likya.pinara.utils.PersistApi;

public class TestDataMigration extends TestCase {

	public void testMigrate_null_to_0_9_1() {
		
		String jobListDocumentStr = null;
		
		try {
			jobListDocumentStr = PersistApi.deserializeAsFlat("/Users/serkan/git/pinaragroup/PinaraTest/data/nullV/senaryo.pnr");
		} catch (Exception e) {
			e.printStackTrace();
		}

		XmlObject xmlObject = null;
		try {
			xmlObject = DataMigration.migrate_null_to_0_9_1(jobListDocumentStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean reVAlue = XMLValidations.validateWithXSDAndLog(Logger.getLogger(TestMigration.class), xmlObject);
		
		Assert.assertTrue(reVAlue);
	}

}
