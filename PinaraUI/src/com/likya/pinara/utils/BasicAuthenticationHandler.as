package com.likya.pinara.utils
{
	import com.adobe.fiber.services.wrapper.HTTPServiceWrapper;
	import com.likya.pinara.model.ModelLocator;
	import com.likya.pinara.model.User;
	
	import flash.display.DisplayObject;
	import flash.net.URLVariables;
	
	import mx.controls.Alert;
	import mx.core.IFlexDisplayObject;
	import mx.managers.PopUpManager;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
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
			var tmpUserInfo:User = ModelLocator.getInstance().currentUser;
			// encoder.encode("pinara:pinara");
			encoder.encode(tmpUserInfo.username + ":" + tmpUserInfo.password);
			
			service.headers = {Authorization:"Basic " + encoder.toString()};   
			
			service.request.data = new URLVariables("name=ifthisdataisnotpassedPOSTmethodisconvertedtoGETbyflashplayer");
			
			if(service.url.indexOf('?_method=GET') <= 0) {
				if(service.url.indexOf('?') > 0) { // Has url param var başına eklenecek
					var pairs:Array = service.url.split('?');
					if(pairs.length == 2) {
						service.url = pairs[0] + "?_method=GET" + "&" + pairs[1];
					} else {
						service.url = pairs[0] + "?_method=GET";
					}
				} else { // boş sona eklenecek
					service.url += "?_method=GET";
				}
			}
			
			service.send();
		}
		
		public static function authAndCall(service:HTTPServiceWrapper, operationName:String, parameter:String = null):mx.rpc.AsyncToken {
			
			var encoder:Base64Encoder = new Base64Encoder();
			encoder.insertNewLines = false;
			//encoder.encode("pinara:pinara");
			var tmpUserInfo:User = ModelLocator.getInstance().currentUser;
			encoder.encode(tmpUserInfo.username + ":" + tmpUserInfo.password);
			
			service.operations[operationName].headers["Authorization"] = "Basic " + encoder.toString();
			
			if(service.operations[operationName].method == "GET") {
				service.operations[operationName].method = "POST";
				service.operations[operationName].contentType = "application/xml";
				if(service.operations[operationName].url.indexOf('?_method=GET') <= 0) {
					service.operations[operationName].url += "?_method=GET";
				}
			}
			
			if(parameter == null) {
				return service.operations[operationName].send();
			}
			
			return service.operations[operationName].send(parameter);
		}
		
		public static function service_faultHandler(event:FaultEvent):void {
			// Alert.show("xmlService_faultHandler : " + event.toString());
			//outputText.text += "\nxmlService_faultHandler " + event;
			// Alert.show(event.fault.faultString);
			if(event.statusCode == 200) {
			} else if (event.statusCode == 400) {
				Alert.show("Kullacını adı ya da şifre hatalı !");
			/*} else if (event.statusCode == 499) {
				Alert.show("Server state is not available, please relogin !");
				FlexGlobals.topLevelApplication.dispatchEvent(new ResourceEvent(ResourceEvent.UPDATE_TREE, null));
				FlexGlobals.topLevelApplication.dispatchEvent(new ResourceEvent(ResourceEvent.LOGOUT));*/
			} else {
				trace("Unexpected Event : " + event.toString());
				// Alert.show("Unexpected Event : " + event.toString());
				// Alert.show("Server state is not available, please relogin !");
				// FlexGlobals.topLevelApplication.dispatchEvent(new ResourceEvent(ResourceEvent.LOGOUT));
			}
		}
		
		public static function service_resultHandler(refObject:DisplayObject, event:ResultEvent):ResultEvent {
			
	/*		var returnXml:XML;
			var returnTxt:String = null;
			
			try {
				returnXml = XML(event.message.body);
				returnTxt = "" + returnXml;
				if(startsWith(returnTxt, "NOK : ")) {
					WindowUtils.showDummyWindow(refObject, returnTxt.substr(6));
				} 
				
			} catch(err:Error) {
				Alert.show("Result : " + err.message);
				returnTxt = String(event.result)
			}
			
			// Alert.show("Result : " + event.message.body);
			// trace(returnXml);
			
			 */
			
			var returnXml:XML;
			var returnTxt:String = null;
			
			// Alert.show("Result : " + event.message.body);
			
			try {
				// returnXml = XML(event.result);
				returnXml = XML(event.message.body);
				// Alert.show("Result : " + returnXml.toString());
				if(returnXml.result == "NOK") {
					WindowUtils.showDummyWindow(refObject, returnXml.desc.toString());
				} else {
					PopUpManager.removePopUp(refObject as IFlexDisplayObject);
				}
			} catch(err:Error) {
				Alert.show("Result : " + err.message);
				returnTxt = String(event.result)
			}
			
			// Alert.show("Result : " + event.message.body);
			// trace(returnXml);
			
			return event;
		}
		
		public static function service_customHandler(refObject:DisplayObject, event:ResultEvent, showWin:Boolean = false):Boolean {
			var returnXml:XML;
			var returnTxt:String = null;
			
			try {
				returnXml = XML(event.message.body);
				if(returnXml.result == "NOK") {
					if(showWin) {
						WindowUtils.showDummyWindow(refObject, returnXml.desc.toString());
					}
					return false;
				} else {
					//PopUpManager.removePopUp(this);
				}
			} catch(err:Error) {
				Alert.show("Result : " + err.message);
				returnTxt = String(event.result)
					
				return false;
			}	
			
			return true;
		}
		
		public static function startsWith(haystack:String, needle:String):Boolean {
			return haystack.indexOf(needle) == 0;
		}
		
	}
}