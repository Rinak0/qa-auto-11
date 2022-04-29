import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Pages;

public class InventoryListPageTests extends WebTests {

    private int goodsPerPage = 6;

    @BeforeEach
    void setupAndSetCookies() {
        new Pages().inventoryListPage.open();
        Pages.inventoryListPage.loginByCookies();

    }

    @Test
    public void changeButtonNameAfterClick() {
        int itemNumber = 0;
        String targetButtonName = "Remove";
        String buttonNameBeforeClick = Pages.inventoryListPage.returnButtonName(itemNumber);
        Pages.inventoryListPage.clickItemButton(itemNumber);
        String buttonNameAfterClick = Pages.inventoryListPage.returnButtonName(itemNumber);
        Pages.inventoryListPage.assertButtonNamesAreDifferent(buttonNameBeforeClick, targetButtonName);
        Pages.inventoryListPage.assertButtonNameChanged(buttonNameAfterClick, targetButtonName);
    }

    @Test
    public void sortByPriceLowToHigh() {
        Pages.inventoryListPage.chooseSortOptionLowToHigh();
        Pages.inventoryListPage.assertIsSortedByPrice(goodsPerPage, Pages.inventoryListPage);
    }

}
