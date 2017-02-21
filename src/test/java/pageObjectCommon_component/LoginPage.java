package pageObjectCommon_component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics_component.*;

public class LoginPage extends BasePage
{
	
	@FindBy(id= "txtUsername")
	private WebElement userName;
	
	@FindBy(id= "txtPassword")
	private WebElement userPassword;
	
	@FindBy(id= "btnLogin")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) 
	{
	super(driver);
	PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String UN)
	{
		verifyElementPresent(userName);
		userName.sendKeys(UN);
	}
	
	public void setPassword(String PW)
	{
		verifyElementPresent(userPassword);
		userPassword.sendKeys(PW);
	}
	
	public void clickLogin()
	{
		verifyElementPresent(loginButton);
		loginButton.click();
	}
}
