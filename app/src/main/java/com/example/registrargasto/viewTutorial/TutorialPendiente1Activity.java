package com.example.registrargasto.viewTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.registrargasto.R;

public class TutorialPendiente1Activity extends AppCompatActivity {
    private ImageView next;
    private ImageView back;

    private ImageView item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pendiente1);
        next =findViewById(R.id.t_pend1_next);
        back =findViewById(R.id.t_pend1_back);
        item= findViewById(R.id.t_pend_item);
        final int distancia = 500; // La distancia en píxeles que se desplaza la imagen
        final int duracion = 1000; // La duración en milisegundos de cada animación

        final Runnable slideRunnable = new Runnable() {
            @Override
            public void run() {
                item.animate().translationXBy(-distancia)
                        .setDuration(duracion)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                item.animate().translationXBy(distancia)
                                        .setDuration(duracion)
                                        .withEndAction(this)
                                        .start();
                            }
                        })
                        .start();
            }
        };

        item.post(new Runnable() {
            @Override
            public void run() {
                slideRunnable.run();
                item.postDelayed(this, duracion * 3); // Wait for 2 duration amounts before starting next animation
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), TutorialPendiente2Activity.class);
                    startActivity(i);
                }
            }
        );
        back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(getApplicationContext(), TutorialPendiente2Activity.class);
                    //startActivity(i);
                }
            }
        );

    }
}