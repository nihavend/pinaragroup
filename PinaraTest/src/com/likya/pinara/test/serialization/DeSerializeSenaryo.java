package com.likya.pinara.test.serialization;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

import com.likya.pinara.utils.PersistApi;

public class DeSerializeSenaryo {
	
	public static void main(String[] args) throws Exception {
		
		String srcFileName = "/Users/serkan/git/pinaragroup/Pinara-0.0.1/1.xml.bin";
		
		FileInputStream inputStream = new FileInputStream(srcFileName);
		
		GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
		
		// long sizeOfFile = Files.size(FileSystems.getDefault().getPath(srcFileName));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] data = new byte[1024];
		
        int len;
        while((len = gzipInputStream.read(data)) != -1){
        	out.write(data, 0, len);
        }
		
		gzipInputStream.close();
		out.close();
		
		String decryptedString = PersistApi.decryptedString(out.toByteArray());
		
		System.out.println(decryptedString);
	}

}
