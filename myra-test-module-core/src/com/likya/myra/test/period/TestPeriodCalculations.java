package com.likya.myra.test.period;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlException;
import org.junit.Ignore;
import org.junit.Test;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.jef.utils.timeschedules.PeriodCalculations;
import com.likya.myra.test.helpers.SimplePropsGenerator;
import com.likya.xsd.myra.model.joblist.AbstractJobType;

public class TestPeriodCalculations {

	@Test
	public void addPeriod() {

		AbstractJobType abstractJobType;
		try {
			abstractJobType = AbstractJobType.Factory.parse(SimplePropsGenerator.generateAbstractJobType(false).xmlText());
			ArrayList<String> errorMessages = new ArrayList<String>();

			abstractJobType.getManagement().getPeriodInfo().setRelativeStart(false);
			
			Calendar forwarded = PeriodCalculations.forward(abstractJobType, errorMessages);
			System.out.println(abstractJobType.getManagement().getTimeManagement().getJsScheduledTime().getStartTime());
			System.out.println(DateUtils.getDate(forwarded));
			
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);

		GDate base = new GDate(cal);
		GDate d = base.add(gDuration);

		long durationInMillis = d.getDate().getTime();

		return durationInMillis;
	}

}
