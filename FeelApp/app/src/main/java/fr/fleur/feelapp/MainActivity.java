package fr.fleur.feelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    ImageView Froid,Bien,Chaud;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;

        // Recherche des vues
        Froid = findViewById(R.id.cold_ImageView);
        Bien = findViewById(R.id.good_ImageView);
        Chaud = findViewById(R.id.hot_ImageView);

        Bien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder Adequat = new AlertDialog.Builder(activity);
                Adequat.setTitle("Vos vêtements vous ont convenus ? Super !");
                Adequat.setMessage("Cliquez sur le bouton pour valider votre choix");
                Adequat.setPositiveButton("Valider", (dialog, which) -> Toast.makeText(getApplicationContext(), "Vous avez apprécié les vêtements du jour", Toast.LENGTH_LONG).show());

                Adequat.show();
            }
        });
    }





}