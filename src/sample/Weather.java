package sample;

public class Weather {

    private String name;
    private String country;
    private double temp;
    private long humidity;
    private double lon;
    private double lat;
    private long visibility;
    private long sunrise;
    private long sunset;
    private double windSpeed;

    public Weather(String name, String country, double temp, long humidity, double lon, double lat, long visibility, long sunrise, long sunset, double windSpeed) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
        this.visibility=visibility;
        this.sunrise = sunrise;
        this.sunset= sunset;
        this.windSpeed = windSpeed;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public long getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public long getHumidity() {
        return humidity;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
