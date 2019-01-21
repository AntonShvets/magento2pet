package pages;

import data.Links;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

/**
 * Created by anton on 09/05/18.
 * The class that describes Registration page behavior
 */
public class Registration extends BasePage {

    private static final String PAGE_URL = Links.main + Links.registration;

    public Registration() {
        super(true);
    }

    @FindBy(css = ".action.submit.primary")
    public WebElement createAccountButton;

    @FindBy(css = "#firstname")
    private WebElement firstNameField;

    @FindBy(css = "#lastname")
    private WebElement lastNameField;

    @FindBy(css = "#email_address")
    private WebElement emailField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#password-confirmation")
    public WebElement confirmPasswordField;

    @FindBy(css = ".message-success.success.message>div")
    private WebElement successMessage;

    /*
    * Error messages
    * */
    @FindBy (xpath = "//div[@for='firstname' and contains(text(), 'This is a required field')]")
    public WebElement firstNameRequiredError;

    @FindBy (xpath = "//div[@for='lastname' and contains(text(), 'This is a required field')]")
    public WebElement lastNameRequiredError;

    @FindBy (xpath = "//div[@for='email_address' and contains(text(), 'This is a required field')]")
    public WebElement emailRequiredError;

    @FindBy (xpath = "//div[@for='password' and contains(text(), 'This is a required field')]")
    public WebElement passwordRequiredError;

    @FindBy (xpath = "//div[@for='password-confirmation' and contains(text(), 'This is a required field')]")
    public WebElement passwordConfirmationRequiredError;

    @FindBy (xpath = "//div[contains(text(),'There is already an account with this email address.')]")
    public WebElement emailAlreadyExistsError;

    @FindBy (xpath = "//div[contains(text(),'Minimum length of this field must be equal or greater than 8 symbols.')]")
    public WebElement passwordIsShortError;

    @FindBy (xpath = "//div[contains(text(),'Please enter the same value again.')]")
    public WebElement confirmationPasswordIsWrongError;

    @FindBy (xpath = "//div[contains(text(),'Please enter a valid email address (Ex: johndoe@domain.com).')]")
    public WebElement emailIsWrongError;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        Log.info("Opening Registration page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Create New Customer Account";

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(createAccountButton));
        elementIsDisplayed(createAccountButton);

        Log.info("Registration page is opened");
        return true;
    }

    public void registerNewUser(String email, String firstName, String lastName, String password, String confirmationPassword) {
        Log.info("Registering new user using the following data: " + "email: " + email + ", first name: " + firstName + ", last name: " + lastName + ", password: " + password);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmationPassword);
        createAccountButton.click();
    }

    public boolean registrationSuccessful() {
        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.visibilityOf(successMessage));

        Log.info("Registration was successful");
        return successMessage.isDisplayed();
    }
}
