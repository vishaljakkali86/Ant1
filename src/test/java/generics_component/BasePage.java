package generics_component;

import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import generics_component.*;

public class BasePage {
	
	public Logger log;
	public WebDriver driver;
	public String configFile;
	public long timeout;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		configFile = AutomationConstants.CONFIG_FILE;
		timeout = Long.parseLong(Property.getPropertyValue(configFile, "EXPLICIT"));		
	}
	
	public void verifyElementPresent(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void verifyURLis(String eURL)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlToBe(eURL));
	}
	
	public void verifyURLhas(String eURL)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(eURL));
	}
	
	public void verifyBrowser(WebDriver driver, String eTitle)
	{
		boolean found=false;
		String parent = driver.getWindowHandle();
		
		log.info("Parent Browser:"+driver.getTitle());
		
		Set<String> allWh = driver.getWindowHandles();
		
		for(String wh:allWh)
		{
			driver.switchTo().window(wh);
			String aTitle = driver.getTitle();
			log.info("Switched to Child Browser:");
			
			if(aTitle.equals(eTitle))
			{
				log.info("Child Browser exists:"+aTitle);
				found=true;
				break;
			}
						
			driver.switchTo().window(parent);
			log.info("Switched to Parent Window");
			
		}
		Assert.assertTrue(found, "Browser does not Exists"+eTitle);		
	}
	
	public void verifySearchListBox(WebElement element, String eText)
	{
		boolean found=false;
		
		Select select = new Select(element);
		
		List<WebElement> allOption = select.getOptions();
		
		for(WebElement option:allOption)
		{
			String aTest = option.getText();
			
			if(aTest.equals(eText))
			{
				log.info("Name appears in the Search List Box");
				found=true;
				break;
			}
			
			Assert.assertTrue(found, "Name does not appear in the Search List Box");
			
		}	
	}
}