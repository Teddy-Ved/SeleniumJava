package pageRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utility.Utility;

public class FinalPurchasePage {
	
	WebDriver driver;
	
	private By ConfirmationMessage = By.cssSelector(".hero-primary");
	
	public FinalPurchasePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public Boolean VerifyOrderSuccesfull(String expectedmessage)
	{
		Utility.VisibilityOfElementLocated(ConfirmationMessage);
		
		String SuccessMessgae = driver.findElement(ConfirmationMessage).getText().toLowerCase();
		Boolean confirmflag = SuccessMessgae.equals(expectedmessage.toLowerCase());
		return confirmflag;
	}

}
