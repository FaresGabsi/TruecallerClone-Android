package com.example.mycallerapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public static final String table_contact="Contact";
    public static final String col_nom="Nom";
    public static final String col_num="Num";
    public static final String col_id="id";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+ table_contact+" ("
                + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col_nom + " TEXT not null, "
                + col_num + " TEXT not null )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_contact);
        onCreate(db);
    }
}
