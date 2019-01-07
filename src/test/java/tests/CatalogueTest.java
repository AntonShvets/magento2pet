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

        int productsInCatalogueInitially = 12;
        int productsInFilteredCatalogue = 6;

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.productsInCatalogue(productsInCatalogueInitially);
        catalogue.expandFilter("Style");
        catalogue.filterBy("Full Zip");
        catalogue.productsInCatalogue(productsInFilteredCatalogue);

    }

    /*
    * 10. Sorting works as expected
    * */
    @Test
    public void sorting() {

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.sortBy("Price");
        catalogue.sortingIsCorrect();

    }

    /*
    * 11. Pagination works as expected
    * */
    @Test
    public void pagination() {

        int productsExpectedOnThePage = 3;

        Catalogue catalogue = new Catalogue();
        catalogue.open(Links.hoodiesAndSweatshirts);
        catalogue.goToPage(2);
        catalogue.productsOnThePage(productsExpectedOnThePage);


    }
}
