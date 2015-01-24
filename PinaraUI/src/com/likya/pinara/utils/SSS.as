package com.likya.pinara.utils
{
	import mx.utils.StringUtil;

	public class SSS
	{
		
		private var _state:String;
		private var _substate:String;
		private var _status:String;
		
		public function SSS(complexText:String)
		{
			if(complexText.indexOf(':') > 0) {
				
				var pairs:Array = complexText.split(':');
				
				if(pairs.length > 0) {
					_state = StringUtil.trim(pairs[0]);
				} 
				if(pairs.length > 1) {
					_substate = StringUtil.trim(pairs[1]);
				} 
				if(pairs.length > 2) {
					_status = StringUtil.trim(pairs[2]);
				} 
				
				return;
			} else if(complexText != null) {
				_state = StringUtil.trim(complexText);
				return;
			}
			throw new Error("SSS is malformed (eg : s:s:s) : " + complexText);
		}

		public function get state():String
		{
			return _state;
		}

		public function set state(value:String):void
		{
			_state = value;
		}

		public function get substate():String
		{
			return _substate;
		}

		public function set substate(value:String):void
		{
			_substate = value;
		}

		public function get status():String
		{
			return _status;
		}

		public function set status(value:String):void
		{
			_status = value;
		}


	}
}