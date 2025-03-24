package com.example.ahbmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected TextView textView1;
    protected ListView listView1;
    protected DataBaseSQL db;
    protected ArrayList<String> canciones;
    protected ArrayAdapter adapter;
    protected Intent pasarPantalla;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = findViewById(R.id.textView1_main);
        listView1 = findViewById(R.id.listView1_main);
        db = new DataBaseSQL(MainActivity.this);
        canciones = db.obtenerMedia();

        if (canciones.isEmpty()) {
            canciones.add("No hay canciones");
        }

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, canciones);
        listView1.setAdapter(adapter);

    }
    @Override
    public void onResume() {
        super.onResume();
        actualizarListado();
    }
    private void actualizarListado() {
        canciones = db.obtenerMedia();
        if (canciones.isEmpty()) {
            canciones.add("No hay canciones");
        }
        adapter.clear();
        adapter.addAll(canciones);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1_menu) {
            Toast.makeText(this, "He pulsado en añadir", Toast.LENGTH_SHORT).show();
            pasarPantalla = new Intent(MainActivity.this, CrearActivity.class);
            startActivity(pasarPantalla);
            return true;
        } else if (id == R.id.item2_menu) {
            Toast.makeText(this, "He pulsado en salir", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}