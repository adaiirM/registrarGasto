package com.example.registrargasto.Complements;

import com.example.registrargasto.entidades.AdeudoDTO;
import com.example.registrargasto.entidades.GastoDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OperacionesFechas {
    public  String fechaActual(){
        Date fecha= new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(fecha);
    }

    public Date StringADate(String string) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date date1= formato.parse(string);
        return date1;
    }
    //Este metodo compara que una fecha sea anterior a otra
    public boolean compararFechaAnterior(Date date1, Date date2){
        boolean estado=false;
        if(date1.before(date2)){
            estado=true;
        }
        return estado;
    }

    public boolean compararFechasIguales(Date date1, Date date2){
        boolean estado=false;
        if(date1.equals(date2)){
            estado=true;
        }
        return estado;
    }

    //Este metodo compara que una fecha sea posterior a otra
    public boolean compararFechaPorterior(Date date1, Date date2){
        boolean estado=false;
        if(date1.after(date2)){
            estado=true;
        }
        return estado;
    }

    public ArrayList<GastoDTO> fechasOrdenadasGasto(ArrayList<GastoDTO> gastoDTOS) throws Exception {
        int tamanio=gastoDTOS.size();
        for (int i=0; i < tamanio-1 ; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < tamanio; j++) {
                if (compararFechaAnterior(StringADate(gastoDTOS.get(j).getFechaRegistro()), StringADate(gastoDTOS.get(maxIndex).getFechaRegistro()))==false) {
                    maxIndex = j;
                }
            }

            GastoDTO gastoDTO=gastoDTOS.get(i);
            gastoDTOS.set(i,gastoDTOS.get(maxIndex));
            gastoDTOS.set(maxIndex,gastoDTO);
        }

        return gastoDTOS;
    }
    public ArrayList<AdeudoDTO> fechasOrdenadasAdeudo(ArrayList<AdeudoDTO> adeudoDTOS) throws Exception {
        int tamanio=adeudoDTOS.size();
        for (int i=0; i < tamanio-1 ; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < tamanio; j++) {
                if (compararFechaAnterior(StringADate(adeudoDTOS.get(j).getFechaLimite()), StringADate(adeudoDTOS.get(maxIndex).getFechaLimite()))==false) {
                    maxIndex = j;
                }
            }

            AdeudoDTO adeudoDTO=adeudoDTOS.get(i);
            adeudoDTOS.set(i,adeudoDTOS.get(maxIndex));
            adeudoDTOS.set(maxIndex,adeudoDTO);
        }


        return adeudoDTOS;
    }


    public long restarFechas(Date date1, Date date2){
        long diff = date2.getTime()-date1.getTime() ;
        TimeUnit time = TimeUnit.DAYS;
        long diferencia = time.convert(diff, TimeUnit.MILLISECONDS);
        return diferencia;
    }
}