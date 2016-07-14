package ozu.tweetanalyzer;

import twitter4j.Status;
import twitter4j.User;

public class SpamDetector {


	public SpamDetector(){}


	public Boolean isNotSpam(Status tweet){

		User user = tweet.getUser();

		if(user.isDefaultProfile() && user.getFriendsCount()>=50 && user.getFollowersCount()>=50)
		{
			//isDefaultProfile returns true if user changed theme or background(picture)

			System.out.println("NOT spam =(userName="+user.getName()+", friends= "+user.getFriendsCount()+ ", followers="+user.getFollowersCount()+", isDefault = "+user.isDefaultProfile());

			return true;
		}
		else
		{
			System.out.println("spam =(userName="+user.getName()+", friends= "+user.getFriendsCount()+ ", followers="+user.getFollowersCount()+", isDefault = "+user.isDefaultProfile());

			return false;
		}


	}








}
