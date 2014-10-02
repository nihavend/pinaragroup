package com.likya.myra.test.binxml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import com.likya.myra.test.deps.TestBase;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class ZipUtil extends TestBase {

	public static String compress(String str) throws Throwable {

		if (str == null || str.length() == 0) {
			return str;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = new GZIPOutputStream(out);

		gzip.write(str.getBytes());
		gzip.close();

		return out.toString("ISO-8859-1");
	}

	public static void main(String[] args) throws IOException {

//		String string = "admin";
//		System.out.println("after compress:");
//
//		try {
//			System.out.println(ZipUtil.compress(string));
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}

		try {
			ZipUtil.compressXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void compressXml() throws Exception {

		String pathName = "/Users/serkan/git/localgit/TL-2.0.0-Test/src/com/likya/myra/test/deps/";

		String fileNames[] = { "5.xml", "10.xml", "50.xml", "100.xml", "200.xml", "400.xml", "500.xml", "800.xml", "1600.xml", "3200.xml", "5000.xml" };

		int idx = 9;

		JobListDocument jobListDocument = getJobList(pathName, fileNames[idx]);

		File file = new File(pathName + fileNames[idx] + ".bin");
		
		long startTime = System.currentTimeMillis();
		byte[] data = jobListDocument.toString().getBytes("utf-8");
		long duration = System.currentTimeMillis() - startTime;
		System.err.println(file.getName() + "	>> to byte[] in " + duration + " ms");
		
		startTime = System.currentTimeMillis();
		OutputStream outputStream = new FileOutputStream(file);
		// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
		gzip.write(data);
		gzip.close();
		duration = System.currentTimeMillis() - startTime;
		System.err.println(file.getName() + "	>> is saved in " + duration + " ms");


	}
}