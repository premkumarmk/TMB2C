package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;


public class CompareQuestions extends BaseTest
{
		public static Map<String, Integer> map = new HashMap<String, Integer>();
		public static Map<String, String> mapOfQuestionId = new HashMap<String, String>();
		SoftAssert softAssert = new SoftAssert();
		
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
		 
		 public static boolean SearchQuestionIdAndInsertToHashMap(String questionTextFromDB, String questionId) 
		 {
			 	//String searchKey = questionText;

		        // Check if the search key exists in the hashmap
		        if (!mapOfQuestionId.containsValue(questionId)) 
		        {
		            // Insert the new key if it doesn't exist
		        	mapOfQuestionId.put(questionTextFromDB,questionId);
		            System.out.println("Inserted Question ID and Question Description From DB: " + questionId +", "+questionTextFromDB);
		            return true;
		        } 
		        else 
		        {
		        	mapOfQuestionId.put(questionTextFromDB,questionId);
		            System.out.println("Question Id already exists: " + questionId +", "+ questionTextFromDB );
		            return false;
		        }

		        // Print the updated list
		       
			
		}
		
		public static boolean isNullOrEmptyMap(Map < ? , ? > map) 
		 {
			 System.out.println("INside isNullOrEmptyMap() method");
		        return (map == null || map.isEmpty());
		 }

		
		public void addQuestionToMap(String question)
		{
			 System.out.println("INside addQuestionToMap() method");
			int count=0;
			if(isNullOrEmptyMap(map))
			{
				
				System.out.println("HashTable is empty");
				System.out.println("\nINserting FIRST Question: "+ question+"\n");
				map.put(question, 1);
				//QuestionsMap.
			}
			else
			{
				System.out.println("HashTable is NOT empty");
				System.out.println("\nINserting Question: "+ question+"\n");
				if(map.containsKey(question))
				{
					count = map.containsKey(question) ? map.get(question) : 0;
					map.put(question, count+1);
				}
				
				
			}
			
			
		}
		
		public void displayHashTable()
		{
			System.out.println("INside displayHashTable() method");
			for(Map.Entry<String, Integer> entry : map.entrySet()) 
			{
//				if(entry.getValue()>=0) 
//				{
					System.out.println(entry.getKey() +"==>"+ entry.getValue());
//				}
				
			}
		}

		public static void scrollDown(WebElement element)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}


}
