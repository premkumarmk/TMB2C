package utilities;

import org.json.JSONObject;

import generic.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import kong.unirest.HttpResponse;
import kong.unirest.core.Unirest;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.util.Base64;
import java.util.List;

public class ToReadResponse {
	static int count=1;
	public static void main(String[] args) throws Exception 
	{
		//updateDatabase();
//		String token=getUserAuthToken();
//		System.err.println("In main: token is:" +token);
		
//		String questionId= sendAuthAndGetResponseQuestionId();
//		System.out.println("Printing QuestionId is: "+questionId);
		
		//mongoDBSeleniumIntegration.getAnswersByQuestionId("61e6a00e2beec30009e55999");
		//mongoDBSeleniumIntegration.getAnswersByQuestionId();
//		List<String> chestIds=getChestIds();
//		int count=1;
//		for(String s:chestIds)
//		{
//			System.out.println(count+"-->"+s);
//			count++;
//		}
		
//		getQuestionId();
//	       String originalString = "stuPwd906040";
//
//	        // Encode the string to Base64
//	        String encodedString = base64Encode(originalString);
//
//	        // Print the original and encoded strings
//	        System.out.println("Original String: " + originalString);
//	        System.out.println("Encoded String: " + encodedString);
	    
		//userRegistrationSuccessful();
	mongoDBSeleniumIntegration.getSubjectIdBySubjectNameAndClassId("Science", "III");
		
		
	}

	 private static String base64Encode(String originalString) {
	        // Encode the string to Base64
	        byte[] encodedBytes = Base64.getEncoder().encode(originalString.getBytes());

	        // Convert the byte array to a string
	        return new String(encodedBytes);
	    }
	 
	public static void updateDatabase() throws Exception
	{
		UpdateDB.changeDate(1);
		Thread.sleep(4000);
	} //I want to read ChestId by API by sending token,wrote method getChestId(), but not working

	
	
	
	public static List<String> getChestIds(String accessToken, String studentId,String subjectId) {
		System.out.println("Inside CHestID method");
		System.out.println("accessToken is: "+accessToken);
		System.out.println("Student ID is: "+studentId);
		System.out.println("Subject Id is: "+subjectId);
		
		 List<String> chestIds=null;
//       RestAssured.baseURI = "https://dev.tautmore.com/api";
		 RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
		 RequestSpecification request = RestAssured.given();
		//String accessToken = userRegistrationSuccessful();
		 JSONObject requestParams = new JSONObject();
//		 requestParams.put("studentId", "659661083a72690008952122");
//		 requestParams.put("subjectId", "61cc6753c32134a3653b3131");
		 
		 requestParams.put("studentId", studentId);
		 requestParams.put("subjectId", subjectId);
		 
		//String accessToken=accessToken;
		//https://dev.tautmore.com/api/brain-gym/start-test
		 Response response = given()
	                //.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjU5NjYxMDgzYTcyNjkwMDA4OTUyMTIyIiwidXNlclR5cGUiOiJTVFVERU5UIiwiRmNtRG9jSWQiOiI2NTk2NjIzZDI1MTZiNzAwMDgzNjdlYjAiLCJpYXQiOjE3MDU1OTI1MjUsImV4cCI6MTcwNTY3ODkyNX0.VuqkRbnOhizpbO3hqRnVzVPRVRV0e8qB5jyF-YeKobA")
				 	.header("Authorization", accessToken)
	                .header("Content-Type", "application/json")
	                .body(requestParams.toString())
	                .log()
	                .all()
	                .when()
	                .post("/brain-gym/start-test")
	                .then()
	                .extract().response();
		
		// System.out.println("Body:   " + response.asPrettyString());
		// System.out.println(jsonpath.toString());
		 //System.out.println("Status Code: " + response.getStatusCode());
	    // System.out.println("Body:" +response.getBody().asPrettyString());
		  JsonPath jsonpath= response.jsonPath();
		 //String chest = jsonpath.getString("data.chests[0]._id");
		 chestIds = jsonpath.getList("data.chests._id",String.class);
		 if(!chestIds.isEmpty())
		 {
		 System.out.println("chest "+chestIds);
		 }
		 System.out.println("ENd of Inside CHestID method ");
		return chestIds;
		
	}
	
	
	public static String  getAnswerAttemptResponse(String accessToken, String chestId, String questionId) {

		
		System.out.println("Inside getAnswerAttemptResponse (): repeated ");

		System.out.println("accessToken is: "+accessToken);
		System.out.println("ChestId is: "+chestId);
		System.out.println("questionId is: "+questionId);
        // Set the base URI for your API
//      RestAssured.baseURI = "https://dev.tautmore.com/api";
        RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
       
        JSONObject requestParams = new JSONObject();
        requestParams.put("chestId", chestId);
        requestParams.put("questionId", questionId);

      // " {"chestId":"65d880a70f93810008599791","questionId":"62b5c06602a32d0009b9db8a","attemptedSolutionIndex":[2],"
     //  		+ ""attemptedFillInTheBlankSolution":null,"timeTaken":68}" "
      
        //{"chestId":"65d89bc36c60590008ae946c","questionId":"62b54fd7f6914800091f664a","attemptedSolutionIndex":[0,1],"attemptedFillInTheBlankSolution":null,"timeTaken":20}
        
        //  request.header("Content-Type", "application/json");

        Response response = given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(requestParams.toString())
                .log()
                .all()
                .when()
                .post("/brainGym/attempt-question")
                .then()
                .extract().response();

        // Print the response body
        String responseBody = response.getBody().asString();
        System.out.println("getAnswerAttemptResponse Response Body: " + responseBody);

     //   System.out.println("getAnswerAttemptResponse() Response Body:" +response.getBody());
        JsonPath jsonpath= response.jsonPath();
       
       // System.out.println("response of data.message is:"+res1);
        System.out.println("Status code is: "+jsonpath.get("statusCode"));
        System.out.println("Status is: "+jsonpath.get("status"));
        System.out.println("message code is: "+jsonpath.get("message"));
        
      
        return jsonpath.get("message"); //
    }	
	
	
	public static String  getQuestionId(String accessToken, String chestId) {

		
		System.out.println("Inside getQuestionId (): repeated "+count);
		count++;
		System.out.println("accessToken is: "+accessToken);
		System.out.println("ChestId is: "+chestId);
        // Set the base URI for your API
//      RestAssured.baseURI = "https://dev.tautmore.com/api";
        RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
       
       // String accessToken = userRegistrationSuccessful(); //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjU5NjYxMDgzYTcyNjkwMDA4OTUyMTIyIiwidXNlclR5cGUiOiJTVFVERU5UIiwiRmNtRG9jSWQiOiI2NTk2NjIzZDI1MTZiNzAwMDgzNjdlYjAiLCJpYXQiOjE3MDU0MTI0NjcsImV4cCI6MTcwNTQ5ODg2N30.RixEvV3T1pwtKJfVpgI5-TXizVcrD6Mh23DdCyipxKE";
      //  System.out.println("Access token is: "+accessToken);
     //   RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("chestId", chestId);
        //  request.header("Content-Type", "application/json");

        Response response = given()
                .header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(requestParams.toString())
                .log()
                .all()
                .when()
                .post("/brainGym/question-in-chest")
                .then()
                .extract().response();

        // Print the response body
        String responseBody = response.getBody().asString();
      //  System.out.println("Response Body: " + responseBody);

        System.out.println("getQuestionId() Response Body:" +response.getBody());
        JsonPath jsonpath= response.jsonPath();
        String  questionId= jsonpath.getString("data[0]._id");
        System.out.println("Question ID is:"+questionId);
        return questionId;

    }
//	public static String userRegistrationSuccessful(String un, String pw) 
	public static String userRegistrationSuccessful(String un, String pw){

	       // RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
	       // RestAssured.baseURI = "https://dev.tautmore.com/api";
			RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
	        RequestSpecification request = RestAssured.given();
	        JSONObject requestParams = new JSONObject();
	        
	  
	        
	      //  requestParams.put("userName", "autostudentone.9606178621");
	        requestParams.put("userName", un);
	     //   System.out.println("MAnually method"+convertToBinaryManually("stuPwd906040"));
	        //requestParams.put("password", Base64.getEncoder().encode("stuPwd906040".getBytes()));
	    //    System.out.println("BAse64 method String: " + Base64.getEncoder().encode(convertToBinaryManually("stuPwd906040").getBytes()));
	   //    String pwd= base64Encode("stuPwd906040");
	      //  byte[] encodedBytes = Base64.getEncoder().encode(base64Encode("stuPwd906040").getBytes());	        
	   //    System.out.println("New method: "+base64Encode("stuPwd906040"));
	       // String pwd=base64Encode("stuPwd906040");
	        String pwd=base64Encode(pw);
	        System.out.println("====>"+pwd);
	      //   requestParams.put("password", "c3R1UHdkOTA2MDQw");
	        requestParams.put("password", pwd);
	        requestParams.put("userType", "parent");
	        // userName: "autostudentone.9606178621", password: "c3R1UHdkOTA2MDQw", userType: "parent"

	        // request.body(requestParams);

	        Response response = given()
	                .header("Content-type", "application/json")
	                .and()
	                .body(requestParams.toString())	
	                .log()
	                .all()
	                .when()
	                .post("/students/login")
	                .then()
	                .extract().response();
	        
//	        .log()
//            .all()

	        System.out.println("Inside login Aut method" + response);

	            JSONObject json = new JSONObject(response.getBody().print());
	            System.out.println("Body:" + json);

	        JsonPath jsonpath= response.jsonPath();
	        String accessToken = jsonpath.getString("data.accessToken");
	        System.out.println("Inside userRegistrationSuccessful() accesstoken:   " +accessToken); //2
	        return accessToken;

	    }
	
	
	    public static String convertToBinaryManually(String input) {
	        StringBuilder binaryString = new StringBuilder();

	        for (char c : input.toCharArray()) {
	            String binary = Integer.toBinaryString(c);
	            binaryString.append(String.format("%16s", binary).replace(' ', '0'));
	        }

	        return binaryString.toString();
	    }

}
//public static void sendAuthAndGetResponse() {
//
//        // Set the base URI for your API
//         RestAssured.baseURI = "https://dev.tautmore.com/api";
//
//        // Replace "your_access_token" with your actual access token
//        String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNjU5NjYxMDgzYTcyNjkwMDA4OTUyMTIyIiwidXNlclR5cGUiOiJTVFVERU5UIiwiRmNtRG9jSWQiOiI2NTk2NjIzZDI1MTZiNzAwMDgzNjdlYjAiLCJpYXQiOjE3MDU0NzUxNDgsImV4cCI6MTcwNTU2MTU0OH0.r9Ze6_T8cn4LJSbM3t07KUjeUOlE6Ge6T90R-RZLiAU";
//
//        RequestSpecification request = RestAssured.given(); 
//		JSONObject requestParams = new JSONObject();	
//       requestParams.put("chestId", "65a77262ceca5a000878bb7a");
//	  //  request.header("Content-Type", "application/json");
//        
//        Response response = given()
//                .header("Authorization", accessToken)
//                .header("Content-Type", "application/json")
//                .and()
//                .body(requestParams.toString())
//                .log()
//                .all()
//                .when()
//                .get("/brainGym/question-in-chest")
//                .then()
//                .extract().response();
//
//        // Print the response body
//        String responseBody = response.getBody().asString();
//        System.out.println("Response Body: " + responseBody);
//        int apiStatusCode = response.getStatusCode();
//        System.out.println("API Response Status Code: " + apiStatusCode);
//    
//      
////        System.out.println("Body:" +response.getBody().asPrettyString());
////        JsonPath jsonpath= response.jsonPath();
////	     String  id= jsonpath.getString("data.status");
////	     System.out.println("ID is:"+id);
////		return "y";
//
//}
//public static String getUserAuthToken() 
//{ 
//	//RestAssured.baseURI = "https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api";
//	
//		RestAssured.baseURI = "https://dev.tautmore.com/api";
//	
//	RequestSpecification request = RestAssured.given(); 
//	JSONObject requestParams = new JSONObject();		
//	requestParams.put("userName", "autostudentone.9606178621");
// //   requestParams.put("userName", "three.9393688889");
//    requestParams.put("password", "c3R1UHdkOTA2MDQw");
////    requestParams.put("password", "VGF1dEAyMDIz");
//    requestParams.put("userType", "parent");
//    request.header("Content-Type", "application/json");
//
//     Response response = given()
//                .header("Content-type", "application/json")
//                .and()
//                .body(requestParams.toString())
//                .log()
//                .all()
//                .when()
//                .post("/students/login")
//                .then()
//                .extract().response();
//     
//     System.out.println("Inside login Aut method"+response);
//     System.out.println("Body:" +response.getBody().asPrettyString());
//     JsonPath jsonpath= response.jsonPath();
//     String token = jsonpath.getString("data.accessToken");
//     System.out.println("\n\naccesstoke" +token); //2
//	return token;
//
//}
//}