package lesson3BrowserLaunch;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class TestBase1 {

//------ Variables
    WebDriver driver;


//----------------------------------------------------------------------------------------------------
    //------ Driver Service functions

    private void chromeLaunch(){    driver = new ChromeDriver();   }
    private void fireFoxLaunch(){   driver = new FirefoxDriver();  }

    private void fireFoxLegacyLaunch(){
        FirefoxOptions options = new FirefoxOptions();
        options.setLegacy(true);
        options.setBinary("C:\\ FirefoxESR\\firefox.exe");

        driver = new FirefoxDriver(options);
    }

    private void ffNightlyLaunch(){
        FirefoxOptions options = new FirefoxOptions();
        options.setLegacy(false);
        options.setBinary("C:\\Program Files\\Nightly\\firefox.exe");
        driver = new FirefoxDriver(options);
    }


    private void ieLaunch(){        driver = new InternetExplorerDriver();   }
    private void edgeLaucnh(){      driver = new EdgeDriver();}

    protected void browserLaunch(String browserName){
        switch (browserName) {
            case "chrome"   : chromeLaunch(); break;
            case "firefox"  : fireFoxLaunch(); break;
            case "firefoxLegacy"  : fireFoxLegacyLaunch(); break;
            case "firefoxNightly"  : ffNightlyLaunch(); break;
            case "ie"       : ieLaunch(); break;
            case "edge"     : edgeLaucnh(); break;
            default         : Assert.fail("Invalid browser name!");
        }

    }



    protected void waitForElementAppears(By locator, Integer timeOutSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//------ Test Service functions


//----------------------------------------------------------------------------------------------------

    @After
    public void stop(){
        driver.quit();
    }

}