package com.likya.pinara.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.likya.myra.jef.ConfigurationManagerImpl;
import com.likya.myra.jef.utils.MyraPersistApi;

public class RecoveryHelper {
	
	public static boolean isInRecoveryState() {

		boolean checkValue = false;

		checkValue = MyraPersistApi.getMyraConfig(false).getPersistent();
		
		if(!checkValue) return checkValue;
		
		Path persistFile = Paths.get(ConfigurationManagerImpl.fileToPersist);
		
		checkValue = checkValue && Files.exists(persistFile);

		return checkValue;
	}
	
}
