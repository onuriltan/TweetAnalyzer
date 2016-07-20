package ozu.tweetanalyzer;

import controller.ChartController;
import controller.MapController;
import model.DatabaseModel;
import model.MapModel;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import view.MapView;

public class Stream {

	public void startStream(TwitterAuthorization authorize, final DatabaseModel database,
			final EntityRecognition recognition, final SpamDetector spamDetector, final CurrentTime currentTime,
			final ChartController locationController, final ChartController organizationController,
			final ChartController personController, final ChartController languageController,
			final ChartController hashtagController, final ChartController urlController) {



		MapModel mapModel = new MapModel();
		TweetLocationFinder getLocationFromAccountInfo = new TweetLocationFinder();
		MapController mapController = new MapController(mapModel, new MapView(mapModel,getLocationFromAccountInfo));
		mapController.populateMap();




		StatusListener listener = new StatusListener() {
			public void onStatus(Status tweet) {// data keep coming to onStatus method, 
				// the code that written under onStatus method will execute the code again and again when new tweet comes

				if(spamDetector.isNotSpam(tweet,currentTime) && tweet.isRetweet() == false){// if tweet is not spam according to our parameters and not a retweet
					recognition.entityRecognition(tweet,locationController,organizationController,personController,languageController,hashtagController,urlController); // apply entity recognition on tweet text
					//this command takes tweets and analyzes them and updates charts according to analyzation

					mapController.updateMap(tweet);


				}
			}
			public void onException(Exception arg0) {}
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
			public void onScrubGeo(long arg0, long arg1) {}
			public void onStallWarning(StallWarning arg0) {}
			public void onTrackLimitationNotice(int arg0) {}

		};
		TwitterStreamFactory tf = new TwitterStreamFactory(authorize.getCb().build());
		TwitterStream stream = tf.getInstance();
		stream.addListener(listener);

		FilterQuery filtre = new FilterQuery();
		String[] keywordsArray = { database.getSearchQuery() }; //filter based on choice of keywords
		filtre.track(keywordsArray);
		stream.filter(filtre);
	}


}
