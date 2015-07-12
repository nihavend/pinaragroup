package com.likya.tloslite.valueObjects {
	import com.likya.pinara.model.Permissions;
	
	public class UserRole {
		public static const ADMIN:String = "ADMIN";
		public static const OPERATION:String = "OPERATION";
		public static const VIEW:String = "VIEW";
		
		private var _adminPermissions:Array;
		private var _operationPermissions:Array;
		private var _viewPermissions:Array;
		
		public function UserRole() {
			_viewPermissions = [];
			
			_operationPermissions = [Permissions.SUSPEND_TLOS_MENUITEM, Permissions.RESUME_TLOS_MENUITEM, 
				Permissions.NORMAL_SHUTDOWN_MENUITEM, Permissions.FORCE_SHUTDOWN_MENUITEM, 
				Permissions.DISABLE_JOB_BUTTON, Permissions.START_JOB_BUTTON, Permissions.PAUSE_JOB_BUTTON, 
				Permissions.ENABLE_JOB_BUTTON, Permissions.RETRY_JOB_BUTTON, Permissions.SETSUCCESS_JOB_BUTTON, 
				Permissions.SKIP_JOB_BUTTON, Permissions.STOP_JOB_BUTTON, Permissions.RESUME_JOB_BUTTON, 
				Permissions.PARAMETER_BUTTON];
			
			_adminPermissions = [Permissions.SUSPEND_TLOS_MENUITEM, Permissions.RESUME_TLOS_MENUITEM, 
				Permissions.NORMAL_SHUTDOWN_MENUITEM, Permissions.FORCE_SHUTDOWN_MENUITEM, 
				Permissions.ADD_NEW_TLOS_NODE, Permissions.EDIT_TLOS_BUTTON, 
				Permissions.DELETE_TLOS_BUTTON, Permissions.CHANGE_PASSWORD_MENUITEM, 
				Permissions.DISABLE_JOB_BUTTON, Permissions.START_JOB_BUTTON, Permissions.PAUSE_JOB_BUTTON, 
				Permissions.ENABLE_JOB_BUTTON, Permissions.RETRY_JOB_BUTTON, Permissions.SETSUCCESS_JOB_BUTTON, 
				Permissions.SKIP_JOB_BUTTON, Permissions.STOP_JOB_BUTTON, Permissions.RESUME_JOB_BUTTON, 
				Permissions.EDIT_USER_BUTTON, Permissions.PARAMETER_BUTTON];
		}
		
		public function get adminPermissions():Array {
			return _adminPermissions;
		}
		
		public function get operationPermissions():Array {
			return _operationPermissions;
		}
		
		public function get viewPermissions():Array {
			return _viewPermissions;
		}
	}
}