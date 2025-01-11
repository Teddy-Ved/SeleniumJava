package pageRepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utility.Utility;

public class CheckoutPage extends Utility{
	
	WebDriver driver;
	
	private By SelectCountryText = By.cssSelector("input[placeholder='Select Country']");
	private By CountriesListed = By.cssSelector("div section");
	private By SelectCountry = By.cssSelector("button[type='button']");
	private By PlaceOrder = By.cssSelector(".action__submit");
	
	public CheckoutPage(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver = driver;
	}
	
	public void SelectCountryToCheckout(String countryname)
	{
		Utility.ElementToBeClickable(SelectCountryText);
		Utility.ActionClickWithSendKeys(driver,SelectCountryText, countryname);
		Utility.ElementToBeClickable(CountriesListed);
		
		List<WebElement> countrylisted = driver.findElements(SelectCountry);
		long cnt = countrylisted.stream().count();
		
		for(int i = 0;i<cnt;i++)
		{
			System.out.println(countrylisted.get(i).getText());
			if(countrylisted.get(i).getText().equals(countryname))
			{
				countrylisted.get(i).click();
			}
		}
	}
	
	public void PlaceOrder()
	{
		Utility.ActionScrollToElement(driver, PlaceOrder);
		Utility.ActionClick(driver, PlaceOrder);
	}
	

}
