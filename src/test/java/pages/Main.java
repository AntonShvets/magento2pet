package pages;

import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

/**
 * Main page behavior described
 */
public class Main extends BasePage {

    private static final String PAGE_URL = MAIN_URL;

    public Main() {
        super(true);
    }

    @FindBy (xpath = "//button[@title='Add to Cart']")
    private WebElement addToCartButton;

    @Override
    public void openPage() {
        Drivers.getDriver().get(PAGE_URL);
        Log.info("Opening Main page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Home Page";

        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        Log.info("Main page is opened");
        return true;
    }

}
