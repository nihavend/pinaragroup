package com.likya.myra.commons.utils;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.stateinfo.LiveStateInfoDocument.LiveStateInfo;
import com.likya.xsd.myra.model.stateinfo.StateNameDocument.StateName;

public class StateFilter implements JobListFilter {

	private Predicate []  predicateStateNames;

	Predicate[] filters = new Predicate[] { new Predicate() {
		public boolean evaluate(Object object) {
			LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
			StateName.Enum tmpStateName = liveStateInfo.getStateName();
			return StateName.CANCELLED.intValue() == tmpStateName.intValue();
		}
	}, new Predicate() {
		public boolean evaluate(Object object) {
			LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
			StateName.Enum tmpStateName = liveStateInfo.getStateName();
			return StateName.FAILED.intValue() == tmpStateName.intValue();
		}
	}, new Predicate() {
		public boolean evaluate(Object object) {
			LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
			StateName.Enum tmpStateName = liveStateInfo.getStateName();
			return StateName.FINISHED.intValue() == tmpStateName.intValue();
		}
	}, new Predicate() {
		public boolean evaluate(Object object) {
			LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
			StateName.Enum tmpStateName = liveStateInfo.getStateName();
			return StateName.PENDING.intValue() == tmpStateName.intValue();
		}
	}, new Predicate() {
		public boolean evaluate(Object object) {
			LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
			StateName.Enum tmpStateName = liveStateInfo.getStateName();
			return StateName.RUNNING.intValue() == tmpStateName.intValue();
		}
	} };

	public StateFilter(StateName.Enum[] stateNames) {
		super();
		
		predicateStateNames = new Predicate [stateNames.length] ;
		int i = 0;
		for (StateName.Enum stateName : stateNames) {

			Predicate predicate = null;

			switch (stateName.intValue()) {
			case StateName.INT_CANCELLED:
				predicate = new Predicate() {
					public boolean evaluate(Object object) {
						LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
						StateName.Enum tmpStateName = liveStateInfo.getStateName();
						return StateName.CANCELLED.intValue() == tmpStateName.intValue();
					}
				};
				break;
			case StateName.INT_FAILED:
				predicate = new Predicate() {
					public boolean evaluate(Object object) {
						LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
						StateName.Enum tmpStateName = liveStateInfo.getStateName();
						return StateName.FAILED.intValue() == tmpStateName.intValue();
					}
				};
				break;
			case StateName.INT_FINISHED:
				predicate = new Predicate() {
					public boolean evaluate(Object object) {
						LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
						StateName.Enum tmpStateName = liveStateInfo.getStateName();
						return StateName.FINISHED.intValue() == tmpStateName.intValue();
					}
				};
				break;
			case StateName.INT_PENDING:
				predicate = new Predicate() {
					public boolean evaluate(Object object) {
						LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
						StateName.Enum tmpStateName = liveStateInfo.getStateName();
						return StateName.PENDING.intValue() == tmpStateName.intValue();
					}
				};
				break;
			case StateName.INT_RUNNING:
				predicate = new Predicate() {
					public boolean evaluate(Object object) {
						LiveStateInfo liveStateInfo = ((AbstractJobType) object).getStateInfos().getLiveStateInfos().getLiveStateInfoArray(0);
						StateName.Enum tmpStateName = liveStateInfo.getStateName();
						return StateName.RUNNING.intValue() == tmpStateName.intValue();
					}
				};
				break;

			default:
				break;
			}

			predicateStateNames[i++] = predicate;
		}

	}

	public Predicate anyPredicate() {
		return PredicateUtils.anyPredicate(predicateStateNames);
	}

}
