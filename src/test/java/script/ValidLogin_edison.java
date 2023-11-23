package script;

import org.testng.Assert;
import org.testng.annotations.Test;
import generic.BaseTest;
import generic.Excel;
import page.DashboardPage;
import page.LoginPage;

public class ValidLogin_edison extends BaseTest
{
	@Test()
	public void testValidLogin()
	{
		String un = Excel.getData("./data/input.xlsx","ValidLogin_edison", 1, 0);
		String pw = Excel.getData("./data/input.xlsx","ValidLogin_edison", 1, 1);

		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);

		loginPage.setPassword(pw);

		loginPage.clickLoginButton();

		DashboardPage dashboardPage=new DashboardPage(driver);
		boolean result = dashboardPage.verifyDashBoardPageIsDisplayed(wait);
		Assert.assertTrue(result);
	}
}
