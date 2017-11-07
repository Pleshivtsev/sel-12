package pages.frontEnd;

import org.openqa.selenium.By;

public class ProductPage {
    //------ Locators
    public static By productContainerLocator = By.cssSelector("#box-product");
    public static By nameLocator = By.cssSelector("h1");
    public static By regularPriceLocator = By.cssSelector(".price-wrapper .regular-price");
    public static By campaignPriceLocator = By.cssSelector(".price-wrapper .campaign-price");


    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}
