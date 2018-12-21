package tests;

import data.InputData;
import data.Users;
import pages.*;
import org.testng.annotations.Test;
import utilities.ConditionsWebDriverFactory;

/**
 * 1. Check that mandatory fields validations work correctly on the registration page
 * 2. Check validations on the login page (wrong password)
 * 3. Check validations on the Gift card page
 */
public class ValidationsTest extends ConditionsWebDriverFactory {

    // 1. Check that mandatory fields validations work correctly on the registration page
    @Test
    public void registrationFieldsTest() {

        Registration registration = new Registration();
        registration.scrollDown();
        registration.createAccountButton.click();
        registration.emailErrorMessage.isDisplayed();
        registration.firstNameErrorMessage.isDisplayed();
        registration.lastNameErrorMessage.isDisplayed();
        registration.passwordErrorMessage.isDisplayed();
        registration.passwordConfirmationErrorMessage.isDisplayed();
    }

    // 2. Check validations on the login page
    @Test
    public void loginFieldsTest() {
        Users user = new Users();

        Login login = new Login();
        login.logIn(Users.permanentUserName, user.getIncorrectPassword());
        login.loginNotSuccessfull();

    }

    // 3. Check validations on the Gift card page
    @Test (dataProviderClass = InputData.class, dataProvider = "incorrectDigitalGiftCardAmount")
    public void giftCardFieldsTest(String amount, String quantity, String to, String from, String message) {
        Users user = new Users();

        Login login = new Login();
        login.logIn(Users.permanentUserName, user.getCorrectPassword());
        login.loginSuccessfull();

        GiftCardPage page = new GiftCardPage();
        page.open();
        page.addToCart(amount, quantity, to, from, message);
        page.inputErrorIsDisplayed();

        Logout logout = new Logout();
    }







}
