package ozu.tweetanalyzer;

import java.util.ArrayList;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TrendTopic {
	private ArrayList<String> trendTopicList = new ArrayList<String>();
	private ConfigurationBuilder cb;

	public void getTrendsFromTwitter(){
		cb = defineConfBuilder();
		try {
			Twitter twitter = new TwitterFactory(cb.build()).getInstance();
			//int turkeyTrends = getTrendsFromTurkey(twitter);
			Trends trends = twitter.getPlaceTrends(1);//takes worldwide trends
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
	private ConfigurationBuilder defineConfBuilder(){
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("xjhjEo5dv9l9gkH6aOsYT9FEW")
		.setOAuthConsumerSecret("BCFiOEtwg49XtHzkhQ08CF5Nm4Nafx2ppHjg6gmA0aB862L7ps")
		.setOAuthAccessToken("4013320817-ShvBoFTZYGMm8RQMrcZEmsihk9yua2KpU3WEoPJ")
		.setOAuthAccessTokenSecret("Fmz8lmkNce9Fx5svO7XyFJikvyRp3Y0hssZQDDrjtxhP7");
		cb.setJSONStoreEnabled(true);
		return cb;
	}

}
