package model;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class MapModel {
	
	private JSplitPane panel;
	private String createdAt;   
	private JMapViewer map;
	private JTextPane twitterStreamPanel;
	private JScrollPane streamContainer ;
	private JTextField querytext ;
	private String text;
	
	
	public JSplitPane getPanel() {
		return panel;
	}
	
	public void setPanel(JSplitPane panel) {
		this.panel = panel;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public JMapViewer getMap() {
		return map;
	}
	
	public void setMap(JMapViewer map) {
		this.map = map;
	}
	
	public JTextPane getTwitterStreamPanel() {
		return twitterStreamPanel;
	}
	
	public void setTwitterStreamPanel(JTextPane twitterStreamPanel) {
		this.twitterStreamPanel = twitterStreamPanel;
	}
	
	public JScrollPane getStreamContainer() {
		return streamContainer;
	}
	
	public void setStreamContainer(JScrollPane streamContainer) {
		this.streamContainer = streamContainer;
	}
	
	public JTextField getQuerytext() {
		return querytext;
	}
	
	public void setQuerytext(JTextField querytext) {
		this.querytext = querytext;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
