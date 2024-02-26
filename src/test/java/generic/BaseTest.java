package generic;
import java.io.File;
import utilities.ExcelConstructionExample;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.gargoylesoftware.css.dom.Property;

import io.github.bonigarcia.wdm.WebDriverManager;
import script.RunBGScript;



public class BaseTest 
{
	public static WebDriver driver;
	public WebDriverWait wait;
	public Assert asert;
	//public static String appURL="https://d2c0p5f3p3k3ka.cloudfront.net/";
	//public static String appURL="https://www.tautmore.com/";
	//public static String appURL="https://u75lkusioi.execute-api.us-east-1.amazonaws.com/";
	
	//@Parameters({"browser","appURL","ITO","ETO"})
	
//	@BeforeMethod
//	public void preCondition(@Optional("chrome") String browser,	
//				//@Optional("https://d2c0p5f3p3k3ka.cloudfront.net/") String appURL,
//				@Optional("https://www.tautmore.com/") String appURL,
//				//@Optional("https://u75lkusioi.execute-api.us-east-1.amazonaws.com/") String appURL,
//				@Optional("10") String ITO,
//				@Optional("5") String ETO) throws MalformedURLException	
//	{
	@BeforeMethod
	public void preCondition() throws MalformedURLException	
	{

		
	//	System.setProperty("webdriver.chrome.driver","./exes/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		options.addArguments("--disable-notifications");
		options.addArguments("--force-device-scale-factor=" + 0.75);
		options.setBrowserVersion("116.0.5845.111");
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
		//double zoomFactor = 1.5;

        // Create ChromeOptions
    	System.out.println("APP URL is:" +RunBGScript.appURL);
		Reporter.log("Browser is:"+RunBGScript.browserType,true);
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(options);
		
	//driver=new HtmlUnitDriver(true);
		
		//driver=new FirefoxDriver();
			
		
		Reporter.log("Set ITO:"+RunBGScript.ITO,true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(RunBGScript.ITO)));
		
		Reporter.log("Enter the URL:"+RunBGScript.appURL,true);
		driver.get(RunBGScript.appURL);
		
		
		Reporter.log("Maximize the browser",true);
		//driver.manage().window().maximize();
		
		Reporter.log("Set ETO:"+RunBGScript.ETO,true);
		wait=new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(RunBGScript.ETO)));
		
		
	}
	
	@AfterMethod
	public void postCondition(ITestResult result) throws Exception
	{
		String name = result.getName();
		int status = result.getStatus();
	
		if(status==2)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
	        String timestamp = dateFormat.format(new Date());
	        
			TakesScreenshot t=(TakesScreenshot) driver;
			File scrImage = t.getScreenshotAs(OutputType.FILE);
			File dstImage= new File("./images/"+name+timestamp+".png");
			FileUtils.copyFile(scrImage, dstImage);
			//ExcelConstructionExample.writeToExcel();
			ExcelConstructionExample.writeExcel();
			Reporter.log("Close the browser",true);
			driver.quit();
					
		}
		

	}
	
//	@AfterTest
//	public void afterEachTest() throws FileNotFoundException, IOException
//	{
//		ExcelConstructionExample.writeToExcel();
//		BrainGymPage.clickLogout();
//		Reporter.log("Close the browser",true);
//		driver.quit();
//	}
}
