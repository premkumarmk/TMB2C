package generic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.*;

public class BaseTest 
{
	public WebDriver driver;
	public WebDriverWait wait;
	public Assert asert;
	
	@Parameters({"browser","appURL","ITO","ETO"})
	@BeforeMethod
	public void preCondition
			(
							
				@Optional("chrome") String browser,		
				
				//@Optional("https://edison.tautmore.com/") String appURL,
				//@Optional("https://raj.tautmore.com/login") String appURL,
				//@Optional("https://tautmore.com") String appURL,
				@Optional("https://d2c0p5f3p3k3ka.cloudfront.net/") String appURL,
				
				//@Optional("https://myschool.tautmore.com/login") String appURL,
				@Optional("100") String ITO,
				@Optional("60") String ETO	
			) throws MalformedURLException	
	{
		
		//System.setProperty("webdriver.chrome.driver","./exes/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
		Reporter.log("Browser is:"+browser,true);

		driver=new ChromeDriver(options);
		
		//driver=new FirefoxDriver();
			
				
		Reporter.log("Set ITO:"+ITO,true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ITO)));
		
		Reporter.log("Enter the URL:"+appURL,true);
		driver.get(appURL);
		
		
		Reporter.log("Maximize the browser",true);
		driver.manage().window().maximize();
		
		Reporter.log("Set ETO:"+ETO,true);
		wait=new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(ETO)));
		
		
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
					
		}
		
		
		Reporter.log("Close the browser",true);
		driver.quit();
	}
}
