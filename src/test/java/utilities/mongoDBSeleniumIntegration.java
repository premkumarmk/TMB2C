package utilities;
import com.mongodb.MongoClientSettings;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.UpdateResult;

import script.RemoveHtmlTags;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    @SuppressWarnings("unchecked")
	public static List<Integer> getAnswersForFillInTheBlanks(String questionid)
    {
    	 List<Integer> answersList = null;
    	System.out.println("Inside getAnswersForFillInTheBlanks()");
    	MongoCollection<Document> questionsCollection=database.getCollection("questions");
    	//Document query= new Document("_id","61e6a00e2beec30009e55999");
    	//ObjectId documentId = new ObjectId("61e6a00e2beec30009e55999");
    	ObjectId documentId = new ObjectId(questionid);
    	 Document queryResult = questionsCollection.find(Filters.eq("_id",documentId)).first();
    	 if (queryResult != null) 
    	 {
    		  answersList = (List<Integer>)queryResult.get("fillInTheBlankSolution", List.class);
	          System.out.println("answers "+answersList);

	          for(int i=0; i<answersList.size();i++)
	          {
	        	  System.out.println(answersList.get(i));
	          }
    	 }
    	 else {
             System.out.println("Document not found");
         }
    	 
    	
		return answersList;
    	
    }
    

    public static String getSolutionType(String questionid)
    {
    	String solutionType = null;
       	System.out.println("Inside getSolutionType()");
    	MongoCollection<Document> questionsCollection=database.getCollection("questions");
    	ObjectId documentId = new ObjectId(questionid);
    	Document queryResult = questionsCollection.find(Filters.eq("_id",documentId)).first();
    	 if (queryResult != null) 
    	 {
    		//  answers = (List<Integer>)queryResult.get("description", List.class);
    		 solutionType=(String)queryResult.get("solutionType", String.class);
	          System.out.println("answer solution Type "+solutionType);
	       	          
    	 }
    	 else 
    	 {
             System.out.println("Document not found");
         }
    	
		return solutionType;
    }
    

	public static String getQuestionStatement(String questionid)
    {
    	 String questionDescription = null;
    	 String afterTrimming=null;
    	System.out.println("Inside getQuestionStatement()");
    	MongoCollection<Document> questionsCollection=database.getCollection("questions");
    	ObjectId documentId = new ObjectId(questionid);
    	 Document queryResult = questionsCollection.find(Filters.eq("_id",documentId)).first();
    	 if (queryResult != null) 
    	 {
    		 
    		//  answers = (List<Integer>)queryResult.get("description", List.class);
    		 questionDescription=(String)queryResult.get("description", String.class);
	          System.out.println("answer description "+questionDescription);
	          System.out.println("questionDescription: Before trimming: "+questionDescription);
	          
	        //  RemoveHtmlTags rmvtags=new RemoveHtmlTags();
	          //rmvtags.
	         afterTrimming= RemoveHtmlTags.removeHtmlTags(questionDescription);
	         System.out.println("questionDescription: afterTrimming: "+afterTrimming);
	          
    	 }
    	 else 
    	 {
             System.out.println("Document not found");
         }
    	 
    	
		return afterTrimming;
    	
    }
    
    @SuppressWarnings("unchecked")
	public static List<Integer> getAnswersByQuestionId(String questionid)
    {
    	List<Integer> answers = null;
    	System.out.println("Inside getAnswersByQuestionId()");
    	MongoCollection<Document> questionsCollection=database.getCollection("questions");
    	//Document query= new Document("_id","61e6a00e2beec30009e55999");
    	//ObjectId documentId = new ObjectId("61e6a00e2beec30009e55999");
    	ObjectId documentId = new ObjectId(questionid);
    	 Document queryResult = questionsCollection.find(Filters.eq("_id",documentId)).first();
    	 if (queryResult != null) 
    	 {
    		 
    		  answers = (List<Integer>)queryResult.get("solutionIndex", List.class);
	          System.out.println("answers "+answers);
	          

	          for(int i=0; i<answers.size();i++)
	          {
	        	  System.out.println(answers.get(i));
	          }

	      
//    		 Document answerOptionsDocument = queryResult.get("answerOptions", Document.class);
////    		 List<String> answerOptions = queryResult.getList("solutionIndex", String.class);
//    		// Map<Object, Object> answerOptions = answerOptionsDocument.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    		 System.out.println("Answer Options: " + answerOptions);
    	 }
    	 else {
             System.out.println("Document not found");
         }
    	 
    	
		return answers;
    	
    }
    
    
   
    public static String getSubjectIdBySubjectNameAndClassId(String subjectName, String grade) {
    	
    	System.out.println("Inside getSubjectIdBySubjectNameAndClassId()");
    	System.out.println(" variables: subjectName "+subjectName);
    	//System.out.println(" variables: classId "+classId);
    	
    	
//    	MongoClient mongoClient = MongoClients.create(connectionString);
//            MongoDatabase database = mongoClient.getDatabase(databaseName);
    	
        MongoCollection<Document> studentCollection = database.getCollection("subjects");

        // Create a query to find the document with the given email
     //   Document query = new Document("class",classId).append("name", subjectName);
       // Document query = new Document("class", classId);
//        String classId=mongoDBSeleniumIntegration.getClassIdByGrade();
        
        
        Document query = new Document("class", new ObjectId(getClassIdByGrade(grade))).append("name", subjectName);

        // Execute the query and retrieve the result
         FindIterable<Document> findIterable = studentCollection.find(query);

        // Get the first matching document
        Document document = findIterable.first();

        if (document != null) {
            // Retrieve the _id from the document
            String subId = document.getObjectId("_id").toString();
            System.out.println("Subject Id for"+subjectName+" is: "+subId);
            return subId;
        } else {
            return null;
        }
    }

public static String getClassNameByGrade(String grade) {
    	
    	System.out.println("Inside getClassIdByGrade()");
//    	MongoClient mongoClient = MongoClients.create(connectionString);
//            MongoDatabase database = mongoClient.getDatabase(databaseName);
    	
        MongoCollection<Document> studentCollection = database.getCollection("classes");

        // Create a query to find the document with the given email
        Document query = new Document("name", grade);

        // Execute the query and retrieve the result
         FindIterable<Document> findIterable = studentCollection.find(query);

        // Get the first matching document
        Document document = findIterable.first();

        if (document != null) {
            // Retrieve the _id from the document
        //    String classId = document.getObjectId("_id").toString();
            String className = document.getString("name").toString();
            System.out.println("class name for Grade "+ grade +" is: "+className);
            return className;
        } else {
            return null;
        }
    }

    
    public static String getClassIdByGrade(String grade) {
    	String classId = null;
    	System.out.println("Inside getClassIdByGrade()");
//    	MongoClient mongoClient = MongoClients.create(connectionString);
//            MongoDatabase database = mongoClient.getDatabase(databaseName);
    	
//    	{board:ObjectId('61c33124b6b2aa0009e0ddcf'),name:'III'}
    	
        MongoCollection<Document> studentCollection = database.getCollection("classes");

        // Create a query to find the document with the given email
        Document query = new Document("name", grade).append("board", new ObjectId("61c33124b6b2aa0009e0ddcf"));

        // Execute the query and retrieve the result
         FindIterable<Document> findIterable = studentCollection.find(query);

        // Get the first matching document
        Document document = findIterable.first();

        if (document != null) {
            // Retrieve the _id from the document
             classId = document.getObjectId("_id").toString();
            System.out.println("class id for Grade III is: "+classId);
           
        } else {
             System.out.println("No classId found");
        }
        return classId;
    }

    
    public static String getStudentIdByUserName(String username) {
    	
    	System.out.println("Inside getStudentIdByUserName()");
//    	MongoClient mongoClient = MongoClients.create(connectionString);
//            MongoDatabase database = mongoClient.getDatabase(databaseName);
    	
        MongoCollection<Document> studentCollection = database.getCollection("students");

        // Create a query to find the document with the given email
        Document query = new Document("userName", username);

        // Execute the query and retrieve the result
         FindIterable<Document> findIterable = studentCollection.find(query);

        // Get the first matching document
        Document document = findIterable.first();

        if (document != null) {
            // Retrieve the _id from the document
            String studId = document.getObjectId("_id").toString();
            System.out.println("Student Id for username "+"username"+" is: "+studId);
            return studId;
        } else {
            return null;
        }
    }

    
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
	    
//	    public static Date addDays(Date date, int days) {
//	        Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(date);
//	        calendar.add(Calendar.DAY_OF_YEAR, days);
//	        return calendar.getTime();
//	    }
	    
//	    public static String changeDate() {
//	    	Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(new Date());
//	        calendar.add(Calendar.DAY_OF_YEAR, -7);
//	        Date oneWeekBefore = calendar.getTime();
//	        
//	     // Create a SimpleDateFormat with the desired date format
//	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//
//	        // Format a custom date string for the one week before date
//	        String customDateOneWeekBefore = dateFormat.format(oneWeekBefore);
//	        return customDateOneWeekBefore;
//	    }
	    
// public static int getAnswer() {
//	    	
//	 System.out.println("Inside getAnswer()");
//	    	Object firstElement;			
//			int intValue = 0;
////	    	System.out.println("Tesxt value"+queText);
//	    
//	        
//	    	MongoCollection<Document> questionCollection = database.getCollection("questions");
//	    	Document query = new Document("description","<p><span id=\"docs-internal-guid-25fb84ed-7fff-5c64-44a2-825f5a642211\"><span style=\"font-size: 13pt; font-family: &quot;times new roman&quot;; font-variant-numeric: normal; font-variant-east-asian: normal; vertical-align: baseline; white-space: pre-wrap;\">Pick the word and its Synonym:</span></span></p>");
//	    	FindIterable<Document> find = questionCollection.find(query);
//	    	Document result = find.first();
////	    	System.out.println("STEP -1");
//	    	System.out.println("result is:"+result);
//	//    	  Getting "solutionIndex" array list
//	        List<Object> solutionIndexArray = result.getList("solutionIndex", Object.class);
//	       System.out.println("Printing solutionIndexArray");
//	       System.out.println(solutionIndexArray);
//	        if (!solutionIndexArray.isEmpty()) {
//	            // Get the first element from the array
//	        	System.out.println("Inside LIST");
//	             firstElement = solutionIndexArray.get(0);
//	            System.out.println("First element of 'solutionIndex' array: " + firstElement);
//	            intValue = Integer.parseInt(firstElement.toString());
//	            System.out.println(intValue);
//	            System.out.println("Result....................");
//	            
//	        }
//	        else {
//	            System.out.println("The 'solutionIndex' array is empty.");
//	        }
//			return intValue;
//	        
//	    }

}

	
	


