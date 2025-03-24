package com.example.ahbmusic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    public DataBaseSQL(@Nullable Context context) {
        super(context, "audio", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE media (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, url TEXT)");

        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Titulo1', 'url1')");
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Titulo2', 'url2')");
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Titulo3', 'url3')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> obtenerMedia() {
        ArrayList<String> canciones = new ArrayList<String>(); //Array de canciones

        SQLiteDatabase db = this.getReadableDatabase(); //Referenciamos a la base de datos

        Cursor cursor = db.rawQuery("SELECT * FROM media", null); //Estructura para almacenar los datos de la base de datos

        if (cursor != null) { //Si no está vacío, recorre y muestre las canciones
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                canciones.add(id + ". " + titulo);
                cursor.moveToNext();
            }
        }
        return canciones;
    }

    public boolean insertarMedia(String titulo, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('" + titulo + "', '" + url + "')");
        return true;
    }
}
