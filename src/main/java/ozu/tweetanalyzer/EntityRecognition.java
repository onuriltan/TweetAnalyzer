package ozu.tweetanalyzer;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import controller.ChartController;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import model.DatabaseModel;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

public class EntityRecognition {

	private String classifierPath = "libs/english.all.3class.distsim.crf.ser.gz";
	private AbstractSequenceClassifier<CoreLabel> classifier;
	private DatabaseModel database;




	public EntityRecognition(DatabaseModel database){
		this.database = database;
		loadClassifier();
	}



	public void entityRecognition(Status tweet, ChartController locationChartController, ChartController organizationChartController, ChartController personChartController, ChartController languageChartController, ChartController hashTagChartController, ChartController verifiedUrlChartController,
			ChartController allWordsController) 
	{
		String text = tweet.getText();
		String tweetForEntity = text.replaceAll("[\\d[^\\w\\s]]+", " ").replaceAll("(\\s{2,})", " ");


		List<Triple<String, Integer, Integer>> out = classifier.classifyToCharacterOffsets(tweetForEntity);
		for (int i = 0; i < out.size(); i++) {
			if (out.get(i).first.equals("LOCATION")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS LOCATION
				String location = tweetForEntity.substring(out.get(i).second,	out.get(i).third);//TAKE LOCATION, MAKE IT UPPER CASED LETTERS TO MATCH SAME WORDS(e.g ONUR onur)
				updateDatabase(database.getLocationList(), location.toLowerCase(), "location");//UPDATE THE LOCATION LIST
				locationChartController.setDataset(listToPieChartDataset(database.getLocationList()));// CHANGE THE CHART DATASET
				locationChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
				System.out.println("Location updated.");
			}		
			if (out.get(i).first.equals("ORGANIZATION")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS ORGANIZATION
				String organization = tweetForEntity.substring(out.get(i).second,	out.get(i).third);
				updateDatabase(database.getOrganizationList(), organization.toLowerCase(), "organization");//UPDATE DATA WHEN NEW TOKEN COMES
				organizationChartController.setDataset(listToPieChartDataset(database.getOrganizationList()));// CHANGE THE CHART DATASET
				organizationChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
				System.out.println("Organization updated.");

			}
			if (out.get(i).first.equals("PERSON")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS PERSON
				String person = tweetForEntity.substring(out.get(i).second, out.get(i).third);
				updateDatabase(database.getPersonList(), person.toLowerCase(),"person");
				personChartController.setDataset(listToPieChartDataset(database.getPersonList()));
				personChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
				System.out.println("Person updated.");

			}
		}

		String language = tweet.getLang().toUpperCase();// GET THE TWEET LANGUAGE
		updateDatabase(database.getLanguageList(), language, "language");
		languageChartController.setDataset(listToPieChartDataset(database.getLanguageList()));// CHANGE THE CHART DATASET
		languageChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET

		HashtagEntity[] hashtagsEntities = tweet.getHashtagEntities();// TO GET HASHTAGS THAT USED IN TWEET
		for (HashtagEntity hashtag : hashtagsEntities){
			updateDatabase(database.getHashTagList(), "#"+hashtag.getText(),"hashtag");
		}
		hashTagChartController.setDataset(listToPieChartDataset(database.getHashTagList()));// CHANGE THE CHART DATASET
		hashTagChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET

		URLEntity[] urls = tweet.getURLEntities();// TAKE URL ENTITIES
		for(URLEntity url : urls){
			updateDatabase(database.getVerifiedURLList(), url.getURL(), "verifiedURLList");
		}						

		verifiedUrlChartController.setDataset(listToPieChartDataset(database.getVerifiedURLList()));// CHANGE THE CHART DATASET
		verifiedUrlChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET


		StringTokenizer tokenizer = new StringTokenizer(tweet.getText());
		String lang = tweet.getLang();

		StopWords stopWords = new StopWords(lang);
		stopWords.loadStopWordsFromFile(database);
		while(tokenizer.hasMoreTokens()){

			String word = tokenizer.nextToken().toLowerCase();
			if(database.getStopWords().contains(word.toUpperCase()) == false ){
				if(word.charAt(0) != '#'){
					if(word.contains("https") == false){
						if(word.length()>2){
							updateDatabase(database.getAllWords(), word, "allword");

						}
					}
				}

				if(database.getStopWords().contains(word.toLowerCase().toString()) == false){
				}


			}
		}
		allWordsController.setDataset(listToPieChartDataset(database.getAllWords()));// CHANGE THE CHART DATASET
		allWordsController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET */


	}




	public void updateDatabase(Hashtable<String, Integer> table, String key, String tableName){

		if(!table.containsKey(key)){
			table.put(key,1);
		}
		if(table.containsKey(key)){
			table.put(key, table.get(key)+1);
		}
		if(tableName.equals("location")){
			database.setLocationList(table);
		}
		if(tableName.equals("organization")){
			database.setOrganizationList(table);
		}
		if(tableName.equals("person")){
			database.setPersonList(table);
		}
		if(tableName.equals("language")){
			database.setLanguageList(table);
		}
		if(tableName.equals("hashtag")){
			database.setHashTagList(table);
		}
		if(tableName.equals("verifiedURLList")){
			database.setVerifiedURLList(table);
		}
		if(tableName.equals("allword")){
			database.setAllWords(table);
		}

	}
	public PieDataset listToPieChartDataset(Hashtable<String, Integer> list){


		final DefaultPieDataset result = new DefaultPieDataset();

		if(list.size()<5){//IF DATASET HAS LESS THAN 5 TOKEN, SHOW IT RANDOM
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				result.setValue(key+" = "+list.get(key), list.get(key));

			}
		}
		if(list.size()>= 5){// TO SHOW NO MORE THAN 5 DIFFERENT TOKEN IN GRAPH, 
			//BECAUSE NO POINT SHOWING ALL THE TOKENS IN ONE GRAPH 

			ArrayList<Map.Entry<String, Integer>> sortedList = sortHashTable(list);

			for(int i = sortedList.size()-1 ; i>=sortedList.size()-5;i--){
				String token = sortedList.get(i).toString();
				String[] parts = token.split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
				result.setValue(tokenName.toString()+" = "+tokenValue.toString(), Integer.valueOf(tokenValue.toString()));

			}
		}
		return result;

	}

	public ArrayList<Map.Entry<String, Integer>> sortHashTable (Hashtable<String, Integer> list){

		//TO SORT THE TOKENS OF HASHTABLE IN DESCENDING ORDER 
		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(list.entrySet());
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>(){

			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}});

		return sortedList;
	}

	private void loadClassifier() {
		try {
			classifier = CRFClassifier.getClassifier(classifierPath);//LOAD CLASSIFIER FROM FILE TO START ENTITY RECOGNITION
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String cleanData(String tweet) {


		//remove # from hash tag
		tweet = tweet.replaceAll("RT", " ");
		tweet = tweet.toLowerCase();
		tweet = tweet.replaceAll("[^a-zA-Z0-9 ]+", "").trim();


		return tweet;
	}



}
