import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InventoryPageTests {

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupDriverAndSetCookies() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        Cookie authCookie = new Cookie("session-username", "standard_user", "www.saucedemo.com", "/", new Date(2030, Calendar.DECEMBER, 30));
        driver.manage().addCookie(authCookie);
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void changeButtonNameAfterClick() {
        String targetButtonName = "Remove";
        String buttonName = driver.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]")).get(0).getText();
        driver.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]")).get(0).click();
        String buttonNameAfterClick = driver.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]")).get(0).getText();
        Assertions.assertTrue(!buttonName.equalsIgnoreCase(targetButtonName), "Название кнопки после нажатия не изменилось");
        Assertions.assertTrue(buttonNameAfterClick.equalsIgnoreCase(targetButtonName), "Название кнопки после нажатия должно измениться на " + targetButtonName);
    }

    @Test
    public void sortByPriceLowToHigh() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//select/option[@value='lohi']")).click();
        List<String> prices = new ArrayList<>();
        int goodsPerPage = driver.findElements(By.xpath("//div[@class='inventory_item_price']")).size();
        for (int i = 0; i < goodsPerPage; i++) {
            prices.add(driver.findElements(By.xpath("//div[@class='inventory_item_price']")).get(i).getText().replaceAll("\\$", ""));
        }
        double lowestPrice = 0;
        boolean isSorted = true;
        for (int j = 0; j < goodsPerPage; j++) {
            if (Double.parseDouble(prices.get(j)) < lowestPrice) {
                isSorted = false;
            }
            lowestPrice = Double.parseDouble(prices.get(j));
        }
        Assertions.assertTrue(isSorted, "Товары не отсортированы в порядке увеличения цены");
    }

    @AfterEach
    public void cleanCookiesAndTeardown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
