package pages;

import extensions.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    @FindBy(how = How.CSS, using = ".input_error.form_input#user-name")
    private WebElement usernameInputField;

    @FindBy(how = How.CSS, using = ".input_error.form_input#password")
    private WebElement passwordInputField;

    @FindBy(how = How.CSS, using = ".submit-button.btn_action[name='login-button']")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "div.error-message-container.error")
    private WebElement errorMessage;

    public LoginPage() {
        URL = URL + "/";
    }

    public LoginPage open() {
        Driver.get().get(URL);
        return this;
    }

    public InventoryListPage loginUserSuccess(String login, String password) {
        return clearUsernameInputField()
                .fillUsernameInputField(login)
                .clearPasswordInputField()
                .fillPasswordInputField(password)
                .clickLoginButton();
    }

    public LoginPage loginUserFailed(String login, String password) {
        clearUsernameInputField()
                .fillUsernameInputField(login)
                .clearPasswordInputField()
                .fillPasswordInputField(password)
                .clickLoginButton();
        return this;
    }

    public LoginPage clearUsernameInputField() {
        usernameInputField.clear();
        return this;
    }

    public LoginPage fillUsernameInputField(String username) {
        usernameInputField.sendKeys(username);
        return this;
    }

    public LoginPage clearPasswordInputField() {
        passwordInputField.clear();
        return this;
    }

    public LoginPage fillPasswordInputField(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public InventoryListPage clickLoginButton() {
        loginButton.click();
        return new InventoryListPage();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public LoginPage assertErrorMessage(String message) {
        Assertions.assertTrue(message.equalsIgnoreCase(getErrorMessage()));
        return this;
    }

}
