package ozu.tweetanalyzer;


import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;

public class SpamDetector {


	public SpamDetector(){}


	public Boolean isNotSpam(Status tweet, CurrentTime time){

		User user = tweet.getUser();
		int userAccountCreationYear = user.getCreatedAt().getYear();
		int userAccountCreationMonth = user.getCreatedAt().getMonth();
		int currentYear = time.getYear();
		int currentMonth = time.getMonth();
		int yearDifference = currentYear-userAccountCreationYear;
		int monthDifference = currentMonth - userAccountCreationMonth;
		Boolean isUserAccountOlderThanOneMonth;
		
		if(yearDifference>0)
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

			System.out.println(user.getName()+" = "+ tweet.getText()+" tarih ="+user.getCreatedAt()
					);
			for (HashtagEntity hashtag : tweet.getHashtagEntities()){
				System.out.print("#"+hashtag.getText()+ " ");

			}
			System.out.println();
			return true;
		}
		else
		{
			//System.out.println("spam =(userName="+user.getName()+", friends= "+user.getFriendsCount()+ ", followers="+user.getFollowersCount()+", isDefault = "+user.isDefaultProfile());

			return false;
		}


	}








}
