package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.DependencyListForm;
	import com.likya.pinara.comps.jobcrud.JobBaseTypeInfoForm_0;
	import com.likya.pinara.comps.jobcrud.JobBaseTypeInfoForm_1;
	import com.likya.pinara.comps.jobcrud.JobManagementInfoForm_0;
	import com.likya.pinara.comps.jobcrud.JobManagementInfoForm_1;
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
		
		public static function prepare_baseInfoForm(baseInfoForm_0:JobBaseTypeInfoForm_0, baseInfoForm_1:JobBaseTypeInfoForm_1, jobDetailXml:XML):void {
			
			baseInfoForm_0.jsJobGroup.text = jobDetailXml.@groupId;
			baseInfoForm_0.jsName.text = jobDetailXml.baseJobInfos.jsName;
			baseInfoForm_0.jsCommand.text = jobDetailXml.baseJobInfos.jobTypeDetails.jobCommand;
			baseInfoForm_0.jsJobWorkDir.text = jobDetailXml.baseJobInfos.jobTypeDetails.jobWorkDir;
			// Alert.show("" + jobDetailXml.baseJobInfos.jobTypeDetails.jobCommandType);
			baseInfoForm_0.jsJobType.selectedItem = "" + jobDetailXml.baseJobInfos.jobTypeDetails.jobCommandType;
			// Alert.show(baseInfoForm.jsJobType.selectedItem);
			baseInfoForm_0.jsJobArgs.text = jobDetailXml.baseJobInfos.jobTypeDetails.argValues;
			
			for each (var item:Object in jobDetailXml.baseJobInfos.jobTypeDetails.envVariables.entry) {
				baseInfoForm_1.envVarList.dataProvider.addItem(item.@key + "=" + item);
			}
			
			baseInfoForm_1.jsJobLogFile.text = jobDetailXml.baseJobInfos.jobLogFile;
			baseInfoForm_1.jsJobLogPath.text = jobDetailXml.baseJobInfos.jobLogPath;
			
			//baseInfoForm.jsOsType.selectedItem = "" + jobDetailXml.baseJobInfos.oSystem;
			
			baseInfoForm_1.jsJobPriority.selectedItem = "" + jobDetailXml.baseJobInfos.jobPriority;
			baseInfoForm_1.jsJobActivity.selectedItem = "" + jobDetailXml.baseJobInfos.jsIsActive;
			
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
			
			dependencyListForm.filterId = jobDetailXml.@Id;
			for each (var item:Object in jobDetailXml.DependencyList.Item) {
				var stateComposite:String = item.jsDependencyRule.StateName + ":" + item.jsDependencyRule.SubstateName + ":" + item.jsDependencyRule.StatusName;
				var depArray:Object = {depid:item.@dependencyID, jobinfo:item.jsId + ":" + item.jsName, stateinfo:stateComposite , comment:"" + item.comment }; // çift tırnak ile string yapmaz isek, tag ile birlikte node alınıyor, içerik değil.
				dependencyListForm.dependencyListGrid.dataProvider.addItem(depArray);
			}
			dependencyListForm.depExp.text = "" + jobDetailXml.DependencyList.DependencyExpression;
		}
		
		public static function prepare_managementInfoForm(managementInfoForm_0:JobManagementInfoForm_0, managementInfoForm_1:JobManagementInfoForm_1, jobDetailXml:XML):void {
			
			var mXML:XMLList = jobDetailXml.management;
			
			var selectedCombo:Number = JobXmlToView.indexOf(mXML.trigger, managementInfoForm_0.jsJobTriggerType.dataProvider.toArray());
			
			managementInfoForm_0.jsJobTriggerType.selectedIndex = selectedCombo;
			
			if(selectedCombo == 1) {
				managementInfoForm_0.handleDecoration(false);
				managementInfoForm_1.handleDecoration(false);
				prepare_timeManagement(managementInfoForm_0, mXML);
			} else {
				managementInfoForm_0.handleDecoration(true);
				managementInfoForm_1.handleDecoration(true);
				if(mXML.hasOwnProperty("periodInfo")) {
					managementInfoForm_1.periodInfo.selected = true;
					managementInfoForm_1.relativeStart.selectedItem = "" + mXML.periodInfo.@relativeStart;
					managementInfoForm_1.stepValue.text = mXML.periodInfo.@step;
					managementInfoForm_1.maxCountValue.text = mXML.periodInfo.@maxCount;
				} else {
					managementInfoForm_1.periodInfo.selected = false;
				}
				
				managementInfoForm_1.periodInfoDecoration();
				
				prepare_timeManagement(managementInfoForm_0, mXML);
				
				managementInfoForm_1.runEvenFailed.selectedItem = "" + mXML.cascadingConditions.runEvenIfFailed;
				managementInfoForm_1.safeToRestart.selectedItem = "" + mXML.cascadingConditions.jobSafeToRestart;
				
				if(mXML.cascadingConditions.hasOwnProperty("jobAutoRetryInfo")) {
					managementInfoForm_1.autoRetry.selectedItem = "" + mXML.cascadingConditions.jobAutoRetryInfo.jobAutoRetry;
					managementInfoForm_1.arStepValue.text = mXML.cascadingConditions.jobAutoRetryInfo.@step;
					managementInfoForm_1.maxCountValueAr.text = mXML.cascadingConditions.jobAutoRetryInfo.@maxCount;
				} else {
					managementInfoForm_1.autoRetry.selectedItem = "false";
				}
				
			}
			
		}
		
		public static function getDatePart(pXML:String):Date {
			return DateField.stringToDate(pXML.split('T')[0], "YYYY-MM-DD");
		}
		
		public static function getTimePart(pXML:String):Array {
			return pXML.split('T')[1].split(".")[0].split(":");
		}
		
		public static function prepare_timeManagement(managementInfoForm:JobManagementInfoForm_0, mXML:XMLList):void {
			
			var tmXML:XMLList = mXML.timeManagement;
			var pairs:Array;
			
			/**
			 * Aşağıdaki kısmı disable ettik, çünkü çalşışma aralığı analizi yeterli değil
			 * Analiz bittiğinde tekrar gözden geçireceğim.
			 * İsim : Serkan Taş
			 * Tarih : 20.11.2016
			 * Hedef : 2.0.0 Sürümü (GUI replacement sonrası)
			 */
			/*
			if(tmXML.hasOwnProperty("jsExecutionTimeFrame")) {
				var tfXML:XMLList = mXML.timeManagement.jsExecutionTimeFrame;
				if(tfXML.hasOwnProperty("startTime")) {
					managementInfoForm.timeFrameStart.selected = true;
					managementInfoForm.tFbdate.selectedDate = getDatePart(tfXML.startTime);
					var pairs:Array = getTimePart(tfXML.startTime);
					managementInfoForm.tFbhour.value = pairs[0];
					managementInfoForm.tFbminute.value = pairs[1];
					managementInfoForm.tFbsecond.value = pairs[2];
					
					managementInfoForm.tFStartTime.enabled = true; 
				} else {
					managementInfoForm.tFStartTime.enabled = false;
				}
				
				if(tfXML.hasOwnProperty("stopTime")) {
					managementInfoForm.timeFrameStop.selected = true;
					managementInfoForm.tFedate.selectedDate = getDatePart(tfXML.stopTime);
					pairs = getTimePart(tfXML.stopTime);
					managementInfoForm.tFehour.value = pairs[0];
					managementInfoForm.tFeminute.value = pairs[1];
					managementInfoForm.tFesecond.value = pairs[2];
					
					managementInfoForm.tFStopTime.enabled = true; 
				} else {
					managementInfoForm.tFStopTime.enabled = false;
				}
			}
			*/
			
			if(tmXML.hasOwnProperty("jsScheduledTime")) {
				tmXML = mXML.timeManagement.jsScheduledTime;
				if(tmXML.hasOwnProperty("startTime")) {
					managementInfoForm.bdate.selectedDate = getDatePart(tmXML.startTime);
					pairs = getTimePart(tmXML.startTime);
					managementInfoForm.bhour.value = pairs[0];
					managementInfoForm.bminute.value = pairs[1];
					managementInfoForm.bsecond.value = pairs[2];
				}
			}
			
			
			
//			if(tmXML.hasOwnProperty("jsPlannedTime")) {
//				
//				/**
//				 * jsPlannedTime : Planlanan Zaman demek, bir işin çalışacağı aralığını tanımı demektir. Bu aralığın başlangıç zamanına
//				 * jsPlannedTime.startTime, bitiş zamanına ise jsPlannedTime.stopTime denir.
//				 * 
//				 * Bu zamanların, işin başladığı ve bittiği zaman ile bir alakası yoktur.
//				 */
//				
//				if(tmXML.jsPlannedTime.hasOwnProperty("startTime")) {
//			
//					// jsPlannedTime.startTime
//					// managementInfoForm.bdate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.startTime.split('T')[0]);
//					managementInfoForm.bdate.selectedDate = DateField.stringToDate(mXML.timeManagement.jsPlannedTime.startTime.split('T')[0], "YYYY-MM-DD");
//					
//					var pairs:Array = mXML.timeManagement.jsPlannedTime.startTime.split('T')[1].split(".")[0].split(":");
//					// managementInfoForm.bhour.text = pairs[0];
//					managementInfoForm.bhour.value = pairs[0];
//					// managementInfoForm.bminute.text = pairs[1];
//					managementInfoForm.bminute.value = pairs[1];
//					// managementInfoForm.bsecond.text = pairs[2];
//					managementInfoForm.bsecond.value = pairs[2];
//					
//					// managementInfoForm.selectStartCond.selected = true;
//					managementInfoForm.startTime.enabled = true;
//					
//				}
//				
//				if(tmXML.jsPlannedTime.hasOwnProperty("stopTime")) {
//				
//					// jsPlannedTime.stopTime
//					managementInfoForm.tFedate.selectedDate = DateField.stringToDate(mXML.timeManagement.jsPlannedTime.stopTime.split('T')[0], "YYYY-MM-DD");
//					pairs = mXML.timeManagement.jsPlannedTime.stopTime.split('T')[1].split(".")[0].split(":");
//					//managementInfoForm.ehour.value = pairs[0];
//					//managementInfoForm.eminute.value = pairs[1];
//					//managementInfoForm.esecond.value = pairs[2];
//				
//					/*
//					// managementInfoForm.edate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.stopTime.split('T')[0]);
//					pairs = mXML.timeManagement.jsPlannedTime.stopTime.split('T')[1].split(".")[0].split(":");
//					managementInfoForm.ehour.text = pairs[0];
//					managementInfoForm.eminute.text = pairs[1];
//					managementInfoForm.esecond.text = pairs[2];
//					*/
//					
//					//managementInfoForm.selectStopCond.selected = true;
//					//managementInfoForm.stopTime.enabled = true;
//				}

//				managementInfoForm.timoutValue.text = mXML.timeManagement.jsTimeOut.value_integer;
//				managementInfoForm.timeoutUnit.selectedItem = "" + mXML.timeManagement.jsTimeOut.unit;
//				
//				managementInfoForm.expectedValue.text = mXML.timeManagement.expectedTime.value_integer;
//				managementInfoForm.expectedTimeUnit.selectedItem = "" + mXML.timeManagement.expectedTime.unit;
				
				// }
		}
		
		public static function prepare_logAnalysisForm(logAnalysisForm:LogAnalysisForm, jobDetailXml:XML):void {
			
			if(!jobDetailXml.hasOwnProperty("logAnalysis")) {
				logAnalysisForm.currentState = "undefined";
				// logAnalysisForm.disableLA.selected = true;
				// logAnalysisForm.handleDecoration("false");
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
			logAnalysisForm.thenLogLineNumBack.text = jobDetailXml.logAnalysis.action.thencase.event.content.@logLineNumBack;
			logAnalysisForm.thenLogLineNumForward.text = jobDetailXml.logAnalysis.action.thencase.event.content.@logLineNumForward;
			logAnalysisForm.thenActionContent.text = jobDetailXml.logAnalysis.action.thencase.event.content;
			
			logAnalysisForm.thenSSS.stateName.selectedItem = "" + jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.StateName;
			logAnalysisForm.thenSSS.stateName_changeHandler(null);
			logAnalysisForm.thenSSS.refreshSubstateNameCombo();
			
			if(jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.hasOwnProperty("SubstateName")) {
				logAnalysisForm.thenSSS.substateName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.SubstateName, logAnalysisForm.thenSSS.substateName.dataProvider.toArray());
				logAnalysisForm.thenSSS.substateName_changeHandler(null);
				if(logAnalysisForm.thenSSS.statusName.dataProvider != null) {
					(logAnalysisForm.thenSSS.statusName.dataProvider as ArrayCollection).refresh();
				}
			}
			
			if(jobDetailXml.logAnalysis.thencase.event.forcedResult.LiveStateInfo.hasOwnProperty("StatusName")) {
				logAnalysisForm.thenSSS.statusName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.StatusName, logAnalysisForm.thenSSS.statusName.dataProvider.toArray());
			}
			
			logAnalysisForm.thenRetCode.text = jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.ReturnCode.Code;
			logAnalysisForm.thenRetCodeDesc.text = jobDetailXml.logAnalysis.action.thencase.forcedResult.LiveStateInfo.ReturnCode.Desc;
			
			// Else Action Definition
			logAnalysisForm.elseLogLineNumBack.text = jobDetailXml.logAnalysis.action["else"].event.content.@logLineNumBack;
			logAnalysisForm.elseLogLineNumForward.text = jobDetailXml.logAnalysis.action["else"].event.content.@logLineNumForward;
			logAnalysisForm.elseActionContent.text = jobDetailXml.logAnalysis.action["else"].event.content;
			
			logAnalysisForm.elseSSS.stateName.selectedItem = "" + jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.StateName;
			logAnalysisForm.elseSSS.stateName_changeHandler(null);
			logAnalysisForm.elseSSS.refreshSubstateNameCombo();
			
			if(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.hasOwnProperty("SubstateName")) {
				logAnalysisForm.elseSSS.substateName.selectedIndex = JobXmlToView.indexOf(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.SubstateName, logAnalysisForm.elseSSS.substateName.dataProvider.toArray());
				logAnalysisForm.elseSSS.substateName_changeHandler(null);
				if(logAnalysisForm.elseSSS.statusName.dataProvider != null) {
					(logAnalysisForm.elseSSS.statusName.dataProvider as ArrayCollection).refresh();
				}
			}
			
			if(jobDetailXml.logAnalysis.action["else"].forcedResult.LiveStateInfo.hasOwnProperty("StatusName")) {
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
				
				// if(jobDetailXml.scheduleInfo.daysOfMonth.firstDayOfMonth != null) {
				if(jobDetailXml.scheduleInfo.daysOfMonth.hasOwnProperty("firstDayOfMonth")) {
					scheduleInfoForm.fdom.selected = true;
				}
				
				// if(jobDetailXml.scheduleInfo.daysOfMonth.lastDayOfMonth != null) {
				if(jobDetailXml.scheduleInfo.daysOfMonth.hasOwnProperty("lastDayOfMonth")) {
					scheduleInfoForm.ldom.selected = true;
				}
				
				/*
				for each (var item:Object in jobDetailXml.scheduleInfo.daysOfMonth.days) {
					scheduleInfoForm.specDays.text += item + ",";
				}
				
				if(scheduleInfoForm.specDays.text.lastIndexOf(",") == scheduleInfoForm.specDays.text.length - 1) {
					scheduleInfoForm.specDays.text = scheduleInfoForm.specDays.text.substr(0, scheduleInfoForm.specDays.text.length - 1);
				}
				*/
				
				if(jobDetailXml.scheduleInfo.daysOfMonth.hasOwnProperty("daysTextRep")) {
					scheduleInfoForm.specDays.text = jobDetailXml.scheduleInfo.daysOfMonth.daysTextRep + "";
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

