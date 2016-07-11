package ozu.tweetanalyzer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

public class EntityRecognition {

	private String classifierPath = "libs/english.all.3class.distsim.crf.ser.gz";
	private AbstractSequenceClassifier<CoreLabel> classifier;
	private Database database;


	public EntityRecognition(Database database){
		this.database = database;
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

	public void entityRecognition(Status tweet, CurrentTime time,ChartGeneration locationChart,ChartGeneration organizationChart,ChartGeneration personChart,ChartGeneration languageChart,ChartGeneration hashTagChart,ChartGeneration verifiedUrlChart,Deneme deneme) 
	{
		String text = tweet.getText();



		List<Triple<String, Integer, Integer>> out = classifier.classifyToCharacterOffsets(text);

		for (int i = 0; i < out.size(); i++) {
			if (out.get(i).first.equals("LOCATION")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS LOCATION
				String location = text.substring(out.get(i).second,	out.get(i).third).toUpperCase();//TAKE LOCATION, MAKE IT UPPER CASED LETTERS TO MAKE SAME WORDS 
				updateLists(database.getLocationList(), location, "location");//UPDATE THE LOCATION LIST
				locationChart.getPlot().setDataset(listToPieChartDataset(database.getLocationList()));// CHANGE THE CHART DATASET
	

			}		
			if (out.get(i).first.equals("ORGANIZATION")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS ORGANIZATION
				String organization = text.substring(out.get(i).second,	out.get(i).third).toUpperCase();
				updateLists(database.getOrganizationList(), organization, "organization");//UPDATE DATA WHEN NEW TOKEN COMES
				organizationChart.getPlot().setDataset(listToPieChartDataset(database.getOrganizationList()));// CHANGE THE CHART DATASET
			}
			if (out.get(i).first.equals("PERSON")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS PERSON
				String person = text.substring(out.get(i).second, out.get(i).third).toUpperCase();
				updateLists(database.getPersonList(), person,"person");
				personChart.getPlot().setDataset(listToPieChartDataset(database.getPersonList()));// CHANGE THE CHART DATASET

			}
		}

		String language = tweet.getLang();// GET THE TWEET LANGUAGE
		updateLists(database.getLanguageList(), language, "language");
		languageChart.getPlot().setDataset(listToPieChartDataset(database.getLanguageList()));// CHANGE THE CHART DATASET


		HashtagEntity[] hashtagsEntities = tweet.getHashtagEntities();// TO GET HASHTAGS THAT USED IN TWEET
		for (HashtagEntity hashtag : hashtagsEntities){
			updateLists(database.getHashTagList(), "#"+hashtag.getText(),"hashtag");
		}

		hashTagChart.getPlot().setDataset(listToPieChartDataset(database.getHashTagList()));// CHANGE THE CHART DATASET

		if(tweet.getUser().isVerified()){// IF USER ACCOUNT IS VERIFIED ACCOUNT
			URLEntity[] urls = tweet.getURLEntities();// TAKE URL ENTITIES
			for(URLEntity url : urls){
				updateLists(database.getVerifiedURLList(), url.getURL(), "verifiedURLList");
			}						
		}
		verifiedUrlChart.getPlot().setDataset(listToPieChartDataset(database.getVerifiedURLList()));// CHANGE THE CHART DATASET





	}

	public void updateLists(Hashtable<String, Integer> table, String key, String tableName){

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



	public PieDataset listToPieChartDataset(Hashtable<String, Integer> list){


		final DefaultPieDataset result = new DefaultPieDataset();


		if(list.size()<5){//IF DATASET HAS LESS THAN 5 TOKEN, SHOW IT RANDOM
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				result.setValue(key+" = "+list.get(key), list.get(key));
				System.out.print(key+" = "+list.get(key)+" ");

			}
			System.out.println();
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
				System.out.print(tokenName.toString()+" = "+tokenValue.toString()+" ");

			}
			System.out.println();
		}
		return result;

	}


}
