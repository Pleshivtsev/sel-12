package lesson6Actions;

import appSettings.AppSettings;
import org.openqa.selenium.WebDriver;

public class Task11NewUserRegistration extends TestBase3 {

//----------------------------------------------------------------------------------------------------------------------
//--- Test Service methods
    private void openHomePage(WebDriver driver){
        driver.navigate().to(AppSettings.frontEndUrl);
    }


}
