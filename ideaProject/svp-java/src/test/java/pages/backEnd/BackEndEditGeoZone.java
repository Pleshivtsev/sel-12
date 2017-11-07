package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndEditGeoZone {
    //------ Locators
    public static By zonesLocator = By.cssSelector("#table-zones select[name*=zone_code] option[selected=selected]");

    //------ Settings
    public static String header = "Edit Geo Zone";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}