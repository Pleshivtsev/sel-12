package lesson5Properties;

import driverService.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class TestBase3 {

//----------------------------------------------------------------------------------------------------
    //------ Driver Service functions
    protected static void waitForElementAppears(WebDriver driver, By locator, Integer timeOutSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected static WebDriver browserLaunch(String browserName){
        SeleniumDriver seleniumDriver = new SeleniumDriver(browserName);
        return seleniumDriver.getDriver();
    }

    protected static void browserStop(WebDriver driver){
        driver.quit();
    }


}
