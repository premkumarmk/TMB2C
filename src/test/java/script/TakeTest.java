package script;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.*;
import utilities.mongoDB;
import utilities.mongoDBSeleniumIntegration;

public class TakeTest extends BaseTest{

	@Test
	public void clickOnShell() throws Exception 
	{
		//mongoDBSeleniumIntegration.updateBrainGymMappings1(mongoDBSeleniumIntegration.getStudentIdByEmail(), 4);
//		String un = Excel.getData("./data/userIds.xlsx","user_ids", 1, 0);
//		String pw = Excel.getData("./data/userIds.xlsx","user_ids", 1, 1);
//		String un="three.9393688889";
//		String pw="Taut@2023";
//		String un="uiop.7981345807";
//		String pw="stuPwd456870";
		
		String un="autostudentone.9606178621";
		String pw="stuPwd906040";
		System.out.println("Username:"+un);
		System.out.println("Password:"+pw);
		LoginPage login=new LoginPage();
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
		brainGym.clickleftLinkBrainGym(wait);
//////		brainGym.clickSubject();
		brainGym.selectSubject("English");
		brainGym.clickWorkoutBtn();
		brainGym.clickStartNowShell();
		brainGym.clickStartNowOnPopupBtn();
		String questionText;
	
		String shellStatus="no";
		do
		{
//			questionText=brainGym.getQuestionText();
//			if (map.containsKey(questionText)) {
//				map.put(questionText,map.get(questionText)+1);
//			} else {
//				map.put(questionText,1);
//			}
//			
			
			brainGym.clickAnswerOption();
		//	brainGym.clickSubmitAnswerBtn();
			//brainGym.clickNextQuestionBtn();
			
			
		//	shellStatus=brainGym.verifyShellCompletedSingle();
			
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
		
		
		
	//====================================================//	
//		System.out.println("Print the list");
//		for(String l:list)
//		{
//			
//			System.out.println(l.toString());
//		}
		//button[text()='Next']
		//button[@class="beltpopup-button" and text()='Next']
	}

}
