package ozu.tweetanalyzer;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DatabaseModel;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TrendPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private DatabaseModel database;
	private ConfigurationBuilder cb;
	private DefaultListModel<String> model;

	public TrendPanel(DatabaseModel database) {
		this.database = database;
		getTrendsFromTwitter();
		createTrendPanel(database);

	}


	private void createTrendPanel(DatabaseModel database) {
		setLayout(new BorderLayout());
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane pane = new JScrollPane(list);
		int i  = 0 ;
		for (String t : database.getTrendTopicList()) {
			if(i==20)break;
			model.addElement(t);
			database.getTrendTopicArray()[i] = t;
			i++;
		}    
		add(pane, BorderLayout.NORTH);
	}


	public void getTrendsFromTwitter(){
		ConfigurationBuilder cb = defineConfBuilder();
		try {
			Twitter twitter = new TwitterFactory(cb.build()).getInstance();
			//int turkeyTrends = getTrendsFromTurkey(twitter);
			Trends trends = twitter.getPlaceTrends(1);//takes worldwide trends
			int i  = 0;
			for (Trend trend : trends.getTrends()) {
				if(i==20)break;
				database.getTrendTopicList().add(trend.getName());
				i++;
			}
		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("Failed to get current trends: " + e.getMessage());
		}
	}

	private ConfigurationBuilder defineConfBuilder(){
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("xjhjEo5dv9l9gkH6aOsYT9FEW")
		.setOAuthConsumerSecret("BCFiOEtwg49XtHzkhQ08CF5Nm4Nafx2ppHjg6gmA0aB862L7ps")
		.setOAuthAccessToken("4013320817-ShvBoFTZYGMm8RQMrcZEmsihk9yua2KpU3WEoPJ")
		.setOAuthAccessTokenSecret("Fmz8lmkNce9Fx5svO7XyFJikvyRp3Y0hssZQDDrjtxhP7");
		cb.setJSONStoreEnabled(true);
		return cb;
	}


	public JList<String> getList() {
		return list;
	}


	public void setList(JList<String> list) {
		this.list = list;
	}







}
