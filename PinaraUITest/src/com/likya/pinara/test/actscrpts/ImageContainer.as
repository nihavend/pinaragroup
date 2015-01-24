package com.likya.pinara.test.actscrpts
{
	import mx.collections.ArrayList;
	
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
		
		// Komut ikonları
		
		[@Embed(source='/images/disableJob.jpg')]
		public static const disableJob:Class;
		
		[@Embed(source='/images/startJob.jpg')]
		public static const startJob:Class;
		
		[@Embed(source='/images/pauseJob.jpg')]
		public static const pauseJob:Class;
		
		[@Embed(source='/images/enableJob.jpg')]
		public static const enableJob:Class;
		
		[@Embed(source='/images/retryJob.jpg')]
		public static const retryJob:Class;
		
		[@Embed(source='/images/setSuccessJob.jpg')]
		public static const setSuccessJob:Class;
		
		[@Embed(source='/images/skipJob.jpg')]
		public static const skipJob:Class;
		
		[@Embed(source='/images/stopJob.jpg')]
		public static const stopJob:Class;
		
		[@Embed(source='/images/resumeJob.jpg')]
		public static const resumeJob:Class;
		
		
		private static var imageList:ArrayList = new ArrayList();
		
		// 0-9 Statu ikonları
		imageList.addItem(clock1)
		imageList.addItem(kilit)
		imageList.addItem(kosu2)
		imageList.addItem(ok2)
		imageList.addItem(error)
		imageList.addItem(timeout)
		imageList.addItem(skip)
		imageList.addItem(stop)
		imageList.addItem(pause)
		imageList.addItem(disabled)

		// 10-18 Komut ikonları
		imageList.addItem(disableJob)
		imageList.addItem(startJob)
		imageList.addItem(pauseJob)
		imageList.addItem(enableJob)
		imageList.addItem(retryJob)
		imageList.addItem(setSuccessJob)
		imageList.addItem(skipJob)
		imageList.addItem(stopJob)
		imageList.addItem(resumeJob)

		public function ImageContainer() {}
		
		public static function getStatuImage(statuCode:Number):Class {
			// Alert.show("size : " + imageList.length);
			// Alert.show("image : " + imageList.getItemAt(i));			
			return imageList.getItemAt(statuCode) as Class;
		}

		public static function getManageImage(manageCode:Number):Class {
			// Alert.show("size : " + imageList.length);
			// Alert.show("image : " + imageList.getItemAt(i));			
			return imageList.getItemAt(manageCode) as Class;
		}

	}
}

