package ozu.tweetanalyzer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;


public class ApplicationMainFrame extends JFrame{


	private static final long serialVersionUID = 1L;
	private  JTabbedPane tabs = new JTabbedPane();
	private JPanel content = new JPanel(new BorderLayout());
	public void populateApplication(JPanel cosineSimilarityPanel,JPanel searchPanel,JSplitPane mapPanel,JSplitPane jSplitPane,JSplitPane jSplitPane2,JSplitPane jSplitPane3,JSplitPane jSplitPane4,JSplitPane jSplitPane5, JSplitPane jSplitPane6,
			JSplitPane jSplitPane7){



		tabs.add("SearchPanel", searchPanel);
		tabs.add("LocationChart", jSplitPane);
		tabs.add("OrganizationChart", jSplitPane2);
		tabs.add("PersonChart", jSplitPane3);
		tabs.add("LanguageChart", jSplitPane4);
		tabs.add("HashtagChart", jSplitPane5);
		tabs.add("URLChart", jSplitPane6);
		tabs.add("MostCommonWords", jSplitPane7);
		tabs.add("TrendTopicSimilarity", cosineSimilarityPanel);
		tabs.add("MapVisualizaiton", mapPanel);



		content.setPreferredSize(new java.awt.Dimension(1000, 600));
		content.setMaximumSize(new java.awt.Dimension(1000, 600));
		content.setMinimumSize(new java.awt.Dimension(1000, 600));

		content.add(tabs);
		setContentPane(content);


	}
	
	
	





}
