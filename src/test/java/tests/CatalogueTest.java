package tests;

import data.Links;
import org.testng.annotations.Test;
import pages.Catalogue;
import utilities.ConditionsWebDriverFactory;

/**
 * Test cases that cover Catalogue functionality
 */
public class CatalogueTest extends ConditionsWebDriverFactory {

    /*
    * 9. Filtering works as expected
    * */
    @Test
    public void filtering() {

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.productsInCatalogue(12);
        catalogue.expandFilter("Style");
        catalogue.filterBy("Full Zip");
        catalogue.productsInCatalogue(6);

    }

    /*
    * 10.
    * */
    @Test
    public void sorting() {

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.sortBy("Price");
        catalogue.sortingIsCorrect();


    }
}
