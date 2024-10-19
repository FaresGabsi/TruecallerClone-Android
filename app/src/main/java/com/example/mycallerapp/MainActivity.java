package com.example.mycallerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button btnquitter,btnvalider;
    EditText ednom,edpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recuperation des composantes
        btnquitter=findViewById(R.id.btnquitter_auth);
        btnvalider=findViewById(R.id.btnvalider_auth);
        edpwd=findViewById(R.id.edpwd_auth);
        ednom=findViewById(R.id.ednom_auth);
        //Evenement
        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent();
//                i.setAction(Intent.ACTION_CALL);
//                i.setData()
                String nom=ednom.getText().toString();
                String pwd=edpwd.getText().toString();
                if(nom.equals("fares")&& pwd.equals("123")){
                    Intent i=new Intent(MainActivity.this,Acceuil.class);
                    i.putExtra("USER",nom);
                    MainActivity.this.startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this,"valuer non valide",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}