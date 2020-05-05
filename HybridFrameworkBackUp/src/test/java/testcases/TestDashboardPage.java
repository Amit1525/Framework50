package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dataprovider.DataProviderClassExcel;
import factory.DataProviderFactory;
import pages.BaseClass;
import pages.DashboardPage;
import pages.LoginPage;

public class TestDashboardPage extends BaseClass{
	
	DashboardPage dashboard;
	LoginPage login;
	
	
	@Test(priority = 1)
	public void clearLeaves()
	{
		logger = reports.createTest("Clear Leaves Report");
		dashboard = PageFactory.initElements(driver, DashboardPage.class);
		login = PageFactory.initElements(driver, LoginPage.class);
		login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		dashboard.clearOldLeaves();
	}  
	
	@Test(priority = 2, dataProvider = "excelAppData", dataProviderClass = DataProviderClassExcel.class)
	public void assignLeaveToEmployee(String EmpName, String userName, String password)
	{
		logger = reports.createTest("Assign Leaves Report");
		
		dashboard = PageFactory.initElements(driver, DashboardPage.class);
		login = PageFactory.initElements(driver, LoginPage.class);
		login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		dashboard.assignLeave(EmpName);	
	} 
	
	
	@Test (priority = 3, dataProvider = "excelAppData", dataProviderClass = DataProviderClassExcel.class)
	public void generateLeaveReportForEmp(String EmpName, String userName, String password)
	{
		logger = reports.createTest("Generate Leaves Report Test");
		login = PageFactory.initElements(driver, LoginPage.class);
		dashboard = PageFactory.initElements(driver, DashboardPage.class);
		login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		dashboard.generateLeaveReport(EmpName);
		
	} 
	
}
