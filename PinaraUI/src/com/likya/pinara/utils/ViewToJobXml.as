package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.JobEditWindow;
	
	import mx.formatters.DateFormatter;
	
	public class ViewToJobXml {
		
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
		
		namespace myra_stateinfo ="http://www.likyateknoloji.com/myra-stateinfo";
		use namespace myra_stateinfo;
		
		public static function getXML(j:JobEditWindow):XML {
			
			var myraJobList:XML = 
				<myra:jobList xmlns:myra="http://www.likyateknoloji.com/myra-joblist" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" xmlns:wla="http://www.likyateknoloji.com/wla-gen"
						xmlns:lik="http://www.likyateknoloji.com/likya-gen" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
					<myra:genericJob xsi:type="myra:simpleProperties" />
				</myra:jobList>;

			if(j.jobDetailXml == null) {
				myraJobList.myra::genericJob.@handlerURI = "com.likya.myra.jef.jobs.ExecuteInShell";
				myraJobList.myra::genericJob.@Id = "-1"; // will be replaced on the service side with the valid value
				myraJobList.myra::genericJob.@groupId = "my_group"; // Ekrandan girilmesi gerekiyor !!!!
				trace("UYARI !!!! : myraJobList.myra::genericJob.@groupId ekrandan girilmeli");
				myraJobList.myra::genericJob.@agentId = "1";
				trace("UYARI !!!! : myraJobList.myra::genericJob.@agentId ekrandan girilmeli");
			} else { 
				myraJobList.myra::genericJob.@handlerURI = j.jobDetailXml.@handlerURI; //"com.likya.myra.jef.jobs.ExecuteInShell";
				myraJobList.myra::genericJob.@Id = j.jobDetailXml.@Id; //"22";
				// myraJobList.myra::genericJob.@groupId = j.jobDetailXml.@groupId; //"my_group";
				myraJobList.myra::genericJob.@agentId = j.jobDetailXml.@agentId; //"1";
			}
			
			// Alert.show("myraJobList.myra::genericJob.@handlerURI : " + myraJobList.myra::genericJob.@handlerURI);
			
			myraJobList = getBaseJobInfos(j, myraJobList);

			myraJobList.myra::genericJob.appendChild(<myra-jobprops:graphInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);

			// Serkan Taş 25 Ocak Pazar 20:13
			// Sunucu tarafında bağımlı iş tanımı ile ilgili kısım yapılınca açılacak
			myraJobList = getDependencyInfo(j, myraJobList);
			
			myraJobList = getStateInfos(j, myraJobList);
			
			myraJobList = getManagementInfo(j, myraJobList);
			
			myraJobList = getLogAnalysis(j, myraJobList);

			myraJobList = getScheduleInfo(j, myraJobList);

			return myraJobList;
			
		}
		
		private static function getDependencyInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			/*
			<myra-jobprops:DependencyList>
				<myra-jobprops:sensInfo>
					<myra-jobprops:sensTime delay="PT30S" relativeStart="true" />
				</myra-jobprops:sensInfo>
				<wla:Item dependencyID="mydep">
					<wla:jsName>depJsName</wla:jsName>
					<wla:jsId>1</wla:jsId>
					<wla:jsType>JOB</wla:jsType>
					<lik:comment>no comment</lik:comment>
					<myra-stateinfo:jsDependencyRule>
						<myra-stateinfo:StateName>FINISHED</myra-stateinfo:StateName>
					</myra-stateinfo:jsDependencyRule>
				</wla:Item>
				<myra-jobprops:DependencyExpression>mydep</myra-jobprops:DependencyExpression>
			</myra-jobprops:DependencyList>			
			*/
			
			if(j.dependencyListForm.dependencyListGrid.dataProvider.length == 0) {
				return myraJobList;
			}
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:DependencyList xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var dependencyListXML:Object = myraJobList.myra::genericJob.myra_jobprops::DependencyList;

			if(j.dependencyListForm.sensInfo.selectedValue == "time") {
				dependencyListXML.appendChild(<myra-jobprops:sensInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"><myra-jobprops:sensTime/></myra-jobprops:sensInfo>);
				if(j.dependencyListForm.step.text == "") {
					dependencyListXML.myra_jobprops::sensInfo.myra_jobprops::sensTime.@delay = "PT10S"
				} else {
					dependencyListXML.myra_jobprops::sensInfo.myra_jobprops::sensTime.@delay = j.dependencyListForm.step.text;
				}
				dependencyListXML.myra_jobprops::sensInfo.myra_jobprops::sensTime.@relativeStart = j.dependencyListForm.relative.selectedItem;
			} 
			
			var counter:int = 0;
			for each (var item:Object in j.dependencyListForm.dependencyListGrid.dataProvider.toArray()) {
				dependencyListXML.appendChild(<wla:Item xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				dependencyListXML.wla::Item[counter].@dependencyID = item.depid;
				
				var pairs:Array = item.jobinfo.split(':');
				
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsName xmlns:wla="http://www.likyateknoloji.com/wla-gen">{pairs[1]}</wla:jsName>);
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsId xmlns:wla="http://www.likyateknoloji.com/wla-gen">{pairs[0]}</wla:jsId>);
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsType xmlns:wla="http://www.likyateknoloji.com/wla-gen">JOB</wla:jsType>);
				dependencyListXML.wla::Item[counter].appendChild(<lik:comment xmlns:lik="http://www.likyateknoloji.com/likya-gen">{item.comment}</lik:comment>);
				
				dependencyListXML.wla::Item[counter].appendChild(<myra-stateinfo:jsDependencyRule xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
				
				var sssTmp:SSS = new SSS(item.stateinfo);
				
				dependencyListXML.wla::Item[counter].myra_stateinfo::jsDependencyRule.appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.state}</myra-stateinfo:StateName>);
				
				if(sssTmp.substate != null) {
					dependencyListXML.wla::Item[counter].myra_stateinfo::jsDependencyRule.appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.substate}</myra-stateinfo:SubstateName>);
				}
				
				if(sssTmp.status != null) {
					dependencyListXML.wla::Item[counter].myra_stateinfo::jsDependencyRule.appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{sssTmp.status}</myra-stateinfo:StatusName>);
				}
				
				counter ++;
			}
			
			if(counter > 0) {
				var depExp:String = j.dependencyListForm.depExp.text;
				if(depExp == null || depExp == "") {
					depExp = "";
					for (var i:int = 0; i < j.dependencyListForm.dependencyListGrid.dataProvider.toArray().length; i++) {
						item = j.dependencyListForm.dependencyListGrid.dataProvider.toArray()[i];
						if(i == 0) {
							depExp = item.depid;
							continue;
						}
						depExp = depExp + " AND " + item.depid;
					}
				}
				dependencyListXML.appendChild(<myra-jobprops:DependencyExpression xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{depExp}</myra-jobprops:DependencyExpression>);
			}
			
			return myraJobList;
		}
		
		private static function getStateInfos(j:JobEditWindow, myraJobList:XML):XML {
			
/*			<myra-stateinfo:JobStatusList>
				<myra-stateinfo:JobStatus>
					<myra-stateinfo:StatusName>SUCCESS</myra-stateinfo:StatusName>
					<myra-stateinfo:Desc />
					<myra-stateinfo:ReturnCodeList osType="Windows">
						<myra-stateinfo:ReturnCode>
							<myra-stateinfo:Code>5</myra-stateinfo:Code>
							<myra-stateinfo:Desc>Dönüş kodu 5 ise başarılı kabul et</myra-stateinfo:Desc>
						</myra-stateinfo:ReturnCode>
					</myra-stateinfo:ReturnCodeList>
				</myra-stateinfo:JobStatus>
			</myra-stateinfo:JobStatusList>*/
			
/*			myraJobList.myra::genericJob.appendChild(
				<myra-stateinfo:stateInfos xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
						<myra-stateinfo:JobStatusList>
							<myra-stateinfo:JobStatus>
								<myra-stateinfo:StatusName>SUCCESS</myra-stateinfo:StatusName>
								<myra-stateinfo:Desc />
								<myra-stateinfo:ReturnCodeList osType="MACOS">
									<myra-stateinfo:ReturnCode>
										<myra-stateinfo:Code>0</myra-stateinfo:Code>
										<myra-stateinfo:Desc>Açıklama</myra-stateinfo:Desc>
									</myra-stateinfo:ReturnCode>
								</myra-stateinfo:ReturnCodeList>
							</myra-stateinfo:JobStatus>
						</myra-stateinfo:JobStatusList>
						<myra-stateinfo:LiveStateInfos>
							<myra-stateinfo:LiveStateInfo LSIDateTime="2014-01-30T23:41:58.595+02:00">
								<myra-stateinfo:StateName>PENDING</myra-stateinfo:StateName>
								<myra-stateinfo:SubstateName>IDLED</myra-stateinfo:SubstateName>
								<myra-stateinfo:StatusName>BYUSER</myra-stateinfo:StatusName>
							</myra-stateinfo:LiveStateInfo>
						</myra-stateinfo:LiveStateInfos>
				</myra-stateinfo:stateInfos>
			);*/
			
			myraJobList.myra::genericJob.appendChild(<myra-stateinfo:stateInfos xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
			myraJobList.myra::genericJob.myra_stateinfo::stateInfos.appendChild(<myra-stateinfo:JobStatusList xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo"/>);
			
			var jobStatusListXML:Object = myraJobList.myra::genericJob.myra_stateinfo::stateInfos.myra_stateinfo::JobStatusList;
			
			var counter:int = 0;
			for each (var item:Object in j.stateInfosForm.statusInfoGrid.dataProvider.toArray()) {
					jobStatusListXML.appendChild(<myra-stateinfo:JobStatus xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
					jobStatusListXML.myra_stateinfo::JobStatus[counter].appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{"" + item.statusname}</myra-stateinfo:StatusName>);
					jobStatusListXML.myra_stateinfo::JobStatus[counter].appendChild(<myra-stateinfo:Desc xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{"" + item.desc}</myra-stateinfo:Desc>);
					
					jobStatusListXML.myra_stateinfo::JobStatus[counter].appendChild(<myra-stateinfo:ReturnCodeList osType="MACOS" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
					
					var innercounter:int = 0;
					for each (var innerItem:Object in item.retCodeList..toArray()) {
						jobStatusListXML.myra_stateinfo::JobStatus[counter].myra_stateinfo::ReturnCodeList.appendChild(<myra-stateinfo:ReturnCode xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
						
						var codeDescCuple:CodeDesc = new CodeDesc(String(innerItem));
						jobStatusListXML.myra_stateinfo::JobStatus[counter].myra_stateinfo::ReturnCodeList.myra_stateinfo::ReturnCode[innercounter].appendChild(<myra-stateinfo:Code xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{codeDescCuple.code}</myra-stateinfo:Code>);
						jobStatusListXML.myra_stateinfo::JobStatus[counter].myra_stateinfo::ReturnCodeList.myra_stateinfo::ReturnCode[innercounter].appendChild(<myra-stateinfo:Desc xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{codeDescCuple.desc}</myra-stateinfo:Desc>);
						innercounter ++;
					}
					
					counter ++;
			}
			
			myraJobList.myra::genericJob.myra_stateinfo::stateInfos.appendChild(<myra-stateinfo:LiveStateInfos xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
							<myra-stateinfo:LiveStateInfo LSIDateTime="">
								<myra-stateinfo:StateName>PENDING</myra-stateinfo:StateName>
								<myra-stateinfo:SubstateName>DEACTIVATED</myra-stateinfo:SubstateName>
							</myra-stateinfo:LiveStateInfo>
						</myra-stateinfo:LiveStateInfos>);

			var dateTimeString:String = getformatteddatetime();		

			myraJobList.myra::genericJob.myra_stateinfo::stateInfos.myra_stateinfo::LiveStateInfos.myra_stateinfo::LiveStateInfo.@LSIDateTime = dateTimeString;
			
			// trace(myraJobList.myra::genericJob);
			
			return myraJobList;
		}
		
		private static function getManagementInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:management xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var managementInfoXML:Object = myraJobList.myra::genericJob.myra_jobprops::management;
			
			if(j.managementInfoForm_0.jsJobTriggerType.selectedItem.value == "USER") {
				/** User Managed ****/
				managementInfoXML.appendChild(<wla:trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">USER</wla:trigger>);
			} else {
				/** Time Managed ****/
				managementInfoXML.appendChild(<wla:trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">TIME</wla:trigger>);
			}
				
			/** Period Info ****/
			
			if(j.managementInfoForm_1.periodInfo.selected) {
				managementInfoXML.appendChild(<myra-jobprops:periodInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				
				managementInfoXML.periodInfo.@relativeStart = j.managementInfoForm_1.relativeStart.selectedItem;
				managementInfoXML.periodInfo.@step = j.managementInfoForm_1.stepValue.text;
				if(j.managementInfoForm_1.maxCountValue.text != null && j.managementInfoForm_1.maxCountValue.text.length > 0 && j.managementInfoForm_1.maxCountValue.text != "0") {
					managementInfoXML.periodInfo.@maxCount = j.managementInfoForm_1.maxCountValue.text;
				}
			}
			
			/** Time Management ****/
			managementInfoXML = get_TimeManagement(managementInfoXML, j);
			
			/** Cascading Conditions ****/
			
			managementInfoXML.appendChild(<wla:cascadingConditions xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			
			managementInfoXML.cascadingConditions.appendChild(<wla:runEvenIfFailed xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
			managementInfoXML.cascadingConditions.appendChild(<wla:jobSafeToRestart xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
			
			managementInfoXML.cascadingConditions.runEvenIfFailed = j.managementInfoForm_1.runEvenFailed.selectedItem;
			managementInfoXML.cascadingConditions.jobSafeToRestart = j.managementInfoForm_1.safeToRestart.selectedItem;
			
			if(j.managementInfoForm_1.autoRetry.selectedItem == "true") {
				managementInfoXML.cascadingConditions.appendChild(<wla:jobAutoRetryInfo xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.cascadingConditions.wla::jobAutoRetryInfo.appendChild(<wla:jobAutoRetry xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.cascadingConditions.jobAutoRetryInfo.@step = j.managementInfoForm_1.arStepValue.text;
				if(j.managementInfoForm_1.maxCountValueAr.text != "") {
					managementInfoXML.cascadingConditions.jobAutoRetryInfo.@maxCount = j.managementInfoForm_1.maxCountValueAr.text;
				}
				
				managementInfoXML.cascadingConditions.jobAutoRetryInfo.jobAutoRetry = j.managementInfoForm_1.autoRetry.selectedItem;
			}
			
			return myraJobList;
		}
		
		private static function get_TimeManagement(managementInfoXML:Object, j:JobEditWindow):Object {
			
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "YYYY-MM-DD";
			
			managementInfoXML.appendChild(<wla:timeManagement xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			
			// if(j.managementInfoForm_0.jsJobTriggerType.selectedItem.value == "TIME") {
				
				var bInfo : Boolean = j.managementInfoForm_0.timeFrameStart.selected;
				var eInfo : Boolean = j.managementInfoForm_0.timeFrameStop.selected;
				
				if(bInfo || eInfo) {
				
					managementInfoXML.wla::timeManagement.appendChild(<wla:jsExecutionTimeFrame xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
					
					if(bInfo) {
						managementInfoXML.wla::timeManagement.wla::jsExecutionTimeFrame.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
						var sDate:Date = j.managementInfoForm_0.tFbdate.selectedDate;
						var dateString:String = currentDF.format(sDate);
						dateString = getW3Dt(sDate, j.managementInfoForm_0.tFbhour.value, j.managementInfoForm_0.tFbminute.value, j.managementInfoForm_0.tFbsecond.value);
						managementInfoXML.timeManagement.jsExecutionTimeFrame.startTime = dateString;
					}
					
					if(eInfo) {
						managementInfoXML.wla::timeManagement.wla::jsExecutionTimeFrame.appendChild(<wla:stopTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
						sDate = j.managementInfoForm_0.tFedate.selectedDate;
						dateString = currentDF.format(sDate);
						dateString = getW3Dt(sDate, j.managementInfoForm_0.tFehour.value, j.managementInfoForm_0.tFeminute.value, j.managementInfoForm_0.tFesecond.value);
						managementInfoXML.timeManagement.jsExecutionTimeFrame.stopTime = dateString;
						
						
					}
					
				}
				
				// if(j.managementInfoForm_0.selectStartCond.selected) {
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:jsScheduledTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsScheduledTime.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				sDate = j.managementInfoForm_0.bdate.selectedDate;
				dateString = currentDF.format(sDate);
				
				dateString = getW3Dt(sDate, j.managementInfoForm_0.bhour.value, j.managementInfoForm_0.bminute.value, j.managementInfoForm_0.bsecond.value);
				
				managementInfoXML.timeManagement.jsScheduledTime.startTime = dateString;
				
				managementInfoXML.wla::timeManagement.appendChild(<wla:jsActualTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.wla::timeManagement.wla::jsActualTime.appendChild(<wla:startTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.timeManagement.jsActualTime.startTime = dateString;
				//}
				
			// }
			
			managementInfoXML.wla::timeManagement.appendChild(<wla:jsTimeOut xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
			managementInfoXML.wla::timeManagement.wla::jsTimeOut.appendChild(<lik:value_integer xmlns:lik="http://www.likyateknoloji.com/likya-gen" />);
			managementInfoXML.wla::timeManagement.wla::jsTimeOut.appendChild(<lik:unit xmlns:lik="http://www.likyateknoloji.com/likya-gen"  />);
			
			managementInfoXML.wla::timeManagement.appendChild(<wla:expectedTime xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
			managementInfoXML.wla::timeManagement.wla::expectedTime.appendChild(<lik:value_integer xmlns:lik="http://www.likyateknoloji.com/likya-gen" />);
			managementInfoXML.wla::timeManagement.wla::expectedTime.appendChild(<lik:unit xmlns:lik="http://www.likyateknoloji.com/likya-gen"  />);
			
			managementInfoXML.timeManagement.jsTimeOut.lik::value_integer = j.managementInfoForm_0.timoutValue.text;
			managementInfoXML.timeManagement.jsTimeOut.lik::unit = j.managementInfoForm_0.timeoutUnit.selectedItem;
			
			managementInfoXML.timeManagement.expectedTime.lik::value_integer = j.managementInfoForm_0.expectedValue.text;
			managementInfoXML.timeManagement.expectedTime.lik::unit = j.managementInfoForm_0.expectedTimeUnit.selectedItem;
			
			return managementInfoXML;
		}
		
		private static function getLogAnalysis(j:JobEditWindow, myraJobList:XML):XML {
			/*<wla:logAnalysis active="true" id="100">
				<wla:findWhat direction="Down" matchCase="false" matchWholeWordOnly="false" mode="regEx">string</wla:findWhat>
				<wla:action>
				  <wla:then>
					<wla:event code="email" id="100">
					  <lik:EmailList>
						<lik:email>q@q.com</lik:email>
					  </lik:EmailList>
					  <wla:content logLineNumBack="201" logLineNumForward="201">string</wla:content>
					</wla:event>
					<wla:forcedResult active="true">
					  <myra-stateinfo:LiveStateInfo LSIDateTime="string" userId="1">
						<myra-stateinfo:StateName>PENDING</myra-stateinfo:StateName>
						<myra-stateinfo:SubstateName>PAUSED</myra-stateinfo:SubstateName>
						<myra-stateinfo:StatusName>BYUSER</myra-stateinfo:StatusName>
						<myra-stateinfo:ReturnCode cdId="string">
						  <myra-stateinfo:Code>3</myra-stateinfo:Code>
						  <myra-stateinfo:Desc>string</myra-stateinfo:Desc>
						</myra-stateinfo:ReturnCode>
					  </myra-stateinfo:LiveStateInfo>
					</wla:forcedResult>
				  </wla:then>
				  <wla:else>
					<wla:event code="email" id="100">
					  <lik:EmailList>
						<lik:email>a@a.com</lik:email>
					  </lik:EmailList>
					  <wla:content logLineNumBack="201" logLineNumForward="201">string</wla:content>
					</wla:event>
					<wla:forcedResult active="true">
					  <myra-stateinfo:LiveStateInfo LSIDateTime="string" userId="1">
						<myra-stateinfo:StateName>RUNNING</myra-stateinfo:StateName>
						<myra-stateinfo:SubstateName>ON-RESOURCE</myra-stateinfo:SubstateName>
						<myra-stateinfo:StatusName>BYUSER</myra-stateinfo:StatusName>
						<myra-stateinfo:ReturnCode cdId="string">
						  <myra-stateinfo:Code>3</myra-stateinfo:Code>
						  <myra-stateinfo:Desc>string</myra-stateinfo:Desc>
						</myra-stateinfo:ReturnCode>
					  </myra-stateinfo:LiveStateInfo>
					</wla:forcedResult>
				  </wla:else>ls
				</wla:action>
			  </wla:logAnalysis>*/
			
			if(j.logAnalysisForm.currentState == "undefined") {
				return myraJobList;
			}
			
			var logAnalysisXML:XML = <wla:logAnalysis xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>;
			
			logAnalysisXML.@id = 100;
			if(j.logAnalysisForm.enableLA.selected) {
				logAnalysisXML.@active = true;
			} else {
				logAnalysisXML.@active = false;
			}
			
			logAnalysisXML.appendChild(<wla:findWhat xmlns:wla="http://www.likyateknoloji.com/wla-gen">{j.logAnalysisForm.searchPattern.text}</wla:findWhat>);
			
			logAnalysisXML.wla::findWhat.@direction = j.logAnalysisForm.searchDirection.selectedItem;
			logAnalysisXML.wla::findWhat.@matchCase = j.logAnalysisForm.matchCase.selected;
			logAnalysisXML.wla::findWhat.@matchWholeWordOnly = j.logAnalysisForm.matchWholeWordOnly.selected;
			
			if((j.logAnalysisForm.patternType.selectedItem == "String")) {
				logAnalysisXML.wla::findWhat.@mode = "normal";
			} else {
				logAnalysisXML.wla::findWhat.@mode = "regEx";
			}
			
				
			logAnalysisXML.appendChild(<wla:action xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			
			
			// Thencase Part
			
			logAnalysisXML.wla::action.appendChild(<wla:thencase xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			var thencaseXML:Object = logAnalysisXML.wla::action.wla::thencase;
			
			thencaseXML.appendChild(<wla:event code="email" id="100" xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			thencaseXML.wla::event.appendChild(<wla:content xmlns:wla="http://www.likyateknoloji.com/wla-gen">{j.logAnalysisForm.thenActionContent.text}</wla:content>);
			if(j.logAnalysisForm.thenLogLineNumBack.text != "") {
				thencaseXML.wla::event.wla::content.@logLineNumBack = j.logAnalysisForm.thenLogLineNumBack.text;	
			}
			if(j.logAnalysisForm.thenLogLineNumForward.text != "") {
				thencaseXML.wla::event.wla::content.@logLineNumForward = j.logAnalysisForm.thenLogLineNumForward.text;
			}	
			
			thencaseXML.appendChild(<wla:forcedResult active="true" xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			
			//uuuu
			
			var liveStateInfo:XML = <myra-stateinfo:LiveStateInfo LSIDateTime="" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />;
			
			// thencaseXML.wla::forcedResult.appendChild(<myra-stateinfo:LiveStateInfo LSIDateTime="" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
			
			if(j.logAnalysisForm.thenSSS.stateName.selectedIndex > -1) {
				//thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.stateName.selectedItem}</myra-stateinfo:StateName>);
				liveStateInfo.appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.stateName.selectedItem}</myra-stateinfo:StateName>);
			}
			
			if(j.logAnalysisForm.thenSSS.substateName.selectedIndex > -1) {
				// thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.substateName.selectedItem.value}</myra-stateinfo:SubstateName>);
				liveStateInfo.appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.substateName.selectedItem.value}</myra-stateinfo:SubstateName>);
			} 
			
			if(j.logAnalysisForm.thenSSS.statusName.selectedIndex > -1) {
				// thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.statusName.selectedItem.value}</myra-stateinfo:StatusName>);
				liveStateInfo.appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.statusName.selectedItem.value}</myra-stateinfo:StatusName>);
			}
			
			
			if(j.logAnalysisForm.thenRetCode.text != "" || j.logAnalysisForm.thenRetCodeDesc.text != "") {
				/*thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(
					<myra-stateinfo:ReturnCode cdId="string" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
												<myra-stateinfo:Code>{j.logAnalysisForm.thenRetCode.text}</myra-stateinfo:Code>
												<myra-stateinfo:Desc>{j.logAnalysisForm.thenRetCodeDesc.text}</myra-stateinfo:Desc>
											</myra-stateinfo:ReturnCode>);*/
				liveStateInfo.appendChild(
					<myra-stateinfo:ReturnCode cdId="string" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
												<myra-stateinfo:Code>{j.logAnalysisForm.thenRetCode.text}</myra-stateinfo:Code>
												<myra-stateinfo:Desc>{j.logAnalysisForm.thenRetCodeDesc.text}</myra-stateinfo:Desc>
											</myra-stateinfo:ReturnCode>);
			}
			
			var dateTimeString:String = getformatteddatetime();		
			
			// logAnalysisXML.wla::action.wla::thencase.wla::forcedResult.myra_stateinfo::LiveStateInfo.@LSIDateTime = dateTimeString;
			liveStateInfo.@LSIDateTime = dateTimeString;

			
			// Elsecase definitons...
			if(j.logAnalysisForm.elseActionCheckBox.selected) {

				// logAnalysisXML.wla::action.appendChild(<wla:elsecase xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				// var elsecaseXML:Object = logAnalysisXML.wla::action.wla::elsecase;

				var elsecaseXML:XML = <wla:elsecase xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>;
				

				elsecaseXML.appendChild(<wla:event code="email" id="100" xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				elsecaseXML.wla::event.appendChild(<wla:content xmlns:wla="http://www.likyateknoloji.com/wla-gen">{j.logAnalysisForm.elseActionContent.text}</wla:content>);
				if(j.logAnalysisForm.elseLogLineNumBack.text != "") {
					elsecaseXML.wla::event.wla::content.@logLineNumBack = j.logAnalysisForm.elseLogLineNumBack.text;
				}
				if(j.logAnalysisForm.elseLogLineNumForward.text != "") {
					elsecaseXML.wla::event.wla::content.@logLineNumForward = j.logAnalysisForm.elseLogLineNumForward.text;
				}
				
				elsecaseXML.appendChild(<wla:forcedResult active="true" xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);

				elsecaseXML.wla::forcedResult.appendChild(<myra-stateinfo:LiveStateInfo LSIDateTime="" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
				
				if(j.logAnalysisForm.elseSSS.stateName.selectedIndex > -1) {
					elsecaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.elseSSS.stateName.selectedItem}</myra-stateinfo:StateName>);
				}
				
				if(j.logAnalysisForm.elseSSS.substateName.selectedIndex > -1) {
					elsecaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.elseSSS.substateName.selectedItem.value}</myra-stateinfo:SubstateName>);
				} 

				if(j.logAnalysisForm.elseSSS.statusName.selectedIndex > -1) {
					elsecaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.elseSSS.statusName.selectedItem.value}</myra-stateinfo:StatusName>);
				}
				
				if(j.logAnalysisForm.elseRetCode.text != null || j.logAnalysisForm.elseRetCodeDesc.text != null) {
					elsecaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(
						<myra-stateinfo:ReturnCode cdId="string" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
													<myra-stateinfo:Code>{j.logAnalysisForm.elseRetCode.text}</myra-stateinfo:Code>
													<myra-stateinfo:Desc>{j.logAnalysisForm.elseRetCodeDesc.text}</myra-stateinfo:Desc>
												</myra-stateinfo:ReturnCode>);
				}
				
				elsecaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.@LSIDateTime = dateTimeString;
				
				/*logAnalysisXML.wla::action.appendChild(
					<wla:elsecase xmlns:wla="http://www.likyateknoloji.com/wla-gen">
						<wla:event code="email" id="100" xmlns:wla="http://www.likyateknoloji.com/wla-gen">
							<wla:content logLineNumBack={j.logAnalysisForm.elseLogLineNumBack.text} logLineNumForward={j.logAnalysisForm.elseLogLineNumForward.text} xmlns:wla="http://www.likyateknoloji.com/wla-gen">
								{j.logAnalysisForm.elseActionContent.text}
							</wla:content>
						</wla:event>
						<myra-stateinfo:LiveStateInfo LSIDateTime={dateTimeString} xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
							<myra-stateinfo:StateName>{j.logAnalysisForm.elseSSS.stateName.selectedItem}</myra-stateinfo:StateName>
							<myra-stateinfo:SubstateName>{substateName}</myra-stateinfo:SubstateName>
							<myra-stateinfo:StatusName>{statusName}</myra-stateinfo:StatusName>
							<myra-stateinfo:ReturnCode cdId="string">
								<myra-stateinfo:Code>{j.logAnalysisForm.elseRetCode.text}</myra-stateinfo:Code>
								<myra-stateinfo:Desc>{j.logAnalysisForm.elseRetCodeDesc.text}</myra-stateinfo:Desc>
							</myra-stateinfo:ReturnCode>
						</myra-stateinfo:LiveStateInfo>
					</wla:elsecase>);*/
				
				logAnalysisXML.wla::action.appendChild(elsecaseXML);
			}
			
			if(liveStateInfo.hasOwnProperty("StateName")) {
				thencaseXML.wla::forcedResult.appendChild(liveStateInfo);
				myraJobList.myra::genericJob.appendChild(logAnalysisXML);
			}
			
			trace(logAnalysisXML);
			
			return myraJobList;	
		}
		
		private static function getScheduleInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:scheduleInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var scheduleInfoXML:Object = myraJobList.myra::genericJob.myra_jobprops::scheduleInfo;
			
			if(j.scheduleInfoForm.scheduleType.selection.id == "everyDaySchedule") {

				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">1</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">2</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">3</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">4</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">5</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">6</myra-jobprops:daysOfWeekIntType>);
				scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">7</myra-jobprops:daysOfWeekIntType>);

			} else {
				
				var daysOfMonthXML:XML = <myra-jobprops:daysOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />;
				
				if(j.scheduleInfoForm.fdom.selected) {
					daysOfMonthXML.appendChild(<myra-jobprops:firstDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}

				if(j.scheduleInfoForm.ldom.selected) {
					daysOfMonthXML.appendChild(<myra-jobprops:lastDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}
				
				if(j.scheduleInfoForm.specDays.text.length > 0) {
					
					var specDays:String = j.scheduleInfoForm.specDays.text;
					
					daysOfMonthXML.appendChild(<myra-jobprops:daysTextRep xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"> {specDays} </myra-jobprops:daysTextRep>);
					
					if(specDays.indexOf(',') > 0) {
						var results:Array = specDays.split(',');
						for each (var item:String in results) {
							
							if(item.indexOf('-') > 0) {
								var dashList:Array = item.split('-');
								if(dashList.length == 1) {
									daysOfMonthXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{dashList[0]}</myra-jobprops:days>);
								} else if(dashList.length == 2) {
									
									var minNum:Number = Number(dashList[0]);
									var maxNum:Number = Number(dashList[1]);
									
									if(!isNaN(minNum) && !isNaN(maxNum) && minNum > 0 && maxNum < 31 && minNum < maxNum) {
										var counter:int = 0;
										for (counter = minNum; counter <= maxNum; counter++)
										{
											//trace(counter);
											daysOfMonthXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{counter}</myra-jobprops:days>);
										}
									}
				
								}
							} else {
								daysOfMonthXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{item}</myra-jobprops:days>);
							}
						}
					} else {
						daysOfMonthXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{specDays}</myra-jobprops:days>);
					}
					
				}
				
				if(daysOfMonthXML.children().length() != 0) {
					scheduleInfoXML.appendChild(daysOfMonthXML);
				}
				
				if(j.scheduleInfoForm.d1.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">1</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d2.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">2</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d3.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">3</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d4.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">4</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d5.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">5</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d6.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">6</myra-jobprops:daysOfWeekIntType>);
				}
				if(j.scheduleInfoForm.d7.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:daysOfWeekIntType xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">7</myra-jobprops:daysOfWeekIntType>);
				}

			}
			
			return myraJobList;
			
		}
		
		private static function getBaseJobInfos(j:JobEditWindow, myraJobList:XML):XML {
			
			myraJobList.myra::genericJob.@groupId = j.baseInfoForm_0.jsJobGroup.text;
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:baseJobInfos xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var baseJobInfosXML:Object = myraJobList.myra::genericJob.myra_jobprops::baseJobInfos;
			
			baseJobInfosXML.appendChild(<wla:jsName xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<lik:jobTypeDetails xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobLogFile xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobLogPath xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:oSystem xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<wla:jobPriority xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			baseJobInfosXML.appendChild(<myra-jobprops:jsIsActive xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			baseJobInfosXML.appendChild(<lik:userId xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobCommandType xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobCommand xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:jobWorkDir xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
			baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:argValues xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);

			baseJobInfosXML.wla::jsName = j.baseInfoForm_0.jsName.text;
			baseJobInfosXML.wla::jobLogFile = j.baseInfoForm_1.jsJobLogFile.text;
			baseJobInfosXML.wla::jobLogPath = j.baseInfoForm_1.jsJobLogPath.text;
			//baseJobInfosXML.wla::oSystem = j.baseInfoForm.jsOsType.selectedItem;
			baseJobInfosXML.wla::jobPriority = j.baseInfoForm_1.jsJobPriority.selectedItem;
			baseJobInfosXML.myra_jobprops::jsIsActive = j.baseInfoForm_1.jsJobActivity.selectedItem;
			baseJobInfosXML.lik::userId = "007";
			
			
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommandType = j.baseInfoForm_0.jsJobType.selectedItem;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommand = j.baseInfoForm_0.jsCommand.text;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobWorkDir = j.baseInfoForm_0.jsJobWorkDir.text; 
			baseJobInfosXML.lik::jobTypeDetails.lik::argValues = j.baseInfoForm_0.jsJobArgs.text; 
			
			
			if(j.baseInfoForm_1.envVarList.dataProvider.length > 0) {
				baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:envVariables xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
				for each (var item:String in j.baseInfoForm_1.envVarList.dataProvider.toArray()) {
					// var xmlText:String = "<entry key=\"" + item + "\">" + item + "</entry>";
					
					if(item.indexOf('=') > 0) {
						var pairs:Array = item.split('=');
						if(pairs.length == 2) {
							var xmlText:String = "<lik:entry xmlns:lik=\"http://www.likyateknoloji.com/likya-gen\" key=\"" + pairs[0] + "\">" + pairs[1] + "</lik:entry>";
							baseJobInfosXML.lik::jobTypeDetails.lik::envVariables.appendChild(XML(xmlText));
						}
					}
					
				}
			}
			
			
			return myraJobList;
		}
		
		public static function zeroize(text:String):String {
			if(text.length < 2) { 
				text = '0' + text;
			}
			return text;
		}		
		
		private static function getformatteddatetime():String {
			
			var currentDateTime:Date = new Date();
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "YYYY-MM-DD";
			var dateString:String = currentDF.format(currentDateTime);
			currentDF.formatString = "JJ:NN:SS"; //"LL:NN:SS";
			var timeString:String = currentDF.format(currentDateTime);
			
			var dateTimeString:String = dateString + "T" + timeString + ".000+03:00";	
			
			return dateTimeString;
		}
		
		public static function isValidSpecDays(str:String):Boolean {
			var pattern:RegExp = /^(?!([ \d]*-){2})\d+(?: *[-,] *\d+)*$/;
			
			var result:Object = str.match(pattern);
			if(result == null || !isInRange(str)) {
				return false;
			}
			return true;
		}
		
		public static function isInRange(specDays:String):Boolean {
			
			if(specDays.indexOf(',') > 0) {
				var results:Array = specDays.split(',');
				for each (var item:String in results) {
					if(item.indexOf('-') > 0) {
						var dashList:Array = item.split('-');
						if(dashList.length == 1) {
							var dayNum:Number = Number(dashList[0]);
							if(isNaN(dayNum) || dayNum == 0 || dayNum > 30) {
								return false;
							}
						} else if(dashList.length == 2) {
							
							var minNum:Number = Number(dashList[0]);
							var maxNum:Number = Number(dashList[1]);
							
							if(isNaN(minNum) || isNaN(maxNum) || minNum < 0 || maxNum > 30 || minNum > maxNum) {
								return false;
							}
							
						}
					} else {
						var dayNum2:Number = Number(item);
						if(isNaN(dayNum2) || dayNum2 <= 0 || dayNum2 > 30) {
							return false;
						}
					}
				}
			}
			
			return true;
			
		}
		
		private static function getW3Dt(sDate:Date, hour:Number, minute:Number, second:Number):String {
			
			var currentDF:DateFormatter = new DateFormatter(); 
			currentDF.formatString = "YYYY-MM-DD"
			
			var dateString:String = currentDF.format(sDate);
			
			dateString = dateString + "T" + zeroize(hour + "") + ":" + zeroize(minute + "") + ":" + zeroize(second + "") + ".000+03:00";
			
			return dateString;
		}
		
	}
	
}

