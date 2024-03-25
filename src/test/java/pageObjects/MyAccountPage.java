package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	
	//Web Elements
	
	// This is the locator for My Account label in My Account page
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement lblMyAccount;
	
	// This is the locator for Logout link (bottom right corner) in My Account page
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	//Action Methods
	
	//This method validates the presence of 'My Account' label in My Account page
	public boolean isMyAccountPageExists()
	{
		try
		{
			return(lblMyAccount.isDisplayed());
		}
		catch (Exception e)
		{
			return(false);
		}
		
	}
	
	//This method performs the click action on Login link
	public void clkLogout()
	{
		lnkLogout.click();
	}
}
