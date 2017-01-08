package com.likya.myra.commons.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class IdFilter {

	public static <T> Collection<T> noneFilter(Collection<T> target, String [] idArray) {
		
		Collection<T> result = new ArrayList<T>();
		for (T element : target) {
			for(String jobId : idArray) {
				if (!jobId.equals(((AbstractJobType)element).getId())) {
					result.add(element);
				}
			}
		}
		return result;
	}

}
