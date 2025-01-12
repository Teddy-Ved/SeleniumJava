package WebAutomation.web;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class baseClassGrid {
	
	WebDriver driver;
	
	@Parameters({"bname"})
	@BeforeTest()
	public void setup(String browserName)
	{
		String username = System.getenv("BROWSERSTACK_USERNAME");
		String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		
		if(browserName.equals("Chrome"))
		{
			ChromeOptions opt = new ChromeOptions();
			opt.setPlatformName("Windows 10");
			opt.setBrowserVersion("120.0");
			try {
				driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),opt);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browserName.equals("Firefox"))
		{
			FirefoxOptions ffd = new FirefoxOptions();
			ffd.setPlatformName("Windows 10");
			ffd.setBrowserVersion("132.0");
			try {
				driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),ffd);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@AfterTest()
	public void teadDown()
	{
		driver.quit();
	}

}
