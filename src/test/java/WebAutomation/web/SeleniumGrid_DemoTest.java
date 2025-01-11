package WebAutomation.web;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGrid_DemoTest extends baseClassGrid{
	
	
	@Test
	public void launcbrowser() throws MalformedURLException
	{
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());	
	}

}
