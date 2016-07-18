package controller;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;


import org.openstreetmap.gui.jmapviewer.JMapViewer;

import model.MapModel;
import twitter4j.Status;
import view.MapView;

public class MapController {

	private MapModel mapModel;
	private MapView mapView;




	public MapController(MapModel model, MapView view){

		this.mapModel=model;
		this.mapView=view;

	}


	public JSplitPane getPanel() {
		return mapModel.getPanel();
	}

	public void setPanel(JSplitPane panel) {
		mapModel.setPanel(panel);;
	}

	public String getCreatedAt() {
		return mapModel.getCreatedAt();
	}

	public void setCreatedAt(String createdAt) {
		mapModel.setCreatedAt(createdAt);
	}

	public JMapViewer getMap() {
		return mapModel.getMap();
	}

	public void setMap(JMapViewer map) {
		mapModel.setMap(map);
	}

	public JTextPane getTwitterStreamPanel() {
		return mapModel.getTwitterStreamPanel();
	}

	public void setTwitterStreamPanel(JTextPane twitterStreamPanel) {
		mapModel.setTwitterStreamPanel(twitterStreamPanel);
	}

	public JScrollPane getStreamContainer() {
		return mapModel.getStreamContainer();
	}

	public void setStreamContainer(JScrollPane streamContainer) {
		mapModel.setStreamContainer(streamContainer);
	}

	public JTextField getQuerytext() {
		return mapModel.getQuerytext();
	}

	public void setQuerytext(JTextField querytext) {
		mapModel.setQuerytext(querytext);;
	}

	public String getText(){
		return mapModel.getText();
	}

	public void setText(String text){
		mapModel.setText(text);
	}

	public void populateMap(){
		mapView.populateMap(mapModel);
	}


	public void updateMap(Status tweet){
		mapView.updateMap(tweet);
	}






















}
