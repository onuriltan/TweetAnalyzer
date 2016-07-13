package ozu.tweetanalyzer;





import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class ChartGeneration extends JFrame {


	private static final long serialVersionUID = 1L;
	private String chartName;
	private PieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D plot;


	public ChartGeneration(String chartName, CurrentTime time){
		super(chartName);

		this.chartName = chartName;
		// create the chart...
		chart = createChart(dataset);
		// add the chart to a panel...
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);


	}
	private JFreeChart createChart(final PieDataset dataset) {

		final JFreeChart chart = ChartFactory.createPieChart3D(
				chartName,  // chart title
				dataset,    // data
				true,       // include legend
				true,
				false
				);

		plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		return chart;
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
	
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	
	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public PiePlot3D getPlot() {
		return plot;
	}
	
	public void setPlot(PiePlot3D plot) {
		this.plot = plot;
	}

















}










