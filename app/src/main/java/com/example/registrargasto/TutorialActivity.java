package com.example.registrargasto;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.registrargasto.viewTutorial.TutorialPendienteActivity;

public class TutorialActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle saveIndtance) {
        super.onCreate(saveIndtance);
        setContentView(R.layout.activity_tutorial);

        Button btnIr = findViewById(R.id.btnIr);

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TutorialPendienteActivity.class);
                startActivity(i);
            }
        });

    }
}