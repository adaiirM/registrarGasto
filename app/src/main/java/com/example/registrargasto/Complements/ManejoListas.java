package com.example.registrargasto.Complements;

import java.util.ArrayList;

public class ManejoListas<T> {
    public <T> ArrayList<T> ordenarLista(ArrayList<T> lista){
        ArrayList<T> auxiliar = new ArrayList<>();

        for (int i=lista.size()-1, j=0; i>=0 ; --i,++j ){
            auxiliar.add(j, lista.get(i));
        }

        return auxiliar;
    }
}