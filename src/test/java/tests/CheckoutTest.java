package tests;

import data.InputData;
import data.Links;
import data.Users;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConditionsWebDriverFactory;

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
        login.loginSuccessfull();

        Cart cart = new Cart();
        cart.checkIfCartIsEmpty();

        ProductPage page = new ProductPage();
        page.open(Links.productArcadioGymShort);
        page.selectSize(size);
        page.selectColor(color);
        page.addToCart();

        Cart cart2 = new Cart();
        cart2.proceedToCheckout();

        Checkout checkout = new Checkout();
        checkout.chooseShippingMethodProceed(InputData.flatRateShippingMethod);
        checkout.placeOrder();

        Logout logout = new Logout();
        logout.successful();
    }

}
