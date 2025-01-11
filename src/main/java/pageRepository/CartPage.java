package pageRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.Utility;

public class CartPage {
	
	WebDriver driver;
	
	private By ContinueShopping = By.xpath("(//button[@class='btn btn-primary'])[1]");
	private By AddedProducts = By.cssSelector("div.infoWrap");
	private By AddedproductText = By.cssSelector("h3");
	private By Checkout = By.xpath("//div/ul/li/button");
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public Boolean VerifyProductAddedtoCart(String productname)
	{
		Utility.TextToBePresentInElementLocated(ContinueShopping, "Continue Shopping");
		List<WebElement> addedProducts = driver.findElements(AddedProducts);
		
		Boolean prodaddedmtachflag = addedProducts.stream().anyMatch(addedproduct->addedproduct.findElement(AddedproductText).getText().equals(productname));
		return prodaddedmtachflag;
	}
	
	public void ProceedToCheckout()
	{
		Utility.ClickButton(driver, Checkout);
	}

}
