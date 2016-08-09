package controller;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import model.CosineSimilarityPanelModel;
import model.DatabaseModel;
import view.CosineSimilarityPanelView;

public class CosineSimilarityPanelController {


	private CosineSimilarityPanelModel model;
	private CosineSimilarityPanelView view;



	public CosineSimilarityPanelController(CosineSimilarityPanelModel model, CosineSimilarityPanelView view){

		this.model=model;
		this.view=view;

	}


	public JLabel getLabelOne(){
		return model.getLabelOne();
	}
	public void setLabelOne(JLabel labelOne){
		model.setLabelOne(labelOne);
	}
	public JLabel getlabelTwo(){
		return model.getLabelTwo();
	}
	public void setLabelTwo(JLabel labelTwo){
		model.setLabelTwo(labelTwo);
	}
	public JLabel getLabelThree(){
		return model.getLabelThree();
	}
	public void setLabelThree(JLabel labelThree){
		model.setLabelThree(labelThree);
	}
	public JLabel getLabelFour(){
		return model.getLabelFour();
	}
	public void setLabelFour(JLabel labelFour){
		model.setLabelFour(labelFour);
	}
	public JLabel getLabelFive(){
		return model.getLabelFive();
	}
	public void setLabelFive(JLabel labelFive){
		model.setLabelFive(labelFive);
	}
	public JLabel getLabelSix(){
		return model.getLabelFour();
	}
	public void setLabelSix(JLabel labelSix){
		model.setLabelSix(labelSix);
	}
	public JLabel getLabelSeven(){
		return model.getLabelSeven();
	}
	public void setLabelSeven(JLabel labelSeven){
		model.setLabelSeven(labelSeven);
	}
	public JLabel getLabelEight(){
		return model.getLabelEight();
	}
	public void setLabelEight(JLabel labelSeven){
		model.setLabelEight(labelSeven);
	}
	public JLabel getLabelNine(){
		return model.getLabelNine();
	}
	public void setLabelNine(JLabel labelNine){
		model.setLabelNine(labelNine);
	}
	public JLabel getLabelTen(){
		return model.getLabelTen();
	}
	public void setLabelTen(JLabel labelTen){
		model.setLabelTen(labelTen);
	}
	public ArrayList<JLabel> getlabelList(){
		return model.getLabelList();
	}
	public void setLabelList(ArrayList<JLabel> labelList){
		model.setLabelList(labelList);
	} 


	public void populateCosinePanel(){				
		view.populateCosinePanel();
	}	
	
	public void updateCosinePanel(Hashtable<String, Double> topSimilarTrends,DatabaseModel database, ArrayList<String> sortedKeys, ArrayList<Double> sortedValues){
		view.updateCosinePanel(topSimilarTrends,database,sortedKeys,sortedValues);
	}


	public CosineSimilarityPanelView getView() {
		return view;
	}


	public void setView(CosineSimilarityPanelView view) {
		this.view = view;
	}






}
