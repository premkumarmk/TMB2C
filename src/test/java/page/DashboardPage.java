package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage 
{
	@FindBy(xpath="//h2[text()='Dashboard']")
	private WebElement dashboard;
	
	@FindBy(xpath="//a[@href='/students']")
	private WebElement StudentsMenu;
	
	@FindBy(xpath="//a[@href='/institution-management']")
	private WebElement InstituteManagement;
	
	public DashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void clickStudentMenu()
	{
		StudentsMenu.click();
	}
	
	public void clickInstituteLink()
	{
		InstituteManagement.click();
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
	
	public boolean verifyStudentMenuLinkIsDisplayed(WebDriverWait wait)
	{
		try
		{
			Thread.sleep(10);
			wait.until(ExpectedConditions.visibilityOf(StudentsMenu));
			System.out.println("Students Menu Link is displayed");
			return true;
		}
		catch (Exception e) 
		{
			System.out.println("Students Menu Link is Not displayed");
			return false;
		}
	}
	
	public boolean verifyInstituteMenuLinkIsDisplayed(WebDriverWait wait)
	{
		try
		{
			Thread.sleep(10);
			wait.until(ExpectedConditions.visibilityOf(InstituteManagement));
			System.out.println("Institute Management Link is displayed");
			return true;
		}
		catch (Exception e) 
		{
			System.out.println("Institute Management Link is Not displayed");
			return false;
		}
	}
}
