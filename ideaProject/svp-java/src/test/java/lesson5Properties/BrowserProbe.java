package lesson5Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BrowserProbe extends TestBase3 {

    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass(){
        driver = browserLaunch("chrome");
    }

    @Test
    public void test1(){
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void test2(){
        driver.get("http://www.google.com");
    }

    @Test
    public void test3(){
        driver.get("https://www.amazon.com");
    }

    @Test
    public void test4(){
        WebDriver ieDriver = browserLaunch("ie");
        ieDriver.get("http://www.yandex.ru");
        browserStop(ieDriver);
    }

    @Test
    public void test5(){
        WebDriver ffDriver = browserLaunch("firefox");
        ffDriver.get("http://www.yandex.ru");
        browserStop(ffDriver);
    }

    @AfterClass
    public static void afterClass() {
        browserStop(driver);
    }

}