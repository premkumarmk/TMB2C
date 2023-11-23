package script;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.DashboardPage;
import page.InstitutionPage;
import page.LoginPage;

public class AddInstitution extends BaseTest {
	
	
	 @DataProvider(name = "data-set")
	    public static Object[][] DataSet() throws Exception {
	       
	        Excel excel = new Excel();

	        Object[][] obj = excel.to2DArray("./data/input.xlsx", "AddInstitution");
	        return obj;
	    }
	
	@Test(dataProvider="data-set")
//	public void testAddStudent(String un, String pw) throws InterruptedException, AWTException {
		public void testAddStudent(String un, String pw, String nameOfInstitute, String phone, String email, String tenantId, String password, String affiliationNo, String tagLine, String themeCode, String website, String country, String state, String city, String street1, String street2, String pincode, String board) throws InterruptedException, AWTException {
						
//		String un = Excel.getData("./data/input.xlsx", "ValidLogin", 2, 0);
//		String pw = Excel.getData("./data/input.xlsx", "ValidLogin", 2, 1);
//		String NameOfInstitute,Phone,Email,TenantId,Password,AffiliationNo,TagLine,ThemeCode,Website,Country,State,City,Street1,Street2,Pincode,
//		Board;
		
		System.out.println(un);
		System.out.println(pw);
		System.out.println(nameOfInstitute);
		System.out.println(phone);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(un);
//		2. enter valid pw
		loginPage.setPassword(pw);
//		3. click on login button
		loginPage.clickLoginButton();
		
		DashboardPage db = new DashboardPage(driver);
		db.verifyInstituteMenuLinkIsDisplayed(wait);
		db.clickInstituteLink();
		Thread.sleep(2000);
		
		InstitutionPage  ip=new InstitutionPage(driver);
		ip.clickAddInstituteBtn();
		Thread.sleep(2000);

		ip.setNameOfInstitute(nameOfInstitute);
		
		ip.setInstitutePhone(phone);
		ip.setInstituteEmail(email);
		ip.setInstituteTenantId(tenantId);
		ip.setInstitutePassword(password);
		ip.setInstituteAffiliationNo(affiliationNo);
		ip.setInstituteTagLine(tagLine);
		
		ip.setThemeCode(driver,themeCode);
		ip.setInstituteWebsite(website);
		ip.setCountry(driver,country);
		Thread.sleep(1000);
		ip.setState(driver,state);
		Thread.sleep(1000);
		ip.setCity(driver,city);
		ip.setInstituteStreet1(street1);
		ip.setInstituteStreet2(street1);
		ip.setInstitutePincode(pincode);
		ip.setInstituteBoard(driver,board);
	//	driver.findElement(By.xpath("//button[text()='Save']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		System.out.println("Clicked on ENTER key");
		
		Thread.sleep(10000);
		wait.until(ExpectedConditions.urlToBe("https://edison.tautmore.com/institution-management"));
		try
		{
			WebElement element=driver.findElement(By.xpath("//div[@id='tabulator']/descendant::table/tr/td/strong[text()='Contact Person']/ancestor::td/following-sibling::td"));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.textToBePresentInElement(element, email));
			System.out.println("Institute Entry verified for....."+email);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Institute Creation failed for....."+email);
		}
		
		System.out.println("test completed");
	
		
		
	}
}

//WebElement element=driver.findElement(By.id("board0"));
//driver.switchTo().activeElement();
//if(element.equals(driver.switchTo().activeElement()))
//     System.out.println("Element is focused");
//  else
//     System.out.println("Element is not focused");
//ip.setOlympiad("yes");
//ip.SaveInstitute(wait);
//SAVE

//Robot robot = new Robot();
//Thread.sleep(2000);
//robot.keyPress(KeyEvent.VK_TAB);
//
//robot.delay(2000);
//Thread.sleep(2000);
//System.out.println("Clicked on 1st TAB key");
//
//robot.keyPress(KeyEvent.VK_TAB);
//
//robot.delay(2000);
//Thread.sleep(5000);
//System.out.println("Clicked on  2nd TAB key");
//robot.keyPress(KeyEvent.VK_ENTER);
//robot.delay(2000);
