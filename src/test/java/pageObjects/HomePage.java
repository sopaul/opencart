package pageObjects;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//OR
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class HomePage extends BasePage
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Web Elements
	
	// This is the locator for My Account link in the home page menu (Top Right)
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	// This is the locator for Register link in the home page (under My Account dropdown menu)
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	// This is the locator for Login link in the home page (under My Account dropdown menu)
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")  //For Search Product Test
	WebElement txtSearchbox;
	
	@FindBy(xpath="//div[@id='search']//button[@type='button']") //For Search Product Test
	WebElement btnSearch;
	
	// Action Methods
	//This method click on  'My Account'link in the home page menu (Top Right)
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	//This method click on 'Register'link in the home page menu (under My Account dropdown menu)
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	//This method click on 'Login'link in the home page menu (under My Account dropdown menu)
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	public void enterProductName(String pName)   //For Search Product Test
	{
		txtSearchbox.sendKeys(pName);
	}
	
	public void clickSearch()  //For Search Product Test
	{
		btnSearch.click();
	}
	
}
