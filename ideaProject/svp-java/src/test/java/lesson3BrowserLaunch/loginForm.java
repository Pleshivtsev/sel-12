package lesson3BrowserLaunch;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class loginForm extends TestBase1 {

//------ Variables

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


 //----Test Service functions
    private void loginToBackend(){
        driver.findElement(userNameInputLocator).sendKeys(uLogin);
        driver.findElement(userPasswordInputLocator).sendKeys(uPassword);

        driver.findElement(loginButtonLocator).click();
    }


//----------------------------------------------------------------------------------------------------

    @Before
    public void init(){
        browserLaunch("firefoxNightly");
    }

    @Test
    public void case0001_Login(){
        driver.get(getBackEndUrl());
        loginToBackend();
        waitForElementAppears(platformLabelLocator, defaultTimeOut);
    }


}
