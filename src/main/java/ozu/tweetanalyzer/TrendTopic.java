package ozu.tweetanalyzer;

import java.util.ArrayList;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TrendTopic {
	ArrayList<String> trendTopicList = new ArrayList<String>();
	
	public void getTrendsFromTwitter(){
		TwitterAuthorization authorize = new TwitterAuthorization();
		 try {
			             Twitter twitter = new TwitterFactory(authorize.getCb().build()).getInstance();
			             Trends trends = twitter.getPlaceTrends(1);
			          //   System.out.println("Showing current trends");
			          //  System.out.println("As of : " + trends.getAsOf());
			             for (Trend trend : trends.getTrends()) {
			               //  System.out.println(" " + trend.getName());
			                 trendTopicList.add(trend.getName());
			             }
			          //   System.out.println("done.");
			             
			         } catch (TwitterException e) {
			             e.printStackTrace();
			             System.out.println("Failed to get current trends: " + e.getMessage());
			            
			         }
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
