package com.likya.myra.test.timeshift.plugin;

import com.likya.commons.utils.DateUtils;

public final class TimeSourceOneDayAhead implements TimeSource {

	/** One day in advance of the actual time. */
	@Override
	public long currentTimeMillis() {
		return DateUtils.getCurrentTimeMilliseconds() + ONE_DAY;
	}

	private static final long ONE_DAY = 24 * 60 * 60 * 1000;

}
