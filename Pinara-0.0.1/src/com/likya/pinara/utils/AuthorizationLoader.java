package com.likya.pinara.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.likya.pinara.Pinara;
import com.likya.pinara.model.PinaraAuthorization;

public class AuthorizationLoader {

	public static String fileToPersist = Pinara.DATA_PATH + File.separator + "Pinara.authorization";

	public static PinaraAuthorization readAuthorization() throws Exception {

		FileInputStream fis = null;
		ObjectInputStream in = null;
		PinaraAuthorization pinaraAuthorization;

		fis = new FileInputStream(fileToPersist);
		in = new ObjectInputStream(fis);
		Object input = in.readObject();

		pinaraAuthorization = (PinaraAuthorization) input;
		in.close();

		return pinaraAuthorization;
	}

	public static boolean persistAuthorization(PinaraAuthorization pinaraAuthorization) {

		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(fileToPersist);
			out = new ObjectOutputStream(fos);
			out.writeObject(pinaraAuthorization);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return true;

	}
}
