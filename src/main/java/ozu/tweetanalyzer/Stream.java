package ozu.tweetanalyzer;


import controller.ChartController;
import controller.MapController;
import model.DatabaseModel;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
	private TwitterStreamFactory tf;
	private TwitterStream stream;
	private FilterQuery filtre;
	private String[] keywordsArray;
	private ConfigurationBuilder cb;

	private SearchPanel searchPanel;

	public Stream(SearchPanel searchPanel){
		this.searchPanel = searchPanel;
	}

	public void startStream(DatabaseModel database,
			final EntityRecognition recognition,  final SpamDetector spamDetector,  final CurrentTime currentTime,
			final MapController mapController, final ChartController locationController,  final ChartController organizationController,
			final ChartController personController,  final ChartController languageController,
			final ChartController hashtagController,  final ChartController urlController,final ChartController allWordsController) {


		StatusListener listener = new StatusListener() {
			public void onStatus(Status tweet) {// data keep coming to onStatus method, 
				// the code that written under onStatus method will execute the code again and again when new tweet comes


				if(spamDetector.isNotSpam(tweet,currentTime) && tweet.isRetweet() == false){// if tweet is not spam according to our parameters and not a retweet

					recognition.entityRecognition(tweet,locationController,organizationController,personController,languageController,hashtagController,urlController, allWordsController); // apply entity recognition on tweet text
					//this command takes tweets and analyzes them and updates charts according to analyzation
					database.setTweetCount(database.getTweetCount()+1);
					searchPanel.getTweetCountlabel().setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
					mapController.updateMap(tweet);


				}
			}
			public void onException(Exception arg0) {}
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
			public void onScrubGeo(long arg0, long arg1) {}
			public void onStallWarning(StallWarning arg0) {}
			public void onTrackLimitationNotice(int arg0) {}

		};
		cb = defineConfBuilder();
		tf = new TwitterStreamFactory(cb.build());
		stream = tf.getInstance();
		stream.addListener(listener);

		filtre = new FilterQuery();
		keywordsArray =new String[] { database.getSearchQuery() }; //filter based on choice of keywords
		filtre.track(keywordsArray);
		stream.filter(filtre);
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

	public TwitterStreamFactory getTf() {
		return tf;
	}

	public void setTf(TwitterStreamFactory tf) {
		this.tf = tf;
	}

	public TwitterStream getStream() {
		return stream;
	}

	public void setStream(TwitterStream stream) {
		this.stream = stream;
	}

	public FilterQuery getFiltre() {
		return filtre;
	}

	public void setFiltre(FilterQuery filtre) {
		this.filtre = filtre;
	}

	public String[] getKeywordsArray() {
		return keywordsArray;
	}

	public void setKeywordsArray(String[] keywordsArray) {
		this.keywordsArray = keywordsArray;
	}
















}
