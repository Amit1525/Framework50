package utility;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pages.BaseClass;
import pages.LoginPage;

public class ClassToTestHelperClassMethods extends BaseClass{
	
	LoginPage login;
	By scrollTo=By.xpath("//div[text()='My Library']");
	By srcLocator = By.xpath("//span[text()='Learning DHTMLX Suite UI']");
	By destLocator = By.xpath("//li[text()='Zend Framework in Action']");
	
	@Test
	public void login()
	{
		logger = reports.createTest("dfdfd");
		Helper.waitForWebElementAndScrollTo(driver, scrollTo, "");
		//Helper.waitForWebElementAndPerformDragNDrop(driver, srcLocator, destLocator, "");
		Helper.waitForWebElementAndPerformDragNDropInSteps(driver, srcLocator, destLocator, 1, "");
	} 
	
	
	

}
