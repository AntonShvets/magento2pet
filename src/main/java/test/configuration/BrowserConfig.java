package test.configuration;

import test.configuration.properties.PropertiesLoader;
import test.configuration.properties.Property;
import test.configuration.properties.PropertyFile;
import test.webtestsbase.Browser;

/**
 * Class for base tests properties - browser name and version
 */
@PropertyFile("config.properties")
public class BrowserConfig {

    private static BrowserConfig config;

    public static BrowserConfig getConfig() {
        if (config == null) {
            config = new BrowserConfig();
        }
        return config;
    }

    public BrowserConfig() {
        PropertiesLoader.populate(this);
    }

    @Property("browser.name")
    private String browser = "firefox";

    @Property("browser.version")
    private String version = "";

    /**
     * getting browser object
     * @return browser object
     */
    public Browser getBrowser() {
        Browser browserForTests = Browser.getByName(browser);
        if (browserForTests != null) {
            return browserForTests;
        } else {
            throw new IllegalStateException("Browser name '" + browser + "' is not valid");
        }
    }

    /**
     * getting browser version
     * @return browser version
     */
    public String getBrowserVersion() {
        return version;
    }


}
