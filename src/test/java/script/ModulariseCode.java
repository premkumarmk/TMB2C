
package script;

import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.ExcelConstructionExample;
import utilities.JS;
import utilities.ToReadResponse;
import utilities.mongoDBSeleniumIntegration;

public class ModulariseCode extends BaseTest
{	public static String accessToken=null;
	
	@DataProvider(name = "data-set")
    public static Object[][] DataSet() throws Exception 
 	{
       
        Excel excel = new Excel();

        Object[][] obj = excel.to2DArray("./data/userIds.xlsx", "user_ids");
        return obj;
    }

	 @Test(dataProvider="data-set")
	public void takeTest(String un, String pw, String grade, String subject) throws Exception 
	{
//		 System.out.println("Before JS");
//		//JS.setZoomLevel(driver,0.75);
//		System.out.println("After JS");
	//	seleniumTest(driver);
			ObjectId studentId = new ObjectId();
	        ObjectId subjectId = new ObjectId();
		 studentId=mongoDBSeleniumIntegration.getStudentIdByUserName(un);
//		System.out.println("getStudentIdByUserName: "+studentId);
		 
		String classId=mongoDBSeleniumIntegration.getClassIdByGrade(grade);
//		System.out.println("getClassIdByGrade: "+classId);
		
		 subjectId=mongoDBSeleniumIntegration.getSubjectIdBySubjectNameAndClassId(subject, classId);
	//	System.out.println("Subject Id for Science is : "+subjectId);
		
		String className= mongoDBSeleniumIntegration.getClassNameByGrade(grade);
//		System.out.println("getClassNameByGrade: "+className); 
		
	
		
		BrainGymPage brainGym= new BrainGymPage(driver);
		String accessToken=null;
		try 
		{
			accessToken = brainGym.login(un, pw, grade, subject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		Thread.sleep(20000);		
		
		//brainGym.clickLogout();
		if(!accessToken.equals(null))
		{
			System.out.println("In main(), after login()");
			System.out.println("In main (),accessToken is: "+accessToken);
			
			brainGym.numberOfDaystoRun(driver, wait,accessToken, un, pw, grade, subject, studentId, subjectId, BrainGymPage.numberOfDaysToRun);
			//brainGym.testOneShell(driver, wait, un, pw, grade, subject,shellStatus);
		
			System.out.println("1 rounds of shells completed");	
			ExcelConstructionExample.writeToExcel();
			
		}
		else
		{
			System.out.println("!!!!!!!!!!!  Error, Login Session Error !!!!!!!!!!!!");
		}
		System.out.println("Script Completed.....");
		
		brainGym.clickLogout();
	}
}
