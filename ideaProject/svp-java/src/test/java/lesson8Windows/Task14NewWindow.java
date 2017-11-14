package lesson8Windows;

import appSettings.AppSettings;
import data.Country;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.backEnd.BackEndCountriesList;
import pages.backEnd.BackEndEditCountry;
import pages.backEnd.BackEndLoginPage;
import pages.backEnd.BackEndMainPage;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task14NewWindow extends TestBase4 {
    private Boolean result = true;

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods

    private void backEndLogin(WebDriver driver){
        driver.get(AppSettings.backEndUrl);
        driver.findElement(BackEndLoginPage.userNameInputLocator).sendKeys(AppSettings.adminLogin);
        driver.findElement(BackEndLoginPage.userPasswordInputLocator).sendKeys(AppSettings.adminPassword);
        driver.findElement(BackEndLoginPage.loginButtonLocator).click();
        waitForElementLocatedByIsVisible(driver, BackEndMainPage.platformLabelLocator, BackEndMainPage.elementsTimeout);
    }

    private String getHeader(WebDriver driver){
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    private void openCountriesPage(WebDriver driver){
        driver.navigate().to(BackEndCountriesList.url);
        Assert.assertEquals(getHeader(driver),BackEndCountriesList.header);
    }

    //Task9Sorting
    private Country parseCountry3(WebElement urlElement){
        Country country;
        String countryName = urlElement.getAttribute("text");
        String countryUrl = urlElement.getAttribute("href");
        country = new Country(countryName, countryUrl, 0);
        return country;
    }

    private void openFirstCountryPage(WebDriver driver){
        List<WebElement> urlElements = driver.findElements(BackEndCountriesList.urlsLocator);
        if (urlElements.size() == 0) Assert.fail("Не найден список стран");
        Country firstCountry = parseCountry3(urlElements.get(0));
        driver.navigate().to(firstCountry.getUrl());
    }

    // Слегка модифицированный код из лекции к уроку№8 - ожидание нового окта
    private ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    // Слегка модифицированный код из лекции к уроку№8 - переключение в новое окно и обратно
    private void hobbit(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String href = element.getAttribute("href");

        String originalWindow = driver.getWindowHandle(); // запоминаем идентификатор текущего окна
        Set<String> existingWindows = driver.getWindowHandles(); // запоминаем идентификаторы уже открытых окон
        element.click(); // кликаем кнопку, которая открывает новое окно

        try{
            String newWindow = wait.until(anyWindowOtherThan(existingWindows)); // ждем появления нового окна, с новым идентификатором
            driver.switchTo().window(newWindow); // переключаемся в новое окно
        }
        catch (TimeoutException e){
            System.out.println("Ссылка: " + href + " открывается не в новом окне, либо вообще не открывается");
            result = false;
            return;
        }
        try {
            waitForElementLocatedByIsVisible(driver, By.cssSelector("h1"), timeout);
        }
        catch (TimeoutException e)
        {
            System.out.println("Проблемы с ожиданием заголовка страницы: " + href);
            result = false;
        }

        driver.close(); // закрываем его
        driver.switchTo().window(originalWindow); // и возвращаемся в исходное окно
    }

    private void isExtLinksOpenInNewPage(WebDriver driver){
        List<WebElement> externalLinks = driver.findElements(BackEndEditCountry.externalLinksLocator);
        for (WebElement externalLink: externalLinks){
            hobbit(driver, externalLink, 10);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    private void mainTest(WebDriver driver){
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            backEndLogin(driver);
            openCountriesPage(driver);
            openFirstCountryPage(driver);
            isExtLinksOpenInNewPage(driver);
            Assert.assertTrue(result);
        }
        finally {
            browserStop(driver);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Test
    public void Test1_IE(){
        WebDriver driver = browserLaunch("ie");
        mainTest(driver);
    }

    @Test
    public void Test2_Chrome(){
        WebDriver driver = browserLaunch("chrome");
        mainTest(driver);
    }

    @Test
    public void Test3_ChromeHeadless(){
        WebDriver driver = browserLaunch("chromeHeadless");
        mainTest(driver);
    }

    @Test
    public void Test4_FireFox(){
        WebDriver driver = browserLaunch("firefox");
        mainTest(driver);
    }

    @Test
    public void Test5_FireFoxHeadless(){
        WebDriver driver = browserLaunch("firefoxHeadless");
        mainTest(driver);
    }
}