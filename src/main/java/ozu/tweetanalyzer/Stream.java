package ozu.tweetanalyzer;




import controller.ChartController;
import controller.MapController;
import controller.UrlController;
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
	private String notSpamText = "";
	private String spamText = "";
	private SearchPanel searchPanel;


	public Stream(SearchPanel searchPanel){
		this.searchPanel = searchPanel;
	}

	public void startStream(final DatabaseModel database,
			final EntityRecognition recognition,  final SpamDetector spamDetector,  final CurrentTime currentTime,
			final MapController mapController, final ChartController locationController,  final ChartController organizationController,
			final ChartController personController,  final ChartController languageController,
			final ChartController hashtagController,  final UrlController urlController,final ChartController allWordsController) {

		StatusListener listener = new StatusListener() {

			public void onStatus(Status tweet) {// data keep coming to onStatus method, 
				// the code that written under onStatus method will execute the code again and again when new tweet comes




				if(spamDetector.isNotSpam(database,tweet,currentTime) && tweet.isRetweet() == false){// if tweet is not spam according to our parameters and not a retweet

				
					recognition.entityRecognition(0,tweet,locationController,organizationController,personController,languageController,hashtagController,urlController, allWordsController); // apply entity recognition on tweet text
					mapController.updateMap(tweet);


					String fullText = tweet.getUser().getScreenName()+" : "+tweet.getText();
					StringBuilder str = new StringBuilder();
					int j  = 1 ;
					for(int i = 0 ; i< fullText.length()  ;i++){
						str.append(fullText.charAt(i));
						if(i == 50*j){
							database.getNotSpamModel().addElement(str.toString());
							str.setLength(0);
							j++;
						}
					}
					database.getNotSpamModel().addElement(str.toString());
					database.getNotSpamModel().addElement(" ");
					j = 1;
					str.setLength(0);
					database.setNotSpamCount(database.getNotSpamCount()+1);
					database.setTweetCount(database.getTweetCount()+1);
					searchPanel.getTweetCountlabel().setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
					searchPanel.getNotSpamTitle().setTitle("PASSED - "+database.getNotSpamCount());;
					searchPanel.repaint();
				}
				if (!spamDetector.isNotSpam(database,tweet,currentTime) && tweet.isRetweet() == false){


					String fullText = tweet.getUser().getScreenName()+" : "+tweet.getText();
					StringBuilder str = new StringBuilder();
					int j  = 1 ;
					for(int i = 0 ; i< fullText.length()  ;i++){
						str.append(fullText.charAt(i));
						if(i == 50*j){
							database.getSpamModel().addElement(str.toString());
							str.setLength(0);
							j++;
						}

					}
					database.getSpamModel().addElement(str.toString());
					database.getSpamModel().addElement(" ");
					j = 1;
					str.setLength(0);
					database.setSpamCount(database.getSpamCount()+1);
					searchPanel.getWhyTweetIsSpam().setText(database.getEliminationReason());
					searchPanel.getTweetCountlabel().setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
					searchPanel.getSpamTitle().setTitle("SPAMS - "+database.getSpamCount());;

					database.setTweetCount(database.getTweetCount()+1);

					searchPanel.repaint();

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
				.setOAuthConsumerKey("gYBOmrWOnlS69DScInBWrTGFr")
				.setOAuthConsumerSecret("wv8LHjQnCUjR28ALBVinPFRIiufvCQoh82qvmCo2ufkioZVFkU")
				.setOAuthAccessToken("964455826084777984-OxlNnBZYownQAwRAa57c61UApqsYUJ3")
				.setOAuthAccessTokenSecret("frUPdfMpefBgWrwGOFn292o4emhUdYg31gpTy9PijLldW");
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
	public String getNotSpamText() {
		return notSpamText;
	}


	public void setNotSpamText(String notSpamText) {
		this.notSpamText = notSpamText;
	}


	public String getSpamText() {
		return spamText;
	}


	public void setSpamText(String spamText) {
		this.spamText = spamText;
	}















}
