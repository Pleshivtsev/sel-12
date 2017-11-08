package lesson5Properties;

import appSettings.AppSettings;
import data.Product;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.frontEnd.HomePage;
import pages.frontEnd.ProductPage;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Task10RightPage extends TestBase3 {

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods
    private void openHomePage(WebDriver driver){
        driver.navigate().to(AppSettings.frontEndUrl);
    }


    private void openProductPage(WebDriver driver){
        driver.findElement(HomePage.campaignProductLocator).click();
        waitForElementAppears(driver, ProductPage.productContainerLocator,ProductPage.elementsTimeout);
    }

    private String compareProducts(Product product1, Product product2){
        String result = "";

        String name1 = product1.getName();
        String name2 = product1.getName();
        String regularPrice1 = product1.getRegularPrice();
        String regularPrice2 = product2.getRegularPrice();
        String campaignPrice1 = product1.getCampaignPrice();
        String campaignPrice2 = product2.getCampaignPrice();

        if (!name1.equals(name2))                   result = result.concat("Название не совпадает;");
        if (!regularPrice1.equals(regularPrice2))   result = result.concat(" Обычная цена не совпадает;");
        if (!campaignPrice1.equals(campaignPrice2)) result = result.concat(" Акционная цена не совпадает;");

        return result;
    }

    private Product getProductFromHomepage(WebDriver driver){
        List<WebElement> campaignElements = driver.findElements(HomePage.campaignProductLocator);
        if (campaignElements.size() == 0) Assert.fail("Не найдено акционных продуктов");

        String name = campaignElements.get(0).findElement(HomePage.productNameLocator).getText();
        String regularPrice = campaignElements.get(0).findElement(HomePage.productRegularPriceLocator).getText();
        String campaignPrice = campaignElements.get(0).findElement(HomePage.productCampaignPriceLocator).getText();

        Product product = new Product( name);
        product.setRegularPrice(regularPrice).setCampaignPrice(campaignPrice);

        return product;
    }

    private Product getProductFromProductPage(WebDriver driver){

        WebElement productContainer = driver.findElement(ProductPage.productContainerLocator);
        String name = productContainer.findElement(ProductPage.nameLocator).getText();
        String regularPrice = productContainer.findElement(ProductPage.regularPriceLocator).getText();
        String campaignPrice = productContainer.findElement(ProductPage.campaignPriceLocator).getText();

        Product product = new Product(name);
        product.setRegularPrice(regularPrice).setCampaignPrice(campaignPrice);
        return product;
    }

    private String verifyRegularPrice(WebElement regularPriceElement){
        String result = "";
         if (!regularPriceElement.getTagName().equals("s")) result = result.concat(" Обычная цена не зачеркнута;");

         String color = regularPriceElement.getCssValue("color");
         String strBuff = color.split("[\\(\\)]")[1];
         String[] channels = strBuff.split(",");
         String R = channels[0].trim();
         String G = channels[1].trim();
         String B = channels[2].trim();

         if (! (R.equals(G) && G.equals(B))) result = result.concat(" Обычная цена не серая;");

        return result;
    }

    private String verifyCampaignPrice(WebElement campaignPriceElement){
        String result = "";

        String color = campaignPriceElement.getCssValue("color");
        String strBuff = color.split("[\\(\\)]")[1];
        String[] channels = strBuff.split(",");
        String G = channels[1].trim();
        String B = channels[2].trim();

        if (! (G.equals("0") && B.equals("0"))) result = result.concat(" Акционная цена не красная;");

        return result;
    }

    private String verifyCampaignPriceBigger(WebElement regularPriceElement, WebElement campaignPriceElement){
        String result ="";
        Dimension regularPriceSize = regularPriceElement.getSize();
        Dimension campaignPriceSize = campaignPriceElement.getSize();

        if (!((campaignPriceSize.height > regularPriceSize.height) && (campaignPriceSize.width > regularPriceSize.width)))
            result = result.concat(" Размер элемента акционной цены не больше;");

        return result;
    }

    private String verifyProductStyleOnHome(WebDriver driver){
        String result = "";
        WebElement regularPriceElement;
        WebElement campaignPriceElement;

        List<WebElement> campaignElements = driver.findElements(HomePage.campaignProductLocator);
        regularPriceElement = campaignElements.get(0).findElement(HomePage.productRegularPriceLocator);
        campaignPriceElement = campaignElements.get(0).findElement(HomePage.productCampaignPriceLocator);

        result = result.concat(verifyRegularPrice(regularPriceElement));
        result = result.concat(verifyCampaignPrice(campaignPriceElement));
        result = result.concat(verifyCampaignPriceBigger(regularPriceElement, campaignPriceElement));

        return result;
    }


    private String verifyProductStyleOnPage(WebDriver driver){
        String result = "";
        WebElement regularPriceElement;
        WebElement campaignPriceElement;

        WebElement productContainer = driver.findElement(ProductPage.productContainerLocator);
        regularPriceElement = productContainer.findElement(ProductPage.regularPriceLocator);
        campaignPriceElement = productContainer.findElement(ProductPage.campaignPriceLocator);

        result = result.concat(verifyRegularPrice(regularPriceElement));
        result = result.concat(verifyCampaignPrice(campaignPriceElement));
        result = result.concat(verifyCampaignPriceBigger(regularPriceElement, campaignPriceElement));

        return result;
    }




    //----------------------------------------------------------------------------------------------------------------------
    private void main_Test(WebDriver driver){
       try {
           Product productFromHome;
           Product productFromPage;
           String homeResult ="";
           String pageResult ="";
           String compareResult ="";

           openHomePage(driver);                                    // Открыли домашнюю страницу
           productFromHome = getProductFromHomepage(driver);        // Загрузили данные о товаре в объект productFromHome
           homeResult = homeResult.concat(verifyProductStyleOnHome(driver));         // Проверили стили на главной странице
           System.out.println("Ошибки на домашней странице: " + homeResult);

           openProductPage(driver);                                 // Открыли страницу с товаром
           productFromPage = getProductFromProductPage(driver);     // Загрузили данные о товаре в объект productFromPage
           pageResult = pageResult.concat(verifyProductStyleOnPage(driver));         // Проверили стили на продуктовой странице
           System.out.println("Ошибки на продуктовой странице: " + pageResult);

           compareResult = compareResult.concat(compareProducts(productFromHome,productFromPage));     // Сравнили товары и сформировали результат
           System.out.println("Ошибки сравнения продуктов: " + compareResult);

           if ((homeResult.equals("")) && (pageResult.equals("")) && (compareResult.equals(""))) System.out.println("All ok");
                else Assert.fail();

       }
       finally {
           driver.quit();
       }

    }


//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void Test1_checkProduct_Chrome(){
        WebDriver driver = browserLaunch("chrome");
        main_Test(driver);
    }

    @Test
    public void Test2_checkProduct_FireFox(){
        WebDriver driver = browserLaunch("firefox");
        main_Test(driver);
    }

    @Test
    public void Test3_checkProduct_IE(){
        WebDriver driver = browserLaunch("ie");
        main_Test(driver);
    }

}
