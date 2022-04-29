import extensions.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

public class WebTests {

    @AfterAll
    public static void teardown() {
        Driver.quit();
    }

    @AfterEach
    public void cleanCookies() {
        Driver.get().manage().deleteAllCookies();

    }
}
