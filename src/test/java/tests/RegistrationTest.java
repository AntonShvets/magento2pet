package tests;

import data.InputData;
import data.Links;
import data.Users;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConditionsWebDriverFactory;
import utilities.Log;

/**
 * Test cases that are responsible for the customer's registration procedure and all activities that can happen before the registration procedure
 */
public class RegistrationTest extends ConditionsWebDriverFactory {

    /*
    * 1. Register new user successfully
    */
    @Test
    public void customerRegistration() {
        Users user = new Users();

        Registration registration = new Registration();
        registration.registerNewUser(
                user.getEmail(),
                user.defaultFirstName,
                user.defaultLastName,
                user.getCorrectPassword(),
                user.getCorrectPassword());

        Assert.assertTrue(registration.registrationSuccessful());
        Log.info("Checking that registration was successful");

        Logout logout = new Logout();
        logout.successful();
    }

    /*
    * 2. Register new user after placing a guest order
    */
    @Test (dataProviderClass = InputData.class, dataProvider = "productArcadioGymShort")
    public void guestOrderCustomerRegistration(int size, String color) {
        ProductPage page = new ProductPage();
        page.open(Links.productArcadioGymShort);
        page.selectSize(size);
        page.selectColor(color);
        page.addToCart();

        Cart cart = new Cart();
        cart.proceedToCheckout();

        Users user = new Users();

        Checkout checkout = new Checkout();
        checkout.fillInGuestShippingDetails();
        checkout.chooseShippingMethodProceed(InputData.flatRateShippingMethod);
        checkout.placeOrder();
        checkout.registerGuestUser();

        Assert.assertTrue(checkout.myAccountText.isDisplayed());
        Log.info("Checking that newly created customer has logged in successfully");

        Logout logout = new Logout();
        logout.successful();
    }

    /*
    * 3. Validation: All fields are required
     */
    @Test
    public void allFieldsAreRequired() {
        Registration page = new Registration();
        page.createAccountButton.click();

        Assert.assertTrue(page.firstNameRequiredError.isDisplayed());
        Assert.assertTrue(page.lastNameRequiredError.isDisplayed());
        Assert.assertTrue(page.emailRequiredError.isDisplayed());
        Assert.assertTrue(page.passwordRequiredError.isDisplayed());
        Assert.assertTrue(page.passwordConfirmationRequiredError.isDisplayed());
        Log.info("Checking that appropriate error messages are displayed on the page");
    }

    /*
    * 4. Validation: Email already exists
    * */
    @Test
    public void emailAlreadyExists() {
        Users user = new Users();

        Registration page = new Registration();
        page.registerNewUser(
                user.permanentUserName,
                user.defaultFirstName,
                user.defaultLastName,
                user.getCorrectPassword(),
                user.getCorrectPassword()
        );

        Assert.assertTrue(page.emailAlreadyExistsError.isDisplayed());
        Log.info("Checking that appropriate error message is displayed on the page");
    }

    /*
     * 5. Validation: Password is shorter than 8 symbols
     * */
    @Test
    public void shortPassword() {
        Users user = new Users();

        Registration page = new Registration();
        page.registerNewUser(
                user.getEmail(),
                user.defaultFirstName,
                user.defaultLastName,
                user.getShortPassword(),
                user.getShortPassword()
        );

        Assert.assertTrue(page.passwordIsShortError.isDisplayed());
        Log.info("Checking that appropriate error message is displayed on the page");
    }

    /*
     * 6. Validation: Wrong confirmation password
     * */
    @Test
    public void wrongConfirmationPassword() {
        Users user = new Users();

        Registration page = new Registration();
        page.registerNewUser(
                user.getEmail(),
                user.defaultFirstName,
                user.defaultLastName,
                user.getCorrectPassword(),
                user.getIncorrectPassword()
        );

        Assert.assertTrue(page.confirmationPasswordIsWrongError.isDisplayed());
        Log.info("Checking that appropriate error message is displayed on the page");
    }

    /*
    * 7. Validation: Wrong email format
    * */

    @Test
    public void wrongEmailFormat() {
        String wrongValue = "@";

        Users user = new Users();

        Registration page = new Registration();
        page.registerNewUser(
                user.getEmail() + wrongValue,
                user.defaultFirstName,
                user.defaultLastName,
                user.getCorrectPassword(),
                user.getCorrectPassword()
        );

        Assert.assertTrue(page.emailIsWrongError.isDisplayed());
        Log.info("Checking that appropriate error message is displayed on the page");
    }
}
