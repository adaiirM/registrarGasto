package com.example.registrargasto.entidades;

public class PresupuestoDTO {
    private long id;
    private double cantidad;
    private String fechaIni;
    private String getFechaFin;

    public PresupuestoDTO(long id, double cantidad, String fechaIni, String getFechaFin) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaIni = fechaIni;
        this.getFechaFin = getFechaFin;
    }
    public PresupuestoDTO(double cantidad, String fechaIni, String getFechaFin) {
        this.cantidad = cantidad;
        this.fechaIni = fechaIni;
        this.getFechaFin = getFechaFin;
    }

    public PresupuestoDTO(double cantidad) {
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public String getGetFechaFin() {
        return getFechaFin;
    }
}
