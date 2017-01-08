package testas {
	import mx.collections.XMLListCollection;
	
	public class MenuOperations {
		public function MenuOperations() {
		}
		
		public static function deleteRoledMenuItems(menuData:XMLListCollection, role:String): XMLListCollection {
			
			var retXmlList:XMLListCollection = new XMLListCollection();
			
			if(role == "ADMIN") {
				// filtre uygulanmaz
				return menuData;
			} 
			
			var i:int;
			for (i = 0; i < menuData.length; i++) {
				
				if(role == "OPERATION") { // filter for no ADMIN
					if (XMLList(menuData[i]).hasOwnProperty("@role") && XMLList(menuData[i]).@role == "ADMIN") {
						continue;
					}
				} else if(role == "VIEW") {// filter for VIEW and norole
					if (XMLList(menuData[i]).hasOwnProperty("@role") && XMLList(menuData[i]).@role != "VIEW") {
						continue;
					}
				} else {
					//  ???????
					return null;
				}
				
				var tmpXmlList:XMLList = XMLList(menuData[i]).copy();
				
				var j:int = 0;
				for each (var item:XML in tmpXmlList.children()) {
					delete tmpXmlList.children()[0];
				}
				trace(tmpXmlList);
				
				j = 0;
				for each (item in XMLList(menuData[i]).children()) {
					trace("Checking item " + item.@id);
					if(role == "OPERATION") { // filter for no ADMIN
						if (!item.hasOwnProperty("@role") || item.@role != "ADMIN") {
							tmpXmlList.appendChild(XMLList(menuData[i]).children()[j]);
						}
					} else if(role == "VIEW") {// filter for VIEW and norole
						if (!item.hasOwnProperty("@role") || item.@role == role) {
							// delete XMLList(testXml).children()[j];
							tmpXmlList.appendChild(XMLList(menuData[i]).children()[j]);
						}
					}
					j++;
				}
				
				retXmlList.addItem(tmpXmlList);
			}
			
			return retXmlList;
		}
		
		public static function deleteMenuItems(tlosMenuData:XMLListCollection, firstDepthId:String):void {
			var i:int;
			
			for (i = 0; i < tlosMenuData.length; i++) {
				if (XMLList(tlosMenuData[i]).@id == firstDepthId) {
					tlosMenuData.removeItemAt(i);
					return;
				}
			}
		}
		
		public static function testMe(): void {
			
/*			var testXml:XMLList = XMLList(
				<menuitem label="{resourceManager.getString('messages', 'configFiles')}" id="configfiles" role="ADMIN">			
					<menuitem label="{resourceManager.getString('messages', 'pinaraProperties')}" id="pinaraProperties" role="VIEW"/>			
					<menuitem label="{resourceManager.getString('messages', 'myraProperties')}" id="myraProperties"/>
					<menuitem label="{resourceManager.getString('messages', 'senaryoFile')}" id="scenarioFile" role="OPERATION"/>			
					<menuitem label="{resourceManager.getString('messages', 'myraLog')}" id="myraLog"/>
					<menuitem label="{resourceManager.getString('messages', 'pinaraLog')}" id="pinaraLog"/>
					<menuitem label="{resourceManager.getString('messages', 'pinaraEkran')}" id="pinaraEkran"/>
				</menuitem>);
*/	
			var testXml:XMLList = XMLList(
				<menuitem label="{resourceManager.getString('messages', 'configFiles')}" id="configfiles" role="OPERATION">			
					<menuitem label="{resourceManager.getString('messages', 'pinaraProperties')}" id="pinaraProperties" role="VIEW"/>			
					<menuitem label="{resourceManager.getString('messages', 'myraProperties')}" id="myraProperties"/>
					<menuitem label="{resourceManager.getString('messages', 'senaryoFile')}" id="scenarioFile" role="ADMIN"/>			
					<menuitem label="{resourceManager.getString('messages', 'myraLog')}" id="myraLog"/>
					<menuitem label="{resourceManager.getString('messages', 'pinaraLog')}" id="pinaraLog"/>
					<menuitem label="{resourceManager.getString('messages', 'pinaraEkran')}" id="pinaraEkran"/>
				</menuitem>);

			var myColl:XMLListCollection = new XMLListCollection();

			myColl.source = testXml;
			
			// var tmpXml:XMLList = deleteRoledMenuItems(testXml, "ADMIN");
			var tmpXml:XMLListCollection = deleteRoledMenuItems(myColl, "OPERATION");
			
			trace("xxxxxxxxxxxxxxxxxxxxxxxxx");
			trace(tmpXml);
			trace("xxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
		/*		public static function deleteInnerMenuItems(tlosMenuData:XMLListCollection, firstDepthId:String, secondDepthId:String):void {
		var i:int;
		
		for (i = 0; i < tlosMenuData.length; i++) {
		if (XMLList(tlosMenuData[i]).@id == firstDepthId) {
		var j:int = 0;
		
		for each (var item:XML in XMLList(tlosMenuData[i]).children()) {
		if (item.@id == secondDepthId) {
		delete XMLList(tlosMenuData[i]).children()[j];
		return;
		}
		j++;
		}
		}
		}
		}
		
		public static function deleteRoledMenuItemsOrj(tlosMenuData:XMLListCollection, role:String): XMLListCollection {
		
		var retList:XMLListCollection = new XMLListCollection();
		
		var testXml:XMLList = XMLList(
		<menuitem label="{resourceManager.getString('messages', 'configFiles')}" id="configfiles" role="ADMIN">			
		<menuitem label="{resourceManager.getString('messages', 'pinaraProperties')}" id="pinaraProperties" role="VIEW"/>			
		<menuitem label="{resourceManager.getString('messages', 'myraProperties')}" id="myraProperties"/>
		<menuitem label="{resourceManager.getString('messages', 'senaryoFile')}" id="scenarioFile" role="OPERATION"/>			
		<menuitem label="{resourceManager.getString('messages', 'myraLog')}" id="myraLog"/>
		<menuitem label="{resourceManager.getString('messages', 'pinaraLog')}" id="pinaraLog"/>
		<menuitem label="{resourceManager.getString('messages', 'pinaraEkran')}" id="pinaraEkran"/>
		</menuitem>);
		
		trace(testXml);
		role = "VIEW";
		// delete testXml.children()[0];
		
		var tmpXmlList:XMLList = XMLList(testXml).copy();
		
		var j:int = 0;
		for each (var item:XML in XMLList(testXml).children()) {
		delete XMLList(testXml).children()[0];
		}
		trace(testXml);
		
		j = 0;
		for each (item in tmpXmlList.children()) {
		trace("Checking item " + item.@id);
		if (!item.hasOwnProperty("@role") || item.@role == role) {
		// delete XMLList(testXml).children()[j];
		XMLList(testXml).appendChild(tmpXmlList.children()[j]);
		}
		j++;
		}
		trace(testXml);
		
		// trace(testXml.menuitem.(hasOwnProperty('@role') && @role == "ADMIN"));
		
		
		return retList;
		}
		}*/
	}
}