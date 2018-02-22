package ozu.tweetanalyzer;


import java.util.List;


import controller.ChartController;
import controller.MapController;
import controller.UrlController;
import model.DatabaseModel;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Search {
	private SearchPanel searchPanel;

	
	
	
	public Search(SearchPanel searchPanel){
		this.searchPanel = searchPanel;

	}


	public void searchRecentlyRelatedTweets(SpamDetector spamDetector,CurrentTime currentTime,DatabaseModel database,EntityRecognition entityRecognition,MapController mapController,
			ChartController locationController,ChartController organizationController,ChartController personController,ChartController languageController,ChartController hashtagController,UrlController urlController,
			ChartController allWordsController){


		ConfigurationBuilder cb = new ConfigurationBuilder();// WE CREATE NEW CB AGAIN BECAUSE TWITTER SEATCH API AND STREAM API CANNOT USE SAME CB
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("gYBOmrWOnlS69DScInBWrTGFr")
		.setOAuthConsumerSecret("wv8LHjQnCUjR28ALBVinPFRIiufvCQoh82qvmCo2ufkioZVFkU")
		.setOAuthAccessToken("964455826084777984-OxlNnBZYownQAwRAa57c61UApqsYUJ3")
		.setOAuthAccessTokenSecret("frUPdfMpefBgWrwGOFn292o4emhUdYg31gpTy9PijLldW");
		cb.setJSONStoreEnabled(true);

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		try {
			Query query = new Query(database.getSearchQuery());
			QueryResult result;
			query.setCount(50);
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					if(spamDetector.isNotSpam(database,tweet,currentTime) && tweet.isRetweet() == false){// if tweet is not spam according to our parameters and not a retweet
						entityRecognition.entityRecognition(0,tweet,locationController,organizationController,personController,languageController,hashtagController,urlController, allWordsController); // apply entity recognition on tweet text
						//this command takes tweets and analyzes them and updates charts according to analyzation
						database.setTweetCount(database.getTweetCount()+1);
						mapController.updateMap(tweet);
						searchPanel.getTweetCountlabel().setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
					
						String notSpamText = tweet.getUser().getScreenName()+" : "	+tweet.getText();
						database.getNotSpamModel().addElement(notSpamText);
						
						
						searchPanel.revalidate();
						  

					}else if ( tweet.isRetweet() == false){
						String spamText = tweet.getUser().getScreenName()+" : "+tweet.getText();
						database.getSpamModel().addElement(spamText);
						searchPanel.getSpamPanel().revalidate();
						searchPanel.revalidate();

					}




				}
			} while ((query = result.nextQuery()) != null);
			//System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}











}
