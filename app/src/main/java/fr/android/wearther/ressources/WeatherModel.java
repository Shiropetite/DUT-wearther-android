package fr.android.wearther.ressources;

public class WeatherModel {
    private int id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private float min_temp;
    private float max_temp;
    private float temp;
    private float wind_speed;
    private int humidity;

    public WeatherModel() { }

    public WeatherModel(int id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, float min_temp, float max_temp, float temp, float wind_speed, int humidity) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.temp = temp;
        this.wind_speed = wind_speed;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "id=" + id +
                ", weather_state_name='" + weather_state_name + '\'' +
                ", weather_state_abbr='" + weather_state_abbr + '\'' +
                ", wind_direction_compass='" + wind_direction_compass + '\'' +
                ", min_temp=" + min_temp +
                ", max_temp=" + max_temp +
                ", temp=" + temp +
                ", wind_speed=" + wind_speed +
                ", humidity=" + humidity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
