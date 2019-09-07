package framework.tools.utils.dataprovider;

public enum LoginDataProvider {

    DEPARTURE_CITY("test@test.pl"),
    ARRIVAL_CITY("testing");

    private final String cityName;

    LoginDataProvider(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
