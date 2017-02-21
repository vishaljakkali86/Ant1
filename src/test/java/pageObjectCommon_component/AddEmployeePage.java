package pageObjectCommon_component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import generics_component.*;

public class AddEmployeePage extends BasePage
{
	
	@FindBy(id= "firstName")
	private WebElement firstName;
		
	@FindBy(id= "lastName")
	private WebElement lastName;
		
	@FindBy(id= "btnSave")
	private WebElement saveButton;	
	
	public AddEmployeePage(WebDriver driver) 
	{
	super(driver);
	PageFactory.initElements(driver, this);		
	}
	
	public void setFirstName(String FN)
	{
		verifyElementPresent(firstName);
		firstName.sendKeys(FN);
	}
	
	public void setLastName(String LN)
	{
		verifyElementPresent(lastName);
		lastName.sendKeys(LN);
	}
	
	public void clickSave()
	{
		verifyElementPresent(saveButton);
	    saveButton.click();
	}	
}