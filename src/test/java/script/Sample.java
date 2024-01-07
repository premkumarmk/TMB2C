package script;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 @Test(priority = 3, dataProvider="data-set")
	 public void tryMultiLogin(String un, String pw, String grade, String subject) throws InterruptedException {
			System.out.println(un);
			System.out.println(pw);
			System.out.println(grade);
			System.out.println(subject);
			LoginPage login=new LoginPage(driver);
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
	
	