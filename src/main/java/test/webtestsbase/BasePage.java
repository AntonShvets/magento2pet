package test.webtestsbase;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.utils.TimeUtils;

/**
 * This is the main class for pages. When you create your page - you must extend your class from this
 */
public abstract class BasePage {

    public int defaultTimeout = 10;

    protected static final int WAIT_FOR_PAGE_LOAD_IN_SECONDS = 5;
    /**
     * In subclasses  should be used for page opening
     */
    protected abstract void openPage();

    /**
     * checks is page opened
     * @return true if opened
     */
    public abstract boolean isPageOpened();

    public BasePage(boolean openPageByUrl){
        if(openPageByUrl){
            openPage();
        }
        PageFactory.initElements(getDriver(), this);
        waitForOpen();
    }

    /**
     * Waiting for page opening
     */
    protected void waitForOpen(){
        int secondsCount = 0;
        boolean isPageOpenedIndicator = isPageOpened();
        while (!isPageOpenedIndicator && secondsCount < WAIT_FOR_PAGE_LOAD_IN_SECONDS) {
            TimeUtils.waitForSeconds(1);
            secondsCount++;
            isPageOpenedIndicator = isPageOpened();
        }
        if(!isPageOpenedIndicator) {
            throw new AssertionError("Page was not opened");
        }
    }

    /**
     * getting webdriver instance
     * @return initialized in tests webdriver instance
     */
    protected org.openqa.selenium.WebDriver getDriver(){
        return Drivers.getDriver();
    }

    public boolean elementIsDisplayed(WebElement element) {
        Dimension size = element.getSize();
        int result;
        result = size.getHeight() + size.getWidth();
        //System.out.println("Height: " + size.getHeight() + ", Width: " + size.getWidth());
        return result > 0;
    }

    public void elementIsNotDIsplayed(WebElement element) {
        Dimension size = element.getSize();
        int result;
        result = size.getHeight() + size.getWidth();
        System.out.println("Height: " + size.getHeight() + ", Width: " + size.getWidth());
        System.out.println("Result: " + result);
        Assert.assertEquals(result, 0);
    }

    public void scrollDown(int x, int y) {
        JavascriptExecutor jsx = (JavascriptExecutor)getDriver();
        jsx.executeScript("window.scrollBy(" + x + "," + y + ")", "");
    }
}
