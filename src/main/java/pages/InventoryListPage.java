package pages;

import config.Config;
import extensions.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class InventoryListPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//select[@class='product_sort_container']")
    private WebElement sortList;

    @FindBy(how = How.XPATH, using = "//select/option[@value='lohi']")
    private WebElement lowToHighSortOption;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'inventory_list')]")
    private WebElement inventoryList;

    private List<WebElement> inventoryButtons() {
        return inventoryList.findElements(By.xpath("//button[contains(@class, 'btn_inventory')]"));
    }

    private List<WebElement> inventoryPrices() {
        return inventoryList.findElements(By.xpath("//div[@class='inventory_item_price']"));
    }

    public InventoryListPage() {
        URL = URL + "/inventory.html";
    }

    public void open() {
        Driver.get().navigate().to(URL);
    }

    public InventoryListPage loginByCookies() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        Cookie authCookie = Config.authCookie;
        Driver.get().manage().addCookie(authCookie);
        InventoryListPage inventoryListPage = new InventoryListPage();
        inventoryListPage.open();
        return this;
    }

    public boolean inventoryListIsDisplayed() {
        return inventoryList.isDisplayed();
    }

    public String returnButtonName(int number) {
        return inventoryButtons().get(number).getText();
    }

    public void clickItemButton(int number) {
        inventoryButtons().get(number).click();
    }

    public void openSorterList() {
        sortList.click();
    }

    public void chooseSortOptionLowToHigh() {
        openSorterList();
        lowToHighSortOption.click();
    }

    public int goodsPerPage() {
        return inventoryPrices().size();
    }

    public String getPriceOfExactGood(int goodNumber) {
        return inventoryPrices().get(goodNumber).getText().replaceAll("\\$", "");
    }

    public boolean compareButtonsNames(String buttonName, String targetButtonName) {
        boolean isEquals = false;
        if (targetButtonName.equalsIgnoreCase(buttonName)) {
            isEquals = true;
        }
        return isEquals;
    }

    public boolean goodsIsSortedByPrice(int goodsPerPage, InventoryListPage page) {
        List<String> prices = new ArrayList<>();
        goodsPerPage = page.goodsPerPage();
        for (int i = 0; i < goodsPerPage; i++) {
            prices.add(page.getPriceOfExactGood(i));
        }
        double lowestPrice = 0;
        boolean isSorted = true;
        for (int j = 0; j < goodsPerPage; j++) {
            if (Double.parseDouble(prices.get(j)) < lowestPrice) {
                isSorted = false;
            }
            lowestPrice = Double.parseDouble(prices.get(j));
        }
        return isSorted;
    }

    public InventoryListPage assertInventoryPageIsOpened() {
        Assertions.assertTrue(inventoryListIsDisplayed());
        return this;
    }

    public InventoryListPage assertButtonNamesAreDifferent(String buttonName, String targetButtonName) {
        Assertions.assertFalse(compareButtonsNames(buttonName, targetButtonName));
        return this;
    }

    public InventoryListPage assertButtonNameChanged(String buttonName, String targetButtonName) {
        Assertions.assertTrue(compareButtonsNames(buttonName, targetButtonName));
        return this;
    }

    public InventoryListPage assertIsSortedByPrice(int goodsPerPage, InventoryListPage page) {
        Assertions.assertTrue(goodsIsSortedByPrice(goodsPerPage, page));
        return this;
    }

}
