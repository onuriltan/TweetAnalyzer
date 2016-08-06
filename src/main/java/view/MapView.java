package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import model.MapModel;
import ozu.tweetanalyzer.TweetLocationFinder;
import twitter4j.Status;


public class MapView extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapModel mapModel;
	private TweetLocationFinder locationFromAccount;

	public MapView(MapModel mapModel, TweetLocationFinder locationFromAccount){
		this.mapModel = mapModel;
		this.locationFromAccount = locationFromAccount;

	}


	public void populateMap(MapModel mapModel){

		this.mapModel = mapModel;
		JMapViewer map = new JMapViewer();


		map.setSize(new Dimension(700, 400));
		mapModel.setMap(map);

		JTextPane twitterStreamPanel = new JTextPane();
		mapModel.setTwitterStreamPanel(twitterStreamPanel); 

		JScrollPane streamContainer = new JScrollPane(twitterStreamPanel);
		streamContainer.setSize(new Dimension(900,900));
		mapModel.setStreamContainer(streamContainer);

		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, map, streamContainer);
		panel.setResizeWeight(0.5);
		mapModel.setPanel(panel);

	}

	public void updateMap(Status tweet){

		MapMarkerDot marker;
		String text;
		if(mapModel.getText() == null){
			text = "";
		}else{
			text = mapModel.getText();
		}
		if(tweet.getGeoLocation() != null)
		{
			double lat = tweet.getGeoLocation().getLatitude();
			double lon = tweet.getGeoLocation().getLongitude();
			marker = new MapMarkerDot(lat,lon);
			marker.setBackColor(Color.black);// to differentiate that if users shared their exact location, show it with black dot
			//marker.setName(tweet.getUser().getScreenName());
			String nameText = tweet.getUser().getScreenName();
			mapModel.getMap().addMapMarker(marker);
			text = text+"\n"
					+nameText+" : "
					+tweet.getText()+"\n";
			mapModel.setText(text);
			mapModel.getTwitterStreamPanel().setText(mapModel.getText());


		}
		if(tweet.getUser().getLocation() != null)
		{

			String location = tweet.getUser().getLocation().toString();
			//String name = tweet.getUser().getScreenName();

			try{
				locationFromAccount.locationRecognizer(location);
				if(locationFromAccount.locationRecognizer(location)){
					marker = new MapMarkerDot(locationFromAccount.getLatitude(),locationFromAccount.getLongitude());
					marker.setBackColor(Color.blue);//to differentiate that if users have a location description of their accounts, show it with blue dot
					mapModel.getMap().addMapMarker(marker);
					text = text+"\n"
							+tweet.getUser().getScreenName()+" : "
							+tweet.getText()+"\n";
					mapModel.setText(text);
					mapModel.getTwitterStreamPanel().setText(mapModel.getText());
				}

			}catch(Exception e){}

		}
		else{// if location can't recognized, do nothing
			

		}
	}

}
