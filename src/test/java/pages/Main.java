package pages;

import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

import static test.webtestsbase.Drivers.getDriver;

/**
 * Created by anton on 14/05/18.
 * Main page behavior described
 */
class Main extends BasePage {

    private static final String PAGE_URL = Links.main;

    Main() {
        super(true);
    }

    @FindBy(css = "#ui-id-3")
    public WebElement buyButton;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        System.out.println("Opening Main page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Home page";

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(buyButton));
        System.out.println("Main page is opened");
        return true;
    }

}
