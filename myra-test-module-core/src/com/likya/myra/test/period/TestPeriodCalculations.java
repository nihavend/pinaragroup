package com.likya.myra.test.period;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.junit.Ignore;
import org.junit.Test;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.jef.model.Forwarder;
import com.likya.myra.jef.utils.timeschedules.PeriodCalculations;
import com.likya.myra.test.helpers.PeriodTesterDataGenerator;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class TestPeriodCalculations {

	@Test
	public void addPeriod() {

		AbstractJobType abstractJobType;
		try {
			abstractJobType = PeriodTesterDataGenerator.generate(); 
			ArrayList<String> errorMessages = new ArrayList<String>();

			Forwarder forwarder = PeriodCalculations.forward(abstractJobType, errorMessages);
			comperator(abstractJobType, forwarder);
			
			abstractJobType = PeriodTesterDataGenerator.generateMaxCountExceeded();
			
			forwarder = PeriodCalculations.forward(abstractJobType, errorMessages);
			comperator(abstractJobType, forwarder);

			abstractJobType = PeriodTesterDataGenerator.generateOutOfTimeFrame();
			forwarder = PeriodCalculations.forward(abstractJobType, errorMessages);
			comperator(abstractJobType, forwarder);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	public void comperator(AbstractJobType abstractJobType, Forwarder forwarder) {
		if(forwarder.equals(Forwarder.MAX_COUNT_EXCEEDED)) {
			// System.out.println("Due to a reason that maxCount reached, process changed to disabled !");
			System.out.println(forwarder.getObject());
			System.out.println(abstractJobType.getStateInfos().toString());
		} else if (forwarder.equals(Forwarder.CALENDAR_CALCULATED)) {
			System.out.println(abstractJobType.getManagement().getTimeManagement().getJsScheduledTime().getStartTime());
			System.out.println(DateUtils.getDate((Calendar) forwarder.getObject()));
		} else if (forwarder.equals(Forwarder.CALENDAR_NOT_CALCULATED)) {
			System.out.println(forwarder.getObject());
		} else {
			System.out.println("Unexpected forwarder value, should be BUG !");
		}
	}
		
	
	@Ignore
	public void duration() {
		
		for(int i = 0; i < 1000; i++) {
			long millisDuration = getDurationInMilliSecs();
			System.out.println("Millis : " + millisDuration);
		}
	}
	
	protected static long getDurationInMilliSecs() {

		GDuration gDuration = new GDuration("PT5M");

		Calendar cal = DateUtils.getCalendarInstance();
		cal.setTimeInMillis(0);

		GDate base = new GDate(cal);
		GDate d = base.add(gDuration);

		long durationInMillis = d.getDate().getTime();

		return durationInMillis;
	}

}
