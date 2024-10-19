package com.example.mycallerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
