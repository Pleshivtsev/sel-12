package appLogic;

import driverService.WaitService;
import org.openqa.selenium.WebDriver;
import pages.backEnd.BackEndMainPage;
import pages.backEnd.pageFactoryPages.PFHomePage;
import pages.backEnd.pageFactoryPages.PFLoginPage;

public class LCAdminApp {

    WebDriver driver;

// --- Constructor --------------------------------------
    public LCAdminApp(WebDriver driver){
        this.driver = driver;
    }

// --- Services --------------------------------------
    public void login2App(String login, String password){
        PFLoginPage loginPage = new PFLoginPage(driver);
        WaitService.waitForElementLocatedByIsVisible(driver, BackEndMainPage.platformLabelLocator, 10);
    }

    public void openHomePage(){
        PFHomePage homePage = new PFHomePage(driver);
        homePage.open();
    }

    public void addProducts2Cart(){
        PFHomePage homePage = new PFHomePage(driver);



    }

}
