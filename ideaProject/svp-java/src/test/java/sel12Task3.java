// Sel-12
// Задание №3. Плешивцев Семён

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sel12Task3 {

//------ Variables
    private WebDriver driver;

    private String uLogin = "admin";
    private String uPassword = "admin";

    private String baseUrl = "http://localhost/";
    private String backEndUrl = "litecart/admin";

//------ Locators
    private By userNameInputLocator = By.name("username");
    private By userPasswordInputLocator = By.name("password");
    private By loginButtonLocator = By.name("login");

    private By platformLabelLocator = By.id("platform");

//------ TimeOuts
    private Integer defaultTimeOut = 10;

//----------------------------------------------------------------------------------------------------

    //------ Driver Service functions
    private String getBackEndUrl(){
        return baseUrl+backEndUrl;
    }

    private void waitForElementAppears(By locator, Integer timeOutSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//----Test Service functions
    private void loginToBackend(){
        driver.findElement(userNameInputLocator).sendKeys(uLogin);
        driver.findElement(userPasswordInputLocator).sendKeys(uPassword);

        driver.findElement(loginButtonLocator).click();
    }

//----------------------------------------------------------------------------------------------------

    @Before
    public void init(){
        driver = new ChromeDriver();
    }

    @Test
    public void case0001_Login(){
        driver.get(getBackEndUrl());
        loginToBackend();
        waitForElementAppears(platformLabelLocator, defaultTimeOut);
    }

    @After
    public void stop(){
        driver.quit();
    }

}