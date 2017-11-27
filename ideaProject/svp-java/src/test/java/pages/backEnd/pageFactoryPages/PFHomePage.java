package pages.backEnd.pageFactoryPages;

import appSettings.AppSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Page;

import java.util.List;

public class PFHomePage extends Page {

// --- Elements --------------------------------------
    @FindBy(css="li.product") private List<WebElement> products;

// --- Constructor --------------------------------------
    public PFHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        url = AppSettings.frontEndUrl;
    }

// --- Services --------------------------------------
    public void clickOnFirstProduct(){
        products.get(0).click();
    }
}