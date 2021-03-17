package fr.android.wearther.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.android.wearther.R;
import fr.android.wearther.data.BodyPart;
import fr.android.wearther.data.Cloth;
import fr.android.wearther.data.DataBaseHelper;
import fr.android.wearther.data.User;
import fr.android.wearther.ressources.ClothAdapter;
import fr.android.wearther.ressources.WeatherDataService;
import fr.android.wearther.ressources.WeatherModel;

import static android.content.Context.LOCATION_SERVICE;

public class DashboardFragment extends Fragment implements LocationListener {

    private Context context;
    private LocationManager locationManager;
    private User user;
    private GridView suggestionsList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        this.context = getContext();
        this.suggestionsList = root.findViewById(R.id.glist_cloth);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        this.user = dataBaseHelper.getUser("default","default");

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
                    float temperature = Float.parseFloat(weatherModel.getTemp() + "");

                    List<Cloth> list = new ArrayList<Cloth>();

                    for(Cloth cloth : user.getClothes()) {
                        if (cloth.getMinTemperature() <= temperature && temperature <= cloth.getMaxTemperature()) {
                            list.add(cloth);
                        }
                    }
                    suggestionsList.setAdapter(new ClothAdapter(context,list));

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