package controller;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.DatabaseModel;
import model.UrlModel;
import view.UrlView;

public class UrlController {


	private UrlModel model;
	private UrlView view;



	public UrlController(UrlModel model, UrlView view){

		this.model=model;
		this.view=view;

	}

	public JTextPane getVerifiedTextPane() {
		return model.getVerifiedTextPane();
	}
	public void setVerifiedTextPane(JTextPane verifiedTextPane) {
		model.setVerifiedTextPane(verifiedTextPane);
	}

	public JTextPane getTextPane() {
		return model.getTextPane();
	}
	public void setTextPane(JTextPane textPane) {
		model.setTextPane(textPane);
	}
	public String getText() {
		return model.getText();
	}
	public void setText(String text) {
		model.setText(text);
	}

	public JTextField getLabelOne() {
		return model.getLabelOne();
	}
	public void setLabelOne(JTextField labelOne) {
		model.setLabelOne(labelOne);
	}
	public JTextField getLabelTwo() {
		return model.getLabelTwo();
	}
	public void setLabelTwo(JTextField labelTwo) {
		model.setLabelTwo(labelTwo);
	}
	public JTextField getLabelThree() {
		return model.getLabelThree();
	}
	public void setLabelThree(JTextField labelThree) {
		model.setLabelThree(labelThree);
	}
	public JTextField getLabelFour() {
		return model.getLabelFour();
	}
	public void setLabelFour(JTextField labelFour) {
		model.setLabelFour(labelFour);
	}
	public JTextField getLabelFive() {
		return model.getLabelFive();
	}
	public void setLabelFive(JTextField labelFive) {
		model.setLabelFive(labelFive);
	}
	public JTextField getLabelSix() {
		return model.getLabelSix();
	}
	public void setLabelSix(JTextField labelSix) {
		model.setLabelSix(labelSix);
	}
	public JTextField getLabelSeven() {
		return model.getLabelSeven();
	}
	public void setLabelSeven(JTextField labelSeven) {
		model.setLabelSeven(labelSeven);
	}
	public JTextField getLabelEight() {
		return model.getLabelEight();
	}
	public void setLabelEight(JTextField labelEight) {
		model.setLabelEight(labelEight);;
	}
	public JTextField getLabelNine() {
		return model.getLabelNine();
	}
	public void setLabelNine(JTextField labelNine) {
		model.setLabelNine(labelNine);
	}
	public JTextField getLabelTen() {
		return model.getLabelTen();
	}
	public void setLabelTen(JTextField labelTen) {
		model.setLabelTen(labelTen);
	}
	public void populateUrlPanel(){				
		view.populateUrlPanel();
	}	
	public JPanel getTopOccuredUrls() {
		return model.getTopOccuredUrls();
	}
	public void setTopOccuredUrls(JPanel topOccuredUrls) {
		model.setTopOccuredUrls(topOccuredUrls);
	}
	public void updateUrlPanel(DatabaseModel database,Hashtable<String, Integer> topSimilarTrends){
		view.updateUrl(database,topSimilarTrends);
	}


	public UrlView getView() {
		return view;
	}


	public void setView(UrlView view) {
		this.view = view;
	}

	public void clear(){
		model.getLabelOne().setText("");
		model.getLabelTwo().setText("");
		model.getLabelThree().setText("");
		model.getLabelFour().setText("");
		model.getLabelFive().setText("");
		model.getLabelSix().setText("");
		model.getLabelSeven().setText("");
		model.getLabelEight().setText("");
		model.getLabelNine().setText("");
		model.getLabelTen().setText("");
		for (int i = 0; i < model.getLabelList().size(); i++) {
			model.getLabelList().get(i).setText("");

		}
		model.setText("");
		model.getTextPane().setText("");
		model.getVerifiedTextPane().setText("");



	}





}
