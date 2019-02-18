package tests;

import data.InputData;
import data.Links;
import data.Users;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.Login;
import pages.Logout;
import pages.ProductPage;
import pages.Wishlist;
import utilities.ConditionsWebDriverFactory;
import utilities.Log;

public class WishListTest extends ConditionsWebDriverFactory {

    @Test (dataProviderClass = InputData.class, dataProvider = "productArcadioGymShort")
    public void wishListAddToCart(int size, String color) {
        Users user = new Users();

        Login login = new Login();
        login.logIn(Users.permanentUserName, user.getCorrectPassword());
        login.loginSuccessful();

        Wishlist wishList = new Wishlist();
        wishList.clearWishList();

        ProductPage page = new ProductPage();
        page.open(Links.productArcadioGymShort);
        page.addToWishList();

        wishList.openPage();
        wishList.productIsDisplayed(Links.productArcadioGymShort);
        wishList.addProductToCart(Links.productArcadioGymShort);

        page.selectSize(size);
        page.selectColor(color);
        page.addToCart();

        wishList.openPage();

        Assert.assertTrue(wishList.wishListIsEmpty());
        Log.info("WishList is empty");

    }

    @AfterMethod
    public void logout() {
        Logout logout = new Logout();
        logout.successful();
    }

}
