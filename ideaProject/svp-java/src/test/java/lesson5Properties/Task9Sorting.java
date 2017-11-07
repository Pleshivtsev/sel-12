package lesson5Properties;

import appSettings.AppSettings;
import data.Country;
import data.GeoZone;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.backEnd.*;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task9Sorting extends TestBase3 {

    private static WebDriver driver;
    private static List<Country> countries;
    private static List<GeoZone> zones;

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods
    private static void backEndLogin(){
        driver.get(AppSettings.backEndUrl);
        driver.findElement(BackEndLoginPage.userNameInputLocator).sendKeys(AppSettings.adminLogin);
        driver.findElement(BackEndLoginPage.userPasswordInputLocator).sendKeys(AppSettings.adminPassword);
        driver.findElement(BackEndLoginPage.loginButtonLocator).click();
        waitForElementAppears(driver, BackEndMainPage.platformLabelLocator, BackEndMainPage.elementsTimeout);
    }

    private String getHeader(){
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    private Country parseCountry2(WebElement urlElement, WebElement zoneElement){
        Country country;

        // В ходе разработки выяснилось, что getText() работает в 2 раза медленнее
        String countryName = urlElement.getAttribute("text");
        String countryUrl = urlElement.getAttribute("href");
        Integer zonesNumber = Integer.valueOf(zoneElement.getAttribute("textContent"));

        country = new Country(countryName, countryUrl, zonesNumber);
        return country;
    }

    private GeoZone parseZone(WebElement urlElement){
        GeoZone geoZone;

        String zoneName = urlElement.getAttribute("text");
        String zoneUrl = urlElement.getAttribute("href");

        geoZone = new GeoZone(zoneName, zoneUrl);
        return geoZone;

    }

    private List<String> zonesToString(List<WebElement> zoneElements){
        List<String> zonesNames = new ArrayList<>();
        for (WebElement element: zoneElements){
            zonesNames.add(element.getAttribute("value"));
        }
        return zonesNames;
    }

    private List<String> zonesSelectedToString(List<WebElement> zoneElements){
        List<String> zonesNames = new ArrayList<>();
        for (WebElement element: zoneElements){
            zonesNames.add(element.getAttribute("textContent"));
        }
        return zonesNames;
    }

    //------
    //https://stackoverflow.com/questions/8695531/java-checking-if-an-arraylist-of-string-are-in-alphabetical-order
    //-----
    private Boolean isStringArrayListSorted(List<String> stringArrayList){
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (final String current: stringArrayList) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

//----------------------------------------------------------------------------------------------------------------------

    @BeforeClass
    public static void beforeClass(){
        countries = new ArrayList<>();
        zones = new ArrayList<>();
        driver = browserLaunch("chrome");
        backEndLogin();
    }
//***********************************************
//***** Часть 1)
//***********************************************

    @Test
    public void Test1_OpenCountriesPage(){
        driver.navigate().to(BackEndCountriesList.url);
        Assert.assertEquals(getHeader(),BackEndCountriesList.header);
    }

    @Test
    public void Test2_GetCountriesList(){
        // Выбрали ссылки и названия стран. Если список пустой нет смысла что-то делать дальше
        List<WebElement> urlElements = driver.findElements(BackEndCountriesList.urlsLocator);
        if (urlElements.size() == 0) Assert.fail("Не найден список стран");

        //Выбрали геоЗоны
        List<WebElement> zoneElements = driver.findElements(BackEndCountriesList.zonesLocator) ;
        if (urlElements.size() != zoneElements.size()) Assert.fail("Что-то не так с версткой. Разное количество стран и геозон");

        // Парсим каждую строку и добавляем в массив стран
        for(Integer i=0; i< urlElements.size(); i++ ){
            countries.add(parseCountry2(urlElements.get(i), zoneElements.get(i)));
        }
    }

    @Test
    public void Test3_isCountriesSorted(){
        List<String> countriesNames = new ArrayList<>();
        for (Country country:countries){
            countriesNames.add(country.getName());
        }
        Assert.assertTrue(isStringArrayListSorted(countriesNames));
    }

    @Test
    public void Test4_isGeozonesInsideCountriesSorted(){
        for (Country country:countries){
            if (country.getZonesNumber() > 0){
                driver.navigate().to(country.getUrl());
                List<WebElement> zoneElements = driver.findElements(BackEndEditCountry.zonesLocator);
                if (zoneElements.size() == 0) Assert.fail("Внезапно в карточке страны пропали геоЗоны");
                List<String> zoneNames = zonesToString(zoneElements);
                Assert.assertTrue(isStringArrayListSorted(zoneNames));
            }//--if
        }//--for
    }

//***********************************************
//***** Часть 2)
//***********************************************

    @Test
    public void Test5_OpenZonesList(){
        driver.navigate().to(BackEndGeoZones.url);
        Assert.assertEquals(getHeader(),BackEndGeoZones.header);
    }

    @Test
    public void Test6_GetZonesList(){
        List<WebElement> urlList;

        urlList = driver.findElements(BackEndGeoZones.urlsLocator);
        if (urlList.size() == 0) Assert.fail("Отсутствует список геозон");

        for(WebElement urlElement: urlList){
            zones.add(parseZone(urlElement));
        }
    }

    @Test
    public void Test7_isZonesSortedInsideGeoZones(){

        for(GeoZone zone: zones){
           driver.navigate().to(zone.getUrl());
           List<WebElement> zoneElements = driver.findElements(BackEndEditGeoZone.zonesLocator);
           if (zoneElements.size() == 0) Assert.fail("Внезапно пропали геозоны");

           //getAttribute("value") использовать неправильно, поскольку возвращается код зоны, а не ее наименование
           List<String> zoneNames = zonesSelectedToString(zoneElements);
           Assert.assertTrue(isStringArrayListSorted(zoneNames));
        }
    }

    @AfterClass
    public static void afterClass() {
        browserStop(driver);
    }

}