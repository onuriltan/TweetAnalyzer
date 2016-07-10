package ozu.tweetanalyzer;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {


	private DateFormat dateFormat;
	private Date date;
	private Calendar cal;



	public void startTime(){

		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//get current date time with Date()
		date = new Date();
		System.out.println(dateFormat.format(date));
		//get current date time with Calendar()
		cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));

	}



	public DateFormat getDateFormat() {
		return dateFormat;
	}



	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Calendar getCal() {
		return cal;
	}



	public void setCal(Calendar cal) {
		this.cal = cal;
	}






}