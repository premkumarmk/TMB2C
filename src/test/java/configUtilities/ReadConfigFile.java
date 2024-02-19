package configUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile
{
	Properties properties;
	String congifPath = "./configuration.properties";
	
	public ReadConfigFile()
	{
		properties=new Properties();
		try 
		{
			FileInputStream input = new FileInputStream(congifPath);
			properties.load(input);
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
	}

	public String getURL()
	{
		String url=properties.getProperty("appURL");
		if(url!=null)
			return url;
		else
			throw new RuntimeException("Url is not specified in config file. ");
		
	}
	
	public String getBrowserType()
	{
		String browser=properties.getProperty("browserType");
		if(browser!=null)
			return browser;
		else
			throw new RuntimeException("Browser is not specified in config file. ");
		
	}
}
