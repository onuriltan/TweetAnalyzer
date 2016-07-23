package ozu.tweetanalyzer;

import java.util.ArrayList;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TrendTopic {
	private ArrayList<String> trendTopicList = new ArrayList<String>();

	public void getTrendsFromTwitter(){

		TwitterAuthorization authorize = new TwitterAuthorization();


		try {
			Twitter twitter = new TwitterFactory(authorize.getCb().build()).getInstance();
			int turkeyTrends = getTrendsFromTurkey(twitter);
			Trends trends = twitter.getPlaceTrends(turkeyTrends);

			for (Trend trend : trends.getTrends()) {
				
				trendTopicList.add(trend.getName());
			}

		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("Failed to get current trends: " + e.getMessage());

		}
	}




	public int getTrendsFromTurkey(Twitter twitter){
		int woeid = 1;
		try {
			ResponseList<Location> locations;
			locations = twitter.getAvailableTrends();
			System.out.println("Showing the locations that Twitter has trending topic information for Turkey.");
			for (Location location : locations) {
				if(location.getName().toUpperCase().equals("TURKEY")){
					System.out.println(location.getName() + " (woeid:" + location.getWoeid() + ")");
					woeid = location.getWoeid();

				}
			}
			System.out.println("done.");
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get trends: " + te.getMessage());
		}
		return woeid;
	}




	public ArrayList<String> getTrendList(){
		return trendTopicList;
	}

	public void printTrendList(){
		for (String trend : trendTopicList) {
			System.out.println(trend);              
		}
	}

}
