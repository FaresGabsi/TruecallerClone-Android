package com.example.mycallerapp;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Affiche extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Contact> data=new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);
        rv=findViewById(R.id.rv_contact);
        data.add(new Contact(1,"Fares","22509369"));
        data.add(new Contact(2,"Yosra","22509369"));
        data.add(new Contact(2,"Wassim","22509369"));
        data.add(new Contact(1,"Fares","22509369"));
        data.add(new Contact(2,"Yosra","22509369"));
        data.add(new Contact(2,"Wassim","22509369"));
        data.add(new Contact(1,"Fares","22509369"));
        data.add(new Contact(2,"Yosra","22509369"));
        data.add(new Contact(2,"Wassim","22509369"));
        data.add(new Contact(1,"Fares","22509369"));
        data.add(new Contact(2,"Yosra","22509369"));
        data.add(new Contact(2,"Wassim","22509369"));
        //ContactAdapter<Contact> ad=new ContactAdapter<>(Affiche.this,data);
        MyContactRecyclerAdapter ad=new MyContactRecyclerAdapter(Affiche.this,data);
        LinearLayoutManager manager=new LinearLayoutManager(Affiche.this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);
        rv.setAdapter(ad);

    }
}