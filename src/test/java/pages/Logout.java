package pages;


import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

/**
 * Created by anton on 21/05/18.
 */
public class Logout extends BasePage {


    private static final String PAGE_URL = MAIN_URL + Links.logout;

    public Logout() {
        super(true);
    }

    @FindBy(xpath = "//span[contains(text(), 'You are signed out')]")
    private WebElement loggedOutText;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        Log.info("Logging out");
    }

    @Override
    public boolean isPageOpened() {
        return true;
    }

    public void successful() {
        Assert.assertTrue(loggedOutText.isDisplayed());
        Log.info("User is logged out");
    }
}
