package com.likya.commons.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class SortUtils {
	
	public static String[] sortKeys(Set<String> keySet) {
		
		String[] keys = keySet.toArray(new String[0]);
		
		class CompTest implements Comparator<String> {

			@Override
			public int compare(String o1, String o2) {
				int i1 = Integer.parseInt(o1);
				int i2 = Integer.parseInt(o2);
				return i1 - i2;
			}
			
		}
		
		Arrays.sort(keys, new CompTest());
		
		return keys;
	}

}
