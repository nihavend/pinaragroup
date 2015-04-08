package com.likya.myra.commons.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class SSSFilter {

	public static <T> Collection<T> noneFilter(Collection<T> target, StateName.Enum stateName) {
		Collection<T> result = new ArrayList<T>();
		for (T element : target) {
			if (LiveStateInfoUtils.equalStates(((AbstractJobType) element), stateName)) {
				result.add(element);
			}
		}
		return result;
	}

	public static <T> Collection<T> noneFilter(Collection<T> target, StateName.Enum stateName, SubstateName.Enum substateName) {
		Collection<T> result = new ArrayList<T>();
		for (T element : target) {
			if (LiveStateInfoUtils.equalStates(((AbstractJobType) element), stateName, substateName)) {
				result.add(element);
			}
		}
		return result;
	}

	public static <T> Collection<T> noneFilter(Collection<T> target, StateName.Enum stateName, SubstateName.Enum substateName, StatusName.Enum statusName) {
		Collection<T> result = new ArrayList<T>();
		for (T element : target) {
			if (LiveStateInfoUtils.equalStates(((AbstractJobType) element), stateName, substateName, statusName)) {
				result.add(element);
			}
		}
		return result;
	}

}
