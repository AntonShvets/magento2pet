package tests;

import data.InputData;
import org.testng.annotations.Test;
import pages.*;
import test.webtestsbase.Drivers;
import utilities.ConditionsWebDriverFactory;
import static data.Links.*;

/**
 * Created by anton on 09/05/18.
 * TestRail project: One4All Gift Cards (Magento 2)
 * Test cases that are responsible for the customer's registration procedure and all activities that can happen before the registration procedure
 */
public class RegistrationTest extends ConditionsWebDriverFactory {

    /*
    Test case #1357
    https://monsoonconsulting.testrail.io/index.php?/cases/view/1357
    */
    @Test
    public void customerRegistration() {

        Registration registration = new Registration();
        registration.registerNewUser();

        Logout logout = new Logout();
    }

    /*
    Test case #1359
    https://monsoonconsulting.testrail.io/index.php?/cases/view/1359
     */
    @Test (dataProviderClass = InputData.class, dataProvider = "correctGiftCardData")
    public void guestOrderRegistration(String amount, String quantity, String to, String from, String message) {
        GiftCardPage page = new GiftCardPage();
        page.open();
        page.addToCart(amount, quantity, to, from, message);
        page.cardAddedSuccessfully();

        Cart cart = new Cart();
        cart.proceedToCheckout();

        Checkout checkout = new Checkout();
        checkout.fillInGuestData();
        checkout.fillInShippingDetails();
        checkout.chooseShippingMethodProceed();
        checkout.placeOrderAsGuest();
        checkout.registerGuestButtonCheck();
    }
}
