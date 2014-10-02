package com.likya.myra.commons.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.java.dev.eval.Expression;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.log4j.Logger;

import com.likya.myra.commons.model.UnresolvedDependencyException;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.jobprops.DependencyListDocument.DependencyList;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class JobDependencyResolver {

	/* SpcLookupTable spcLookupTable, String runId */

	public static boolean isResolved(Logger logger, AbstractJobType ownerJob, HashMap<String, AbstractJobType> jobQueue) throws UnresolvedDependencyException {

		boolean retValue = false;
		
		DependencyList dependencyList = ownerJob.getDependencyList();
		
		if (dependencyList == null || dependencyList.getItemArray().length == 0) {
			return true;
		}

		String dependencyExpression = dependencyList.getDependencyExpression().trim().toUpperCase();

		Item[] dependencyArray = dependencyList.getItemArray();

		try {

			String ownerJsName = ownerJob.getBaseJobInfos().getJsName();

			dependencyExpression = dependencyExpression.replace("AND", "&&");
			dependencyExpression = dependencyExpression.replace("OR", "||");

			Expression exp = null;
			try {
				exp = new Expression(dependencyExpression);
			} catch (Throwable t) {
				String errorMessage = "     > " + ownerJsName + " isi icin hatali bagimlilik tanimlamasi yapilmis ! (" + dependencyExpression + ") kontrol ediniz.";
				logger.error(errorMessage);
				throw new UnresolvedDependencyException(errorMessage);
			}

			ArrayIterator dependencyArrayIterator = new ArrayIterator(dependencyArray);

			Map<String, BigDecimal> variables = new HashMap<String, BigDecimal>();

			AbstractJobType depJob = null;

			while (dependencyArrayIterator.hasNext()) {

				Item item = (Item) (dependencyArrayIterator.next());

				/*	
				 * 	bağımlılıktanımları dependencyExpression içinde var mı kontrolü yapılıyor.
				 * Yok ise hata veriyordu. Kaldırıldı. Tersi kontroll edilebilir. 
				 *
				if (dependencyExpression.indexOf(item.getDependencyID().toUpperCase()) < 0) {
					String errorMessage = "     > " + ownerJsName + " isi icin hatali bagimlilik tanimlamasi yapilmis ! (" + dependencyExpression + ") kontrol ediniz.";
					logger.info(errorMessage);
					logger.error(errorMessage);
					throw new UnresolvedDependencyException(errorMessage);
				}
				*/
				if (item.getJsPath() == null || item.getJsPath() == "") {
					// Lokal bir bagimlilik
					if (jobQueue.get(item.getJsId()) == null) {
						// SWErrorOperations.logErrorForItemJsId(logger, ownerJsName, item.getJsName(), ownerJob.getJobRuntimeProperties().getAbsoluteJobPath(), ownerJob.getJobRuntimeProperties().getJobProperties().getID());
					}
					depJob = jobQueue.get(item.getJsId());
				} else {
					// Global bir bagimlilik
					// SpcInfoType spcInfoType = spcLookupTable.getTable().get(BasePathType.getRootPath() + "." + item.getJsPath());

					// if (spcInfoType == null) {
					// SWErrorOperations.logErrorForSpcInfoType(logger, ownerJsName, item.getJsPath(), runId, ownerJob.getJobRuntimeProperties().getAbsoluteJobPath(), spcLookupTable);
					// }

					// Job job = spcInfoType.getSpcReferance().getJobQueue().get(item.getJsId());
					// if (job == null) {
					// SWErrorOperations.logErrorForJob(logger, ownerJsName, item.getJsName(), item.getJsPath(), runId, spcInfoType.getSpcReferance().getSpcAbsolutePath());
					// }

					// jobRuntimeProperties = job.getJobRuntimeProperties();
				}

				if (depJob == null) {
					logger.info("     > jobRuntimeProperties.getJobProperties() == null !!");
					throw new UnresolvedDependencyException("     > jobRuntimeProperties.getJobProperties() == null !!");
				}
				
				if(depJob.getGraphInfo().getDeadBranch()) {
					return retValue;
				}

				// Cyclic dependency shoud be checked !!!
				// Bunun yanı sıra Hem true hem false bağımlılığına sahip dallar var ise ayıklanmalı.
				// cleanCyclecDeps(meJob, jobProperties, ?);

				
				if(StatusName.SUCCESS.equals(LiveStateInfoUtils.getLastStateInfo(depJob).getStatusName()) && StatusName.FAILED.equals(item.getJsDependencyRule().getStatusName())) {
					// This job is dependent with fail but, it is success so we have to set this as dead !
					ownerJob.getGraphInfo().setDeadBranch(true);
					return retValue;
				}
				
				LiveStateInfo liveStateInfo = LiveStateInfoUtils.getLastStateInfo(depJob);

				boolean resolveRet = LiveStateInfoUtils.compareDepencyRule(variables, item, liveStateInfo);

				if (!resolveRet) {
					return resolveRet;
				}
			}

			int result = exp.eval(variables).intValue();

			retValue = (result == 0 ? false : true);

		} catch (Throwable t) {
			throw new UnresolvedDependencyException(t);
		}

		return retValue;
	}
}
