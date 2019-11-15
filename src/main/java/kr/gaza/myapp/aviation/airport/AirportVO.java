package kr.gaza.myapp.aviation.airport;

public class AirportVO {
    private int airportName;
    private String IATA;
    private String city;
    private String continent;
    private String latitude;
    private String longitude;
    private String cityImg;

    public int getAirportName() {
        return airportName;
    }

    public void setAirportName(int airportName) {
        this.airportName = airportName;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCityImg() {
        return cityImg;
    }

    public void setCityImg(String cityImg) {
        this.cityImg = cityImg;
    }
}