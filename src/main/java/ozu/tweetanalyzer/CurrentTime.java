package ozu.tweetanalyzer;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {




	private String currentDate;
	private String currentTime;
	private int year;
	private int month ;
	private int day ;

	public CurrentTime(){

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		// your date
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		currentDate = dateFormat.format(date).toString();
		currentTime = dateFormat.format(cal.getTime()).toString();

	}


	public String getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}


	public String getCurrentTime() {
		return currentTime;
	}


	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}













}