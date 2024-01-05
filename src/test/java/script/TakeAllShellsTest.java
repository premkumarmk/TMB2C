package script;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.UpdateDB;
import utilities.mongoDB;
import utilities.mongoDBSeleniumIntegration;

public class TakeAllShellsTest extends BaseTest
{	
	List<String> studentStrings = new ArrayList<>();
	
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

//		String un="three.9393688889";
//		String pw="Taut@2023";
//		String un="uiop.7981345807";
//		String pw="stuPwd456870";
		int count = 0; //start form zero, else effects DB reset
//		String un="autostudentone.9606178621";
//		String pw="stuPwd906040";
		System.out.println("Username:"+un);
		System.out.println("Password:"+pw);
		System.out.println("grade:"+grade);
		System.out.println("subject:"+subject);
		LoginPage login=new LoginPage(driver);
	//	mongoDB.getStudentIdByEmail("abdul@codewave.com");
		login.clickOnloginBtnOnDashboard();
		Thread.sleep(1000);
		login.clickOnOptionLoginAs();
		Thread.sleep(1000);
		login.clickOnNext();
		Thread.sleep(1000);
		login.setUserName(un);
		Thread.sleep(1000);
		login.setPassword(pw);
		Thread.sleep(1000);
		login.clickLoginButton();
		Map<String, Integer> map = new HashMap<String, Integer>();
		BrainGymPage brainGym= new BrainGymPage(driver);
		Thread.sleep(2000);
		String shellAvailable="no";
	do 
	{		driver.navigate().refresh();
			Thread.sleep(1000);
			brainGym.clickleftLinkBrainGym(wait);
	//////		brainGym.clickSubject();
			brainGym.selectSubject(subject);
			brainGym.clickWorkoutBtn();
			String hasPerDayShellsAreFinsihed="no";
		do {
			brainGym.clickStartNowShell();
			brainGym.clickStartNowOnPopupBtn();
			String questionText;
		
			String shellStatus="no";
			do
			{
				questionText=brainGym.getQuestionText();
				if (map.containsKey(questionText)) 
				{
					map.put(questionText,map.get(questionText)+1);
					Reporter.log(questionText, true);
					//Assert.fail("Repeated Question:"+questionText );
				} 
				else 
				{
					map.put(questionText,1);
				}
				
				brainGym.clickAnswerOption();
				brainGym.clickSubmitAnswerBtn();
				brainGym.clickNextQuestionBtn();
				shellStatus=brainGym.verifyShellCompleted();
			//	String oneSetSellCompleted=brainGym.verifyOneShellSetCompletion();
				System.out.println("Shell Status is:"+shellStatus);
				
				
			}while(shellStatus.equals("no"));
			
			System.out.println("-------------");
			System.out.println("End !!! "+map.size());
			System.out.println(map);
			System.out.println("-------------");
			brainGym.displayHashTable();
			System.out.println("\n\nMAP content");
			for(Map.Entry<String, Integer> entry : map.entrySet()) 
			{
				if(entry.getValue()>=1) 
				{
					System.out.println(entry.getKey() +"==>"+ entry.getValue());
				}
				
			}
						
			brainGym.clickNextBtn(); // Click on after one loop of 5 shells
			brainGym.nextBtnForNextShell(); //click on Belt Next button
			shellAvailable=brainGym.verifyNextShellStartNowIsAvailable();
			System.out.println("verify Next Shell Start Now Is Available:  "+shellAvailable);
			if(shellAvailable.equals("no"))
			{
				hasPerDayShellsAreFinsihed=brainGym.verifyperDayShellsAreCompleted();
				System.out.println("has Per Day Shells Are Finsihed:  "+hasPerDayShellsAreFinsihed);
				
				if(hasPerDayShellsAreFinsihed.equals("yes"))
				{
					System.out.println("before clicking clickFinishperDayShells  in IF");
					brainGym.clickFinishPerDayShells();
				}
			}
			
			
		}while(shellAvailable.equals("yes"));
		
		UpdateDB.changeDate(count);
		Thread.sleep(3000);
		
		studentStrings.add(StringUtils.join(un,pw,grade,subject,"pass","comments"));
		count++;
	}while(count<4)	;
		
		
	System.out.println("Student Strings"+studentStrings);
	String[] studentString =  studentStrings.toArray(new String[4]);
	try {
		createExcelSheet(studentString);
	} catch(IOException e) {
		
	}
		 
	}
	 
		public static  void createExcelSheet(String[] lines) throws IOException
	    {
	        FileOutputStream out = new FileOutputStream("test.xls");
	        //FileOutputStream out = new FileOutputStream("/home/yos/lakshmi.xls");

	        HSSFWorkbook wb = new HSSFWorkbook();

	        HSSFSheet s = wb.createSheet();

	        Row r = null;

	        Cell c = null;

	        for(int i=0;i<lines.length;i++)
	        {
	            r = s.createRow(i); 
	           String[] rowStrings = StringUtils.split(lines[i], ',');
				 
	            //System.out.println(""+lines[i]);
	            //System.out.println("Column string count "+rowStrings.length);
	            for(int j=0;j<rowStrings.length;j++)
	            {
	                c = r.createCell(j);
	               // System.out.println("Cell text should be "+rowStrings[j]);
	                c.setCellValue( rowStrings[j] );
	            }
	        }


	        wb.write(out);
	        out.close();
	    }

}
