package lesson6Actions;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.backEnd.BackEndCatalog;
import pages.backEnd.BackEndLoginPage;
import pages.backEnd.BackEndMainPage;
import pages.backEnd.BackEndNewProduct;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task12AddNewProduct extends TestBase3{

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods
    private String getHeader(WebDriver driver){
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    //----
    // https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
    protected String getSaltString(Integer strLength) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < strLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private void backEndLogin(WebDriver driver){
        driver.get(AppSettings.backEndUrl);
        driver.findElement(BackEndLoginPage.userNameInputLocator).sendKeys(AppSettings.adminLogin);
        driver.findElement(BackEndLoginPage.userPasswordInputLocator).sendKeys(AppSettings.adminPassword);
        driver.findElement(BackEndLoginPage.loginButtonLocator).click();
        waitForElementAppears(driver, BackEndMainPage.platformLabelLocator, BackEndMainPage.elementsTimeout);
    }

    private void openAddNewProductPage(WebDriver driver){
        backEndLogin(driver);
        driver.findElement(BackEndMainPage.catalogMenuItemLocator).click();
        driver.findElement(BackEndCatalog.newProductButton).click();
        Assert.assertEquals(getHeader(driver), BackEndNewProduct.header);
    }

    private void fillGeneralTab(WebDriver driver, String productName, String path2ProductImage){
        driver.findElement(BackEndNewProduct.nameLocator).sendKeys(productName);
        driver.findElement(BackEndNewProduct.codeLocator).sendKeys(productName);
        driver.findElement(BackEndNewProduct.categoryLocator).click();

        WebElement select = driver.findElement(BackEndNewProduct.defaultCategorySelectLocator);
        select.click();
        new Select(select).selectByVisibleText("Selenium12");

        driver.findElement(BackEndNewProduct.groupsLocator).click();
        driver.findElement(BackEndNewProduct.quantityLocator).clear();
        driver.findElement(BackEndNewProduct.quantityLocator).sendKeys("10");
        driver.findElement(BackEndNewProduct.imageFileLocator).sendKeys(path2ProductImage);

        driver.findElement(BackEndNewProduct.dateValidFromLocator).sendKeys("2017-01-01");
        driver.findElement(BackEndNewProduct.dateValidToLocator).sendKeys("2019-01-01");
    }

    private void fillInformationTab(WebDriver driver, String productName){
        driver.findElement(BackEndNewProduct.informationTabLocator).click();
        waitForElementClickable(driver, BackEndNewProduct.manufacturerSelectLocator, BackEndNewProduct.elementsTimeout);
        WebElement select = driver.findElement(BackEndNewProduct.manufacturerSelectLocator);
        select.click();
        new Select(select).selectByVisibleText("ACME Corp.");
        driver.findElement(BackEndNewProduct.keywordsLocator).sendKeys(productName);
        driver.findElement(BackEndNewProduct.shortDescriptionLocator).sendKeys("shrt-" + productName);
        driver.findElement(BackEndNewProduct.descriptionLocator).sendKeys("long-long-" + productName);
        driver.findElement(BackEndNewProduct.headTitleLocator).sendKeys(productName);
        driver.findElement(BackEndNewProduct.metaDescriptionLocator).sendKeys(productName);
    }

    private void fillPricesTab(WebDriver driver){
        driver.findElement(BackEndNewProduct.pricesTabLocator).click();
        waitForElementClickable(driver, BackEndNewProduct.priceLocator, BackEndNewProduct.elementsTimeout);
        driver.findElement(BackEndNewProduct.priceLocator).clear();
        driver.findElement(BackEndNewProduct.priceLocator).sendKeys("11");
        WebElement select = driver.findElement(BackEndNewProduct.priceCurrencySelectLocator);
        select.click();
        new Select(select).selectByVisibleText("Euros");
        driver.findElement(BackEndNewProduct.usdTaxLocator).clear();
        driver.findElement(BackEndNewProduct.usdTaxLocator).sendKeys("20");
        driver.findElement(BackEndNewProduct.eurTaxLocator).clear();
        driver.findElement(BackEndNewProduct.eurTaxLocator).sendKeys("30");
    }

    private void fillProductCard(WebDriver driver, String productName, String path2ProductImage){
        fillGeneralTab(driver, productName, path2ProductImage);
        fillInformationTab(driver, productName);
        fillPricesTab(driver);
        driver.findElement(BackEndNewProduct.saveButton).click();
    }

    private Boolean verifyProductAppear(WebDriver driver, String productName){
        driver.findElement(BackEndMainPage.catalogMenuItemLocator).click();    // обновляем страницу с каталогом
        driver.findElement(By.linkText(productName)).click();
        if (getHeader(driver).contains(productName)) return true;
            else return false;

    }

//----------------------------------------------------------------------------------------------------------------------
    private void mainTest(WebDriver driver){
        String productName = "sel12-" + getSaltString(6);
        String imageFile = "selenium.png";
        ClassLoader classLoader = getClass().getClassLoader();
        String path2ProductImage = classLoader.getResource(imageFile).getFile().toString().substring(1).replace('/','\\');

        try{
            openAddNewProductPage(driver);
            fillProductCard(driver, productName, path2ProductImage);
            if (!verifyProductAppear(driver, productName)) Assert.fail("Новый продукт не появился в админке");
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