package com.example.registrargasto.viewTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.registrargasto.MainActivity;
import com.example.registrargasto.R;

public class TutorialInicioActivity extends AppCompatActivity {
    private ImageView siguiente;
    private TextView skip;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_inicio);
        siguiente = findViewById(R.id.btn_siguiente);
        skip = findViewById(R.id.btn_skip);
        btnSiguiente();
        btnSkip();
    }

    public void btnSiguiente(){
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialGastoActivity.class);
                startActivity(i);
                //Animacion para pasaar de un activity a otro
                overridePendingTransition(R.anim.left_out, R.anim.left_in);
            }
        });
    }

    public void btnSkip(){
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}