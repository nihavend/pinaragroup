package com.likya.pinara.utils {
	
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
	}
}