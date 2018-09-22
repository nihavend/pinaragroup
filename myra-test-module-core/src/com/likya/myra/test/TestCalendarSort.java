package com.likya.myra.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.likya.commons.utils.DateUtils;
import com.likya.myra.commons.utils.MyraDateUtils;

public class TestCalendarSort {

	public static void main(String[] args) {
		
		ArrayList<Calendar> floatingSchedules = new ArrayList<Calendar>();
		
		Calendar cal = DateUtils.getCalendarInstance();
		
		cal.add(Calendar.DAY_OF_MONTH, -1);
		System.out.println("1 : " + MyraDateUtils.getDate(cal));
		floatingSchedules.add(cal);
		
		cal = DateUtils.getCalendarInstance();
		cal.add(Calendar.DAY_OF_MONTH, 3);
		System.out.println("2 : " + MyraDateUtils.getDate(cal));
		floatingSchedules.add(cal);
		
		cal = DateUtils.getCalendarInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);	
		System.out.println("3 : " + MyraDateUtils.getDate(cal));
		floatingSchedules.add(cal);

		cal = DateUtils.getCalendarInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2);
		System.out.println("4 : " + MyraDateUtils.getDate(cal));
		floatingSchedules.add(cal);

		Calendar[] sortedCals = floatingSchedules.toArray(new Calendar[0]);
		
		Arrays.sort(sortedCals);
		
		System.out.println();
		int idx = 1;
		for(Calendar c : sortedCals) {
			System.out.println(idx ++ + " : " + MyraDateUtils.getDate(c));
		}
		
		System.out.println();
		System.err.println("Minimum of options : " + MyraDateUtils.getDate(sortedCals[0]));
	}

}
