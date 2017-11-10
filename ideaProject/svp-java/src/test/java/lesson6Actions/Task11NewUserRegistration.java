package lesson6Actions;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.frontEnd.HomePage;
import pages.frontEnd.RegistrationPage;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task11NewUserRegistration extends TestBase3 {

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

    private void openRegistrationPage(WebDriver driver){
        driver.navigate().to(AppSettings.frontEndUrl);
        driver.findElement(HomePage.registrationLinkLocator).click();
        waitForElementAppears(driver, RegistrationPage.createAccountButtonLocator, RegistrationPage.elementsTimeout);
        Assert.assertEquals(getHeader(driver), RegistrationPage.header);
    }

    private String generateEmailString(String inputString){
        return inputString + "@" + inputString +".com";
    }

    private void fillCountry(WebDriver driver, String country){
        driver.findElement(RegistrationPage.countrySelectorLocator).click();
        waitForElementAppears(driver, RegistrationPage.countrySearchInputLocator, RegistrationPage.elementsTimeout);
        driver.findElement(RegistrationPage.countrySearchInputLocator).sendKeys(country + Keys.ENTER);
    }

    private void fillState(WebDriver driver, String state){
        waitForElementClickable(driver, RegistrationPage.stateSelectorLocator, RegistrationPage.elementsTimeout);
        WebElement element1 = driver.findElement(RegistrationPage.stateSelectorLocator);
        element1.click();
        new Select(element1).selectByVisibleText(state);
    }


    private void fillRegistrationForm(WebDriver driver, String testString){
        String country = "United States";
        String state = "Florida";
        String postcode = "12345";

        driver.findElement(RegistrationPage.firstNameInputLocator).sendKeys("name-" + testString);
        driver.findElement(RegistrationPage.lastNameInputLocator).sendKeys("last" + testString);
        driver.findElement(RegistrationPage.address1InputLocator).sendKeys("addr-" + testString);
        driver.findElement(RegistrationPage.postcodeInputLocator).sendKeys(postcode);
        driver.findElement(RegistrationPage.cityInputLocator).sendKeys("city-" + testString);

        fillCountry(driver,country);
        fillState(driver, state);

        driver.findElement(RegistrationPage.emailInputLocator).sendKeys(generateEmailString(testString));
        driver.findElement(RegistrationPage.phoneInputLocator).sendKeys("1234567890");
        driver.findElement(RegistrationPage.passwordInputLocator).sendKeys(testString);
        driver.findElement(RegistrationPage.confirmedPasswordInputLocator).sendKeys(testString);

        driver.findElement(RegistrationPage.createAccountButtonLocator).click();

    }

    private void logout(WebDriver driver){
        waitForElementAppears(driver, HomePage.logoutLinkLocator, HomePage.elementsTimeout);
        driver.findElement(HomePage.logoutLinkLocator).click();
    }

    private void login(WebDriver driver, String userEmail, String userPassword){
        waitForElementAppears(driver, HomePage.emailInputLocator, HomePage.elementsTimeout);
        driver.findElement(HomePage.emailInputLocator).sendKeys(userEmail);
        driver.findElement(HomePage.passwordInputLocator).sendKeys(userPassword);
        driver.findElement(HomePage.loginButtonLocator).click();
    }

    private Boolean verifySuccessLogin(WebDriver driver, String userName){
        waitForElementAppears(driver, HomePage.noticeSuccessLocator, HomePage.elementsTimeout);
        WebElement notice = driver.findElement(HomePage.noticeSuccessLocator);
        if (notice.getText().contains(userName)) return true;
            else return false;
    }

//----------------------------------------------------------------------------------------------------------------------

    private void mainTest(WebDriver driver){
        String testString = getSaltString(8);

        try{
            openRegistrationPage(driver);                                // Переход на форму регистрации нового пользователя
            fillRegistrationForm(driver, testString);                    // Заполнение формы
            logout(driver);
            login(driver, generateEmailString(testString), testString);
            if (!verifySuccessLogin(driver, testString)) Assert.fail("Попытка логина не удалась");
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