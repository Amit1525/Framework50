package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import dataprovider.ConfigDataProvider;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import utility.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public static ExtentReports reports; // This static will ensure there is only ONE report instance available for ALL the tests
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest logger;
	public ConfigDataProvider config;
	
	
	@BeforeSuite
	public void setUpReport()
	{
		System.out.println("LOG INFO: Setting up reports .." );
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Report"+Helper.getCurrentDateTime()+".html");
		htmlReporter.config().setReportName("Automation Report");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		System.out.println("LOG INFO: Reports are set up .." );
		
	}
	
	@BeforeClass
	public void startBrowserSession()
	{
		System.out.println("LOG INFO: Starting the browser session .." );
		
		config = new ConfigDataProvider();
		driver = new BrowserFactory().startBrowser(DataProviderFactory.getConfig().getBrowser(), DataProviderFactory.getConfig().getUrl());
		
	}
	
	@AfterClass
	public void closeBrowserSession()
	{
		driver.quit();
		System.out.println("LOG INFO: Closing the browser session .." );
		
	}
	
	@AfterMethod
	public void addResultToReport(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			System.out.println("LOG INFO: Test Result is Success .." );
			logger.pass("Test Passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{	
			System.out.println("LOG INFO: Test Result is Failed .." );
			try {
				logger.fail("Test Failed "+result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				System.out.println("Exception is >>>"+e.getMessage());
			}
		}
		else
		{
			System.out.println("LOG INFO: Test Result is Skipped .." );
			try {
				logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				System.out.println("Exception is >>>"+e.getMessage());
			}
		}
		
		reports.flush();
		System.out.println("LOG INFO: Adding Test Result to Reports .." );
	}
	

}
