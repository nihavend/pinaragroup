package com.likya.myra.test.variableclock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;

class MyClock extends Clock {

	private long amountToAdd;
	private TemporalUnit forwardUnit = null;
	
	public MyClock(long amountToAdd, TemporalUnit forwardUnit) {
		super();
		this.amountToAdd = amountToAdd;
		this.forwardUnit = forwardUnit;
	}

	@Override
	public ZoneId getZone() {
		// TODO Auto-generated method stub
		return Clock.systemUTC().getZone();
	}

	@Override
	public Instant instant() {
		// TODO Auto-generated method stub
		return Clock.systemUTC().instant().plus(amountToAdd, forwardUnit);
	}

	@Override
	public Clock withZone(ZoneId arg0) {
		// TODO Auto-generated method stub
		return Clock.systemUTC().withZone(arg0);
	}

}