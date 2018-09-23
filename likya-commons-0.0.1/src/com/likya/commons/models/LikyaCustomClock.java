package com.likya.commons.models;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;

public class LikyaCustomClock extends Clock {

	private static Clock likyaCustomClock;
	
	private final Instant WHEN_STARTED = Instant.now();
	private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
	
	private static long amountToAdd;
	private static TemporalUnit temporalUnit;
	
	public static Clock getInstance() {
		if(likyaCustomClock == null) {
			likyaCustomClock = new LikyaCustomClock();
		}
		amountToAdd = 0;
		temporalUnit = null;
		
		return likyaCustomClock;
	}
	
	public static synchronized Clock getInstance(long a, TemporalUnit t) {
		if(likyaCustomClock == null) {
			likyaCustomClock = new LikyaCustomClock();
		}
		
		amountToAdd = a;
		temporalUnit = t;
		
		return likyaCustomClock;
	}
	
	private LikyaCustomClock() {
		super();
	}

	@Override
	public ZoneId getZone() {
		return DEFAULT_TZONE;
	}

	@Override
	public Instant instant() {
		if(amountToAdd == 0 || temporalUnit == null) {
			return Clock.systemUTC().instant();
		} else {
			return Clock.systemUTC().instant().plus(amountToAdd, temporalUnit);
		}
	}

	@Override
	public Clock withZone(ZoneId arg0) {
		return Clock.fixed(WHEN_STARTED, arg0);
	}

}
