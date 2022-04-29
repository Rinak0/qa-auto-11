package config;

import org.openqa.selenium.Cookie;

import java.util.Calendar;
import java.util.Date;

public class Config {
    public static String correctUsername = "standard_user";
    public static String correctPassword = "secret_sauce";
    public static String incorrectPassword = "incorrectpassword";
    public static String emptyPassword = "";
    public static Cookie authCookie = new Cookie("session-username", "standard_user", "www.saucedemo.com", "/", new Date(2030, Calendar.DECEMBER, 30));

}
