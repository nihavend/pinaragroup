package com.likya.myra.test.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument.JobList;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class TestJobListFilter extends TestFilterBase {
	
	public static void main(String[] args) {
		
		// User myUser = new User(1, "serkan");
		
		// Predicate userNameFilter = new UserNameFilter(myUser.userName);
		// Predicate userNoFilter =  new UserNoFilter(myUser.userNo);
		
		
		ArrayList<Predicate> filters = new ArrayList<Predicate>();

		// filters.add(userNoFilter);
		// filters.add(userNameFilter);
		
		// PredicateUtils.anyPredicate(filters);
		
		// StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING};
		
		

//		for(StateName.Enum stateName : filterStates) {
//			filters.add(new StateFilter(stateName));
//		}
		
		StateName.Enum stateName;
		
		stateName = StateName.RUNNING;
//		stateName = StateName.CANCELLED;
//		stateName = StateName.FAILED;
//		stateName = StateName.FINISHED;
		stateName = StateName.PENDING;
		
		filters.add(new StateFilter(stateName));
		
		JobList jobList = getJobList("/Users/serkan/git/localgit/TL-2.0.0-Test/xmls/10.xml");
		
		Collection<?> collection = Arrays.asList(jobList.getGenericJobArray());

		@SuppressWarnings("unchecked")
		Collection<AbstractJobType> filteredList = CollectionUtils.select(collection, PredicateUtils.anyPredicate(filters));
		
		System.err.println(filteredList.size());
		
	}
}
