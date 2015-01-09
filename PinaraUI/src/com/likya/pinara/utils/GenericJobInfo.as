package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.JobEditWindow;
	
	public class GenericJobInfo {
		
		public static function getXML(j:JobEditWindow):XML {
			
			var myraGenericJob:XML = <genericJob/>;
			
			myraGenericJob.@handlerURI = "com.likya.myra.jef.jobs.ExecuteInShell";
			myraGenericJob.@Id = "22";
			myraGenericJob.@groupId = "my_group";
			myraGenericJob.@agentId = "1";
			
			myraGenericJob = getBaseJobInfos(j, myraGenericJob);
			
			myraGenericJob = getScheduleInfo(j, myraGenericJob);
			
			return myraGenericJob;
			
		}
		
		private static function getScheduleInfo(j:JobEditWindow, myraGenericJob:XML):XML {

			myraGenericJob.scheduleInfo = <scheduleInfo/>;

			if(j.scheduleInfoForm.scheduleType.selection.id == "everyDaySchedule") {

				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>1</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>2</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>3</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>4</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>5</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>6</daysOfWeekIntType>);
				myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>7</daysOfWeekIntType>);

			} else {
				
				if(j.scheduleInfoForm.fdom.selected) {
					myraGenericJob.scheduleInfo.appendChild(<firstDayOfMonth/>);
				}

				if(j.scheduleInfoForm.ldom.selected) {
					myraGenericJob.scheduleInfo.appendChild(<lastDayOfMonth/>);
				}

				if(j.scheduleInfoForm.specDays.text.length > 0) {
					var specDays:String = j.scheduleInfoForm.specDays.text;
					
					var results:Array = specDays.split(',');
					
					for each (var item:String in results) {
						
						if(item.indexOf('-') > 0) {
							var dashList:Array = item.split('-');
							if(dashList.length == 1) {
								myraGenericJob.scheduleInfo.appendChild(<days>{dashList[0]}</days>);
							} else if(dashList.length > 1) {
								var counter:int = 0;								
								for each (var dashitem:String in dashList) {
									myraGenericJob.scheduleInfo.appendChild(<days>{dashList[counter ++]}</days>);
								}
							}
						} else {
							myraGenericJob.scheduleInfo.appendChild(<days>{item}</days>);
						}

					}
				}
				
				if(j.scheduleInfoForm.d1.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>1</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d2.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>2</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d3.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>3</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d4.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>4</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d5.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>5</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d6.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>6</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d7.selected) {
					myraGenericJob.scheduleInfo.appendChild(<daysOfWeekIntType>7</daysOfWeekIntType>);
				}

			}
			
			
			return myraGenericJob;;
			
			// return XML(j.scheduleInfoForm.scheduleType.selection.id);
			
			
			// return XML(j.scheduleInfoForm.everyDaySchedule.selected);
			
			//if(j.scheduleInfo.scheduleType;
			
			// myraGenericJob.scheduleInfo.jsName = 
			
			
/*			<s:VGroup>
				<s:RadioButton group="{scheduleType}" 
							   id="everyDaySchedule"
							   value="everyDaySchedule"
							   label="Execute Everyday" 
							   width="150"/>
				<s:RadioButton group="{scheduleType}" 
							   id="specificSchedule"
							   value="specificSchedule"
							   label="Execute On Day(s) Of Month" 
							   width="200"/>
			</s:VGroup>
			
			
			<s:VGroup enabled="true" id="dom">
				<s:FormItem label="">
					<s:CheckBox width="150" label="First Day Of Month"/>
				</s:FormItem>
				<s:FormItem label="">
					<s:CheckBox width="150" label="Last Day Of Month"/>
				</s:FormItem>
				<s:FormItem label="Day list">
					<s:TextInput id="specDays" width="100%"/>
					<s:helpContent>
						<s:Label text="E.g. 1,5,9-13"/>
					</s:helpContent>
				</s:FormItem>
				<s:FormItem label="Days of Week">
					<s:CheckBox width="150" label="Monday" id="d1"/>
					<s:CheckBox width="150" label="Tuesday" id="d2"/>
					<s:CheckBox width="150" label="Wednesday" id="d3"/>
					<s:CheckBox width="150" label="Thursday" id="d4"/>
					<s:CheckBox width="150" label="Friday" id="d5"/>
					<s:CheckBox width="150" label="Saturday" id="d6"/>
					<s:CheckBox width="150" label="Sunday" id="d7"/>
				</s:FormItem>
			</s:VGroup>*/
		}
		
		private static function getBaseJobInfos(j:JobEditWindow, myraGenericJob:XML):XML {
			
			myraGenericJob.baseJobInfos.jsName = j.baseInfoForm.jsName.text;
			myraGenericJob.baseJobInfos.jobLogFile = j.baseInfoForm.jsJobLogFile.text;
			myraGenericJob.baseJobInfos.jobLogPath = j.baseInfoForm.jsJobLogPath.text;
			myraGenericJob.baseJobInfos.oSystem = j.baseInfoForm.jsOsType.selectedItem;
			myraGenericJob.baseJobInfos.jobPriority = j.baseInfoForm.jsJobPriority.selectedItem;
			myraGenericJob.baseJobInfos.jsIsActive = j.baseInfoForm.jsJobActivity.selectedItem;
			myraGenericJob.baseJobInfos.userId = "serkan";
			
			myraGenericJob.baseJobInfos.jobTypeDetails.jobCommandType = j.baseInfoForm.jsJobType.selectedItem;
			myraGenericJob.baseJobInfos.jobTypeDetails.jobCommand = j.baseInfoForm.jsCommand.text;
			myraGenericJob.baseJobInfos.jobTypeDetails.jobPath = j.baseInfoForm.jsJobPath.text; 
			myraGenericJob.baseJobInfos.jobTypeDetails.argValues = j.baseInfoForm.jsJobArgs.text; 
			
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables = <envVariables></envVariables>;
			
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry.@key = "0";
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry = "value_0";
			
			// var envVarsXML:XML = XML(myraGenericJob.baseJobInfos.jobTypeDetails.envVariables); //myraGenericJob.baseJobInfos.jobTypeDetails;
			
			var envVarsXML:XML = <envVariables/>;
			
			for each (var item:String in j.baseInfoForm.envVarList.dataProvider.toArray()) {
				// var xmlText:String = "<entry key=\"" + item + "\">" + item + "</entry>";
				var xmlText:String = "<entry>" + item + "</entry>";
				envVarsXML.appendChild(XML(xmlText));
			}
			
			myraGenericJob.baseJobInfos.jobTypeDetails.envVariables = envVarsXML;
			
			// myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry[0].@key = "0"; // j.baseInfoForm.envVarList.dataProvider.toArray();
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry = "value_0"; // j.baseInfoForm.envVarList.dataProvider.toArray();
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry.@key = "1"; 
			//myraGenericJob.baseJobInfos.jobTypeDetails.envVariables.entry = "value_1"; 
			
			// return envVarsXML;
			
			return myraGenericJob;
		}
		
	}
}

