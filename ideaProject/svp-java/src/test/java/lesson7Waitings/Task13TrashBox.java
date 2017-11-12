package lesson7Waitings;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.frontEnd.CartPage;
import pages.frontEnd.HomePage;
import pages.frontEnd.ProductPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task13TrashBox extends TestBase3 {

    private Integer implicitlyWaitDefault=10;

//----------------------------------------------------------------------------------------------------------------------
//--- Ожидания
    private void waitForElementLocatedByIsVisible(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForElementLocatedByToBeClickable(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForTextOfElementChanged(WebDriver driver, WebElement element, String oldText, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until((RavenDarkholme) -> !(element.getText().equals(oldText)));
    }

    private void waitForStalenessOfElement(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods
    private void openHomePage(WebDriver driver){
        driver.navigate().to(AppSettings.frontEndUrl);
        waitForElementLocatedByIsVisible(driver, HomePage.productsLocator, HomePage.elementsTimeout);
    }

    // Для разнообразия учебного процесса реализовал через неявные ожидания
    private Boolean isOptionsSelectorPresent(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(ProductPage.optionsSelectorLocator);
        driver.manage().timeouts().implicitlyWait(implicitlyWaitDefault, TimeUnit.SECONDS);

        if (elements.size() == 0) return false;
        else return true;
    }

    private void selectOption(WebElement optionSelector, String option){
        optionSelector.click();
        new Select(optionSelector).selectByVisibleText(option);
    }

    private void addFirstProduct2Cart(WebDriver driver){
        driver.findElements(HomePage.productsLocator).get(0).click();
        WebElement add2CartButton;
        add2CartButton = waitForElementLocatedByToBeClickable(driver, ProductPage.add2CartButtonLocator, ProductPage.elementsTimeout);
        WebElement quantity = driver.findElement(ProductPage.quantityLocator);
        String quantityOldValue =  quantity.getText();

        if (!isOptionsSelectorPresent(driver)){
            add2CartButton.click(); // Для некоторых уток нужно проверять наличие выбора опций
        }
        else {
                WebElement optionSelector = driver.findElement(ProductPage.optionsSelectorLocator);
                selectOption(optionSelector, "Small");
                add2CartButton.click();
        }

        waitForTextOfElementChanged(driver, quantity, quantityOldValue, ProductPage.elementsTimeout);
        driver.findElement(ProductPage.homeLinkLocator).click();
    }

    private void navigate2Cart(WebDriver driver){
        waitForElementLocatedByToBeClickable(driver,HomePage.checkOutLinkLocator, HomePage.elementsTimeout).click();
        waitForElementLocatedByToBeClickable(driver, CartPage.removeItemButtonsLocator, CartPage.elementsTimeout);
    }

    // Я не согласен с идеей проверять обновление таблицы с заказанным товаром
    // поскольку это элемент который может появиться, а может и нет. Но задание есть задание.
    private void removeAllProductsFromCart(WebDriver driver) {
        // Вынес переменные, чтобы код ниже полегче читался
        By tableLocator = CartPage.orderSummaryTableLocator;
        By removeButtonLocator = CartPage.removeItemButtonsLocator;
        Integer timeout = CartPage.elementsTimeout;
        //---------------------
        List<WebElement> orderTables = driver.findElements(tableLocator);
        WebElement staledTable;

        if (orderTables.size() == 0) Assert.fail("Внезапно пропала таблица с заказанными товарами");
        while (orderTables.size() > 0) {
            staledTable = driver.findElement(tableLocator);
            waitForElementLocatedByToBeClickable(driver, removeButtonLocator, timeout).click();
            waitForStalenessOfElement(driver, staledTable, timeout);
            orderTables = driver.findElements(tableLocator);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    private void mainTest(WebDriver driver){
        try{
            openHomePage(driver);
            for(int i=0; i<3; i++){
                addFirstProduct2Cart(driver);
            }
            navigate2Cart(driver);
            removeAllProductsFromCart(driver);
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