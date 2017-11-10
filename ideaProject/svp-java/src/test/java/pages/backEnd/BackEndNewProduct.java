package pages.backEnd;

import org.openqa.selenium.By;

public class BackEndNewProduct{
//------ Locators----------------

    // General tab
    public static By nameLocator = By.cssSelector("input[name*=name]");
    public static By codeLocator = By.cssSelector("input[name=code]");
    public static By categoryLocator = By.cssSelector("input[data-name=Selenium12]");
    public static By defaultCategorySelectLocator = By.cssSelector("select[name=default_category_id]");
    public static By groupsLocator = By.cssSelector("input[name*=product_groups][value='1-1']");
    public static By quantityLocator = By.cssSelector("input[name=quantity]");
    public static By imageFileLocator = By.cssSelector("input[type=file]");
    public static By dateValidFromLocator = By.cssSelector("input[name=date_valid_from]");
    public static By dateValidToLocator = By.cssSelector("input[name=date_valid_to]");
    public static By saveButton = By.cssSelector("button[name=save]");

    // Information tab
    public static By informationTabLocator = By.linkText("Information");
    public static By manufacturerSelectLocator = By.cssSelector("select[name=manufacturer_id]");
    public static By keywordsLocator = By.cssSelector("input[name=keywords]");
    public static By shortDescriptionLocator = By.cssSelector("input[name*=short_description]");
    public static By descriptionLocator = By.cssSelector("div.trumbowyg-editor");
    public static By headTitleLocator = By.cssSelector("input[name*=head_title]");
    public static By metaDescriptionLocator = By.cssSelector("input[name*=meta_description]");

    //Prices tab
    public static By pricesTabLocator = By.linkText("Prices");
    public static By priceLocator = By.cssSelector("input[name=purchase_price]");
    public static By priceCurrencySelectLocator = By.cssSelector("select[name=purchase_price_currency_code]");
    public static By usdTaxLocator = By.cssSelector("input[name*='gross_prices[USD]']");
    public static By eurTaxLocator = By.cssSelector("input[name*='gross_prices[EUR]']");

//------ Settings---------------
    public static String header = "Add New Product";
    public static Integer pageTimeout = 10;
    public static Integer elementsTimeout = 10;
}