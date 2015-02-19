package com.likya.pinara.utils
{
	import com.likya.pinara.main.PinaraUI;
	import com.likya.pinara.model.UserInfo;
	
	import flash.net.URLVariables;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.http.mxml.HTTPService;
	import mx.utils.Base64Encoder;

	public class BasicAuthenticationHandler
	{
		public function BasicAuthenticationHandler()
		{
		}
		
		public static function authAndSend(service:HTTPService):void {
			var encoder:Base64Encoder = new Base64Encoder();
			encoder.insertNewLines = false; // see below for why you need to do this
			var tmpUserInfo:UserInfo = (FlexGlobals.topLevelApplication as PinaraUI).currentUser;
			// encoder.encode("pinara:pinara");
			encoder.encode(tmpUserInfo.username + ":" + tmpUserInfo.password);
			
			service.headers = {Authorization:"Basic " + encoder.toString()};   
			
			service.request.data = new URLVariables("name=ifthisdataisnotpassedPOSTmethodisconvertedtoGETbyflashplayer");
			
			if(service.url.indexOf('?_method=GET') <= 0) {
				service.url += "?_method=GET";
			}
			
			service.send();
		}
		
		public static function service_faultHandler(event:FaultEvent):void {
			// Alert.show("xmlService_faultHandler : " + event.toString());
			//outputText.text += "\nxmlService_faultHandler " + event;
			if (event.statusCode == 400) {
				Alert.show("Kullacını adı ya da şifre hatalı !");
			} else {
				Alert.show("Unexpected Event : " + event.toString());
			}
		}
	}
}