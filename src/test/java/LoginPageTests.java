import config.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Pages;

public class LoginPageTests extends WebTests {

    @BeforeEach
    void setup() {
        new Pages().loginPage.open();
    }

    @Test
    public void authorizationSuccess() {
        Pages.loginPage.loginUserSuccess(Config.correctUsername, Config.correctPassword)
                .assertInventoryPageIsOpened();
    }

    @Test
    public void authorizationWithoutPassword() {
        Pages.loginPage.loginUserFailed(Config.correctUsername, Config.emptyPassword)
                .assertErrorMessage("Epic sadface: Password is required");
    }

    @Test
    public void incorrectPassword() {
        Pages.loginPage.loginUserFailed(Config.correctUsername, Config.incorrectPassword)
                .assertErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

}
