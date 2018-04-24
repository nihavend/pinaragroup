package com.likya.pinara.model {
	
	import flash.events.EventDispatcher;
	
	public class AppInfo extends EventDispatcher {
		
		[Bindable]
		public var version:String = "";
		
		public function AppInfo()
		{
		}
		
		public function getVersion():String
		{
			return version;
		}
	
		public function setVersion(version:String):void
		{
			this.version = version;
		}

	}
}

