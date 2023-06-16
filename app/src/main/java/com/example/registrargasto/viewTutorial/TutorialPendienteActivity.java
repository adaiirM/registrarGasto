package com.example.registrargasto.viewTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.registrargasto.R;
import com.example.registrargasto.TutorialInicioActivity;

public class TutorialPendienteActivity extends AppCompatActivity {

    private ImageView siguiente;
    private ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pendiente);
        atras = findViewById(R.id.btn_atras);
        btnAtras();
        btnSiguiente();
    }
    public void btnAtras(){
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), TutorialGastoActivity.class);
                //startActivity(i);
                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }
    public void btnSiguiente(){
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialPresupuestoActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }
}