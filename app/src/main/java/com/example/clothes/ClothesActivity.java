package com.example.clothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothes.adapters.ClothAdapter;
import com.example.clothes.ressources.BodyPart;
import com.example.clothes.ressources.Cloth;
import com.example.clothes.ressources.User;
import com.example.vetements_test.R;

import java.util.ArrayList;
import java.util.List;

public class ClothesActivity extends AppCompatActivity {
    private EditText inputTemperature;
    private GridView suggestionsList;
    private Button validationButton;
    private Button chooseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_activity);

        this.inputTemperature = findViewById(R.id.input_temperature);
        this.suggestionsList = findViewById(R.id.grid_view);
        this.validationButton = findViewById(R.id.button_validation);
        this.chooseButton = findViewById(R.id.button_choose);
        User user = new User();

        this.chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),ChooseClothesActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

        this.validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ClothesActivity.this, "casse les couilles", Toast.LENGTH_SHORT).show();
                if(inputTemperature.getText().length() == 0) {
                    suggestionsList.setAdapter(null);
                    Toast.makeText(ClothesActivity.this, "You write nothing", Toast.LENGTH_SHORT).show();
                }
                else {
                    int temperature = Integer.parseInt(inputTemperature.getText().toString());
                    List<Cloth> list = new ArrayList<Cloth>();

                    for(Cloth cloth : user.getClothes()) {
                        if (cloth.getMinTemperature() <= temperature && temperature <= cloth.getMaxTemperature()) {
                            list.add(cloth);
                        }
                    }
                    suggestionsList.setAdapter(new ClothAdapter(ClothesActivity.this,list));
                }
            }
        });
    }
}