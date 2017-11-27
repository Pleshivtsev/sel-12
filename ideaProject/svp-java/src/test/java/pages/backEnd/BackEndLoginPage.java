package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndLoginPage {

//------ Locators
    public static By userNameInputLocator = By.name("username");
    public static By userPasswordInputLocator = By.name("password");
    public static By loginButtonLocator = By.name("login");

//------ Settings

    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;

}
