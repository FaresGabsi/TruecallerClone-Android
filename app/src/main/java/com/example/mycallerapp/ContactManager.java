package com.example.mycallerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db=null;
    Context con;
    ContactManager(Context con){
        this.con=con;

    }
    public void ouvrir(){
        DBHandler handler = new DBHandler(con,"mabase.db",null,1);
        db=handler.getWritableDatabase();
    }
    public long ajout(String nom, String numTel){
        long a=0;
        ContentValues values=new ContentValues();
        values.put(DBHandler.col_nom,nom);
        values.put(DBHandler.col_num,numTel);
        a= db.insert(DBHandler.table_contact,null,values );
        return a;
    }

    public boolean contactExists(String nom, String tel) {
        Cursor cursor = db.query(DBHandler.table_contact,
                new String[]{DBHandler.col_id},  // Columns to select
                DBHandler.col_nom + "=? AND " + DBHandler.col_num + "=?",
                new String[]{nom, tel},           // Selection args
                null, null, null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close(); // Close the cursor
        return exists;
    }

    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> l= new ArrayList<Contact>();
        Cursor cr=db.query(DBHandler.table_contact,new String[]
                        {
                                DBHandler.col_id,
                                DBHandler.col_nom,
                                DBHandler.col_num
                        }
                ,null,null,null,null,null);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            int i1 = cr.getInt(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            l.add(new Contact(i1, i2, i3));
            cr.moveToNext();
        }
        cr.close();
        return l;
    }
    public void supprimer(int id){
        db.delete(DBHandler.table_contact, DBHandler.col_id + " = ?", new String[]{String.valueOf(id)});

    }
    public void fermer() {
        if (db != null && db.isOpen()) {
            db.close(); // Close the database connection if it’s open
        }
    }

    public void modifier(int contactId, String newName, String newPhone) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.col_nom, newName);
        values.put(DBHandler.col_num, newPhone);

        // Update the contact in the database where the ID matches
        db.update(DBHandler.table_contact, values, DBHandler.col_id + " = ?", new String[]{String.valueOf(contactId)});
    }

}
