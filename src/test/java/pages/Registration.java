package pages;

import data.Links;
import data.Users;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

/**
 * Created by anton on 09/05/18.
 * The class that describes Registration page behavior
 */
public class Registration extends BasePage {

    private static final String PAGE_URL = Links.main + "customer/account/create/";

    public Registration() {
        super(true);
    }

    @FindBy(css = ".action.submit.primary")
    public WebElement createAccountButton;

    @FindBy(css = "#firstname")
    private WebElement firstNameField;

    @FindBy(css = "#lastname")
    private WebElement lastNameField;

    @FindBy(css = "#is_subscribed")
    private WebElement newsletterCheckbox;

    @FindBy(css = "#dob")
    private WebElement dateOfBirthField;

    @FindBy(css = "#gender")
    private WebElement genderField;

    @FindBy(css = "#email_address")
    private WebElement emailField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(css = ".message-success.success.message>div")
    private WebElement successMessage;

    @FindBy(id = "firstname-error")
    public WebElement firstNameErrorMessage;

    @FindBy(id = "lastname-error")
    public WebElement lastNameErrorMessage;

    @FindBy(id = "email_address-error")
    public WebElement emailErrorMessage;

    @FindBy(id = "password-error")
    public WebElement passwordErrorMessage;

    @FindBy(id = "password-confirmation-error")
    public WebElement passwordConfirmationErrorMessage;


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

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        System.out.println("Opening Registration page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Create New Customer Account";

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(createAccountButton));
        elementIsPresented(createAccountButton);

        System.out.println("Registration page is opened");
        return true;
    }




    public void registerNewUser(String email, String firstName, String lastName, String password) {

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        createAccountButton.click();

    }

    public void registrationSuccessful() {

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.visibilityOf(successMessage));

    }
}
