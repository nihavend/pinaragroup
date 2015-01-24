package com.likya.pinara.test.actscrpts {
	
	public class Embedded {
		
		[@Embed(source='/images/clock1.jpg')]
		public static const btnSrcOrangeBoxIdle:Class;
		
		[@Embed(source='/images/kilit.jpg')]
		public static const btnSrcOrangeBoxPressed:Class;
		
		[@Embed(source='/images/kosu2.jpg')]
		public static const btnHL1Idle:Class;
		
		[@Embed(source='/images/ok2.jpg')]
		public static const btnHL1Pressed:Class;
		
		public function Embedded(){}
	}
}