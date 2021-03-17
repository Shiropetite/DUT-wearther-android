package fr.android.wearther;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.android.wearther.data.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private Button connexion;
    private Button go_to_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.login = this.findViewById(R.id.input_pseudo_login);
        this.password = this.findViewById(R.id.input_password_login);
        this.connexion = this.findViewById(R.id.btn_connexion);
        this.go_to_sign_in = this.findViewById(R.id.btn_go_to_sign_in);

        this.connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);

                if(dataBaseHelper.getUser(login.getText().toString(),password.getText().toString()) != null) {
                    Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(otherActivity);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.go_to_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }

}
