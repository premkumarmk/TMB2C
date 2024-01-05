package utilities;

import java.util.HashMap;
import java.util.Map;

public class CompareQuestions 
{

	
		Map<String, Integer> QuestionsMap = new HashMap<String, Integer>();
		
		public static boolean isNullOrEmptyMap(Map < ? , ? > map) 
		 {
			 System.out.println("INside isNullOrEmptyMap() method");
		        return (map == null || map.isEmpty());
		 }

		
		public void addQuestionToMap(String question)
		{
			 System.out.println("INside addQuestionToMap() method");
			int count=0;
			if(isNullOrEmptyMap(QuestionsMap))
			{
				
				System.out.println("HashTable is empty");
				System.out.println("\nINserting FIRST Question: "+ question+"\n");
				QuestionsMap.put(question, 1);
				//QuestionsMap.
			}
			else
			{
				System.out.println("HashTable is NOT empty");
				System.out.println("\nINserting Question: "+ question+"\n");
				if(QuestionsMap.containsKey(question))
				{
					count = QuestionsMap.containsKey(question) ? QuestionsMap.get(question) : 0;
					QuestionsMap.put(question, count+1);
				}
				
				
			}
			
			
		}
		
		public void displayHashTable()
		{
			System.out.println("INside displayHashTable() method");
			for(Map.Entry<String, Integer> entry : QuestionsMap.entrySet()) 
			{
//				if(entry.getValue()>=0) 
//				{
					System.out.println(entry.getKey() +"==>"+ entry.getValue());
//				}
				
			}
		}




}
