package pageObject_Component;

import org.testng.annotations.Test;

import generics_component.BaseTest;
import generics_component.Excel;
import generics_component.HomePage;
import pageObjectCommon_component.AddEmployeePage;
import pageObjectCommon_component.EmployeeInformationPage;


public class CreateDeleteEmployee extends BaseTest {
	
	public CreateDeleteEmployee()
	{
		AutoLoginRequired=true;
		AutoLogoutRequired=true;
	}
	
	@Test
	
	public void verifyCreateEmployee()
	{
		String FN = Excel.getCellValue(INPUT_FILE, "AddEmployee", 1, 0);
		String LN = Excel.getCellValue(INPUT_FILE, "AddEmployee", 1, 1);
		
		HomePage hp = new HomePage(driver);
		hp.clickPIM();
		hp.clickAddEmployee();
		
		AddEmployeePage aep = new AddEmployeePage(driver);
		aep.setFirstName(FN);
		aep.setLastName(LN);
		aep.clickSave();
		
		EmployeeInformationPage eip = new EmployeeInformationPage(driver);
		eip.clickEmployeeList();
		eip.verifyEmployeePresent(FN);
		eip.deleteEmployee(FN);
		eip.verifyEmployeeNotPresent(FN);
	}
}