package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String email, String password , String exp)
	{
		logger.info("******Starting TC_003_LoginDDT******");
		logger.debug("Capturing application debug logs....");
		
		  try {
			
					// Accessing Home page, page object.
					HomePage hp=new HomePage(driver);
					
					// Click on My Account menu link on the home page
					hp.clickMyAccount();
					logger.info("Clicked on My Account menu link on the home page");
					
					// Click on Login menu link under My Account on the home page
					hp.clickLogin();
					logger.info("Clicked on Login menu link under My Account on the home page");
					
					// Accessing Login page, page object.
					LoginPage lp=new LoginPage(driver);
					
					// Enter email id in the login page from Excel file Opencart_LoginData.xlsx' under testdata folder
					logger.info("Entering valid email id..");
					lp.setEmail(email);
					
					// Enter password in the login page from Excel file Opencart_LoginData.xlsx' under testdata folder
					logger.info("Entering valid password..");
					lp.setPassword(password);
					
					// Click on Login button in the login page
					logger.info("Clicked on Login button..");
					lp.clickLogin();
					
					// Accessing My Account page, page object.
					MyAccountPage MyAP=new MyAccountPage(driver);
					boolean targetPage=MyAP.isMyAccountPageExists();
					
					if(exp.equalsIgnoreCase("Valid"))
					{
						if(targetPage==true)
						{
							MyAP.clkLogout();
							Assert.assertTrue(true);
						}
						else
						{
							Assert.assertTrue(false);
						}
					}
					
					if(exp.equalsIgnoreCase("Invalid"))
					{
						if(targetPage==true)
						{
							MyAP.clkLogout();
							Assert.assertTrue(false);
						}
						else
						{
							Assert.assertTrue(true);
						}
					}

		    }
			catch (Exception e)
			{
				Assert.fail("An exception occurred: " + e.getMessage());
			}
			logger.info("******Finished TC_003_LoginDDT******");
	}
}
