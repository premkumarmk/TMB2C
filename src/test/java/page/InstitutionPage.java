package page;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class InstitutionPage 
{

	@FindBy(xpath="//button[text()='Institution']")
	WebElement AddInstituteBtn;
	
	@FindBy(xpath="//h2[text()='Institution Profile']")
	WebElement InstitutionProfile;
	
	@FindBy(id="name")
	WebElement NameofInstitute;
	
	@FindBy(id="email")
	WebElement InstituteEmail;
	
	@FindBy(id="phone")
	WebElement InstitutePhone;
	
	@FindBy(id="password")
	WebElement InstitutePassword;
	
	@FindBy(id="affiliationNo")
	WebElement InstituteAffiliationNo;
	
	@FindBy(id="tagLine")
	WebElement InstituteTagLine;
	
	@FindBy(id="themeCode")
	WebElement InstituteThemeCode;
	
	@FindBy(id="website")
	WebElement InstituteWebsite;
	
	@FindBy(id="country")
	WebElement InstituteCountry;
	
	@FindBy(id="state")
	WebElement InstituteState;
	
	@FindBy(id="city")
	WebElement InstituteCity;
	
	@FindBy(id="street1")
	WebElement InstituteStreet1;
	
	@FindBy(id="street2")
	WebElement InstituteStreet2;
	
	@FindBy(id="pincode")
	WebElement InstitutePincode;
	
	@FindBy(id="isContentExist")
	WebElement EnableOlympiad;
	
	@FindBy(id="board0")
	WebElement InstituteBoard;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement Save;
	
	@FindBy(id="tenantId")
	WebElement InstituteTenantId;
	
	public void setInstituteTenantId(String tenantId)
	{
		InstituteTenantId.clear();
		InstituteTenantId.sendKeys(tenantId);
	}
	
//	public void setOlympiad(String y)
//	{
//		if(y.equals("yes"))
//		{
//			EnableOlympiad.click();
//		}
//		
//	}
	public void SaveInstitute(WebDriverWait wait) throws InterruptedException
	{	
		wait.until(ExpectedConditions.elementToBeClickable(Save)).click();
		Thread.sleep(5000);
		//Save.click();
	}
	
	public void setInstituteBoard(WebDriver driver,String board) throws InterruptedException
	{
		selectItem(driver, InstituteBoard, board);
	}
	public void setInstitutePincode(String pincode)
	{
		InstitutePincode.sendKeys(pincode);
	}
	
	public void setInstituteStreet2(String street2)
	{
		InstituteStreet2.sendKeys(street2);
	}
	public void setInstituteStreet1(String street1)
	{
		InstituteStreet1.sendKeys(street1);
	}
	
	public void setCity(WebDriver driver,String city) throws InterruptedException
	{	System.out.println("In setCity Method Line-1");
		Thread.sleep(2000);
		selectItem(driver, InstituteCity,city);
		System.out.println("In setCity Method Line last");
	}
	public void setState(WebDriver driver,String state) throws InterruptedException
	{	System.out.println("In setState Method Line-1");
		Thread.sleep(2000);
		selectItem(driver, InstituteState,state);
		System.out.println("In setState Method Line last");
	}
	
	public void setCountry(WebDriver driver,String country) throws InterruptedException
	{
		System.out.println("In setCountry Method Line-1");
		selectItem(driver, InstituteCountry,country);
		System.out.println("In setCountry Method Line last");
	}
	
	public void setThemeCode(WebDriver driver,String theme) throws InterruptedException
	{
	
		selectItem(driver, InstituteThemeCode,theme);
	}
	
	public void selectItem(WebDriver driver, WebElement listbox, String option) throws InterruptedException
	{	
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", listbox);
		System.out.println("In selectItem Method Line-1");
		Select select=new Select(listbox);
	//	listbox.click();
		System.out.println("In selectItem Method After Click");
		Thread.sleep(000);
		
		select.selectByVisibleText(option);
		System.out.println("In selectItem Method After selection");
	}
	
	public void setInstituteWebsite(String website)
	{
		InstituteWebsite.sendKeys(website);
	}
	public void setInstituteTagLine(String tagLine)
	{
		InstituteTagLine.sendKeys(tagLine);
	}
	public void setInstituteAffiliationNo(String affiliationNo)
	{
		InstituteAffiliationNo.sendKeys(affiliationNo);
	}
	public void setInstitutePassword(String pwd)
	{
		InstitutePassword.clear();
		InstitutePassword.sendKeys(pwd);
	}
	public void setInstitutePhone(String phone)
	{
		InstitutePhone.sendKeys(phone);
	}
	
	public void setInstituteEmail(String email)
	{
		InstituteEmail.sendKeys(email);
	}
	public void setNameOfInstitute(String name)
	{
		NameofInstitute.sendKeys(name);
	}
	
	public void clickAddInstituteBtn()
	{
		AddInstituteBtn.click();
	}
	
	
	public InstitutionPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public boolean verifyInstituteFormIsDisplayedOrNot(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(InstitutionProfile));
			System.out.println("Institution Profile Form is displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Institution Profile Form is NOT displayed");
			return false;
		}
	}
	
	
	public boolean verifyAddInstituteButtonIsDisplayedOrNot(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(AddInstituteBtn));
			System.out.println("Add Institute is button displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Add Institute button is NOT displayed");
			return false;
		}
		
	}




	
	
	
}
