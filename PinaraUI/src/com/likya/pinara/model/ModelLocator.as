package com.likya.pinara.model
{
	

	public class ModelLocator
	{
		private static var modelLocator:ModelLocator;
		
		public static function getInstance():ModelLocator
		{
			if(modelLocator == null)
			{
				modelLocator = new ModelLocator();
			}
			return modelLocator;
		}
		
		[Bindable]
		public var currentUser:User;
		
		[Bindable]
		public var currentBusyCursorId:int;
		
		[Bindable]
		public var appInfo:AppInfo;
	}
}