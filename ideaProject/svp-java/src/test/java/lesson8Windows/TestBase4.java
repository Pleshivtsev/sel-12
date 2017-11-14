package lesson8Windows;

import driverService.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase4 {

//----------------------------------------------------------------------------------------------------------------------
//--- Ожидания
    protected void waitForElementLocatedByIsVisible(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementLocatedByToBeClickable(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForTextOfElementChanged(WebDriver driver, WebElement element, String oldText, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until((RavenDarkholme) -> !(element.getText().equals(oldText)));
    }

    protected void waitForStalenessOfElement(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
//----------------------------------------------------------------------------------------------------------------------

    protected static WebDriver browserLaunch(String browserName){
        SeleniumDriver seleniumDriver = new SeleniumDriver(browserName);
        return seleniumDriver.getDriver();
    }

    protected static void browserStop(WebDriver driver){
        driver.quit();
    }

}