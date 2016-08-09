package model;

import java.util.ArrayList;
import java.util.Hashtable;


import javax.swing.DefaultListModel;
import javax.swing.JList;


public class DatabaseModel {
	private int tweetCount;
	private int trendsCount;
	private String searchQuery;
	private Hashtable<String, Integer> locationList = new Hashtable<String, Integer>()  ;
	private Hashtable<String, Integer> organizationList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> personList= new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> languageList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> hashTagList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> verifiedURLList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> allWords = new Hashtable<String, Integer>()  ;
	private ArrayList<String> stopWords = new ArrayList<String>();
	private DefaultListModel<String> notSpamModel = new DefaultListModel<String>();
	private DefaultListModel<String> spamModel = new DefaultListModel<String>();
	private JList<String> notSpamList = new JList<String>(notSpamModel);
	private JList<String> spamList = new JList<String>(spamModel);
	private String trendNumberOne = " ";
	private String trendNumberTwo = " ";
	private String trendNumberThree = " ";
	private String trendNumberFour = " ";
	private String trendNumberFive = " ";
	private String trendNumberSix = " ";
	private String trendNumberSeven = " ";
	private String trendNumberEight = " ";
	private String trendNumberNine = " ";
	private String trendNumberTen = " ";
	private String trendNumber11 = " ";
	private String trendNumber12 = " ";
	private String trendNumber13 = " ";
	private String trendNumber14 = " ";
	private String trendNumber15 = " ";
	private String trendNumber16 = " ";
	private String trendNumber17 = " ";
	private String trendNumber18 = " ";
	private String trendNumber19 = " ";
	private String trendNumber20 = " ";
	private String[] trendTopicArray = new String[20];
	private ArrayList<String> trendDatabaseList = new ArrayList<String>();
	private ArrayList<String> trendTopicList = new ArrayList<String>();
	private ArrayList<String> cosineSimilarityArray = new ArrayList<String>();
	private Hashtable<String, Double> topSimilarTrends= new Hashtable<String, Double>() ;
	private int cosineSimilarityCalculatonVariable = 1;
	private int spamCount = 0;
	private int notSpamCount = 0;
	private String eliminationReason = " ";
	private String location = " ";
	private String organization = " ";
	private String person = " ";
	private String language = " ";
	private String hashtag = " ";
	private String url = " ";
	private String mostcommon = " ";
	private String urlText = " "; 



	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getPerson() {
		return person;
	}


	public void setPerson(String person) {
		this.person = person;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getHashtag() {
		return hashtag;
	}


	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMostcommon() {
		return mostcommon;
	}


	public void setMostcommon(String mostcommon) {
		this.mostcommon = mostcommon;
	}


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



	public ArrayList<String> getStopWords() {
		return stopWords;
	}

	public void setStopWords(ArrayList<String> stopWords) {
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





	public String getTrendNumberOne() {
		return trendNumberOne;
	}


	public void setTrendNumberOne(String trendNumberOne) {
		this.trendNumberOne = trendNumberOne;
	}


	public String getTrendNumberTwo() {
		return trendNumberTwo;
	}


	public void setTrendNumberTwo(String trendNumberTwo) {
		this.trendNumberTwo = trendNumberTwo;
	}


	public String getTrendNumberThree() {
		return trendNumberThree;
	}


	public void setTrendNumberThree(String trendNumberThree) {
		this.trendNumberThree = trendNumberThree;
	}


	public String getTrendNumberFour() {
		return trendNumberFour;
	}


	public void setTrendNumberFour(String trendNumberFour) {
		this.trendNumberFour = trendNumberFour;
	}


	public String getTrendNumberFive() {
		return trendNumberFive;
	}


	public void setTrendNumberFive(String trendNumberFive) {
		this.trendNumberFive = trendNumberFive;
	}


	public String getTrendNumberSix() {
		return trendNumberSix;
	}


	public void setTrendNumberSix(String trendNumberSix) {
		this.trendNumberSix = trendNumberSix;
	}


	public String getTrendNumberSeven() {
		return trendNumberSeven;
	}


	public void setTrendNumberSeven(String trendNumberSeven) {
		this.trendNumberSeven = trendNumberSeven;
	}


	public String getTrendNumberEight() {
		return trendNumberEight;
	}


	public void setTrendNumberEight(String trendNumberEight) {
		this.trendNumberEight = trendNumberEight;
	}


	public String getTrendNumberNine() {
		return trendNumberNine;
	}


	public void setTrendNumberNine(String trendNumberNine) {
		this.trendNumberNine = trendNumberNine;
	}


	public String getTrendNumberTen() {
		return trendNumberTen;
	}


	public void setTrendNumberTen(String trendNumberTen) {
		this.trendNumberTen = trendNumberTen;
	}




	public String[] getTrendTopicArray() {
		return trendTopicArray;
	}


	public void setTrendTopicArray(String[] trendTopicArray) {
		this.trendTopicArray = trendTopicArray;
	}


	public ArrayList<String> getTrendDatabaseList() {
		return trendDatabaseList;
	}


	public void setTrendDatabaseList(ArrayList<String> trendDatabaseList) {
		this.trendDatabaseList = trendDatabaseList;
	}

	public ArrayList<String> getTrendTopicList() {
		return trendTopicList;
	}


	public void setTrendTopicList(ArrayList<String> trendTopicList) {
		this.trendTopicList = trendTopicList;
	}


	public ArrayList<String> getCosineSimilarityArray() {
		return cosineSimilarityArray;
	}


	public void setCosineSimilarityArray(ArrayList<String> cosineSimilarityArray) {
		this.cosineSimilarityArray = cosineSimilarityArray;
	}



	public Hashtable<String, Double> getTopSimilarTrends() {
		return topSimilarTrends;
	}


	public void setTopSimilarTrends(Hashtable<String, Double> topSimilarTrends) {
		this.topSimilarTrends = topSimilarTrends;
	}


	public String getTrendNumber11() {
		return trendNumber11;
	}


	public void setTrendNumber11(String trendNumber11) {
		this.trendNumber11 = trendNumber11;
	}


	public String getTrendNumber12() {
		return trendNumber12;
	}


	public void setTrendNumber12(String trendNumber12) {
		this.trendNumber12 = trendNumber12;
	}


	public String getTrendNumber13() {
		return trendNumber13;
	}


	public void setTrendNumber13(String trendNumber13) {
		this.trendNumber13 = trendNumber13;
	}


	public String getTrendNumber14() {
		return trendNumber14;
	}


	public void setTrendNumber14(String trendNumber14) {
		this.trendNumber14 = trendNumber14;
	}


	public String getTrendNumber15() {
		return trendNumber15;
	}


	public void setTrendNumber15(String trendNumber15) {
		this.trendNumber15 = trendNumber15;
	}


	public String getTrendNumber16() {
		return trendNumber16;
	}


	public void setTrendNumber16(String trendNumber16) {
		this.trendNumber16 = trendNumber16;
	}


	public String getTrendNumber17() {
		return trendNumber17;
	}


	public void setTrendNumber17(String trendNumber17) {
		this.trendNumber17 = trendNumber17;
	}


	public String getTrendNumber18() {
		return trendNumber18;
	}


	public void setTrendNumber18(String trendNumber18) {
		this.trendNumber18 = trendNumber18;
	}


	public String getTrendNumber19() {
		return trendNumber19;
	}


	public void setTrendNumber19(String trendNumber19) {
		this.trendNumber19 = trendNumber19;
	}


	public String getTrendNumber20() {
		return trendNumber20;
	}


	public void setTrendNumber20(String trendNumber20) {
		this.trendNumber20 = trendNumber20;
	}


	public int getTrendsCount() {
		return trendsCount;
	}


	public void setTrendsCount(int trendsCount) {
		this.trendsCount = trendsCount;
	}


	public int getCosineSimilarityCalculatonVariable() {
		return cosineSimilarityCalculatonVariable;
	}


	public void setCosineSimilarityCalculatonVariable(int cosineSimilarityCalculatonVariable) {
		this.cosineSimilarityCalculatonVariable = cosineSimilarityCalculatonVariable;
	}


	public int getSpamCount() {
		return spamCount;
	}


	public void setSpamCount(int spamCount) {
		this.spamCount = spamCount;
	}


	public int getNotSpamCount() {
		return notSpamCount;
	}


	public void setNotSpamCount(int notSpamCount) {
		this.notSpamCount = notSpamCount;
	}


	public String getEliminationReason() {
		return eliminationReason;
	}


	public void setEliminationReason(String eliminationReason) {
		this.eliminationReason = eliminationReason;
	}


	public String getUrlText() {
		return urlText;
	}


	public void setUrlText(String urlText) {
		this.urlText = urlText;
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
		trendsCount = 0;
		notSpamModel.clear();
		spamModel.clear();
		trendNumberOne = " ";
		trendNumberTwo = " ";
		trendNumberThree = " ";
		trendNumberFour = " ";
		trendNumberFive = " ";
		trendNumberSix = " ";
		trendNumberSeven = " ";
		trendNumberEight = " ";
		trendNumberNine = " ";
		trendNumberTen = " ";
		topSimilarTrends.clear();
		trendDatabaseList.clear();
		cosineSimilarityCalculatonVariable = 1;
		trendDatabaseList.clear();
		trendTopicList.clear();
		cosineSimilarityArray.clear();
		topSimilarTrends.clear();
		spamCount = 0;
		notSpamCount = 0;
		eliminationReason = " ";
		location = " ";
		organization = " ";
		person = " ";
		language = " ";
		hashtag = " ";
		url = " ";
		mostcommon = " ";
		urlText = " ";

		//trendTopicList.clear();

	}




}
