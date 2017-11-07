package data;

public class GeoZone {

    private String name;
    private String url;

    public GeoZone(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
