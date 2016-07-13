package view;


import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;
import model.ChartModel;

public class ChartView extends JFrame{


	private static final long serialVersionUID = 1L;

	//private ChartModel model = new ChartModel();
	private ChartModel model;


	public void populateChart(ChartModel model) {

		this.model = model;

		// create the chart...
		JFreeChart chart = ChartFactory.createPieChart3D(
				model.getChartName(),  // chart title
				model.getDataset(),    // data
				true,       // include legend
				true,
				false
				);

		model.setChart(chart);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		model.setPlot(plot);
		// add the chart to a panel...
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		model.setChartPanel(chartPanel);

		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}

	public void updateChart(PieDataset dataset){

		model.getPlot().setDataset(dataset);

	}






}
