package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	
	
	@Test(groups={"regression", "master"})
	public void verify_account_registration()
	{
		logger.info("******Starting TC_001_AccountRegistrationTest******");
		
		logger.debug("Capturing application debug logs....");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on registration link");
		
		logger.info("Entering customer info");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		//regpage.setFirstName("abc");
		//regpage.setLastName("Labc");
		//regpage.setEmail("abc@gmail.com");
		//regpage.setPassword("TESTsp");
		
		regpage.setFirstName(randomeString().toUpperCase());//Randomly generated FirstName
		regpage.setLastName(randomeString().toUpperCase());//Randomly generated LastName
		regpage.setEmail(randomeString()+"@gmail.com"); //Randomly generated email 
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("clicked on continue..");
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("Validating expected message..");
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch (Exception e)
		{
			logger.error("Test Failed....");
			Assert.fail();
		}
		
		logger.info("******Finished TC_001_AccountRegistrationTest******");
	}
}