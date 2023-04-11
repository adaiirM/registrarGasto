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

import com.example.registrargasto.DAOS.DAOAdeudoImp;
import com.example.registrargasto.DAOS.DAOGastoImp;
import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.R;
import com.example.registrargasto.adapter.AdapterListaAdeudos;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.activity.AdeudoActivity;
import com.example.registrargasto.view.activity.IActivity.IPresupuestoactivityGasto;
import com.example.registrargasto.view.fragment.IFragment.IAdeudoFragmentView;
import com.example.registrargasto.view.fragment.IFragment.IGastoFragmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AdeudoFragment extends Fragment implements IAdeudoFragmentView, IGastoFragmentView, IPresupuestoactivityGasto {
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
        adapterListaAdeudos = new AdapterListaAdeudos(consultarAdeudo());
        recyclerView.setAdapter(adapterListaAdeudos);
        relativeLayout = rootView.findViewById(R.id.linearLayoutAdeudos);


        actionButtonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getContext(), AdeudoActivity.class);
                startActivity(intent);
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
            if(consultarPresupuestoid().getCantidad()>0){
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

    /*
    public long adeudoAgasto(AdeudoDTO adeudoDTO){
        GastoDTO gastoDTO;
        gastoDTO=new GastoDTO(adeudoDTO.getNombreadeudo(),adeudoDTO.getFechaLimite(),adeudoDTO.getLugar(),
                adeudoDTO.getPrecio(),adeudoDTO.getCantidad(),adeudoDTO.getTotal(),adeudoDTO.getIdTipoGasto());
        return registrarNuevoGastoAAdeudo(gastoDTO);
    }

     */


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





    /*
    @Override
    public void eliminarid(long id) {
        IAdeudoFragmentView iAdeudoFragmentView=new DAOAdeudoImp(getContext());
        iAdeudoFragmentView.eliminarid(id);
    }

     */


}