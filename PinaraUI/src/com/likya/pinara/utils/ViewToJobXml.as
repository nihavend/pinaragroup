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

			myraJobList.myra::genericJob.@handlerURI = "com.likya.myra.jef.jobs.ExecuteInShell";
			myraJobList.myra::genericJob.@Id = "22";
			myraJobList.myra::genericJob.@groupId = "my_group";
			myraJobList.myra::genericJob.@agentId = "1";
			
			// Alert.show("myraJobList.myra::genericJob.@handlerURI : " + myraJobList.myra::genericJob.@handlerURI);
			
			// TO-DO Aşağıdaki 2 fonksiyon yeni ns yapısına uygun hale getirilecek ardından dependency yapılacak
			
			myraJobList = getBaseJobInfos(j, myraJobList);

			myraJobList.myra::genericJob.appendChild(<myra-jobprops:graphInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);

			// Serkan Taş 25 Ocak Pazar 20:13
			// Sunucu tarafında bağımlı iş tanımı ile ilgili kısım yapılınca açılacak
			// myraJobList = getDependencyInfo(j, myraJobList);
			
			myraJobList = getStateInfos(j, myraJobList);
			
			myraJobList = getManagementInfo(j, myraJobList);
			
			myraJobList = getLogAnalysis(j, myraJobList);

			myraJobList = getScheduleInfo(j, myraJobList);

			return myraJobList;
			
		}
		
		private static function getDependencyInfo(j:JobEditWindow, myraJobList:XML):XML {
			
			// Ekran tarafı yapılmadan burası yapılamaz
			
/*			<myra2:DependencyList xmlns:myra2="http://www.likyateknoloji.com/myra-jobprops">
				<myra2:sensInfo>
					<myra2:sensTime delay="PT30S" relativeStart="true" />
				</myra2:sensInfo>
			<wla:Item dependencyID="mydep">
				<wla:jsName>depJsName</wla:jsName>
				<wla:jsId>0</wla:jsId>
				<wla:jsType>JOB</wla:jsType>
				<lik:comment>no comment</lik:comment>
				<myra1:jsDependencyRule>
					<myra1:StateName>PENDING</myra1:StateName>
					<myra1:SubstateName>IDLED</myra1:SubstateName>
					<myra1:StatusName>BYTIME</myra1:StatusName>
				</myra1:jsDependencyRule>
			</wla:Item>
			<myra2:DependencyExpression>mydep</myra2:DependencyExpression>
		</myra2:DependencyList>*/
			
			if(j.dependencyListForm.dependencyListGrid.dataProvider.length == 0) {
				return myraJobList;
			}
			
			myraJobList.myra::genericJob.appendChild(<myra-jobprops:DependencyList xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
			
			var dependencyListXML:Object = myraJobList.myra::genericJob.myra_jobprops::DependencyList;

			if(j.dependencyListForm.sensInfo.selectedValue == "time") {
				dependencyListXML.appendChild(<myra-jobprops:sensInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"><myra-jobprops:sensTime/></myra-jobprops:sensInfo>);
				dependencyListXML.myra_jobprops::sensInfo.myra_jobprops::sensTime.@delay = j.dependencyListForm.step.text;
				dependencyListXML.myra_jobprops::sensInfo.myra_jobprops::sensTime.@relativeStart = j.dependencyListForm.relative.selectedItem;
			} 
			
			var counter:int = 0;
			for each (var item:Object in j.dependencyListForm.dependencyListGrid.dataProvider.toArray()) {
				dependencyListXML.appendChild(<wla:Item xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				dependencyListXML.wla::Item.@dependencyID = item.depid;
				
				var pairs:Array = item.jobinfo.split(':');
				
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsName xmlns:wla="http://www.likyateknoloji.com/wla-gen">{pairs[1]}</wla:jsName>);
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsId xmlns:wla="http://www.likyateknoloji.com/wla-gen">{pairs[0]}</wla:jsId>);
				dependencyListXML.wla::Item[counter].appendChild(<wla:jsType xmlns:wla="http://www.likyateknoloji.com/wla-gen">JOB</wla:jsType>);
				dependencyListXML.wla::Item[counter].appendChild(<lik:comment xmlns:lik="http://www.likyateknoloji.com/likya-gen">{item.comment}</lik:comment>);
				
				dependencyListXML.wla::Item[counter].appendChild(<myra-jobprops:jsDependencyRule xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops"/>);
				
				var sssTmp:SSS = new SSS(item.stateinfo);
				
				dependencyListXML.wla::Item[counter].myra_jobprops::jsDependencyRule.appendChild(<myra-jobprops:StateName xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{sssTmp.state}</myra-jobprops:StateName>);
				
				if(sssTmp.substate != null) {
					dependencyListXML.wla::Item[counter].myra_jobprops::jsDependencyRule.appendChild(<myra-jobprops:SubstateName xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{sssTmp.substate}</myra-jobprops:SubstateName>);
				}
				
				if(sssTmp.status != null) {
					dependencyListXML.wla::Item[counter].myra_jobprops::jsDependencyRule.appendChild(<myra-jobprops:StatusName xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{sssTmp.status}</myra-jobprops:StatusName>);
				}
				
			}
			
			dependencyListXML.appendChild(<myra-jobprops:DependencyExpression xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{j.dependencyListForm.depExp.text}</myra-jobprops:DependencyExpression>);
			
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
			
			if(j.managementInfoForm.jsJobTriggerType.selectedItem.value == "USER") {
				managementInfoXML.appendChild(<wla:trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">USER</wla:trigger>);
			} else {
				
				/** Trigger Info ****/
				
				managementInfoXML.appendChild(<wla:trigger xmlns:wla="http://www.likyateknoloji.com/wla-gen">TIME</wla:trigger>);
				
				/** Period Info ****/
				
				if(j.managementInfoForm.periodInfo.selected) {
					managementInfoXML.appendChild(<myra-jobprops:periodInfo xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
					
					managementInfoXML.periodInfo.@relativeStart = j.managementInfoForm.relativeStart.selectedItem;
					managementInfoXML.periodInfo.@step = j.managementInfoForm.stepValue.text;
					managementInfoXML.periodInfo.@maxCount = j.managementInfoForm.maxCountValue.text;
				}
				
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
				
				dateString = dateString + "T" + zeroize(j.managementInfoForm.bhour.text) + ":" + zeroize(j.managementInfoForm.bminute.text) + ":" + zeroize(j.managementInfoForm.bsecond.text) + ".000+02:00";
				
				managementInfoXML.timeManagement.bornedPlannedTime.startTime = dateString;
				managementInfoXML.timeManagement.jsPlannedTime.startTime = dateString;
				
				sDate = j.managementInfoForm.edate.selectedDate;
				dateString = currentDF.format(sDate);
				
				dateString = dateString + "T" + zeroize(j.managementInfoForm.ehour.text) + ":" + zeroize(j.managementInfoForm.eminute.text) + ":" + zeroize(j.managementInfoForm.esecond.text) + ".000+02:00";
				
				managementInfoXML.timeManagement.bornedPlannedTime.stopTime = dateString;
				managementInfoXML.timeManagement.jsPlannedTime.stopTime = dateString;
				
				managementInfoXML.timeManagement.jsTimeOut.lik::value_integer = j.managementInfoForm.timoutValue.text;
				managementInfoXML.timeManagement.jsTimeOut.lik::unit = j.managementInfoForm.timeoutUnit.selectedItem;
				
				managementInfoXML.timeManagement.expectedTime.lik::value_integer = j.managementInfoForm.expectedValue.text;
				managementInfoXML.timeManagement.expectedTime.lik::unit = j.managementInfoForm.expectedTimeUnit.selectedItem;
				
				/** Cascading Conditions ****/
				
				managementInfoXML.appendChild(<wla:cascadingConditions xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				
				managementInfoXML.cascadingConditions.appendChild(<wla:runEvenIfFailed xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				managementInfoXML.cascadingConditions.appendChild(<wla:jobSafeToRestart xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
				
				managementInfoXML.cascadingConditions.runEvenIfFailed = j.managementInfoForm.runEvenFailed.selectedItem;
				managementInfoXML.cascadingConditions.jobSafeToRestart = j.managementInfoForm.safeToRestart.selectedItem;
				
				if(j.managementInfoForm.autoRetry.selectedItem == "true") {
					managementInfoXML.cascadingConditions.appendChild(<wla:jobAutoRetryInfo xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
					managementInfoXML.cascadingConditions.wla::jobAutoRetryInfo.appendChild(<wla:jobAutoRetry xmlns:wla="http://www.likyateknoloji.com/wla-gen" />);
					
					managementInfoXML.cascadingConditions.jobAutoRetryInfo.@step = j.managementInfoForm.arStepValue.text;
					if(j.managementInfoForm.maxCountValueAr.text != "") {
						managementInfoXML.cascadingConditions.jobAutoRetryInfo.@maxCount = j.managementInfoForm.maxCountValueAr.text;
					}
					
					managementInfoXML.cascadingConditions.jobAutoRetryInfo.jobAutoRetry = j.managementInfoForm.autoRetry.selectedItem;
				}
			
				
				
			}
			
			return myraJobList;
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
			
			myraJobList.myra::genericJob.appendChild(<wla:logAnalysis xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
			
			var logAnalysisXML:Object = myraJobList.myra::genericJob.wla::logAnalysis;
			
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
			thencaseXML.wla::forcedResult.appendChild(<myra-stateinfo:LiveStateInfo LSIDateTime="" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo" />);
			
			if(j.logAnalysisForm.thenSSS.stateName.selectedIndex > -1) {
				thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.stateName.selectedItem}</myra-stateinfo:StateName>);
			}
			
			if(j.logAnalysisForm.thenSSS.substateName.selectedIndex > -1) {
				thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:SubstateName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.substateName.selectedItem.value}</myra-stateinfo:SubstateName>);
			} 
			
			if(j.logAnalysisForm.thenSSS.statusName.selectedIndex > -1) {
				thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(<myra-stateinfo:StatusName xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">{j.logAnalysisForm.thenSSS.statusName.selectedItem.value}</myra-stateinfo:StatusName>);
			}
			
			if(j.logAnalysisForm.thenRetCode.text != "" || j.logAnalysisForm.thenRetCodeDesc.text != "") {
				thencaseXML.wla::forcedResult.myra_stateinfo::LiveStateInfo.appendChild(
					<myra-stateinfo:ReturnCode cdId="string" xmlns:myra-stateinfo="http://www.likyateknoloji.com/myra-stateinfo">
												<myra-stateinfo:Code>{j.logAnalysisForm.thenRetCode.text}</myra-stateinfo:Code>
												<myra-stateinfo:Desc>{j.logAnalysisForm.thenRetCodeDesc.text}</myra-stateinfo:Desc>
											</myra-stateinfo:ReturnCode>);
			}
			
			var dateTimeString:String = getformatteddatetime();		
			
			logAnalysisXML.wla::action.wla::thencase.wla::forcedResult.myra_stateinfo::LiveStateInfo.@LSIDateTime = dateTimeString;
			
			
			// Elsecase definitons...
			if(j.logAnalysisForm.elseActionCheckBox.selected) {

				logAnalysisXML.wla::action.appendChild(<wla:elsecase xmlns:wla="http://www.likyateknoloji.com/wla-gen"/>);
				var elsecaseXML:Object = logAnalysisXML.wla::action.wla::elsecase;


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
				
				if(j.scheduleInfoForm.fdom.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:firstDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}

				if(j.scheduleInfoForm.ldom.selected) {
					scheduleInfoXML.appendChild(<myra-jobprops:lastDayOfMonth xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops" />);
				}
				
				if(j.scheduleInfoForm.specDays.text.length > 0) {
					
					var specDays:String = j.scheduleInfoForm.specDays.text;
					
					if(specDays.indexOf(',') > 0) {
						var results:Array = specDays.split(',');
						for each (var item:String in results) {
							
							if(item.indexOf('-') > 0) {
								var dashList:Array = item.split('-');
								if(dashList.length == 1) {
									scheduleInfoXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{dashList[0]}</myra-jobprops:days>);
								} else if(dashList.length > 1) {
									var counter:int = 0;								
									for each (var dashitem:String in dashList) {
										scheduleInfoXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{dashList[counter ++]}</myra-jobprops:days>);
									}
								}
							} else {
								scheduleInfoXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{item}</myra-jobprops:days>);
							}
						}
					} else {
						scheduleInfoXML.appendChild(<myra-jobprops:days xmlns:myra-jobprops="http://www.likyateknoloji.com/myra-jobprops">{specDays}</myra-jobprops:days>);
					}
					
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

			baseJobInfosXML.wla::jsName = j.baseInfoForm.jsName.text;
			baseJobInfosXML.wla::jobLogFile = j.baseInfoForm.jsJobLogFile.text;
			baseJobInfosXML.wla::jobLogPath = j.baseInfoForm.jsJobLogPath.text;
			baseJobInfosXML.wla::oSystem = j.baseInfoForm.jsOsType.selectedItem;
			baseJobInfosXML.wla::jobPriority = j.baseInfoForm.jsJobPriority.selectedItem;
			baseJobInfosXML.myra_jobprops::jsIsActive = j.baseInfoForm.jsJobActivity.selectedItem;
			baseJobInfosXML.lik::userId = "007";
			
			
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommandType = j.baseInfoForm.jsJobType.selectedItem;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobCommand = j.baseInfoForm.jsCommand.text;
			baseJobInfosXML.lik::jobTypeDetails.lik::jobWorkDir = j.baseInfoForm.jsJobWorkDir.text; 
			baseJobInfosXML.lik::jobTypeDetails.lik::argValues = j.baseInfoForm.jsJobArgs.text; 
			
			
			if(j.baseInfoForm.envVarList.dataProvider.length > 0) {
				baseJobInfosXML.lik::jobTypeDetails.appendChild(<lik:envVariables xmlns:lik="http://www.likyateknoloji.com/likya-gen"/>);
				for each (var item:String in j.baseInfoForm.envVarList.dataProvider.toArray()) {
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
			currentDF.formatString = "LL:NN:SS";
			var timeString:String = currentDF.format(currentDateTime);
			
			var dateTimeString:String = dateString + "T" + timeString + ".000+02:00";	
			
			return dateTimeString;
		}
	}
}

