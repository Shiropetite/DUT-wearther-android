package fr.davenese.wearther;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mHourText,mdateText,mDegreesText,mWindSpeedText,
            mHumidityText,mWearText;
    private ImageView weatherImage,coldimage, goodImage,hotImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        //Initialisation des composants graphiques
        this.mHourText = (TextView) findViewById(R.id.hour_textView);
        this.mdateText = (TextView) findViewById(R.id.date_textView);
        this.mDegreesText = (TextView) findViewById(R.id.tempDegrees_textView);
        this.mWindSpeedText = (TextView) findViewById(R.id.windSpeed_textView);
        this.mHumidityText = (TextView) findViewById(R.id.humidity_textView);
        this.mWearText = (TextView) findViewById(R.id.wear_textView3);

        //Initialisation des composants graphiques
        this.weatherImage = (ImageView) findViewById(R.id.weather_imageView);
        this.coldimage = (ImageView) findViewById(R.id.cold_ImageView);
        this.goodImage = (ImageView) findViewById(R.id.weather_imageView);
        this.hotImage = (ImageView) findViewById(R.id.hot_ImageView);




    }
}