package com.likya.pinara.utils
{
	import mx.utils.StringUtil;

	public class CodeDesc
	{
		
		private var _code:int;
		private var _desc:String;
		
		public function CodeDesc(complexText:String)
		{
			if(complexText.indexOf(':') > 0) {
				var pairs:Array = complexText.split(':');
				if(pairs.length == 2) {
					_code = Number(StringUtil.trim(pairs[0]));
					_desc = pairs[1];
					return;
				}
			}
			throw new Error("Code desc couple is mulformed (eg : code:desc) : " + complexText);
		}

		public function get code():int
		{
			return _code;
		}

		public function set code(value:int):void
		{
			_code = value;
		}

		public function get desc():String
		{
			return _desc;
		}

		public function set desc(value:String):void
		{
			_desc = value;
		}


	}
}