package com.likya.pinara.utils {
	
	import com.adobe.utils.DateUtil;
	
	import mx.formatters.DateFormatter;
	
	import spark.components.gridClasses.GridColumn;
	
	public class Transformer {
		
		public function Transformer() {
		}
		
		public static function getDate(dateTxt:String):String {
			// Alert.show("" + formatedDate(item.management.timeManagement.jsPlannedTime.startTime));
			
			var dateFormatter:DateFormatter = new DateFormatter();
			dateFormatter.formatString = 'JJ:NN:SS DD/MM/YYYY';
			
			//Alert.show(" : " + DateFormatter.parseDateString(item.management.timeManagement.jsPlannedTime.startTime));
			
			// Alert.show(dateFormatter.format(DateFormatter.parseDateString(item.management.timeManagement.jsPlannedTime.startTime)));
			
			// return item.management.timeManagement.jsPlannedTime.startTime;
			// var dateTimeFormatter:DateTimeFormatter = new DateTimeFormatter();
			// dateTimeFormatter.dateTimePattern = 'DD/MM/YYYY HH:mm:ss';
			// dateTimeFormatter.timeStyle = 'HH:mm:ss';
			if(dateTxt != null && dateTxt.length > 0) {
				var myDate:Date = DateUtil.parseW3CDTF(dateTxt);
				return dateFormatter.format(DateFormatter.parseDateString(myDate.toString()));
			}
			
			// trace(dateTxt);
			// trace(dateFormatter.format(DateFormatter.parseDateString(myDate.toString())));
			// trace(dateFormatter.error);
			return dateFormatter.format(DateFormatter.parseDateString(dateTxt))
		}
		
		public static function getRealizedDate(item:Object, column:GridColumn):String {
			return getDate(item.management.timeManagement.jsRealTime.startTime);
		}
		
		public static function getPlannedDate(item:Object, column:GridColumn):String {
			return getDate(item.management.timeManagement.jsPlannedTime.startTime);
		}
		
		public static function getStateChangedDate(item:Object, column:GridColumn):String {
			return getDate(item.@LSIDateTime);
		}
	}
}