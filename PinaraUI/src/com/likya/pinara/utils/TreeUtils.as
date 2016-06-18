package com.likya.pinara.utils
{
	import com.likya.pinara.containers.AutoSizeTree;
	

	public class TreeUtils
	{
		public static function findNode(dataProviderXml:XML, selectedTreeNodeId:Object):XMLList {
			// return (dataProviderXml).descendants().(@id.toLowerCase().search(selectedTreeNodeId) > -1);
			
			return dataProviderXml.descendants().(attribute("id") == selectedTreeNodeId);
		}
		
		public static function expandNode(liveTree:AutoSizeTree, xmlNode:XML):void
		{
			while (xmlNode.parent() != null) {
				xmlNode = xmlNode.parent();
				liveTree.expandItem(xmlNode, true, false);
			}
		}
	}
}