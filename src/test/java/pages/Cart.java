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
 * Created by anton on 15/05/18.
 * Cart page behavior described
 */
public class Cart extends BasePage {

    private static final String PAGE_URL = MAIN_URL + Links.cart;

    public Cart() {
        super(true);
    }

    @FindBy(xpath = "//span[text()='Shopping Cart']")
    private WebElement shoppingCartText;

    @FindBy(xpath = "//button[contains(@data-role,'proceed-to-checkout')]")
    private WebElement proceedToCheckoutButton;

    @FindBy (xpath = "//a[contains(@class,'action action-delete')]")
    private WebElement deleteProductButton;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        Log.info("Opening Cart page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Shopping Cart";

        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        Log.info("Cart page is opened");
        return shoppingCartText.isDisplayed();
    }

    public void checkIfCartIsEmpty() {
        Log.info("Checking if the Cart is empty");
        if (Drivers.getDriver().getPageSource().contains("You have no items in your shopping cart.")) {
            Log.info("Cart is empty");;
        } else while (!Drivers.getDriver().getPageSource().contains("You have no items in your shopping cart.")) {
            Log.info("Cart is not empty");
            deleteProductButton.click();
        }
    }

    public void proceedToCheckout() {
        Log.info("Proceeding to Checkout");
        proceedToCheckoutButton.click();
    }
}
