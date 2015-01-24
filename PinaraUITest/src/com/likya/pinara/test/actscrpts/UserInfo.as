package com.likya.pinara.test.actscrpts {
	
	public class UserInfo {
	
		private var _username:String = new String();
		private var _password:String = new String();

		public function get username():String {
			return _username;
		}

		public function set username(value:String):void {
			_username = value;
		}

		public function get password():String {
			return _password;
		}

		public function set password(value:String):void {
			_password = value;
		}

	}
}