

package model;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;

public class ChartModel {

	private String chartName;
	private PieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D plot;
	private JScrollPane scrollPane;
	private JSplitPane splitPane;
	private String text;
	private JTextPane textPane ;

	public JTextPane getTextPane() {
		return textPane;
	}


	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JSplitPane getSplitPane() {
		return splitPane;
	}


	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}


	public String getChartName() {
		return chartName;
	}


	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public PieDataset getDataset() {
		return dataset;
	}


	public void setDataset(PieDataset dataset) {
		this.dataset = dataset;
	}


	public JFreeChart getChart() {
		return chart;
	}


	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}


	public ChartPanel getChartPanel() {
		return chartPanel;
	}


	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}


	public PiePlot3D getPlot() {
		return plot;
	}


	public void setPlot(PiePlot3D plot) {
		this.plot = plot;
	}	












}