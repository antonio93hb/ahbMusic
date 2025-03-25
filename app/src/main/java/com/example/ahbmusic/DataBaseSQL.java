package com.example.ahbmusic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    public DataBaseSQL(@Nullable Context context) {
        super(context, "audio", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE media (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, url TEXT)");

        db.execSQL("INSERT INTO media (titulo, url) VALUES ('SoundHelix', 'https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3')");
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('Sample MP3', 'https://filesamples.com/samples/audio/mp3/sample1.mp3')");
        //db.execSQL("INSERT INTO media (titulo, url) VALUES ('Upbeat Pop Guitar', 'https://cdn.pixabay.com/download/audio/2023/03/01/audio_54321.mp3')");
        //db.execSQL("INSERT INTO media (titulo, url) VALUES ('Ambient Chillout', 'https://cdn.pixabay.com/download/audio/2023/03/01/audio_98765.mp3')");
        //db.execSQL("INSERT INTO media (titulo, url) VALUES ('Energetic Rock Background', 'https://cdn.pixabay.com/download/audio/2023/03/01/audio_19283.mp3')");
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

    public Pair<String, String> obtenerDatos(String input) {
        //Primero parseamos el input para obtener el id
        if (input.isEmpty()) return null;
        int id;
        try {
            id = Integer.parseInt(input.split("\\.") [0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        //Ahora que tenemos el id hacemos una búsqueda a la bbdd
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM media WHERE id = ?", new String[]{String.valueOf(id)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String url = cursor.getString(cursor.getColumnIndexOrThrow("url"));
                return new Pair<>(titulo, url);
            }
        }
        return null;
    }
}
