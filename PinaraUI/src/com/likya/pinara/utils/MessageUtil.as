package com.likya.pinara.utils
{
	import mx.core.FlexGlobals;
	import mx.resources.ResourceManager;

	public class MessageUtil
	{
		public static const ERROR:String = ResourceManager.getInstance().getString('messages', 'error'); 
		public static const WARNING:String = ResourceManager.getInstance().getString('messages', 'warning');
		public static const INFO:String = ResourceManager.getInstance().getString('messages', 'info'); 
		
		public static function showMessagePanel(msgType:String, message:String):void 
		{ 
			FlexGlobals.topLevelApplication.messagePanel.title = msgType; 
			FlexGlobals.topLevelApplication.messageText.text = message; 
			FlexGlobals.topLevelApplication.messagePanel.visible = true; 
			FlexGlobals.topLevelApplication.messagePanel.includeInLayout = true;
		}
		
		public static function closeMessagePanel():void 
		{ 
			FlexGlobals.topLevelApplication.messagePanel.visible = false; 
			FlexGlobals.topLevelApplication.messagePanel.includeInLayout = false;
		}

	}
}