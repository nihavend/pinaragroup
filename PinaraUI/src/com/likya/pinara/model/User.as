package com.likya.pinara.model {
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	
	public class User extends EventDispatcher
	{
		
		private static const ROLES_CHANGED:String = "rolesChanged";
		
		[Bindable]
		public var username:String;
		
		public var password:String;
		public var roleinfo:String;
		public var statuinfo:String;
		
		private var _roles:Array;
		
		public function get roles():Array
		{
			return _roles;
		}
		public function set roles(a:Array):void
		{
			_roles = a;
			dispatchEvent(new Event(ROLES_CHANGED));
		}
		
		public function User(username:String = "", password:String = "")
		{
			this.username = username;
			this.password = password;
		}
		
		[Bindable(event=ROLES_CHANGED)]
		public function hasRole(userRole:UserRole):Boolean
		{
			if(roles != null)
			{
				for each(var role:UserRole in roles)
				{
					/*if(role.equals(userRole))
					{
						return true;
					}*/
				}
				return false;
			}
			return false;
		}
		
	}
}