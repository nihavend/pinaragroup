package com.likya.myra.test.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.log4j.Logger;

import com.likya.commons.utils.FileUtils;
import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.myra.commons.utils.StateFilter;
import com.likya.myra.commons.utils.XMLValidations;
import com.likya.myra.jef.jobs.JobImpl;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.joblist.JobListDocument;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class TestPredicateUtils {

	class SimpleJob {

		StateName.Enum stateName;
		String id;

		public SimpleJob(String id, StateName.Enum stateName) {
			super();
			this.stateName = stateName;
			this.id = id;
		}

		public StateName.Enum getStateName() {
			return stateName;
		}

		public String getId() {
			return id;
		}

	}

	public static void main(String[] args) {
		stateFilter();
	}

	private static void stateFilter() {

		StringBuffer xmlString = FileUtils.readFile("/Users/serkan/git/localgit/TL-2.0.0-Test/1.xml");
		
		JobListDocument jobListDocument = null;
		try {
			jobListDocument = JobListDocument.Factory.parse(xmlString.toString());
			
			if (!XMLValidations.validateWithXSDAndLog(Logger.getRootLogger(), jobListDocument)) {
				throw new Exception("JobList.xml is null or damaged !");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		StateName.Enum filterStates[] = { StateName.RUNNING, StateName.CANCELLED, StateName.FAILED, StateName.FINISHED, StateName.PENDING};

		Collection collection = Arrays.asList(jobListDocument.getJobList().getGenericJobArray());

		@SuppressWarnings("unchecked")
		Collection<AbstractJobType> filteredList = CollectionUtils.select(collection, new StateFilter(filterStates).anyPredicate());

		System.err.println(filteredList.size());

	}

	public static Predicate getEqualStatesPredicate(final StateName.Enum stateName) {

		Predicate[] predicateStateNames = new Predicate[] { new Predicate() {
			public boolean evaluate(Object simpleJob) {
				StateName.Enum tmpStateName = ((SimpleJob) simpleJob).getStateName();
				System.err.println(tmpStateName);
				return stateName.equals(tmpStateName);
			}
		} };

		return PredicateUtils.anyPredicate(predicateStateNames);

	}

	public static void test01() {

		Predicate[] predicateStateNames = new Predicate[] { new Predicate() {
			public boolean evaluate(Object stateInfo) {
				return LiveStateInfoUtils.equalStates((LiveStateInfo) stateInfo, StateName.CANCELLED);
			}
		}, new Predicate() {
			public boolean evaluate(Object stateInfo) {
				return LiveStateInfoUtils.equalStates((LiveStateInfo) stateInfo, StateName.FAILED);
			}
		}, new Predicate() {
			public boolean evaluate(Object stateInfo) {
				return LiveStateInfoUtils.equalStates((LiveStateInfo) stateInfo, StateName.FINISHED);
			}
		}, new Predicate() {
			public boolean evaluate(Object stateInfo) {
				return LiveStateInfoUtils.equalStates((LiveStateInfo) stateInfo, StateName.PENDING);
			}
		}, new Predicate() {
			public boolean evaluate(Object stateInfo) {
				return LiveStateInfoUtils.equalStates((LiveStateInfo) stateInfo, StateName.RUNNING);
			}
		} };

		Predicate anyStates = PredicateUtils.anyPredicate(predicateStateNames);

		@SuppressWarnings("unchecked")
		Collection<JobImpl> jobList = CollectionUtils.select(new ArrayList<JobImpl>(), anyStates);

		jobList.size();
	}

	public static void test02() {
		TestPredicateUtils testPredicateUtils = new TestPredicateUtils();

		HashMap<String, SimpleJob> jobList = new HashMap<String, SimpleJob>();

		jobList.put("1", testPredicateUtils.new SimpleJob("1", StateName.CANCELLED));
		jobList.put("2", testPredicateUtils.new SimpleJob("2", StateName.FAILED));
		jobList.put("3", testPredicateUtils.new SimpleJob("3", StateName.FINISHED));
		jobList.put("4", testPredicateUtils.new SimpleJob("4", StateName.PENDING));
		jobList.put("5", testPredicateUtils.new SimpleJob("5", StateName.RUNNING));
		jobList.put("6", testPredicateUtils.new SimpleJob("6", StateName.RUNNING));

		StateName.Enum filterState = StateName.RUNNING;

		@SuppressWarnings("unchecked")
		Collection<SimpleJob> filteredList = CollectionUtils.select(jobList.values(), getEqualStatesPredicate(filterState));

		System.out.println(filteredList.size());
	}
}
