package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{

		@Test(groups={"sanity", "master"})
		public void verify_login()
		{
			logger.info("******Starting TC_002_LoginTest******");
			logger.debug("Capturing application debug logs....");
			
			try // This is used to catch any issues with the execution of the script
			{
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
			
			// Enter valid email id in the login page from properties file
			logger.info("Entering valid email id..");
			lp.setEmail(p.getProperty("email"));
			
			// Enter valid password in the login page from properties file
			logger.info("Entering valid password..");
			lp.setPassword(p.getProperty("password"));
			
			// Click on Login button in the login page
			logger.info("Clicked on Login button..");
			lp.clickLogin();
			
			// Accessing My Account page, page object.
			MyAccountPage MyAP=new MyAccountPage(driver);
			boolean targetPage = MyAP.isMyAccountPageExists();
			
				if(targetPage==true)
				{
					logger.info("Login test passed");
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("Login test failed");
					Assert.fail();
				}
			}
			catch(Exception e)
			{
				logger.error("Test Failed....");
				Assert.fail();
			}
			logger.error("******Finished TC_002_LoginTest******");
		}
		
}
