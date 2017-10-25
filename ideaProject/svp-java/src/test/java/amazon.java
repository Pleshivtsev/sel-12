import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazon {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void init(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void case0001_homePage(){
        WebElement searchString;

        driver.get("https://www.amazon.com/");

        searchString = driver.findElement(By.id("twotabsearchtextbox"));
        searchString.sendKeys("selenium");
        searchString.submit();
    }

    @After
    public void afterTest(){
        driver.quit();
        driver = null;
    }

}
