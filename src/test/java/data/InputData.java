package data;

import org.testng.annotations.DataProvider;

/**
 * This is the storage with the different Data providers that are used in the tests
 */

public class InputData {

    public static final String flatRateShippingMethod = "FlatRate";

    @DataProvider (name = "productArcadioGymShort")
    public static Object[][] attributes() {

        return new Object[][] {
                // Size, Color
                {32, "Red"}
        };
    }

    @DataProvider (name = "searchByProductName")
    public static Object[][] searchByProductName() {

        return new Object[][] {
                // Search word, expected number of products found
                {"tee", 26}
        };
    }

    @DataProvider (name = "searchByProductSku")
    public static Object[][] searchByProductSku() {

        return new Object[][] {
                // Search word, expected number of products found
                {"WS04", 1, "Layla Tee"}
        };
    }

    @DataProvider (name = "searchByProductAttribute")
    public static Object[][] searchByProductAttribute() {

        return new Object[][] {
                // Search word, expected number of products found
                {"blue", 9}
        };
    }

}
