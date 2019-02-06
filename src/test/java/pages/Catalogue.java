package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogue extends BasePage {

    public Catalogue() {
        super(true);
    }

    @FindBy (xpath = "//div[contains(@class,'products wrapper grid products-grid')]")
    private WebElement productsGrid;

    @FindAll({
            @FindBy(xpath = "//span[@data-price-type='finalPrice']")
    })
    private List<WebElement> priceValues;

    @Override
    protected void openPage() {
        Log.info("Opening Catalogue page");
    }

    @Override
    public boolean isPageOpened() {
        Log.info("Catalogue page is opened");
        return true;
    }

    public void open(String link) {
        Drivers.getDriver().get(Drivers.getUrl() + link);
        productsGrid.isDisplayed();
        Log.info(link + " page is opened");
    }

    public void expandFilter(String attribute) {
        Log.info("Expanding filter with " + attribute + " attribute");
        WebElement attributeName = Drivers.getDriver().findElement(By.xpath("//div[contains(text(),'" + attribute + "')]"));
        attributeName.click();
    }

    public void filterBy(String attributeValue) {
        Log.info("Filtering by " + attributeValue + " attribute");
        WebElement attributeValueName = Drivers.getDriver().findElement(By.xpath("//a[contains(text(),'" + attributeValue + "')]"));
        attributeValueName.click();
    }

    public boolean productsInCatalogue(int number) {
        Log.info("Checking that Catalogue page contains " + number + " products");
        WebElement numberOfProducts = Drivers.getDriver().findElement(By.xpath("//span[contains(@class,'toolbar-number') and contains(text(),'" + number + "')]"));
        return numberOfProducts.isDisplayed();
    }

    public void sortBy(String parameter) {
        Log.info("Sorting by " + parameter + " parameter");
        Select sortByDropDown = new Select(Drivers.getDriver().findElement(By.id("sorter")));
        sortByDropDown.selectByVisibleText(parameter);
    }

    public boolean sortingIsCorrect() {
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = priceValues;

        Log.info("Creating an array of Price values");
        for (WebElement i:elementList){
            obtainedList.add(i.getAttribute("data-price-amount"));
        }

        Log.info("Getting the array and sorting it in ascending order in the new array");
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s:obtainedList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);

        return sortedList.equals(obtainedList);
    }

    public void goToPage(int pageNumber) {
        Log.info("Going to page # " + pageNumber);
        String currentUrl = Drivers.getDriver().getCurrentUrl();
        Drivers.getDriver().get(currentUrl + "?p=" + pageNumber);
    }

    public int productsOnThePage() {
        Log.info("Getting a list of products that are on the page");
        List<WebElement> elementList = priceValues;
        int actualNumberOfProducts = elementList.size();

        Log.info("Products on the page: " + actualNumberOfProducts);
        return actualNumberOfProducts;
    }

}
