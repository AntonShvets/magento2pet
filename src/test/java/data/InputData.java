package data;

import org.testng.annotations.DataProvider;

/**
 * This is the storage with the different Data providers that are used in the tests
 */

public class InputData {

    @DataProvider (name = "correctGiftCardData")
    public static Object[][] correctGiftCardData() {

        return new Object[][] {
                {"15", "2", "WebDriver", "Selenium", "Test Selenium WebDriver message"}
        };
    }

    @DataProvider (name = "incorrectGiftCardAmount")
    public static Object[][] incorrectGiftCardAmount() {

        return new Object[][] {
                {"14", "", "", "", ""},
                {"201", "", "", "", ""}
        };
    }

    @DataProvider (name = "incorrectDigitalGiftCardAmount")
    public static Object[][] incorrectDigitalGiftCardAmount() {

        return new Object[][] {
                {"14", "", "", "", ""},
                {"501", "", "", "", ""}
        };
    }

}
