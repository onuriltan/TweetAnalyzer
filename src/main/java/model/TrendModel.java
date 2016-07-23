package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ozu.tweetanalyzer.TrendTopic;

public class TrendModel extends JPanel {


	private static final long serialVersionUID = 1L;
	public JList list;
	TrendTopic trend= new TrendTopic();

	DefaultListModel model;

	int counter = 15;

	public TrendModel() {
		trend.getTrendsFromTwitter();
		setLayout(new BorderLayout());
		model = new DefaultListModel();
		list = new JList(model);
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
