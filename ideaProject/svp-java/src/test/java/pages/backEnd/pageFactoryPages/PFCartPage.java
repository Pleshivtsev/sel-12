package pages.backEnd.pageFactoryPages;

import appSettings.AppSettings;
import driverService.WaitService;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Page;

import java.util.List;

public class PFCartPage extends Page {

//------ Locators
    public static By orderSummaryTableLocator = By.cssSelector("table.dataTable");
// --- Elements --------------------------------------
    @FindBy(css="button[name=remove_cart_item]")    private WebElement removeItemButton;
    @FindBy(css="table.dataTable")  private List<WebElement> orderSummaryTables;
    @FindBy(css="table.dataTable")  private WebElement orderSummaryTable;

// --- Constructor --------------------------------------
    public PFCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        url = AppSettings.frontEndUrl + "/en/checkout";
    }

// --- Services --------------------------------------
    public void waitForPageLoaded(){
        WaitService.waitForElementToBeClickable(driver, removeItemButton, 10);
    }

    public Boolean isAnyProductInTheCart(){
        try {
            WaitService.waitForElementPresent(driver, orderSummaryTable, 1);
        }
        catch (TimeoutException ex){
            return false;
        }
        return true;
    }

    public void removeArbitraryProduct(){
        WebElement staledTable = driver.findElement(orderSummaryTableLocator);          // Прилошсь подставлять костыль, т.к. через PageFactory staleness работает некорректно
        WaitService.waitForElementToBeClickable(driver, removeItemButton, 10);
        removeItemButton.click();
        WaitService.waitForStalenessOfElement(driver, staledTable, 10);
    }
}