package com.likya.pinara.test.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPOutputStream;

import com.likya.commons.utils.FileUtils;
import com.likya.pinara.utils.PersistApi;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class SerializeSenaryo {
	
	public static void main(String[] args) throws Exception {

		String srcFile = "/Users/serkan/git/pinaragroup/Pinara-0.0.1/1.xml";

		StringBuffer xmlString = FileUtils.readFile(srcFile);

		String dstFile = srcFile + ".bin";

		String tmpDstFile = dstFile + ".tmp";

		File binaryFile = new File(tmpDstFile);

		JobListDocument jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
        
		FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
		
		byte[] data = PersistApi.encryptedArray(jobListDocument);
		
		GZIPOutputStream gzip = new GZIPOutputStream(fileOutputStream);
		gzip.write(data);
		gzip.close();

		// Check the newly created binary senaryo file...
		Path dstPath = FileSystems.getDefault().getPath(dstFile);
		Path tmpPath = FileSystems.getDefault().getPath(tmpDstFile);
		if (FileUtils.checkFile(tmpDstFile)) {
			if (FileUtils.checkFile(dstFile)) {
				// remove the old binary
				Files.delete(dstPath);
			}
			Files.move(tmpPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
		} else {
			throw new Exception(binaryFile + " is not created, serialization failed !");
		}

	}

}
