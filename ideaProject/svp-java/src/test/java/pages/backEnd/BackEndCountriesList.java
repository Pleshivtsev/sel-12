package pages.backEnd;

import appSettings.AppSettings;
import org.openqa.selenium.By;

public class BackEndCountriesList {

//------ Locators
    public static By urlsLocator = By.xpath("//tr[@class='row']/td[5]/a");
    public static By zonesLocator = By.xpath("//tr[@class='row']/td[6]");

//------ Settings
    public static String url = AppSettings.backEndUrl + "/?app=countries&doc=countries";
    public static String header = "Countries";

    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}