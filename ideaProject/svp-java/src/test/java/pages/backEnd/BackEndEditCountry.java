package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndEditCountry {
    //------ Locators
    public static By zonesLocator = By.cssSelector("#table-zones input[type=hidden][name *=name]");

    //Такой локатор сделан для того, чтобы вытащить ссылку на внешнюю страницу
    public static By externalLinksLocator = By.xpath("//i[contains(@class, 'fa-external-link')]//..");

    //------ Settings
    public static String header = "Edit Country";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}