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
import com.example.registrargasto.TutorialGastoActivity;
import com.example.registrargasto.TutorialInicioActivity;

public class TutorialPendienteActivity extends AppCompatActivity {

    private ImageView siguiente;
    private ImageView atras;
    private TextView skip;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pendiente);
        atras = findViewById(R.id.btn_atras);
        siguiente= findViewById(R.id.btn_siguiente);
        skip = findViewById(R.id.btn_skip);
        btnAtras();
        btnSiguiente();
        btnSkip();
    }
    public void btnAtras(){
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialGastoActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }
    public void btnSiguiente(){
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialPresupuestoActivity.class);
                startActivity(i);
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