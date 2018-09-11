package com.likya.pinara.containers
{
	import mx.collections.ArrayList;
	import mx.controls.Alert;
	
	public class ImageContainer {
		
		// Ağaç için statu ikonları
		
		[@Embed(source='/images/clock1s.png')]
		public static const clock1s:Class;
		
		[@Embed(source='/images/kilits.png')]
		public static const kilits:Class;
		
		[@Embed(source='/images/kosu2s.png')]
		public static const kosu2s:Class;
		
		[@Embed(source='/images/ok2s.png')]
		public static const ok2s:Class;
		
		[@Embed(source='/images/errors.png')]
		public static const errors:Class;
		
		[@Embed(source='/images/timeouts.png')]
		public static const timeouts:Class;
		
		[@Embed(source='/images/skips.png')]
		public static const skips:Class;
		
		[@Embed(source='/images/stops.png')]
		public static const stops:Class;
		
		[@Embed(source='/images/pauses.png')]
		public static const pauses:Class;
		
		[@Embed(source='/images/disableds.png')]
		public static const disableds:Class;
		
		[@Embed(source='/images/warnings.png')]
		public static const warnings:Class;
		
		// Liste için statu ikonları
		
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
		
		[@Embed(source='/images/warning.jpg')]
		public static const warning:Class;
		
		// Liste için komut ikonları
		
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
		
		
		// Log için ikonlar
		
		[@Embed(source='/images/logfile_s.png')]
		public static const logFileResides:Class;
		
		[@Embed(source='/images/noLogfile_s.png')]
		public static const logFileMissing:Class;
		
		private static var treeIconList:ArrayList = new ArrayList();
		private static var statuImageList:ArrayList = new ArrayList();
		private static var manageImageList:ArrayList = new ArrayList();
		private static var logImageList:ArrayList = new ArrayList();

		// 0-9 Tree Statu ikonları
		treeIconList.addItem(clock1s)
		treeIconList.addItem(kilits)
		treeIconList.addItem(kosu2s)
		treeIconList.addItem(ok2s)
		treeIconList.addItem(errors)
		treeIconList.addItem(timeouts)
		treeIconList.addItem(skips)
		treeIconList.addItem(stops)
		treeIconList.addItem(pauses)
		treeIconList.addItem(disableds)
		treeIconList.addItem(warnings)
		
		// 0-9 Statu ikonları
		//
		// FILTER_READY:int = 0;
		// FILTER_WAITING:int = 1;
		// FILTER_WORKING:int = 2;
		// FILTER_SUCCESSFUL:int = 3;
		// FILTER_FAILED:int = 4;
		// FILTER_TIMEOUT:int = 5;
		// FILTER_SKIPPED:int = 6;
		// FILTER_STOPPED:int = 7;
		// FILTER_PAUSED:int = 8;
		// FILTER_DISABLED:int = 9;
		statuImageList.addItem(clock1)
		statuImageList.addItem(kilit)
		statuImageList.addItem(kosu2)
		statuImageList.addItem(ok2)
		statuImageList.addItem(error)
		statuImageList.addItem(timeout)
		statuImageList.addItem(skip)
		statuImageList.addItem(stop)
		statuImageList.addItem(pause)
		statuImageList.addItem(disabled)
		statuImageList.addItem(warning)

		// 10-18 Komut ikonları
		manageImageList.addItem(disableJob)
		manageImageList.addItem(startJob)
		manageImageList.addItem(pauseJob)
		manageImageList.addItem(enableJob)
		manageImageList.addItem(retryJob)
		manageImageList.addItem(setSuccessJob)
		manageImageList.addItem(skipJob)
		manageImageList.addItem(stopJob)
		manageImageList.addItem(resumeJob)
			
		// Log ikonları
		logImageList.addItem(logFileResides)
		logImageList.addItem(logFileMissing)

		public function ImageContainer() {}
		
		public static function getLogImage(logCode:Number):Class {
			return logImageList.getItemAt(logCode) as Class;
		}
		
		public static function getStatuImage(statuCode:Number):Class {
			// Alert.show("size : " + imageList.length);
			// Alert.show("image : " + imageList.getItemAt(statuCode));			
			return statuImageList.getItemAt(statuCode) as Class;
		}
		
		public static function getTreeStatuImage(statuCode:Number):Class {
			// Alert.show("size : " + imageList.length);
			// Alert.show("image : " + imageList.getItemAt(statuCode));			
			return treeIconList.getItemAt(statuCode) as Class;
		}

		public static function getManageImage(statuCode:Number):Class {
			
			// Alert.show("data : " + data);
			// Alert.show("size : " + imageList.length);
			// Alert.show("image : " + imageList.getItemAt(i));		
			/*
			<visualParams>
				<statu>1</statu>
				<commandabilityParams>
					<isPausable>true</isPausable>
					<isResumable>false</isResumable>
					<isRetryable>false</isRetryable>
					<isSkipable>false</isSkipable>
					<isStartable>false</isStartable>
					<isStopable>false</isStopable>
					<isSuccessable>false</isSuccessable>
				</commandabilityParams>
			</visualParams>
			*/
			return manageImageList.getItemAt(statuCode) as Class;
		}
		
		public static function resolveStatus(data:Object):Class {
			
			var statu:int = data.visualParams.statu;
			
			// Alert.show("Vparams : " + data.visualParams);
			// Alert.show("statu:" + statu + " image:" + imageList.getItemAt(statu));
			
			return statuImageList.getItemAt(statu) as Class;
		}
		
		/*
		public static function resolveStatus(data:Object):Class {
			
			var statu:int;
			
			// Alert.show(XML(data.stateInfos.LiveStateInfos).children()[0].StateName);
			
			XML(data.stateInfos.LiveStateInfos).children()[0].StateName
			
			var liveStateInfo:XML = XML(data.stateInfos.LiveStateInfos).children()[0];
			
			if(liveStateInfo.StateName == "PENDING" && liveStateInfo.SubstateName == "IDLED" && liveStateInfo.StatusName == "BYTIME") {
				statu = 0;
			} else if(liveStateInfo.StateName == "PENDING" && liveStateInfo.SubstateName == "IDLED" && liveStateInfo.StatusName == "WAITING") {
				statu = 1;
			} else if(liveStateInfo.StateName == "RUNNING" && liveStateInfo.SubstateName == "ON_RESOURCE" && liveStateInfo.StatusName == "TIME_IN") {
				statu = 2;
			} else if(liveStateInfo.StateName == "FINISHED" && liveStateInfo.SubstateName == "COMPLETED" && liveStateInfo.StatusName == "SUCCESS") {
				statu = 3;
			} else if(liveStateInfo.StateName == "FINISHED" && liveStateInfo.SubstateName == "COMPLETED" && liveStateInfo.StatusName == "FAILED") {
				statu = 4;
			} else if(liveStateInfo.StateName == "RUNNING" && liveStateInfo.SubstateName == "ON_RESOURCE" && liveStateInfo.StatusName == "TIME_OUT") {
				statu = 5;
			} else if(liveStateInfo.StateName == "FINISHED" && liveStateInfo.SubstateName == "SKIPPED") {
				statu = 6;
			} else if(liveStateInfo.StateName == "FINISHED" && liveStateInfo.SubstateName == "STOPPED") {
				statu = 7;
			} else if(liveStateInfo.StateName == "PENDING" && liveStateInfo.SubstateName == "PAUSED") {
				statu = 8;
			} else if(liveStateInfo.StateName == "PENDING" && liveStateInfo.SubstateName == "DEACTIVATED") {
				statu = 9;
			}
			
			return imageList.getItemAt(statu) as Class;
		}
		*/
	
	}
}

