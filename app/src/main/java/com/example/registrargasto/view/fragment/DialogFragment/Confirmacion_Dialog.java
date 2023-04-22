package com.example.registrargasto.view.fragment.DialogFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.R;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.activity.PresupuestoActivity;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;

public class Confirmacion_Dialog extends DialogFragment implements IPresupuestoFragmentView {

    private Button cancelar;
    private Button Aceptar;
    private TextView heater;
    private Activity actividad;

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle sevedInstanceState){
        return lista();
    }

    @SuppressLint("MissingInflatedId")
    private Dialog lista(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.activity_confirmacion_dialog,null);
        builder.setView(rootView);
        cancelar=rootView.findViewById(R.id.dc_b_cancelar);
        Aceptar =rootView.findViewById(R.id.dc_b_confirmar);
        confirmarOp();
        cancelarOp();

        return builder.create();
    }
    public void confirmarOp(){
        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), PresupuestoActivity.class);
                startActivity(intent);
            }
        });

    }
    public void cancelarOp(){
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad=(Activity) context;
        }else{
            throw new RuntimeException(context.toString()+"Implementar");
        }

    }

    @Override
    public PresupuestoDTO consultarPresupuestoDatos() {
        IPresupuestoFragmentView iPresupuestoFragmentView= new DAOPresupuestoIm(getContext());
        return iPresupuestoFragmentView.consultarPresupuesto();
    }

    @Override
    public PresupuestoDTO consultarPresupuesto() {
        return null;
    }
}