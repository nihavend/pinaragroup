package com.likya.myra.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import com.likya.commons.utils.SortUtils;

public class TestKeyOrder {

	public static void main(String[] args) throws Exception {

		HashMap<String, Object> testQueue = new HashMap<String, Object>();

		testQueue.put("5", new String("5"));
		testQueue.put("4", new String("4"));
		testQueue.put("1", new String("1"));
		testQueue.put("8", new String("8"));
		testQueue.put("10", new String("10"));
		testQueue.put("3", new String("3"));
		testQueue.put("6", new String("6"));
		testQueue.put("15", new String("15"));
		testQueue.put("7", new String("7"));
		testQueue.put("22", new String("22"));

//		String[] keys = testQueue.keySet().toArray(new String[0]);
//		
//		class CompTest implements Comparator<String> {
//
//			@Override
//			public int compare(String o1, String o2) {
//				int i1 = Integer.parseInt(o1);
//				int i2 = Integer.parseInt(o2);
//				return i1 - i2;
//			}
//			
//		}
//		
//		
//		Arrays.sort(keys, new CompTest());
		//Arrays.sort(a, c);(keys, );
		
		String[] keys = SortUtils.sortKeys(testQueue.keySet());
		
		for (Object key : keys) {
			System.out.println(testQueue.get(key));
		}
	}
}
