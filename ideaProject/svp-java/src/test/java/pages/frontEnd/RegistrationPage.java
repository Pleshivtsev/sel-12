package pages.frontEnd;

import org.openqa.selenium.By;

public class RegistrationPage {
//------ Locators
    public static By createAccountButtonLocator = By.cssSelector("button[name=create_account]");

    //Input fields
    public static By firstNameInputLocator = By.name("firstname");
    public static By lastNameInputLocator = By.name("lastname");
    public static By address1InputLocator = By.name("address1");
    public static By postcodeInputLocator = By.name("postcode");
    public static By cityInputLocator = By.name("city");

    public static By countrySelectorLocator = By.cssSelector("span.select2-selection__rendered");
    public static By countrySearchInputLocator = By.cssSelector("input.select2-search__field");

    public static By stateSelectorLocator = By.cssSelector("select[name=zone_code]");

    public static By emailInputLocator = By.name("email");
    public static By phoneInputLocator = By.name("phone");

    public static By passwordInputLocator = By.name("password");
    public static By confirmedPasswordInputLocator = By.name("confirmed_password");

    //------ Settings
    public static String header = "Create Account";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}