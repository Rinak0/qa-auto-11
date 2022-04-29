import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private WebDriver driver;


    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        LoginPage page = new LoginPage(driver);
    }

    @Test
    public void authorizationSuccess() {
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).clear();
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector(".input_error.form_input#password")).clear();
        driver.findElement(By.cssSelector(".input_error.form_input#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".submit-button.btn_action[name='login-button']")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("span.title")).isDisplayed(), "После успешной авторизации пользователь должен перейти на страницу с товарами");
    }

    @Test
    public void authorizationWithoutPassword() {
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).clear();
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector(".input_error.form_input#password")).clear();
        driver.findElement(By.cssSelector(".submit-button.btn_action[name='login-button']")).click();
        String message = driver.findElement(By.cssSelector("div.error-message-container.error")).getText();
        Assertions.assertTrue(message.equals("Epic sadface: Password is required"), "Должно отображаться сообщение о необходимости ввода пароля");
    }

    @Test
    public void incorrectPassword() {
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).clear();
        driver.findElement(By.cssSelector(".input_error.form_input#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector(".input_error.form_input#password")).clear();
        driver.findElement(By.cssSelector(".input_error.form_input#password")).sendKeys("incorrectpassword");
        driver.findElement(By.cssSelector(".submit-button.btn_action[name='login-button']")).click();
        String message = driver.findElement(By.cssSelector("div.error-message-container.error")).getText();
        Assertions.assertTrue(message.equals("Epic sadface: Username and password do not match any user in this service"), "Должно отображаться сообщение о некорректных введенных данных");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

}
