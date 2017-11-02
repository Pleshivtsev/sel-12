package pages.frontEnd;

import org.openqa.selenium.By;

public class HomePage {
    //------ Locators
    public static By productsLocator = By.cssSelector("li.product");
    public static By productNameLocator = By.cssSelector("div.name");
    public static By stickerLocator = By.cssSelector("div.sticker");


    //------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}