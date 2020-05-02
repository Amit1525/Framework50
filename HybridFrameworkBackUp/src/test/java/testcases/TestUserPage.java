package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dataprovider.DataProviderClassExcel;
import factory.DataProviderFactory;
import pages.BaseClass;
import pages.LoginPage;
import pages.UserPage;

public class TestUserPage extends BaseClass{
	
	LoginPage login;
	UserPage user;
	
	
	@Test(priority = 1, dataProvider = "excelAppData", dataProviderClass = DataProviderClassExcel.class)
	public void createUser(String EmpName, String userName, String password)
	{
		 logger = reports.createTest("Regression Test - Create User");
		
		 login = PageFactory.initElements(driver, LoginPage.class);
		 user = PageFactory.initElements(driver, UserPage.class);
		 login.verifyBeforeLogin();
		 login.verifySocialLinks();
		 login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		 login.verifyAfterLogin();		 
		 user.addUser(EmpName, userName, password); 
		 
		 
	}
	
	@Test(priority = 2, dataProvider = "excelAppData", dataProviderClass = DataProviderClassExcel.class)
	public void deleteUsers(String EmpName, String userName, String password)
	{
		login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		user.deleteUser(userName);
		
	}
		
	

}
