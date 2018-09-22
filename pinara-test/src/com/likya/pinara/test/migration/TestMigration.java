package com.likya.pinara.test.migration;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import com.likya.myra.commons.utils.XMLValidations;
import com.likya.pinara.utils.PersistApi;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class TestMigration {

	public static void main(String[] args) {

		try {
			String jobListDocument = PersistApi.deserializeAsFlat("/Users/serkan/git/pinaragroup/Pinara-0.0.1/data/senaryo.pnr");

			jobListDocument = jobListDocument.replace("bornedPlannedTime", "jsScheduledTime");

			XmlObject xmlObject = XmlObject.Factory.parse(jobListDocument);

			xmlObject = removeStopTimeWithSelect(xmlObject);

			JobListDocument jobListDoc = JobListDocument.Factory.parse(xmlObject.toString());

			XMLValidations.validateWithXSDAndLog(Logger.getLogger(TestMigration.class), xmlObject);
			
			// xmlObject.execQuery("rename node $bornedPlannedTime as 'jsScheduledTime'");

			// XmlCursor xmlCursor = xmlObject.newCursor();
			// boolean retValue = xmlCursor.toChild(new QName("http://www.likyateknoloji.com/wla-gen", "timeManagement"));

			// xmlCursor.toChild("bornedPlannedTime");
			// Node node = xmlCursor.getDomNode();
			// xmlCursor.toPrevToken();
			// Node node = xmlCursor.getDomNode();
			// node.getLocalName();
			// node.re
			// (new QName("http://www.likyateknoloji.com/wla-gen", "jsScheduledTime"));
			System.out.println("end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static XmlObject removeStopTimeWithSelect(XmlObject empDoc) {
		
//		xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" 
//		xmlns:myra="http://www.likyateknoloji.com/myra-joblist" 
//		xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" 
//		xmlns:wla="http://www.likyateknoloji.com/wla-gen" 
//		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
//		xmlns:lik="http://www.likyateknoloji.com/likya-gen">
	

		String namespaceDeclaration = "declare namespace myra-joblist='http://www.likyateknoloji.com/myra-joblist';";
		namespaceDeclaration += "declare namespace myra-stateinfo='http://www.likyateknoloji.com/myra-stateinfo';";
		namespaceDeclaration += "declare namespace myra='http://www.likyateknoloji.com/myra-joblist';";
		namespaceDeclaration += "declare namespace myra-jobprops='http://www.likyateknoloji.com/myra-jobprops';";
		namespaceDeclaration += "declare namespace wla='http://www.likyateknoloji.com/wla-gen';";
		namespaceDeclaration += "declare namespace xsi='http://www.likyateknoloji.com/http://www.w3.org/2001/XMLSchema-instance';";
		namespaceDeclaration += "declare namespace lik='http://www.likyateknoloji.com/likya-gen';";
		
	    // Insert a cursor and move it to the first element.
	    XmlCursor cursor = empDoc.newCursor();
	    cursor.toNextToken();
	    
	    cursor.getTextValue();
		
	    // Save the cursor's current location by pushing it
	    // onto a stack of saved locations.
	    cursor.push();
	    // Query for zip elements.
	    // cursor.selectPath(namespaceDeclaration + "$this/myra-joblist:jobList/myra-joblist:genericJob/myra-jobprops:management/wla:timeManagement/wla:jsScheduledTime/wla:stopTime");
	    cursor.selectPath(namespaceDeclaration + "$this//wla:jsScheduledTime/wla:stopTime");
	    
	    // Loop through the list of selections, getting the value of
	    // each element.
	    while (cursor.toNextSelection())
	    {
	        System.out.println(cursor.getTextValue());
	        cursor.removeXml();
	    }
	    // Pop the saved location off the stack.
	    cursor.pop();
	    
		
		return cursor.getObject();
		
	}

	public static XmlObject removeStopTime(XmlObject empDoc) {
		
//		xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" 
//		xmlns:myra="http://www.likyateknoloji.com/myra-joblist" 
//		xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" 
//		xmlns:wla="http://www.likyateknoloji.com/wla-gen" 
//		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
//		xmlns:lik="http://www.likyateknoloji.com/likya-gen">
	

		String namespaceDeclaration = "declare namespace myra-joblist='http://www.likyateknoloji.com/myra-joblist';";
		namespaceDeclaration += "declare namespace myra-stateinfo='http://www.likyateknoloji.com/myra-stateinfo';";
		namespaceDeclaration += "declare namespace myra='http://www.likyateknoloji.com/myra-joblist';";
		namespaceDeclaration += "declare namespace myra-jobprops='http://www.likyateknoloji.com/myra-jobprops';";
		namespaceDeclaration += "declare namespace wla='http://www.likyateknoloji.com/wla-gen';";
		namespaceDeclaration += "declare namespace xsi='http://www.likyateknoloji.com/http://www.w3.org/2001/XMLSchema-instance';";
		namespaceDeclaration += "declare namespace lik='http://www.likyateknoloji.com/likya-gen';";
		
		// A cursor instance to query with.
		XmlCursor empCursor = empDoc.newCursor();
		empCursor.toNextToken();
		
		// The expression: Get the  elements with  elements whose
		// value is "WA".
		// String queryExpression = "for $e in $this/jobList/genericJob " + "let $s := $e/myra-jobprops:management/wla:timeManagement/wla:jsScheduledTime " + "return $e//wla:stopTime";

		String queryExpression = "for $e in $this/myra-joblist:jobList/myra-joblist:genericJob " + "return $e/myra-jobprops:management/wla:timeManagement/wla:jsScheduledTime/wla:stopTime";
		
		// Execute the query. Results, if any, will be available at 
		// the position of the resultCursor in a new XML document.
		XmlCursor resultCursor = empCursor.execQuery(namespaceDeclaration + queryExpression);

		System.out.println("The query results, element copies made " + "from the received document: \n");
		System.out.println(resultCursor.getObject().toString() + "\n");

		if (resultCursor.toFirstChild()) {
			do {
				XmlCursor editCursor = resultCursor.newCursor();
				editCursor.removeXml();
			} while (resultCursor.toNextSibling());
			
			resultCursor.toStartDoc();
			System.out.println("The query results after changes: \n");
			System.out.println(resultCursor.getObject().toString() + "\n");
		}
		
		return resultCursor.getObject();
		
	}

	public static boolean updateWorkPhone(XmlObject empDoc) {

		String namespaceDeclaration = "declare namespace xq='http://xmlbeans.apache.org/samples/xquery/employees';";

		boolean hasResults = false;

		// A cursor instance to query with.
		XmlCursor empCursor = empDoc.newCursor();
		empCursor.toNextToken();

		// The expression: Get the  elements with  elements whose
		// value is "WA".
		String queryExpression = "for $e in $this/xq:employees/xq:employee " + "let $s := $e/xq:address/xq:state " + "where $s = 'WA' " + "return $e//xq:phone[@location='work']";

		// Execute the query. Results, if any, will be available at 
		// the position of the resultCursor in a new XML document.
		XmlCursor resultCursor = empCursor.execQuery(namespaceDeclaration + queryExpression);

		System.out.println("The query results, element copies made " + "from the received document: \n");
		System.out.println(resultCursor.getObject().toString() + "\n");

		// If there are results, the results will be children of the fragment root
		// where the new cursor is positioned. This statement tests for children
		// and moves the cursor if to the first if it exists.
		if (resultCursor.toFirstChild()) {
			hasResults = true;
			// Use the cursor to loop through the results, printing each sibling
			// element returned by the query.
			int i = 0;
			do {
				// Change the phone numbers.
				XmlCursor editCursor = resultCursor.newCursor();
				editCursor.toLastAttribute();
				editCursor.toNextToken();
				editCursor.removeXml();
				editCursor.insertChars("(206)555-1234");
			} while (resultCursor.toNextSibling());

			resultCursor.toStartDoc();
			System.out.println("The query results after changes: \n");
			System.out.println(resultCursor.getObject().toString() + "\n");

			System.out.println("The received document -- note that it is unchanged. " + "Changes were made to the copy created by the execQuery method. \n");
			System.out.println(empDoc + "\n");
		}
		return hasResults;
	}

}
