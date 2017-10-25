package com.likya.pinara.test.jobcrud;

import com.likya.xsd.myra.model.joblist.JobListDocument;

public abstract class JobCrudDAO {

	public abstract JobListDocument readJobs(String dbPath); 
	
	public abstract void saveJob(String dbPath, String jobId, String jobXml);

//	public void testSimpleGetUserList();
//	
//	public void testSimpleReadWithId();
//
//	public void testSimpleReadWithUsername();
//
//	public void testSimpleUpdate();
//	
//	public void testSimpleDelete();
//	
//	public void testSimpleDeleteWithId();
//	
//	public void testChangePasswordWithId();
//	
//	public void testChangePasswordWithUsername();
//	
//	public void testChangePasswordAdmWithId();
//	
//	public void testChangePasswordAdmWithUsername();

	
}
