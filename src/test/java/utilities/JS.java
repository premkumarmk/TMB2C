package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JS
{
//	public static void setZoomLevel(WebDriver driver, double zoomLevel) 
//	{
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom = arguments[0]", zoomLevel);
//        return;
//    }


    public static void setZoomLevel(WebDriver driver, double zoomLevel) {
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String script = "document.body.style.zoom='" + zoomLevel + "'";
            jsExecutor.executeScript(script);
        } else {
            throw new UnsupportedOperationException("WebDriver is not capable of executing JavaScript.");
        }
    }
	
}
