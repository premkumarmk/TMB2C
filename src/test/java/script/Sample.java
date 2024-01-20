package script;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.Excel;
import page.LoginPage;
import generic.BaseTest;


public class Sample extends BaseTest {
		 	
   // public static List<String> list = new ArrayList<>();
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static Map<String, Integer> stringIntHashMap = new HashMap<String, Integer>();
	 @Test(priority = 3, dataProvider="data-set")
	 public void tryMultiLogin(String un, String pw, String grade, String subject) throws InterruptedException {
			System.out.println(un);
			System.out.println(pw);
			System.out.println(grade);
			System.out.println(subject);
			LoginPage login=new LoginPage();
			//	mongoDB.getStudentIdByEmail("abdul@codewave.com");
				login.clickOnloginBtnOnDashboard();
				Thread.sleep(1000);
				login.clickOnOptionLoginAs();
				Thread.sleep(1000);
				login.clickOnNext();
				Thread.sleep(1000);
				login.setUserName(un);
				
				login.setPassword(pw);
				
				
				//login.clickLoginButton();
			
		 
	 }

public void selectQuestionType()
{
	stringIntHashMap.put("Complete the sentence with the correct form of the adverb:", 1);
    stringIntHashMap.put("Read the text and drag the correct adverb/ adjective in the blanks:", 2);
    stringIntHashMap.put("choose-the-correct-answer", 3);
    stringIntHashMap.put("Multi select question", 4);
    stringIntHashMap.put("match-the-following", 5);
    stringIntHashMap.put("drag and drop", 6);
    stringIntHashMap.put("choose-the-correct-answer", 7);
    
    Boolean isTextPresent;
    for (Map.Entry<String, Integer> entry : stringIntHashMap.entrySet()) 
    {
    	String key = entry.getKey();
        Integer value = entry.getValue();
        isTextPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.includes(arguments[0]);", key);
        if(isTextPresent)
        {
        	System.out.println("Question is:"+key+" with key value"+value);
        
        	 switch(value)
     	    {
     	    case 1:
     	    	System.out.println("Found:"+ key);
     	    	return;
     	    	
     	    	
     	    case 2:
     	    	System.out.println("Found:"+ key);
     	    	return;
     	    
     	    case 3:
     	    	System.out.println("Found:"+ key);
     	    	return;
    	    	
     	   	case 4:
     	   		System.out.println("Found:"+ key);
     	   		return;
     	   		
     	   	case 5:
    	    	System.out.println("Found:"+ key);
    	    	return;
    	    
    	    case 6:
    	    	System.out.println("Found:"+ key);
    	    	return;
   	    	
    	   	case 7:
    	   		System.out.println("Found:"+ key);
    	   		return;
     	    	
     	    }
        	
        }
        
	   
    }
    
}
	

	 
	 @Test
	 public static boolean SearchAndInsertToHashMap(String questionText) 
	 {
		 	//String searchKey = questionText;

	        // Check if the search key exists in the hashmap
	        if (!map.containsKey(questionText)) 
	        {
	            // Insert the new key if it doesn't exist
	            map.put(questionText,1);
	            System.out.println("Inserted: " + questionText);
	            return true;
	        } 
	        else 
	        {
	        	map.put(questionText,map.get(questionText)+1);
	            System.out.println("Search key already exists: " + questionText);
	            return false;
	        }

	        // Print the updated list
	       
		
	}
}
	
	