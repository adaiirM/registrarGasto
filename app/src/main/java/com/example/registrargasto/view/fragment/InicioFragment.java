package com.example.registrargasto.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registrargasto.DAOS.DAOAdeudoImp;
import com.example.registrargasto.DAOS.DAOGastoImp;
import com.example.registrargasto.DAOS.DAOPresupuestoIm;
import com.example.registrargasto.DAOS.IDAOS.IDAOAdeudo;
import com.example.registrargasto.R;
import com.example.registrargasto.adapter.AdapterListaGastos;
import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;
import com.example.registrargasto.entidades.PresupuestoDTO;
import com.example.registrargasto.view.activity.IActivity.IGastoActivityView;
import com.example.registrargasto.view.activity.ModificarPresupuestoActivity;
import com.example.registrargasto.view.activity.PresupuestoActivity;
import com.example.registrargasto.view.fragment.IFragment.IAdeudoFragmentView;
import com.example.registrargasto.view.fragment.IFragment.IPresupuestoFragmentView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class InicioFragment extends Fragment implements IGastoActivityView, IPresupuestoFragmentView, IDAOAdeudo {

    private CompactCalendarView compactCalendar;
    private final SimpleDateFormat dateFormatMonth = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private RecyclerView recyclerView;
    private TextView textView;
    private TextView mPresupuesto;
    private Button buttonCam;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);
        compactCalendar = rootView.findViewById(R.id.calendar);
        recyclerView = rootView.findViewById(R.id.rviewGastos);
        textView =rootView.findViewById(R.id.txtHeader);
        mPresupuesto=rootView.findViewById(R.id.mosPresupuesto);
        //mtxtPresupuesto=rootView.findViewById(R.id.textPresupuesto);
        buttonCam = rootView.findViewById(R.id.buttonCambiar);

        //Adaptador de recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        AdapterListaGastos adapterHome = null;
        try {
            adapterHome = new AdapterListaGastos(consultarGastos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        recyclerView.setAdapter(adapterHome);

        compactCalendar.setUseThreeLetterAbbreviation(true);

        if(!consultarAdeudo().isEmpty()){
            agregarEventos();
        }


        cambiarPres();

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener(){
            @Override
            public void onDayClick(Date dateClicked) {
                ArrayList<AdeudoDTO> adeudoDTOS=consultarAdeudo();
                PresupuestoDTO presupuestoDTOS=consultarPresupuestoDatos();

                long mes= dateClicked.getTime();
                String date=milliADate(mes);

                for (AdeudoDTO adeudo: adeudoDTOS) {
                    if(adeudo.getFechaLimite().equals(date)){
                        String mensaje=adeudo.getNombreadeudo();
                        Toast.makeText(getContext(),mensaje, Toast.LENGTH_SHORT).show();
                    }
                }
               if(presupuestoDTOS.getFechaIni() != ""){
                   if(presupuestoDTOS.getGetFechaFin().equals(date)){
                       Toast.makeText(getContext(),"Fin de tu presupuesto", Toast.LENGTH_SHORT).show();
                   }
                   if (presupuestoDTOS.getFechaIni().equals(date)) {
                       Toast.makeText(getContext(),"Inicio de tu presupuesto", Toast.LENGTH_SHORT).show();
                   }
               }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
        if(consultarPresupuesto().getCantidad()<=0){
            mPresupuesto.setText("0.0");
        }else {
            mPresupuesto.setText(String.valueOf( consultarPresupuesto().getCantidad()));

        }

        return  rootView;
    }

    public void cambiarPres(){
        buttonCam.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (consultarPresupuesto().getCantidad() != 0){
                         Intent intent;
                         intent = new Intent(getContext(), ModificarPresupuestoActivity.class);
                         startActivity(intent);
                     }else
                         Toast.makeText(getContext(), "Establesca un presupuesto primero", Toast.LENGTH_SHORT).show();
                 }
             }
        );
    }

    @Override
    public ArrayList<GastoDTO> consultarGastos() {
        IGastoActivityView iGastoActivityView=new DAOGastoImp(getContext());
        return iGastoActivityView.consultarGastos();
    }

    public String milliADate(Long milli){
        // Estableciendo formato
        DateFormat simple = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        // Creando fecha de milliseconds
        // usando constructor de Date
        Date date = new Date(milli);
        return simple.format(date);
    }
    public Long fechaALong(String fecha){
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        Long timeInMillis = null;
        try {
            Date d = f.parse(fecha);
            assert d != null;
            timeInMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMillis;
    }


    public void agregarEventos(){
        Event evento;
        ArrayList<Event> eventos = new ArrayList<>();
        PresupuestoDTO presupuestoDTO=consultarPresupuestoDatos()
;
        for (AdeudoDTO adeudoDTO:consultarAdeudo()) {
            evento=new Event(Color.rgb(26, 188, 156),fechaALong(adeudoDTO.getFechaLimite()),adeudoDTO.getNombreadeudo());
            compactCalendar.addEvent(evento);
        }
        if(!presupuestoDTO.getFechaIni().isEmpty() || !presupuestoDTO.getFechaIni().equals(" ")){
            evento=new Event(Color.RED,fechaALong(presupuestoDTO.getFechaIni()),"Inicio de tu presupuesto");
            compactCalendar.addEvent(evento);
        }
        if(!presupuestoDTO.getGetFechaFin().isEmpty() || !presupuestoDTO.getGetFechaFin().equals(" ")){
            evento=new Event(Color.RED,fechaALong(presupuestoDTO.getGetFechaFin()),"Fin de tu presupuesto");
            compactCalendar.addEvent(evento);
        }
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


    @Override
    public long registarNuevoAdeudo(AdeudoDTO adeudoDTO) {
        return 0;
    }

    @Override
    public ArrayList<AdeudoDTO> consultarAdeudo() {
        IAdeudoFragmentView iAdeudoFragmentView = new DAOAdeudoImp(getContext());
        return iAdeudoFragmentView.consultarAdeudoDatos();
    }
}
