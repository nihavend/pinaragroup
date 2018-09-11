package com.likya.pinara.modules
{
	import mx.modules.IModule;

	public interface IModuleInterface extends IModule {
		function setDataXml(xml:XML):void;
		function getDataXml():XML;
		function setEditMode(mode:Boolean):void;
		function validateForm():Boolean;
	}
}