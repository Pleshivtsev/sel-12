package pages.backEnd.pageFactoryPages;

import appSettings.AppSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.Page;
import pages.backEnd.BackEndLoginPage;
import pages.backEnd.BackEndMainPage;

public class PFLoginPage extends Page {

// --- Elements --------------------------------------
    @FindBy(name = "username") private WebElement loginInput;
    @FindBy(name = "password") private WebElement passwordInput;
    @FindBy(name = "login") private WebElement loginButton;

// --- Constructor --------------------------------------
    public PFLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void setSettings(){
        url = AppSettings.backEndUrl;
    }

// --- Services --------------------------------------
    public void login(String login, String password){
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}