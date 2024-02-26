
package script;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.EmailIntegration;
import utilities.ExcelConstructionExample;
import utilities.mongoDBSeleniumIntegration;

public class RunBGScript extends BaseTest
{	public static String accessToken=null;
	public static int numberOfDaysToRun;
	public static String browserType;
	public static String appURL;
	public static int ITO;
	public static int  ETO;
	public static String connectionString;
	public static String databaseName;

	RunBGScript() throws IOException
	{
		System.out.println("Inside constructor");
		Properties properties= new Properties();
		try
		{
			InputStream inputFile=new FileInputStream("./configuration.properties");
			properties.load(inputFile);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		numberOfDaysToRun=Integer.parseInt(properties.getProperty("numberOfDaysToRun"));
		browserType=properties.getProperty("browserType");
		appURL=properties.getProperty("appURL");
		ITO= Integer.parseInt(properties.getProperty("ImplicitTimeOut"));
		ETO= Integer.parseInt(properties.getProperty("ExplicitTimeOut"));
		
		connectionString = properties.getProperty("connectionString");
		databaseName = properties.getProperty("databaseName");
		
		System.out.println("numberOfDaysToRun : "+numberOfDaysToRun);
		System.out.println("browserType : "+browserType);
		System.out.println("appURL : "+appURL);
		System.out.println("ITO : " +ITO );
		System.out.println("ETO : " +ETO );
		System.out.println("connectionString : " +connectionString);
		System.out.println("databaseName : " +databaseName);
		
		
	}


	@DataProvider(name = "data-set")
    public static Object[][] DataSet() throws Exception 
 	{
       
        Excel excel = new Excel();

     //  Object[][] obj = excel.to2DArray("./data/userIds.xlsx", "user_ids");
       Object[][] obj = excel.to2DArray("./data/userIds.xlsx", "prod_input");
        return obj;
    }

	@Test(dataProvider="data-set")
	public void takeTest(String un, String pw, String grade, String subject) throws Exception 
	{
		System.out.println("Inside takeTest()");
//		 System.out.println("Before JS");
//		//JS.setZoomLevel(driver,0.75);
//		System.out.println("After JS");
	//	seleniumTest(driver);
		String studentId =null;
		String subjectId = null;
		studentId=mongoDBSeleniumIntegration.getStudentIdByUserName(un);
		System.out.println("getStudentIdByUserName: "+studentId);
		 
		String classId=mongoDBSeleniumIntegration.getClassIdByGrade(grade);
		System.out.println("getClassIdByGrade: "+classId);
		
		 subjectId=mongoDBSeleniumIntegration.getSubjectIdBySubjectNameAndClassId(subject, grade);
		System.out.println("Subject Id is : "+subjectId);
		
		String className= mongoDBSeleniumIntegration.getClassNameByGrade(grade);
		System.out.println("getClassNameByGrade: "+className); 
		
	
		
		BrainGymPage brainGym= new BrainGymPage(driver);
		String accessToken=null;
		try 
		{
			System.out.println("Before login ()");
			accessToken = brainGym.login(un, pw, grade, subject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		Thread.sleep(3000);		
		
	//	brainGym.clickLogout();
		if(!accessToken.equals(null))
		{
			System.out.println("In main(), after login()");
			System.out.println("In main (),accessToken is: "+accessToken);
			
			brainGym.numberOfDaystoRun(driver, wait,accessToken, un, pw, grade, subject, studentId, subjectId, RunBGScript.numberOfDaysToRun);
			//brainGym.testOneShell(driver, wait, un, pw, grade, subject,shellStatus);
		
			System.out.println("1 rounds of shells completed");	
			
		
			
		}
		else
		{
			System.out.println("!!!!!!!!!!!  Error, Login Session Error !!!!!!!!!!!!");
		}
		System.out.println("Script Completed.....");
		
		BrainGymPage.clickLogout();
		Thread.sleep(5000);
		
		//ExcelConstructionExample.writeToExcel();
		String outputfilePath = ExcelConstructionExample.writeExcel();
		System.out.println("Output written to the file : "+outputfilePath);
		
		driver.close();
		driver.quit();
		
		
	}
}
