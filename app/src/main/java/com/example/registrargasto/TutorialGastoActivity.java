package com.example.registrargasto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TutorialGastoActivity extends AppCompatActivity {
    private ImageView siguiente;
    private ImageView atras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_gasto);
        atras = findViewById(R.id.btn_atras);
        btnAtras();
    }

    public void btnAtras(){
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialInicioActivity.class);
                startActivity(i);
            }
        });
    }
}