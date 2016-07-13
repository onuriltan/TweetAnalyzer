package controller;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;

import model.ChartModel;
import view.ChartView;

public class ChartController {


	private ChartModel model;
	private ChartView view;

	public ChartController(ChartModel model, ChartView view){

		this.model=model;
		this.view=view;

	}


	public String getChartName() {
		return model.getChartName();
	}

	public void setChartName(String chartName) {
		model.setChartName(chartName);
	}
	public PieDataset getDataset() {
		return model.getDataset();
	}

	public void setDataset(PieDataset dataset) {
		model.setDataset(dataset);
	}

	public JFreeChart getChart() {
		return model.getChart();
	}

	public void setChart(JFreeChart chart) {
		model.setChart(chart);;
	}

	public ChartPanel getChartPanel() {
		return model.getChartPanel();
	}

	public void setChartPanel(ChartPanel chartPanel) {
		model.setChartPanel(chartPanel);;
	}

	public PiePlot3D getPlot() {
		return model.getPlot();
	}

	public void setPlot(PiePlot3D plot) {
		model.setPlot(plot);
	}

	public void populateChart(){				
		view.populateChart(model);
	}	
	
	public void updateChart(){
		view.updateChart();
	}




}
