package com.likya.pinara.model {
	
	import flash.events.EventDispatcher;
	
	public class AppInfo extends EventDispatcher {
		
		public static const OS_WINDOWS:String = "Windows";
		public static const OS_UNIX:String = "Unix"; 
		private static const OS_WINDOWS7:String = OS_WINDOWS + " " + "7";  
		private static const OS_WINDOWS_VISTA:String = OS_WINDOWS + " " + "Vista";  
		private static const OS_WINDOWS_2003:String = OS_WINDOWS + " " + "2003";  
		private static const OS_WINDOWS_NT:String = OS_WINDOWS + " " + "NT";  
		private static const OS_WINDOWS_95:String = OS_WINDOWS + " " + "95";  
		private static const OS_WINDOWS_XP:String = OS_WINDOWS + " " + "XP";  
		private static const OS_HP_UX:String = "HP-UX"; 
		private static const OS_AIX:String = "AIX"; 
		private static const OS_LINUX:String = "Linux"; 
		private static const OS_SUNOS:String = "SunOS"; 
		private static const MAC_OS_X:String = "Mac OS X"; 
		
		[Bindable]
		public var version:String = "";
		
		public var osname:String = "";
		public var ostype:String = "";

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

		public function setOsType(osname:String):void
		{	
			if(osname.indexOf("Windows") != -1) {
				this.ostype = OS_WINDOWS;
			} else {
				this.ostype = OS_UNIX;
			}
		}

	}
}

