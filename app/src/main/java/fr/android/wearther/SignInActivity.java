package fr.android.wearther;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.android.wearther.data.DataBaseHelper;
import fr.android.wearther.data.User;

public class SignInActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button sign_in;
    private Button go_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.login = this.findViewById(R.id.input_pseudo_sign_in);
        this.password = this.findViewById(R.id.input_password_sign_in);
        this.sign_in = this.findViewById(R.id.btn_sign_in);
        this.go_to_login = this.findViewById(R.id.btn_go_to_login);

        this.sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(SignInActivity.this);

                if(dataBaseHelper.addOne(new User(-1,login.getText().toString(),password.getText().toString()))) {
                    Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(otherActivity);
                    //finish();
                }
                else {
                    // TODO : Erreur
                }
            }
        });

        this.go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }
}
