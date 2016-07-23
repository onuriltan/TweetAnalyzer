package model;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ozu.tweetanalyzer.TrendPanel;

public class SearchPanelModel {
	private JLabel  namelabel;
	private JPanel controlPanel;	      
	private JTextField userText ;	        
	private JButton searchButton ;	  
	private JButton refreshButton ;	
	private JLabel label ;
	private JLabel tweetCountlabel;
	private TrendPanel trendPanel;



	public JLabel getNamelabel() {
		return namelabel;
	}
	public void setNamelabel(JLabel namelabel) {
		this.namelabel = namelabel;
	}
	public JPanel getControlPanel() {
		return controlPanel;
	}
	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	public JTextField getUserText() {
		return userText;
	}
	public void setUserText(JTextField userText) {
		this.userText = userText;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	public JButton getRefreshButton() {
		return refreshButton;
	}
	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JLabel getTweetCountlabel() {
		return tweetCountlabel;
	}
	public void setTweetCountlabel(JLabel tweetCountlabel) {
		this.tweetCountlabel = tweetCountlabel;
	}
	public TrendPanel getTrendPanel() {
		return trendPanel;
	}
	public void setTrendPanel(TrendPanel trendPanel) {
		this.trendPanel = trendPanel;
	}






}
