package pages;

import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.webtestsbase.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by anton on 15/05/18.
 * Cart page behavior described
 */
public class Cart extends BasePage {

    private static final String PAGE_URL = Links.main + "checkout/cart/";

    public Cart() {
        super(true);
    }

    @FindBy (xpath = "//span[text()='Shopping Cart']")
    private WebElement shoppingCartText;

    @FindBy (xpath = "//button[contains(@data-role,'proceed-to-checkout')]")
    private WebElement proceedToCheckoutButton;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        System.out.println("Opening Cart page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Shopping Cart";

        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
//        elementIsPresented(trolleyBlock);

        System.out.println("Cart page is opened");
        return shoppingCartText.isDisplayed();
    }

    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }
}
