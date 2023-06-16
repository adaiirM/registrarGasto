package com.example.registrargasto.viewTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.registrargasto.R;
import com.example.registrargasto.TutorialInicioActivity;

public class TutorialPresupuestoActivity extends AppCompatActivity {
    private ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_presupuesto);
        atras = findViewById(R.id.btn_atras);
        btnAtras();
    }
    public void btnAtras(){
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialPendienteActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }
}