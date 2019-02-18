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
 * Created by anton on 21/05/18.
 */
public class Login extends BasePage {

    public static final String PAGE_URL = MAIN_URL + Links.login;

    public Login() {
        super(true);
    }

    @FindBy(css = "#send2")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(css = "#pass")
    private WebElement passwordField;

    @FindBy(xpath = "//a[contains(text(), 'My Account')]")
    private WebElement myAccountText;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        Log.info("Opening Login page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Customer Login";

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        elementIsDisplayed(signInButton);

        Log.info("Login page is opened");
        return true;
    }

    public void logIn(String email, String password) {
        Log.info("Logging in with username: " + email + ", password: " + password);

        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public void loginSuccessful() {
        myAccountText.isDisplayed();
        Log.info("User logged in successfully");
    }


}
