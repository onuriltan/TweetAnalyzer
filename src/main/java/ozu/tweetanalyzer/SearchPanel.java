package ozu.tweetanalyzer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import controller.CosineSimilarityPanelController;
import controller.MapController;
import controller.UrlController;
import model.DatabaseModel;

public class SearchPanel extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel enterQueryLabel= new JLabel("Enter query: ", JLabel.RIGHT);
	private JTextField userText = new JTextField();	
	private JButton searchButton = new JButton("SEARCH");	  
	private JButton refreshButton = new JButton("REFRESH");	
	private JButton resultButton = new JButton("GENERATE RESULTS");	
	private JLabel tweetCountlabel;
	private JLabel whyTweetIsSpam;
	private TrendPanel trendPanel;
	private Calendar cal;
	private JPanel spamPanel;
	private JPanel notSpamPanel;
	private JSplitPane splitPanel;
	private JScrollPane spamScrollPane;
	private JScrollPane notSpamScrollPane;
	private TitledBorder spamTitle;
	private TitledBorder notSpamTitle;
	public SearchPanel(TrendPanel trendPanel, Calendar cal){
		this.trendPanel = trendPanel;
		this.cal = cal;
	}

	public void populateSearchPanel(final CosineSimilarityStream cosineStream,final Search search,final Stream stream,final DatabaseModel database, final EntityRecognition recognition,final SpamDetector spamDetector, final CurrentTime currentTime,
			final MapController mapController,final ChartController locationController, final ChartController organizationController,final ChartController personController,final ChartController languageController,final ChartController hashtagController,final UrlController urlController, final ChartController allWordsController
			,final CosineSimilarityPanelController  cosineController){


		this.setLayout(new BorderLayout());
		userText.setPreferredSize( new Dimension( 200, 24 ) );
		this.setBackground(Color.white);
		tweetCountlabel = new JLabel("Tweet count: "+database.getTweetCount());
		tweetCountlabel.setPreferredSize( new Dimension( 100, 24 ) );
		whyTweetIsSpam = new JLabel(" ");
		whyTweetIsSpam.setPreferredSize( new Dimension( 1000, 24 ) );

		spamPanel = new JPanel();
		notSpamPanel = new JPanel();
		spamPanel.setLayout(new BorderLayout());
		notSpamPanel.setLayout(new BorderLayout());
		spamScrollPane = new JScrollPane(database.getSpamList());
		spamTitle = new TitledBorder(new LineBorder(Color.WHITE, 1),"SPAMS");
		spamTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
		spamTitle.setTitleJustification(TitledBorder.CENTER);
		spamTitle.setTitlePosition(TitledBorder.TOP);
		Color red = new Color(255, 0, 0);
		spamTitle.setTitleColor(red);
		spamScrollPane.setBorder(spamTitle);
		spamScrollPane.setPreferredSize((new Dimension(400,500)));
		spamScrollPane.setBackground(Color.white);
		spamPanel.setPreferredSize((new Dimension(400,500)));
		spamPanel.setBackground(Color.white);
		spamPanel.add(spamScrollPane, BorderLayout.NORTH);

		notSpamScrollPane = new JScrollPane(database.getNotSpamList());
		notSpamTitle = new TitledBorder(new LineBorder(Color.WHITE, 1),"PASSED");
		notSpamTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
		notSpamTitle.setTitleJustification(TitledBorder.CENTER);
		notSpamTitle.setTitlePosition(TitledBorder.TOP);
		Color green = new Color(34,139,34);
		notSpamTitle.setTitleColor(green);
		notSpamScrollPane.setBorder(notSpamTitle);
		notSpamScrollPane.setPreferredSize((new Dimension(400,500)));
		notSpamPanel.setPreferredSize((new Dimension(400,500)));
		notSpamPanel.setBackground(Color.white);
		notSpamScrollPane.setBackground(Color.white);
		notSpamPanel.add(notSpamScrollPane, BorderLayout.NORTH);
		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notSpamPanel, spamPanel);
		splitPanel.setResizeWeight(0.5);


		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.add(whyTweetIsSpam);
		labelPanel.add(Box.createGlue()); //creates space between the JLabels
		labelPanel.add(tweetCountlabel);

		searchButton.setPreferredSize(new Dimension(150,20));
		refreshButton.setPreferredSize(new Dimension(150,20));
		resultButton.setPreferredSize(new Dimension(150,20));
		JPanel subPanel = new JPanel();
		subPanel.add(enterQueryLabel);
		subPanel.add(userText);
		subPanel.add(searchButton);
		subPanel.add(refreshButton);
		subPanel.add(resultButton);

		add(subPanel,BorderLayout.PAGE_START);
		JPanel sad = new JPanel();
		sad.setLayout(new BorderLayout());

		JLabel label = new JLabel("<html>SEARCH: Searches a query <BR> "
				+ "through twitter stream<BR>"
				+"<BR>"
				+"REFRESH: Refreshes everything to<BR>"
				+ "make search again<BR>"
				+"<BR>"
				+"GENERATE RESULTS: Put results from <BR>"
				+"database in project directory.<html>");
		sad.add(trendPanel,BorderLayout.NORTH);	
		sad.add(label,BorderLayout.CENTER);	



		add(sad,BorderLayout.LINE_END);
		add(splitPanel,BorderLayout.CENTER);
		add(labelPanel,BorderLayout.PAGE_END);

		resultButton.setEnabled(false);



		trendPanel.getList().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {
					String selectedName = (String) trendPanel.getList().getSelectedValue();
					userText.setText(selectedName);
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(userText.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please enter a search query...");

				}else{

					JOptionPane.showMessageDialog(null, "Your query sent, please wait...");
					database.setSearchQuery(userText.getText());
					stream.startStream(database, recognition, spamDetector, currentTime, mapController,locationController, organizationController, personController, languageController, hashtagController, urlController,allWordsController);
					//search.searchRecentlyRelatedTweets(spamDetector, currentTime, database, recognition, mapController, locationController, organizationController, personController, languageController, hashtagController, urlController, allWordsController);
					cosineStream.startCosineStream(cosineController);
					JOptionPane.showMessageDialog(null, "Search completed.");
					resultButton.setEnabled(true);
					searchButton.setEnabled(false);

				}
			}
		}); 

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				stream.getStream().cleanUp(); // shutdown internal stream consuming thread
				stream.getStream().shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
				cosineStream.getStream().cleanUp();
				cosineStream.getStream().shutdown();
				database.clearDatabase();
				mapController.getMap().removeAllMapMarkers();
				mapController.setText("");
				mapController.getTwitterStreamPanel().setText("");
				locationController.getPlot().setDataset(null);
				locationController.setDataset(null);
				locationController.setText("");
				locationController.getTextPane().setText("");;

				organizationController.getPlot().setDataset(null);
				organizationController.setDataset(null);
				organizationController.setText("");
				organizationController.getTextPane().setText("");;

				personController.getPlot().setDataset(null);
				personController.setDataset(null);
				personController.setText("");
				personController.getTextPane().setText("");;

				languageController.getPlot().setDataset(null);
				languageController.setDataset(null);
				languageController.setText("");
				languageController.getTextPane().setText("");;

				hashtagController.getPlot().setDataset(null);
				hashtagController.setDataset(null);
				hashtagController.setText("");
				hashtagController.getTextPane().setText("");;

				urlController.clear();
				/*	urlController.getPlot().setDataset(null);
				urlController.setDataset(null);
				urlController.setText("");
				urlController.getTextPane().setText("");;*/

				allWordsController.getPlot().setDataset(null);
				allWordsController.setDataset(null);
				allWordsController.setText("");
				allWordsController.getTextPane().setText("");;
				for (int i = 0; i < cosineController.getlabelList().size(); i++) {
					cosineController.getlabelList().get(i).setText("");

				}
				cosineController.getView().repaint();
				tweetCountlabel.setText("<html>Tweet count: "+database.getTweetCount()+"<html>");
				searchButton.setEnabled(true);
				resultButton.setEnabled(false);
				getWhyTweetIsSpam().setText("");
				spamTitle.setTitle("SPAMS");
				notSpamTitle.setTitle("PASSED");

				repaint();

			}
		});

		resultButton.addActionListener(new ActionListener(){


			public void actionPerformed(ActionEvent arg0) {
				//locationController, organizationController, personController, languageController, hashtagController, urlController,allWordsController
				//	MongoConnection mongoConnection = new MongoConnection(database.getSearchQuery());
				//	Document basicObj = new Document();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH;mm;ss");
				Date date = new Date();
				String mainFile = "results";
				String file = dateFormat.format(cal.getTime())+" to "+dateFormat.format(date);
				ArrayList<String> cosineArray = new ArrayList<String>();
				try {
					cosineSimilarityToTxt(database.getCosineSimArray(),0);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				File asd = new File(database.getSearchQuery()+"/"+file);
				//ImageIO.write(image, "jpg",asd);

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
					ChartUtilities.saveChartAsJPEG(organizationfile, locationController.getChart(), 600, 400);
					ChartUtilities.saveChartAsJPEG(asd, organizationController.getChart(), 600, 400);
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
				JOptionPane.showMessageDialog(null, "Your search results created in the project directory.");
				//basicObj.put(file, asd);
				//	mongoConnection.coll.insertOne(basicObj);

			}

		});



	}

	public void cosineSimilarityToTxt(ArrayList<String> array,int k) throws IOException{
		OutputStream outputStream;
		if(k == 1){
			 outputStream = new FileOutputStream("words.txt");
		}else{
			outputStream = new FileOutputStream("cosineSimilarity.txt");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH;mm;ss");
		Date date = new Date();
		String mainFile = "results";
		String file = dateFormat.format(cal.getTime())+" to "+dateFormat.format(date);
		File cosfile = new File(mainFile+"/"+file+"/cosineSimilarity.txt");
		File parentDir = cosfile.getParentFile();
		if(! parentDir.exists() ){ 
			parentDir.mkdirs();
		}
		Writer w = new OutputStreamWriter(new FileOutputStream(cosfile));
	
		w.close();
		String eol = System.getProperty("line.separator");
		@SuppressWarnings("resource")        
		Writer out = new OutputStreamWriter(outputStream);
	
		for (int i = 0; i < array.size(); i++) {
			w.write(array.get(i));
			w.write(eol);
			w.write(eol);
			w.write(eol);
			w.write(eol);
		}
		w.flush();
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

	public TitledBorder getSpamTitle() {
		return spamTitle;
	}

	public void setSpamTitle(TitledBorder spamTitle) {
		this.spamTitle = spamTitle;
	}

	public TitledBorder getNotSpamTitle() {
		return notSpamTitle;
	}

	public void setNotSpamTitle(TitledBorder notSpamTitle) {
		this.notSpamTitle = notSpamTitle;
	}

	public JLabel getWhyTweetIsSpam() {
		return whyTweetIsSpam;
	}

	public void setWhyTweetIsSpam(JLabel whyTweetIsSpam) {
		this.whyTweetIsSpam = whyTweetIsSpam;
	}













}
