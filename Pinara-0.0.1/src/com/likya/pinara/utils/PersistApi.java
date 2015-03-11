package com.likya.pinara.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Key;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.likya.commons.utils.FileUtils;
import com.likya.pinara.Pinara;
import com.likya.pinara.utils.license.LicenseMap;
import com.likya.pinara.utils.license.LikyaSecurity;
import com.likya.xsd.myra.model.joblist.JobListDocument;

public class PersistApi {
	
	private static final String FILE_EXT = ".pnr";

	public static void serialize(JobListDocument jobListDocument) throws Exception {

		String fileName = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getSenaryoDosyasi();
		String dstFile = fileName + FILE_EXT;

		String tmpDstFile = dstFile + ".tmp";

		byte[] data = encryptedArray(jobListDocument);

		FileOutputStream outputStream = new FileOutputStream(tmpDstFile);

		GZIPOutputStream gzip = new GZIPOutputStream(outputStream);

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
			throw new Exception(fileName + " is not created, serialization failed !");
		}
	}

	public static JobListDocument deserialize() throws Exception {

		JobListDocument jobListDocument;

		String srcFileName = Pinara.getInstance().getConfigurationManager().getPinaraConfig().getSenaryoDosyasi() + FILE_EXT;
		
		if (!FileUtils.checkFile(srcFileName)) {
			return null;
		}

		FileInputStream inputStream = new FileInputStream(srcFileName);

		GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

		// long sizeOfFile = Files.size(FileSystems.getDefault().getPath(srcFileName));

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] data = new byte[1024];

		int len;
		while ((len = gzipInputStream.read(data)) != -1) {
			out.write(data, 0, len);
		}

		gzipInputStream.close();
		out.close();
		
		String decryptedString = PersistApi.decryptedString(out.toByteArray());

		jobListDocument = JobListDocument.Factory.parse(decryptedString);

		return jobListDocument;

	}

	public static byte[] encryptedArray(JobListDocument jobListDocument) throws Exception {

		LicenseMap licenseMap = LikyaSecurity.encrypt(jobListDocument.toString());

		byte[] senaryoBytes = licenseMap.getLicenseData();
		int sizeOfLicenseData = senaryoBytes.length;
		Key licenseKey = licenseMap.getKey();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		byteArrayOutputStream.write(LikyaSecurity.intToByteArray(sizeOfLicenseData));
		byteArrayOutputStream.write(senaryoBytes);

		byte[] data = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();

		byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

		objectOutputStream.writeObject(licenseKey);
		objectOutputStream.writeObject(data);

		data = byteArrayOutputStream.toByteArray();

		byteArrayOutputStream.close();
		objectOutputStream.close();

		return data;
	}
	
	public static String decryptedString(byte[] input) throws Exception {
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Key licenseKey = (Key) objectInputStream.readObject();
		byte[] data = (byte[]) objectInputStream.readObject();

		byte[] sizeOfLicenseDataArray = new byte[4];
		System.arraycopy(data, 0, sizeOfLicenseDataArray, 0, 4);
		int sizeOfLicenseData = LikyaSecurity.byteArrayToInt(sizeOfLicenseDataArray);
		
		ByteArrayOutputStream tempBaos = new ByteArrayOutputStream();
		tempBaos.write(data, 4, sizeOfLicenseData);
		data = tempBaos.toByteArray();
		
		String licenseDataString = LikyaSecurity.decrypt(data, licenseKey);

		return licenseDataString;
	}
}
