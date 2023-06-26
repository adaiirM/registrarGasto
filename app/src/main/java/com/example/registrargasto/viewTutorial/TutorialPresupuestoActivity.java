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

public class TutorialPresupuestoActivity extends AppCompatActivity {
    private ImageView atras;
    private TextView skip;
    private ImageView iniciar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_presupuesto);
        atras = findViewById(R.id.btn_atras);
        skip = findViewById(R.id.btn_skip);
        iniciar = findViewById(R.id.btn_iniciar);
        btnIniciar();
        btnAtras();
        btnSkip();
    }
    public void btnAtras(){
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialPendienteActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
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

    public void btnIniciar(){
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}