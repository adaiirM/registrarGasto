package com.example.registrargasto.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.R;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.activity.PresupuestoActivity;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PresupuestoFragment extends Fragment implements IPresupuestoFragmentView {
    private TextView titulo;
    private TextView presupuesto;
    private TextView fechaIni;
    private TextView fechaFini;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_presupuesto, container, false);
        titulo=rootView.findViewById(R.id.txtPresHeader);
        presupuesto=rootView.findViewById(R.id.txtmosPres);
        fechaIni=rootView.findViewById(R.id.txtmosMosInPres);
        fechaFini=rootView.findViewById(R.id.txtmosFinPres);
        floatingActionButton=rootView.findViewById(R.id.botAgregarPres);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getContext(), PresupuestoActivity.class);
                startActivity(intent);
            }
        });

        if(consultarPresupuesto().getCantidad()==0){
            presupuesto.setText("0.0");
            fechaIni.setText("");
            fechaFini.setText("");
        }else {
            presupuesto.setText(String.valueOf( consultarPresupuestoDatos().getCantidad()));
            fechaIni.setText(consultarPresupuestoDatos().getFechaIni());
            fechaFini.setText(consultarPresupuestoDatos().getGetFechaFin());
        }

        return rootView;
    }


    @Override
    public PresupuestoDTO consultarPresupuestoDatos() {
        IPresupuestoFragmentView iPresupuestoFragmentView=new DAOPresupuestoIm(getContext());
        return iPresupuestoFragmentView.consultarPresupuestoDatos();
    }

    @Override
    public PresupuestoDTO consultarPresupuesto() {
        IPresupuestoFragmentView iPresupuestoFragmentView=new DAOPresupuestoIm(getContext());
        return iPresupuestoFragmentView.consultarPresupuesto();
    }


}