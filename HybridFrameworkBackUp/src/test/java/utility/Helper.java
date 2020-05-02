package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;



public class Helper {
	
	public void testMethodForGit()
	{
		System.out.println("A Test Method..");
	}
	
/*	public static void selectCalendarDateMMMyyyy(WebDriver driver,By monthYearLocator,By previousMonthLocator,By nextMonthLocator,By allDatesLocator, String monthInMMMyyyy,String day,String stepInfo)
	{
		
		while(true)
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(monthYearLocator));
			WebElement allDatesElement = wait.until(ExpectedConditions.elementToBeClickable(allDatesLocator));
			WebElement nextMonthElement = wait.until(ExpectedConditions.elementToBeClickable(nextMonthLocator));
			WebElement previousMonthElement = wait.until(ExpectedConditions.elementToBeClickable(previousMonthLocator));
			
			if(monthElement.getText().equalsIgnoreCase(monthInMMMyyyy))
					break;
			else
				nextMonthElement.click();
		}
		
		//Write table all row xPath and then use [contains(text(),'')] function and then pass Date Variable as [contains(text(),"+Data+")] 
			
	} */
	
	public static void waitForWebElementAndPerformDragNDrop(WebDriver driver, By srcLocator, By destLocator, String stepInfo) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		act.dragAndDrop(element1, element2).perform();//Perform method is a must when using Actions class.
		
	}
	
	public static void waitForWebElementAndPerformDragNDropInSteps(WebDriver driver, By srcLocator, By destLocator, int waitTimeInSeconds, String stepInfo) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		//act.clickAndHold(element1).pause(waitTimeInSeconds).moveToElement(element2).pause(waitTimeInSeconds).release(element2).build().perform();
		act.clickAndHold(element1).
		pause(Duration.ofSeconds(waitTimeInSeconds)).
		moveToElement(element2).
		pause(Duration.ofSeconds(waitTimeInSeconds)).
		build().perform();
		
	}
	
	public static void waitForWebElementAndPerformDragNDropWithXY(WebDriver driver, By srcLocator, int XCordinates, int YCordinates, String stepInfo) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		Actions act = new Actions(driver);
		act.dragAndDropBy(element1, XCordinates, YCordinates).perform();//Perform method is a must when using Actions class.
	}
	
	public static void waitForWebElementAndMouseHoverToElementAndClick(WebDriver driver,By srcLocator,By destLocator,int waitTimeInSeconds,String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		act.moveToElement(element1).pause(Duration.ofSeconds(waitTimeInSeconds)).click(element2).build().perform();
	}
	
	public static void waitForWebElementAndJustMouseHoverToElement(WebDriver driver,By srcLocator,int waitTimeInSeconds,String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		Actions act = new Actions(driver);
		act.moveToElement(element1).pause(Duration.ofSeconds(waitTimeInSeconds)).build().perform();
	}
	
	
	public static void waitForWebElementAndScrollTo(WebDriver driver, By locator, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}

	public static void justScrollToTheElement(WebDriver driver, By locator, String stepInfo) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 05);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void waitForWebElementAndPressTABkey(WebDriver driver, By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.sendKeys(Keys.TAB);
	}
	
	
	//Below method can be used for calendar, auto-suggestion, webtables and any other scenario where multiple options are present
	public static void waitForAutoSuggestionAndSelect(WebDriver driver, By locator, String text, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for(WebElement ele:elements)
		{
			if(ele.getText().contains(text))
			{
				//highLightWebElement(driver, ele);
				ele.click();
				break;
			}
		}
		Reporter.log("LOG INFO: "+stepInfo, true);
	}

	
	public static void waitForWebElementAndSelectDropdownWithVisibleText(WebDriver driver, By locator, String visibleText, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		Select sel = new Select(element);
		highLightWebElement(driver, element);
		sel.selectByVisibleText(visibleText);
		Reporter.log("LOG INFO: "+stepInfo, true);
	}
	
	public static void waitForWebElementAndSelectDropdownWithValue(WebDriver driver, By locator, String value, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		Select sel = new Select(element);
		highLightWebElement(driver, element);
		sel.selectByValue(value);
		Reporter.log("LOG INFO: "+stepInfo, true);
	}
	
	public static void waitForWebElementAndSelectDropdownWithIndex(WebDriver driver, By locator, int indexValue, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		Select sel = new Select(element);
		highLightWebElement(driver, element);
		sel.selectByIndex(indexValue);
		Reporter.log("LOG INFO: "+stepInfo, true);
	}
	
	public static void verifyCompleteMessages(WebDriver driver, By locator, String text, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(locator)).getText().equalsIgnoreCase(text));
		Reporter.log("LOG INFO: "+stepInfo, true);
	}
	
	public static void verifyPartialMessages(WebDriver driver, By locator, String text, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(locator)).getText().contains(text));
		Reporter.log("LOG INFO: "+stepInfo, true);

	}
	
	public static void waitForAlertAndAccept(WebDriver driver, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		Reporter.log("LOG INFO: "+stepInfo, true);
	}
	
	public static void waitForAlertAndDismiss(WebDriver driver, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		Reporter.log("LOG INFO: "+stepInfo, true);

	}
	
	public static void waitForAlertAndVerifyCompleteText(WebDriver driver, String expectedAlertMessage, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Assert.assertEquals(wait.until(ExpectedConditions.alertIsPresent()).getText(), expectedAlertMessage);
		Reporter.log("LOG INFO: "+stepInfo, true);

	}
	
	public static void waitForAlertAndVerifyPartialText(WebDriver driver, String expectedAlertMessage, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Assert.assertTrue(wait.until(ExpectedConditions.alertIsPresent()).getText().contains(expectedAlertMessage));
		Reporter.log("LOG INFO: "+stepInfo, true);

	}
	
	public static WebElement waitForWebElementAndClickUsingJS(WebDriver driver, By locator, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highLightWebElement(driver, element);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(locator));
		Reporter.log("LOG INFO: JavaScript Click Operation "+stepInfo, true);
		return element;
	}
	
	public static WebElement waitForWebElementAndTypeUsingJS(WebDriver driver, By locator, String data, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highLightWebElement(driver, element);
		((JavascriptExecutor)driver).executeScript("arguments[0].value=argument[1];", driver.findElement(locator), data);
		Reporter.log("LOG INFO: "+stepInfo, true);
		return element;
	}
	
	
	public static WebElement waitForWebElementAndClick(WebDriver driver, By locator, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highLightWebElement(driver, element);
		element.click();
		Reporter.log("LOG INFO: "+stepInfo, true);
		return element;
	}
	
	
	public static WebElement waitForWebElementAndType(WebDriver driver, By locator, String data, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.clear();
		highLightWebElement(driver, element);
		Helper.wait(1, "wait");
		element.sendKeys(data);
		Reporter.log("LOG INFO: "+stepInfo, true);
		return element;
	}
	
	public static WebElement waitForWebElementAndClearInputField(WebDriver driver, By locator, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highLightWebElement(driver, element);
		element.clear();
		Reporter.log("LOG INFO: "+stepInfo, true);
		return element;
	}
	
	
	public static void highLightWebElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border: 2px solid red');", element);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white');", element);
		
	}
	

	public static String getCurrentDateTime() {

		/*
		 * SimpleDateFormat myDateFormat = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		 * Date d = new Date(); String date = myDateFormat.format(d);
		 * System.out.println(date);
		 * 
		 * return date;
		 */

		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}
	
	public static String getCurrentDateInYYYYMMDD()
	{
		return new SimpleDateFormat("yyyy_MM_dd").format(new Date());
	}
	
	public static String getCurrentDateInDDMMYYYY()
	{
		return new SimpleDateFormat("dd_MM_yyyy").format(new Date());
	}
	
	public static String getCurrentDateInMMDDYYYY()
	{
		return new SimpleDateFormat("MM_dd_yyyy").format(new Date());
	}
	
	public static String getCurrentPlusNumberOfDays(int numberOfDays)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, numberOfDays);
		return dateFormat.format(c.getTime());
		
	}
	
	public static String captureScreenshot(WebDriver driver) {

		//String path=System.getProperty("user.dir")+"/Screenshots/"+Helper.getCurrentDateTime()+".png";
				    //System.getProperty("user.dir")+"/Reports/Report"+Helper.getCurrentDateTime()+".html";
		
		String path = System.getProperty("user.dir")+"/Screenshots/"+Helper.getCurrentDateTime()+".png";
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);

		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception is: " + e.getMessage());
		}

		return path;
	}
	
	public static WebElement waitForWebElement(WebDriver driver, By locator, int timeInSeconds, String stepInfo)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highLightWebElement(driver, element);
		Reporter.log("LOG INFO: "+stepInfo, true);
		return element;
	}
	
	public static WebElement waitForWebElement(WebDriver driver, String xpath, int time) {

		WebElement element = null;
		for (int i = 0; i < time; i++) {

			try {
				element = driver.findElement(By.xpath(xpath));
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
					System.out.println("Waiting for WebElement");
				} catch (InterruptedException e1) {

				}
			}
		}

		return element;

	}
	
	
	public static void wait(int time, String stepInfo) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Reporter.log("LOG INFO: "+stepInfo, true);

	}
	
	public static boolean isElementPresent(WebDriver driver, String xpathExpression) {
		
		int s = driver.findElements(By.xpath(xpathExpression)).size();
		if(s==0)
			return false;
		else
			return true;
		
	}
	
	public static void waitForPageToLoad(WebDriver driver, String stepInfo) {
		//check page load status
		//check AJAX and jQuery page load status
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		while(i!=10) {
			String state = (String)js.executeScript("return document.readyState;");
			System.out.println(state);

			if(state.equals("complete"))
				break;
			else
				wait(1, "wait for 1 second");
			i++;
		}

		i=0;
		while(i!=10) {
			Long d = (Long)js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue()==0)
				break;
			else
				wait(1, "wait for 1 second");
			i++;
		}
		
		Reporter.log("LOG INFO: "+stepInfo, true);

	}	

}

