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
		
		namespace lik = "http://www.likyateknoloji.com/likya-gen";
		use namespace lik;
		
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
			
			// TO-DO Aşağıdaki 2 fonksiyon yeni ns yapısına uygun hale getirilecek ardından dependency yapılacak
			
			myraJobList = getBaseJobInfos(j, myraJobList);
			// myraGenericJob = getScheduleInfo(j, myraGenericJob);
			
			myraJobList = getManagementInfo(j, myraJobList);
			
			return myraJobList;
			
		}
		
		private static function getDependencyInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			// Ekran tarafı yapılmadan burası yapılamaz
			
/*			<!--  myra-jobprops:DependencyList>
				<wla:Item dependencyID="mydep">
					<wla:jsName>depJsName</wla:jsName>
					<wla:jsId>1</wla:jsId>
					<wla:jsType>JOB</wla:jsType>
					<lik:comment>no comment</lik:comment>
					<myra-stateinfo:jsDependencyRule>
						<myra-stateinfo:StateName>PENDING</myra-stateinfo:StateName>
						<myra-stateinfo:SubstateName>IDLED</myra-stateinfo:SubstateName>
						<myra-stateinfo:StatusName>BYTIME</myra-stateinfo:StatusName>
					</myra-stateinfo:jsDependencyRule>
				</wla:Item>
				<myra-jobprops:DependencyExpression>I can not express my deps !</myra-jobprops:DependencyExpression>
			</myra-jobprops:DependencyList-->*/

			return myraJobList;
		}
		
		private static function getManagementInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:management xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var managementInfoXML:Object = myraJobList.myra::genericJob.myra_jobprops::management;
			
			if(j.managementInfoForm.jsJobTriggerType.selectedItem.value == "USER") {
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">USER</wla-trigger>);
			} else {
				
				/** Trigger Info ****/
				
				managementInfoXML.appendChild(<wla-trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">TIME</wla-trigger>);
				
				/** Period Info ****/
				
				managementInfoXML.appendChild(<myra-jobprops:periodInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				
				managementInfoXML.periodInfo.@relativeStart = j.managementInfoForm.relativeStart.selectedItem;
				managementInfoXML.periodInfo.@step = j.managementInfoForm.stepValue.text;
				managementInfoXML.periodInfo.@maxCount = j.managementInfoForm.maxCountValue.text;
				
				/** Time Management ****/
				
				managementInfoXML.appendChild(<wla:timeManagement xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:bornedPlannedTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::bornedPlannedTime.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::bornedPlannedTime.appendChild(<wla:stopTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:jsPlannedTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsPlannedTime.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsPlannedTime.appendChild(<wla:stopTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:jsTimeOut xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsTimeOut.appendChild(<lik:value_integer xmlns:lik="http://www.likyateknoloji.com/likya-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsTimeOut.appendChild(<lik:unit xmlns:lik="http://www.likyateknoloji.com/likya-gen"  />);
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:expectedTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::expectedTime.appendChild(<lik:value_integer xmlns:lik="http://www.likyateknoloji.com/likya-gen" />);
				managementInfoXML.wla::timeManagement.wla::expectedTime.appendChild(<lik:unit xmlns:lik="http://www.likyateknoloji.com/likya-gen"  />);
				
				
				var sDate:Date = j.managementInfoForm.bdate.selectedDate;
				var currentDF:DateFormatter = new DateFormatter(); 
				currentDF.formatString = "YYYY-MM-DD"
				var dateString:String = currentDF.format(sDate);
				
				dateString = dateString + "T" + j.managementInfoForm.bhour.text + ":" + j.managementInfoForm.bminute.text + ":" + j.managementInfoForm.bsecond.text + ".000+02:00";
				
				managementInfoXML.timeManagement.bornedPlannedTime.startTime = dateString;
				managementInfoXML.timeManagement.jsPlannedTime.startTime = dateString;
				
				sDate = j.managementInfoForm.edate.selectedDate;
				dateString = currentDF.format(sDate);
				
				dateString = dateString + "T" + j.managementInfoForm.ehour.text + ":" + j.managementInfoForm.eminute.text + ":" + j.managementInfoForm.esecond.text + ".000+02:00";
				
				managementInfoXML.timeManagement.bornedPlannedTime.stopTime = dateString;
				managementInfoXML.timeManagement.jsPlannedTime.stopTime = dateString;
				
				managementInfoXML.timeManagement.jsTimeOut.lik::value_integer = j.managementInfoForm.timoutValue.text;
				managementInfoXML.timeManagement.jsTimeOut.lik::unit = j.managementInfoForm.timeoutUnit.selectedItem;
				
				managementInfoXML.timeManagement.expectedTime.lik::value_integer = j.managementInfoForm.expectedValue.text;
				managementInfoXML.timeManagement.expectedTime.lik::unit = j.managementInfoForm.expectedTimeUnit.selectedItem;
				
				/** Cascadşng Conditions ****/
				
				managementInfoXML.appendChild(<wla:cascadingConditions xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				
				managementInfoXML.cascadingConditions.appendChild(<wla:runEvenIfFailed xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.cascadingConditions.appendChild(<wla:jobSafeToRestart xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.cascadingConditions.runEvenIfFailed = j.managementInfoForm.runEvenFailed.selectedItem;
				managementInfoXML.cascadingConditions.jobSafeToRestart = j.managementInfoForm.safeToRestart.selectedItem;
				
				
				managementInfoXML.cascadingConditions.appendChild(<wla:jobAutoRetryInfo xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.cascadingConditions.wla::jobAutoRetryInfo.appendChild(<wla:jobAutoRetry xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.cascadingConditions.jobAutoRetryInfo.@step = j.managementInfoForm.arStepValue.text;
				managementInfoXML.cascadingConditions.jobAutoRetryInfo.@maxCount = j.managementInfoForm.maxCountValueAr.text;

				managementInfoXML.cascadingConditions.jobAutoRetryInfo.jobAutoRetry = j.managementInfoForm.autoRetry.selectedItem;
				
			}
			
			return myraJobList;
		}
		
		private static function getScheduleInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:scheduleInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var scheduleInfoXML:Object = myraJobList.myra::genericJob.myra_jobprops::scheduleInfo;
			
			if(j.scheduleInfoForm.scheduleType.selection.id == "everyDaySchedule") {

				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">1</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">2</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">3</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">4</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">5</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">6</daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">7</daysOfWeekIntType>);

			} else {
				
				if(j.scheduleInfoForm.fdom.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:firstDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}

				if(j.scheduleInfoForm.ldom.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:lastDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}

				if(j.scheduleInfoForm.specDays.text.length > 0) {
					var specDays:String = j.scheduleInfoForm.specDays.text;
					
					var results:Array = specDays.split(',');
					
					for each (var item:String in results) {
						
						if(item.indexOf('-') > 0) {
							var dashList:Array = item.split('-');
							if(dashList.length == 1) {
								scheduleInfoXML.scheduleInfo.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{dashList[0]}</myra-jobprops:days>);
							} else if(dashList.length > 1) {
								var counter:int = 0;								
								for each (var dashitem:String in dashList) {
									scheduleInfoXML.scheduleInfo.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{dashList[counter ++]}</myra-jobprops:days>);
								}
							}
						} else {
							scheduleInfoXML.scheduleInfo.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{item}</myra-jobprops:days>);
						}

					}
				}
				
				if(j.scheduleInfoForm.d1.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">1</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d2.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">2</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d3.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">3</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d4.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">4</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d5.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">5</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d6.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">6</daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d7.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">7</daysOfWeekIntType>);
				}

			}
			
			return myraJobList;
			
		}
		
		private static function getBaseJobInfos(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:baseJobInfos xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var baseJobInfosXML:Object = myraJobList.myra::genericJob.myra_jobprops::baseJobInfos;
			
			baseJobInfosXML.appendChild(<wla:jsName xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobLogFile xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobLogPath xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:oSystem xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobPriority xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<myra-jobprops:jsIsActive xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			baseJobInfosXML.appendChild(<lik:userId xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			
			baseJobInfosXML.appendChild(<lik:jobTypeDetails xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobCommandType xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobCommand xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobPath xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:argValues xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:envVariables xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);

			baseJobInfosXML.wla::jsName = j.baseInfoForm.jsName.text;
			baseJobInfosXML.wla::jobLogFile = j.baseInfoForm.jsJobLogFile.text;
			baseJobInfosXML.wla::jobLogPath = j.baseInfoForm.jsJobLogPath.text;
			baseJobInfosXML.wla::oSystem = j.baseInfoForm.jsOsType.selectedItem;
			baseJobInfosXML.wla::jobPriority = j.baseInfoForm.jsJobPriority.selectedItem;
			baseJobInfosXML.myra_jobprops::jsIsActive = j.baseInfoForm.jsJobActivity.selectedItem;
			baseJobInfosXML.lik::userId = "serkan";
			
			
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommandType = j.baseInfoForm.jsJobType.selectedItem;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommand = j.baseInfoForm.jsCommand.text;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobPath = j.baseInfoForm.jsJobPath.text; 
			baseJobInfosXML.lik::jobTypeDetails.lik::argValues = j.baseInfoForm.jsJobArgs.text; 
			
			
			for each (var item:String in j.baseInfoForm.envVarList.dataProvider.toArray()) {
				// var xmlText:String = "<entry key=\"" + item + "\">" + item + "</entry>";
				var xmlText:String = "<lik:entry xmlns:lik=\"http://www.likyateknoloji.com/likya-gen\" key=\"" + item + "\">" + item + "</lik:entry>";
				baseJobInfosXML.lik::jobTypeDetails.lik::envVariables.appendChild(XML(xmlText));
			}
			
			return myraJobList;
		}
		
	}
}

