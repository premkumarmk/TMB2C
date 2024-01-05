package page;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class StudentsPage 
{
		
	@FindBy(xpath="//h2[text()='Students']")
	WebElement AddStudentPage;
	
	@FindBy(xpath="//button[text()='Student']")
	private WebElement AddStudentBtn;
	
	@FindBy(xpath="//h2[text()='Add Student']")
	WebElement AddStudentForm;
	
	@FindBy(id="email")
	private WebElement email;

	@FindBy(id="rollNo")
	private WebElement rollNo;
	
	@FindBy(id="firstName")
	private WebElement firstName;
	
	@FindBy(id="male")
	private WebElement genderMale;
	
	@FindBy(id="female")
	private WebElement genderFemale;
	
	@FindBy(id="others")
	private WebElement genderOthers;
	
	@FindBy(id="mobile")
	private WebElement mobile;
	
	@FindBy(xpath="//input[@id='react-select-2-input']")
	private WebElement countryInputBox;

	@FindBy(xpath="//input[@id='react-select-3-input']")
	private WebElement stateInputBox;
	
	@FindBy(xpath="//input[@id='react-select-4-input']")
	private WebElement cityInputBox;
		
	@FindBy(id="zipcode")
	private WebElement pinCode;
	
	@FindBy(id="preferredAddr")
	private WebElement preferredAddr;
	
	@FindBy(id="presentAddr")
	private WebElement presentAddr;
	
	@FindBy(id="dob")
	private WebElement dob;
	
	@FindBy(id="doa")
	private WebElement doa;
	
	@FindBy(xpath="//input[@id='react-select-5-input']")
	private WebElement admissionYear;
	
	@FindBy(xpath="//input[@id='react-select-6-input']")
	private WebElement gradeOrClassInputBox;
	
	@FindBy(xpath="//input[@id='react-select-7-input']")
	private WebElement sectionInputBox;
	
	@FindBy(id="houseOrTeam")
	private WebElement houseOrTeam;
	
	@FindBy(id="motherTongue")
	private WebElement motherTongue;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement save;
	
	@FindBy(xpath="//div[@id='success-notification-content']/div/div[1]")
	private WebElement successMessage;
	
	@FindBy(xpath="//div[@id='success-notification-content']/div/div[1]")
	private WebElement failureMessage;
	
	public StudentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void verifyFailureMsg(WebDriver driver, WebDriverWait wait)
	{
		String actualMsg = null;
		String expectedMsg="E-mail already exist";
		try
		{
			////div[@id='failed-notification-content']/descendant::div[contains(text(),'E-mail already exist')]
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='failed-notification-content']/div/div[2]")));
			actualMsg=driver.findElement(By.xpath("//div[@id='failed-notification-content']/div/div[2]")).getText();
			System.out.println("Message is:"+actualMsg);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("In FINALLY");
		
		}
		System.out.println("Message is: "+actualMsg);
		System.out.println("Element found");		
		assert(expectedMsg.equals(actualMsg));
	}
	
	public void verifySuccessMsg(WebDriver driver, WebDriverWait wait)
	{
		String actualMsg = null;
		String expectedMsg="Success!";
		try
		{
		
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='success-notification-content']/div/div[1]")));
			actualMsg=driver.findElement(By.xpath("//div[@id='success-notification-content']/div/div[1]")).getText();
			System.out.println("Message is:"+actualMsg);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("In FINALLY");
		
		}
		System.out.println("Message is: "+actualMsg);
		System.out.println("Element found");		
		assert(expectedMsg.equals(actualMsg));
	}
	
	public void clickSave()
	{
		
		save.click();
	}

	
	public void setHouseOrTeam(String houseTeam)
	{
		houseOrTeam.sendKeys(houseTeam);
	}
	
	public void setMotherTongue(String language)
	{
		motherTongue.sendKeys(language);
	}
	
	public void setDoaDate(String date)
	{
		doa.clear();
		doa.sendKeys(date);
	}

	public void setDobDate(String date) throws InterruptedException
	{	
		Thread.sleep(1000);
//		dob.click();
		dob.clear();
		dob.sendKeys(date);
	}
	

	
	public void setAdmissionYear(WebDriver driver, String admn) throws InterruptedException
	{
		System.out.println("In setAdmissionYear Method");
		selectFromList(driver, admissionYear, admn, 5);
		System.out.println("Exit setAdmissionYear Method");
	}

	public void setSection(WebDriver driver, String sect) throws InterruptedException 
	{
		System.out.println("In setSection method");
		////input[@id='react-select-7-input']/ancestor::div[@class=' css-t3ipsp-control']/following-sibling::div[@id='react-select-7-listbox']/descendant::div[text()='A']
		selectFromList(driver,sectionInputBox,sect,7);
		System.out.println("setSection Last statement");
	}

	public void setGradeOrClass(WebDriver driver, String grade) throws InterruptedException 
	{
		System.out.println("In setGradeOrClass method");
		////input[@id='react-select-6-input']/ancestor::div[@class=' css-t3ipsp-control']/following-sibling::div[@id='react-select-6-listbox']/descendant::div[text()='I']
		selectFromList(driver,gradeOrClassInputBox,grade,6);
	}

	public void setPreferredAddress(String prefAddress) 
	{
		preferredAddr.sendKeys(prefAddress);
			
	}
	
	public void setPresentAddress(String presAddress)
	{
		presentAddr.sendKeys(presAddress);
	}
	

	public void selectFromList(WebDriver driver ,WebElement inputBox,String value, int i) throws InterruptedException
	{
		System.out.println("In select "+value+" in List method");
		inputBox.sendKeys(value);
		
		Thread.sleep(3000);
		WebElement element= driver.findElement(By.xpath("//input[@id='react-select-"+i+"-input']/ancestor::div[@class=' css-t3ipsp-control']/following-sibling::div[@id='react-select-"+i+"-listbox']/descendant::div[text()='"+value+"']"));
		//driver.findElement(By.xpath("//div[text()='"+value+"' and @id]")).click();
		System.out.println("next Clicking on xpath"+element);
		element.click();
		System.out.println("Clicked on xpath"+element);
		Thread.sleep(1000);
	}
	


	public void setCountry(WebDriver driver,String cntry) throws InterruptedException
	{
		System.out.println("In setCountry method");
		System.out.println("Step -1");
		//verifyReactListIsDisplayedOrNot(wait, countryList);
		
		//selectFromList(driver,"Country",cntry);
		System.out.println("Step -2");
		selectFromList(driver,countryInputBox,cntry,2);
		System.out.println("step-3");
		
	}
	
	public void setState(WebDriver driver, String stat) throws InterruptedException
	{
		System.out.println("In setState method");
		//verifyReactListIsDisplayedOrNot(wait, countryList);
		//selectFromList(driver,"State",stat);//"+stat+"
		selectFromList(driver,stateInputBox,stat,3);
	}
	
	public void setCity(WebDriver driver, String cty) throws InterruptedException
	{
		System.out.println("In setCity method");
		//WebElement element= driver.findElement(By.xpath("//input[@id='react-select-2-input']/ancestor::div[@class=' css-t3ipsp-control']/following-sibling::div[@id='react-select-2-listbox']/descendant::div[text()='"+cty+"']"));
		selectFromList(driver,cityInputBox,cty,4);
	}
	
	public void setPinCode(String pincode)
	{
		pinCode.sendKeys(pincode);
	}
	
	public void setMobile(String mn)
	{
		mobile.sendKeys(mn);
	}
	
	public void setGender(WebDriver driver, String gendr)
	{
		if(gendr.equalsIgnoreCase("male"))
		{
			genderMale.click();
		}
		else if(gendr.equalsIgnoreCase("female"))
		{
			genderFemale.click();	
		}
		else if(gendr.equalsIgnoreCase("others"))
		{
			genderOthers.click();
		}
		else
		{
			System.out.println("Invalid Gender");
		}
		
	}
	
	public void setFirstName(String fn)
	{
		firstName.sendKeys(fn);
	}
	
	public void setRollNo(String rollNumber)
	{
		rollNo.sendKeys(rollNumber);
	}
	
	public void setEmail(String emailID)
	{
		email.sendKeys(emailID);
	}
	
	public void clickStudentBtn() 
	{
		AddStudentBtn.click();		
	}
	
	public boolean verifyStudentFormIsDisplayedOrNot(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(AddStudentForm));
			System.out.println("Add Student Form is displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Add Student Form is NOT displayed");
			return false;
		}
	}
	
	public boolean verifyAddStudentPageIsDisplayedOrNot(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(AddStudentPage));
			System.out.println("Add Student page is displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Add Student page is NOT displayed");
			return false;
		}
		
	}
	
	public boolean verifyAddStudentButtonIsDisplayedOrNot(WebDriverWait wait)
	{
		try
		{
			wait.until(ExpectedConditions.visibilityOf(AddStudentBtn));
			System.out.println("Add Student is button displayed");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Add Student button is NOT displayed");
			return false;
		}
		
	}







	
	
	
}
