package ozu.tweetanalyzer;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {




	private String currentDate;
	private String currentTime;


	public CurrentTime(){

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal.getTime()));

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













}