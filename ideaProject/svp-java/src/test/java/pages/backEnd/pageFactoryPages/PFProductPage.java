package pages.backEnd.pageFactoryPages;

import driverService.WaitService;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.Page;

public class PFProductPage extends Page {

// --- Elements --------------------------------------
    @FindBy(css="button[name=add_cart_product]") private WebElement add2CartButton;
    @FindBy(css="select[name*=options]") private WebElement optionSelector;
    @FindBy(css="span.quantity") private WebElement quantity;

// --- Constructor --------------------------------------
    public PFProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

// --- InternalMethods --------------------------------------
    private Boolean isOptionsSelectorPresent(){
        try{
            WaitService.waitForElementPresent(driver, optionSelector, 1);
            return true;
        }
        catch (TimeoutException ex){
            return false;
        }
    }

    private void selectOptionIfExists(){
        if (isOptionsSelectorPresent()){
            optionSelector.click();
            new Select(optionSelector).selectByVisibleText("Small");
        }
    }

    private void waitForPageLoaded(){
        WaitService.waitForElementPresent(driver,add2CartButton,10);
    }

// --- Services --------------------------------------
    public void add2Cart(){
        waitForPageLoaded();
        selectOptionIfExists();
        String quantityOldValue =  quantity.getText();
        add2CartButton.click();
        WaitService.waitForTextOfElementChanged(driver, quantity, quantityOldValue, 10);
    }
}