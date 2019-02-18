package tests;

import data.InputData;
import data.Links;
import data.Users;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConditionsWebDriverFactory;
import utilities.Log;

/**
 * Test cases that cover Checkout process
 */
public class CheckoutTest extends ConditionsWebDriverFactory {

    /*
     * 8. Place order (Logged in user)
     */
    @Test (dataProviderClass = InputData.class, dataProvider = "productArcadioGymShort")
    public void placeOrderAsLoggedIn(int size, String color) {
        Users user = new Users();

        Login login = new Login();
        login.logIn(Users.permanentUserName, user.getCorrectPassword());
        login.loginSuccessful();

        Cart cart = new Cart();
        cart.checkIfCartIsEmpty();

        ProductPage page = new ProductPage();
        page.open(Links.productArcadioGymShort);
        page.selectSize(size);
        page.selectColor(color);
        page.addToCart();

        Cart cartCheckout = new Cart();
        cartCheckout.proceedToCheckout();

        Checkout checkout = new Checkout();
        checkout.chooseShippingMethodProceed(InputData.flatRateShippingMethod);
        checkout.placeOrder();

        Assert.assertTrue(checkout.orderConfirmationMessage.isDisplayed());
        Log.info("Checking that order confirmation message is displayed");
    }

    @AfterMethod
    public void logout() {
        Logout logout = new Logout();
        logout.successful();
    }

}
