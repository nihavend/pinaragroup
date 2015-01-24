package com.likya.pinara.test.events {
	import flash.events.Event;
	
	public class ResourceEvent extends Event {
	
		public static const LOGIN:String = "loginRequest";
		public static const LOGOUT:String = "logoutRequest";
		
		public static const CHANGE_LOCALE:String = "changeLocaleRequest";
		
		public static const JOBNODE_SELECTED:String = "jobNodeSelected";
		public static const LISTNODE_SELECTED:String = "listNodeSelected";
		
		public static const ANASAYFA:String = "anasayfa";
		
		public var object:Object;
	
		public function ResourceEvent(type:String, object:Object = null, bubbles:Boolean = true, cancelable:Boolean = true) {
			// Alert.show("Event : " + type);
			super(type, bubbles, cancelable);
   			this.object = object;
		}

	}
}