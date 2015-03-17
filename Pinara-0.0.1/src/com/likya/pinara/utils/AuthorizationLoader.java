package com.likya.pinara.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.likya.pinara.Pinara;
import com.likya.pinara.model.PinaraAuthorization;

public class AuthorizationLoader {

	public static String fileToPersist = Pinara.DATA_PATH + File.separator + "Pinara.authorization";

	@SuppressWarnings("unchecked")
	public static HashMap<String, PinaraAuthorization> readAuthorizationList() throws Exception {

		FileInputStream fis = null;
		ObjectInputStream in = null;
		HashMap<String, PinaraAuthorization> pinaraAuthorizationList;

		fis = new FileInputStream(fileToPersist);
		in = new ObjectInputStream(fis);
		Object input = in.readObject();

		pinaraAuthorizationList = (HashMap<String, PinaraAuthorization>) input;
		in.close();

		return pinaraAuthorizationList;
	}

	public static boolean persistAuthorizationList(HashMap<String, PinaraAuthorization> pinaraAuthorizationList) {

		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(fileToPersist);
			out = new ObjectOutputStream(fos);
			out.writeObject(pinaraAuthorizationList);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return true;

	}
}
