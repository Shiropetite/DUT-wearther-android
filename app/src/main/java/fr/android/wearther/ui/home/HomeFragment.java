package fr.android.wearther.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import fr.android.wearther.R;
import fr.android.wearther.data.Cloth;
import fr.android.wearther.data.DataBaseHelper;
import fr.android.wearther.data.User;
import fr.android.wearther.ressources.WeatherDataService;
import fr.android.wearther.ressources.WeatherModel;

import static android.content.Context.LOCATION_SERVICE;

public class HomeFragment extends Fragment implements LocationListener{

    private TextView tv_temperature;
    private TextView tv_wind_speed;
    private TextView tv_humidity;
    private TextView tv_information;
    private ImageView image_info;
    private ImageView icone;
    private Context context;
    private LocationManager locationManager;
    public static float temperature;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView tv_date = root.findViewById(R.id.tv_date);
        final TextView tv_heure = root.findViewById(R.id.tv_heure);

        this.icone = root.findViewById(R.id.weather_icone);
        this.tv_temperature = root.findViewById(R.id.tv_temperature);
        this.tv_wind_speed = root.findViewById(R.id.tv_wind_speed);
        this.tv_humidity = root.findViewById(R.id.tv_humidity);
        this.tv_information = root.findViewById(R.id.tv_information);
        this.image_info = root.findViewById(R.id.image_info);
        this.context = getContext();

        // date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE).format(calendar.getTime());

        // heure
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.FRANCE);
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        simpleDateFormat.setTimeZone(timeZone);
        String currentDateTime = simpleDateFormat.format(calendar.getTime());

        String maj = currentDate.charAt(0) + "";
        tv_date.setText(maj.toUpperCase() + currentDate.substring(1));
        tv_heure.setText(currentDateTime);

        // temps
        getLocation();

        return root;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            WeatherDataService weatherDataService = new WeatherDataService(context);
            weatherDataService.getWeatherByLatLong(location.getLatitude(), location.getLongitude(), new WeatherDataService.ForeCastByIDResponse() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse(WeatherModel weatherModel) {
                    temperature = weatherModel.getTemp();
                    tv_temperature.setText(weatherModel.getTemp() + " °C");
                    int wind_speed = (int) (weatherModel.getWind_speed() * 1609.34) / 1000;
                    tv_wind_speed.setText(wind_speed + " km/h");
                    tv_humidity.setText(weatherModel.getHumidity() + " %");

                    String weather = weatherModel.getWeather_state_abbr();
                    String image_url = WeatherDataService.getWeatherIcone(weather);
                    Picasso.get().load(image_url).into(icone);

                    int reaId;
                    switch(weatherModel.getWeather_state_abbr()) {
                        case "sn":
                        case "sl":
                            tv_information.setText("Attention à ne pas glisser !");
                            reaId = context.getResources().getIdentifier("slip","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            break;
                        case "h":
                            tv_information.setText("Prenez un parapluie pour la grêle");
                            reaId = context.getResources().getIdentifier("umbrella","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            break;
                        case "t":
                            tv_information.setText("Pas de parapluie ! On ne voudrais pas se prendre un éclair !");
                            reaId = context.getResources().getIdentifier("thunderbolt","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            break;
                        case "hr":
                        case "lr":
                        case "s":
                            reaId = context.getResources().getIdentifier("umbrella","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            tv_information.setText("Prenez un parapluie il pleut !");
                            break;
                        case "c":
                            tv_information.setText("Prenez vos lunettes de soleil !");
                            reaId = context.getResources().getIdentifier("sunglasses","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            break;
                        default:
                            tv_information.setText("Une journée normale");
                            reaId = context.getResources().getIdentifier("cloud","drawable", context.getPackageName());
                            image_info.setImageResource(reaId);
                            break;
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}