package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.JobEditWindow;
	
	import mx.controls.Alert;
	import mx.formatters.DateFormatter;
	
	public class GenericJobInfo {
		
		/*namespace xsi="http://www.w3.org/2001/XMLSchema-instance"
		use namespace xsi;*/
		
		namespace myra = "http://www.likyateknoloji.com/myra-joblist";
		use namespace myra;
		
		namespace myra_jobprops="http://www.likyateknoloji.com/myra-jobprops";
		use namespace myra_jobprops;
		
		namespace wla = "http://www.likyateknoloji.com/wla-gen";
		use namespace wla;
		
		public static function getXML_1(j:JobEditWindow):XML {
			
			var managementInfoXML:XML = <myra-jobprops:management xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>;
			
			if(j.managementInfoForm.jsJobTriggerType.selectedItem.value == "USER") {
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">USER</wla-trigger>);
			} else {
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">TIME</wla-trigger>);
			}

			
			return managementInfoXML;
		}

		
		public static function getXML(j:JobEditWindow):XML {
			
			var myraJobList:XML = 
				<myra:jobList xmlns:myra="http://www.likyateknoloji.com/myra-joblist" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" xmlns:wla="http://www.likyateknoloji.com/wla-gen"
						xmlns:lik="http://www.likyateknoloji.com/likya-gen" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
					<myra:genericJob xsi:type="myra:simpleProperties" />
				</myra:jobList>;


			myraJobList.myra::genericJob.@handlerURI = "com.likya.myra.jef.jobs.ExecuteInShell";
			myraJobList.myra::genericJob.@Id = "22";
			myraJobList.myra::genericJob.@groupId = "my_group";
			myraJobList.myra::genericJob.@agentId = "1";
			
			// Alert.show("myraJobList.myra::genericJob.@handlerURI : " + myraJobList.myra::genericJob.@handlerURI);
			
			// myraGenericJob = getBaseJobInfos(j, myraGenericJob);
			// myraGenericJob = getScheduleInfo(j, myraGenericJob);
			
			// myraJobList.myra::genericJob = getManagementInfo(j, myraJobList.myra::genericJob);
			
			// var genericJob:XML = myraJobList.genericJob.@id;
			
			getManagementInfo(j, myraJobList);
			
			// myraJobList.appendChild(<myra:genericJob xsi:type="myra:simpleProperties" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:myra="http://www.likyateknoloji.com/myra-joblist"/>);
			
			return myraJobList;
			
		}
		
		private static function getManagementInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:management xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var managementInfoXML:Object = myraJobList.myra::genericJob.myra_jobprops::management;
			
			if(j.managementInfoForm.jsJobTriggerType.selectedItem.value == "USER") {
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">USER</wla-trigger>);
			} else {
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">TIME</wla-trigger>);
				
				managementInfoXML.appendChild(<myra-jobprops:periodInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				
				managementInfoXML.periodInfo.@relativeStart = j.managementInfoForm.relativeStart.selectedItem;
				managementInfoXML.periodInfo.@step = j.managementInfoForm.stepValue.text;
				managementInfoXML.periodInfo.@maxCount = j.managementInfoForm.maxCountValue.text;
				
				managementInfoXML.appendChild(<wla:timeManagement xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				
				var sDate:Date = j.managementInfoForm.bdate.selectedDate;
				var currentDF:DateFormatter = new DateFormatter();
				currentDF.formatString = "YYYY-MM-DD"
				var dateString:String = currentDF.format(sDate);
				
				dateString = dateString + "T" + j.managementInfoForm.bhour.text + ":" + j.managementInfoForm.bminute.text + ":" + j.managementInfoForm.bsecond.text + ".000+02:00";
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:bornedPlannedTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.wla::timeManagement.wla::bornedPlannedTime.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::bornedPlannedTime.appendChild(<wla:stopTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.timeManagement.bornedPlannedTime.startTime = dateString;
				managementInfoXML.timeManagement.jsPlannedTime.startTime = dateString;
				
			}
			
			// Alert.show("managementInfoXML : " + managementInfoXML);
			
/*			if(j.managementInfoForm.jsJobTriggerType.selectedItem.value == "USER") {
				myraGenericJob.management.appendChild(<trigger>USER</trigger>);
			} else {
				myraGenericJob.management.appendChild(<trigger>TIME</trigger>);
				myraGenericJob.management.periodInfo.@relativeStart = j.managementInfoForm.relativeStart.selectedItem;
				myraGenericJob.management.periodInfo.@step = j.managementInfoForm.stepValue.text;
				myraGenericJob.management.periodInfo.@maxCount = j.managementInfoForm.maxCountValue.text;
				
				myraGenericJob.management.timeManagement = <timeManagement/>;
				
				var sDate:Date = j.managementInfoForm.bdate.selectedDate;
				var currentDF:DateFormatter = new DateFormatter();
				currentDF.formatString = "YYYY-MM-DD"
				var dateString:String = currentDF.format(sDate);
				
				dateString = dateString + "T" + j.managementInfoForm.bhour.text + ":" + j.managementInfoForm.bminute.text + ":" + j.managementInfoForm.bsecond.text + ".000+02:00";
				
				myraGenericJob.timeManagement.bornedPlannedTime.startTime = dateString;
				myraGenericJob.timeManagement.jsPlannedTime.startTime = dateString;
				
				sDate = j.managementInfoForm.edate.selectedDate;
				dateString = currentDF.format(sDate);
				
				dateString = dateString + "T" + j.managementInfoForm.ehour.text + ":" + j.managementInfoForm.eminute.text + ":" + j.managementInfoForm.esecond.text + ".000+02:00";
				
				myraGenericJob.timeManagement.bornedPlannedTime.stopTime = dateString;
				myraGenericJob.timeManagement.jsPlannedTime.stopTime = dateString;
				
				myraGenericJob.timeManagement.jsTimeOut.value_integer = j.managementInfoForm.timoutValue.text;
				myraGenericJob.timeManagement.jsTimeOut.unit = j.managementInfoForm.timeoutUnit.selectedItem;
				
				myraGenericJob.timeManagement.expectedTime.value_integer = j.managementInfoForm.expectedValue.text;
				myraGenericJob.timeManagement.expectedTime.unit = j.managementInfoForm.expectedTimeUnit.selectedItem;
			}*/
			
			return myraJobList;
		}
		
		/*
		
		<s:BorderContainer id="cascCondBox" width="100%" height="240" borderWeight="2" cornerRadius="3" dropShadowVisible="true" enabled="true">
			
			<s:Label left="15" top="10" text="Cascading Conditions" fontSize="14" fontWeight="bold"/>
			
			<s:FormItem label="Run Even Failed" top="30">
				<s:DropDownList id="runEvenFailed" width="120" selectedIndex="0"> 
					<s:dataProvider>
						<mx:ArrayList>
							<fx:String>TRUE</fx:String>
							<fx:String>FALSE</fx:String>
						</mx:ArrayList>
					</s:dataProvider>
				</s:DropDownList>
				<s:helpContent>
					<s:Label text="Continue Execution On Error"/>
				</s:helpContent>
			</s:FormItem>
			
			<s:FormItem label="Is Safe To Restart" top="60">
				<s:DropDownList id="safeToRestart" width="120" selectedIndex="0"> 
					<s:dataProvider>
						<mx:ArrayList>
							<fx:String>TRUE</fx:String>
							<fx:String>FALSE</fx:String>
						</mx:ArrayList>
					</s:dataProvider>
				</s:DropDownList>
				<s:helpContent>
					<s:Label text="Safe To Restart Info"/>
				</s:helpContent>
			</s:FormItem>
			
			<s:BorderContainer id="auotRetryBox" left="10" top="100" width="90%" height="130" borderWeight="2" cornerRadius="3" dropShadowVisible="true" enabled="true">
				
				<s:Label left="15" top="10" text="Auto Retry Info" fontSize="14" fontWeight="bold"/>
				
				<s:FormItem label="Auto Retry" top="30">
					<s:DropDownList id="autoRetry" width="120" selectedIndex="0"> 
						<s:dataProvider>
							<mx:ArrayList>
								<fx:String>TRUE</fx:String>
								<fx:String>FALSE</fx:String>
							</mx:ArrayList>
						</s:dataProvider>
					</s:DropDownList>
					<s:helpContent>
						<s:Label text="Autor Retry On Error"/>
					</s:helpContent>
				</s:FormItem>
				
				<s:FormItem label="Delay between auto retry" top="60">
					<s:TextInput id="arStepValue" width="100%"/>
					<s:helpContent>
						<s:Label text="Define delay, e.g. P1D"/>
					</s:helpContent>
				</s:FormItem>
				
				<s:FormItem label="Maximum Count" top="90">
					<s:TextInput id="maxCountValueAr" width="100%"/>
					<s:helpContent>
						<s:Label text="Maximum Count of Execution"/>
					</s:helpContent>
				</s:FormItem>
				
			</s:BorderContainer>
		</s:BorderContainer>*/
		
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
			
			
			return myraGenericJob;
			
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
			
			// myraGenericJob.baseJobInfos = <myra-jobprops:baseJobInfos />;
			
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

