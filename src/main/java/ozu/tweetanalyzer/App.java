package ozu.tweetanalyzer;


import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.jfree.ui.RefineryUtilities;
import controller.ChartController;
import controller.CosineSimilarityPanelController;
import controller.MapController;
import model.ChartModel;
import model.CosineSimilarityPanelModel;
import model.DatabaseModel;
import model.MapModel;
import view.ChartView;
import view.CosineSimilarityPanelView;
import view.MapView;


public class App 
{


	public static void main(String[] args)
	{
		CurrentTime time = new CurrentTime();
		Calendar cal = time.getCal();
		DatabaseModel database = new DatabaseModel();
		EntityRecognition recognition = new EntityRecognition(database);
		SpamDetector spamDetector = new SpamDetector();
		TrendPanel trendPanel= new TrendPanel(database);
		CosineSimilarityStream cosineStream = new CosineSimilarityStream(database,time,spamDetector);
		SearchPanel searchPanel = new SearchPanel(trendPanel,cal);
		Search searchRecentTweets = new Search(searchPanel);
		ApplicationMainFrame appFrame = new ApplicationMainFrame();
		Stream stream = new Stream(searchPanel);
		TweetLocationFinder getLocationFromAccountInfo = new TweetLocationFinder();
		startMVCPattern(cosineStream,getLocationFromAccountInfo,searchPanel,searchRecentTweets,stream,appFrame,database,recognition,spamDetector,time);

	}



	public static void startMVCPattern(CosineSimilarityStream cosineStream,TweetLocationFinder getLocationFromAccountInfo,SearchPanel searchPanel,Search searchRecentTweets, Stream stream,ApplicationMainFrame appFrame, DatabaseModel database,  EntityRecognition recognition, SpamDetector spamDetector,  CurrentTime currentTime)
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

	
		
		CosineSimilarityPanelModel cosinePanelModel = new CosineSimilarityPanelModel();
		CosineSimilarityPanelView  cosineSimilarityPanelView = new CosineSimilarityPanelView(cosinePanelModel);
		cosineSimilarityPanelView.populateCosinePanel();
		CosineSimilarityPanelController  cosineController = new CosineSimilarityPanelController(cosinePanelModel,cosineSimilarityPanelView);

		database.setTweetCount(0);
		searchPanel.populateSearchPanel(cosineStream,searchRecentTweets,stream, database, recognition, spamDetector, currentTime, mapController,locationController,
				organizationController, personController, languageController, hashtagController, urlController, allWordsController,cosineController);


		appFrame.populateApplication(cosineSimilarityPanelView,searchPanel,mapController.getPanel(),locationController.getSplitPane(),organizationController.getSplitPane(), personController.getSplitPane(), languageController.getSplitPane(), hashtagController.getSplitPane(), urlController.getSplitPane(),allWordsController.getSplitPane());
		appFrame.pack();
		RefineryUtilities.centerFrameOnScreen(appFrame);
		appFrame.setVisible(true);
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}
