package ozu.tweetanalyzer;


import java.util.List;

import controller.ChartController;
import controller.MapController;
import model.DatabaseModel;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Search {


	public void searchRecentlyRelatedTweets(SpamDetector spamDetector,CurrentTime currentTime,DatabaseModel database,TwitterAuthorization authorization,EntityRecognition entityRecognition,MapController mapController,
			ChartController locationController,ChartController organizationController,ChartController personController,ChartController languageController,ChartController hashtagController,ChartController urlController){


		ConfigurationBuilder cb = new ConfigurationBuilder();// WE CREATE NEW CB AGAIN BECAUSE TWITTER SEATCH API AND STREAM API CANNOT USE SAME CB
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("xjhjEo5dv9l9gkH6aOsYT9FEW")
		.setOAuthConsumerSecret("BCFiOEtwg49XtHzkhQ08CF5Nm4Nafx2ppHjg6gmA0aB862L7ps")
		.setOAuthAccessToken("4013320817-ShvBoFTZYGMm8RQMrcZEmsihk9yua2KpU3WEoPJ")
		.setOAuthAccessTokenSecret("Fmz8lmkNce9Fx5svO7XyFJikvyRp3Y0hssZQDDrjtxhP7");
		cb.setJSONStoreEnabled(true);

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try {
			Query query = new Query(database.getSearchQuery());
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if(spamDetector.isNotSpam(tweet,currentTime) && tweet.isRetweet() == false){// if tweet is not spam according to our parameters and not a retweet
						entityRecognition.entityRecognition(tweet,locationController,organizationController,personController,languageController,hashtagController,urlController); // apply entity recognition on tweet text
						//this command takes tweets and analyzes them and updates charts according to analyzation
						mapController.updateMap(tweet);

					}	                }
			} while ((query = result.nextQuery()) != null);
			//System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

}
