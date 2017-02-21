package pageObjectCommon_component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import generics_component.*;

public class EmployeeInformationPage extends BasePage{
	
	
	@FindBy(id= "menu_pim_viewEmployeeList")
	private WebElement EmployeeList;
	
	@FindBy(id = "btnDelete")
	private WebElement deleteButton;
	
	@FindBy(id = "dialogDeleteBtn")
	private WebElement dialogDeleteButton;	
	
	public EmployeeInformationPage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickEmployeeList()
	{
		verifyElementPresent(EmployeeList);
		EmployeeList.click();
	}
	
	public void clickDelete()
	{		
		verifyElementPresent(deleteButton);
		deleteButton.click();		
	}
	
	public void clickDialogDeleteBtn()
	{
		verifyElementPresent(dialogDeleteButton);
		dialogDeleteButton.click();
	}	
	
	public void verifyEmployeePresent(String fn)
	{
		boolean found = true;
		String xP = "//table[@id='resultTable']//a[text()= '"+fn+"']";
		
		try
		{
		WebElement element = driver.findElement(By.xpath(xP));
		verifyElementPresent(element);
		found=true;
		Reporter.log("PASS:Employee Present in Table"+fn, true);
		
		} catch (Exception e)
		{
			Reporter.log("FAIL:Employee Not Present in Table"+fn, false);
			found=false;
		}
		
		Assert.assertTrue(found);		
	}
	
	public void deleteEmployee(String fn)
	{
		String xP = "//a[text() = '"+fn+"']/../..//input[@type='checkbox']";
		try
		{
		WebElement element = driver.findElement(By.xpath(xP));
		element.click();
		clickDelete();
		clickDialogDeleteBtn();		
		}catch (Exception e)
		{
			e.printStackTrace();
		}		
     }
	
	public void verifyEmployeeNotPresent(String fn)
	{
		boolean found = true;
		String xP = "//table[@id='resultTable']//a[text()= '"+fn+"']";
		
		try
		{
		WebElement element = driver.findElement(By.xpath(xP));
		verifyElementPresent(element);
		Reporter.log("FAIL:Employee Present in Table:"+fn,true);
		found=true;
		
		} catch (Exception e)
		{
			Reporter.log("PASS:Employee Not Present in Table"+fn, false);
			found=false;
		}
		
		Assert.assertTrue(found);		
	}	
}