package com.likya.pinara.tools.expimp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

import com.likya.pinara.utils.PersistApi;

public class DeSerializeSenaryo {
	
	public static String exportXml(String srcFileName) throws Exception {
		
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
		
		return decryptedString;
	}
	
	public static void main(String[] args) throws Exception {
		
		String srcFileName = "";
		
		if(args != null && args.length == 1) {
			srcFileName = args[0];
		} else {
			System.out.println("Usage : /path/xmlFile");
			System.exit(0);
		}
		
		String decryptedString = exportXml(srcFileName);
		
		System.out.println(decryptedString);
	}

}
