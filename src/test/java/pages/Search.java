package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

public class Search extends BasePage {

    public Search() {
        super(true);
    }

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy (xpath = "//span[contains(@class,'base') and contains(text(),'Search results for:')]")
    private WebElement searchResultsText;

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        return true;
    }

    public void searchBy(String searchText) {

        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);

        searchResultsText.isDisplayed();

    }

    public void expectedNumberOfProducts(int number) {

        WebElement numberOfProducts = Drivers.getDriver().findElement(By.xpath("//span[contains(@class,'toolbar-number') and contains(text(),'" + number + "')]"));
        numberOfProducts.isDisplayed();

    }

    public void productNameIsDispayed(String productName) {

        WebElement name = Drivers.getDriver().findElement(By.xpath("//a[contains(@class,'product-item-link') and contains(text(),'" + productName + "')]"));
        name.isDisplayed();
    }
}
