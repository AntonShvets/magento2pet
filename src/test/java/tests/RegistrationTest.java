package tests;

import data.InputData;
import data.Users;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConditionsWebDriverFactory;

/**
 * Created by anton on 09/05/18.
 * TestRail project: One4All Gift Cards (Magento 2)
 * Test cases that are responsible for the customer's registration procedure and all activities that can happen before the registration procedure
 */
public class RegistrationTest extends ConditionsWebDriverFactory {

    /*
    1. Register new user successfully
    */
    @Test
    public void customerRegistration() {

        Users user = new Users();

        Registration registration = new Registration();
        registration.registerNewUser(user.getName(), "Selenium", "WebDriver", user.getCorrectPassword());
        registration.registrationSuccessful();

        Logout logout = new Logout();
        logout.successful();
    }

    /*
    2. Register new user after placing a guest order
    */
    @Test
    public void guestOrderCustomerRegistration() {

        ProductPage page = new ProductPage();
        page.open("arcadio-gym-short.html");
        page.selectSize(32);
        page.selectColor("Red");
        page.addToCart();

        Cart cart = new Cart();
        cart.proceedToCheckout();

        Users user = new Users();

        Checkout checkout = new Checkout();
        checkout.fillInGuestShippingDetails(user.getName());
        checkout.chooseShippingMethodProceed("FlatRate");
        checkout.placeOrderAsGuest();
        checkout.registerGuestUser();

    }

    /*
    3. Registration page:
    Fields validations (Negative):
     - all fields are required
     - email already exists
     - password is shorter than 8 symbols
     - wrong confirmation password
     */
    @Test
    public void fieldsValidation() {

        // All fields are required
        Registration requiredFields = new Registration();
        requiredFields.createAccountButton.click();
        requiredFields.firstNameRequiredError.isDisplayed();
        requiredFields.lastNameRequiredError.isDisplayed();
        requiredFields.emailRequiredError.isDisplayed();
        requiredFields.passwordRequiredError.isDisplayed();
        requiredFields.passwordConfirmationRequiredError.isDisplayed();

        // Email already exists
        Users user = new Users();

        Registration emailAlreadyExists = new Registration();
        emailAlreadyExists.registerNewUser(user.permanentUserName, "Selenium", "Webdriver", user.getCorrectPassword());
        emailAlreadyExists.emailAlreadyExistsError.isDisplayed();


    }
}
