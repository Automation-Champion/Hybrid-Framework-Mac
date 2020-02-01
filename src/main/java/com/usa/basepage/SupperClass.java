package com.usa.basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.usa.pagefactory.OutLinePage;
import com.usa.pagefactory.ScenarioPage;
import com.usa.utilty.UtilTest;

public class SupperClass {
	public static Logger logger;
	public static WebDriver driver;
	public static Properties prop;
	public ScenarioPage pf;
	public OutLinePage ot;
	public Properties configProp;

	public WebDriver getDriver() {
		return driver;
	}

	public SupperClass() {
		// Logger
		logger = Logger.getLogger("PMCS"); // Added logger
		PropertyConfigurator.configure("Log4j.properties");// Added logger
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/usa/config/Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization() throws Throwable {
		// Loading Config.properties file steps
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		// Loading Config.properties file is done
		String br = configProp.getProperty("browser");
		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(UtilTest.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(UtilTest.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.navigate().to(prop.getProperty("url"));

	}

	@SuppressWarnings("unlikely-arg-type")
	public void openBrowser() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			Reporter.log("=====Browser Session Started=====", true);
			logger.info("******** I am a chrome browser*********");
			System.setProperty("webdriver.chrome.driver", "/Users/mohammedalam/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Arrays.asList("ignore-certificate-errors"));
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			driver = new EventFiringWebDriver(new ChromeDriver(options));
		}

		else if (browserName.equalsIgnoreCase("headless")) {
			logger.info("******** I am a headless mode chrome browser*********");
			String chromeDriverPath = "/Users/mohammedalam/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
					"--ignore-certificate-errors", "--silent");
			driver = new ChromeDriver(options);
		}

		else if (browserName.equalsIgnoreCase("ie")) {
			logger.info("******** I am a ie browser*********");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.introduceFlakinessByIgnoringSecurityDomains().equals(true);
			ieOptions.requireWindowFocus().equals(true);
			ieOptions.ignoreZoomSettings().equals(true);
			ieOptions.enableNativeEvents().equals(false);
			ieOptions.enablePersistentHovering().equals(true);
			System.setProperty("webdriver.ie.driver", "./Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver(ieOptions);
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			logger.info("******** I am a edge browser*********");
			System.setProperty("webdriver.edge.driver", "./Driver/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(UtilTest.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(UtilTest.IMPLICIT_WAIT, TimeUnit.SECONDS);

	}

}
