package test.webtestsbase;

/**
 * This class represents environment URLs.
 */
public enum UrlLoader {
    TEST("test"),
    QA("http://magento-222384-676323.cloudwaysapps.com/");
    private String urlName;

    private UrlLoader(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlName() {
        return urlName;
    }

    /**
     * returns url object by name
     * @param name name of url
     * @return url object
     */
    public static UrlLoader getByName(String name){
        for(UrlLoader loader : values()) {
            if(loader.getUrlName().equalsIgnoreCase(name)) {
                return loader;
            }
        }
        return null;
    }


}
