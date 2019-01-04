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

}
