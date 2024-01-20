package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.BaseTest;

public class LoginPage extends BaseTest
{
	@FindBy(xpath="//button[@class='login-signin' and text()='Login']")
	private WebElement loginBtnDashboard;
	
	@FindBy(xpath="//p[text()='Parent/Student']")
	private WebElement optionLoginAs; 
	
	@FindBy(xpath="//button[text()='Next']")
	private WebElement nextBtn;
	
	@FindBy(xpath="//input[@placeholder='Enter your email or username']")
	private WebElement UsernameTB;
	
	@FindBy(xpath="//input[@placeholder='Enter your password']")
	private WebElement pwTB;
	
	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginBTN;
	
	@FindBy(xpath="//h3[text()='UPCOMING CLASSES']")
	private WebElement dashboard;
	
	@FindBy(xpath="//div[@class='login-main']/descendant::p[text()='No such user found in the system']")
	private WebElement NoSuchUserFoundMsg;
	
	public LoginPage()
	{
		PageFactory.initElements(driver,this);
	}
	

	public Boolean verifyNoSuchUserFoundMsg(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(NoSuchUserFoundMsg));
			System.out.println("No Such User Found Msg is Displayed");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("No Such User Found Msg is NOT Displayed");
			return false;
		}
		
	}
	
	public void clickLoginButton()
	{
		loginBTN.click();
	}
		
	public void setPassword(String pw) throws InterruptedException
	{
		pwTB.sendKeys(pw);
		//Thread.sleep(13000);
	}
	
	public void setUserName(String un)
	{
		UsernameTB.sendKeys(un);
	}
	
	public void clickOnOptionLoginAs()
	{
		optionLoginAs.click();
	}
	
	public void clickOnNext()
	{
		nextBtn.click();
	}
	
	public void clickOnloginBtnOnDashboard()
	{
		loginBtnDashboard.click();
	}
	
	public boolean verifyDashBoardPageIsDisplayed(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(dashboard));
			System.out.println("DashboardPage is displayed");
			return true;
		}
		catch (Exception e) 
		{
			System.out.println("DashboardPage is Not displayed");
			return false;
		}
	}

}
