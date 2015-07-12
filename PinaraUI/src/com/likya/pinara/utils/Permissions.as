package com.likya.tloslite.valueObjects {
	
	public class Permissions {
		public static const ADD_NEW_TLOS_NODE:Permissions = new Permissions("addTlos");
		
		public static const SUSPEND_TLOS_MENUITEM:Permissions = new Permissions("suspendTlos");
		public static const RESUME_TLOS_MENUITEM:Permissions = new Permissions("resumeTlos");
		public static const NORMAL_SHUTDOWN_MENUITEM:Permissions = new Permissions("shutdownNormal");
		public static const FORCE_SHUTDOWN_MENUITEM:Permissions = new Permissions("forceShutdown");
		public static const CHANGE_PASSWORD_MENUITEM:Permissions = new Permissions("changePassword");
		
		public static const EDIT_TLOS_BUTTON:Permissions = new Permissions("editTlos");
		public static const DELETE_TLOS_BUTTON:Permissions = new Permissions("deleteTlos");
		public static const DISABLE_JOB_BUTTON:Permissions = new Permissions("disableJob");
		public static const START_JOB_BUTTON:Permissions = new Permissions("startJob");
		public static const PAUSE_JOB_BUTTON:Permissions = new Permissions("pauseJob");
		public static const ENABLE_JOB_BUTTON:Permissions = new Permissions("enableJob");
		public static const RETRY_JOB_BUTTON:Permissions = new Permissions("retryJob");
		public static const SETSUCCESS_JOB_BUTTON:Permissions = new Permissions("setSuccessJob");
		public static const SKIP_JOB_BUTTON:Permissions = new Permissions("skipJob");
		public static const STOP_JOB_BUTTON:Permissions = new Permissions("stopJob");
		public static const RESUME_JOB_BUTTON:Permissions = new Permissions("resumeJob");
		public static const EDIT_USER_BUTTON:Permissions = new Permissions("editUser");
		public static const PARAMETER_BUTTON:Permissions = new Permissions("setParameter");
		
		private var name:String;
		
		public function Permissions(name:String) {
			this.name = name;
		}
		
		public function equals(permission:Permissions):Boolean {
			if(permission != null) {
				return this.name == permission.name;
			}
			return false;
		}
	}
}