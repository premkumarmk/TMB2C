
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.CompareQuestions;
import utilities.ExcelConstructionExample;
import utilities.UpdateDB;
import utilities.mongoDB;
import utilities.mongoDBSeleniumIntegration;

public class ModulariseCode extends BaseTest
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
		int numberOfDays=1; //how may days of shells to complete
		BrainGymPage brainGym= new BrainGymPage(driver);
		brainGym.login(driver, un, pw, grade, subject);
		Thread.sleep(2000);		
		
		brainGym.numberOfDaystoRun(driver, wait, un, pw, grade, subject, numberOfDays);
		//brainGym.testOneShell(driver, wait, un, pw, grade, subject,shellStatus);
		
		System.out.println("1 rounds of shells completed");	
		ExcelConstructionExample.writeToExcel();
	}
}
