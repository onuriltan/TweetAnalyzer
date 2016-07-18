package ozu.tweetanalyzer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class TweetLocationFinder {



	private double latitude;
	private double longitude;
	private MapMarkerDot marker;



	public void locationRecognizer(String location){

		String jsonString = getHttpLocationFromGoogleMapsAPI(location);
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonString);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		if(obj.get("status").toString().equals("ZERO_RESULTS")){
			//System.out.println("Google Map API cant found location");

		}
		if(obj.get("status").toString().equals("OK")){
			JSONArray results = (JSONArray) obj.get("results");
			JSONObject data = (JSONObject) results.get(0);
			JSONObject geometry = (JSONObject)data.get("geometry");
			JSONObject JsonLocation = (JSONObject) geometry.get("location"); 

			latitude =  (Double) JsonLocation.get("lat");
			longitude =  (Double) JsonLocation.get("lng");
			marker = new MapMarkerDot(latitude,longitude); 
		}

	}


	public String getHttpLocationFromGoogleMapsAPI(String location){

		String jsonString = "";
		URI url = null;
		try {
			url = new URIBuilder()
					.setScheme("http")
					.setHost("maps.googleapis.com")
					.setPath("/maps/api/geocode/json")
					.setParameter("address", location.replaceAll(",","").replaceAll(" ",""))
					.setParameter("sensor", "true")
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		HttpGet get = new HttpGet(url);			//Assign the URI to the get request
		CloseableHttpClient client = HttpClients.createDefault();	
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			jsonString = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}		
		return jsonString;



	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double lon) {
		this.longitude = lon;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double lat) {
		this.latitude = lat;
	}


	public MapMarkerDot getMarker() {
		return marker;
	}


	public void setMarker(MapMarkerDot marker) {
		this.marker = marker;
	}

	
	



}
