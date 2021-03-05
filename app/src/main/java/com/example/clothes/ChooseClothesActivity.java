package com.example.clothes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothes.adapters.ClothAdapter;
import com.example.clothes.ressources.BodyPart;
import com.example.clothes.ressources.Cloth;
import com.example.clothes.ressources.User;
import com.example.clothes.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseClothesActivity extends AppCompatActivity {
    private ListView listView;
    private Button retourButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_clothes);

        this.listView = findViewById(R.id.list_clothes);
        this.retourButton = findViewById(R.id.button_return);

        this.retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),ClothesActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
        
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                Cloth user = (Cloth) listView.getItemAtPosition(position);
                user.setChecked(!currentCheck);
            }
        });

        this.initListViewData();
    }

    private void initListViewData()  {
        ArrayList<Cloth> clothes = new ArrayList<Cloth>();
        clothes.add(new Cloth("bonnet", BodyPart.tete,-10,10));
        clothes.add(new Cloth("echarpe",BodyPart.cou,-10,10));
        clothes.add(new Cloth("tshirt", BodyPart.haut,20,40));
        clothes.add(new Cloth("pull", BodyPart.haut,0,20));
        clothes.add(new Cloth("manteaux", BodyPart.haut,0,20));
        clothes.add(new Cloth("gant", BodyPart.mains,-10,10));
        clothes.add(new Cloth("short", BodyPart.jambes,20,30));
        clothes.add(new Cloth("pantalon", BodyPart.jambes,0,20));
        clothes.add(new Cloth("chaussure", BodyPart.pieds,10,30));
        clothes.add(new Cloth("botte", BodyPart.pieds,-10,10));

        // android.R.layout.simple_list_item_checked:
        // ListItem is very simple (Only one CheckedTextView).
        ArrayAdapter<Cloth> arrayAdapter
                = new ArrayAdapter<Cloth>(this, android.R.layout.simple_list_item_checked , clothes);

        this.listView.setAdapter(arrayAdapter);

        for(int i=0;i< clothes.size(); i++ )  {
            this.listView.setItemChecked(i,clothes.get(i).isChecked());
        }
    }

}