package ozu.tweetanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import model.DatabaseModel;

public class StopWords {

	private String lang;

	public StopWords(String lang){
		this.lang = lang;

	}



	public void loadStopWordsFromFile(DatabaseModel database){
		Set<String> stopWords = new LinkedHashSet<String>();
		BufferedReader SW = null;
		try {
			SW = new BufferedReader(new FileReader("StopWords/"+lang+".txt"));
		} catch (FileNotFoundException e) {
			
		}
		try {
			for(String line;(line = SW.readLine()) != null;)
				stopWords.add(line.toUpperCase().trim());
		} catch (IOException e) {
		}
		try {
			SW.close();
		} catch (IOException e) {
		}
		database.setStopWords(stopWords);
	}
}
