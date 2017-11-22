package lesson9Remote;

import driverService.BrowserStack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


public class Task16BrowserStack {

    BrowserStack browserStack;
    WebDriver driver;
//--------------------

    private String getHeader(){
        return driver.getTitle();
    }

    private void getGoogleHeader(){
        driver.navigate().to("https://www.google.com");
        System.out.println(getHeader());
    }

    private void getYandexHeader(){
        driver.navigate().to("https://www.yandex.ru");
        System.out.println(getHeader());
    }

    private void getAmazonHeader(){
        driver.navigate().to("https://www.amazon.com");
        System.out.println(getHeader());
    }


    @Before
    public void beforeTest() throws MalformedURLException {
        browserStack = new BrowserStack();
        driver = browserStack.getDriver();
    }


    @Test
    public void Test1(){
        getGoogleHeader();
        getYandexHeader();
        getAmazonHeader();
    }

    @After
    public void afterTest(){
        driver.quit();
    }

}