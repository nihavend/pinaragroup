package com.likya.myra.test.helpers;

import java.math.BigInteger;
import java.util.Calendar;

import org.apache.xmlbeans.XmlException;

import com.likya.xsd.myra.model.joblist.AbstractJobType;
import com.likya.xsd.myra.model.wlagen.JsExecutionTimeFrameDocument.JsExecutionTimeFrame;

public class PeriodTesterDataGenerator {
	
	public static AbstractJobType generate() {

		AbstractJobType abstractJobType = null;

		try {
			abstractJobType = AbstractJobType.Factory.parse(SimplePropsGenerator.generateAbstractJobType(false).xmlText());
			abstractJobType.getManagement().getPeriodInfo().setRelativeStart(false);

		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return abstractJobType;
	}

	public static AbstractJobType generateMaxCountExceeded() {

		AbstractJobType abstractJobType = null;

		try {
			abstractJobType = AbstractJobType.Factory.parse(SimplePropsGenerator.generateAbstractJobType(false).xmlText());
			abstractJobType.getManagement().getPeriodInfo().setRelativeStart(false);

			abstractJobType.getManagement().getPeriodInfo().setCounter(BigInteger.ONE);
			abstractJobType.getManagement().getPeriodInfo().setMaxCount(BigInteger.ONE);

		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return abstractJobType;
	}

	public static AbstractJobType generateOutOfTimeFrame() {

		AbstractJobType abstractJobType = null;

		try {
			
			abstractJobType = AbstractJobType.Factory.parse(SimplePropsGenerator.generateAbstractJobType(false).xmlText());
			abstractJobType.getManagement().getPeriodInfo().setRelativeStart(false);
			
			abstractJobType.getManagement().getTimeManagement().setJsExecutionTimeFrame(JsExecutionTimeFrame.Factory.newInstance());

			Calendar calendar = Calendar.getInstance(); // this would default to now
			calendar.add(Calendar.DAY_OF_MONTH, 1);

			abstractJobType.getManagement().getTimeManagement().getJsExecutionTimeFrame().setStartTime(calendar);

		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return abstractJobType;
	}
}
