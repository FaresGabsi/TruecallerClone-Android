package com.example.mycallerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyContactRecyclerAdapter extends RecyclerView.Adapter<MyContactRecyclerAdapter.MyViewHolder> {
    Context con;
    ArrayList<Contact> data;
    public MyContactRecyclerAdapter(Context con, ArrayList<Contact> data) {
        this.con = con;
        this.data = data;
    }


    @NonNull
    @NotNull
    @Override
    public MyContactRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //Creation des view hholder selon l'ecran +2
        CardView v=null;
        LayoutInflater inf=LayoutInflater.from(con);
        v=(CardView) inf.inflate(R.layout.element,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyContactRecyclerAdapter.MyViewHolder holder, int position) {
        Contact c=data.get(position);
        holder.tv_idContact.setText(""+c.getId());
        holder.tv_nomContact.setText(""+c.getNom());
        holder.tv_numtelContact.setText(""+c.getNumtel());
        holder.imgview_call_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel=c.getNumtel();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + tel));
                con.startActivity(callIntent);
            }
        });
        holder.imgview_delete_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(con);
                alertDialog.setTitle("Supprimer le contact");
                alertDialog.setMessage("Êtes-vous sûr de vouloir supprimer ce contact ?");
                alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = data.get(position).getId(); // Use the correct method to get the contact ID
                        ContactManager manager = new ContactManager(con);
                        manager.ouvrir();
                        manager.supprimer(id); // Delete contact from database

                        // Clear current data and fetch updated contact list
                        data.clear();
                        data.addAll(manager.getAllContacts()); // Refresh contacts
                        Toast.makeText(con, "Contact supprimé", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged(); // Notify adapter of data changes
                        manager.fermer(); // Close the database
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

        holder.imgview_edit_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder editDialogBuilder = new AlertDialog.Builder(con);
                LayoutInflater inflater = LayoutInflater.from(con);
                View editView = inflater.inflate(R.layout.edit_contact_dialog, null);
                editDialogBuilder.setView(editView);

                EditText etEditName = editView.findViewById(R.id.et_edit_name);
                EditText etEditPhone = editView.findViewById(R.id.et_edit_phone);

                etEditName.setText(c.getNom());
                etEditPhone.setText(c.getNumtel());

                editDialogBuilder.setTitle("Modifier le contact");
                editDialogBuilder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog.Builder confirmDialog = new AlertDialog.Builder(con);
                        confirmDialog.setTitle("Confirmer la modification");
                        confirmDialog.setMessage("Êtes-vous sûr de vouloir enregistrer les modifications ?");
                        confirmDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String newName = etEditName.getText().toString();
                                String newPhone = etEditPhone.getText().toString();

                                // Update the contact in the database
                                ContactManager manager = new ContactManager(con);
                                manager.ouvrir();
                                int contactId = c.getId(); // Assuming c has a method to get the ID
                                manager.modifier(contactId, newName, newPhone); // Update contact in the database
                                manager.fermer();

                                // Update the contact in your local data
                                c.setNom(newName);
                                c.setNumtel(newPhone);

                                notifyDataSetChanged();
                                Toast.makeText(con, "Contact mis à jour", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                        confirmDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        confirmDialog.show();
                        dialogInterface.dismiss();
                    }
                });

                editDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                editDialogBuilder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_idContact;
        TextView tv_nomContact;
        TextView tv_numtelContact;
        ImageView imgview_call_Contact;
        ImageView imgview_delete_Contact;
        ImageView imgview_edit_Contact;
        public MyViewHolder(@NonNull @NotNull View l) {
            super(l);
           tv_idContact=l.findViewById(R.id.tv_idContact);
           tv_nomContact=l.findViewById(R.id.tv_nomContact);
           tv_numtelContact=l.findViewById(R.id.tv_numtelContact);
            imgview_call_Contact=l.findViewById(R.id.imgview_call_Contact);
            imgview_delete_Contact=l.findViewById(R.id.imgview_delete_Contact);
            imgview_edit_Contact=l.findViewById(R.id.imgview_edit_Contact);

        }
    }
}
