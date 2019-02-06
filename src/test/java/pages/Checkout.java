package pages;

import data.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;
import utilities.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by anton on 15/05/18.
 * Checkout page behavior described
 */
public class Checkout extends BasePage {

    public Checkout() {
        super(true);
    }

    @FindBy(xpath = "//button[contains(@data-role,'opc-continue')]")
    private WebElement nextButton;

    @FindBy(id = "customer-email")
    private WebElement emailField;

    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "company")
    private WebElement companyField;

    @FindBy(name = "street[0]")
    private WebElement streetOneField;

    @FindBy(name = "street[1]")
    private WebElement streetTwoField;

    @FindBy(name = "street[2]")
    private WebElement streetThreeField;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "postcode")
    private WebElement zipCodeField;

    @FindBy(name = "region")
    private WebElement stateProvinceField;

    Select countryDropDown = new Select(Drivers.getDriver().findElement(By.name("country_id")));


    @FindBy(name = "telephone")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[contains(@value,'flatrate_flatrate')]")
    private WebElement flatRateCheckbox;

    @FindBy(xpath = "//button[contains(@title,'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//span[contains(text(),'Thank you for your purchase!')]")
    public WebElement orderConfirmationMessage;

    @FindBy(xpath = "//input[contains(@value,'Create an Account')]")
    private WebElement createAnAccountButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "password_confirmation")
    private WebElement passwordConfirmationField;

    @FindBy(xpath = "//button[contains(@title,'Create an Account')]")
    private WebElement createAccountButton;

    @FindBy (xpath = "//span[contains(text(),'My Account')]")
    public WebElement myAccountText;

    @Override
    protected void openPage() {
        Log.info("Opening Checkout page");
    }

    @Override
    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        Log.info("Checkout page is opened");
        return nextButton.isDisplayed();
    }

    public void registerGuestUser() {
        Log.info("Registering new user");
        Users user = new Users();

        createAnAccountButton.isDisplayed();
        createAnAccountButton.click();

        passwordField.sendKeys(user.getCorrectPassword());
        passwordConfirmationField.sendKeys(user.getCorrectPassword());
        createAccountButton.click();
    }

    public void fillInGuestShippingDetails() {
        Users user = new Users();

        Log.info("Filling in guest shipping details");
        emailField.sendKeys(user.getEmail());
        firstNameField.sendKeys(Users.defaultFirstName);
        lastNameField.sendKeys(Users.defaultLastName);
        companyField.sendKeys(Users.company);
        streetOneField.sendKeys(Users.streetOne);
        streetTwoField.sendKeys(Users.streetTwo);
        streetThreeField.sendKeys(Users.streetThree);
        cityField.sendKeys(Users.city);
        countryDropDown.selectByVisibleText(Users.country);
        zipCodeField.sendKeys(Users.zip);
        phoneNumberField.sendKeys(Users.telephone);
        stateProvinceField.sendKeys(Users.province);
    }

    public void chooseShippingMethodProceed(String method) {
        Log.info("Choosing " + method + " shipping method");
        if (method.equals("FlatRate")) {
            flatRateCheckbox.click();
        } else nextButton.click();

        nextButton.click();
    }

    public void placeOrder() {
        Log.info("Placing the order");
        placeOrderButton.isDisplayed();

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));

        placeOrderButton.click();
    }

}
