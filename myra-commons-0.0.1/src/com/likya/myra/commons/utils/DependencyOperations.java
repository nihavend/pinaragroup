/*******************************************************************************
 * Copyright 2013 Likya Teknoloji
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.likya.myra.commons.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.likya.commons.utils.DateUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class DependencyOperations {

	public static ArrayList<String> getDependencyJobKeys(DependencyList dependencyList) {

		ArrayList<String> myList = new ArrayList<String>();

		for (Item item : dependencyList.getItemArray()) {
			myList.add(item.getJsId());
		}

		return myList;
	}

	//	public static ArrayList<Integer> getDependencyStatusList(ArrayList<DependencyInfo> dependencyInfoList) {
	//
	//		ArrayList<Integer> myList = new ArrayList<Integer>();
	//
	//		Iterator<DependencyInfo> myIterator = dependencyInfoList.iterator();
	//
	//		while (myIterator.hasNext()) {
	//
	//			DependencyInfo dependencyInfo = myIterator.next();
	//
	//			myList.add(dependencyInfo.getStatus());
	//		}
	//
	//		return myList;
	//	}

	//	public static ArrayList<Integer> getDependencyStatusList(String jobKey, ArrayList<DependencyInfo> dependencyInfoList) {
	//
	//		ArrayList<Integer> myList = new ArrayList<Integer>();
	//
	//		Iterator<DependencyInfo> myIterator = dependencyInfoList.iterator();
	//
	//		while (myIterator.hasNext()) {
	//
	//			DependencyInfo dependencyInfo = myIterator.next();
	//			if (dependencyInfo.getJobKey().equals(jobKey)) {
	//				myList.add(dependencyInfo.getStatus());
	//			}
	//		}
	//
	//		return myList;
	//	}

	//	public static boolean hasDependentWithStatus(String jobKey, int status) {
	//
	//		HashMap<String, Job> jobQueue = new HashMap<String, Job>(TlosServer.getJobQueue());
	//
	//		// remove myself
	//		jobQueue.remove(jobKey);
	//
	//		Iterator<Job> jobListIterator = jobQueue.values().iterator();
	//
	//		while (jobListIterator.hasNext()) {
	//
	//			Job tmpJob = jobListIterator.next();
	//
	//			Iterator<DependencyInfo> dependentToMeListIterator = tmpJob.getJobProperties().getJobDependencyInfoList().iterator();
	//
	//			while (dependentToMeListIterator.hasNext()) {
	//				DependencyInfo tmpDependencyInfo = dependentToMeListIterator.next();
	//
	//				if (tmpDependencyInfo.getJobKey().equals(jobKey)) {
	//					if (getDependencyStatusList(tmpJob.getJobProperties().getJobDependencyInfoList()).indexOf(new Integer(status)) >= 0) {
	//						return true;
	//					}
	//				}
	//			}
	//
	//		}
	//
	//		return false;
	//	}

	//	public static ArrayList<Job> getDependencyList(HashMap<String, Job> jobQueue, Object jobKey) {
	//
	//		ArrayList<Job> jobList = new ArrayList<Job>();
	//
	//		try {
	//			Iterator<Job> jobsIterator = jobQueue.values().iterator();
	//			while (jobsIterator.hasNext()) {
	//				Job scheduledJob = jobsIterator.next();
	//				ArrayList<String> dependentJobList = DependencyOperations.getDependencyJobKeys(scheduledJob.getJobProperties().getJobDependencyInfoList());
	//				int indexOfJob = dependentJobList.indexOf(jobKey);
	//				if (indexOfJob > -1) {
	//					jobList.add(scheduledJob);
	//				}
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return jobList.size() == 0 ? null : jobList;
	//
	//	}

	public static ArrayList<AbstractJobType> getDependencyList(HashMap<String, AbstractJobType> jobQueue, Object jobKey) {

		ArrayList<AbstractJobType> jobList = new ArrayList<AbstractJobType>();

		try {

			Iterator<AbstractJobType> jobsIterator = jobQueue.values().iterator();

			while (jobsIterator.hasNext()) {

				AbstractJobType abstractJobType = jobsIterator.next();
				
				DependencyList dependencyList = abstractJobType.getDependencyList();
				
				if(dependencyList == null || dependencyList.sizeOfItemArray() == 0) {
					continue;
				}
				
				ArrayList<String> dependentJobList = DependencyOperations.getDependencyJobKeys(dependencyList);
				int indexOfJob = dependentJobList.indexOf(jobKey);
				if (indexOfJob > -1) {
					jobList.add(abstractJobType);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobList.size() == 0 ? null : jobList;

	}

	public static boolean checkCyclicDependency(Logger logger, HashMap<String, AbstractJobType> jobQueue) {

		try {

			for (AbstractJobType abstractJobType : jobQueue.values()) {

				// System.err.println("Checking job : " + abstractJobType.getId());
				
				DependencyList dependencyList = abstractJobType.getDependencyList();

				if (dependencyList == null)
					continue;

				ArrayList<String> dependentToJobList = DependencyOperations.getDependencyJobKeys(dependencyList);

				if(logger != null) {
					logger.warn("         >> " + abstractJobType.getId());
				}

				// System.err.println("List of job : " + abstractJobType.getId());
				if (recurseInToCycle("	>>", logger, abstractJobType, jobQueue, dependentToJobList)) {
					return true;
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return false;
	}

	private static boolean recurseInToCycle(String headStr, Logger logger, AbstractJobType abstractJobType, HashMap<String, AbstractJobType> jobQueue, ArrayList<String> dependentToJobList) {

		if (dependentToJobList.indexOf(abstractJobType.getId()) >= 0) {
			return true;
		} else {

			for(String recurJobKey : dependentToJobList) {
				
				if (!jobQueue.containsKey(recurJobKey)) {
					continue;
				}

				AbstractJobType recurJob = jobQueue.get(recurJobKey);
				DependencyList dependencyList = recurJob.getDependencyList();

				if (dependencyList == null) {
					continue;
				}
				
				ArrayList<String> tmpDependentToJobList = DependencyOperations.getDependencyJobKeys(dependencyList);
				
				if (tmpDependentToJobList.indexOf(recurJob.getId()) >= 0) {
					return true;
				}
				// logger.warn("  Analyzing dependency list of          >> " + recurJob.getId());
				// System.err.println(headStr + "List of job : " + recurJobKey);
				if (recurseInToCycle("	>>" + headStr, logger, abstractJobType, jobQueue, tmpDependentToJobList)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean validateDependencyList(Logger logger, HashMap<String, AbstractJobType> jobQueue) {

		Date startTime = Calendar.getInstance().getTime();

		boolean cyclCheck = checkCyclicDependency(logger, jobQueue);

		String duration = "" + DateUtils.getDurationNumeric(startTime);

		logger.warn("Cyclicdependency checkduration : " + duration);

		if (cyclCheck) {
			logger.fatal("Cyclic dependency detected !");
			return false;
		}

		Iterator<AbstractJobType> jobsIterator = jobQueue.values().iterator();

		while (jobsIterator.hasNext()) {

			AbstractJobType abstractJobType = jobsIterator.next();

			DependencyList dependencyList = abstractJobType.getDependencyList();

			if (dependencyList == null) {
				continue;
			}

			if (getDependencyJobKeys(dependencyList).indexOf(abstractJobType.getId()) >= 0) {
				logger.fatal(abstractJobType.getId() + " bagimlilik bilgileri hatali ! : Bir iş kendine bağlanamaz !");
				return false;
			}

			int i = 0;
			while (i < dependencyList.sizeOfItemArray()) {

				String key = (dependencyList.getItemArray(i)).getJsId();

				if (jobQueue.get(key) == null) {
					logger.fatal(abstractJobType.getId() + "Bağımlilik bilgileri hatalı ! : Tanımsız bağımlılık adı :" + key);
					return false;
				}

				
				boolean isManuel = LiveStateInfoUtils.equalStatesPIU(jobQueue.get(key));
				if (isManuel) {
					logger.warn("Job Id : " + abstractJobType.getId() + " >> Bağımlılık bilgilerinde manuel işlere bağmlılık tanımı var :" + key);
					// return false;
				}
				
				++i;
			}
		}

		return true;

	}

}
