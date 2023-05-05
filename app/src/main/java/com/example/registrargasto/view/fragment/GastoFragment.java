package com.example.registrargasto.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.registrargasto.Complements.ManejoListas;
import com.example.registrargasto.Complements.OperacionesFechas;
import com.example.registrargasto.DAOS.DAOGastoImp;
import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.R;
import com.example.registrargasto.adapter.AdapterListaGastos;
import com.example.registrargasto.entidades.GastoDTO;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.fragment.DialogFragment.Confirmacion_Dialog;
import com.example.registrargasto.view.activity.IActivity.IGastoActivityView;
import com.example.registrargasto.view.activity.ActivityGastos;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;


public class GastoFragment extends Fragment implements IGastoActivityView, IPresupuestoFragmentView {
    private RecyclerView recyclerView;
    private TextView textView;
    private FloatingActionButton actionButtonAgregar;
    private SearchView buscarGasto;
    private SwipeRefreshLayout swipeRefreshLayout;
    AdapterListaGastos adapterHome;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gastos, container, false);

        recyclerView= rootView.findViewById(R.id.rviewGastos);
        actionButtonAgregar= rootView.findViewById(R.id.botAgregarGasto);
        buscarGasto= rootView.findViewById(R.id.searchGbucarGasto);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ArrayList<GastoDTO> gastoDTO=consultarGastos();

        try {
            adapterHome = new AdapterListaGastos(gastoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        recyclerView.setAdapter(adapterHome);

        buscarGasto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterHome.getFilter().filter(newText);
                return false;
            }
        });
        agregar();
        return rootView;
    }

    public  void  agregar(){
        actionButtonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(consultarPresupuesto().getCantidad()==0.0  || consultarPresupuesto().getCantidad()<0 || consultarPresupuesto()==null){
                    Confirmacion_Dialog confirmacion_dialog=new Confirmacion_Dialog();
                    confirmacion_dialog.show(getActivity().getSupportFragmentManager(),"Seleccione Usuario");
                }else{
                    Intent intent;
                    intent = new Intent(getContext(), ActivityGastos.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public ArrayList<GastoDTO> consultarGastos() {
        IGastoActivityView iGastoActivityView=new DAOGastoImp(getContext());
        return iGastoActivityView.consultarGastos();
    }


    @Override
    public PresupuestoDTO consultarPresupuestoDatos() {
        return null;
    }

    @Override
    public PresupuestoDTO consultarPresupuesto() {
        IPresupuestoFragmentView iPresupuestoFragmentView=new DAOPresupuestoIm(getContext());
        return iPresupuestoFragmentView.consultarPresupuesto();
    }
}