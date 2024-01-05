package utilities;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class mongoDBSeleniumIntegration {
	
//	public static FindIterable<Document> findIterable;
	static MongoCollection<Document> studentCollection;
	static Date oneWeekBefore;

  // 	static String studentEmailToFind = "sakthivel@codewave.com"; // Replace with the actual student email
   	static String studentEmailToFind = "premkumar@tautmore.com";
    static String connectionString = "mongodb+srv://tautmore-dev:tautMore%242021@tmcw2m10.t7eym.mongodb.net/";
//    mongodb+srv://tautmore-dev:tautMore%242021@tmcw2m10.t7eym.mongodb.net/test?authSource=admin&replicaSet=atlas-4odvzd-shard-0&readPreference=primary&ssl=true
   static String databaseName = "tautmore-development"; // Replace with the actual database name
   static String studentname = "autostudentone";
   
	static MongoClient mongoClient = MongoClients.create(connectionString);
    static MongoDatabase database = mongoClient.getDatabase(databaseName);

//    public void allConnections() throws Exception{
//    try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//        MongoDatabase database = mongoClient.getDatabase(databaseName);
//
//        // Step 1: Fetch _id from the "student" collection based on email
//        String studentId = getStudentIdByEmail();
//
//        if (studentId != null) {
//            System.out.println("Student ID for email " + studentEmailToFind + ": " + studentId);
//
//            // Step 2: Update "braingymmappings" collection based on studentId with a custom date
////            Date customDate = new Date(System.currentTimeMillis() - 86400000L); // Set your custom timestamp
//            updateBrainGymMappings( studentId);
//        } else {
//            System.out.println("No student found for email: " + studentEmailToFind);
//        }
//} 
//	        }

	    public static String getStudentIdByEmail() {
	    	
	    	System.out.println("Inside getStudentIdByEmail()");
//	    	MongoClient mongoClient = MongoClients.create(connectionString);
//	            MongoDatabase database = mongoClient.getDatabase(databaseName);
	    	
	        MongoCollection<Document> studentCollection = database.getCollection("students");

	        // Create a query to find the document with the given email
	        Document query = new Document("email", studentEmailToFind).append("studentName",studentname );

	        // Execute the query and retrieve the result
	         FindIterable<Document> findIterable = studentCollection.find(query);

	        // Get the first matching document
	        Document document = findIterable.first();

	        if (document != null) {
	            // Retrieve the _id from the document
	            String stud = document.getObjectId("_id").toString();
	            System.out.println(stud);
	            return stud;
	        } else {
	            return null;
	        }
	    }

	    public static void updateBrainGymMappings(String studentId) throws Exception {
	    	System.out.println("Inside updateBrainGymMappings");
	        MongoCollection<Document> brainGymMastersCollection = database.getCollection("braingymmasters");

	     // Create a query to find documents with the given studentId
	        Document query = new Document("student", new ObjectId(studentId));
	        
	     // Add a sort query to the existing query
//	       Document sorted = query.append("$orderBy", new Document("updatedAt", -1));
	          FindIterable<Document> findIterable = brainGymMastersCollection.find(query).sort(Sorts.descending("updatedAt"));
	        		
	       
	       Document change = findIterable.first();
	       
	      System.out.println(brainGymMastersCollection.countDocuments(query));
//	       System.out.println(Integer.parseInt(changeDate()));
	       
	       System.out.println("First Record:"+change);

	     
	        // Create a document with the new values for createdAt and updatedAt
	        Document update = new Document("$set", new Document("createdAt",changeDate1()).append("updatedAt",changeDate1()).append("createdAtNew", changeDate1()));
	    
	        // Update documents in the "braingymmappings" collection based on the query
	        UpdateResult updateResult = brainGymMastersCollection.updateOne(change, update);

	        // Print the number of documents updated
	        System.out.println("Number of documents updated in braingymasters: " + updateResult.getModifiedCount());
	    }

	    public static void updateBrainGymMappings1(String studentId,int count) throws Exception {
	    
	    	System.out.println("Inside updateBrainGymMappings1");
	        MongoCollection<Document> brainGymMastersCollection = database.getCollection("braingymmasters");

	     // Create a query to find documents with the given studentId
	        Document query = new Document("student", new ObjectId(studentId));
	        
	     // Add a sort query to the existing query
//	       Document sorted = query.append("$orderBy", new Document("updatedAt", -1));
	          FindIterable<Document> findIterable = brainGymMastersCollection.find(query).sort(Sorts.descending("updatedAt"));
	        		
	       
	       Document change = findIterable.first();
	       
	      System.out.println(brainGymMastersCollection.countDocuments(query));
//	       System.out.println(Integer.parseInt(changeDate()));
	       
	       System.out.println("First Record:"+change);
	       
	       System.out.println(brainGymMastersCollection.countDocuments(query));
//	       System.out.println(Integer.parseInt(changeDate()));
	       
//	       System.out.println("First Record:"+change);
//	        
//	        // Get the date that is one week before the current date
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DAY_OF_YEAR, -7);
	        Date oneWeekBefore = calendar.getTime();
//	        
//	        System.out.println("One week before:"+oneWeekBefore);
	      // Calendar calendar = Calendar.getInstance();
	        calendar.setTime(oneWeekBefore);
	        calendar.add(Calendar.DAY_OF_YEAR, count);
	        Date updateddays = calendar.getTime();

	     
	        // Create a document with the new values for createdAt and updatedAt
	        Document update = new Document("$set", new Document("createdAt",updateddays).append("updatedAt",updateddays).append("createdAtNew", updateddays));
	    
	        // Update documents in the "braingymmappings" collection based on the query
	        UpdateResult updateResult = brainGymMastersCollection.updateOne(change, update);

	        // Print the number of documents updated
	        System.out.println("Number of documents updated in braingymasters: " + updateResult.getModifiedCount());
	    }

	    
	    public static Date changeDate1() throws Exception {
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DAY_OF_YEAR, -7);
	        Date oneWeekBefore = calendar.getTime();
	        
	        // Create a SimpleDateFormat with the desired date format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	        // Format a custom date string for the one week before date
	        String customDateOneWeekBefore = dateFormat.format(oneWeekBefore);
	        Date abc = dateFormat.parse(customDateOneWeekBefore);
	        return abc;
	    }
	    
	    public static Date addDays(Date date, int days) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.DAY_OF_YEAR, days);
	        return calendar.getTime();
	    }
	    
	    public static String changeDate() {
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DAY_OF_YEAR, -7);
	        Date oneWeekBefore = calendar.getTime();
	        
	     // Create a SimpleDateFormat with the desired date format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	        // Format a custom date string for the one week before date
	        String customDateOneWeekBefore = dateFormat.format(oneWeekBefore);
	        return customDateOneWeekBefore;
	    }
}

	
	


