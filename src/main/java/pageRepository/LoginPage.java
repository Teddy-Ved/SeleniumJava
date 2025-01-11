package pageRepository;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import WebAutomation.web.baseClass;
import utility.Utility;

public class LoginPage {
	WebDriver driver;
	
	private By Username = By.cssSelector("#userEmail");
	private By Password = By.cssSelector("#userPassword");
	private By Login = By.cssSelector("#login");
	
	 public LoginPage(WebDriver driver)
	{
		 this.driver = driver;
	}
	
	public void login(String username,String password)
	{
		driver.findElement(Username).sendKeys(username);
		driver.findElement(Password).sendKeys(password);
		driver.findElement(Login).click();	
	}
	
//	public void goTo()
//	{
//		driver.get();
//		System.out.println(driver.getTitle());
//		
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//	}

}
