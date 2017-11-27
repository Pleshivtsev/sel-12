package appLogic;

import org.openqa.selenium.WebDriver;
import pages.backEnd.pageFactoryPages.PFCartPage;
import pages.backEnd.pageFactoryPages.PFHomePage;
import pages.backEnd.pageFactoryPages.PFProductPage;

public class LCShopApp {
    WebDriver driver;

// --- Constructor --------------------------------------
    public LCShopApp(WebDriver driver){
        this.driver = driver;
    }

// --- Services --------------------------------------
    public void openHomePage(){
        PFHomePage homePage = new PFHomePage(driver);
        homePage.open();
    }

    public void addProduct2Cart(){
        PFHomePage homePage = new PFHomePage(driver);
        PFProductPage productPage = new PFProductPage(driver);
        homePage.open();
        homePage.clickOnFirstProduct();
        productPage.add2Cart();
    }

    public void addProducts2Cart(Integer times){
        for(int i = 0; i<times; i++){
            addProduct2Cart();
        }
    }

    public void openTheCart(){
        PFCartPage cartPage = new  PFCartPage(driver);
        cartPage.open();
        cartPage.waitForPageLoaded();
    }

    public Boolean isAnyProductsInTheCart(){
        PFCartPage cartPage = new PFCartPage(driver);
        return cartPage.isAnyProductInTheCart();
    }

    public void removeArbitraryProductFromTheCart(){
        PFCartPage cartPage = new PFCartPage(driver);
        cartPage.removeArbitraryProduct();
    }

    public void removeAllProductsFromTheCart(){
        while (isAnyProductsInTheCart()) removeArbitraryProductFromTheCart();
    }
}