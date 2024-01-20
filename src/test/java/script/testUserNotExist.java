package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.LoginPage;

@Test //Invalid user credentials is the input from Excel sheets
public class testUserNotExist extends BaseTest 
{
	public void verifyUserNotexistMessage() throws InterruptedException
	{
		String un = Excel.getData("./data/userIds.xlsx","user_ids", 1, 0);
		String pw = Excel.getData("./data/userIds.xlsx","user_ids", 1, 1);
		
		LoginPage login=new LoginPage();
		
		login.clickOnloginBtnOnDashboard();
		login.clickOnOptionLoginAs();
		login.clickOnNext();
		login.setUserName(un);
		login.setPassword(pw);
		login.clickLoginButton();
		//Thread.sleep(10);
		Boolean result=login.verifyNoSuchUserFoundMsg(wait);
		Assert.assertTrue(result);

	}
}
