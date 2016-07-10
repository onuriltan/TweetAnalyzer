package ozu.tweetanalyzer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class Deneme extends ApplicationFrame {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimeSeries series;
	private XYSeriesCollection data;
	private JFreeChart chart ;
	private XYPlot plot;


	public Deneme(final String title) {

		super(title);
		series = new TimeSeries("Random Data", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
		final JFreeChart chart = createChart(dataset);


		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"Dynamic Data Demo", 
				"Time", 
				"Value",
				dataset, 
				true, 
				true, 
				false
				);
		final XYPlot plot = result.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis.setFixedAutoRange(60000.0);  // 60 seconds
		axis = plot.getRangeAxis();
		axis.setRange(0.0, 200.0); 
		return result;
	}




	public TimeSeries getSeries() {
		return series;
	}


	public void setSeries(TimeSeries series) {
		this.series = series;
	}


	public XYSeriesCollection getData() {
		return data;
	}


	public void setData(XYSeriesCollection data) {
		this.data = data;
	}


	public JFreeChart getChart() {
		return chart;
	}


	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}


	public XYPlot getPlot() {
		return plot;
	}


	public void setPlot(XYPlot plot) {
		this.plot = plot;
	}













}
