package com.likya.pinara.model.transfer.tree {
	
	public class JobGroupModel {
	
		private var _groupName:String = new String();
		private var _groupId:String = new String();
		private var _groupType:String = new String();

		private var _jobNodeList:Array = new Array();
		

		public function get groupName():String
		{
			return _groupName;
		}

		public function set groupName(value:String):void
		{
			_groupName = value;
		}

		public function get groupId():String
		{
			return _groupId;
		}

		public function set groupId(value:String):void
		{
			_groupId = value;
		}

		public function get groupType():String
		{
			return _groupType;
		}

		public function set groupType(value:String):void
		{
			_groupType = value;
		}

		[ArrayElementType("com.likya.pinara.model.JobNodeModel")]
		public function get jobNodeList():Array
		{
			return _jobNodeList;
		}

		public function set jobNodeList(value:Array):void
		{
			_jobNodeList = value;
		}


	}
}