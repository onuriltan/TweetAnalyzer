package ozu.tweetanalyzer;


import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Millisecond;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import twitter4j.Status;

public class EntityRecognition {

	private String classifierPath = "libs/english.all.3class.distsim.crf.ser.gz";
	private AbstractSequenceClassifier<CoreLabel> classifier;
	private Database database;


	public EntityRecognition(Database database){
		this.database = database;

	}

	public void entityRecognition(Status status, CurrentTime time,ChartGeneration locationChart,ChartGeneration organizationChart,ChartGeneration personChart,ChartGeneration languageChart,Deneme deneme) 
	{
		String text = status.getText();
		try {
			classifier = CRFClassifier.getClassifier(classifierPath);//LOAD CLASSIFIER FROM FILE TO START ENTITY RECOGNITION
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		List<Triple<String, Integer, Integer>> out = classifier.classifyToCharacterOffsets(text);

		for (int i = 0; i < out.size(); i++) {
			if (out.get(i).first.equals("LOCATION")) {// IF ENETITYRECOGNIZER RECOGNIZE A TOKEN AS LOCATION
				String location = text.substring(out.get(i).second,	out.get(i).third);//TAKE LOCATION
				location = location.toUpperCase();//MAKE IT UPPER CASED LETTERS TO MAKE SAME WORDS 
				updateLists(database.getLocationList(), location, "location");//UPDATE THE LOCATION LIST
				time.startTime();

				PieDataset result = new DefaultPieDataset();//CREATE NEW PIECHARTDATASET
				result = locationChart.createSampleDataset(database.getLocationList());//CREATE NEW DATASET BASED ON UPDATED DATASET
				locationChart.getPlot().setDataset(result);// CHANGE THE CHART DATASET

				XYSeries series = new XYSeries(location);
				//double asd= Double.valueOf(time.getDateFormat().format(time.getCal().getTime()));
				series.add(10.2, 10.2);
				series.add(15.2, 10.2);
				series.add(20.2, 10.2);
				series.add(30.2, 10.2);

				final Millisecond now = new Millisecond();
				System.out.println("Now = " + now.toString());
				deneme.getSeries().add(new Millisecond(), 10.0);

			}		
			if (out.get(i).first.equals("ORGANIZATION")) {
				String organization = text.substring(out.get(i).second,	out.get(i).third);
				organization = organization.toUpperCase();
				updateLists(database.getOrganizationList(), organization, "organization");//UPDATE DATA WHEN NEW TOKEN COMES
				time.startTime();
				PieDataset result = new DefaultPieDataset();
				result = organizationChart.createSampleDataset(database.getOrganizationList());
				organizationChart.getPlot().setDataset(result);// CHANGE DATA WHEN NEW TOKEN COMES
			}
			if (out.get(i).first.equals("PERSON")) {
				String person = text.substring(out.get(i).second, out.get(i).third);
				person = person.toUpperCase();
				updateLists(database.getPersonList(), person,"person");
				time.startTime();
				PieDataset result = new DefaultPieDataset();
				result = personChart.createSampleDataset(database.getPersonList());
				personChart.getPlot().setDataset(result);

			}
		}

		String language = status.getLang();
		updateLists(database.getLanguageList(), language, "language");
		PieDataset result = new DefaultPieDataset();
		result = languageChart.createSampleDataset(database.getLanguageList());
		languageChart.getPlot().setDataset(result);


	}

	public void updateLists(Hashtable<String, Integer> table, String key, String tableName){

		if(!table.containsKey(key)){
			table.put(key,1);
		}
		if(table.containsKey(key)){
			table.put(key, table.get(key)+1);
		}
		if(tableName.equals("location")){
			database.setLocationList(table);
		}
		if(tableName.equals("organization")){
			database.setOrganizationList(table);
		}
		if(tableName.equals("person")){
			database.setPersonList(table);
		}
		if(tableName.equals("language")){
			database.setLanguageList(table);
		}

	}



}
