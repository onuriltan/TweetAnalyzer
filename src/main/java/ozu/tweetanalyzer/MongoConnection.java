package ozu.tweetanalyzer;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoConnection {
	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	MongoDatabase db = mongoClient.getDatabase("TwitterDatabase");
	MongoCollection<Document> coll;	


	
	public MongoConnection(String collectionName){
		this.coll=db.getCollection(collectionName);
	}
	
	
}
