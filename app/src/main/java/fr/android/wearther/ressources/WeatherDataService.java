package fr.android.wearther.ressources;

import android.content.Context;
import android.media.Image;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String QUERY_CITY = "https://www.metaweather.com/api/location/search/?lattlong=";
    public static final String QUERY_WEATHER_BY_CITY_ID = "https://www.metaweather.com/api/location/";
    public static final String QUERY_WEATHER_ICONE = "https://www.metaweather.com/static/img/weather/png/64/";

    private Context context;
    private String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface ForeCastByIDResponse {
        void onError(String message);
        void onResponse(WeatherModel weatherModel);
    }

    public void getWeatherByLatLong(double latitude, double longitude, ForeCastByIDResponse volleyResponseListener) {
        String url = QUERY_CITY + latitude + "," + longitude;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getWeather(cityID, new ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        volleyResponseListener.onError("Someting wrong");
                    }

                    @Override
                    public void onResponse(WeatherModel weatherModel) {
                        volleyResponseListener.onResponse(weatherModel);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Someting wrong");
            }
        });

        // Add the request to the RequestQueue.
        Singleton.getInstance(context).addToRequestQueue(request);
    }

    private void getWeather(String cityID,ForeCastByIDResponse foreCastByIDResponse) {

        List<WeatherModel> weatherReportModels = new ArrayList<>();

        String url = QUERY_WEATHER_BY_CITY_ID + cityID + "/";

        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");

                    WeatherModel weather = new WeatherModel();
                    JSONObject first_day_from_api = (JSONObject) consolidated_weather_list.get(0);

                    weather.setId(first_day_from_api.getInt("id"));
                    weather.setWeather_state_name(first_day_from_api.getString("weather_state_name"));
                    weather.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
                    weather.setWind_direction_compass(first_day_from_api.getString("wind_direction_compass"));
                    weather.setMin_temp(first_day_from_api.getLong("min_temp"));
                    weather.setMax_temp(first_day_from_api.getLong("max_temp"));
                    weather.setTemp(first_day_from_api.getLong("the_temp"));
                    weather.setWind_speed(first_day_from_api.getLong("wind_speed"));
                    weather.setHumidity(first_day_from_api.getInt("humidity"));

                    foreCastByIDResponse.onResponse(weather);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                foreCastByIDResponse.onError(error.getMessage());
            }
        });

        Singleton.getInstance(context).addToRequestQueue(request);

    }


    public interface IconeResponse {
        void onError(String message);
        void onResponse(Image weatherIcone);
    }

    public static String getWeatherIcone(String weather_abbr) {
        return QUERY_WEATHER_ICONE + weather_abbr + ".png";
    }

}
