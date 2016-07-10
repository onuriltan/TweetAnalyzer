package ozu.tweetanalyzer;

import java.util.Hashtable;


public class Database {
	
	private String searchQuery;
	private Hashtable<String, Integer> locationList = new Hashtable<String, Integer>()  ;
	private Hashtable<String, Integer> organizationList = new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> personList= new Hashtable<String, Integer>() ;
	private Hashtable<String, Integer> languageList = new Hashtable<String, Integer>() ;
	

	public Database(){



	}

	public Hashtable<String, Integer> getLocationList() {
		return locationList;
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




}
