package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.Helper;


public class LoginPage {
	
	
	WebDriver driver;
	WebDriverWait wait;
	By forgtoPasswordLink = By.xpath("//a[text()='Forgot your password?']");
	By usernameLocator = By.id("txtUsername");
	By passwordLocator = By.id("txtPassword");
	By loginButtonLocator = By.xpath("//input[@id='btnLogin']");
	By facebookIcon = By.xpath("//img[contains(@alt,'Facebook')]");
	By youtubeIcon = By.xpath("//img[contains(@alt,'youtube')]");
	By linkedinIcon = By.xpath("//img[contains(@alt,'LinkedIn')]");
	By twitterIcon = By.xpath("//img[contains(@alt,'twitter')]");

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		//this.wait = new WebDriverWait(driver, 20);
	}
	
	public void signIntoApplication(String username, String password)
	{
	/*	WebElement uname = driver.findElement(usernameLocator);
		uname.click();
		uname.sendKeys(username);
		WebElement pass = driver.findElement(passwordLocator);
		pass.click();
		pass.sendKeys(password);
		WebElement loginBtn = driver.findElement(loginButtonLocator);
		loginBtn.click();  */
		
		Helper.waitForWebElementAndType(driver, usernameLocator, username, "Enter Username");
		Helper.waitForWebElementAndType(driver, passwordLocator, password, "Enter Password");
		Helper.waitForWebElementAndClick(driver, loginButtonLocator, "Click on Login Button");
		
	}
	
	public void verifyBeforeLogin()
	{
		Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));

	}
	
	public void verifyAfterLogin()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}
	
	public void verifySocialLinks()
	{
		Assert.assertTrue(driver.findElement(facebookIcon).isDisplayed());
		Assert.assertTrue(driver.findElement(youtubeIcon).isDisplayed());
		Assert.assertTrue(driver.findElement(linkedinIcon).isDisplayed());
		Assert.assertTrue(driver.findElement(twitterIcon).isDisplayed());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
