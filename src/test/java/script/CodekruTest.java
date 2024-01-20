package script;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.events.local.GuavaEventBus;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import generic.BaseTest;
import generic.Excel;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import page.BrainGymPage;
import page.LoginPage;
import utilities.CompareQuestions;
import utilities.ExcelConstructionExample;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers.*;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
public class CodekruTest {
 
		public void whenRequestedPost_thenCreated() {
		   Response response = given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get("https://u75lkusioi.execute-api.us-east-1.amazonaws.com/prod/api/admin/open-practice-countries-list")
	                .then()
	                .extract().response();
			System.out.println("Output Response: "+response.getBody().asPrettyString());
       
	}
		
	
		public void getLoginSeesionToken(String username, String password) throws Exception
		{
			RestAssured.baseURI ="https://dev.tautmore.com/api"; 
		    RequestSpecification request = RestAssured.given(); 
		    JSONObject requestParams = new JSONObject();
		     
		    requestParams.put("userName", username);
		    requestParams.put("password", password);
		    requestParams.put("userType", "parent");
		    request.header("Content-Type", "application/json");
		    request.body(requestParams);
		    
		    Response response = request.put("/students/login"); 
		    ResponseBody body = response.getBody();
		    System.out.println("Inside loginAPI method");
		  
		    
		    System.out.println(response.getStatusLine());
		    System.out.println(body.asString());
		    
		    
		    
		}

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
//		 System.out.println("Before JS");
//		//JS.setZoomLevel(driver,0.75);
//		System.out.println("After JS");
	//	seleniumTest(driver);
		BrainGymPage brainGym= new BrainGymPage(driver);
		brainGym.login(un, pw, grade, subject);
		getLoginSeesionToken(un, pw);
		Thread.sleep(2000);		
		
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		brainGym.clickleftLinkBrainGym(wait);
		brainGym.selectSubject(subject);
		brainGym.clickWorkoutBtn();
		SoftAssert softAssert=new SoftAssert();
		brainGym.clickStartNowShell();
		brainGym.clickStartNowOnPopupBtn();
		String questionText;
//--		getQuestionText();
			
		String shellStatus="no";
		do
		{
			
			questionText=brainGym.getQuestionText(subject);
			//questionText=getQuestionText();
			System.out.println("New Question: "+questionText);
//			System.out.println("---------------");
//			System.out.println(utilities.mongoDBSeleniumIntegration.getAnswer());
//			System.out.println(">>>>>>>>>>>>>.");
			
			System.out.println("Before calling selectQuestionType()");
			brainGym.selectQuestionType(driver);
			System.out.println("After calling selectQuestionType()");
			if(CompareQuestions.SearchAndInsertToHashMap(questionText))
			{
				softAssert.assertTrue(true, questionText);
				System.out.println("IF");
				Reporter.log("Pass: No Duplicate Found");
				
				//Write code to write into Excel
			    String joinedString = StringUtils.join(un + "," + grade + "," + subject + ",Pass," + questionText);
			    System.out.println("joinedString is :"+joinedString);
			    brainGym.ResultListToExcel.addLast(joinedString);
							
			}
			else
			{
				softAssert.assertTrue(false, questionText);
				System.out.println("ELSE");
				Reporter.log("Fail: Duplicate Found");
				//Write code to write into Excel
				String joinedString = StringUtils.join(un + "," + grade + "," + subject + ",Fail," + questionText);
				System.out.println("joinedString is :"+joinedString);
				brainGym.ResultListToExcel.addLast(joinedString);
				
			}		
			
			
			//brainGym.clickAnswerOption();
			//brainGym.clickSubmitAnswerBtn();
			//brainGym.clickNextQuestionBtn();
			 shellStatus=brainGym.verifyShellCompleted();
		
			System.out.println("Shell Status is:"+shellStatus);
			
		}while(shellStatus.equals("no"));
		System.out.println("After End of While Loop");
		
	}
}
}
