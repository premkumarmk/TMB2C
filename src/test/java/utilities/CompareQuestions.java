package utilities;

import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import script.Sample;

public class CompareQuestions 
{
		public static Map<String, Integer> map = new HashMap<String, Integer>();
		SoftAssert softAssert = new SoftAssert();
		//Map<String, Integer> QuestionsMap = new HashMap<String, Integer>();
		
		public void processQuestion()
		{
			if(Sample.SearchAndInsertToHashMap("What is your favorite color?"))
			{
				softAssert.assertTrue(true, "What is your favorite color?");
				System.out.println("IF");
				Reporter.log("Failed: Duplicate Found");
				//Write code to write into Excel
				
			}
			else
			{
				softAssert.assertTrue(false, "What is your favorite color?");
				System.out.println("ELSE");
				//Write code to write into Excel
			}
		}
		
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

		


}
