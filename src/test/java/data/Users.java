package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anton on 21/05/18.
 */
public class Users {

    private String customerEmail;

    private String password;

    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
    Date date = new Date();

    public static final String permanentUserName = "anton.shvets.88+selenium.webdriver@gmail.com";

    public void setCustomerName() {
        customerEmail = "anton.shvets.88+"+ dateFormat.format(date) + "@gmail.com";
    }

    public String getName() {
        setCustomerName();
        return customerEmail;
    }

    public String getCorrectPassword() {

        password = "Password@123";
        return password;
    }

    public String getIncorrectPassword() {

        password = "Password@12";
        return password;
    }

}
