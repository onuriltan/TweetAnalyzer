package model;

import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JList;


public class DatabaseModel {
	private int tweetCount;
	private String searchQuery;
	private Hashtable<String, Integer> locationList = new Hashtable<String, Integer>()  ;
	private Hashtable<String, Integer> organizationList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> personList= new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> languageList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> hashTagList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> verifiedURLList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> allWords = new Hashtable<String, Integer>()  ;
	private Set<String> stopWords = new LinkedHashSet<String>();
	private DefaultListModel<String> notSpamModel = new DefaultListModel<String>();
	private DefaultListModel<String> spamModel = new DefaultListModel<String>();
	private JList<String> notSpamList = new JList<String>(notSpamModel);
	private JList<String> spamList = new JList<String>(spamModel);



	public DatabaseModel(){}


	public Hashtable<String, Integer> getLocationList() {
		return locationList;
	}

	public int getTweetCount() {
		return tweetCount;
	}

	public void setTweetCount(int tweetCount) {
		this.tweetCount = tweetCount;
	}

	public void setLocationList(Hashtable<String, Integer> locationList) {
		this.locationList = locationList;
	}

	public Hashtable<String, Integer> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(Hashtable<String, Integer> organizationList) {
		this.organizationList = organizationList;
	}

	public Hashtable<String, Integer> getPersonList() {
		return personList;
	}

	public void setPersonList(Hashtable<String, Integer> personList) {
		this.personList = personList;
	}

	public Hashtable<String, Integer> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(Hashtable<String, Integer> languageList) {
		this.languageList = languageList;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public Hashtable<String, Integer> getHashTagList() {
		return hashTagList;
	}

	public void setHashTagList(Hashtable<String, Integer> hashTagList) {
		this.hashTagList = hashTagList;
	}

	public Hashtable<String, Integer> getVerifiedURLList() {
		return verifiedURLList;
	}

	public void setVerifiedURLList(Hashtable<String, Integer> verifiedURLList) {
		this.verifiedURLList = verifiedURLList;
	}
	public Hashtable<String, Integer> getAllWords() {
		return allWords;
	}



	public void setAllWords(Hashtable<String, Integer> allWords) {
		this.allWords = allWords;
	}



	public Set<String> getStopWords() {
		return stopWords;
	}

	public void setStopWords(Set<String> stopWords) {
		this.stopWords = stopWords;
	}


	public JList<String> getNotSpamList() {
		return notSpamList;
	}

	public void setNotSpamList(JList<String> notSpamTList) {
		this.notSpamList = notSpamTList;
	}

	public JList<String> getSpamList() {
		return spamList;
	}

	public void setSpamList(JList<String> spamList) {
		this.spamList = spamList;
	}

	public DefaultListModel<String> getNotSpamModel() {
		return notSpamModel;
	}

	public void setNotSpamModel(DefaultListModel<String> notSpamModel) {
		this.notSpamModel = notSpamModel;
	}

	public DefaultListModel<String> getSpamModel() {
		return spamModel;
	}

	public void setSpamModel(DefaultListModel<String> spamModel) {
		this.spamModel = spamModel;
	}


	public void clearDatabase(){
		hashTagList.clear();
		locationList.clear();
		organizationList.clear();
		personList.clear();
		languageList.clear();
		verifiedURLList.clear();
		allWords.clear();
		searchQuery = "";
		tweetCount = 0;
		notSpamModel.clear();
		spamModel.clear();
		

	}




}
