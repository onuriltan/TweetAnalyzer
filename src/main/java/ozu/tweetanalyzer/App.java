package ozu.tweetanalyzer;

import java.util.Scanner;


import controller.ChartController;
import model.ChartModel;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import view.ChartView;


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





	public static void stream(TwitterAuthorization authorize,final Database database, final EntityRecognition recognition,final CurrentTime time)
	{


		final ChartModel locationChartModel = new ChartModel();
		locationChartModel.setChartName("Location");

		final ChartModel organizationChartModel = new ChartModel();
		organizationChartModel.setChartName("Organization");

		final ChartModel personChartModel = new ChartModel();
		personChartModel.setChartName("Person");

		final ChartModel languageChartModel = new ChartModel();
		languageChartModel.setChartName("Language");

		final ChartModel hashtagChartModel = new ChartModel();
		hashtagChartModel.setChartName("Hashtag");

		final ChartModel verifiedUrlChartModel = new ChartModel();
		verifiedUrlChartModel.setChartName("VerfiedURLs");


		final ChartController locationController = new ChartController(locationChartModel, new ChartView());
		final ChartController organizationController = new ChartController(organizationChartModel, new ChartView());
		final ChartController personController = new ChartController(personChartModel, new ChartView());
		final ChartController languageController = new ChartController(languageChartModel, new ChartView());
		final ChartController hashtagController = new ChartController(hashtagChartModel, new ChartView());
		final ChartController urlController = new ChartController(verifiedUrlChartModel, new ChartView());

		locationController.populateChart();
		organizationController.populateChart();
		personController.populateChart();
		languageController.populateChart();
		hashtagController.populateChart();
		urlController.populateChart();

		/*


		final ChartGeneration locationChart = new ChartGeneration("Location",time);
		locationChart.pack();
		RefineryUtilities.centerFrameOnScreen(locationChart);
		locationChart.setVisible(true);//CREATE CHARTS WHEN STREAM STARTS AND SHOW THEM

		final ChartGeneration organizationChart = new ChartGeneration("Organization",time);
		organizationChart.pack();
		RefineryUtilities.centerFrameOnScreen(organizationChart);
		organizationChart.setVisible(true);

		final ChartGeneration personChart = new ChartGeneration("Person",time);
		personChart.pack();
		RefineryUtilities.centerFrameOnScreen(personChart);
		personChart.setVisible(true);

		final ChartGeneration languageChart = new ChartGeneration("Language",time);
		languageChart.pack();
		RefineryUtilities.centerFrameOnScreen(languageChart);
		languageChart.setVisible(true);

		final ChartGeneration hashtagChart = new ChartGeneration("Hashtag",time);
		hashtagChart.pack();
		RefineryUtilities.centerFrameOnScreen(hashtagChart);
		hashtagChart.setVisible(true);

		final ChartGeneration verifiedUrlChart = new ChartGeneration("VerfiedURLs",time);
		verifiedUrlChart.pack();
		RefineryUtilities.centerFrameOnScreen(verifiedUrlChart);
		verifiedUrlChart.setVisible(true);

		final Deneme deneme = new Deneme("Deneme",time, database.getLocationList());
		deneme.pack();
		RefineryUtilities.centerFrameOnScreen(deneme);
		deneme.setVisible(true);
		 */

		StatusListener listener = new StatusListener() {
			public void onStatus(Status tweet) {// data keep coming to onStatus method, 
				// the code that written under onStatus method will execute the code again and again when new tweet comes

				recognition.entityRecognition(tweet,locationController,organizationController,personController,languageController,hashtagController,urlController); // apply entity recognition on tweet text
				//this command takes tweets and analyzes them and updates charts according to analyzation

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



	public static void getQuerySearchKey(Database database) 
	{
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a query: ");
		String searchQuery = reader.nextLine();
		database.setSearchQuery(searchQuery);
		reader.close();

	}

}