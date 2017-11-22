package lesson10Logging;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import pages.backEnd.BackEndCatalog;
import pages.backEnd.BackEndLoginPage;
import pages.backEnd.BackEndMainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task17BrowserLog extends TestBase4 {

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods

    private void backEndLogin(WebDriver driver){
        driver.get(AppSettings.backEndUrl);
        driver.findElement(BackEndLoginPage.userNameInputLocator).sendKeys(AppSettings.adminLogin);
        driver.findElement(BackEndLoginPage.userPasswordInputLocator).sendKeys(AppSettings.adminPassword);
        driver.findElement(BackEndLoginPage.loginButtonLocator).click();
        waitForElementLocatedByIsVisible(driver, BackEndMainPage.platformLabelLocator, BackEndMainPage.elementsTimeout);
    }

    private void openCatalogPage(WebDriver driver){
        driver.navigate().to(BackEndCatalog.directUrl);
    }

    private List<String> getProductsLinks(WebDriver driver){
        List<String> rawLinks = new ArrayList<>();
        List<String> productsLinks = new ArrayList<>();
        List<WebElement> productsElements = driver.findElements(BackEndCatalog.productsLinksLocator);

        // Получаем все ссылки
        productsElements.forEach(p -> rawLinks.add(p.getAttribute("href")));
        // Оставляем только ссылки на продукты
        rawLinks.forEach(link -> {if (link.contains("edit_product")) productsLinks.add(link);});

        return productsLinks;
    }

    private String decorateString(String newString){
        String eol = System.getProperty("line.separator");
        return newString.concat(eol);
    }

    private String getBrowserLog(WebDriver driver){
        String resultLog = "";

        for (LogEntry logEntry : driver.manage().logs().get("browser")) {
            if (!logEntry.toString().equals("")) resultLog += decorateString(logEntry.toString());
        }

        return resultLog;
    }

    private String verifyProductsLinks(WebDriver driver){
        List<String> links = getProductsLinks(driver);
        String totalLog = "";

        for(String link:links){
            driver.navigate().to(link);
            totalLog += getBrowserLog(driver);
        }
        return totalLog;
    }

//----------------------------------------------------------------------------------------------------------------------
    private void mainTest(WebDriver driver){
        try{
            String totalLog;
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            backEndLogin(driver);
            openCatalogPage(driver);
            totalLog = verifyProductsLinks(driver);
            if (!totalLog.equals("")){
                System.out.println(totalLog);
                Assert.fail("Есть ошибки в логе браузера");
            }
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