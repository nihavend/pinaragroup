package com.likya.pinara.test.actscrpts
{
	public class DataLoader
	{
		public function DataLoader()
		{
		}
		
		public static function loadXML2():XML {
		
			var treeXml:XML =	
				<list title="ana liste" id="000">
				  <parentNode ID="2" Title="Hand Tools" NodeType="root" isBranch="true" isOpen="false">
					<equipment ID="0" Title="[Add New Category]" NodeType="subcat" isBranch="false" ParentID="2" isOpen="false"/>
					<equipment ID="0" Title="[Add New Item]" NodeType="subcat" isBranch="false" ParentID="2" isOpen="false"/>
				  </parentNode>
				  <parentNode ID="4" Title="Power Tools" NodeType="root" isBranch="true" isOpen="false">
					<equipment ID="10" Title="Hydraulics" NodeType="subcat" isBranch="true" ParentID="4" isOpen="false">
					  <item ID="8" Title="Tool 1" NodeType="item" isBranch="false"/>
					  <item ID="9" Title="Tool 2" NodeType="item" isBranch="false"/>
					  <item ID="10" Title="Tool 3" NodeType="item" isBranch="false"/>
					  <item ID="11" Title="Tool 4" NodeType="item" isBranch="false"/>
					  <item ID="12" Title="Tool 5" NodeType="item" isBranch="false"/>
					  <equipment ID="0" Title="[Add New Category]" NodeType="subcat" isBranch="false" ParentID="10" isOpen="false"/>
					  <equipment ID="0" Title="[Add New Item]" NodeType="subcat" isBranch="false" ParentID="10" isOpen="false"/>
					</equipment>
					<equipment ID="11" Title="Electric, Cordless" NodeType="subcat" isBranch="true" ParentID="4" isOpen="false">
					  <item ID="28" Title="9v screwdriver" NodeType="item" isBranch="false"/>
					  <equipment ID="12" Title="12V" NodeType="subcat" isBranch="true" ParentID="11" isOpen="false">
						<equipment ID="0" Title="[Add New Category]" NodeType="subcat" isBranch="false" ParentID="12" isOpen="false"/>
						<equipment ID="0" Title="[Add New Item]" NodeType="subcat" isBranch="false" ParentID="12" isOpen="false"/>
					  </equipment>
					  <equipment ID="13" Title="18V" NodeType="subcat" isBranch="true" ParentID="11" isOpen="false">
						<equipment ID="0" Title="[Add New Category]" NodeType="subcat" isBranch="false" ParentID="13" isOpen="false"/>
						<equipment ID="0" Title="[Add New Item]" NodeType="subcat" isBranch="false" ParentID="13" isOpen="false"/>
					  </equipment>
					  <equipment ID="0" Title="[Add New Category]" NodeType="subcat" isBranch="false" ParentID="11" isOpen="false"/>
					  <equipment ID="0" Title="[Add New Item]" NodeType="subcat" isBranch="false" ParentID="11" isOpen="false"/>
					</equipment>
				   </parentNode>
				</list>;
		
				return treeXml;
		}
		
		public static function loadXMLLong():XML {
				
				var treeXml:XML = <list title="ana liste" id="000">
							<jobGroup title="group_1" id="1">
								<jobProperty title="Dosyayı Yükle" id="1" ip="1" port="1"/>
								<jobProperty title="Diske yaz" id="2" ip="1" port="1" />
								<jobGroup title="group_2" id="21">
									<jobGroup title="group_2" id="1">
										<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
										<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
										<jobGroup title="group_2" id="22">
											<jobProperty title="ETL_LOAD_SUBSCDATA" id="1" ip="1" port="1" />
											<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
										</jobGroup>
									</jobGroup>
									<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
									<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
								</jobGroup>
							</jobGroup>
							<jobGroup title="group_2" id="2">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="3">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="3" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="4">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="4" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="5">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="5" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="6">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="6" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="7">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="7" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="8">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="8" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="9">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="9" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
							<jobGroup title="group_2" id="10">
								<jobProperty title="ETL_LOAD_SUBSCDATA" id="10" ip="1" port="1" />
								<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
							</jobGroup>
						</list>;
				
				return treeXml;
		}
		
		public static function loadXMLLongNew():XML {
		
			var treeXml:XML = 
				<list title="ana liste" id="000">
											<jobGroup title="group_1" id="1">
												<jobProperty title="Dosyayı Yükle" id="1" ip="1" port="1"/>
												<jobProperty title="Diske yaz" id="2" ip="1" port="1" />
												<jobProperty title="Dosyayı Yükle" id="1" ip="1" port="1"/>
												<jobProperty title="Diske yaz" id="2" ip="1" port="1" />
												<jobGroup title="group_2" id="21">
													<jobGroup title="group_2" id="1">
														<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
														<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
														<jobGroup title="group_2" id="22">
															<jobProperty title="ETL_LOAD_SUBSCDATA" id="1" ip="1" port="1" />
															<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
														</jobGroup>
													</jobGroup>
													<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
													<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
												</jobGroup>
											</jobGroup>
											<jobGroup title="group_2" id="2">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="2" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="3">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="3" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="4">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="4" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="5">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="5" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="6">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="6" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="7">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="7" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="8">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="8" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="9">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="9" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
											<jobGroup title="group_2" id="10">
												<jobProperty title="ETL_LOAD_SUBSCDATA" id="10" ip="1" port="1" />
												<jobProperty title="UPDATE STATUS" id="2" ip="1" port="1" />
											</jobGroup>
										</list>;
			
			return treeXml;
		}
		
		public static function loadSimple():XML {
			
			var treeXml:XML = 
				<list title="Grup Listesi" id="0">
				  <jobGroup title="Independents" id="-1">
					<jobProperty title="ilk tanımlı iş" id="23" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="24" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="25" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="26" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="27" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="28" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="29" statu="9" port="1"/>
					<jobProperty title="serkan işiiiii" id="30" statu="9" port="1"/>

				  </jobGroup>
				</list>
			
			return treeXml;
		}
	}
}