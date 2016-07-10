package ozu.tweetanalyzer;

import java.util.Scanner;

import org.jfree.ui.RefineryUtilities;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;


public class App 
{



	public static void main(String[] args)
	{
		CurrentTime time = new CurrentTime();
		Database database = new Database();
		TwitterAuthorization authorize = new TwitterAuthorization();  // to get twitter API working
		EntityRecognition recognition = new EntityRecognition(database);
		getQuerySearchKey(database);
		stream(authorize,database,recognition,time);



	}

	public static void getQuerySearchKey(Database database) 
	{
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a query: ");
		String searchQuery = reader.nextLine();
		database.setSearchQuery(searchQuery);
		reader.close();

	}



	public static void stream(TwitterAuthorization authorize,final Database database, final EntityRecognition recognition,final CurrentTime time)
	{
	
		final ChartGeneration locationChart = new ChartGeneration("Location",time,database.getLocationList());
		locationChart.pack();
        RefineryUtilities.centerFrameOnScreen(locationChart);
        locationChart.setVisible(true);//CREATE CHARTS WHEN STREAM STARTS AND SHOW THEM
		
		final ChartGeneration organizationChart = new ChartGeneration("Organization",time, database.getOrganizationList());
		organizationChart.pack();
        RefineryUtilities.centerFrameOnScreen(organizationChart);
        organizationChart.setVisible(true);
		
		final ChartGeneration personChart = new ChartGeneration("Person",time, database.getPersonList());
		personChart.pack();
        RefineryUtilities.centerFrameOnScreen(personChart);
        personChart.setVisible(true);
		
		final ChartGeneration languageChart = new ChartGeneration("Language",time, database.getLanguageList());
		languageChart.pack();
        RefineryUtilities.centerFrameOnScreen(languageChart);
        languageChart.setVisible(true);
        
        final Deneme deneme = new Deneme("Deneme");
        deneme.pack();
		RefineryUtilities.centerFrameOnScreen(deneme);
		deneme.setVisible(true);
        

		StatusListener listener = new StatusListener() {
			public void onStatus(Status arg0) {// data keep coming to onStatus method, 
				                               // the code that written under onStatus method will execute the code again and again when new tweet comes

				recognition.entityRecognition(arg0,time,locationChart,organizationChart,personChart,languageChart,deneme); // apply entity recognition on tweet text
			
				



			}
			public void onException(Exception arg0) {}
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
			public void onScrubGeo(long arg0, long arg1) {}
			public void onStallWarning(StallWarning arg0) {}
			public void onTrackLimitationNotice(int arg0) {}

		};
		TwitterStreamFactory tf = new TwitterStreamFactory(authorize.getCb().build());
		TwitterStream stream = tf.getInstance();
		stream.addListener(listener);

		FilterQuery filtre = new FilterQuery();
		String[] keywordsArray = { database.getSearchQuery() }; //filter based on choice of keywords
		filtre.track(keywordsArray);
		stream.filter(filtre);    

	}


}
