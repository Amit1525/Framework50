package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dataprovider.ExcelDataProvider;
import factory.DataProviderFactory;
import pages.BaseClass;
import pages.LoginPage;

public class TestLoginPage extends BaseClass{
	
	LoginPage login;
	
	@Test
	public void testLoginToApplication()
	{
		 logger = reports.createTest("Login To Application Test");
		
		 login = PageFactory.initElements(driver, LoginPage.class);
		 login.verifyBeforeLogin();
		 login.verifySocialLinks();
		 login.signIntoApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		 login.verifyAfterLogin();
	}
}
