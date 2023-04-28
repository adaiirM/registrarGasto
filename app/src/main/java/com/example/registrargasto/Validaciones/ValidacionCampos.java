package com.example.registrargasto.Validaciones;

import android.widget.TextView;

import java.util.ArrayList;

public class ValidacionCampos {
    public boolean validacionCamposVacios(ArrayList<TextView> arrayTextview){
        boolean estado=false;
        for (TextView textview:arrayTextview) {
            if(textview.getText().toString().isEmpty()){
                estado=false;
            }else{
                estado=true;
            }
        }

        return  estado;
    }
}
