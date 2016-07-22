package ozu.tweetanalyzer;


import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
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
		ApplicationFrame appFrame = new ApplicationFrame();
		CurrentTime time = new CurrentTime();
		DatabaseModel database = new DatabaseModel();
		TwitterAuthorization authorize = new TwitterAuthorization();  // to get twitter API working
		EntityRecognition recognition = new EntityRecognition(database);
		SpamDetector spamDetector = new SpamDetector();
		Stream stream = new Stream();
		SearchPanel searchPanel = new SearchPanel();
		TweetLocationFinder getLocationFromAccountInfo = new TweetLocationFinder();
		stream(getLocationFromAccountInfo,searchPanel,stream,appFrame,authorize,database,recognition,spamDetector,time);

	}



	public static void stream(TweetLocationFinder getLocationFromAccountInfo,SearchPanel searchPanel,Stream stream,ApplicationFrame appFrame,TwitterAuthorization authorize, DatabaseModel database,  EntityRecognition recognition, SpamDetector spamDetector,  CurrentTime currentTime)
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
		verifiedUrlChartModel.setChartName("VerfiedURLs");
		ChartController urlController = new ChartController(verifiedUrlChartModel, new ChartView());
		urlController.populateChart();

		MapModel mapModel = new MapModel();
		MapController mapController = new MapController(mapModel, new MapView(mapModel,getLocationFromAccountInfo));
		mapController.populateMap();

		JMapViewer mapPanel = mapController.getMap();
		ChartPanel locationChartPanel = new ChartPanel(locationController.getChart());
		ChartPanel organizationChartPanel = new ChartPanel(organizationController.getChart());
		ChartPanel personChartPanel = new ChartPanel(personController.getChart());
		ChartPanel languageChartPanel = new ChartPanel(languageController.getChart());
		ChartPanel hashtagChartPanel = new ChartPanel(hashtagController.getChart());
		ChartPanel urlChartPanel = new ChartPanel(urlController.getChart());

		
		Search searchRecentTweets = new Search();
		JPanel search = searchPanel.populateSearchPanel(searchRecentTweets,stream,authorize, database, recognition, spamDetector, currentTime, mapController,locationController,
				organizationController, personController, languageController, hashtagController, urlController);

		appFrame.populateApplication(search,mapPanel,locationChartPanel,organizationChartPanel,personChartPanel,languageChartPanel,hashtagChartPanel, urlChartPanel);
		appFrame.pack();
		RefineryUtilities.centerFrameOnScreen(appFrame);
		appFrame.setVisible(true);



	}


}
