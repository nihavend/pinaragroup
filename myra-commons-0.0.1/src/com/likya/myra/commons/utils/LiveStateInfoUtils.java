package com.likya.myra.commons.utils;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.ReturnCodeDocument.ReturnCode;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;
import com.likya.xsd.myra.model.wlagen.ItemDocument.Item;

public class LiveStateInfoUtils {

	public static boolean equalStates(LiveStateInfo liveStateInfo, int stateName, int substateName, int statusName) {

		boolean returnValue = false;

		StateName.Enum enumStateName;
		SubstateName.Enum enumSubStateName;
		StatusName.Enum enumStatusName;

		if (stateName > 0 && substateName > 0 && statusName > 0) {

			enumStateName = StateName.Enum.forInt(stateName);
			enumSubStateName = SubstateName.Enum.forInt(substateName);
			enumStatusName = StatusName.Enum.forInt(statusName);

			returnValue = equalStates(liveStateInfo, enumStateName, enumSubStateName, enumStatusName);
		}

		return returnValue;
	}

	public static boolean equalStates(LiveStateInfo liveStateInfo, int stateName, int substateName) {

		boolean returnValue = false;

		StateName.Enum enumStateName;
		SubstateName.Enum enumSubStateName;

		if (stateName > 0 && substateName > 0) {

			enumStateName = StateName.Enum.forInt(stateName);
			enumSubStateName = SubstateName.Enum.forInt(substateName);

			returnValue = equalStates(liveStateInfo, enumStateName, enumSubStateName);
		}

		return returnValue;
	}

	public static boolean equalStates(LiveStateInfo liveStateInfo, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum, StatusName.Enum statusNameEnum) {
		return liveStateInfo.getStateName() != null && liveStateInfo.getSubstateName() != null && liveStateInfo.getStatusName() != null && liveStateInfo.getStateName().equals(stateNameEnum) && liveStateInfo.getSubstateName().equals(substateNameEnum) && liveStateInfo.getStatusName().equals(statusNameEnum);
	}

	public static boolean equalStates(LiveStateInfo liveStateInfo, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum) {
		return liveStateInfo.getStateName().equals(stateNameEnum) && liveStateInfo.getSubstateName().equals(substateNameEnum);
	}

	public static boolean equalStates(LiveStateInfo liveStateInfo, StateName.Enum stateNameEnum) {
		return liveStateInfo.getStateName().equals(stateNameEnum);
	}

	public static boolean equalStates(LiveStateInfo liveStateInfoSrc, LiveStateInfo liveStateInfoTrg) {
		return liveStateInfoSrc.getStateName().equals(liveStateInfoTrg.getStateName()) && liveStateInfoSrc.getSubstateName().equals(liveStateInfoTrg.getSubstateName()) && liveStateInfoSrc.getStatusName().equals(liveStateInfoTrg.getStatusName());
	}

	public static boolean equalStates(AbstractJobType abstractJobType, StateName.Enum stateNameEnum) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), stateNameEnum);
	}

	public static boolean equalStates(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), stateNameEnum, substateNameEnum);
	}
	
	public static boolean equalStates(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum, StatusName.Enum statusNameEnum) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), stateNameEnum, substateNameEnum, statusNameEnum);
	}
	
	/**
	 * @param liveStateInfo
	 * @return true if equals to PENDING-IDLED-BYTIME
	 */
	public static boolean equalStatesPIT(LiveStateInfo liveStateInfo) {
		return LiveStateInfoUtils.equalStates(liveStateInfo, StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYTIME);
	}

	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-IDLED-BYTIME
	 */
	public static boolean equalStatesPIT(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYTIME);
	}

	/**
	 * @param liveStateInfo
	 * @return true if equals to PENDING-IDLED-BYEVENT
	 */
	public static boolean equalStatesPIE(LiveStateInfo liveStateInfo) {
		return LiveStateInfoUtils.equalStates(liveStateInfo, StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYEVENT);
	}

	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-IDLED-BYEVENT
	 */
	public static boolean equalStatesPIE(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYEVENT);
	}

	/**
	 * @param liveStateInfo
	 * @return true if equals to PENDING-IDLED-BYUSER
	 */
	public static boolean equalStatesPIU(LiveStateInfo liveStateInfo) {
		return LiveStateInfoUtils.equalStates(liveStateInfo, StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYUSER);
	}

	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-IDLED-BYUSER
	 */
	public static boolean equalStatesPIU(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_BYUSER);
	}

	/**
	 * @param liveStateInfo
	 * @return true if equals to PENDING-IDLED-WAITING
	 */
	public static boolean equalStatesPIW(LiveStateInfo liveStateInfo) {
		return LiveStateInfoUtils.equalStates(liveStateInfo, StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_WAITING);
	}

	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-IDLED-WAITING
	 */
	public static boolean equalStatesPIW(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0), StateName.INT_PENDING, SubstateName.INT_IDLED, StatusName.INT_WAITING);
	}

	/**
	 * @param liveStateInfo
	 * @return true if equals to PENDING-READY-WAITING
	 */
	public static boolean equalStatesPRW(LiveStateInfo liveStateInfo) {
		return LiveStateInfoUtils.equalStates(liveStateInfo, StateName.INT_PENDING, SubstateName.INT_READY, StatusName.INT_WAITING);
	}

	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-READY-WAITING
	 */
	public static boolean equalStatesPRW(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(getLastStateInfo(abstractJobType), StateName.INT_PENDING, SubstateName.INT_READY, StatusName.INT_WAITING);
	}
	
	/**
	 * @param abstractJobType
	 * @return true if equals to PENDING-DEACTIVATED
	 */
	public static boolean equalStatesPD(AbstractJobType abstractJobType) {
		return LiveStateInfoUtils.equalStates(getLastStateInfo(abstractJobType), StateName.INT_PENDING, SubstateName.INT_DEACTIVATED);
	}

	public static boolean compareDepencyRule(Map<String, BigDecimal> variables, Item item, LiveStateInfo liveStateInfo) {

		boolean retValue = true;

		StateName.Enum jobStateName = liveStateInfo.getStateName();
		SubstateName.Enum jobSubstateName = liveStateInfo.getSubstateName();
		StatusName.Enum jobStatusName = liveStateInfo.getStatusName();

		StateName.Enum itemStateName = item.getJsDependencyRule().getStateName();
		SubstateName.Enum itemSubstateName = item.getJsDependencyRule().getSubstateName();
		StatusName.Enum itemStatusName = item.getJsDependencyRule().getStatusName();

		if (itemStateName != null && itemSubstateName == null && itemStatusName == null) {
			if (jobStateName.equals(itemStateName)) {
				variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(1)); // true
			} else {
				variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(0)); // false
			}
		} else if (itemStateName != null && itemSubstateName != null && itemStatusName == null) {
			if (jobStateName.equals(itemStateName) && jobSubstateName.equals(itemSubstateName)) {
				variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(1)); // true
			} else {
				variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(0)); // false
			}
		} else if (itemStateName != null && itemSubstateName != null && itemStatusName != null && jobStateName != null && jobSubstateName != null) {
			if (jobStateName.equals(itemStateName) && jobSubstateName.equals(itemSubstateName)) {
				if (jobStatusName != null)
					if (jobStatusName.equals(itemStatusName)) {
						variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(1)); // true
					} else {
						variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(0)); // false
					}
			} else {
				variables.put(item.getDependencyID().toUpperCase(Locale.ENGLISH), BigDecimal.valueOf(0)); // false
			}
		} else {
			retValue = false;
		}

		return retValue;
	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, int enumStateName, int enumSubstateName, int enumStatusName) {

		LiveStateInfo liveStateInfo = generateLiveStateInfo(enumStateName, enumSubstateName, enumStatusName);

		if (abstractJobType.getStateInfos() == null || abstractJobType.getStateInfos().getLiveStateInfos() == null) {
			System.err.println(abstractJobType.toString());
		}

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {
			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.set(liveStateInfo);
			/**
			 * Commented the line below due to thread lock on job >= 19 and used the method above
			 * 
			 * @author hulagu
			 */
			// abstractJobType.getStateInfos().getLiveStateInfos().setLiveStateInfoArray(0, liveStateInfo);
		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, LiveStateInfo liveStateInfo) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {
			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.set(liveStateInfo);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		}

		return returnInfo;
	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, StateName.Enum stateNameEnum) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {
			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.setStateName(stateNameEnum);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {
			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.setStateName(stateNameEnum);
			returnInfo.setSubstateName(substateNameEnum);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum, StatusName.Enum statusNameEnum) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {
			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.setStateName(stateNameEnum);
			returnInfo.setSubstateName(substateNameEnum);
			returnInfo.setStatusName(statusNameEnum);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum, StatusName.Enum statusNameEnum, int returnCode, String returnDecription) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {

			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.setStateName(stateNameEnum);
			returnInfo.setSubstateName(substateNameEnum);
			returnInfo.setStatusName(statusNameEnum);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());

			ReturnCode returnCodeObject = returnInfo.addNewReturnCode();

			returnCodeObject.setCode(returnCode);
			returnCodeObject.setDesc(returnDecription);

		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, StateName.Enum stateNameEnum, SubstateName.Enum substateNameEnum, StatusName.Enum statusNameEnum, String returnDecription) {

		LiveStateInfo returnInfo = null;

		synchronized (abstractJobType) {

			returnInfo = abstractJobType.getStateInfos().getLiveStateInfos().insertNewLiveStateInfo(0);
			returnInfo.setStateName(stateNameEnum);
			returnInfo.setSubstateName(substateNameEnum);
			returnInfo.setStatusName(statusNameEnum);
			returnInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());

			ReturnCode returnCodeObject = returnInfo.addNewReturnCode();

			returnCodeObject.setDesc(returnDecription);

		}

		return returnInfo;

	}

	public static LiveStateInfo insertNewLiveStateInfo(AbstractJobType abstractJobType, int enumStateName, int enumSubstateName, int enumStatusName, String retCodeDesc) {
		LiveStateInfo liveStateInfo = LiveStateInfoUtils.insertNewLiveStateInfo(abstractJobType, enumStateName, enumSubstateName, enumStatusName);
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.addNewReturnCode().setDesc(retCodeDesc);
		//sendStatusChangeInfo();
		return liveStateInfo;
	}

	public static LiveStateInfo generateLiveStateInfo(int enumStateName, int enumSubstateName, int enumStatusName) {

		LiveStateInfo liveStateInfo = generateLiveStateInfo(enumStateName, enumSubstateName);

		if (enumStatusName > 0) {
			liveStateInfo.setStatusName(StatusName.Enum.forInt(enumStatusName));
		}

		return liveStateInfo;
	}

	public static LiveStateInfo generateLiveStateInfo(StateName.Enum enumStateName, SubstateName.Enum enumSubstateName, StatusName.Enum enumStatusName) {

		LiveStateInfo liveStateInfo = generateLiveStateInfo(enumStateName, enumSubstateName);
		liveStateInfo.setStatusName(enumStatusName);

		return liveStateInfo;
	}

	public static LiveStateInfo generateLiveStateInfo(StateName.Enum enumStateName, SubstateName.Enum enumSubstateName) {

		LiveStateInfo liveStateInfo = generateLiveStateInfo(enumStateName);
		liveStateInfo.setSubstateName(enumSubstateName);

		return liveStateInfo;
	}

	public static LiveStateInfo generateLiveStateInfo(StateName.Enum enumStateName) {

		LiveStateInfo liveStateInfo = LiveStateInfo.Factory.newInstance();
		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());
		liveStateInfo.setStateName(enumStateName);

		return liveStateInfo;
	}

	public static LiveStateInfo generateLiveStateInfo(int enumStateName, int enumSubstateName) {

		LiveStateInfo liveStateInfo = LiveStateInfo.Factory.newInstance();

		liveStateInfo.setLSIDateTime(MyraDateUtils.getServerW3CDateTime());

		if (enumStateName > 0) {
			liveStateInfo.setStateName(StateName.Enum.forInt(enumStateName));
		}
		if (enumSubstateName > 0) {
			liveStateInfo.setSubstateName(SubstateName.Enum.forInt(enumSubstateName));
		}

		return liveStateInfo;
	}
	
	/**
	 * @param abstractJobType
	 * @return the state values stored in the given index, the most last value has the index 0
	 */
	
	public static LiveStateInfo getLastStateInfo(AbstractJobType abstractJobType) {
		return abstractJobType.getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
	}
	
}
