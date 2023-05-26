package com.example.registrargasto.viewTutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.registrargasto.R;

public class TutorialPendiente2Activity extends AppCompatActivity {
    private ImageView next;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pendiente2);
        next= findViewById(R.id.t_pend2_next);
        back= findViewById(R.id.t_pend2_back);

        back.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(getApplicationContext(), TutorialPendiente1Activity.class);
                   startActivity(i);
               }
           }
        );
        next.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Intent i = new Intent(getApplicationContext(), TutorialPendiente2Activity.class);
                   //startActivity(i);
               }
           }
        );
    }


}