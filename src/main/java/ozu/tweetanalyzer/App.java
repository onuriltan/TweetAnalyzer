package ozu.tweetanalyzer;


import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.jfree.ui.RefineryUtilities;
import controller.ChartController;
import controller.MapController;
import model.ChartModel;
import model.DatabaseModel;
import model.MapModel;
import view.ChartView;
import view.MapView;


public class App 
{


	public static void main(String[] args)
	{
		CurrentTime time = new CurrentTime();
	    Calendar cal = time.getCal();
		DatabaseModel database = new DatabaseModel();
		EntityRecognition recognition = new EntityRecognition(database);
		TrendPanel trendPanel= new TrendPanel();
		SearchPanel searchPanel = new SearchPanel(trendPanel,cal);
		SpamDetector spamDetector = new SpamDetector();
		Search searchRecentTweets = new Search(searchPanel);
		ApplicationMainFrame appFrame = new ApplicationMainFrame();
		Stream stream = new Stream(searchPanel);
		TweetLocationFinder getLocationFromAccountInfo = new TweetLocationFinder();
		startMVCPattern(getLocationFromAccountInfo,searchPanel,searchRecentTweets,stream,appFrame,database,recognition,spamDetector,time);

	}



	public static void startMVCPattern(TweetLocationFinder getLocationFromAccountInfo,SearchPanel searchPanel,Search searchRecentTweets, Stream stream,ApplicationMainFrame appFrame, DatabaseModel database,  EntityRecognition recognition, SpamDetector spamDetector,  CurrentTime currentTime)
	{


		ChartModel locationChartModel = new ChartModel();
		locationChartModel.setChartName("Location");
		ChartController locationController = new ChartController(locationChartModel, new ChartView());
		locationController.populateChart();

		ChartModel organizationChartModel = new ChartModel();
		organizationChartModel.setChartName("Organization");
		ChartController organizationController = new ChartController(organizationChartModel, new ChartView());
		organizationController.populateChart();

		ChartModel personChartModel = new ChartModel();
		personChartModel.setChartName("Person");
		ChartController personController = new ChartController(personChartModel, new ChartView());
		personController.populateChart();

		ChartModel languageChartModel = new ChartModel();
		languageChartModel.setChartName("Language");
		ChartController languageController = new ChartController(languageChartModel, new ChartView());
		languageController.populateChart();

		ChartModel hashtagChartModel = new ChartModel();
		hashtagChartModel.setChartName("Hashtag");
		ChartController hashtagController = new ChartController(hashtagChartModel, new ChartView());
		hashtagController.populateChart();

		ChartModel verifiedUrlChartModel = new ChartModel();
		verifiedUrlChartModel.setChartName("URLs");
		ChartController urlController = new ChartController(verifiedUrlChartModel, new ChartView());
		urlController.populateChart();

		ChartModel allWordsChartModel = new ChartModel();
		allWordsChartModel.setChartName("MostCommonWords");
		ChartController allWordsController = new ChartController(allWordsChartModel, new ChartView());
		allWordsController.populateChart();

		MapModel mapModel = new MapModel();
		MapController mapController = new MapController(mapModel, new MapView(mapModel,getLocationFromAccountInfo));
		mapController.populateMap();

		JSplitPane mapPanel = mapController.getPanel();


		database.setTweetCount(0);
		searchPanel.populateSearchPanel(searchRecentTweets,stream, database, recognition, spamDetector, currentTime, mapController,locationController,
				organizationController, personController, languageController, hashtagController, urlController, allWordsController);

		
		appFrame.populateApplication(searchPanel,mapPanel,locationController.getChartPanel(),organizationController.getChartPanel(), personController.getChartPanel(), languageController.getChartPanel(), hashtagController.getChartPanel(), urlController.getChartPanel(),allWordsController.getChartPanel());
		appFrame.pack();
		RefineryUtilities.centerFrameOnScreen(appFrame);
		appFrame.setVisible(true);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}
