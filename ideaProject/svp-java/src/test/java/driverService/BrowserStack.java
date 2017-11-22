package driverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStack {
    public static final String USERNAME = "nomaromar1";
    public static final String AUTOMATE_KEY = "zifzWZcqPuV32zheySZL";
    public static final String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    WebDriver driver;

    public BrowserStack() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "chrome");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "First build");
        driver = new RemoteWebDriver(new URL(url), caps);
    }

    public WebDriver getDriver(){
        return driver;
    }

}
