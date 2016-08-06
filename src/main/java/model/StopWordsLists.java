package model;

import ozu.tweetanalyzer.StopWords;

public class StopWordsLists {
	
	
	public StopWordsLists(String lang, DatabaseModel database){
		StopWords stopWords = new StopWords(lang);
		stopWords.loadStopWordsFromFile(database);
	}
	

	
	
	

}
