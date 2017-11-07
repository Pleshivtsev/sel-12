package pages.frontEnd;

import org.openqa.selenium.By;

public class HomePage {
    //------ Locators
    public static By productsLocator = By.cssSelector("li.product");
    public static By productNameLocator = By.cssSelector("div.name");
    public static By stickerLocator = By.cssSelector("div.sticker");

    public static By campaignProductLocator = By.cssSelector("#box-campaigns li.product");
    public static By productRegularPriceLocator = By.cssSelector(".regular-price");
    public static By productCampaignPriceLocator = By.cssSelector(".campaign-price");


    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}