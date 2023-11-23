package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
	@FindBy(name="email")
	private WebElement unTB;
	
	@FindBy(name="password")
	private WebElement pwTB;
	
	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginBTN;
	
	@FindBy(xpath="//div[text()='Email is required and should be valid']")
	private WebElement errMsg;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void setUserName(String un)
	{
		unTB.sendKeys(un);
	}
	
	public void setPassword(String pw)
	{
		pwTB.sendKeys(pw);
	}
	
	public void clickLoginButton()
	{
		loginBTN.click();
	}
	
	public boolean verifyErrMsgIsDisplayed(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(errMsg));
			System.out.println("Err Msg is displayed");
			return true;
		}
		catch (Exception e) 
		{
			System.out.println("Err Msg is Not displayed");
			return false;
		}
	}
}
