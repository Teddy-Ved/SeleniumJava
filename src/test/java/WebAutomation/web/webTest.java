package WebAutomation.web;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class webTest {

	public WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeClass
	public void SetUpBrowser(@Optional("chrome")String browser)
	{
		driver = baseClass.getInstance(browser).getDriver();
		driver.get("https://rahulshettyacademy.com/client");
		System.out.println(driver.getTitle());
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
	}
	
	@Test
	public void launchTestpage() 
	{		
		//login to page
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("testandlearn@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Testlearn12#");
		driver.findElement(By.cssSelector("#login")).click();
		
		//wait for page to land
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-sign-out")));
		
		System.out.println(driver.getTitle());
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
//		for(int i = 0;i<products.size();i++)
//		{
//			System.out.println(products.get(i).getText());
//		}
		////div[@class='card']/div/button[2]
		WebElement prod = products.stream().filter(product->product.findElement(By.xpath("h5/b")).getText().equals("QWERTY")).findFirst().orElse(null);
		prod.findElement(By.xpath("button[2]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'btn-custom')])[3]")).click();
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//button[@class='btn btn-primary'])[1]"), "Continue Shopping"));
		
		List<WebElement> addedProducts = driver.findElements(By.cssSelector("div.infoWrap"));
		
		Assert.assertTrue(addedProducts.stream().anyMatch(addedproduct->addedproduct.findElement(By.cssSelector("h3")).getText().equals("QWERTY")));
		
		//click checkout button
		driver.findElement(By.xpath("//div/ul/li/button")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Select Country']")));
		
		//Enter country and select
		Actions action = new Actions(driver);
		action.click(driver.findElement(By.cssSelector("input[placeholder='Select Country']"))).sendKeys("India").build().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div section")));
		
		List<WebElement> countrylisted = driver.findElements(By.cssSelector("button[type='button']"));
		long cnt = countrylisted.stream().count();
		

		for(int i = 0;i<2;i++)
		{
			System.out.println(countrylisted.get(i).getText());
			if(countrylisted.get(i).getText().equals("India"))
			{
				countrylisted.get(i).click();
			}
		}
		
		//click place order
		action.scrollToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		action.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
//		driver.findElement(By.cssSelector(".action__submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		
		String SuccessMessgae = driver.findElement(By.cssSelector(".hero-primary")).getText().toLowerCase();
		Assert.assertEquals(SuccessMessgae, "Thankyou for the order.".toLowerCase());
		
		
	}
	
	@AfterClass
	public void teardown()
	{
		baseClass.quitBrowser();
	}
}
