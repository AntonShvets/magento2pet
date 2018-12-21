package pages;

import data.Links;
import data.Users;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anton on 09/05/18.
 * The class that describes Registration page behavior
 */
public class Registration extends BasePage {

    private static final String PAGE_URL = Links.main + "customer/account/create/";

    public Registration() {
        super(true);
    }

    @FindBy (css = ".action.submit.primary")
    public WebElement createAccountButton;

    @FindBy (css = "#firstname")
    private WebElement firstNameField;

    @FindBy (css = "#lastname")
    private WebElement lastNameField;

    @FindBy (css = "#is_subscribed")
    private WebElement newsletterCheckbox;

    @FindBy (css = "#dob")
    private WebElement dateOfBirthField;

    @FindBy (css = "#gender")
    private WebElement genderField;

    @FindBy (css = "#email_address")
    private WebElement emailField;

    @FindBy (css = "#password")
    private WebElement passwordField;

    @FindBy (css = "#password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy (css = ".message-success.success.message>div")
    private WebElement successMessage;

    @FindBy (id = "firstname-error")
    public WebElement firstNameErrorMessage;

    @FindBy (id = "lastname-error")
    public WebElement lastNameErrorMessage;

    @FindBy (id = "email_address-error")
    public WebElement emailErrorMessage;

    @FindBy (id = "password-error")
    public WebElement passwordErrorMessage;

    @FindBy (id = "password-confirmation-error")
    public WebElement passwordConfirmationErrorMessage;

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




    public void registerNewUser() {
        Users user = new Users();

        System.out.println("name: " + user.getName() + ", pass: " + user.getCorrectPassword());

        firstNameField.sendKeys("Selenium");
        lastNameField.sendKeys("WebDriver");
        emailField.sendKeys(user.getName());
        passwordField.sendKeys(user.getCorrectPassword());
        confirmPasswordField.sendKeys("monsoon@123");
        createAccountButton.click();

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        System.out.println("Customer " + user.getName() + " registered successfully");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
