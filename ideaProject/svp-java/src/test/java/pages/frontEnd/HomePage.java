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

    public static By registrationLinkLocator = By.linkText("New customers click here");
    public static By logoutLinkLocator = By.linkText("Logout");

    public static By emailInputLocator = By.cssSelector("input[name=email]");
    public static By passwordInputLocator = By.cssSelector("input[name=password]");

    public static By loginButtonLocator = By.cssSelector("button[name=login]");

    public static By noticeSuccessLocator = By.cssSelector("div.notice.success");

    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}