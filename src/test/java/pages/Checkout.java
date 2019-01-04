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
    private WebElement orderConfirmationMessage;

    @FindBy(xpath = "//input[contains(@value,'Create an Account')]")
    private WebElement createAnAccountButton;

    @FindBy(xpath = "//span[contains(text(), 'Check / Money order')]")
    private WebElement checkMoneyOrderButton;

    @FindBy(xpath = "//input[contains(@value,'checkmo')]")
    private WebElement checkMoneyCheckBox;

    @FindAll({
            @FindBy(xpath = "//button[contains(@title,'Place Order')]")
    }) public List<WebElement> placeOrderList;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "password_confirmation")
    private WebElement passwordConfirmationField;

    @FindBy(xpath = "//button[contains(@title,'Create an Account')]")
    public WebElement createAccountButton;

    @FindBy (xpath = "//span[contains(text(),'My Account')]")
    public WebElement myAccountText;

    @Override
    protected void openPage() {
        System.out.println("Opening Checkout page");
        //do nothing
    }

    @Override
    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        System.out.println("Checkout page is opened");
        return nextButton.isDisplayed();
    }

    public void fillInGuestData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = new Date();

        emailField.sendKeys("selenium_test_" + dateFormat.format(date) + "@monsoonconsulting.com");
        firstNameField.sendKeys("Selenium");
        lastNameField.sendKeys("WebDriver");
    }

    public void registerGuestUser() {
        Users user = new Users();

        createAnAccountButton.isDisplayed();
        createAnAccountButton.click();

        passwordField.sendKeys(user.getCorrectPassword());
        passwordConfirmationField.sendKeys(user.getCorrectPassword());
        createAccountButton.click();
        myAccountText.isDisplayed();
    }

    public void fillInShippingDetails() {
        companyField.sendKeys("Monsoon Consulting");
        streetOneField.sendKeys("1 Terminus Mills");
        streetTwoField.sendKeys("Clonskeagh road");
        cityField.sendKeys("Dublin");
        stateProvinceField.sendKeys("Dublin");
        zipCodeField.sendKeys("A12 3BC");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phoneNumberField.sendKeys("08312345678");
    }

    public void fillInGuestShippingDetails(String email) {

        emailField.sendKeys(email);
        firstNameField.sendKeys("test");
        lastNameField.sendKeys("test");
        companyField.sendKeys("test");
        streetOneField.sendKeys("test");
        streetTwoField.sendKeys("test");
        streetThreeField.sendKeys("test");
        cityField.sendKeys("test");
        countryDropDown.selectByVisibleText("Ireland");
        zipCodeField.sendKeys("test");
        phoneNumberField.sendKeys("0838844074");
        stateProvinceField.sendKeys("Dublin");

    }

    public void chooseShippingMethodProceed(String method) {

        if (method == "FlatRate") {
            flatRateCheckbox.click();
        } else nextButton.click();

        nextButton.click();
    }

    public void placeOrder() {

        placeOrderButton.isDisplayed();

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));

        placeOrderButton.click();

        orderConfirmationMessage.isDisplayed();
    }

}
