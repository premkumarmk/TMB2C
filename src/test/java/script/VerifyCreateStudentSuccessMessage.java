
package script;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v117.input.Input;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.DashboardPage;
import page.LoginPage;
import page.StudentsPage;

public class VerifyCreateStudentSuccessMessage extends BaseTest {
	
	 @DataProvider(name = "data-set")
	    public static Object[][] DataSet() throws Exception {
	       
	        Excel excel = new Excel();

	        Object[][] obj = excel.to2DArray("./data/input.xlsx", "AddStudent");
	        return obj;
	    }
	
	
	@Test(priority = 3, dataProvider="data-set")
	public void testAddStudent(String un, String pw, String rollNumber, String email, String firstName, String gender, String mobileNo, String country, String state,
			String city, String pinCode, String preferredAddress, String presentAddress, String dateOfBirth, String admissionDate, String admissionYear,
			String gradeOrClass, String section, String houseTeam, String motherTounge ) throws InterruptedException {
		
		
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(un);

		loginPage.setPassword(pw);

		loginPage.clickLoginButton();
		
		DashboardPage db = new DashboardPage(driver);
		db.verifyStudentMenuLinkIsDisplayed(wait);
		db.clickStudentMenu();
		
		StudentsPage sp = new StudentsPage(driver);
		sp.verifyAddStudentPageIsDisplayedOrNot(wait);
		sp.clickStudentBtn();
		
		sp.verifyStudentFormIsDisplayedOrNot(wait);
			
		System.out.println("step-2");
		
		sp.setFirstName(firstName);
		sp.setGender(driver, gender);
		sp.setMobile(mobileNo);
				
		sp.setEmail(email);
		sp.setRollNo(rollNumber);
	
		sp.setCountry(driver,country);
		System.out.println("Entered Country");
	
		sp.setState(driver,state);
		System.out.println("Entered State");
		
		sp.setCity(driver,city);
		System.out.println("Entered City");
	

		sp.setDobDate(dateOfBirth);
		sp.setDoaDate(admissionDate);
		sp.setAdmissionYear(driver, admissionYear);		
//		 JavascriptExecutor js = (JavascriptExecutor)driver;
//		  js.executeScript("arguments[0].value='October/2023-October/2024';", element);
//		  System.out.println("step-2A");
		sp.setPresentAddress(presentAddress);
		sp.setPreferredAddress(preferredAddress);
		sp.setPinCode(pinCode);
		
		sp.setHouseOrTeam(houseTeam);		
		sp.setMotherTongue(motherTounge);
		
		sp.setGradeOrClass(driver, gradeOrClass);
		sp.setSection(driver, section);
		
		sp.clickSave();
		Thread.sleep(2000);
		System.out.println("Clicked on Save");		
		sp.verifySuccessMsg(driver,wait);
		System.out.println("Last END");
	}

}
