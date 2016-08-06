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
	private Calendar cal;
	private int hourOfDay; // 24 hour clock
	private int minute;
	private int second;
	private SimpleDateFormat dateFormat1;
	private Date date;
	public CurrentTime(){

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		date = new Date();
		// your date
		cal = Calendar.getInstance();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		currentDate = dateFormat.format(date).toString();
		currentTime = dateFormat.format(cal.getTime()).toString();
		hourOfDay  = cal.get(Calendar.HOUR_OF_DAY); // 24 hour clock,
		minute     = cal.get(Calendar.MINUTE);
		second     = cal.get(Calendar.SECOND);
		dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH;mm;ss");
		

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


	public Calendar getCal() {
		return cal;
	}


	public void setCal(Calendar cal) {
		this.cal = cal;
	}


	public int getHourOfDay() {
		return hourOfDay;
	}


	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}


	public int getMinute() {
		return minute;
	}


	public void setMinute(int minute) {
		this.minute = minute;
	}


	public int getSecond() {
		return second;
	}


	public void setSecond(int second) {
		this.second = second;
	}


	public SimpleDateFormat getDateFormat1() {
		return dateFormat1;
	}


	public void setDateFormat1(SimpleDateFormat dateFormat1) {
		this.dateFormat1 = dateFormat1;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}













}