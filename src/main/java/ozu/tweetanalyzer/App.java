package ozu.tweetanalyzer;

import java.util.Scanner;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import controller.ChartController;
import model.ChartModel;
import model.DatabaseModel;

import view.ChartView;


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
		//getQuerySearchKey(database);
		stream(appFrame,authorize,database,recognition,spamDetector,time);

	}



	public static void stream(ApplicationFrame appFrame,TwitterAuthorization authorize,final DatabaseModel database, final EntityRecognition recognition,final SpamDetector spamDetector, final CurrentTime currentTime)
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


		ChartPanel locationChartPanel = new ChartPanel(locationController.getChart());
		ChartPanel organizationChartPanel = new ChartPanel(organizationController.getChart());
		ChartPanel personChartPanel = new ChartPanel(personController.getChart());
		ChartPanel languageChartPanel = new ChartPanel(languageController.getChart());
		ChartPanel hashtagChartPanel = new ChartPanel(hashtagController.getChart());
		ChartPanel urlChartPanel = new ChartPanel(urlController.getChart());


		Stream stream = new Stream();

		SearchPanel searchPanel = new SearchPanel();
		JPanel search = searchPanel.populateSearchPanel(stream,authorize, database, recognition, spamDetector, currentTime, locationController,
				organizationController, personController, languageController, hashtagController, urlController);


		appFrame.populateApplication(search,locationChartPanel,organizationChartPanel,personChartPanel,languageChartPanel,hashtagChartPanel, urlChartPanel);
		appFrame.pack();
		RefineryUtilities.centerFrameOnScreen(appFrame);
		appFrame.setVisible(true);







	}





	public static void getQuerySearchKey(DatabaseModel database) 
	{
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a query: ");
		String searchQuery = reader.nextLine();
		database.setSearchQuery(searchQuery);
		reader.close();

	}

}
