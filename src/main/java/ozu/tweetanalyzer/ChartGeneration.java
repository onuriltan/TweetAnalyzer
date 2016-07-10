package ozu.tweetanalyzer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class ChartGeneration extends JFrame {


	private static final long serialVersionUID = 1L;
	private List<Double> yData;
	private String chartName;
	private String date ;
	private String clock ;
	private Hashtable<String, Integer> list;
	private PieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private PiePlot3D plot;

	public ChartGeneration(String chartName, CurrentTime time, Hashtable<String, Integer> list){
		super(chartName);

		this.chartName = chartName;
		this.list = list;
		//date = time.dateFormat.format(time.date).toString();
		//clock =time.dateFormat.format(time.cal.getTime());
		// create a dataset...
		dataset = createSampleDataset(list);

		// create the chart...
		chart = createChart(dataset);

		// add the chart to a panel...
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);


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



	public PieDataset createSampleDataset(Hashtable<String, Integer> list){


		final DefaultPieDataset result = new DefaultPieDataset();


		if(list.size()<5){//IF DATASET HAS LESS THAN 5 TOKEN, SHOW IT RANDOM
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				result.setValue(key+" = "+list.get(key), list.get(key));

			}
		}
		if(list.size()>= 5){// TO SHOW NO MORE THAN 5 DIFFERENT TOKEN IN GRAPH, 
			                //BECAUSE SHOWING ALL THE TOKENS IN ONE GRAPH LOOKS VERY ANNOYING

			ArrayList<Map.Entry<String, Integer>> sortedList = sortHashTable(list);
			
			for(int i = sortedList.size()-1 ; i>=sortedList.size()-5;i--){
				String token = sortedList.get(i).toString();
				String[] parts = token.split("=", 2);
				String tokenName = parts[0];
				String tokenValue = parts[1];
		
				System.out.println(tokenName.toString() + " "+tokenValue.toString());

				result.setValue(tokenName.toString()+" = "+tokenValue.toString(), Integer.valueOf(tokenValue.toString()));
			}
		}
		return result;

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



	public List<Double> getyData() {
		return yData;
	}



	public void setyData(List<Double> yData) {
		this.yData = yData;
	}



	public String getChartName() {
		return chartName;
	}



	public void setChartName(String chartName) {
		this.chartName = chartName;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getClock() {
		return clock;
	}



	public void setClock(String clock) {
		this.clock = clock;
	}



	public Hashtable<String, Integer> getList() {
		return list;
	}



	public void setList(Hashtable<String, Integer> list) {
		this.list = list;
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










