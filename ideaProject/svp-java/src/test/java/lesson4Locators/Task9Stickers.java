package lesson4Locators;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.frontEnd.HomePage;

import java.util.List;

public class Task9Stickers extends TestBase2 {

    @Before
    public void init(){
        if (driver != null) return;
        browserLaunch("chrome");
    }


    @Test
    public void case0002_Stickers(){
        List<WebElement> products;

        driver.get(AppSettings.frontEndUrl);
        waitForElementAppears(HomePage.productsLocator, HomePage.elementsTimeout);

        products = driver.findElements(HomePage.productsLocator);
        if (products.size() == 0){
            Assert.fail("Не найдены карточки продуктов");
        }

        for (WebElement product:products){
            List<WebElement> stickers;
            String productName;

            stickers = product.findElements(HomePage.stickerLocator);
            productName = product.findElement(HomePage.productNameLocator).getText();

            if (stickers.size() == 0) Assert.fail("Не найдено стикеров у продукта: " + productName);
            if (stickers.size() > 1) Assert.fail("У продукта " + productName + " больше одного стикера");
        }

    }

}