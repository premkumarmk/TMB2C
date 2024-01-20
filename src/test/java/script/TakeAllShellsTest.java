
package script;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.ExcelConstructionExample;

public class TakeAllShellsTest extends BaseTest
{	
	
	
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
		
		BrainGymPage brainGym= new BrainGymPage(driver);
		brainGym.login(un, pw, grade, subject);
		Thread.sleep(2000);		
		
		brainGym.numberOfDaystoRun(driver, wait, un, pw, grade, subject, BrainGymPage.numberOfDaysToRun);
		//brainGym.testOneShell(driver, wait, un, pw, grade, subject,shellStatus);
		
		System.out.println("1 rounds of shells completed");	
		ExcelConstructionExample.writeToExcel();
	}
}
