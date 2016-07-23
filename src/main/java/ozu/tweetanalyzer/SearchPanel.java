package ozu.tweetanalyzer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.ChartController;
import controller.MapController;
import model.DatabaseModel;

public class SearchPanel  {

	private JLabel  namelabel= new JLabel("Enter query: ", JLabel.RIGHT);
	private JPanel controlPanel = new JPanel();	      
	private final JTextField userText = new JTextField(10);	        
	private JButton searchButton = new JButton("SEARCH");	  
	private JButton refreshButton = new JButton("REFRESH");	
	private JLabel label = new JLabel("<html>This is a tool to analyze real-time tweets. You need to enter a query that can be a specific event<BR>and after you enter the search button user can see the tweets visualization from world map<BR>and also user can see the names, organizations, locations and tweets<BR>languages as a graph. This tool uses Stanford named entity recognizer tool to identify tweets and recognizes<BR>which names, locations and organization names mentioned in a tweet, and if Twitter user shared his/her location,<BR> user also can see wheretweet tweeted in world map as a visualization.</html>");
	private JLabel tweetCountlabel;
	private TrendPanel trendPanel;

	public SearchPanel(TrendPanel trendPanel){
		this.trendPanel = trendPanel;
	}

	public JPanel populateSearchPanel(final Search search,final Stream stream,final DatabaseModel database, final EntityRecognition recognition,final SpamDetector spamDetector, final CurrentTime currentTime,
			final MapController mapController,final ChartController locationController, final ChartController organizationController,final ChartController personController,final ChartController languageController,final ChartController hashtagController,final ChartController urlController, ChartController allWordsController){

		label.setBackground(Color.blue);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);

		/*ChartPanel locationChartPanel = new ChartPanel(locationController.getChart());
		locationChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
		ChartPanel organizationChartPanel = new ChartPanel(organizationController.getChart());
		organizationChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
		ChartPanel personChartPanel = new ChartPanel(personController.getChart());
		personChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
		ChartPanel languageChartPanel = new ChartPanel(languageController.getChart());
		languageChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
		ChartPanel hashtagChartPanel = new ChartPanel(hashtagController.getChart());
		hashtagChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));
		ChartPanel urlChartPanel = new ChartPanel(urlController.getChart());
		urlChartPanel.setPreferredSize(new java.awt.Dimension(200, 200));*/
		tweetCountlabel = new JLabel("<html>Tweet count: "+database.getTweetCount()+"<html>");
		tweetCountlabel.setVerticalTextPosition(JLabel.BOTTOM);
		tweetCountlabel.setHorizontalTextPosition(JLabel.RIGHT);
		controlPanel.add(namelabel);
		controlPanel.add(userText);	     
		controlPanel.add(searchButton);
		controlPanel.add(refreshButton);
		controlPanel.add(label);
		controlPanel.add(trendPanel);
		controlPanel.add(tweetCountlabel);
		/*controlPanel.add(locationChartPanel);
		controlPanel.add(organizationChartPanel);
		controlPanel.add(personChartPanel);
		controlPanel.add(languageChartPanel);
		controlPanel.add(hashtagChartPanel);
		controlPanel.add(urlChartPanel);*/



		trendPanel.list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					String selectedName = (String) trendPanel.list.getSelectedValue();
					userText.setText(selectedName);
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     
				database.setSearchQuery(userText.getText());
				stream.startStream(database, recognition, spamDetector, currentTime, mapController,locationController, organizationController, personController, languageController, hashtagController, urlController,allWordsController);
				search.searchRecentlyRelatedTweets(spamDetector, currentTime, database, recognition, mapController, locationController, organizationController, personController, languageController, hashtagController, urlController, allWordsController);
				searchButton.setEnabled(false);

			}
		}); 

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				stream.getStream().cleanUp(); // shutdown internal stream consuming thread
				stream.getStream().shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
				database.clearDatabase();
				mapController.getMap().removeAllMapMarkers();
				mapController.setText("");
				mapController.getTwitterStreamPanel().setText("");
				locationController.getPlot().setDataset(null);
				locationController.setDataset(null);
				organizationController.getPlot().setDataset(null);
				organizationController.setDataset(null);
				personController.getPlot().setDataset(null);
				personController.setDataset(null);
				languageController.getPlot().setDataset(null);
				languageController.setDataset(null);
				hashtagController.getPlot().setDataset(null);
				hashtagController.setDataset(null);
				urlController.getPlot().setDataset(null);
				urlController.setDataset(null);
				allWordsController.getPlot().setDataset(null);
				allWordsController.setDataset(null);
				tweetCountlabel.setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
				searchButton.setEnabled(true);


			}
		});
		return controlPanel;


	}


	public JLabel getNamelabel() {
		return namelabel;
	}



	public void setNamelabel(JLabel namelabel) {
		this.namelabel = namelabel;
	}



	public JPanel getControlPanel() {
		return controlPanel;
	}



	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}



	public JButton getLoginButton() {
		return searchButton;
	}



	public void setLoginButton(JButton loginButton) {
		this.searchButton = loginButton;
	}



	public JButton getRestartButton() {
		return refreshButton;
	}



	public void setRestartButton(JButton restartButton) {
		this.refreshButton = restartButton;
	}



	public JLabel getLabel() {
		return label;
	}



	public void setLabel(JLabel label) {
		this.label = label;
	}


	public JTextField getUserText() {
		return userText;
	}

	public JLabel getTweetCountlabel() {
		return tweetCountlabel;
	}

	public void setTweetCountlabel(JLabel tweetCountlabel) {
		this.tweetCountlabel = tweetCountlabel;
	}











}
