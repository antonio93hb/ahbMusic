package com.example.ahbmusic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReproductorActivity extends AppCompatActivity {
    protected TextView textView1;
    protected TextView textView2;
    protected TextView textView3;
    protected ImageButton buttonPause;
    protected ImageButton buttonPlay;
    protected ImageButton buttonStop;
    protected Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reproductor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = (TextView) findViewById(R.id.textView1_reproductor);
        textView2 = (TextView) findViewById(R.id.textView2_reproductor);
        textView3 = (TextView) findViewById(R.id.textView3_reproductor);
        buttonPause = (ImageButton) findViewById(R.id.buttonPause_reproductor);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay_reproductor);
        buttonStop = (ImageButton) findViewById(R.id.buttonStop_reproductor);
        button1 = (Button) findViewById(R.id.buttonVolver_reproductor);
    }
}