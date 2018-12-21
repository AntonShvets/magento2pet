package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.webtestsbase.BasePage;

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

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "region")
    private WebElement stateProvinceField;

    @FindBy(name = "postcode")
    private WebElement zipCodeField;

    @FindBy(name = "telephone")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[contains(@name,'ko_unique')]")
    private WebElement standardPostCheckbox;

//    @FindBy (xpath = "//input[contains(@name,'ko_unique_2')]")
//    private WebElement courierPostCheckbox;

    @FindBy(xpath = "//button[contains(@title,'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//span[contains(text(),'Thank you for your purchase!')]")
    private WebElement orderConfirmationMessage;

    @FindBy(xpath = "//input[contains(@value,'Create an Account')]")
    private WebElement createAnAccountButton;

    @FindBy(xpath = "//p[contains(text(), 'A letter with further instructions will be sent to your email.')]")
    private WebElement registrationMailMessage;

    @FindBy(xpath = "//span[contains(text(), 'Check / Money order')]")
    private WebElement checkMoneyOrderButton;

    @FindBy(xpath = "//input[contains(@value,'checkmo')]")
    private WebElement checkMoneyCheckBox;

    @FindAll({
            @FindBy(xpath = "//button[contains(@title,'Place Order')]")
    }) public List<WebElement> placeOrderList;

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

    public void registerGuestButtonCheck() {
        createAnAccountButton.isDisplayed();
        createAnAccountButton.click();
        registrationMailMessage.isDisplayed();
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

    public void chooseShippingMethodProceed() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        standardPostCheckbox.click();
        nextButton.click();
    }

    public void placeOrderAsGuest() {
        placeOrderButton.isDisplayed();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement element : placeOrderList) {
            if (element.isDisplayed()) {
                element.click();
            }
        }

        orderConfirmationMessage.isDisplayed();
    }

    public void placeOrder(String paymentMethod) {

        if (paymentMethod == "CheckMoney") {

            WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
            wait.until(ExpectedConditions.elementToBeClickable(checkMoneyCheckBox));
            System.out.println("Placing the order using " + paymentMethod + " payment method");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkMoneyCheckBox.click();
            checkMoneyOrderButton.click();
        }

        placeOrderButton.isDisplayed();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement element : placeOrderList) {
            if (element.isDisplayed()) {
                element.click();
            }
        }

        orderConfirmationMessage.isDisplayed();
    }




}
