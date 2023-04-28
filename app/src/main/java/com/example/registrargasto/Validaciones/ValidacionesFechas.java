package com.example.registrargasto.Validaciones;


import com.example.registrargasto.Complements.OperacionesFechas;

import java.util.Date;

public class ValidacionesFechas {
    OperacionesFechas operacionesFechas=new OperacionesFechas();
    //Este metodo valida que la fecha de termino no sea igual que la fecha actual
    public boolean validarFechaLimite( String fechaFin){
        boolean estado=false;
        try {
            Date fechaFinal= operacionesFechas.fechaDate(fechaFin);
            Date fechaActual= operacionesFechas.fechaDate(operacionesFechas.fechaActual());
            estado=operacionesFechas.compararFechas(fechaActual,fechaFinal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }
    public boolean valifarFin(String fechaFin){
        boolean estado=true;
        if(fechaFin.equals(operacionesFechas.fechaActual())){
            estado=false;
        }
        return estado;
    }
    public boolean validarFechasIniFin ( String fechaIni , String fechaFin) {
        boolean estado=false;
        try {
            Date fechaInicio= operacionesFechas.fechaDate(fechaIni);
            Date fechaFinal= operacionesFechas.fechaDate(fechaFin);
            estado=operacionesFechas.compararFechas(fechaInicio,fechaFinal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return estado;
    }
}
