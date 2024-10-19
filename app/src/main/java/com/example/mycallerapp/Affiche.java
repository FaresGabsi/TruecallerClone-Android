package com.example.mycallerapp;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Affiche extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Contact> data = new ArrayList<Contact>();
    ImageView iv_back_Affiche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        // Initialize views
        rv = findViewById(R.id.rv_contact);
        iv_back_Affiche = findViewById(R.id.iv_back_Ajout);
        iv_back_Affiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        data.add(new Contact(1, "Fares", "22509369"));
        data.add(new Contact(2, "Yosra", "22509369"));
        data.add(new Contact(2, "Wassim", "22509369"));
        data.add(new Contact(1, "Fares", "22509369"));
        data.add(new Contact(2, "Yosra", "22509369"));
        data.add(new Contact(2, "Wassim", "22509369"));
        data.add(new Contact(1, "Fares", "22509369"));
        data.add(new Contact(2, "Yosra", "22509369"));
        data.add(new Contact(2, "Wassim", "22509369"));
        data.add(new Contact(1, "Fares", "22509369"));
        data.add(new Contact(2, "Yosra", "22509369"));
        data.add(new Contact(2, "Wassim", "22509369"));

        MyContactRecyclerAdapter ad = new MyContactRecyclerAdapter(Affiche.this, data);
        LinearLayoutManager manager = new LinearLayoutManager(Affiche.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(ad);
    }
}
