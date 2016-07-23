package ozu.tweetanalyzer;


import twitter4j.Status;
import twitter4j.User;

public class SpamDetector {


	public SpamDetector(){}


	@SuppressWarnings("deprecation")
	public Boolean isNotSpam(Status tweet, CurrentTime time){

		User user = tweet.getUser();
		int userAccountCreationYear = user.getCreatedAt().getYear() + 1900;// IT'S BECAUSE .GETYEAR() METHOD RETURNS CREATION DATE MINUS 1900, BECAUSE COMPUTERS SYSTEM TIME BEGINS FROM 1 JANUARY 1900, GOOGLE IT AS SYSTEM TIME FOR MORE INFO
		int userAccountCreationMonth = user.getCreatedAt().getMonth();
		int currentYear = time.getYear();
		int currentMonth = time.getMonth();
		int yearDifference = currentYear-userAccountCreationYear;
		int monthDifference = currentMonth - userAccountCreationMonth;
		Boolean isUserAccountOlderThanOneMonth;
		int friendsCount = user.getFriendsCount();
		int followersCount = user.getFollowersCount();
		Boolean isDefaultPP=user.isDefaultProfileImage();
		
		if(isDefaultPP){
			return false;
		}
		
		if(friendsCount < 10){
			return false;
		}
		if(followersCount < 10){
			return false;
		}


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
		
		
		if(isUserAccountOlderThanOneMonth && user.isDefaultProfile() && user.getFriendsCount()>=50 && user.getFollowersCount()>=50)
		{
			//isDefaultProfile returns true if user changed theme or background(picture)

			return true;
		}
		else
		{
			return false;
		}


	}








}
