package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

// --- Members --------------------------------------
    protected String url;
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected Integer pageTimeout = 10;
    protected Integer elementsTimeout = 10;

// --- Constructor --------------------------------------
    public Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

// --- Services --------------------------------------
    public void open(){
        driver.navigate().to(url);
    }
}