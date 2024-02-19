package generic;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ExcelConstructionExample;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTestNew {
    protected WebDriver driver;
	public WebDriverWait wait;
	private int numberOfDaysToRun;
    protected String appUrl;
    protected int ITO;
    protected int ETO;
    protected String browserType;
	@Parameters({"browser","appURL","ITO","ETO"})

    @BeforeClass
    public void setUp() {
        // Read configuration properties
        readConfigProperties();

        // Initialize WebDriver based on the browser type
        switch (browserType.toLowerCase()) {
            case "chrome":
            	
            	System.out.println("Inside switch chrome ");
                //System.setProperty("webdriver.chrome.driver", "./exes/chromedriver.exe");
            	WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "path/to/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
	
        Reporter.log("Set ITO:"+ITO,true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ITO)));
		
		Reporter.log("Enter the URL:"+appUrl,true);
		driver.get(appUrl);
		
		
		Reporter.log("Maximize the browser",true);
		//driver.manage().window().maximize();
		
		Reporter.log("Set ETO:"+ETO,true);
		wait=new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(ETO)));
        driver.manage().window().maximize();
        driver.get(appUrl);
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }

    private void readConfigProperties() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("./config.properties")) {
            prop.load(input);

            // Read properties
            numberOfDaysToRun=Integer.parseInt(prop.getProperty("numberOfDaysToRun"));
            browserType = prop.getProperty("browserType");
            appUrl = prop.getProperty("url");
            ITO = Integer.parseInt(prop.getProperty("ITO"));
            ETO = Integer.parseInt(prop.getProperty("ETO")); 
           // otherVariable = prop.getProperty("otherVariable");
            
            System.out.println("numberOfDaysToRun: "+numberOfDaysToRun);
            System.out.println("Brwser type: "+browserType);
            System.out.println("bsae url: "+appUrl);
            System.out.println("ITO: "+ITO);
            System.out.println("ETO: "+ETO);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
			ExcelConstructionExample.writeToExcel();
			Reporter.log("Close the browser",true);
			driver.quit();
					
		}
		

	}
}