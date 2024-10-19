package com.example.mycallerapp;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Ajout extends AppCompatActivity {
    EditText ed_nom, ed_numTel;
    Button btn_annuler, btn_ajouter;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        ed_nom=findViewById(R.id.et_nom_Ajout);
        ed_numTel=findViewById(R.id.et_tel_Ajout);
        btn_ajouter=findViewById(R.id.btn_ajouter_Ajout);
        btn_annuler=findViewById(R.id.btn_annuler_Ajout);
        btn_back=findViewById(R.id.iv_back_Ajout);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_nom.setText("");
                ed_numTel.setText("");
            }
        });

        ed_numTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!charSequence.toString().matches("\\d+")){
                        ed_numTel.setError("Numéro de téléphone doit être un nombre");
                    }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactManager manager = new ContactManager(Ajout.this);
                manager.ouvrir();

                String nom = ed_nom.getText().toString().trim();
                String tel = ed_numTel.getText().toString().trim();

                // Check if the contact already exists in the database
                if (manager.contactExists(nom, tel)) {
                    // Show AlertDialog if contact already exists
                    new AlertDialog.Builder(Ajout.this)
                            .setTitle("Contact Existe")
                            .setMessage("Un contact avec ce nom et ce numéro existe déjà.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                } else {
                    long a = manager.ajout(nom, tel);
                    Toast.makeText(Ajout.this, "Contact ajouté avec succès", Toast.LENGTH_SHORT).show();
                }

                manager.fermer();
            }
        });


    }
}