package WebAutomation.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pageRepository.LoginPage;

public class baseClass {

	private static baseClass instance;
	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
	public static String brow ,Url;
	public static WebDriver driver;
	static Properties prop;
	
	private baseClass() throws IOException {
		
	}
	
	private void setDriver(String browser)
	{
		switch (browser)
		{
		case("chrome"):
		{
			tldriver.set(new ChromeDriver());
			break;
		}
		
		case("edge"):
		{
			tldriver.set(new EdgeDriver());
			break;
		}
		default:
			throw new IllegalArgumentException("browser not supported: "+browser);
		}
		
	}
	
	public static baseClass getInstance(String browser)
	{
		if(instance == null)
		{
			synchronized(baseClass.class)
			{
				if(instance == null)
				{
					try {
						instance = new baseClass();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(tldriver.get() ==null)
		{
			instance.setDriver(browser);
		}
		
		return instance;
	}
	
	public WebDriver getDriver()
	{
		return tldriver.get();
	}
		
	public static void quitBrowser()
	{
		tldriver.get().quit();
		tldriver.remove();
	}

}

