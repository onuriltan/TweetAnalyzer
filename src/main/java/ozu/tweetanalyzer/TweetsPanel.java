package ozu.tweetanalyzer;



import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TweetsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public JList<String> list;
	TrendTopic trend= new TrendTopic();
	DefaultListModel<String> model;

	public TweetsPanel() {
		setBorder(new TitledBorder(new EtchedBorder(), "Tweets"));
        // create the middle panel components
		      
	}
}
