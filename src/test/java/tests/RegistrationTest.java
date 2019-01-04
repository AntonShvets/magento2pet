package tests;

import data.InputData;
import data.Links;
import data.Users;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConditionsWebDriverFactory;

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
        registration.registrationSuccessful();

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
        checkout.fillInGuestShippingDetails(user.getEmail());
        checkout.chooseShippingMethodProceed(InputData.flatRateShippingMethod);
        checkout.placeOrder();
        checkout.registerGuestUser();

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
        page.firstNameRequiredError.isDisplayed();
        page.lastNameRequiredError.isDisplayed();
        page.emailRequiredError.isDisplayed();
        page.passwordRequiredError.isDisplayed();
        page.passwordConfirmationRequiredError.isDisplayed();

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
        page.emailAlreadyExistsError.isDisplayed();

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
        page.passwordIsShortError.isDisplayed();

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
        page.confirmationPasswordIsWrongError.isDisplayed();

    }

    /*
    * 7. Validation: Wrong email format
    * */

    @Test
    public void wrongEmailFormat() {

        Users user = new Users();

        Registration page = new Registration();
        page.registerNewUser(
                user.getEmail()+"@",
                user.defaultFirstName,
                user.defaultLastName,
                user.getCorrectPassword(),
                user.getCorrectPassword()
        );
        page.emailIsWrongError.isDisplayed();

    }
}
