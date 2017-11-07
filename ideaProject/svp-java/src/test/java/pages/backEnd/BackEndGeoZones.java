package pages.backEnd;

import appSettings.AppSettings;
import org.openqa.selenium.By;

public class BackEndGeoZones {
    //------ Locators
    public static By urlsLocator = By.xpath("//tr[@class='row']/td[3]/a");

    //------ Settings
    public static String url = AppSettings.backEndUrl + "/?app=geo_zones&doc=geo_zones";
    public static String header = "Geo Zones";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}