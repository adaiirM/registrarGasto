package com.example.registrargasto.entidades;

public class TipoGastoDTO {
    private final int id;
    private final String nombreTipoGas;

    public TipoGastoDTO(int id, String nombreTipoGas) {
        this.id = id;
        this.nombreTipoGas = nombreTipoGas;
    }

    public int getId() {
        return id;
    }

    public String getNombreTipoGas() {
        return nombreTipoGas;
    }
}
