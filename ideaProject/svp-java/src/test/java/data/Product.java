package data;

public class Product {

    private String name;
    private String regularPrice;
    private String campaignPrice;
    private String manufacturer;

    public Product(String name){
        this.name = name;
    }

// Геттеры
// --------------------------------------------------------------

    public String getName() {
        return name;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public String getCampaignPrice() {
        return campaignPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

// Сеттеры
// --------------------------------------------------------------
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
        return this;
    }

    public Product setCampaignPrice(String campaignPrice) {
        this.campaignPrice = campaignPrice;
        return this;
    }

    public Product setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }
}