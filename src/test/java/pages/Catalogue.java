package pages;

import data.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogue extends BasePage {

    public Catalogue() {
        super(true);
    }

    @FindBy (xpath = "//div[contains(@class,'products wrapper grid products-grid')]")
    private WebElement productsGrid;

    @FindBy (xpath = "//span[@data-price-type='finalPrice']")
    private WebElement priceValue;

    @FindAll({
            @FindBy(xpath = "//span[@data-price-type='finalPrice']")
    })
    private List<WebElement> priceValues;

    @Override
    protected void openPage() {
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        System.out.println("Opening Catalogue page");
        return true;
    }

    public void open(String link) {

        Drivers.getDriver().get(Links.main + link);
        productsGrid.isDisplayed();

    }

    public void expandFilter(String attribute) {

        WebElement attributeName = Drivers.getDriver().findElement(By.xpath("//div[contains(text(),'" + attribute + "')]"));
        attributeName.click();

    }

    public void filterBy(String attributeValue) {

        WebElement attributeValueName = Drivers.getDriver().findElement(By.xpath("//a[contains(text(),'" + attributeValue + "')]"));
        attributeValueName.click();

    }

    public void productsInCatalogue(int number) {

        WebElement numberOfProducts = Drivers.getDriver().findElement(By.xpath("//span[contains(@class,'toolbar-number') and contains(text(),'" + number + "')]"));
        numberOfProducts.isDisplayed();

    }

    public void sortBy(String parameter) {

        Select sortByDropDown = new Select(Drivers.getDriver().findElement(By.id("sorter")));
        sortByDropDown.selectByVisibleText(parameter);

    }

    public void sortingIsCorrect() {

        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = priceValues;

        // Creating an array of "Price" values:
        for (WebElement i:elementList){
            obtainedList.add(i.getAttribute("data-price-amount"));
        }

        // Getting the array and sorting it in ascending order in the new array
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s:obtainedList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);

        // Asserting that new sorted array is equal to the original one
        Assert.assertTrue(sortedList.equals(obtainedList));

    }

    public void goToPage(int pageNumber) {

        String currentUrl = Drivers.getDriver().getCurrentUrl();
        Drivers.getDriver().get(currentUrl + "?p=" + pageNumber);

    }

    public void productsOnThePage(int expectedNumberOfProducts) {

        // Getting a list of products that are on the page
        List<WebElement> elementList = priceValues;
        int actualNumberOfProducts = elementList.size();

        // Asserting that actual number of products on the page equals to the expected number
        Assert.assertEquals(expectedNumberOfProducts, actualNumberOfProducts);

    }

}
