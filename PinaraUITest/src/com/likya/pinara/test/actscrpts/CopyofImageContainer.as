package com.likya.pinara.test.actscrpts
{
	import mx.collections.ArrayList;
	import mx.controls.Alert;

	public class ImageContainer {
		
		[@Embed(source='/images/kosu2.jpg')]
		public static const btnSrcOrangeBoxIdle:Class;
		
		[@Embed(source='/images/clock1.jpg')]
		public static const clock1:Class;
		
		[@Embed(source='/images/kilit.jpg')]
		public static const kilit:Class;

		[@Embed(source='/images/kosu2.jpg')]
		public static const kosu2:Class;

		[@Embed(source='/images/ok2.jpg')]
		public static const ok2:Class;

		[@Embed(source='/images/error.jpg')]
		public static const error:Class;

		[@Embed(source='/images/timeout.jpg')]
		public static const timeout:Class;

		[@Embed(source='/images/skip.jpg')]
		public static const skip:Class;

		[@Embed(source='/images/stop.jpg')]
		public static const stop:Class;

		[@Embed(source='/images/pause.jpg')]
		public static const pause:Class;

		[@Embed(source='/images/disabled.jpg')]
		public static const disabled:Class;
		
		private static var imageList:ArrayList = new ArrayList();
		
		public function ImageContainer() {
/*			imageList.addItem(clock1)
			imageList.addItem(kilit.jpg)
			imageList.addItem(kosu2.jpg)
			imageList.addItem(ok2.jpg)
			imageList.addItem(error.jpg)
			imageList.addItem(timeout.jpg)
			imageList.addItem(skip.jpg)
			imageList.addItem(stop.jpg)
			imageList.addItem(pause.jpg)
			imageList.addItem(disabled.jpg)*/
		}
		
		public static function getImage(imageIdx:Number):Object {
			Alert.show(imageList.getItemAt(imageIdx) as String);
			return imageList.getItemAt(imageIdx);
		}
		
	}
}