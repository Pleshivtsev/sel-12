package lesson11PageObjects;

import appLogic.LCShopApp;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Task19MultiLayersArchitecture extends TestBase4 {

//----------------------------------------------------------------------------------------------------------------------
    private void mainTest(WebDriver driver){
        LCShopApp lcShopApp = new LCShopApp(driver);

        try{
            lcShopApp.openHomePage();
            lcShopApp.addProducts2Cart(3);
            lcShopApp.openTheCart();
            lcShopApp.removeAllProductsFromTheCart();
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