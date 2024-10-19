package com.example.mycallerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ContactAdapter<Contact> extends BaseAdapter {
    ArrayList<com.example.mycallerapp.Contact> data;
    Context con;

    public ContactAdapter(Context con,ArrayList<com.example.mycallerapp.Contact> data) {
        this.data = data;
        this.con = con;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //Creation d'un View
        CardView l=null;
        LayoutInflater inf=LayoutInflater.from(con);
        l=(CardView) inf.inflate(R.layout.element,null);
        // Recuperation des holders
        TextView tv_idContact=l.findViewById(R.id.tv_idContact);
        TextView tv_nomContact=l.findViewById(R.id.tv_nomContact);
        TextView tv_numtelContact=l.findViewById(R.id.tv_numtelContact);
        ImageView imgview_call_Contact=l.findViewById(R.id.imgview_call_Contact);
        ImageView imgview_delete_Contact=l.findViewById(R.id.imgview_delete_Contact);
        ImageView imgview_edit_Contact=l.findViewById(R.id.imgview_edit_Contact);
        // Affectation des Holders
         com.example.mycallerapp.Contact c=data.get(position);
         tv_idContact.setText(""+c.getId());
         tv_nomContact.setText(""+c.getNom());
         tv_numtelContact.setText(""+c.getNumtel());
         imgview_call_Contact.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String tel=c.getNumtel();
                 Intent callIntent = new Intent(Intent.ACTION_DIAL);  // Use ACTION_DIAL to open dialer
                 callIntent.setData(Uri.parse("tel:" + tel));
                 con.startActivity(callIntent);
             }
         });
         imgview_delete_Contact.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder alertDialog= new AlertDialog.Builder(con);
               alertDialog.setTitle("Delete Contact");
               alertDialog.setMessage("Are you sure you want to delete this contact ?");
               alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       data.remove(position);
                       Toast.makeText(con, "Contact deleted", Toast.LENGTH_SHORT).show();
                       dialogInterface.dismiss();
                       notifyDataSetChanged();
                   }
               });
               alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                   }
               });

        alertDialog.show();

             }
         });
         imgview_edit_Contact.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });
        System.out.println(data);
        return l;
    }
}
