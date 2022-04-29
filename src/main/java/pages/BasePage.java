package pages;

import extensions.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected String URL = "https://www.saucedemo.com";

    BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

}
