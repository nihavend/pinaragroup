package com.likya.pinara.utils {
	import com.likya.pinara.comps.jobcrud.StateInfosForm;
	
	import mx.collections.ArrayList;
	
	public class AddJobDefaults {
	
		public static function prepare_stateInfosForm(stateInfosForm:StateInfosForm):void {
			
			var stateInfos:XML = 
				<stateInfos>
					<JobStatusList>
						<JobStatus>
							<StatusName>SUCCESS</StatusName>
							<Desc>0</Desc>
							<ReturnCodeList osType="MACOS">
								<ReturnCode>
									<Code>0</Code>
									<Desc>0</Desc>
								</ReturnCode>
							</ReturnCodeList>
						</JobStatus>
					</JobStatusList>
					<LiveStateInfos/>
				</stateInfos>;
			

			for each (var item:Object in stateInfos.JobStatusList.JobStatus) {
				
				var retList:ArrayList = new ArrayList();
				for each (var innerItem:Object in item.ReturnCodeList.ReturnCode) {
					retList.addItem(innerItem.Code + ":" + innerItem.Desc);
				}
				
				var stateArray:Object = {statusname:item.StatusName, desc:item.Desc, retCodeList:retList};
				stateInfosForm.statusInfoGrid.dataProvider.addItem(stateArray);
			}
			
		}

	}
}

