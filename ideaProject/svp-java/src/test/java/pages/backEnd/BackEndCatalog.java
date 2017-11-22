package pages.backEnd;

import appSettings.AppSettings;
import org.openqa.selenium.By;

public class BackEndCatalog {
//------ Locators
    public static By newProductButton = By.linkText("Add New Product");
    public static By productsLinksLocator = By.xpath("//table[@class='dataTable']/tbody/tr/td[3]/a");

//------ Settings
    public static String header = "Catalog";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

    public static String directUrl = AppSettings.backEndUrl + "?app=catalog&doc=catalog&category_id=1";
}