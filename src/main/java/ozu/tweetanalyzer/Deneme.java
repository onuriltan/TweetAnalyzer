package ozu.tweetanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;



public class Deneme extends ApplicationFrame {



	private static final long serialVersionUID = 1L;
	private String chartTitle;



	private TimeSeriesCollection dataset;
	private JFreeChart chart ;
	private XYPlot plot;
	private CurrentTime time;

	public Deneme(final String chartTitle, CurrentTime time, Hashtable<String, Integer> list) {

		super(chartTitle);
		this.time = time;
		this.chartTitle = chartTitle;
		System.out.println(time.getCurrentDate());

		final JFreeChart chart = createChart(dataset);


		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				chartTitle, 
				time.getCurrentDate(), 
				"MentionCount",
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
		axis.setAutoRangeMinimumSize(1.0);
		return result;
	}




	public TimeSeriesCollection createSampleDataset(Hashtable<String, Integer> list){

		TimeSeriesCollection dataset = new TimeSeriesCollection() ;

		if(list.size()<5){//IF DATASET HAS LESS THAN 5 TOKEN, SHOW IT RANDOM
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				@SuppressWarnings("deprecation")
				TimeSeries series = new TimeSeries(key, Millisecond.class);
				series.add(new Millisecond(),Double.valueOf(list.get(key)));
				dataset.addSeries(series);

			}
		}
		if(list.size()>= 5){// TO SHOW NO MORE THAN 5 DIFFERENT TOKEN IN GRAPH, 
			//BECAUSE SHOWING ALL THE TOKENS IN ONE GRAPH LOOKS VERY CROWDED

			ArrayList<Map.Entry<String, Integer>> sortedList = sortHashTable(list);

			for(int i = sortedList.size()-1 ; i>=sortedList.size()-5;i--){
				String token = sortedList.get(i).toString();
				String[] parts = token.split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];

				@SuppressWarnings("deprecation")
				TimeSeries series = new TimeSeries(tokenName, Millisecond.class);
				series.add(new Millisecond(),Double.valueOf(tokenValue));
				dataset.addSeries(series);

			}
		}
		return dataset;

	}


	public ArrayList<Map.Entry<String, Integer>> sortHashTable (Hashtable<String, Integer> list){

		//TO SORT THE TOKENS OF HASHTABLE IN DESCENDING ORDER 
		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<Entry<String, Integer>>(list.entrySet());
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>(){

			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}});

		return sortedList;
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

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public TimeSeriesCollection getDataset() {
		return dataset;
	}

	public void setDataset(TimeSeriesCollection dataset) {
		this.dataset = dataset;
	}

	public CurrentTime getTime() {
		return time;
	}

	public void setTime(CurrentTime time) {
		this.time = time;
	}













}
