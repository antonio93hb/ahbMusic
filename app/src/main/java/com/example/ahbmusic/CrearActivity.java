package com.example.ahbmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearActivity extends AppCompatActivity {

protected TextView textView1;
protected TextView textView2;
protected TextView textView3;
protected EditText editText1;
protected EditText editText2;
protected Button button1;
protected Button button2;
protected String containerTitle;
protected String containerUrl;
protected Intent pasarPantalla;
protected DataBaseSQL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = (TextView) findViewById(R.id.textView1_crear);
        textView2 = (TextView) findViewById(R.id.textView2_crear);
        textView3 = (TextView) findViewById(R.id.textView3_crear);
        editText1 = (EditText) findViewById(R.id.editText1_crear);
        editText2 = (EditText) findViewById(R.id.editText2_crear);
        button1 = (Button) findViewById(R.id.button1_crear);
        button2 = (Button) findViewById(R.id.button2_crear);
        
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerTitle = editText1.getText().toString();
                containerUrl = editText2.getText().toString();
                if (containerTitle.isEmpty() || containerUrl.isEmpty()) {
                    Toast.makeText(CrearActivity.this, getString(R.string.debesRellenar), Toast.LENGTH_SHORT).show();
                } else {
                    editText1.setText("");
                    editText2.setText("");
                    db = new DataBaseSQL(CrearActivity.this);
                    if (db.insertarMedia(containerTitle, containerUrl)) {
                        Toast.makeText(CrearActivity.this,getString(R.string.audioGuardado), Toast.LENGTH_SHORT).show();
                        pasarPantalla = new Intent(CrearActivity.this, MainActivity.class);
                        startActivity(pasarPantalla);
                    } else {
                        Toast.makeText(CrearActivity.this, getString(R.string.noSeHaPodidoGuardarElArchivo), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(CrearActivity.this, MainActivity.class);
                startActivity(pasarPantalla);
            }
        });
    }
}