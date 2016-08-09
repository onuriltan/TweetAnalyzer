package ozu.tweetanalyzer;


import model.DatabaseModel;
import twitter4j.Status;
import twitter4j.User;

public class SpamDetector {




	public SpamDetector(){}


	@SuppressWarnings("deprecation")
	public Boolean isNotSpam(DatabaseModel database,Status tweet, CurrentTime time){

		User user = tweet.getUser();
		int userAccountCreationYear = user.getCreatedAt().getYear() + 1900;// IT'S BECAUSE .GETYEAR() METHOD RETURNS CREATION DATE MINUS 1900, BECAUSE COMPUTERS SYSTEM TIME BEGINS FROM 1 JANUARY 1900, GOOGLE IT AS SYSTEM TIME FOR MORE INFO
		int userAccountCreationMonth = user.getCreatedAt().getMonth()+1;
		int currentYear = time.getYear();
		int currentMonth = time.getMonth();
		int yearDifference = currentYear-userAccountCreationYear;
		int monthDifference = currentMonth - userAccountCreationMonth;
		Boolean isUserAccountOlderThanOneMonth;
		int friendsCount = user.getFriendsCount();
		int followersCount = user.getFollowersCount();
		Boolean isDefaultPP=user.isDefaultProfileImage();
		isUserAccountOlderThanOneMonth = isOlderThanAMonth(yearDifference, monthDifference);


		if(isDefaultPP ){//isDefaultProfile returns true if user changed theme or background(picture)

			return true;
		}
		if(isUserAccountOlderThanOneMonth){

			return true;

		}
		if(friendsCount>10){
			return true;
		}
		if(followersCount >10){
			return true;
		}
		if(!isDefaultPP ){//isDefaultProfile returns true if user changed theme or background(picture)

			database.setEliminationReason("Tweet eliminated, user still uses default profile picture.");
			return false;		
		}


		if(friendsCount < 10){
			database.setEliminationReason("Tweet eliminated, user has less than 10 friends.");
			return false;
		}
		if(followersCount < 10){
			database.setEliminationReason("Tweet eliminated, user has less than 10 followers.");
			return false;
		}
		if(!isUserAccountOlderThanOneMonth)
		{
			database.setEliminationReason("Yeardifference: "+yearDifference+"  MonthDiffrence"+monthDifference +"user created at:"+userAccountCreationMonth+"."+userAccountCreationYear+"current: "+currentMonth+"."+currentYear);
			return false;

		}else{
			return true;
		}

	}


	private Boolean isOlderThanAMonth(int yearDifference, int monthDifference) {
		Boolean isUserAccountOlderThanOneMonth;
		if(yearDifference > 0)
		{
			isUserAccountOlderThanOneMonth = true;
		}
		else if(yearDifference >= 0 && monthDifference >= 1 )
		{
			isUserAccountOlderThanOneMonth = true;
		}
		else
		{
			isUserAccountOlderThanOneMonth = false;
		}
		return isUserAccountOlderThanOneMonth;
	}










}
