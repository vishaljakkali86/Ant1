package generics_component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generics_component.*;

import pageObjectCommon_component.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


@Listeners(MyListeners.class)
public class BaseTest implements AutomationConstants{
	
	public Logger log; 
	public WebDriver driver;
	public ExtentTest testReport;
	
	public static String ubrowser="";
	public static String url;
	public static String un;
	public static String pw;
	public static String homepage;
	public static String loginpage;
	public static long timeout;
	public static ExtentReports eReport;
	public static boolean AutoLoginRequired=true;
	public static boolean AutoLogoutRequired=true;
	
	public BaseTest()
	{
		log=Logger.getLogger(this.getClass());
		log.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
		
	}
	
	@BeforeSuite
	
	public void initFrameWork()
	{
		log.info("Initializing Framework");
		eReport = new ExtentReports(REPORT_PATH+"/"+Utility.getFormatedDateTime()+".html");
		url = Property.getPropertyValue(CONFIG_FILE, "URL");
		un = Property.getPropertyValue(CONFIG_FILE, "UN");
		pw = Property.getPropertyValue(CONFIG_FILE, "PW");
		homepage = Property.getPropertyValue(CONFIG_FILE, "HOMEPAGE");
		loginpage = Property.getPropertyValue(CONFIG_FILE, "LOGINPAGE");
		timeout = Long.parseLong(Property.getPropertyValue(CONFIG_FILE, "IMPLICIT"));		
	}	
		
	@AfterSuite
	
	public void closeFrameWork()
	{
		log.info("Closing Framework");
		eReport.flush();
	}
	
	@Parameters({"browser"})
	@BeforeTest
	
	public void initBrowser(@Optional("firefox") String browser)
	{
		log.info("Execution started in the Browser:"+browser);
		ubrowser+=browser;
	}	
	
	@Parameters({"browser"})
	@AfterTest
	
	public void closeBrowser(@Optional("firefox") String browser)
	{
		log.info("Execution ended in the Browser:"+browser);
	}
	
	@Parameters({"browser"})
	@BeforeClass
	
	public void initApplication(@Optional("firefox") String browser)
	{
		log.info("Opening Application");
		if(browser.equals("chrome"))
		{
			System.setProperty(CHROME_KEY,CHROME_FILE);
			driver = new ChromeDriver();			
		}
		else
		{
			System.setProperty(FIREFOX_KEY,FIREFOX_FILE);
			driver = new FirefoxDriver();
		}
		
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);		
	}
	
	@AfterClass
	
	public void closeApplication()
	{
		log.info("Closing Application");
		driver.quit();
	}
	
	@BeforeMethod
	
	public void preCondition(Method method)
    {
	 testReport = eReport.startTest(method.getName());
	 
	 if(AutoLoginRequired)
	 {
		 log.info("Implicit Login");
		 driver.get(url);
		 LoginPage lp = new LoginPage(driver);
		 lp.setUserName(un);
		 lp.setPassword(pw);
		 lp.clickLogin();
		 lp.verifyURLhas(Property.getPropertyValue(CONFIG_FILE, "HOMEPAGE"));		 
	 }
	 else
	 {
		 log.warn("Explicit Login");
	 }
	 
	 AutoLoginRequired=true;
	
    }
	
	@AfterMethod
	
	public void postCondition(ITestResult result)
	{
		
		if(AutoLogoutRequired)
		 {
			 log.info("Implicit Logout");
			 HomePage hp = new HomePage(driver);
				hp.clickWelcome();
				hp.clickLogOut();
				hp.verifyURLhas(Property.getPropertyValue(CONFIG_FILE, "LOGINPAGE"));	
			 
			 
			 
			 
		 }
		 else
		 {
			 log.warn("Explicit Logout");
		 }
		 
		 AutoLogoutRequired=true;
		 
		 if(result.getStatus()==ITestResult.FAILURE)
		 {
			 testReport.log(LogStatus.FAIL, "Please Refer the Log");
			 testReport.log(LogStatus.FAIL, result.getThrowable());
		 }
		 else
		 {
			 testReport.log(LogStatus.PASS, "StepName", "Details");
		 }
		
	    }
	
	
	}