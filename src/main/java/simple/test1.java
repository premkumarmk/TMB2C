package simple;

import java.util.Date;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class test1 {

	public static void main() {
		getStudentIdByEmail();
	}
	public static void dbEmailverification(String semail, String value) {
		
		
	    ConnectionString connection = new ConnectionString("mongodb+srv://tautmore-dev:tautMore%242021@tmcw2m10.t7eym.mongodb.net/");
	    MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connection).build();
	    MongoClient mongoClient = MongoClients.create(settings);
	    MongoDatabase database = mongoClient.getDatabase("tautmore-development");
	    MongoCollection<org.bson.Document> collection = database.getCollection("parents");

	    MongoCursor<org.bson.Document> cursor = collection.find().iterator();
	 
	    Document filter = new Document();
	    filter.append("email", semail);

	    Document update = new Document("$set", new Document("emailVerified", value));

	              
	              collection.updateOne(filter, update);    
	        
	  	    cursor.close();
	    mongoClient.close();
	
		 }
	
	public static void getStudentIdByEmail() {
		System.out.println("Inside method");
		
	    ConnectionString connection = new ConnectionString("mongodb+srv://tautmore-dev:tautMore%242021@tmcw2m10.t7eym.mongodb.net/");
	    MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connection).build();
	    MongoClient mongoClient = MongoClients.create(settings);
	    MongoDatabase database = mongoClient.getDatabase("tautmore-development");
	    MongoCollection<org.bson.Document> collection = database.getCollection("student");
//	    System.out.println(collection);
 

//        MongoCollection<Document> studentCollection = database.getCollection("student");

//	    MongoCursor<org.bson.Document> cursor = collection.find().iterator();
//        // Create a query to find the document with the given email
//        Document query = new Document("email", email);

        // Execute the query and retrieve the result
        FindIterable<Document> findIterable = collection.find();
        Iterator it =  findIterable.iterator();
        
        while (it.hasNext()) {
			System.out.println(it.next());
			
		}
//
//        // Get the first matching document
//        Document document = findIterable.first();
//
//        if (document != null) {
//        	System.out.println("Inside if");
//            // Retrieve the _id from the document
//            return document.getObjectId("_id").toString();
//        } else {
//        	System.out.println("Inside else");
//            return null;
//        }
    }
//	
		 }
	
	
	
