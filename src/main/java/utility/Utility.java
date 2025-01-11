package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	public WebDriver driver;
	static WebDriverWait wait;
	static Actions action;
	
	public Utility(WebDriver driver) throws IOException
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		action = new Actions(driver);
	}
	
	public static void VisibilityOfElementLocated(By element)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public static void InvisibilityOfElementLocated(By element)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	public static void ClickButton(WebDriver driver,By element)
	{
		driver.findElement(element).click();
	}

	public static void TextToBePresentInElementLocated(By element,String text)
	{
		wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
	}
	
	public static void ElementToBeClickable(By element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void ActionClick(WebDriver driver,By element)
	{
		action.click(driver.findElement(element)).build().perform();
	}
	
	public static void ActionClickWithSendKeys(WebDriver driver,By element,String text)
	{
		action.click(driver.findElement(element)).sendKeys(text).build().perform();
	}
	
	public static void ActionScrollToElement(WebDriver driver,By element)
	{
		action.scrollToElement(driver.findElement(element)).build().perform();
	}
	
	public String takeScreenshot(String tcname , WebDriver driver) throws IOException
	{
		File path = new File(System.getProperty("user.dir")+"\\screenshots\\"+tcname+".png");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, path);
		return System.getProperty("user.dir")+"\\screenshots\\"+tcname+".png";
	}
	
	
}
