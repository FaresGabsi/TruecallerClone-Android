package com.example.mycallerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button btnquitter, btnvalider;
    EditText ednom, edpwd;
    TextView tv_signup;
    CheckBox checkboxStayConnected;
    UserManager userManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnquitter = findViewById(R.id.btnquitter_auth);
        btnvalider = findViewById(R.id.btnvalider_auth);
        edpwd = findViewById(R.id.edpwd_auth);
        ednom = findViewById(R.id.ednom_auth);
        tv_signup = findViewById(R.id.tv_singup_Main);
        checkboxStayConnected = findViewById(R.id.cb_stay_auth);


        userManager = new UserManager(MainActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean stayConnected = sharedPreferences.getBoolean("stayConnected", false);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (stayConnected && savedUsername != null && savedPassword != null) {

            Intent i = new Intent(MainActivity.this, Acceuil.class);
            i.putExtra("USER", savedUsername);
            startActivity(i);
            finish();
            return;
        }


        tv_signup.setText(Html.fromHtml("vous n'avez pas un compte? <u>inscrivez vous</u>"));
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                MainActivity.this.startActivity(i);
            }
        });

        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edpwd.setText("");
                ednom.setText("");
            }
        });

        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ednom.getText().toString().trim();
                String pwd = edpwd.getText().toString().trim();

                if (nom.isEmpty() || pwd.isEmpty()) {
                    showAlertDialog("Erreur", "Veuillez remplir tous les champs");
                    return;
                }

                userManager.ouvrir();
                if (userManager.userExists(nom, pwd)) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (checkboxStayConnected.isChecked()) {
                        editor.putBoolean("stayConnected", true);
                        editor.putString("username", nom);
                        editor.putString("password", pwd);
                    } else {
                        editor.clear();
                    }
                    editor.apply();


                    Intent i = new Intent(MainActivity.this, Acceuil.class);
                    i.putExtra("USER", nom);
                    MainActivity.this.startActivity(i);
                    finish();
                } else {

                    showAlertDialog("Erreur", "Identifiants non valides");
                }
            }
        });
    }


    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
