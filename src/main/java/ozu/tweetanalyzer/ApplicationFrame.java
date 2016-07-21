package ozu.tweetanalyzer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartPanel;
import org.openstreetmap.gui.jmapviewer.JMapViewer;


public class ApplicationFrame extends JFrame{


	private static final long serialVersionUID = 1L;
	private  JTabbedPane tabs = new JTabbedPane();
	private JPanel content = new JPanel(new BorderLayout());

	public void populateApplication(JPanel searchPanel,JMapViewer mapPanel,ChartPanel locationChartPanel,ChartPanel organizationChartPanel,ChartPanel personChartPanel,ChartPanel languageChartPanel,ChartPanel hashtagChartPanel, ChartPanel urlChartPanel){



		tabs.add("SearchPanel", searchPanel);
		tabs.add("LocationChart", locationChartPanel);
		tabs.add("OrganizationChart", organizationChartPanel);
		tabs.add("PersonChart", personChartPanel);
		tabs.add("LanguageChart", languageChartPanel);
		tabs.add("HashtagChart", hashtagChartPanel);
		tabs.add("URLChart", urlChartPanel);
		tabs.add("MapVİsualizaiton", mapPanel);



		content.setPreferredSize(new java.awt.Dimension(1000, 600));
		content.add(tabs);
		setContentPane(content);


	}






}
