package driverService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitService {

    public static void waitForElementLocatedByIsVisible(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementPresent(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementLocatedByToBeClickable(WebDriver driver, By locator, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForTextOfElementChanged(WebDriver driver, WebElement element, String oldText, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until((RavenDarkholme) -> !(element.getText().equals(oldText)));
    }

    public static void waitForStalenessOfElement(WebDriver driver, WebElement element, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}