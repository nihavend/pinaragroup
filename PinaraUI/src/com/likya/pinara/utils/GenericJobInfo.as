package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.JobEditWindow;
	
	public class GenericJobInfo {
		
		public static function getXML(j:JobEditWindow):XML {
			
			var myraGenericJob:XML = <genericJob/>;
			
			myraGenericJob.@handlerURI = "com.likya.myra.jef.jobs.ExecuteInShell";
			myraGenericJob.@Id = "22";
			myraGenericJob.@groupId = "my_group";
			myraGenericJob.@agentId = "1";
			
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

