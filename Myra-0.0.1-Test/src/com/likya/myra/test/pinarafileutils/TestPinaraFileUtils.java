package com.likya.myra.test.pinarafileutils;

import java.util.ArrayList;

import com.likya.pinara.utils.PinaraFileUtils;

public class TestPinaraFileUtils {

	public static void main(String[] args) {
		

		try {
			// PinaraFileUtils.readTextFile(new ArrayList<Long>(), -1, -1, "/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/2dep.xml", null, false, -1, false);
			
			// Tek satırlık satır sonu olmayan 
			
			PinaraFileUtils.readTextFile(new ArrayList<Long>(), -1, -1, "/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/tek.txt", null, false, -1, false);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
