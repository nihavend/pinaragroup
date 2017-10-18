package com.likya.pinara.test.migration;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

public class TestXPath {

	public static void main(String[] args) throws XmlException {

		TestXPath testXPath = new TestXPath();
		
		String xmlStr = testXPath.myXml();
		
		XmlObject empDoc = XmlObject.Factory.parse(xmlStr);
		
		testXPath.updateWorkPhone(empDoc);
		
	}

	public void test() throws XmlException {
		
		String queryExpression = "declare namespace xq='http://xmlbeans.apache.org/samples/xquery/employees';" + "$this/xq:employees/xq:employee/xq:phone[contains(., '(206)')]";

		// Retrieve the matching phone elements and assign the results to the corresponding
		// generated type.

		// PhoneType[] phones = (PhoneType[])empDoc.selectPath(queryExpression);

		// Loop through the results, printing the value of the phone element.

		// for (int i = 0; i < phones.length; i++)
		// {
		//     System.out.println(phones[i].stringValue());
		// }
		
	}

	public void printZipsAndWorkPhones(XmlObject xml) {
		// Declare the namespace that will be used.
		String xqNamespace = "declare namespace xq='http://xmlbeans.apache.org/samples/xquery/employees';";

		// Insert a cursor and move it to the first element.
		XmlCursor cursor = xml.newCursor();
		cursor.toFirstChild();

		// Save the cursor's current location by pushing it
		// onto a stack of saved locations.
		cursor.push();
		// Query for zip elements.
		cursor.selectPath(xqNamespace + "$this//xq:zip");

		// Loop through the list of selections, getting the value of
		// each element.
		while (cursor.toNextSelection()) {
			System.out.println(cursor.getTextValue());
		}
		// Pop the saved location off the stack.
		cursor.pop();
		// Query again from the top, this time for work phone numbers.
		cursor.selectPath(xqNamespace + "$this//xq:phone[@location='work']");

		// Move the cursor to the first selection, then print that element's
		// value.
		cursor.toNextSelection();
		System.out.println(cursor.getTextValue());
		// Dispose of the cursor.
		cursor.dispose();
	}

	public boolean collectZips(XmlObject empDoc) {
		String namespaceDeclaration = "declare namespace xq='http://xmlbeans.apache.org/samples/xquery/employees';";
		// The query is designed to return results, so return
		// true if it does.
		boolean hasResults = false;

		// The expression: Get the <zip> elements and return them as children 
		// of a new <zip-list> element.
		String queryExpression = "let $e := $this/xq:employees " + "return " + "<zip-list> " + "{for $z in $e/xq:employee/xq:address/xq:zip " + "return $z} " + "</zip-list>";

		// Execute the query. Results will be copies of the XML queried against,
		// stored as members of an XmlObject array.
		XmlObject[] results = empDoc.execQuery(namespaceDeclaration + queryExpression);

		// Print the results.
		if (results.length > 0) {
			hasResults = true;
			System.out.println("The query results: \n");
			System.out.println(results[0].toString() + "\n");
		}
		return hasResults;
	}

	public boolean updateWorkPhone(XmlObject empDoc) {
		
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
	
	public String myXml() {
		
		String str = 
		"<xq:employees xmlns:xq=\"http://xmlbeans.apache.org/samples/xquery/employees\">" +
		    "<xq:employee>" +
		        "<xq:name>Fred Jones</xq:name>" +
		        "<xq:address location=\"home\">" +
		            "<xq:street>900 Aurora Ave.</xq:street>" +
		            "<xq:city>Seattle</xq:city>" +
		            "<xq:state>WA</xq:state>" +
		            "<xq:zip>98115</xq:zip>" +
		        "</xq:address>" +
		        "<xq:address location=\"work\">" +
		            "<xq:street>2011 152nd Avenue NE</xq:street>" +
		            "<xq:city>Redmond</xq:city>" +
		            "<xq:state>WA</xq:state>" +
		            "<xq:zip>98052</xq:zip>" +
		        "</xq:address>" +
		        "<xq:phone location=\"work\">(425)555-5665</xq:phone>" +
		        "<xq:phone location=\"home\">(206)555-5555</xq:phone>" +
		        "<xq:phone location=\"mobile\">(206)555-4321</xq:phone>" +
		    "</xq:employee>" +
	    "</xq:employees>";
	
	    
	    return str;
	}

}
