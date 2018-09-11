package com.likya.pinara.utils.xml.mappers;

import com.likya.myra.commons.utils.LiveStateInfoUtils;
import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;
import com.likya.xsd.myra.model.stateinfo.StatusNameDocument.StatusName;
import com.likya.xsd.myra.model.stateinfo.SubstateNameDocument.SubstateName;

public class LSIToStatusMapper {
	
	public static int getMapped(AbstractJobType abstractJobType) {
		
		LiveStateInfo liveStateInfo = LiveStateInfoUtils.getLastStateInfo(abstractJobType);
		
		int statu = 0;
		
		if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.PENDING, SubstateName.IDLED, StatusName.BYTIME)) {
			statu = 0;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.PENDING, SubstateName.READY, StatusName.WAITING)) {
			statu = 1;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.RUNNING, SubstateName.ON_RESOURCE, StatusName.TIME_IN)) {
			statu = 2;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.COMPLETED, StatusName.SUCCESS)) {
			statu = 3;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.COMPLETED, StatusName.FAILED)) {
			statu = 4;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.RUNNING, SubstateName.ON_RESOURCE, StatusName.TIME_OUT)) {
			statu = 5;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.SKIPPED)) {
			statu = 6;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.STOPPED)) {
			statu = 7;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.PENDING, SubstateName.PAUSED)) {
			statu = 8;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.PENDING, SubstateName.DEACTIVATED)) {
			statu = 9;
		} else if(LiveStateInfoUtils.equalStates(liveStateInfo, StateName.FINISHED, SubstateName.COMPLETED, StatusName.WARNING)) {
			statu = 10;
		}
		
		return statu;
		
	}

}

/*
		imageList.addItem(clock1)	0*
		imageList.addItem(kilit)	1*
		imageList.addItem(kosu2)	2*
		imageList.addItem(ok2)		3*
		imageList.addItem(error)	4*
		imageList.addItem(timeout)	5*
		imageList.addItem(skip)		6*
		imageList.addItem(stop)		7*
		imageList.addItem(pause)	8*
		imageList.addItem(disabled)	9*
*/