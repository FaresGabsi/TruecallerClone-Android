package com.example.mycallerapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Acceuil extends AppCompatActivity {
    private TextView tusername;
    private Button btnajout;
    private Button btnaffiche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        tusername=findViewById(R.id.tv_acceuil);
        btnajout=findViewById(R.id.btn_ajout);
        btnaffiche=findViewById(R.id.btn_affiche);
        Intent x=this.getIntent();
        Bundle b=x.getExtras();
        String u=b.getString("USER");
        tusername.setText("Acceuil de M. "+u.toUpperCase());
        btnaffiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Acceuil.this,Affiche.class);
                Acceuil.this.startActivity(i);
            }
        });
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Acceuil.this,Ajout.class);
                Acceuil.this.startActivity(i);
            }
        });
    }
}