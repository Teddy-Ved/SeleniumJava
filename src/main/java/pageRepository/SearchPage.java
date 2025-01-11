package pageRepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.Utility;

public class SearchPage extends Utility {
	
	WebDriver driver;
	
	private By SignOut = By.cssSelector(".fa-sign-out");
	private By AllProducts = By.xpath("//div[@class='card-body']");
	private By ProductText = By.xpath("h5/b");
	private By Selectproduct = By.xpath("button[2]");
	private By Overlay = By.cssSelector("div.ngx-spinner-overlay");
	private By Cart = By.xpath("(//button[contains(@class,'btn-custom')])[3]");
	
	public SearchPage(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver = driver;
	}
	
	public void FindandSelectProduct(String productname)
	{
		Utility.VisibilityOfElementLocated(SignOut);
		List<WebElement> products = driver.findElements(AllProducts);
		WebElement prod = products.stream().filter(product->product.findElement(ProductText).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(Selectproduct).click();
		
		Utility.VisibilityOfElementLocated(Overlay);
		Utility.InvisibilityOfElementLocated(Overlay);
	}
	
	public void ClickonCart()
	{
		Utility.ClickButton(driver, Cart);
	}
	
	

}
