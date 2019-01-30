package test.configuration;

import test.configuration.properties.PropertiesLoader;
import test.configuration.properties.Property;
import test.configuration.properties.PropertyFile;
import test.webtestsbase.UrlLoader;

/**
 * Class for base tests properties - environment URL
 */

@PropertyFile("url.properties")
public class UrlConfig {

    private static UrlConfig config;

    public static UrlConfig getConfig() {
        if (config == null) {
            config = new UrlConfig();
        }
        return config;
    }

    public UrlConfig() {
        PropertiesLoader.populateUrl(this);
    }

    @Property("url.name")
    private String url = "test";

    public UrlLoader getUrl() {
        UrlLoader urlLoader = UrlLoader.getByName(url);
        if (urlLoader != null) {
            return urlLoader;
        } else {
            throw new IllegalStateException("URL name '" + url + "' is not valid");
        }
    }

}
