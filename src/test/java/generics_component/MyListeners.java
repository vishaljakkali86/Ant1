package generics_component;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import generics_component.*;

public class MyListeners extends BaseTest implements ITestListener{
	
	public static int passCount=0;
	public static int failCount=0;
	public static int skipCount=0;

//	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getName();
		Excel.setCellValue(name, "FAIL", Utility.getFormatedDateTime(),BaseTest.ubrowser);
		
		String folder = AutomationConstants.SNAP_PATH;
		try
		{		
		Utility.getDesktopScreenshot(folder);
		}catch(Exception e)
		{
		e.printStackTrace();
		}
		failCount++;
	}

//	@Override
	public void onTestSkipped(ITestResult result) {
		String name = result.getName();
		Excel.setCellValue(name, "SKIP", Utility.getFormatedDateTime(),BaseTest.ubrowser);
		skipCount++;
		
	}

//	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onTestSuccess(ITestResult result) {
		String name = result.getName();
		Excel.setCellValue(name, "PASS", Utility.getFormatedDateTime(),BaseTest.ubrowser);
		passCount++;
		
	}
	
//	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("Pass Count:"+passCount, true);
		Reporter.log("Fail Count:"+failCount, true);
		Reporter.log("Skip Count:"+skipCount, true);
	}

}
