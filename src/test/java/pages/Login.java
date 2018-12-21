package pages;

import data.Links;
import data.Users;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.image.ImageWatched;
import test.webtestsbase.BasePage;
import test.webtestsbase.Drivers;

/**
 * Created by anton on 21/05/18.
 */
public class Login extends BasePage {

    public static final String PAGE_URL = Links.main + "customer/account/login/";

    public Login() {
        super(true);
    }

    @FindBy (css = "#send2")
    private WebElement signInButton;

    @FindBy (id = "email")
    private WebElement emailField;

    @FindBy (css = "#pass")
    public WebElement passwordField;

    @FindBy (xpath = "//a[contains(text(), 'My giftcards')]")
    private WebElement myGiftCardsLink;

    @FindBy (xpath = "//div[contains(@data-ui-id, 'message-error')]")
    private WebElement loginErrorMessage;

    @Override
    public void openPage() {
        getDriver().get(PAGE_URL);
        System.out.println("Opening Login page");
    }

    @Override
    public boolean isPageOpened() {
        String expectedTitle = "Customer Login";

        WebDriverWait wait = new WebDriverWait(Drivers.getDriver(), defaultTimeout);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        wait.until(ExpectedConditions.visibilityOf(signInButton));
        elementIsPresented(signInButton);

        System.out.println("Login page is opened");
        return true;
    }

    public void logIn(String email, String password) {
//        Users user = new Users();

        System.out.println("User name is: " + email);

        emailField.sendKeys(email); // Users.permanentUserName
        passwordField.sendKeys(password); // user.getPassword()
        signInButton.click();

    }

    public void loginSuccessfull() {
        myGiftCardsLink.isDisplayed();
        System.out.println("User logged in successfully");
    }

    public void loginNotSuccessfull() {
        loginErrorMessage.isDisplayed();
        System.out.println("Login failed as expected");
    }


}
