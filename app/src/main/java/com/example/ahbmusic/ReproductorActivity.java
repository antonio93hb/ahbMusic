package com.example.ahbmusic;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    protected Bundle extra;
    protected String paquete1 = "";
    protected String paquete2 = "";
    protected Intent pasarPantalla;
    protected MediaPlayer mediaPlayer;
    protected float milisegundo = 0;

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

        extra = getIntent().getExtras();
        paquete1 = extra.getString("titulo");
        paquete2 = extra.getString("url");
        if (paquete1.isEmpty() || paquete2.isEmpty()) {
            Toast.makeText(this, "Imposible reproducir el audio", Toast.LENGTH_SHORT).show();
        }
        textView2.setText("Título: " + paquete1);
        textView3.setText("URL: " + paquete2);
        
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (milisegundo > 0) {
                    mediaPlayer.start();
                } else if (milisegundo < 0) {
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                } else {
                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(paquete2);
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.prepareAsync();
                        Toast.makeText(ReproductorActivity.this, "Cargando...", Toast.LENGTH_SHORT).show();

                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                                Toast.makeText(ReproductorActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(ReproductorActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundo = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundo = -1;
                mediaPlayer.stop();
            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(ReproductorActivity.this, MainActivity.class);
                startActivity(pasarPantalla);
            }
        });
    }
}