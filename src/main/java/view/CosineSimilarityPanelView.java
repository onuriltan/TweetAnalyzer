package view;

import java.awt.Color;

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
		/*model.getLabelOne().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelOne().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelOne().setForeground(Color.BLACK);

		model.getLabelTwo().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelTwo().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelTwo().setForeground(Color.BLACK);

		model.getLabelThree().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelThree().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelThree().setForeground(Color.BLACK);

		model.getLabelFour().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelFour().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelFour().setForeground(Color.BLACK);

		model.getLabelFive().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelFive().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelFive().setForeground(Color.BLACK);

		model.getLabelSix().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelSix().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelSix().setForeground(Color.BLACK);

		model.getLabelSeven().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelSeven().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelSeven().setForeground(Color.BLACK);

		model.getLabelEight().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelEight().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelEight().setForeground(Color.BLACK);

		model.getLabelNine().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelNine().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelNine().setForeground(Color.BLACK);

		model.getLabelTen().setPreferredSize( new Dimension( 700, 24 ));
		model.getLabelTen().setFont(new Font("Courier New", Font.ITALIC, 12));
		model.getLabelTen().setForeground(Color.BLACK);*/


		this.setBackground(Color.white);
		this.setLayout(new GridLayout(0,1));
		add(model.getLabelOne());
		add(model.getLabelTwo());
		add(model.getLabelThree());
		add(model.getLabelFour());
		add(model.getLabelFive());
		add(model.getLabelSix());
		add(model.getLabelSeven());
		add(model.getLabelEight());
		add(model.getLabelNine());
		add(model.getLabelTen());



	}	



	public void updateCosinePanel(Hashtable<String, Double> topSimilarTrends,DatabaseModel database, ArrayList<String> sortedKeys, ArrayList<Double> sortedValues){

		ArrayList<Entry<String, Double>> sortedHashTable = sortValue(topSimilarTrends);
		System.out.println(sortedHashTable);
		ArrayList<String> sortedList = new ArrayList<String>();
		for (int i = 0; i < sortedHashTable.size(); i++) {
			sortedList.add(sortedHashTable.get(i).toString());
		}
		for (int i = 0; i < sortedList.size(); i++) {
			System.out.println(sortedList.get(i).toString());
		}
		

		for (int i = 0; i < sortedList.size(); i++) {
			if(i == 0){
				model.getLabelOne().setText(sortedList.get(0).toString());
			}
			if(i == 1){
				model.getLabelTwo().setText(sortedList.get(1).toString());
			}
			if(i == 2){
				model.getLabelThree().setText(sortedList.get(2).toString());
			}
			if(i == 3){
				model.getLabelFour().setText(sortedList.get(3).toString());
			}
			if(i == 4){
				model.getLabelFive().setText(sortedList.get(4).toString());
			}
			if(i == 5){
				model.getLabelSix().setText(sortedList.get(5).toString());
			}
			if(i == 6){
				model.getLabelSeven().setText(sortedList.get(6).toString());
			}			
			if(i == 7){
				model.getLabelEight().setText(sortedList.get(7).toString());
			}
			if(i == 8){
				model.getLabelNine().setText(sortedList.get(8).toString());
			}
			if(i == 9){
				model.getLabelTen().setText(sortedList.get(9).toString());
			}
		}



		

	}
	public ArrayList<Map.Entry<String, Double>>  sortValue(Hashtable<String, Double> t){

		//Transfer as List and sort it
		ArrayList<Map.Entry<String, Double>> l = new ArrayList(t.entrySet());
		Collections.sort(l, new Comparator<Map.Entry<String, Double>>(){

			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return Double.compare(o2.getValue(), o1.getValue());
			}});

		return l;
	}
}
