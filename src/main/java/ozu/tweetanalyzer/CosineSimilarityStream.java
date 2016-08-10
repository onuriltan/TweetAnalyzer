package ozu.tweetanalyzer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import controller.CosineSimilarityPanelController;
import model.DatabaseModel;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class CosineSimilarityStream {


	private DatabaseModel database;
	private CurrentTime currentTime;
	private SpamDetector spamDetector;
	private TwitterStreamFactory tf;
	private TwitterStream stream;
	private FilterQuery filtre;
	public CosineSimilarityStream(DatabaseModel database,CurrentTime currentTime,SpamDetector spamDetector){

		this.database = database;
		this.currentTime = currentTime;
		this.spamDetector = spamDetector;

	}



	public void startCosineStream(CosineSimilarityPanelController cosineController){
		ArrayList<String> asd = new ArrayList<String>();
		for (int i = 0; i < database.getTrendTopicArray().length; i++) {
			asd.add(i,database.getTrendTopicArray()[i]+"  :  "+"\n");
			
		}
		
		
		database.setTrendDatabaseList(asd);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("xjhjEo5dv9l9gkH6aOsYT9FEW")
		.setOAuthConsumerSecret("BCFiOEtwg49XtHzkhQ08CF5Nm4Nafx2ppHjg6gmA0aB862L7ps")
		.setOAuthAccessToken("4013320817-ShvBoFTZYGMm8RQMrcZEmsihk9yua2KpU3WEoPJ")
		.setOAuthAccessTokenSecret("Fmz8lmkNce9Fx5svO7XyFJikvyRp3Y0hssZQDDrjtxhP7");
		cb.setJSONStoreEnabled(true);


		StatusListener listener = new StatusListener() {

			public void onStatus(Status tweet) {
				if(database.getTrendsCount() > 100*database.getCosineSimilarityCalculatonVariable()){

					database.getTrendDatabaseList().add(0,database.getTrendNumberOne());
					database.getTrendDatabaseList().add(1,database.getTrendNumberTwo());
					database.getTrendDatabaseList().add(2,database.getTrendNumberThree());
					database.getTrendDatabaseList().add(3,database.getTrendNumberFour());
					database.getTrendDatabaseList().add(4,database.getTrendNumberFive());
					database.getTrendDatabaseList().add(5,database.getTrendNumberSix());
					database.getTrendDatabaseList().add(6,database.getTrendNumberSeven());
					database.getTrendDatabaseList().add(7,database.getTrendNumberEight());
					database.getTrendDatabaseList().add(8,database.getTrendNumberNine());
					database.getTrendDatabaseList().add(9,database.getTrendNumberTen());
					database.getTrendDatabaseList().add(10,database.getTrendNumber11());
					database.getTrendDatabaseList().add(11,database.getTrendNumber12());
					database.getTrendDatabaseList().add(12,database.getTrendNumber13());
					database.getTrendDatabaseList().add(13,database.getTrendNumber14());
					database.getTrendDatabaseList().add(14,database.getTrendNumber15());
					database.getTrendDatabaseList().add(15,database.getTrendNumber16());
					database.getTrendDatabaseList().add(16,database.getTrendNumber17());
					database.getTrendDatabaseList().add(17,database.getTrendNumber18());
					database.getTrendDatabaseList().add(18,database.getTrendNumber19());
					database.getTrendDatabaseList().add(19,database.getTrendNumber20());
					Hashtable<String, Double> topSimilarTrends= new Hashtable<String, Double>() ;
					ArrayList<String> sortedKeys = new ArrayList<String>();
					ArrayList<Double> sortedValues = new ArrayList<Double>();
					ArrayList<String> cosStream = new ArrayList<String>();
					database.setCosineSimArray(cosStream); 					
					topSimilarTrends.clear();
					calculateCosineSimilarity(cosStream,topSimilarTrends,cosineController,sortedKeys,sortedValues);
					database.setCosineSimilarityCalculatonVariable(database.getCosineSimilarityCalculatonVariable()+1);

				}

				if(spamDetector.isNotSpam(database,tweet,currentTime) && tweet.isRetweet() == false){

					getTweets(tweet);
					database.setTrendsCount(database.getTrendsCount()+1);

				}
			}


			public void onException(Exception arg0) {}
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
			public void onScrubGeo(long arg0, long arg1) {}
			public void onStallWarning(StallWarning arg0) {}
			public void onTrackLimitationNotice(int arg0) {}
		};
		tf = new TwitterStreamFactory(cb.build());

		stream = tf.getInstance();
		stream.addListener(listener);

		filtre = new FilterQuery();
		filtre.track(database.getTrendTopicArray());
		stream.filter(filtre);


	}

	private void getTweets(Status tweet) {

		String[] trendTopicArray = new String[20];
		trendTopicArray = database.getTrendTopicArray();
		
		for(int i = 0 ; i<trendTopicArray.length; i++){
			if(tweet.getText().contains(trendTopicArray[i])){
				String text = tweet.getText();
				text = text.toLowerCase(Locale.ENGLISH);
				text = text.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");
				text = text.replaceAll("\\p{Punct}+", " ");
				text = text.trim().replaceAll(" +", " ");// eliminate double spaces, special words, http and hashtags

				String cleanText = text.toString().toLowerCase(Locale.ENGLISH);
				String entityText ="";
				StringTokenizer tokenizerr = new StringTokenizer(cleanText);

				while(tokenizerr.hasMoreTokens()){
					String token = tokenizerr.nextToken();
					token = Character.toUpperCase(token.charAt(0)) + token.substring(1);
					entityText = entityText+" "+token+" ";
				}

				entityText = entityText.trim().replaceAll(" +", " ");
				cleanText = entityText;


				if(i == 0){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberOne(database.getTrendNumberOne() +" "+ token + " ");
					}
				}
				if(i == 1){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberTwo(database.getTrendNumberTwo() +" "+ token + " ");

					}
				}
				if(i == 2){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberThree(database.getTrendNumberThree() +" "+ token + " ");

					}
				}
				if(i == 3){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						if(token.toString().contains("http") == false)
							database.setTrendNumberFour(database.getTrendNumberFour() +" "+ token + " ");
					}
				}
				if(i == 4){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberFive(database.getTrendNumberFive() +" "+ token + " ");
					}
				}
				if(i == 5){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberSix(database.getTrendNumberSix() + " "+token + " ");
					}
				}
				if(i == 6){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						if(token.toString().contains("http") == false)
							database.setTrendNumberSeven(database.getTrendNumberSeven() +" "+ token + " ");					}
				}
				if(i == 7){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberEight(database.getTrendNumberEight() +" "+ token + " ");
					}
				}
				if(i == 8){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						if(token.toString().contains("http") == false)
							database.setTrendNumberNine(database.getTrendNumberNine() +" "+ token + " ");
					}
				}
				if(i == 9){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumberTen(database.getTrendNumberTen() +" "+ token + " ");					}
				}
				if(i == 10){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber11(database.getTrendNumber11() +" "+ token + " ");

					}
				}
				if(i == 11){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber12(database.getTrendNumber12() +" "+ token + " ");

					}
				}
				if(i == 12){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber13(database.getTrendNumber13() +" "+ token + " ");

					}
				}
				if(i == 13){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						if(token.toString().contains("http") == false)
							database.setTrendNumber14(database.getTrendNumber14() +" "+ token + " ");
					}
				}
				if(i == 14){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber15(database.getTrendNumber15() +" "+ token + " ");
					}
				}
				if(i == 15){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber16(database.getTrendNumber16() + " "+token + " ");
					}
				}
				if(i == 16){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						if(token.toString().contains("http") == false)
							database.setTrendNumber17(database.getTrendNumber17() +" "+ token + " ");					}
				}
				if(i == 17){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber18(database.getTrendNumber18() +" "+ token + " ");
					}
				}
				if(i == 18){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber19(database.getTrendNumber19() +" "+ token + " ");
					}
				}
				if(i == 19){
					StringTokenizer tokenizer = new StringTokenizer(cleanText);
					while(tokenizer.hasMoreTokens()){
						String token = tokenizer.nextToken();
						database.setTrendNumber20(database.getTrendNumber20() +" "+ token + " ");					}
				}
			}

		}
	}


	private  void calculateCosineSimilarity(ArrayList<String> array,Hashtable<String, Double> topSimilarTrends,CosineSimilarityPanelController cosineController, ArrayList<String> sortedKeys, ArrayList<Double> sortedValues) {


		for(int i = 0 ; i< database.getTrendTopicArray().length ; i++){

			for(int j = i + 1 ; j<database.getTrendTopicArray().length ; j++){

				if(database.getTrendDatabaseList().get(i) != database.getTrendDatabaseList().get(j)){

					if(database.getTrendDatabaseList().get(i).isEmpty() == false ){
						if(database.getTrendDatabaseList().get(j).isEmpty() == false){
							double cosSimilarity = cosineSimilarity(database.getTrendDatabaseList().get(i), database.getTrendDatabaseList().get(j));
							if(Double.isNaN(cosSimilarity) == false){

								topSimilarTrends.put("Cosine similarity for trends "+database.getTrendTopicArray()[i]+" and "+database.getTrendTopicArray()[j]+" is ", cosSimilarity);
								sortedKeys.add("Cosine similarity for trends "+database.getTrendTopicArray()[i]+" and "+database.getTrendTopicArray()[j]+" is: "+cosSimilarity);
								sortedValues.add(cosSimilarity);
								cosineController.updateCosinePanel(topSimilarTrends,database,sortedKeys,sortedValues);
								array.add("Cosine similarity for trends "+database.getTrendTopicArray()[i]+" and "+database.getTrendTopicArray()[j]+" is: "+cosSimilarity);

							}

						}
					}
				}
			}
		}
	}

	public  Map<String, Integer> getTermFrequencyMap(String[] terms) {
		Map<String, Integer> termFrequencyMap = new HashMap<>();
		for (String term : terms) {
			Integer n = termFrequencyMap.get(term);
			n = (n == null) ? 1 : ++n;
			termFrequencyMap.put(term, n);
		}
		return termFrequencyMap;
	}



	public  double cosineSimilarity(String text1, String text2) {
		//Get vectors
		//Get unique words from both sequences
		Map<String, Integer> a = getTermFrequencyMap(text1.split("\\W+"));
		Map<String, Integer> b = getTermFrequencyMap(text2.split("\\W+"));
		HashSet<String> intersection = new HashSet<>(a.keySet());
		intersection.retainAll(b.keySet());

		double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

		//Calculate dot product
		for (String item : intersection) {
			dotProduct += a.get(item) * b.get(item);
		}

		//Calculate magnitude a
		for (String k : a.keySet()) {
			magnitudeA += Math.pow(a.get(k), 2);
		}

		//Calculate magnitude b
		for (String k : b.keySet()) {
			magnitudeB += Math.pow(b.get(k), 2);
		}

		//return cosine similarity
		double result = dotProduct / Math.sqrt(magnitudeA * magnitudeB);

		return result;
	}

	public TwitterStream getStream() {
		return stream;
	}

	public void setStream(TwitterStream stream) {
		this.stream = stream;
	}

}







