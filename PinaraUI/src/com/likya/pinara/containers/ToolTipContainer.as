package com.likya.pinara.containers
{
	import mx.collections.ArrayList;
	import mx.resources.ResourceManager;
	
	public class ToolTipContainer {
		
		private static var toolTipList:ArrayList = new ArrayList();

		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'readyTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'waitingTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'workingTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'successfulTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'failedTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'timeoutTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'skippedTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'stoppedTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'pausedTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'workingTooltip'))
		toolTipList.addItem(ResourceManager.getInstance().getString('messages', 'disabledTooltip'))

		public function ToolTipContainer() {}
		
		public static function getToolTip(data:Object):String {
			
			var statu:int = data.visualParams.statu;
			
			// Alert.show("Vparams : " + data.visualParams);
			// Alert.show("statu:" + statu + " image:" + imageList.getItemAt(statu));
			
			return toolTipList.getItemAt(statu) as String;
		}
		
	}
}

