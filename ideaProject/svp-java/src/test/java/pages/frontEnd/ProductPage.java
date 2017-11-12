package pages.frontEnd;

import org.openqa.selenium.By;

public class ProductPage {
    //------ Locators
    public static By productContainerLocator = By.cssSelector("#box-product");
    public static By nameLocator = By.cssSelector("h1");
    public static By regularPriceLocator = By.cssSelector(".price-wrapper .regular-price");
    public static By campaignPriceLocator = By.cssSelector(".price-wrapper .campaign-price");

    public static By add2CartButtonLocator = By.cssSelector("button[name=add_cart_product]");
    public static By optionsSelectorLocator = By.cssSelector("select[name*=options]");
    public static By homeLinkLocator = By.linkText("Home");
    public static By cartLocator = By.cssSelector("div#cart");
    public static By quantityLocator = By.cssSelector("span.quantity");

    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}
