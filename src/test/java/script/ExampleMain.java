package script;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ExampleMain {

	@Test
	public void ExampleMain()
	{
		SoftAssert softAssert = new SoftAssert();
		ArrayList<String> questions= new ArrayList<String>();
		 questions.add("What is your name?");
		 questions.add("How old are you?");
		 questions.add("Where do you live?");
		 questions.add("What is your favorite color?");
		 questions.add("What is your job?");
		 questions.add("What is your name?");
		 questions.add("What is your favorite color?");
		 questions.add("What is your Shirt color?");
	        
		//for(String q: questions)
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
		softAssert.assertAll();
	}

}
