package pages;

import data.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage() {
        super(true);
    }

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[contains(text(), 'You added ')]")
    private WebElement successMessage;

    @FindBy (xpath = "//span[contains(text(),'Add to Wish List')]")
    private WebElement addToWishListButton;

    @Override
    protected void openPage() {
    }

    @Override
    public boolean isPageOpened() {
        Log.info("Opening Product page");
        return true;
    }

    public void open(String link) {
        Drivers.getDriver().get(MAIN_URL + link);
        addToCartButton.isDisplayed();
        Log.info(link + " product page is opened");
    }

    public void selectSize(int size) {
        Log.info("Selecting size attribute: " + size);
        WebElement sizeElement = Drivers.getDriver().findElement(By.xpath("//div[contains(@option-label,'" + size + "')]"));
        sizeElement.click();
    }

    public void selectColor(String color) {
        Log.info("Selecting color attribute:" + color);
        WebElement colorElement = Drivers.getDriver().findElement(By.xpath("//div[contains(@option-label,'" + color + "')]"));
        colorElement.click();
    }

    public void addToCart() {
        Log.info("Clicking Add to Cart button");
        addToCartButton.click();
        successMessage.isDisplayed();
    }

    public void addToWishList() {
        String expectedURL = "My Wish List";
        Log.info("Adding product to WishList");
        addToWishListButton.click();
        Assert.assertEquals(Drivers.getDriver().getTitle(),expectedURL);
    }

}
