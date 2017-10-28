package com.likya.pinara.dao;

import java.nio.file.Path;

import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfosDocument;

public abstract class JobCrudDAO {

	public abstract JobListDocument readJobs(Path dbPath); 
	public abstract boolean saveJob(Path dbPath, String jobId, String jobXml);
	public abstract boolean deleteJob(Path dbPath, String jobId);

	public abstract LiveStateInfosDocument readJobHist(Path dbPath, String jobId); 
	public abstract boolean saveJobHist(Path dbPath, String jobId, String jobXml);
	public abstract boolean deleteJobHist(Path dbPath, String jobId);

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
