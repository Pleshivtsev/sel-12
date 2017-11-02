package lesson4Locators;

import appSettings.AppSettings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.backEnd.BackEndLoginPage;
import pages.backEnd.BackEndMainPage;

import java.util.List;

public class Task8Navigation extends TestBase2 {


    private void case0001_backEndLogin(){

        driver.get(AppSettings.backEndUrl);

        driver.findElement(BackEndLoginPage.userNameInputLocator).sendKeys(AppSettings.adminLogin);
        driver.findElement(BackEndLoginPage.userPasswordInputLocator).sendKeys(AppSettings.adminPassword);

        driver.findElement(BackEndLoginPage.loginButtonLocator).click();

        waitForElementAppears(BackEndMainPage.platformLabelLocator, BackEndMainPage.elementsTimeout);

    }

    private String getHeader(){
        String pageHeader;
        By pageHeaderLocator = By.cssSelector("h1");
        pageHeader = driver.findElement(pageHeaderLocator).getText();
        return pageHeader;
    }


    @Before
    public void init(){
        if (driver != null) return;
            browserLaunch("chrome");
    }

    @Test
    public void case0002_SideMenuNavigation(){
        WebElement menuItem;
        WebElement subMenuItem;
        List<WebElement> menuItems;
        List<WebElement> subMenuItems;

        // Login first
        case0001_backEndLogin();

        //First check
        menuItems = driver.findElements(BackEndMainPage.sideMenuItemsLocator);
        Integer sideMenuSize = menuItems.size();

        System.out.println("Найдено элементов меню: "  + sideMenuSize);
        if (menuItems.size() == 0) Assert.fail("Не найдены элементы бокового меню");

        //Для каждого из найденных элементов меню
        for (int i = 0; i< sideMenuSize; i++){
            Integer subMenuSize;
            String menuItemName;

            menuItem = menuItems.get(i);
            menuItemName = menuItem.getText();

            menuItem.click(); // Кликнули - Страница обновилась

            // Получили список элементов - подменю
            subMenuItems = driver.findElements(BackEndMainPage.subItemsLocator);
            subMenuSize = subMenuItems.size();
            System.out.println(menuItemName + ": " + subMenuSize + "; h1 = " + getHeader());

            // Для каждого из пунктов подменю
            for (int j = 0; j < subMenuSize; j++){
                String subMenuItemName;

                subMenuItem = subMenuItems.get(j);
                subMenuItemName = subMenuItem.getText();

                subMenuItem.click(); // Кликнули - Страница обновилась
                System.out.println("-- " + subMenuItemName + "; h1 = " + getHeader());

                // Обновили контейнер с эелементами подменю
                subMenuItems = driver.findElements(BackEndMainPage.subItemsLocator);
            }

            // Обновили контейнер с боковым меню
            menuItems = driver.findElements(BackEndMainPage.sideMenuItemsLocator);

        }

    }

}