package com.likya.myra.test.helpers;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class XMLBeansNSTester {

	private static String xmlPath = "/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/";
	
	public static void main(String[] args) throws Exception {
		
		String inXmlTxt = FileUtils.readFile(xmlPath + "1.xml").toString();
		
		// methodOne(inXmlTxt);
		// methodTwo(inXmlTxt);
		// methodTree(inXmlTxt);
		methodFour(inXmlTxt);
		
	}
	
	public static void methodFour(String inXmlTxt) throws Exception {
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(inXmlTxt);
		
		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}
		
		String lastRetString = jobListDocument.xmlText();
		/*
		<myra:jobList  
	 
	 >
	<myra:genericJob  handlerURI="com.likya.myra.jef.jobs.ExecuteInShell"
		*/
		
		
		lastRetString = lastRetString.replace("xmlns:myra=\"http://www.likyateknoloji.com/myra-joblist\"", "");
		lastRetString = lastRetString.replace("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
		lastRetString = lastRetString.replace("xmlns:myra-jobprops=\"http://www.likyateknoloji.com/myra-jobprops\"", "");
		lastRetString = lastRetString.replace("xmlns:wla=\"http://www.likyateknoloji.com/wla-gen\"", "");
		lastRetString = lastRetString.replace("xmlns:lik=\"http://www.likyateknoloji.com/likya-gen\"", "");
		lastRetString = lastRetString.replace("xmlns:myra-stateinfo=\"http://www.likyateknoloji.com/myra-stateinfo\"", "");
		
		lastRetString = lastRetString.replace("xsi:type=\"myra:simpleProperties\"", "");
		
		lastRetString = lastRetString.replace("<myra:", "<");
		lastRetString = lastRetString.replace("</myra:", "</");
		
		lastRetString = lastRetString.replace("<myra-jobprops:", "<");
		lastRetString = lastRetString.replace("</myra-jobprops:", "</");

		lastRetString = lastRetString.replace("<wla:", "<");
		lastRetString = lastRetString.replace("</wla:", "</");

		lastRetString = lastRetString.replace("<lik:", "<");
		lastRetString = lastRetString.replace("</lik:", "</");

		lastRetString = lastRetString.replace("<myra-stateinfo:", "<");
		lastRetString = lastRetString.replace("</myra-stateinfo:", "</");

		//System.out.println(inXmlTxt);
		//System.out.println();
		System.out.println(lastRetString.trim());
	}
	
	public static void methodTree(String inXmlTxt) throws Exception {
		
		HashMap<String, String> dnsMap = new HashMap<>(); 
		
		dnsMap.put("http://www.likyateknoloji.com/myra-joblist/", null);
		dnsMap.put("http://www.w3.org/2001/XMLSchema-instance/", "");
		dnsMap.put("http://www.likyateknoloji.com/myra-jobprops/", "");
		dnsMap.put("http://www.likyateknoloji.com/wla-gen/", "");
		dnsMap.put("http://www.likyateknoloji.com/likya-gen/", "");
		dnsMap.put("http://www.likyateknoloji.com/myra-stateinfo/", "");


		XmlOptions xmlOptions = new XmlOptions();
		
		xmlOptions.setSaveSuggestedPrefixes(dnsMap);
		// xmlOptions.setUseDefaultNamespace();
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(inXmlTxt);
		
		if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
			throw new Exception("JobList.xml is null or damaged !");
		}
		
		String lastRetString = jobListDocument.xmlText(xmlOptions);
		
		//System.out.println(inXmlTxt);
		//System.out.println();
		System.out.println(lastRetString);
	}
	
	public static void methodTwo(String inXmlTxt) throws XmlException {
		
		HashMap<String, String> dnsMap = new HashMap<>(); 
		
		dnsMap.put("http://www.likyateknoloji.com/myra-joblist", "");
		dnsMap.put("http://www.w3.org/2001/XMLSchema-instance", "");
		dnsMap.put("http://www.likyateknoloji.com/myra-jobprops", "");
		dnsMap.put("http://www.likyateknoloji.com/wla-gen", "");
		dnsMap.put("http://www.likyateknoloji.com/likya-gen", "");
		dnsMap.put("http://www.likyateknoloji.com/myra-stateinfo", "");


		XmlOptions xmlOptions = new XmlOptions();
		
		xmlOptions.setLoadSubstituteNamespaces(dnsMap);
		xmlOptions.setUseDefaultNamespace();
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(inXmlTxt);
		
		String lastRetString = jobListDocument.xmlText(xmlOptions);
		
		System.out.println(inXmlTxt);
		System.out.println();
		System.out.println(lastRetString);
	}
	
	
	public static void methodOne(String inXmlTxt) throws XmlException {
		
		XmlOptions xmlOptions = new XmlOptions();
		
		xmlOptions.setUseDefaultNamespace();
		
		HashMap<String, String> dnsMap = new HashMap<>(); 
		dnsMap.put("", "http://example.com/schemas/DefaultNameSpace");
		xmlOptions.setSaveImplicitNamespaces(dnsMap);
		xmlOptions.setSavePrettyPrint();
		
		xmlOptions.setSaveNamespacesFirst();
		
		JobListDocument jobListDocument = JobListDocument.Factory.parse(inXmlTxt);
		
	    String lastRetString = jobListDocument.xmlText(xmlOptions);
	      
		/*
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setUseDefaultNamespace();
		
		HashMap<String, String> ns = new HashMap<>(); 
		ns.put("http://www.likyateknoloji.com/myra-jobprops", "myra-jobprops");
		ns.put("http://www.likyateknoloji.com/myra-stateinfo", "myra-stateinfo");
		
		xmlOptions.setSaveSuggestedPrefixes(ns);
			
		xmlOptions.setSaveAggressiveNamespaces();
		
		*/
		// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.toString());
		
		// JobListDocument jobListDocumentNew = JobListDocument.Factory.parse(jobListDocument.xmlText(xmlOptions));
		
	    
		System.out.println(inXmlTxt);
		System.out.println();
		System.out.println(lastRetString);
	}
}
