package com.likya.pinara.test.actscrpts
{
	import flash.events.MouseEvent;
	import flash.text.TextFormat;
	
	import spark.components.TextArea;
	
	public class HighlightTextArea extends TextArea
	{
		public function HighlightTextArea() {
			super();
		}
		
		override protected function createChildren ():void {
			super.createChildren();
			this.textDisplay.addEventListener(MouseEvent.CLICK, textField_clickHandler);
		}
		
		private function textField_clickHandler (event:MouseEvent):void
		{
			var lineIndex:int = textField.getLineIndexAtPoint(event.localX, event.localY);
			if (lineIndex == -1)
				return;
			var lineOffset:int = textField.getLineOffset(lineIndex);
			var lineLength:int = textField.getLineLength(lineIndex);
			if (lineLength > 0)
				textField.setTextFormat(new TextFormat(null, null, 0xFF0000), lineOffset, lineOffset+lineLength);
		}
	}
}
}