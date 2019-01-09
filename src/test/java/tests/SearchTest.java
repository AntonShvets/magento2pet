package tests;

import data.InputData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Main;
import pages.Search;
import utilities.ConditionsWebDriverFactory;

public class SearchTest extends ConditionsWebDriverFactory {

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductName")
    public void searchByName(String searchText, int numberOfProducts) {

        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);
        search.expectedNumberOfProducts(numberOfProducts);

    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductSku")
    public void searchBySku(String searchText, int numberOfProducts, String productName) {

        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);
        search.expectedNumberOfProducts(numberOfProducts);
        search.productNameIsDisplayed(productName);

    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByProductAttribute")
    public void searchByAttribute(String searchText, int numberOfProducts) {

        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);
        search.expectedNumberOfProducts(numberOfProducts);
        search.productsWithAttributeDisplayed(searchText, numberOfProducts);

    }

    @Test (dataProviderClass = InputData.class, dataProvider = "searchByWrongValue")
    public void searchWrongValue(String searchText) {

        Main main = new Main();

        Search search = new Search();
        search.searchBy(searchText);

        // Asserting that search returned no results
        Assert.assertTrue(search.noResultsText.isDisplayed());

    }

}
