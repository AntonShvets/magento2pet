package pages;

import data.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage() {
        super(true);
    }

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[contains(text(), 'You added ')]")
    private WebElement successMessage;

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        System.out.println("Opening Product page");
        return true;
    }

    public void open(String link) {
        Drivers.getDriver().get(Links.main + link);
        addToCartButton.isDisplayed();
    }

    public void selectSize(int size) {

        WebElement sizeElement = Drivers.getDriver().findElement(By.xpath("//div[contains(@option-label,'" + size + "')]"));
        sizeElement.click();

    }

    public void selectColor(String color) {

        WebElement colorElement = Drivers.getDriver().findElement(By.xpath("//div[contains(@option-label,'" + color + "')]"));
        colorElement.click();
    }

    public void addToCart() {
        addToCartButton.click();
        successMessage.isDisplayed();

    }

}
