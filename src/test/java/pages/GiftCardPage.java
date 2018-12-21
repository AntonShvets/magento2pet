package pages;

import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

/**
 * Created by anton on 14/05/18.
 * Gift Card page behavior described
 */
public class GiftCardPage extends BasePage {

    public  GiftCardPage() {
        super(true);
    }

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(css = "#giftcard-amount-input")
    private WebElement cardAmountField;

    @FindBy(css = "#qty")
    private WebElement quantityField;

    @FindBy(css = "#giftcard_recipient_name")
    private WebElement toField;

    @FindBy(css = "#giftcard_sender_name")
    private WebElement fromField;

    @FindBy(css = "#giftcard-message")
    private WebElement messageField;

    @FindBy(css = "#giftcard-amount-input-error")
    private WebElement amountErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'You added Gift card to your shopping cart.')]")
    private WebElement successMessage;

    @FindBy(id = "giftcard-amount-input-error")
    private WebElement inputErrorMessage;

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        System.out.println("Opening Gift Card page");
        return true;
    }

    public void open() {
        Main page = new Main();
        page.buyButton.click();
        Drivers.getDriver().get(Links.main + "gift-cards-online/gift-card.html");
        addToCartButton.isDisplayed();
    }

    public void addToCart(String amount, String quantity, String to, String from, String message) {
        cardAmountField.sendKeys(amount);
        quantityField.clear();
        quantityField.sendKeys(quantity);
        toField.sendKeys(to);
        fromField.sendKeys(from);
        messageField.sendKeys(message);
        addToCartButton.click();
    }

    public void cardAddedSuccessfully() {
        successMessage.isDisplayed();
        System.out.println("Gift card has been added to the Cart successfully");
    }

    public void inputErrorIsDisplayed() {
        inputErrorMessage.isDisplayed();
        System.out.println("Error message occurred as expected");
    }
}
