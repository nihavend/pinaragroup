package com.likya.commons.utils;

import java.util.HashMap;
import java.util.ResourceBundle;

public class LocaleMessages {

	private static HashMap<String, ResourceBundle> BUNDLEMAP = new HashMap<>(); // = "com.likya.tlos.resources.messages"; //$NON-NLS-1$

	/**
	 * @author serkan taş
	 *         25.02.2013
	 *         Aşağıdaki linke binaen değiştirildi.
	 *         http://stackoverflow.com/questions/4659929/how-to-use-utf-8-in-resource-properties-
	 *         with-resourcebundle
	 */

	private LocaleMessages() {
	}

	public static void registerBundle(String bundleName) {

		if (!BUNDLEMAP.containsKey(bundleName)) {
			ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName, new UTF8Control());
			BUNDLEMAP.put(bundleName, RESOURCE_BUNDLE);
		}
	}

	public static String getString(String bundleName, String key) {
		
		if (!BUNDLEMAP.containsKey(bundleName)) {
			throw new RuntimeException(bundleName + " is not registered, register bundle !");
		}
		
		if (BUNDLEMAP.get(bundleName).containsKey(key)) {
			return BUNDLEMAP.get(bundleName).getString(key);
		}
		
		return "Missing : " + "!" + bundleName + " or " + key + "!";
	}

}
