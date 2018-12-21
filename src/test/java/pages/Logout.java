package pages;


import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;

/**
 * Created by anton on 21/05/18.
 */
public class Logout extends BasePage {


    private static final String PAGE_URL = Links.main + "customer/account/logout";

    public Logout() {
        super(true);
    }

    @FindBy(xpath = "//span[contains(text(), 'You are signed out')]")
    private WebElement loggedOutText;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        System.out.println("Logging out");
    }

    @Override
    public boolean isPageOpened() {


        return true;
    }

    public void successful() {

        String expectedTitle = "Home Page";

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(loggedOutText));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        System.out.println("User is logged out");
    }
}
