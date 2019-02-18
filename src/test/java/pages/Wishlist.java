package pages;

import data.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

public class Wishlist extends BasePage {

    public static final String PAGE_URL = MAIN_URL + Links.wishList;

    public Wishlist() {
        super(true);
    }

    @FindBy (xpath = "//span[contains(text(),'My Wish List')]")
    private WebElement wishListText;

    @FindBy (xpath = "//span[contains(text(),'Remove item')]")
    private WebElement removeItemButton;

    @FindBy (xpath = "//img[contains(@class,'product-image-photo')]")
    private WebElement productImage;

    @FindBy (xpath = "//button[contains(@title,'Add to Cart') and contains(@data-role,'tocart')]")
    private WebElement addProductToCartButton;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        Log.info("Opening WishList page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "My Wish List";

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(wishListText));
        elementIsDisplayed(wishListText);

        Log.info("WishList page is opened");
        return true;
    }

    public void clearWishList() {
        Log.info("Checking if the WishList is empty");
        if (Drivers.getDriver().getPageSource().contains("You have no items in your wish list.")) {
            Log.info("WishList is empty");;
        } else while (!Drivers.getDriver().getPageSource().contains("You have no items in your wish list.")) {
            Log.info("WishList is not empty");
            Actions action = new Actions(Drivers.getDriver());
            action.moveToElement(productImage).moveToElement(removeItemButton).click().build().perform();
        }
    }

    public void productIsDisplayed(String product) {
        Log.info("Checking if the product is displayed on WishList page");
        Assert.assertTrue(Drivers.getDriver().getPageSource().contains(product));
        Log.info(product + " URL is displayed on the page");
    }

    public void addProductToCart(String product) {
        Log.info("Adding product to cart");
        Actions action = new Actions(Drivers.getDriver());
        action.moveToElement(Drivers.getDriver().findElement(By.xpath("//a[contains(@href,'" + product + "')]"))).moveToElement(addProductToCartButton).click().build().perform();
        Assert.assertTrue(Drivers.getDriver().getPageSource().contains(product));
    }

    public boolean wishListIsEmpty() {
        Log.info("Checking that WishList is empty");
        return Drivers.getDriver().getPageSource().contains("You have no items in your wish list.");
    }

}
