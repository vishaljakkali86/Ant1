package pageObject_Component;

import org.testng.annotations.Test;

import generics_component.BaseTest;
import generics_component.Excel;
import generics_component.HomePage;
import generics_component.Property;
import pageObjectCommon_component.LoginPage;


public class VerifyLoginLogout extends BaseTest{
	
	public VerifyLoginLogout()
	{
		AutoLoginRequired=false;
		AutoLogoutRequired=false;
	}
	
	@Test
	public void testLoginLogout() throws InterruptedException
	{
		String UN = Excel.getCellValue(INPUT_FILE, INPUT_SHEET, 1, 0);
		String PW = Excel.getCellValue(INPUT_FILE, INPUT_SHEET, 1, 1);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(UN);
		lp.setPassword(PW);
		lp.clickLogin();
		Thread.sleep(5000);
		lp.verifyURLhas(Property.getPropertyValue(CONFIG_FILE, "HOMEPAGE"));
		Thread.sleep(3000);
		HomePage hp = new HomePage(driver);
		hp.clickWelcome();
		Thread.sleep(3000);
		hp.clickLogOut();
		hp.verifyURLhas(Property.getPropertyValue(CONFIG_FILE, "LOGINPAGE"));		
	}
}