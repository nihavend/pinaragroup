package com.likya.myra.test.deps;

import java.io.File;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestJobListEdit extends TestBase {

	static String pathName = "/Users/serkan/git/localgit/TL-2.0.0-Test/src/com/likya/myra/test/deps/";

	static String fileNames[] = { "5.xml", "10.xml", "50.xml", "100.xml", "200.xml", "400.xml", "500.xml", "800.xml", "1600.xml", "3200.xml", "5000.xml" };

	static int idx = 2;

	public static void main(String[] args) {
		try {
			modifyXPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveFile() throws Exception {

		try {

			JobListDocument jobListDocument = getJobList(pathName, fileNames[idx]);

			File file = new File(pathName + "XB_" + fileNames[idx]);
			long startTime = System.currentTimeMillis();
			jobListDocument.save(file);
			long duration = System.currentTimeMillis() - startTime;
			System.err.print(file.getName() + "	>> is saved in " + duration + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void modifyXPath() throws Exception {

		String queryExpression = 
		"declare namespace xmlns='http://www.likyateknoloji.com/myra-joblist';" + "declare namespace xsi='http://www.w3.org/2001/XMLSchema-instance';" +
		"declare namespace jsdl='http://schemas.ggf.org/jsdl/2005/11/jsdl'; declare namespace myra='http://www.likyateknoloji.com/myra-jobprops';" + 
		"declare namespace wla='http://www.likyateknoloji.com/wla-gen;' declare namespace lik='http://www.likyateknoloji.com/likya-gen';" + 
		"declare namespace myra1='http://www.likyateknoloji.com/myra-stateinfo';";
		/*
		[@id=0]
		*/
		
		queryExpression = queryExpression + "$this/myra:genericJob";

		// String queryExpression =
		// "declare namespace xq='http://xmlbeans.apache.org/samples/xquery/employees';" +
		// "$this/xq:employees/xq:employee/xq:phone[contains(., '(206)')]";
		// 
		// Retrieve the matching phone elements and assign the results to the corresponding
		// generated type.
		// PhoneType[] phones = (PhoneType[])empDoc.selectPath(queryExpression);

		JobListDocument jobListDocument = getJobList(pathName, fileNames[idx]);

		AbstractJobType abstractJobType[] = (AbstractJobType[]) jobListDocument.selectPath(queryExpression);

		// Loop through the results, printing the value of the phone element.
		for (int i = 0; i < abstractJobType.length; i++) {
			System.out.println(abstractJobType[i].getId());
		}
	}

	public static void modifyDoc() throws XmlException {

		XmlOptions saveOptions = new XmlOptions().setSavePrettyPrint().setSavePrettyPrintIndent(2);

		XmlObject xobj = XmlObject.Factory.parse("");
		XmlCursor cur = null;
		try {
			cur = xobj.newCursor();
			// We could use the convenient xobj.selectPath() or cur.selectPath()
			// to position the cursor on the <person> element, but let's use the
			// cursor's toChild() instead.
			cur.toChild("rootNode");
			cur.toChild("person");
			// Move to </person> end element.
			cur.toEndToken();
			// Start a new <phoneNumbers> element
			cur.beginElement("phoneNumbers");
			// Start a new <work> element
			cur.beginElement("work");
			cur.insertChars("555-555-5555");
			// Move past the </work> end element
			cur.toNextToken();
			// Or insert a new element the easy way in one step...
			cur.insertElementWithText("home", "555-555-5555");
		} finally {
			if (cur != null)
				cur.dispose();
		}

		System.out.println(xobj.xmlText(saveOptions));
	}
}
