package ozu.tweetanalyzer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import org.jfree.chart.ChartUtilities;
import controller.ChartController;
import controller.MapController;
import model.DatabaseModel;

public class SearchPanel extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel enterQueryLabel= new JLabel("Enter query: ", JLabel.RIGHT);
	private final JTextField userText = new JTextField(10);	        
	private JButton searchButton = new JButton("SEARCH");	  
	private JButton refreshButton = new JButton("REFRESH");	
	private JButton resultButton = new JButton("GENERATE RESULTS");	
	private JLabel label = new JLabel("<html>This is a tool to analyze real-time tweets. You need to enter a query that can be a specific event<BR>and after you enter the search button user can see the tweets visualization from world map<BR>and also user can see the names, organizations, locations and tweets<BR>languages as a graph. This tool uses Stanford named entity recognizer tool to identify tweets and recognizes<BR>which names, locations and organization names mentioned in a tweet, and if Twitter user shared his/her location,<BR> user also can see wheretweet tweeted in world map as a visualization.</html>");
	private JLabel tweetCountlabel;
	private TrendPanel trendPanel;
	private Calendar cal;
	private JPanel spamPanel;
	private JPanel notSpamPanel;
	private JSplitPane splitPanel;
	private JScrollPane spamScrollPane;
	private JScrollPane notSpamScrollPane;


	public SearchPanel(TrendPanel trendPanel, Calendar cal){
		this.trendPanel = trendPanel;
		this.cal = cal;
	}

	public void populateSearchPanel(final Search search,final Stream stream,final DatabaseModel database, final EntityRecognition recognition,final SpamDetector spamDetector, final CurrentTime currentTime,
			final MapController mapController,final ChartController locationController, final ChartController organizationController,final ChartController personController,final ChartController languageController,final ChartController hashtagController,final ChartController urlController, final ChartController allWordsController){

		label.setBackground(Color.blue);
		setBackground(Color.white);
		tweetCountlabel = new JLabel("<html>Tweet count: "+database.getTweetCount()+"<html>");

		spamPanel = new JPanel();
		notSpamPanel = new JPanel();
		spamPanel.setLayout(new BorderLayout());
		notSpamPanel.setLayout(new BorderLayout());
	
		spamScrollPane = new JScrollPane(database.getSpamList());

		TitledBorder border = new TitledBorder(new LineBorder(Color.WHITE, 1),"SPAMS");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		border.setTitleColor(Color.RED);
		spamScrollPane.setBorder(border);
		spamScrollPane.setPreferredSize((new Dimension(400,250)));
		spamScrollPane.setBackground(Color.white);
		spamPanel.setPreferredSize((new Dimension(400,250)));
		spamPanel.setBackground(Color.white);
		spamPanel.add(spamScrollPane, BorderLayout.NORTH);



		notSpamScrollPane = new JScrollPane(database.getNotSpamList());
	
		TitledBorder border1 = new TitledBorder(new LineBorder(Color.WHITE, 1),"PASSED");
		border1.setTitleJustification(TitledBorder.CENTER);
		border1.setTitlePosition(TitledBorder.TOP);
		border1.setTitleColor(Color.GREEN);
		notSpamScrollPane.setBorder(border1);
		notSpamScrollPane.setPreferredSize((new Dimension(400,250)));
		notSpamPanel.setPreferredSize((new Dimension(400,250)));
		notSpamPanel.setBackground(Color.white);
		notSpamScrollPane.setBackground(Color.white);

		notSpamPanel.add(notSpamScrollPane, BorderLayout.NORTH);

		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notSpamPanel, spamPanel);
		splitPanel.setResizeWeight(0.5);

		add(enterQueryLabel);
		add(userText);	     
		add(searchButton);
		add(refreshButton);
		add(resultButton);
		add(label);
		add(trendPanel);
		add(tweetCountlabel);
		add(splitPanel);
		
		resultButton.setEnabled(false);
	


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
				//search.searchRecentlyRelatedTweets(spamDetector, currentTime, database, recognition, mapController, locationController, organizationController, personController, languageController, hashtagController, urlController, allWordsController);
				resultButton.setEnabled(true);
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
				resultButton.setEnabled(false);

			}
		});

		resultButton.addActionListener(new ActionListener(){


			public void actionPerformed(ActionEvent arg0) {
				//locationController, organizationController, personController, languageController, hashtagController, urlController,allWordsController

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH;mm;ss");
				Date date = new Date();
				String mainFile = "results";
				String file = dateFormat.format(cal.getTime())+" to "+dateFormat.format(date);

				File locationfile = new File(mainFile+"/"+file+"/Location.png");
				File organizationfile = new File(mainFile+"/"+file+"/Organization.png");
				File personfile = new File(mainFile+"/"+file+"/Person.png");
				File languagefile = new File(mainFile+"/"+file+"/Language.png");
				File hashtagfile = new File(mainFile+"/"+file+"/Hashtags.png");
				File allWordsfile = new File(mainFile+"/"+file+"/MostCommonWords.png");
				File parentDir = locationfile.getParentFile();
				File parentDir1 = organizationfile.getParentFile();
				File parentDir2 = personfile.getParentFile();
				File parentDir3 = languagefile.getParentFile();
				File parentDir4 = hashtagfile.getParentFile();
				File parentDir5 = allWordsfile.getParentFile();
				if(! parentDir.exists() && ! parentDir1.exists()&& ! parentDir2.exists()&& ! parentDir3.exists()&& ! parentDir4.exists()&& ! parentDir5.exists()){ 
					parentDir.mkdirs();
					parentDir1.mkdirs();
					parentDir2.mkdirs();
					parentDir3.mkdirs();
					parentDir4.mkdirs();
					parentDir5.mkdirs();
				}
				// create parent dir and ancestors if necessary
				// FileWriter does not allow to specify charset, better use this:
				Writer w = null;
				Writer w1= null;
				Writer w2= null;
				Writer w3= null;
				Writer w4= null;
				Writer w5= null;


				try {
					w = new OutputStreamWriter(new FileOutputStream(locationfile));
					w1 = new OutputStreamWriter(new FileOutputStream(organizationfile));
					w2 = new OutputStreamWriter(new FileOutputStream(personfile));
					w3 = new OutputStreamWriter(new FileOutputStream(languagefile));
					w4 = new OutputStreamWriter(new FileOutputStream(hashtagfile));
					w5 = new OutputStreamWriter(new FileOutputStream(allWordsfile));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				try {
					ChartUtilities.saveChartAsJPEG(locationfile, locationController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(organizationfile, organizationController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(personfile, personController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(languagefile, languageController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(hashtagfile, hashtagController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(allWordsfile, allWordsController.getChart(), 600, 400);
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					w.close();
					w1.close();
					w2.close();
					w3.close();
					w4.close();
					w5.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}


			}

		});



	}




	public JLabel getNamelabel() {
		return enterQueryLabel;
	}



	public void setNamelabel(JLabel namelabel) {
		this.enterQueryLabel = namelabel;
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

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JButton getResultButton() {
		return resultButton;
	}

	public void setResultButton(JButton resultButton) {
		this.resultButton = resultButton;
	}

	public TrendPanel getTrendPanel() {
		return trendPanel;
	}

	public void setTrendPanel(TrendPanel trendPanel) {
		this.trendPanel = trendPanel;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}


	public JLabel getEnterQueryLabel() {
		return enterQueryLabel;
	}

	public void setEnterQueryLabel(JLabel enterQueryLabel) {
		this.enterQueryLabel = enterQueryLabel;
	}

	public JPanel getSpamPanel() {
		return spamPanel;
	}

	public void setSpamPanel(JPanel spamPanel) {
		this.spamPanel = spamPanel;
	}

	public JPanel getNotSpamPanel() {
		return notSpamPanel;
	}

	public void setNotSpamPanel(JPanel notSpamPanel) {
		this.notSpamPanel = notSpamPanel;
	}

	public JSplitPane getSplitPanel() {
		return splitPanel;
	}

	public void setSplitPanel(JSplitPane splitPanel) {
		this.splitPanel = splitPanel;
	}

	public JScrollPane getSpamScrollPane() {
		return spamScrollPane;
	}

	public void setSpamScrollPane(JScrollPane spamScrollPane) {
		this.spamScrollPane = spamScrollPane;
	}

	public JScrollPane getNotSpamScrollPane() {
		return notSpamScrollPane;
	}

	public void setNotSpamScrollPane(JScrollPane notSpamScrollPane) {
		this.notSpamScrollPane = notSpamScrollPane;
	}













}
