package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

import java.util.List;

public class Search extends BasePage {

    public Search() {
        super(true);
    }

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy (xpath = "//span[contains(@class,'base') and contains(text(),'Search results for:')]")
    private WebElement searchResultsText;

    @FindBy (xpath = "//div[contains(text(),'Your search returned no results.')]")
    public WebElement noResultsText;

    @Override
    protected void openPage() {
        Log.info("Starting to search");
    }

    @Override
    public boolean isPageOpened() {
        return true;
    }

    public void searchBy(String searchText) {

        Log.info("Searching for: " + searchText);
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);

        searchResultsText.isDisplayed();

    }

    public boolean expectedNumberOfProducts(int number) {

        Log.info("Expected number of products in search results: " + number);
        WebElement numberOfProducts = Drivers.getDriver().findElement(By.xpath("//span[contains(@class,'toolbar-number') and contains(text(),'" + number + "')]"));
        return numberOfProducts.isDisplayed();

    }

    public boolean productNameIsDisplayed(String productName) {

        Log.info("Appropriate product name is displayed:" + productName);
        WebElement name = Drivers.getDriver().findElement(By.xpath("//a[contains(@class,'product-item-link') and contains(text(),'" + productName + "')]"));
        return name.isDisplayed();
    }

    public int productsWithAttributeDisplayed(String color) {

        Log.info("Getting an array of elements that have appropriate color attribute: " + color);
        List<WebElement> list = Drivers.getDriver().findElements(By.xpath("//div[contains(@class,'swatch-option color') and contains(@option-label,'" + color + "')]"));

        Log.info("Returning the number of products that are displayed with appropriate attribute:" + list.size());
        return list.size();

    }
}
