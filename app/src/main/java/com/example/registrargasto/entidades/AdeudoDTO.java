package com.example.registrargasto.entidades;

public class AdeudoDTO {
    private long idAdeudo;
    private  String nombreadeudo ;
    private String lugar;
    private double precio;
    private int cantidad;
    private  double total;
    private  String fechaLimite;
    private long idTipoGasto;


    public AdeudoDTO(String nombreadeudo, String lugar, double precio, int cantidad, double total, String fechaLimite, long idTipoGasto) {
        this.nombreadeudo = nombreadeudo;
        this.lugar = lugar;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaLimite = fechaLimite;
        this.idTipoGasto = idTipoGasto;
    }

    public AdeudoDTO(String nombreadeudo, double total, String fechaLimite) {
        this.nombreadeudo = nombreadeudo;
        this.total = total;
        this.fechaLimite = fechaLimite;
    }

    public AdeudoDTO(long aLong) {
        this.idAdeudo=aLong;
    }

    public AdeudoDTO(long id,String nombreadeudo, double total, String fechaLimite) {
        this.idAdeudo=id;
        this.nombreadeudo = nombreadeudo;
        this.total = total;
        this.fechaLimite = fechaLimite;
    }

    public void setIdAdeudo(long idAdeudo) {
        this.idAdeudo = idAdeudo;
    }

    public long getIdAdeudo() {
        return idAdeudo;
    }

    public String getNombreadeudo() {
        return nombreadeudo;
    }

    public String getLugar() {
        return lugar;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public long getIdTipoGasto() {
        return idTipoGasto;
    }
}
