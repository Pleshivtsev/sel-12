package driverService;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumDriver {

    private WebDriver driver;
    private String browserName;

    public SeleniumDriver(String browserName){
        this.browserName = browserName;
        browserLaunch(browserName);

    }

    public WebDriver getDriver() {return  driver;}
    public void stop(){ driver.quit();}

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
    private void edgeLaunch(){      driver = new EdgeDriver();}

    private WebDriver browserLaunch(String browserName){
        switch (browserName) {
            case "chrome"   : chromeLaunch(); break;
            case "firefox"  : fireFoxLaunch(); break;
            case "firefoxLegacy"  : fireFoxLegacyLaunch(); break;
            case "firefoxNightly"  : ffNightlyLaunch(); break;
            case "ie"       : ieLaunch(); break;
            case "edge"     : edgeLaunch(); break;
            default         : Assert.fail("Invalid browser name!");
        }
        return driver;

    }

}
