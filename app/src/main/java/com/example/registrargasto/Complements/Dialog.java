package com.example.registrargasto.Complements;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Dialog {
    public AlertDialog.Builder mostrarMensaje(Activity inicio, Activity destino){

        // Crea una instancia del constructor AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(inicio);
        // Configura las propiedades del diálogo
        builder.setTitle("Título del diálogo")
                .setMessage("Mensaje del diálogo")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Acción a realizar
                        Intent intent=new Intent();
                        intent = new Intent(inicio, destino.getClass());
                        //startActivity(intent);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Acción a realizar si se presiona el botón Cancelar
                    }
                });

        return builder;
    }
}
