package com.example.registrargasto.Validaciones;


import com.example.registrargasto.Complements.OperacionesFechas;

import java.util.Date;

public class ValidacionesFechas {
    OperacionesFechas operacionesFechas=new OperacionesFechas();
    //Este metodo valida que la fecha de termino no sea igual que la fecha actual
    public boolean validarFechaLimite( String fechaFin){
        boolean estado=false;
        try {
            Date fechaFinal= operacionesFechas.StringADate(fechaFin);
            Date fechaActual= operacionesFechas.StringADate(operacionesFechas.fechaActual());
            estado=operacionesFechas.compararFechaAnterior(fechaActual,fechaFinal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    //Este metodo verifica que la fecha a comparar sea posterior a la fechas actual
    public boolean validarActualPosterior(String fechaFin){
        boolean estado=false;
        try {
            Date fechaFinal= operacionesFechas.StringADate(fechaFin);
            Date fechaActual= operacionesFechas.StringADate(operacionesFechas.fechaActual());
            estado=operacionesFechas.compararFechaPorterior(fechaFinal,fechaActual);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }
    //Este metodo verifica que la fecha a comparar sea anterior a la fechas actual
    public boolean validarActualAterior(String fechaFin){
        boolean estado=false;
        try {
            Date fechaFinal= operacionesFechas.StringADate(fechaFin);
            Date fechaActual= operacionesFechas.StringADate(operacionesFechas.fechaActual());
            estado=operacionesFechas.compararFechaAnterior(fechaFinal,fechaActual);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    public boolean validarFechasIguales(String fecha) throws Exception {
        boolean estado= false;
        Date date1=operacionesFechas.StringADate(fecha);
        Date date2=operacionesFechas.StringADate(operacionesFechas.fechaActual());
        if(operacionesFechas.compararFechasIguales(date1,date2)){
            estado=true;
        }else {
            estado=false;
        }
        return estado;
    }

    //Este metodo compara dos fechas, verifica que la inicial sea anterior a la final
    public boolean validarFechasIniFin ( String fechaIni , String fechaFin) {
        boolean estado=false;
        try {
            Date fechaInicio= operacionesFechas.StringADate(fechaIni);
            Date fechaFinal= operacionesFechas.StringADate(fechaFin);
            estado=operacionesFechas.compararFechaAnterior(fechaInicio,fechaFinal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }
}
