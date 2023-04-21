package com.example.registrargasto.entidades;

public class GastoDTO {
    private long id_gasto;
    private final String nombre;
    private final String fechaRegistro;
    private String lugar;
    private Double precio;
    private final int cantidad;
    private final double total;
    private long tipoGasto;

    public GastoDTO( String nombre, String fechaRegistro, String lugar, Double precio, int cantidad, double total, long tipoGasto) {

        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.lugar = lugar;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.tipoGasto = tipoGasto;
    }
    public GastoDTO( String nombre, String fechaRegistro,int cantidad, double total) {

        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.cantidad = cantidad;
        this.total = total;
    }



    public long getId_gasto() {
        return id_gasto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getLugar() {
        return lugar;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public long getTipoGasto() {
        return tipoGasto;
    }
}
