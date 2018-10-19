package com.likya.pinara.event {
	import flash.events.Event;
	
	public class ResourceEvent extends Event {
	
		public static const LOGIN:String = "loginRequest";
		public static const LOGOUT:String = "logoutRequest";
		
		public static const CHANGE_LOCALE:String = "changeLocaleRequest";
		
		public static const JOBNODE_SELECTED:String = "jobNodeSelected";
		public static const LISTNODE_SELECTED:String = "listNodeSelected";
		
		public static const ANASAYFA:String = "anasayfa";
		
		public static const REFRESH:String = "refresh";
		
		public static const UPDATE_TREE:String = "updatetree";
		
		public static const ROW_SELECTION_CHANGED:String = "rowSelectionChangedEvent";
		
		public static const REFRESH_SELECT:String = "refreshselect";
		
		public static const SELECT_TREENODE:String = "selecttreenode";
		
		public static const DECORATE_MENUBAR:String = "decoratemenubar";
		
		public static const SERVER_UNREADY_LOGOUT:String = "serverUnreadyLogoutRequest";
		
		public static const JOB_TYPE_CHANGED:String = "jobTypeChangedEvent";
		
		public static const UPDATE_ARGS:String = "updateargs";
		
		public var object:Object;
	
		public function ResourceEvent(type:String, object:Object = null, bubbles:Boolean = true, cancelable:Boolean = true) {
			// Alert.show("Event : " + type);
			super(type, bubbles, cancelable);
   			this.object = object;
		}

	}
}