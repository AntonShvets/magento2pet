package tests;

import data.InputData;
import data.Users;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Cart;
import pages.Checkout;
import pages.GiftCardPage;
import pages.Login;
import org.testng.annotations.Test;
import test.webtestsbase.Drivers;
import utilities.ConditionsWebDriverFactory;

import java.security.Key;

/**
 * Created by anton on 21/05/18.
 * TestRail project: One4All Gift Cards (Magento 2)
 * Test cases that are responsible for different Checkout procedures
 */
public class CheckoutTest extends ConditionsWebDriverFactory {

    @Test (priority = 1, dataProviderClass = InputData.class, dataProvider = "correctGiftCardData")
    public void checkMoneyOrder(String amount, String quantity, String to, String from, String message) {
        Users user = new Users();

        Login login = new Login();
        login.logIn(Users.permanentUserName, user.getCorrectPassword());
        login.loginSuccessfull();

        GiftCardPage page = new GiftCardPage();
        page.open();
        page.addToCart(amount, quantity, to, from, message);
        page.cardAddedSuccessfully();

        Cart cart = new Cart();
        cart.proceedToCheckout();

        Checkout checkout = new Checkout();
        checkout.placeOrder("CheckMoney");
    }

}
