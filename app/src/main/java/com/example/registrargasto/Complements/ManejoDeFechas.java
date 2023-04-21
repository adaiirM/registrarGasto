package com.example.registrargasto.Complements;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManejoDeFechas {
    public static String fechaActual(){
        Date fecha= new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(fecha);
    }
}
