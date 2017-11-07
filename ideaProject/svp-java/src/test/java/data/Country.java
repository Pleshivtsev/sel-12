package data;

public class Country {

    private String name;
    private String url;
    Integer zonesNumber; // В итоге отказался от Integer, так как кастинг типов работает очень медленно

    public Country(String name, String url, Integer zonesNumber){
        this.name = name;
        this.url = url;
        this.zonesNumber = zonesNumber;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Integer getZonesNumber() {
        return zonesNumber;
    }
}