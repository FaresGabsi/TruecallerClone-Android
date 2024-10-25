package com.example.mycallerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {
    private TextView tusername;
    private Button btnajout;
    //private Button btnaffiche;
    RecyclerView rv;
    ArrayList<Contact> data, fileredList = new ArrayList<Contact>();
    EditText et_Acceuil;
    MyContactRecyclerAdapter ad;
    ImageView img_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        tusername=findViewById(R.id.tv_acceuil);
        btnajout=findViewById(R.id.btn_ajout);
//        btnaffiche=findViewById(R.id.btn_affiche);
        rv = findViewById(R.id.rv_contact);
        Intent x=this.getIntent();
        Bundle b=x.getExtras();
        String u=b.getString("USER");
        tusername.setText("Acceuil de M. "+u.toUpperCase());
        et_Acceuil=findViewById(R.id.et_search_Acceuil);
        img_logout=findViewById(R.id.img_logout);


        ContactManager contactManager=new ContactManager(Acceuil.this);
        contactManager.ouvrir();
        data=contactManager.getAllContacts();
        fileredList.addAll(data);

        ad = new MyContactRecyclerAdapter(Acceuil.this, fileredList);
        LinearLayoutManager manager = new LinearLayoutManager(Acceuil.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(ad);
        contactManager.fermer();
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Acceuil.this,Ajout.class);
                Acceuil.this.startActivity(i);
            }
        });

        et_Acceuil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent i=new Intent(Acceuil.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
    private void filter(String ch){
        fileredList.clear();
        if(ch.isEmpty())
            fileredList.addAll(data);
        else{
            for(Contact c : data){
                if(c.getNom().toLowerCase().contains(ch.toLowerCase()) || c.getNumtel().toLowerCase().contains(ch.toLowerCase()))
                    fileredList.add(c);
            }
        }


        ad.notifyDataSetChanged();
    }

}