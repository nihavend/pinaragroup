package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.DependencyListForm;
	import com.likya.pinara.comps.jobcrud.JobBaseTypeInfoForm;
	import com.likya.pinara.comps.jobcrud.JobManagementInfoForm;
	import com.likya.pinara.comps.jobcrud.LogAnalysisForm;
	import com.likya.pinara.comps.jobcrud.ScheduleInfoForm;
	import com.likya.pinara.comps.jobcrud.StateInfosForm;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.controls.DateField;
	import mx.formatters.DateFormatter;
	
	public class JobXmlToView {
		
		public static function indexOf(label:String, arr:Array):int {
			
			var found:Boolean = false;
			var count:int = 0;
			for each (var item:Object in arr) {
				if(item.value == label) {
					found = true;
					break;
				}
				count++;
			}
			
			if(!found) count = -1;
			
			return count;
		}
		
		public static function prepare_baseInfoForm(baseInfoForm:JobBaseTypeInfoForm, jobDetailXml:XML):void {
			
			baseInfoForm.jsName.text = jobDetailXml.baseJobInfos.jsName;
			baseInfoForm.jsCommand.text = jobDetailXml.baseJobInfos.jobTypeDetails.jobCommand;
			baseInfoForm.jsJobPath.text = jobDetailXml.baseJobInfos.jobTypeDetails.jobPath;
			// Alert.show("" + jobDetailXml.baseJobInfos.jobTypeDetails.jobCommandType);
			baseInfoForm.jsJobType.selectedItem = "" + jobDetailXml.baseJobInfos.jobTypeDetails.jobCommandType;
			// Alert.show(baseInfoForm.jsJobType.selectedItem);
			baseInfoForm.jsJobArgs.text = jobDetailXml.baseJobInfos.jobTypeDetails.argValues;
			
			for each (var item:Object in jobDetailXml.baseJobInfos.jobTypeDetails.envVariables.entry) {
				baseInfoForm.envVarList.dataProvider.addItem(item.@key + "=" + item);
			}
			
			baseInfoForm.jsJobLogFile.text = jobDetailXml.baseJobInfos.jobLogFile;
			baseInfoForm.jsJobLogPath.text = jobDetailXml.baseJobInfos.jobLogPath;
			
			baseInfoForm.jsOsType.selectedItem = "" + jobDetailXml.baseJobInfos.oSystem;
			
			baseInfoForm.jsJobPriority.selectedItem = "" + jobDetailXml.baseJobInfos.jobPriority;
			baseInfoForm.jsJobActivity.selectedItem = "" + jobDetailXml.baseJobInfos.jsIsActive;
			
			/** 
			 * will be considered after dependecy things  : 
			 * groupId.text = jobDetailXml.@groupId;
			 * 
			 */
		}
		
		public static function prepare_stateInfosForm(stateInfosForm:StateInfosForm, jobDetailXml:XML):void {
			
			for each (var item:Object in jobDetailXml.stateInfos.JobStatusList.JobStatus) {
				
				var retList:ArrayList = new ArrayList();
				for each (var innerItem:Object in item.ReturnCodeList.ReturnCode) {
					retList.addItem(innerItem.Code + ":" + innerItem.Desc);
				}
				
				var stateArray:Object = {statusname:item.StatusName, desc:item.Desc, retCodeList:retList};
				stateInfosForm.statusInfoGrid.dataProvider.addItem(stateArray);
			}
			
		}
		
		public static function prepare_dependencyListForm(dependencyListForm:DependencyListForm, jobDetailXml:XML):void {
			
			for each (var item:Object in jobDetailXml.DependencyList.Item) {
				var stateComposite:String = item.jsDependencyRule.StateName + ":" + item.jsDependencyRule.SubstateName + ":" + item.jsDependencyRule.StatusName;
				var depArray:Object = {depid:item.@dependencyID, jobinfo:item.jsId + ":" + item.jsName, stateinfo:stateComposite , comment:item.comment };
				dependencyListForm.dependencyListGrid.dataProvider.addItem(depArray);
			}
			dependencyListForm.depExp.text = "" + jobDetailXml.DependencyList.DependencyExpression;
		}
		
		public static function prepare_managementInfoForm(managementInfoForm:JobManagementInfoForm, jobDetailXml:XML):void {
			
			var mXML:XMLList = jobDetailXml.management;
			
			var selectedCombo:Number = JobXmlToView.indexOf(mXML.trigger, managementInfoForm.jsJobTriggerType.dataProvider.toArray());
			
			managementInfoForm.jsJobTriggerType.selectedIndex = selectedCombo;
			
			if(selectedCombo == 1) {
				managementInfoForm.handleDecoration(false);
			} else {
				managementInfoForm.handleDecoration(true);
				if(mXML.hasOwnProperty("periodInfo")) {
					managementInfoForm.periodInfo.selected = true;
					managementInfoForm.relativeStart.selectedItem = "" + mXML.periodInfo.@relativeStart;
					managementInfoForm.stepValue.text = mXML.periodInfo.@step;
					managementInfoForm.maxCountValue.text = mXML.periodInfo.@maxCount;
				} else {
					managementInfoForm.periodInfo.selected = false;
				}
				
				managementInfoForm.periodInfoDecoration();
				
				// managementInfoForm.bdate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.startTime.split('T')[0]);
				managementInfoForm.bdate.selectedDate = DateField.stringToDate(mXML.timeManagement.jsPlannedTime.startTime.split('T')[0], "YYYY-MM-DD");
				
				var pairs:Array = mXML.timeManagement.jsPlannedTime.startTime.split('T')[1].split(".")[0].split(":");
				managementInfoForm.bhour.text = pairs[0];
				managementInfoForm.bminute.text = pairs[1];
				managementInfoForm.bsecond.text = pairs[2];
				
				// managementInfoForm.edate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.stopTime.split('T')[0]);	
				managementInfoForm.edate.selectedDate = DateField.stringToDate(mXML.timeManagement.jsPlannedTime.stopTime.split('T')[0], "YYYY-MM-DD");
				
				pairs = mXML.timeManagement.jsPlannedTime.stopTime.split('T')[1].split(".")[0].split(":");
				managementInfoForm.ehour.text = pairs[0];
				managementInfoForm.eminute.text = pairs[1];
				managementInfoForm.esecond.text = pairs[2];
				
				managementInfoForm.timoutValue.text = mXML.timeManagement.jsTimeOut.value_integer;
				managementInfoForm.timeoutUnit.selectedItem = "" + mXML.timeManagement.jsTimeOut.unit;
				
				managementInfoForm.expectedValue.text = mXML.timeManagement.expectedTime.value_integer;
				managementInfoForm.expectedTimeUnit.selectedItem = "" + mXML.timeManagement.expectedTime.unit;
				
				
				managementInfoForm.runEvenFailed.selectedItem = "" + mXML.cascadingConditions.runEvenIfFailed;
				managementInfoForm.safeToRestart.selectedItem = "" + mXML.cascadingConditions.jobSafeToRestart;
				
				managementInfoForm.autoRetry.selectedItem = "" + mXML.cascadingConditions.jobAutoRetryInfo.jobAutoRetry;
				
				managementInfoForm.arStepValue.text = mXML.cascadingConditions.jobAutoRetryInfo.@step;
				managementInfoForm.maxCountValueAr.text = mXML.cascadingConditions.jobAutoRetryInfo.@maxCount;
			}
			
		}
		
		public static function prepare_logAnalysisForm(logAnalysisForm:LogAnalysisForm, jobDetailXml:XML):void {
			
			if(!jobDetailXml.hasOwnProperty("logAnalysis")) {
				logAnalysisForm.disableLA.selected = true;	
				logAnalysisForm.handleDecoration("false");
				return;
			}
			
			var isLogAnalyseEnable:Boolean = jobDetailXml.logAnalysis.@active;
			
			if(isLogAnalyseEnable) {
				logAnalysisForm.enableLA.selected = true;
			} else {
				logAnalysisForm.disableLA.selected = true;				
			}
			// findWhat direction="Down" matchCase="false" matchWholeWordOnly="false" mode="regEx">string</findWhat>
			logAnalysisForm.searchDirection.selectedItem = "" + jobDetailXml.logAnalysis.findWhat.@direction;
			
			if(("" + jobDetailXml.logAnalysis.findWhat.@mode == "regEx")) {
				logAnalysisForm.patternType.selectedItem = "Regex";
			} else {
				logAnalysisForm.patternType.selectedItem = "String";
			}
			
			if(("" + jobDetailXml.logAnalysis.findWhat.@matchCase == "true")) {
				logAnalysisForm.matchCase.selected = true;
			}

			if(("" + jobDetailXml.logAnalysis.findWhat.@matchWholeWordOnly == "true")) {
				logAnalysisForm.matchWholeWordOnly.selected = true;
			}
			
			logAnalysisForm.searchPattern.text = jobDetailXml.logAnalysis.findWhat;
			
			// Then Action Definition
			logAnalysisForm.thenLogLineNumBack.text = jobDetailXml.logAnalysis.action.then.event.content.@logLineNumBack;
			logAnalysisForm.thenLogLineNumForward.text = jobDetailXml.logAnalysis.action.then.event.content.@logLineNumForward;
			logAnalysisForm.thenActionContent.text = jobDetailXml.logAnalysis.action.then.event.content;
			
			logAnalysisForm.thenSSS.stateName.selectedItem = "" + jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.StateName;
			logAnalysisForm.thenSSS.stateName_changeHandler(null);
			logAnalysisForm.thenSSS.refreshSubstateNameCombo();
			
			if(jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.SubstateName != null) {
				logAnalysisForm.thenSSS.substateName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.SubstateName, logAnalysisForm.thenSSS.substateName.dataProvider.toArray());
				logAnalysisForm.thenSSS.substateName_changeHandler(null);
				if(logAnalysisForm.thenSSS.statusName.dataProvider != null) {
					(logAnalysisForm.thenSSS.statusName.dataProvider as ArrayCollection).refresh();
				}
			}
			
			if(jobDetailXml.logAnalysis.then.event.forcedResult.LiveStateInfo.StatusName != null) {
				logAnalysisForm.thenSSS.statusName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.StatusName, logAnalysisForm.thenSSS.statusName.dataProvider.toArray());
			}
			
			logAnalysisForm.thenRetCode.text = jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.ReturnCode.Code;
			logAnalysisForm.thenRetCodeDesc.text = jobDetailXml.logAnalysis.action.then.forcedResult.LiveStateInfo.ReturnCode.Desc;
			
			// Else Action Definition
			logAnalysisForm.elseLogLineNumBack.text = jobDetailXml.logAnalysis.action["else"].event.content.@logLineNumBack;
			logAnalysisForm.elseLogLineNumForward.text = jobDetailXml.logAnalysis.action["else"].event.content.@logLineNumForward;
			logAnalysisForm.elseActionContent.text = jobDetailXml.logAnalysis.action["else"].event.content;
			
			logAnalysisForm.elseSSS.stateName.selectedItem = "" + jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.StateName;
			logAnalysisForm.elseSSS.stateName_changeHandler(null);
			logAnalysisForm.elseSSS.refreshSubstateNameCombo();
			
			if(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.SubstateName != null) {
				logAnalysisForm.elseSSS.substateName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.SubstateName, logAnalysisForm.elseSSS.substateName.dataProvider.toArray());
				logAnalysisForm.elseSSS.substateName_changeHandler(null);
				if(logAnalysisForm.elseSSS.statusName.dataProvider != null) {
					(logAnalysisForm.elseSSS.statusName.dataProvider as ArrayCollection).refresh();
				}
			}
			
			if(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.StatusName != null) {
				logAnalysisForm.elseSSS.statusName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.StatusName, logAnalysisForm.elseSSS.statusName.dataProvider.toArray());
			}
			
			logAnalysisForm.elseRetCode.text = jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.ReturnCode.Code;
			logAnalysisForm.elseRetCodeDesc.text = jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.ReturnCode.Desc;
		}
		
		public static function prepare_scheduleInfoForm(scheduleInfoForm:ScheduleInfoForm, jobDetailXml:XML):void {

			
			var isEveryDayExecute:Boolean = isEveryday(jobDetailXml.scheduleInfo.daysOfWeekIntType);
			if(isEveryDayExecute) {
				scheduleInfoForm.everyDaySchedule.selected = true;
				scheduleInfoForm.handleDecoration("everyDaySchedule");
			} else {
				scheduleInfoForm.specificSchedule.selected = true;
				scheduleInfoForm.handleDecoration("specificSchedule");
				
				if(jobDetailXml.scheduleInfo.daysOfMonth.firstDayOfMonth != null) {
					scheduleInfoForm.fdom.selected = true;
				}
				
				if(jobDetailXml.scheduleInfo.daysOfMonth.lastDayOfMonth != null) {
					scheduleInfoForm.ldom.selected = true;
				}
				
				for each (var item:Object in jobDetailXml.scheduleInfo.daysOfMonth.days) {
					scheduleInfoForm.specDays.text += item + ",";
				}
				
				if(scheduleInfoForm.specDays.text.lastIndexOf(",") == scheduleInfoForm.specDays.text.length - 1) {
					scheduleInfoForm.specDays.text = scheduleInfoForm.specDays.text.substr(0, scheduleInfoForm.specDays.text.length - 1);
				}
				
				for each (var weekdayitem:Object in jobDetailXml.scheduleInfo.daysOfWeekIntType) {
					
					switch("" + weekdayitem)
					{
						case "1":
						{
							scheduleInfoForm.d1.selected = true;
							break;
						}
						case "2":
						{
							scheduleInfoForm.d2.selected = true;
							break;
						}
						case "3":
						{
							scheduleInfoForm.d3.selected = true;
							break;
						}
						case "4":
						{
							scheduleInfoForm.d4.selected = true;
							break;
						}
						case "5":
						{
							scheduleInfoForm.d5.selected = true;
							break;
						}
						case "6":
						{
							scheduleInfoForm.d6.selected = true;
							break;
						}
						case "7":
						{
							scheduleInfoForm.d7.selected = true;
							break;
						}
						default:
						{
							break;
						}
					}
				}
					
			}
		}
		
		private static function xmlDateToNormal(text:String):String {
			
			// String to date
			var sDate:Date = DateField.stringToDate(text, "YYYY-MM-DD");
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "DD/MM/YYYY";
			var dateString:String = currentDF.format(sDate);
			
			return dateString;
		}
		
		private static function isEveryday(myList:XMLList):Boolean {
			var found:Boolean = false;
			var i:int;
			for (i = 1; i < 8; i++) {
				// trace("i : " + i);
				found = false;
				for each (var item:Object in myList) {
					// trace(item);
					if(i == Number(item)) {
						found = true;
						break;
					}
				}
				
				if(!found) {
					break;
				}
			}
			return found;
		}
	}
}

