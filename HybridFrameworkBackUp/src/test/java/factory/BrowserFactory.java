package factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	public WebDriver startBrowser(String browserName, String url) 
	{ 
		System.out.println("LOG INFO: Starting session on "+browserName+" browser and launching application "+url );

		WebDriver driver = null;

		if (browserName.equalsIgnoreCase("chrome")) {
			
			Map<String, Object> pref = new HashMap<String, Object>();//Create an Object of HashMap class
			pref.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions(); 
			options.setExperimentalOption("prefs", pref);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "null");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			//options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
			//options.setExperimentalOption("credentials_enable_service", false);
			//options.setExperimentalOption("profile.password_manager_enabled", false);
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) 
		{
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		} else if (browserName.equalsIgnoreCase("edge")) 
		{
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/Drivers/msedgedriver.exe");
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("chromeheadless")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver(new ChromeOptions().setHeadless(true));

		} else if (browserName.equalsIgnoreCase("firefoxheadless")) 
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));

		} else {
			System.out.println("We do not support this browser...!");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		System.out.println("LOG INFO: Browser & Application are up and running" );
		return driver;

	}

}
