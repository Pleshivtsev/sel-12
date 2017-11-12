package pages.frontEnd;

import org.openqa.selenium.By;

public class CartPage {
    //------ Locators
    public static By removeItemButtonsLocator = By.cssSelector("button[name=remove_cart_item]");
    public static By orderSummaryTableLocator = By.cssSelector("table.dataTable");

    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}
