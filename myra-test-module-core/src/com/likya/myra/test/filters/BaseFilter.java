package com.likya.myra.test.filters;

import org.apache.commons.collections4.Predicate;

import com.likya.myra.jef.jobs.JobImpl;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public abstract class BaseFilter  implements Predicate {
	
	protected abstract boolean helper(AbstractJobType abstractJobType);
	
	public final boolean evaluate(Object object) {
		if(object instanceof AbstractJobType) {
			return helper((AbstractJobType) object);
		} else if (object instanceof JobImpl) {
			return helper(((JobImpl) object).getAbstractJobType());
		}
		return false;
	}

}
