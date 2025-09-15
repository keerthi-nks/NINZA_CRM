package com.ninza.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random rd=new Random();
		int randomNumber = rd.nextInt(1000);
		return randomNumber;
	}
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String currentdate = sim.format(date);
		return currentdate;
	}
    public String getRequireDate(int exDate) {
    	//create the object of date to get the todays date 
    	Date date = new Date();
    	//format date
    	SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
    	sim.format(date);
    	Calendar cal = sim.getCalendar();
    	cal.add(Calendar.DAY_OF_MONTH, exDate);
    	String expecteddate = sim.format(cal.getTime());
    	return expecteddate;
    }

}
