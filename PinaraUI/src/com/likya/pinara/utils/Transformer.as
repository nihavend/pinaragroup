package com.likya.pinara.utils {
	
	import com.adobe.utils.DateUtil;
	
	import mx.formatters.DateFormatter;
	import mx.utils.ObjectUtil;
	
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
		
		public static function getRecordedDT(item:Object, column:GridColumn):String {
			return getDate(item.management.timeManagement.jsRecordedTime.startTime);
		}
		
		public static function getActualDT(item:Object, column:GridColumn):String {
			return getDate(item.management.timeManagement.jsActualTime.startTime);
		}
		
		public static function getStateChangedDate(item:Object, column:GridColumn):String {
			return getDate(item.@LSIDateTime);
		}
		
		public static function compareSortForDate(itemA:XML, itemB:XML, column:GridColumn):int {
			
			var valueA:String = column.labelFunction(itemA, column);
			var valueB:String = column.labelFunction(itemB, column);
			
			var objInt1:Number = Date.parse(valueA);
			var objInt2:Number = Date.parse(valueB);
			
			if(isNaN(objInt1)) objInt1 = 0; 
			if(isNaN(objInt2)) objInt2 = 0; 
			
			var retValue:Number = 0;
			
			if(objInt1 > objInt2) { 
				retValue = 1;
			} else if(objInt1 < objInt2) {
				retValue = -1;
			} 
			
			return retValue;
		}
		
		public static function compareSortForDuration(itemA:XML, itemB:XML, column:GridColumn):int {
			
			var valueX:String = column.labelFunction(itemA, column);
			var valueY:String = column.labelFunction(itemB, column);
			
			var pairsX:Array = valueX.split(".");
			var pairsY:Array = valueY.split(".");
			
			var valueA:String = pairsX[0];
			var valueB:String = pairsY[0];

			//var valueA:String = column.labelFunction(itemA, column);
			//var valueB:String = column.labelFunction(itemB, column);
			
			var pairsA:Array = valueA.split(":");
			var pairsB:Array = valueB.split(":");
			
			var objInt1:Number = (parseInt(pairsA[0]) * 3600 + parseInt(pairsA[1]) * 60 + parseInt(pairsA[2])) * 1000 + pairsX[1];  
			var objInt2:Number = (parseInt(pairsB[0]) * 3600 + parseInt(pairsB[1]) * 60 + parseInt(pairsB[2])) * 1000 + pairsY[1];  
			
			if(isNaN(objInt1)) objInt1 = 0; 
			if(isNaN(objInt2)) objInt2 = 0; 
			
			var retValue:Number = 0;
			
			if(objInt1 > objInt2) { 
				retValue = 1;
			} else if(objInt1 < objInt2) {
				retValue = -1;
			} 
			
			return retValue;
		}
		
		public static function compareSortString(itemA:XML, itemB:XML, column:GridColumn):int {
	
			var valueA:String = column.labelFunction(itemA, column);
			var valueB:String = column.labelFunction(itemB, column);
			
			return ObjectUtil.stringCompare(valueA, valueB);
		}

		// Define the sort compare function used by the first column.
		public static function compareIds(obj1:Object, obj2:Object, gc:GridColumn):int {
			// Make the sort case insensitive. The default is case sensitive.
			// collator.ignoreCase = true;
			// return collator.compare(obj1[gc.dataField], obj2[gc.dataField]);
			var objInt1:Number = parseInt(obj1[gc.dataField])
			var objInt2:Number = parseInt(obj2[gc.dataField])
			
			// If the return value is negative, string1 is less than string2.
			// If the return value is zero, string1 is equal to string2.
			// If the return value is positive, string1 is larger than string2.
			var retValue:Number = 0;
			
			if(objInt1 > objInt2) { 
				retValue = 1;
			} else if(objInt1 < objInt2) {
				retValue = -1;
			} 
			
			return retValue;
		}
			
	}
}