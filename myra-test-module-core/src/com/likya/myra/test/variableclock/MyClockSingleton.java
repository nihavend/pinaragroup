package com.likya.myra.test.variableclock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;

class MyClockSingleton extends Clock {
	
	private static Clock myClockSingleton;

	private final Instant WHEN_STARTED = Instant.now();
	private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
	
	private static long amountToAdd;
	private static TemporalUnit temporalUnit;
	
	public static Clock getInstance() {
		if(myClockSingleton == null) {
			myClockSingleton = new MyClockSingleton();
		}
		amountToAdd = 0;
		return myClockSingleton;
	}
	
	public static synchronized Clock getInstance(long a, TemporalUnit t) {
		if(myClockSingleton == null) {
			myClockSingleton = new MyClockSingleton();
		}
		
		amountToAdd = a;
		temporalUnit = t;
		
		return myClockSingleton;
	}
	
	public MyClockSingleton() {
		super();
	}

	@Override
	public ZoneId getZone() {
		return DEFAULT_TZONE;
	}

	@Override
	public Instant instant() {
		// TODO Auto-generated method stub
		return (temporalUnit == null || amountToAdd == 0) ? Clock.systemUTC().instant() : Clock.systemUTC().instant().plus(amountToAdd, temporalUnit);
	}

	@Override
	public Clock withZone(ZoneId arg0) {
		return Clock.fixed(WHEN_STARTED, arg0);
	}

}