package vo
{
	import mx.rpc.xml.XMLDecoder;
	
	public class StrictXMLDecoder extends XMLDecoder
	{
		public function StrictXMLDecoder()
		{
			super();
		}
		
		public function set strictMode(value:Boolean):void
		{
			strictOccurenceBounds = value;
		}
		
		public function get strictMode():Boolean
		{
			return strictOccurenceBounds;
		}
	}
}