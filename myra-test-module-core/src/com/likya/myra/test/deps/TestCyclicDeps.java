package com.likya.myra.test.deps;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.commons.utils.DependencyOperations;
import com.likya.xsd.myra.model.joblist.JobListDocument;


public class TestCyclicDeps extends TestBase {
	
	public static void main(String[] args) {
		
		try {
		
			String pathName = "/Users/serkan/git/localgit/TL-2.0.0-Test/src/com/likya/myra/test/deps/";
			
			String fileNames [] =  { "5.xml", "10.xml", "50.xml", "100.xml", "200.xml", "400.xml", "500.xml", "800.xml", "1600.xml", "3200.xml", "5000.xml"};
			
			for(String fileName : fileNames) {
				
				JobListDocument jobListDocument = getJobList(pathName, fileName, true);
				
				System.out.println(">> Checking" + fileName + "...");
				long startTime = DateUtils.getCurrentTimeMilliseconds();
				boolean hasCycle = DependencyOperations.checkCyclicDependency(null, toAbstractJobMap(jobListDocument));
				long duration = DateUtils.getCurrentTimeMilliseconds() - startTime;
				System.out.println(">> checked in " + duration + " ms");
				
				if(hasCycle) { 
					System.out.println("Has Cycle !!!");
				}
				
				// break;
			}
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
