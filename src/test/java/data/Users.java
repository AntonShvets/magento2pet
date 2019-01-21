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
    public static final String defaultFirstName = "Anton";
    public static final String defaultLastName = "Shvets";
    public static final String company = "Private Company";
    public static final String streetOne = "House 123";
    public static final String streetTwo = "Clonskeagh road";
    public static final String streetThree = "Dublin 14";
    public static final String city = "Dublin";
    public static final String country = "Ireland";
    public static final String zip = "D14 A1B2";
    public static final String telephone = "0831234567";
    public static final String province = "Dublin";

    public void setCustomerEmail() {
        customerEmail = "anton.shvets.88+"+ dateFormat.format(date) + "@gmail.com";
    }

    public String getEmail() {
        setCustomerEmail();
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

    public String getShortPassword() {
        password = "Pass@12";
        return password;
    }

}
