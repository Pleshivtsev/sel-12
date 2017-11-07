package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndEditCountry {
    //------ Locators
    public static By zonesLocator = By.cssSelector("#table-zones input[type=hidden][name *=name]");

    //------ Settings
    public static String header = "Edit Country";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}