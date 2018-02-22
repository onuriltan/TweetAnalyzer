package ozu.tweetanalyzer;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import controller.ChartController;
import controller.UrlController;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
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


	public void entityRecognition(int count, Status tweet, ChartController locationChartController, ChartController organizationChartController, ChartController personChartController, ChartController languageChartController, ChartController hashTagChartController, UrlController verifiedUrlChartController,
			ChartController allWordsController) 
	{

		String text = tweet.getText();
		text = text.toLowerCase(Locale.ENGLISH);
		text = text.replaceAll("((www\\.[^\\s]+)|(https?://[^\\s]+))", "");
		text = text.replaceAll("\\p{Punct}+", " ");
		text = text.trim().replaceAll(" +", " ");// eliminate double spaces, special words, http and hashtags

		String cleanText = text.toString().toLowerCase(Locale.ENGLISH);
		String entityText ="";
		StringTokenizer tokenizer = new StringTokenizer(cleanText);

		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			token = Character.toUpperCase(token.charAt(0)) + token.substring(1);
			entityText = entityText+" "+token+" ";
		}

		entityText = entityText.trim().replaceAll(" +", " ");

		List<String> recognizedStrs = enetityRecognition(entityText);

		for (int i = 0; i < recognizedStrs.size(); i++) {
			//System.out.println(recognizedStrs.get(i));
			String[] parts = recognizedStrs.get(i).split("=", 2);
			String word = parts[0];
			String answer = parts[1];
			if(answer.equals("LOCATION")){
				if(count == 0){
					database.setLocation(database.getLocation()+"\n"
							+tweet.getUser().getScreenName()+" : "
							+tweet.getText()+"\n"+"\n");

					locationChartController.setText(database.getLocation());
				}
				updateDatabase(database.getLocationList(), word, "location");//UPDATE THE LOCATION LIST
				locationChartController.setDataset(listToPieChartDataset(database.getLocationList()));// CHANGE THE CHART DATASET
				locationChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
			}
			if(answer.equals("ORGANIZATION")){
				if(count == 0){
					database.setOrganization(database.getOrganization()+"\n"
							+tweet.getUser().getScreenName()+" : "
							+tweet.getText()+"\n"+"\n");
					organizationChartController.setText(database.getOrganization());
				}
				updateDatabase(database.getOrganizationList(), word, "organization");//UPDATE DATA WHEN NEW TOKEN COMES
				organizationChartController.setDataset(listToPieChartDataset(database.getOrganizationList()));// CHANGE THE CHART DATASET
				organizationChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
			}
			if(answer.equals("PERSON")){
				if(count == 0){
					database.setPerson(database.getPerson()+"\n"
							+tweet.getUser().getScreenName()+" : "
							+tweet.getText()+"\n"+"\n");
					personChartController.setText(database.getPerson());
				}
				updateDatabase(database.getPersonList(), word,"person");
				personChartController.setDataset(listToPieChartDataset(database.getPersonList()));
				personChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
			}
			count = 1;


		}


		if(!tweet.getLang().isEmpty() && !tweet.getLang().equals("und")){
			String language = tweet.getLang().toUpperCase();// GET THE TWEET LANGUAGE

			updateDatabase(database.getLanguageList(), language, "language");
			database.setLanguage(database.getLanguage()+"\n"
					+tweet.getUser().getScreenName()+" : "
					+tweet.getText()+"\n"+"\n");
			languageChartController.setText(database.getLanguage());
			languageChartController.setDataset(listToPieChartDataset(database.getLanguageList()));// CHANGE THE CHART DATASET
			languageChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET
		}

		HashtagEntity[] hashtagsEntities = tweet.getHashtagEntities();// TO GET HASHTAGS THAT USED IN TWEET
		if(hashtagsEntities.length>0){
			for (HashtagEntity hashtag : hashtagsEntities){
				updateDatabase(database.getHashTagList(), "#"+hashtag.getText(),"hashtag");
			}
			hashTagChartController.setDataset(listToPieChartDataset(database.getHashTagList()));// CHANGE THE CHART DATASET
			database.setHashtag(database.getHashtag()+"\n"
					+tweet.getUser().getScreenName()+" : "
					+text+"\n"+"\n");
			hashTagChartController.setText(database.getHashtag());
			hashTagChartController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET

		}
		URLEntity[] urls = tweet.getURLEntities();// TAKE URL ENTITIES
		if(urls.length>0){
			for(URLEntity url : urls){
				if(tweet.getText().contains(url.getText())){
				updateDatabase(database.getVerifiedURLList(), url.getURL(), "verifiedURLList");
				}
			}						
			database.setUrl(database.getUrl()+"\n"
					+tweet.getUser().getScreenName()+" : "
					+tweet.getText()+"\n"+"\n" );
			verifiedUrlChartController.setText(database.getUrl());
			verifiedUrlChartController.updateUrlPanel(database,database.getVerifiedURLList());//UPDATE CHART BASED ON CHANGED DATASET

		}


		if(tweet.getUser().isVerified()){
			URLEntity[] urlss = tweet.getURLEntities();// TAKE URL ENTITIES
			if(urls.length>0){
				for(URLEntity url : urlss){
					updateDatabase(database.getVerifiedURLList(), url.getURL(), "verifiedURLList");
					String urlText = tweet.getUser().getName()+" : "+url.getExpandedURL()+"\n";
					urlText = urlText+"\n";
					database.setUrlText(database.getUrlText()+urlText); 
					database.setUrl(database.getUrl()+"\n"
							+tweet.getUser().getScreenName()+" : "
							+tweet.getText()+"\n"+"\n");
					verifiedUrlChartController.setText(database.getUrl());
				}
				verifiedUrlChartController.updateUrlPanel(database,database.getVerifiedURLList());
			}				
		}

		StringTokenizer tokenizer1 = new StringTokenizer(entityText);
		String lang = tweet.getLang();

		StopWords stopWords = new StopWords(lang);
		stopWords.loadStopWordsFromFile(database);
		while(tokenizer1.hasMoreTokens()){

			String word = tokenizer1.nextToken().toLowerCase();
			if(database.getStopWords().contains(word.toUpperCase()) == false ){
				if(word.charAt(0) != '#'){
					if(word.contains("https") == false){
						if(word.length()>2){
							updateDatabase(database.getAllWords(), word, "allword");

						}
					}
				}
			}
		}
		allWordsController.setDataset(listToPieChartDataset(database.getAllWords()));// CHANGE THE CHART DATASET
		database.setMostcommon(database.getMostcommon()+"\n"
				+tweet.getUser().getScreenName()+" : "
				+tweet.getText()+"\n"+"\n");
		allWordsController.setText(database.getMostcommon());
		allWordsController.updateChart();//UPDATE CHART BASED ON CHANGED DATASET */


	}




	public void updateDatabase(Hashtable<String, Integer> table, String key, String tableName){

		if(table.containsKey(key)){
			table.put(key, table.get(key)+1);
		}

		if(!table.containsKey(key)){
			table.put(key,1);
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

		if(list.size()<7){//IF DATASET HAS LESS THAN 5 TOKEN, SHOW IT RANDOM
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				result.setValue(key+" = "+list.get(key), list.get(key));

			}
		}
		if(list.size()>= 7){// TO SHOW NO MORE THAN 5 DIFFERENT TOKEN IN GRAPH, 
			//BECAUSE NO POINT SHOWING ALL THE TOKENS IN ONE GRAPH 

			ArrayList<Map.Entry<String, Integer>> sortedList = sortHashTable(list);

			for(int i = sortedList.size()-1 ; i>=sortedList.size()-7;i--){
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
	public List<String> enetityRecognition(String text) {
		List<List<CoreLabel>> classify = classifier.classify(text);
		List<String> results = new ArrayList<String>();
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {
				String word = coreLabel.word();
				String answer = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				if(!"O".equals(answer)){
					results.add((word+"="+answer));
				}

			}
		}
		return results;
	}



}