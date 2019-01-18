package tests;

import data.Links;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Catalogue;
import utilities.ConditionsWebDriverFactory;
import utilities.Log;

/**
 * Test cases that cover Catalogue functionality
 */
public class CatalogueTest extends ConditionsWebDriverFactory {

    /*
    * 9. Filtering works as expected
    * */
    @Test
    public void filtering() {

        String filterCategory = "Style";
        String filterAttribute = "Full Zip";

        int productsInCatalogueInitially = 12;
        int productsInFilteredCatalogue = 6;

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.productsInCatalogue(productsInCatalogueInitially);
        catalogue.expandFilter(filterCategory);
        catalogue.filterBy(filterAttribute);

        Assert.assertTrue(catalogue.productsInCatalogue(productsInFilteredCatalogue));
        Log.info("Checking that after filtering page contains " + productsInFilteredCatalogue + " products");

    }

    /*
    * 10. Sorting works as expected
    * */
    @Test
    public void sorting() {

        String sortingAttribute = "Price";

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.sortBy(sortingAttribute);

        Assert.assertTrue(catalogue.sortingIsCorrect());
        Log.info("Checking that new sorted array is equal to the original one");

    }

    /*
    * 11. Pagination works as expected
    * */
    @Test
    public void pagination() {

        int productsExpectedOnThePage = 3;
        int pageNumber = 2;

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.goToPage(pageNumber);

        Assert.assertEquals(catalogue.productsOnThePage(), productsExpectedOnThePage);
        Log.info("Checking that page " + pageNumber + " contains " + productsExpectedOnThePage + " products");

    }
}
