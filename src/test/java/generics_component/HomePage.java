package generics_component;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
	
	@FindBy(id= "welcome")
	private WebElement welCome;
	
	@FindBy(xpath= "//a[text() = 'Logout']")
	private WebElement logOut;
	
	@FindBy(id= "menu_pim_viewPimModule")
	private WebElement PIM;
	
	@FindBy(id= "menu_pim_addEmployee")
	private WebElement addEmployee;	

	public HomePage(WebDriver driver) 
	{
	super(driver);
	PageFactory.initElements(driver, this);
	}
	
	public void clickWelcome()
	{
		verifyElementPresent(welCome);
		welCome.click();
	}
	
	public void clickLogOut()
	{
		verifyElementPresent(logOut);
		logOut.click();
	}
	
	public void clickPIM()
	{
		verifyElementPresent(PIM);
		PIM.click();
	}	
	
	public void clickAddEmployee()
	{
		verifyElementPresent(addEmployee);
		addEmployee.click();
	}
}