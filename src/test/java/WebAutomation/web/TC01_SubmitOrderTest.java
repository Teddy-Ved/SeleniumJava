package WebAutomation.web;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageRepository.CartPage;
import pageRepository.CheckoutPage;
import pageRepository.FinalPurchasePage;
import pageRepository.LoginPage;
import pageRepository.SearchPage;
import testData.Getjsondata;
import utility.Extentreport;
import utility.LoadPropertydata;
import utility.Utility;

public class TC01_SubmitOrderTest extends LoadPropertydata{
	
	public WebDriver driver;
	String brow,Url;
	Getjsondata tcdata;
	
	TC01_SubmitOrderTest() throws IOException {
		super();
		tcdata = new Getjsondata();
	}
	
	@BeforeClass
	public void Initiatedriver()
	{	
		driver = baseClass.getInstance(LoadPropertydata.getBrow()).getDriver();
		driver.get(LoadPropertydata.getUrl());
		System.out.println(driver.getTitle());
		System.out.println(this.getClass().getSimpleName());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(dataProvider = "getdata")
	public void launchTestpage(Map<String, String> Input) throws IOException 
	{		
		
		LoginPage loginpage = new LoginPage(driver);
		SearchPage searchpage = new SearchPage(driver);
		CartPage cartpage = new CartPage(driver);
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		FinalPurchasePage finalpurchasepage = new FinalPurchasePage(driver);
		Utility utility = new Utility(driver);
			
		
		//login to page
//		loginpage.goTo();
		loginpage.login(Input.get("UserName"), Input.get("Password"));
		
//		find and select product
		searchpage.FindandSelectProduct(Input.get("Product"));
		searchpage.ClickonCart();	
		
		Boolean flag = cartpage.VerifyProductAddedtoCart(Input.get("Product"));
		Assert.assertTrue(flag);
		cartpage.ProceedToCheckout();
		
		checkoutpage.SelectCountryToCheckout(Input.get("Country"));
		checkoutpage.PlaceOrder();
		
		Boolean confflag = finalpurchasepage.VerifyOrderSuccesfull("Thankyou for the order.");
		Assert.assertTrue(confflag);
		
//		utility.takeScreenshot("OrderStatus",driver);
		
		
	}
	
	@DataProvider(name = "getdata")
	public Object[] getSOdata() throws IOException
	{
		String testcasename = this.getClass().getSimpleName();
		System.out.println(testcasename);
		String filename = "SubmitOrder.json";
		System.out.println(filename);
		return new Object[] {tcdata.getjsondata(testcasename, filename)};
	}
	
	@AfterClass
	public void teardown()
	{
		baseClass.quitBrowser();
	}
	
}
