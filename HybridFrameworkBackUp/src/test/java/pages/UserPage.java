package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Helper;

public class UserPage {
	
	WebDriver driver;
	String checkBoxUsername;
	By adminTabLocator = By.xpath("//b[text()='Admin']");
	By addButtonLocator = By.xpath("//input[@value='Add']");
	By empNameLocator = By.xpath("//label[text()='Employee Name']//following::input[1]");
	By usernameLocator = By.xpath("//label[text()='Username']//following::input[1]");
	By passwordLocator = By.xpath("//label[text()='Password']//following::input[1]");
	By confirmPasswordLocator = By.xpath("//label[text()='Confirm Password']//following::input[1]");
	By saveButtonLocator = By.xpath("//input[@value='Save']");
	By successMessageLocator = By.xpath("//*[contains(text(),'Successfully Saved')]");
	By searchUsernameLocator = By.xpath("//label[text()='Username']//following::input[1]");
	By searchButtonLocator = By.xpath("//input[@value='Search']");
	By checkBoxUsernameLocator = By.xpath("//a[contains(text(),'"+ checkBoxUsername +"')]//preceding::input[1]");
	By deleteButtonLocator = By.xpath("//input[@value='Delete']");
	By deleteDialogBtnLocator = By.xpath("//input[@id='dialogDeleteBtn']");
	By welcomeMessageLocator = By.xpath("//a[text()='Welcome Admin']");
	By logoutLocator = By.xpath("//a[text()='Logout']");
	
	
	public UserPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void addUser(String EmpName, String userName, String password)
	{
		Helper.waitForWebElementAndClick(driver, adminTabLocator, "Click on Admin Tab");
		Helper.waitForWebElementAndClick(driver, addButtonLocator, "Click on Add Button");
		Helper.waitForWebElementAndType(driver, empNameLocator, EmpName, "Type Emp Name");
		Helper.waitForWebElementAndType(driver, usernameLocator, userName, "Type Username");
		Helper.waitForWebElementAndType(driver, passwordLocator, password, "Type password");
		Helper.waitForWebElementAndType(driver, confirmPasswordLocator, password, "Type confirmPassword");
		Helper.waitForWebElementAndClick(driver, saveButtonLocator, "Click on Save Button");	
		Helper.verifyPartialMessages(driver, successMessageLocator, "Saved", "Verify success message on save");
		Helper.waitForWebElementAndClick(driver, welcomeMessageLocator, "click on Welcome Message");
		Helper.waitForWebElementAndClick(driver, logoutLocator, "Click on logout link");
		
	}
	
	public void deleteUser(String userName)
	{
		
		Helper.waitForWebElementAndClick(driver, adminTabLocator, "Click on Admin Tab");
		Helper.waitForWebElementAndType(driver, searchUsernameLocator, userName, "Enter Username to be deleted");
		Helper.waitForWebElementAndClick(driver, searchButtonLocator, "Click on Search Button");
		Helper.waitForWebElementAndClick(driver, By.xpath("//a[contains(text(),'"+ userName +"')]//preceding::input[1]"), "Select Username to be deleted");
		Helper.waitForWebElementAndClick(driver, deleteButtonLocator, "Click on delete button");
		Helper.waitForWebElementAndClick(driver, deleteDialogBtnLocator, "Click on OK button on dialog box");
		Helper.waitForWebElementAndClick(driver, welcomeMessageLocator, "click on Welcome Message");
		Helper.waitForWebElementAndClick(driver, logoutLocator, "Click on logout link");
				
	}
	
	

}
