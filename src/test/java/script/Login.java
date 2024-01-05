package script;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import page.LoginPage;

public class Login extends BaseTest
{
	@Test
	public void login() throws InterruptedException
	{
		String un="three.9393688889";
		String pwd="Taut@2023";
		LoginPage login=new LoginPage(driver);
		
		login.clickOnloginBtnOnDashboard();
		login.clickOnOptionLoginAs();
		login.clickOnNext();
		login.setUserName(un);
		login.setPassword(pwd);
		login.clickLoginButton();
		Thread.sleep(10);
		Boolean result=login.verifyDashBoardPageIsDisplayed(wait);
		Assert.assertTrue(result);
		
	}
}
