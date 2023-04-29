package com.example.registrargasto.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registrargasto.Complements.ManejoListas;
import com.example.registrargasto.Complements.OperacionesFechas;
import com.example.registrargasto.DAOS.DAOAdeudoImp;
import com.example.registrargasto.DAOS.DAOGastoImp;
import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.R;
import com.example.registrargasto.adapter.AdapterListaAdeudos;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.fragment.DialogFragment.Confirmacion_Dialog;
import com.example.registrargasto.view.activity.AdeudoActivity;
import com.example.registrargasto.view.activity.IActivity.IPresupuestoactivityGasto;
import com.example.registrargasto.view.fragment.IFragment.IAdeudoFragmentView;
import com.example.registrargasto.view.fragment.IFragment.IGastoFragmentView;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class AdeudoFragment extends Fragment implements IAdeudoFragmentView, IPresupuestoFragmentView, IGastoFragmentView, IPresupuestoactivityGasto {
    private RecyclerView recyclerView;
    private TextView textView;
    private FloatingActionButton actionButtonAgregar;
    private AdapterListaAdeudos adapterListaAdeudos;
    RelativeLayout relativeLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_adeudos, container, false);
        recyclerView = rootView.findViewById(R.id.rviewGastos);
        actionButtonAgregar = rootView.findViewById(R.id.botAgregarAdeudo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<AdeudoDTO> adeudoDTOS=consultarAdeudo();
        ManejoListas<AdeudoDTO> manejoListas=new ManejoListas<>();
        OperacionesFechas operacionesFechas = new OperacionesFechas();
        try {
            adapterListaAdeudos = new AdapterListaAdeudos(manejoListas.ordenarLista(operacionesFechas.fechasOrdenadasAdeudo(adeudoDTOS)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        recyclerView.setAdapter(adapterListaAdeudos);
        relativeLayout = rootView.findViewById(R.id.linearLayoutAdeudos);

        actionButtonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(consultarPresupuesto().getCantidad() == 0.0  || consultarPresupuesto().getCantidad() < 0 || consultarPresupuesto()==null){
                    //Se llama a la clase Presupuesto Dialog para mostrar un cuadro de dialogo
                    Confirmacion_Dialog confirmacion_dialog=new Confirmacion_Dialog();
                    confirmacion_dialog.show(getActivity().getSupportFragmentManager(),"Seleccione Usuario");
                }else{
                    Intent intent;
                    intent = new Intent(getContext(), AdeudoActivity.class);
                    startActivity(intent);
                }
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        return rootView;
    }


    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar snackbar = Snackbar.make(relativeLayout, "Pago realizado", Snackbar.LENGTH_LONG);
            snackbar.show();
            AdeudoDTO adeudoDTO;
            ArrayList<AdeudoDTO> adeudos;
            adeudos=consultarAdeudoDatos();
            adeudoDTO=adeudos.get(viewHolder.getAdapterPosition());
            registrarNuevoGastoAAdeudo(adeudoDTO);


            if(consultarPresupuestoid().getCantidad()>0.0){
                restarPresupuesto(Double.valueOf(adeudoDTO.getTotal()));
            }

            ArrayList<AdeudoDTO> adeudoid= consultarAdeudoid();
            AdeudoDTO adeudoI=adeudoid.get(viewHolder.getAdapterPosition());
            long id=adeudoI.getIdAdeudo();
            eliminarid(id);


            ArrayList<AdeudoDTO> adeudo= consultarAdeudo();
            adapterListaAdeudos=new AdapterListaAdeudos(adeudo);
            recyclerView.setAdapter(adapterListaAdeudos);
            adapterListaAdeudos.notifyDataSetChanged();

        }

    };




    @Override
    public ArrayList<AdeudoDTO> consultarAdeudo() {
        IAdeudoFragmentView iAdeudoFragmentView = new DAOAdeudoImp(getContext());
        return iAdeudoFragmentView.consultarAdeudo();
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudoid() {
        IAdeudoFragmentView iAdeudoFragmentView=new DAOAdeudoImp(getContext());
        return iAdeudoFragmentView.consultarAdeudoid();
    }

    @Override
    public void eliminarid(long id) {
        IAdeudoFragmentView iAdeudoFragmentView=new DAOAdeudoImp(getContext());
        iAdeudoFragmentView.eliminarid(id);
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudoDatos() {
        IAdeudoFragmentView iAdeudoFragmentView = new DAOAdeudoImp(getContext());
        return iAdeudoFragmentView.consultarAdeudoDatos();
    }


    @Override
    public long registrarNuevoGastoAAdeudo(AdeudoDTO adeudoDTO) {
        IGastoFragmentView iGastoFragmentView = new DAOGastoImp(getContext());
        return iGastoFragmentView.registrarNuevoGastoAAdeudo(adeudoDTO);
    }

    @Override
    public void restarPresupuesto(double gasto) {
        IPresupuestoactivityGasto iPresupuestoactivityGasto=new DAOPresupuestoIm(getContext());
        iPresupuestoactivityGasto.restarPresupuesto(gasto);
    }

    @Override
    public PresupuestoDTO consultarPresupuestoid() {
        IPresupuestoactivityGasto iPresupuestoactivityGasto=new DAOPresupuestoIm(getContext());
        return iPresupuestoactivityGasto.consultarPresupuestoid();
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