package tests;

import data.InputData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Main;
import pages.Search;
import utilities.ConditionsWebDriverFactory;
import utilities.Log;

public class SearchTest extends ConditionsWebDriverFactory {

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductName")
    public void searchByName(String searchText, int numberOfProducts) {
        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);

        Assert.assertTrue(search.expectedNumberOfProducts(numberOfProducts));
        Log.info("Checking that " + numberOfProducts + " products displayed in search results");
    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductSku")
    public void searchBySku(String searchText, int numberOfProducts, String productName) {
        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);
        search.expectedNumberOfProducts(numberOfProducts);

        Assert.assertTrue(search.productNameIsDisplayed(productName));
        Log.info("Checking that " + productName + " product name is displayed in search results");
    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductAttribute")
    public void searchByAttribute(String searchText, int numberOfProducts) {
        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);
        search.expectedNumberOfProducts(numberOfProducts);

        Assert.assertEquals(search.productsWithAttributeDisplayed(searchText), numberOfProducts);
        Log.info("Checking that " + numberOfProducts + " products displayed with attribute: " + searchText);
    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByWrongValue")
    public void searchWrongValue(String searchText) {
        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);

        Assert.assertTrue(search.noResultsText.isDisplayed());
        Log.info("Asserting that search returned no results");
    }
}
