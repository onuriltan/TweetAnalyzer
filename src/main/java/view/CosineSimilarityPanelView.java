package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;
import model.CosineSimilarityPanelModel;
import model.DatabaseModel;

public class CosineSimilarityPanelView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CosineSimilarityPanelModel model;



	public CosineSimilarityPanelView(CosineSimilarityPanelModel model){
		this.model = model;



	}

	public void populateCosinePanel(){	
		model.getLabelOne().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelOne().setForeground(Color.BLACK);

		model.getLabelTwo().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelTwo().setForeground(Color.BLACK);

		model.getLabelThree().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelThree().setForeground(Color.BLACK);

		model.getLabelFour().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelFour().setForeground(Color.BLACK);

		model.getLabelFive().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelFive().setForeground(Color.BLACK);

		model.getLabelSix().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelSix().setForeground(Color.BLACK);

		model.getLabelSeven().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelSeven().setForeground(Color.BLACK);

		model.getLabelEight().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelEight().setForeground(Color.BLACK);

		model.getLabelNine().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelNine().setForeground(Color.BLACK);

		model.getLabelTen().setFont(new Font("Courier New", Font.BOLD, 15));
		model.getLabelTen().setForeground(Color.BLACK);


		this.setBackground(Color.white);
		this.setLayout(new GridLayout(0,1));

		model.getLabelList().add(model.getLabelOne());
		model.getLabelList().add(model.getLabelTwo());
		model.getLabelList().add(model.getLabelThree());
		model.getLabelList().add(model.getLabelFour());
		model.getLabelList().add(model.getLabelFive());
		model.getLabelList().add(model.getLabelSix());
		model.getLabelList().add(model.getLabelSeven());
		model.getLabelList().add(model.getLabelEight());
		model.getLabelList().add(model.getLabelNine());
		model.getLabelList().add(model.getLabelTen());
		add(model.getLabelList().get(0));
		add(model.getLabelList().get(1));
		add(model.getLabelList().get(2));
		add(model.getLabelList().get(3));
		add(model.getLabelList().get(4));
		add(model.getLabelList().get(5));
		add(model.getLabelList().get(6));
		add(model.getLabelList().get(7));
		add(model.getLabelList().get(8));
		add(model.getLabelList().get(9));


	}	



	public void updateCosinePanel(Hashtable<String, Double> topSimilarTrends,DatabaseModel database, ArrayList<String> sortedKeys, ArrayList<Double> sortedValues){

		ArrayList<Entry<String, Double>> sortedHashTable = sortValue(topSimilarTrends);
		ArrayList<String> sortedList = new ArrayList<String>();
		for (int i = 0; i < sortedHashTable.size(); i++) {
			sortedList.add(sortedHashTable.get(i).toString());
		}



		for (int i = 0; i < sortedList.size(); i++) {
			if(i == 0){
				model.getLabelList().get(0).setText(sortedList.get(0).toString());
			}
			if(i == 1){
				model.getLabelList().get(1).setText(sortedList.get(1).toString());
			}
			if(i == 2){
				model.getLabelList().get(2).setText(sortedList.get(2).toString());
			}
			if(i == 3){
				model.getLabelList().get(3).setText(sortedList.get(3).toString());
			}
			if(i == 4){
				model.getLabelList().get(4).setText(sortedList.get(4).toString());
			}
			if(i == 5){
				model.getLabelList().get(5).setText(sortedList.get(5).toString());
			}
			if(i == 6){
				model.getLabelList().get(6).setText(sortedList.get(6).toString());
			}			
			if(i == 7){
				model.getLabelList().get(7).setText(sortedList.get(7).toString());
			}
			if(i == 8){
				model.getLabelList().get(8).setText(sortedList.get(8).toString());
			}
			if(i == 9){
				model.getLabelList().get(9).setText(sortedList.get(9).toString());
			}
		}





	}
	public ArrayList<Map.Entry<String, Double>>  sortValue(Hashtable<String, Double> t){

		//Transfer as List and sort it
		ArrayList<Map.Entry<String, Double>> l = new ArrayList<Entry<String, Double>>(t.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<String, Double>>(){

			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return Double.compare(o2.getValue(), o1.getValue());
			}});

		return l;
	}
}
