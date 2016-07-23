package ozu.tweetanalyzer;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TrendPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	public JList<String> list;
	TrendTopic trend= new TrendTopic();
	DefaultListModel<String> model;
	//int counter = 15;

	public TrendPanel() {
		trend.getTrendsFromTwitter();
		setLayout(new BorderLayout());
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane pane = new JScrollPane(list);

		for (String t : trend.getTrendList()) {
			model.addElement(t);              
		}    

		/*    addButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        model.addElement("Element " + counter);
	        counter++;
	      }
	    });
		 */ 

		add(pane, BorderLayout.NORTH);
	}
}
