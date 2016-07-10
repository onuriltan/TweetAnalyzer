

package model;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartModel extends JFrame {
	
	private DefaultPieDataset result ;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private String chartName;
	private String fileName;
	private PiePlot plot;
	
	
	public ChartModel(String fileName, String chartName,JPanel chartsPanel) throws FileNotFoundException, IOException {
		result = new DefaultPieDataset();
		this.chartName = chartName;
		this.fileName = fileName;
		

	// based on the dataset we create the chart
		chart = createChart(result, chartName);
		// we put the chart into a panel
		chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		chartsPanel.add(chartPanel, BorderLayout.CENTER);
		chartsPanel.revalidate();
		setContentPane(chartPanel);
		
	}

	
	
	
	
	
	
	
	
	
	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
				dataset,                // data
				true,                   // include legend
				true,
				false);

		plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
		plot.setNoDataMessage("No data available");
		plot.setCircular(false);
		plot.setLabelGap(0.02);
		return chart;

	}

}