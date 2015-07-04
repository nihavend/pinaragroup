package com.likya.pinara.utils
{
	import com.likya.pinara.comps.DummyForm;
	
	import flash.display.DisplayObject;
	
	import mx.managers.PopUpManager;

	public class WindowUtils
	{
		public static function showDummyWindow(refObject:DisplayObject, xmlText:String):void {
			var dummyWindow:DummyForm = PopUpManager.createPopUp(refObject, DummyForm, true) as DummyForm;
			dummyWindow.title="ERROR : Content of the error message..."
			dummyWindow.htmlTextAsHTML = xmlText;
			PopUpManager.centerPopUp(dummyWindow);
			dummyWindow.setFocus();
		}
	}
}