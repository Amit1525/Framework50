package pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utility.Helper;

public class DashboardPage {
	
	WebDriver driver;
	By dashboardLinkLocator = By.xpath("//b[text()='Dashboard']");
	By assignLeaveLinkLocator = By.xpath("//span[text()='Assign Leave']");
	By empNameLocator = By.xpath("//label[text()='Employee Name ']//following::input[1]");
	By leaveTypeDropdownLocator = By.xpath("//label[text()='Leave Type ']//following::select[1]");
	By fromDateLocator = By.xpath("//label[text()='From Date ']//following::input[1]");
	By toDateLocator = By.xpath("//label[text()='To Date ']//following::input[1]");
	By fromDateCalendarImgLocator = By.xpath("//label[text()='From Date ']//following::img[1]");
	By todateCalendarImgLocator = By.xpath("//label[text()='To Date ']//following::img[1]");
	By partialDaysDropdownLocator = By.xpath("//label[text()='Partial Days']//following::select[1]");
	By durationDropdownLocator = By.xpath("//label[text()='Partial Days']//following::select[2]");
	By durationFromLocator = By.xpath("//label[text()='Partial Days']//following::select[4]");
	By durationToLocator = By.xpath("//label[text()='Partial Days']//following::select[5]");
	By commentLocator = By.xpath("//label[text()='Comment']//following::textarea");
	By assignButtonLocator = By.xpath("//input[@value='Assign']");	
	By OkButtonLocator = By.xpath("//input[@id='confirmOkButton']");
	By successAssignLocator = By.xpath("//div[contains(text(),'Assigned')]");
	By welcomeMessageLocator = By.xpath("//a[text()='Welcome Admin']");
	By logoutLocator = By.xpath("//a[text()='Logout']");
	By leaveLinkLocator = By.xpath("//b[text()='Leave']");
	By fromDateLeaveListLocator = By.xpath("//label[text()='From']//following::input[1]");
	By toDateLeaveListLocator = By.xpath("//label[text()='From']//following::input[2]");
	By allListCheckboxLocator = By.xpath("//input[@id='leaveList_chkSearchFilter_checkboxgroup_allcheck']");
	By EmpLeaveListLocator = By.xpath("//label[text()='Employee']//following::input[1]");
	By searchBtnLeaveListLocator = By.xpath("//input[@id='btnSearch']");
	By leaveListResultTableLocator = By.xpath("//table[@id='resultTable']");
	By leavesListsLocator = By.xpath("//table[@id='resultTable']//tbody//tr");
	By selectActionLocator = By.xpath("//select[@class='select_action quotaSelect']");
	By saveBtnLeaveListLocator = By.xpath("//input[@id='btnSave']");
	By leaveStatusCancelledLocator = By.xpath("//a[contains(text(),'Cancelled')]");
	By footerLocator = By.xpath("//div[@id='footer']");
	
	
	public DashboardPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void assignLeave(String EmpName)
	{
		Helper.waitForWebElementAndClick(driver, dashboardLinkLocator, "Click on Dashboard link after login");
		Helper.waitForWebElementAndClick(driver, assignLeaveLinkLocator, "Click on Assign Leave Link");
		Helper.waitForWebElementAndType(driver, empNameLocator, EmpName, "Enter employee name");
		Helper.waitForWebElementAndSelectDropdownWithVisibleText(driver, leaveTypeDropdownLocator, "Vacation US", "select leave type from dropdown");
		Helper.waitForWebElementAndType(driver, fromDateLocator, Helper.getCurrentPlusNumberOfDays(2), "Enter From Date");
		Helper.waitForWebElementAndPressTABkey(driver, fromDateLocator);
		Helper.waitForWebElementAndType(driver, toDateLocator, Helper.getCurrentPlusNumberOfDays(10), "Enter To Date");
		Helper.waitForWebElementAndPressTABkey(driver, toDateLocator);
		Helper.waitForWebElementAndSelectDropdownWithVisibleText(driver, partialDaysDropdownLocator, "All Days", "Select partial Days");
		Helper.waitForWebElementAndSelectDropdownWithVisibleText(driver, durationDropdownLocator, "Specify Time", "Select duration from dropdown");
		Helper.waitForWebElementAndSelectDropdownWithVisibleText(driver, durationFromLocator, "10:00", "Select duration from");
		Helper.waitForWebElementAndSelectDropdownWithVisibleText(driver, durationToLocator, "17:00", "Select duration To");
		Helper.waitForWebElementAndType(driver, commentLocator, "Travelling", "Add Leave comment");
		Helper.waitForWebElementAndClick(driver, assignButtonLocator, "Click on assign");
		Helper.waitForWebElementAndClick(driver, OkButtonLocator, "Click on OK");
		Helper.verifyPartialMessages(driver, successAssignLocator, "Assigned", "Verify success message on leave assignment");
		Helper.wait(1, "wait");
		Helper.captureScreenshot(driver);
		Helper.waitForWebElementAndClick(driver, welcomeMessageLocator, "click on Welcome Message");
		Helper.waitForWebElementAndClick(driver, logoutLocator, "Click on logout link");	
	}
	
	public void clearOldLeaves()  //String EmpName
	{
		Helper.waitForWebElementAndClick(driver, leaveLinkLocator, "Click on Leave Link after login");
		Helper.waitForWebElementAndClearInputField(driver, fromDateLeaveListLocator, "Clear From Date Field in Leave List");
		Helper.waitForWebElementAndClearInputField(driver, toDateLeaveListLocator, "Clear To Date Field in Leave List");
		Helper.waitForWebElementAndPressTABkey(driver, toDateLeaveListLocator);
		Helper.waitForWebElementAndClick(driver, allListCheckboxLocator, "Click on ALL checkbox");
		//Helper.waitForWebElementAndType(driver, EmpLeaveListLocator, EmpName, "Enter Employee Name");
		Helper.waitForWebElementAndClearInputField(driver, EmpLeaveListLocator, "Clear emp field");
		Helper.waitForWebElementAndClick(driver, searchBtnLeaveListLocator, "Click on Search Button in Leave List");
		Helper.waitForWebElementAndScrollTo(driver, footerLocator, "scroll to footer element");
		List<WebElement> leaves = driver.findElements(By.xpath("//select[@class='select_action quotaSelect']"));
		int totalLeaves = leaves.size();
		//System.out.println("ToTal Leaves rows----"+leaves.size());
		//WebElement leaveStatusCancelled = driver.findElement(leaveStatusCancelledLocator);
		//leaveStatusCancelled.isDisplayed()
		if(totalLeaves > 0 )  //|| (!leaveStatusCancelled.isDisplayed()
		{
			for (int i =0; i <= totalLeaves-1; i++) 
			{
				
				leaves = driver.findElements(By.xpath("//select[@class='select_action quotaSelect']"));
				WebElement elem = leaves.get(i);
				leaves = driver.findElements(By.xpath("//select[@class='select_action quotaSelect']"));
				Select sel = new Select(elem);
					
				//WebElement ele = driver.findElement(By.xpath("(//select[@class='select_action quotaSelect'])["+(i+1)+"]"));
				//Select sel = new Select(ele);
				////(By.xpath("//a[contains(text(),'"+ User +"')]//preceding::input[1]")))
				sel.selectByVisibleText("Cancel");
			}
			
			Helper.waitForWebElementAndClick(driver, saveBtnLeaveListLocator, "click on Save Button on Leave List");
			Helper.captureScreenshot(driver);
			Helper.waitForWebElementAndScrollTo(driver, footerLocator, "scroll to footer element");
			Helper.justScrollToTheElement(driver, welcomeMessageLocator, "Scroll to Element");
			Helper.waitForWebElementAndClick(driver, welcomeMessageLocator, "click on Welcome Message");
			Helper.waitForWebElementAndClick(driver, logoutLocator, "Click on logout link");
			
		}
		else
		{
			Helper.captureScreenshot(driver);
			Helper.justScrollToTheElement(driver, welcomeMessageLocator, "Scroll to Element");
			Helper.waitForWebElementAndClick(driver, welcomeMessageLocator, "click on Welcome Message");
			Helper.waitForWebElementAndClick(driver, logoutLocator, "Click on logout link");
		}
	}
	

}
