package com.likya.pinara.test.basicauth.tr_encod;

import com.likya.pinara.gui.WebManager;

public class TestRestForTrEncoding {

	public static void main(String[] args) {

		WebManager webManager = new WebManager();
		try {
			webManager.initServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
