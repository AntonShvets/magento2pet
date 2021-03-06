package test.webtestsbase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import test.configuration.BrowserConfig;
import test.configuration.UrlConfig;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Base class for web tests. It contains web driver {@link org.openqa.selenium.WebDriver} instance, used in all tests.
 * All communications with driver should be done through this class
 */
public class Drivers {
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    private static org.openqa.selenium.WebDriver driver;
    private static String url;

    /**
     * Getting of pre-configured {@link org.openqa.selenium.WebDriver} instance.
     * Please use this method only after call {@link #startBrowser(boolean) startBrowser} method
     *
     * @return webdriver object, or throw IllegalStateException, if driver has not been initialized
     */
    public static org.openqa.selenium.WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please call WebDriverFactory.startBrowser() before use this method");
        }
    }

    /**
     * Main method of class - initializes driver and starts browser.
     *
     * @param isLocal - are tests will be started locally or not
     */
    public static void startBrowser(boolean isLocal) {
        if (driver == null) {
            Browser browser = BrowserConfig.getConfig().getBrowser();
            if (!isLocal) {
                driver = new RemoteWebDriver(CapabilitiesGenerator.getDefaultCapabilities(browser));
            } else {
                switch (browser) {
                    case FIREFOX:
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new FirefoxDriver(firefoxOptions);
                        break;
                    case CHROMEHEADLESS:
                        ChromeOptions chromeHeadlessOptions = new ChromeOptions();
                        chromeHeadlessOptions.setBinary("/usr/bin/google-chrome");
                        chromeHeadlessOptions.addArguments("--headless");
                        chromeHeadlessOptions.addArguments("--disable-gpu");
                        chromeHeadlessOptions.addArguments("--no-sandbox");
                        driver = new ChromeDriver(chromeHeadlessOptions);
                        break;
                    case CHROME:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    case IE10:
                        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                        driver = new InternetExplorerDriver(ieOptions);
                        break;
                    case SAFARI:
                        SafariOptions safariOptions = new SafariOptions();
                        driver = new SafariDriver(safariOptions);
                        break;
                    default:
                        throw new IllegalStateException("Unsupported browser type");
                }
            }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Driver has already been initialized. Quit it before using this method");
        }
    }

    public static String getUrl() {
        UrlLoader loader = UrlConfig.getConfig().getUrl();
        return loader.getUrlName();
    }

    /**
     * Finishes browser
     */
    public static void finishBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Method for screenshot taking.
     * This method calls in tests listeners on test fail
     */
    public static void takeScreenShot(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                Date date = new Date();
                TakesScreenshot ts = (TakesScreenshot) Drivers.getDriver();
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./Screenshots/" + testResult.getInstanceName() + "." + testResult.getName() + "." + date + ".png"));
            } catch (Exception e) {
                System.out.println("Exception while taking a screenshot: " + e.getMessage());
            }
        }
    }

}
