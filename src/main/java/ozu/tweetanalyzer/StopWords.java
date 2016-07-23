package ozu.tweetanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import model.DatabaseModel;

public class StopWords {



	public StopWords(){

	}



	public void loadStopWordsFromFile(DatabaseModel database){
		Set<String> stopWords = new LinkedHashSet<String>();
		BufferedReader SW = null;
		try {
			SW = new BufferedReader(new FileReader("StopWords.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(String line;(line = SW.readLine()) != null;)
				stopWords.add(line.toUpperCase().trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			SW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		database.setStopWords(stopWords);
	}
}
