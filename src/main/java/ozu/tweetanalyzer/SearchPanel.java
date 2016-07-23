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
	private TrendPanel trendModel= new TrendPanel();


	public JPanel populateSearchPanel(final Search search,final Stream stream,final TwitterAuthorization authorize,final DatabaseModel database, final EntityRecognition recognition,final SpamDetector spamDetector, final CurrentTime currentTime,
			final MapController mapController,final ChartController locationController, final ChartController organizationController,final ChartController personController,final ChartController languageController,final ChartController hashtagController,final ChartController urlController){

		label.setBackground(Color.blue);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		controlPanel.add(namelabel);
		controlPanel.add(userText);	     
		controlPanel.add(searchButton);
		controlPanel.add(refreshButton);
		controlPanel.add(label);
		controlPanel.add(trendModel);


		trendModel.list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {

					String selectedName = (String) trendModel.list.getSelectedValue();
					userText.setText(selectedName);

				}

			}

		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     
				database.setSearchQuery(userText.getText());
				stream.startStream(authorize, database, recognition, spamDetector, currentTime, mapController,locationController, organizationController, personController, languageController, hashtagController, urlController);
				search.searchRecentlyRelatedTweets(spamDetector, currentTime, database, authorize, recognition, mapController, locationController, organizationController, personController, languageController, hashtagController, urlController);
				searchButton.setEnabled(false);

			}
		}); 

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				stream.getStream().cleanUp(); // shutdown internal stream consuming thread
				stream.getStream().shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
				database.getHashTagList().clear();
				database.getLocationList().clear();
				database.getOrganizationList().clear();
				database.getPersonList().clear();
				database.getLanguageList().clear();
				database.getVerifiedURLList().clear();
				database.setSearchQuery("");
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











}
