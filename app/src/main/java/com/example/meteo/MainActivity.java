package com.example.meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    private EditText et_dataInput;
    private ListView lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigne values to each controls in the layout
        this.btn_cityID = findViewById(R.id.btn_getCityID);
        this.btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        this.btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        this.et_dataInput = findViewById(R.id.et_dataInput);
        this.lv_weatherReports = findViewById(R.id.lv_weatherReports);

        // click listeners for each button
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, cityID, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModelList) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModelList);
                        lv_weatherReports.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You typed "
                                + et_dataInput.getText().toString() ,Toast.LENGTH_SHORT).show();
            }
        });

    }
}