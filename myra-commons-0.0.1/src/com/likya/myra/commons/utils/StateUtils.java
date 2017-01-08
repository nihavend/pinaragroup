/*
 * TlosFaz_V2.0
 * com.likya.tlos.core.spc.helpers : StateUtils.java
 * @author Serkan Ta�
 * Tarih : 10.�ub.2010 12:40:09
 */

package com.likya.myra.commons.utils;

import com.likya.xsd.myra.model.stateinfo.JobStatusListDocument.JobStatusList;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeDocument.ReturnCode;
import com.likya.xsd.myra.model.stateinfo.State;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.Status;
import com.likya.xsd.myra.model.stateinfo.SubstateDocument.Substate;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class StateUtils {

	/**
	 * Bu foksiyon, yerel tanımlar için düşünüldüğünde
	 * sadece ve sadece StateNameType.FINISHED ve SubstateNameType.COMPLETED
	 * olarak kabul edilerek statu tanımı yapılmasına müsade ediyor.
	 * 
	 * @param mySubstateNameType
	 * @param jobStatusList
	 * @param returnCode
	 * @return
	 */

	/**
	 * Bir işin statu listesi içindeki returncode listeleri taranıp, verilen returncode
	 * değeri aranıyor. Bulunur ise, hangi status içinde bulunduysa o geri dönüyor.
	 * 
	 * @param jobStatusList
	 * @param returnCode
	 * @return
	 */

	public static Status contains(JobStatusList jobStatusList, int returnCode) {

		Status returnValue = null;

		for (Status myStatus : jobStatusList.getJobStatusArray()) {

			for (ReturnCode myReturnCode : myStatus.getReturnCodeListArray(0).getReturnCodeArray()) {

				if (myReturnCode.getCode() == returnCode) {
					returnValue = myStatus;
				}

			}

		}

		return returnValue;
	}

	/**
	 * Yukarıdaki taramada bulunmaz ise, global değişkenler içinde böyle bir
	 * tanım var mı ona bakıyor.
	 * 
	 * @param myNameType
	 * @param mySubstateNameType
	 * @param globalRegistry
	 * @param returnCode
	 * @return
	 */

	public static Status globalContains(State [] globalStates, StateName.Enum myNameType, SubstateName.Enum mySubstateNameType, int returnCode) {

		Status returnValue = null;

		for (State myGlobalState : globalStates) {

			if (!myGlobalState.getStateName().equals(myNameType)) {
				continue;
			}

			for (Substate mySubstate : myGlobalState.getSubstateArray()) {

				if (!mySubstate.getSubstateName().equals(mySubstateNameType)) {
					continue;
				}

				for (Status mySubStateStatuses : mySubstate.getSubStateStatusesArray()) {

					for (ReturnCode myReturnCode : mySubStateStatuses.getReturnCodeListArray(0).getReturnCodeArray()) {

						if (myReturnCode.getCode() == returnCode) {
							returnValue = mySubStateStatuses;
						}

					}

				}
			}

		}

		return returnValue;
	}

}
