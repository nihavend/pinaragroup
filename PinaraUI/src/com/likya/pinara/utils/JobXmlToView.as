package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.JobBaseTypeInfoForm;
	import com.likya.pinara.comps.jobcrud.JobManagementInfoForm;
	import com.likya.pinara.comps.jobcrud.ScheduleInfoForm;
	import com.likya.pinara.comps.jobcrud.StateInfosForm;
	
	import mx.collections.ArrayList;
	import mx.controls.DateField;
	import mx.formatters.DateFormatter;
	
	public class JobXmlToView {
		
		public static function indexOf(label:String, arr:Array):int {
			var count:int = 0;
			for each (var item:Object in arr) {
				if(item.value == label) {
					break;
				}
				count++;
			}
			
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
		
		public static function prepare_managementInfoForm(managementInfoForm:JobManagementInfoForm, jobDetailXml:XML):void {
			
			var mXML:XMLList = jobDetailXml.management;
			
			managementInfoForm.jsJobTriggerType.selectedIndex = JobXmlToView.indexOf(mXML.trigger, managementInfoForm.jsJobTriggerType.dataProvider.toArray());
			
			managementInfoForm.relativeStart.selectedItem = "" + mXML.periodInfo.@relativeStart;
			managementInfoForm.stepValue.text = mXML.periodInfo.@step;
			managementInfoForm.maxCountValue.text = mXML.periodInfo.@maxCount;
			
			managementInfoForm.bdate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.startTime.split('T')[0]);
			
			var pairs:Array = mXML.timeManagement.jsPlannedTime.startTime.split('T')[1].split(".")[0].split(":");
			managementInfoForm.bhour.text = pairs[0];
			managementInfoForm.bminute.text = pairs[1];
			managementInfoForm.bsecond.text = pairs[2];
			
			managementInfoForm.edate.text = xmlDateToNormal(mXML.timeManagement.jsPlannedTime.stopTime.split('T')[0]);		
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
		
		public static function prepare_scheduleInfoForm(scheduleInfoForm:ScheduleInfoForm, jobDetailXml:XML):void {

		}
		
		private static function xmlDateToNormal(text:String):String {
			
			// String to date
			var sDate:Date = DateField.stringToDate(text, "YYYY-MM-DD");
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "DD/MM/YYYY";
			var dateString:String = currentDF.format(sDate);
			
			return dateString;
		}
		
	}
}

