package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndMainPage {

//------ Locators
    public static By platformLabelLocator = By.id("platform");
    public static By sideMenuItemsLocator = By.cssSelector("ul#box-apps-menu li#app-");
    public static By subItemsLocator = By.cssSelector("li.selected ul.docs li");

//------ Settings
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}