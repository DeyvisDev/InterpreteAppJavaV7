package com.example.interpreteappjavav7;

public class Model {
    String tituloSordo, descripcionSordo, tituloNormal, descripcionNormal, fecha, hora, usuario, categoria;
    String imgSubir;

    public Model() {
    }

    public Model(String tituloSordo, String descripcionSordo, String tituloNormal, String descripcionNormal, String fecha, String hora, String usuario, String imgSubir, String categoria) {
        this.tituloSordo = tituloSordo;
        this.descripcionSordo = descripcionSordo;
        this.tituloNormal = tituloNormal;
        this.descripcionNormal = descripcionNormal;
        this.fecha = fecha;
        this.hora = hora;
        this.usuario = usuario;
        this.imgSubir = imgSubir;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTituloSordo() {
        return tituloSordo;
    }

    public void setTituloSordo(String tituloSordo) {
        this.tituloSordo = tituloSordo;
    }

    public String getDescripcionSordo() {
        return descripcionSordo;
    }

    public void setDescripcionSordo(String descripcionSordo) {
        this.descripcionSordo = descripcionSordo;
    }

    public String getTituloNormal() {
        return tituloNormal;
    }

    public void setTituloNormal(String tituloNormal) {
        this.tituloNormal = tituloNormal;
    }

    public String getDescripcionNormal() {
        return descripcionNormal;
    }

    public void setDescripcionNormal(String descripcionNormal) {
        this.descripcionNormal = descripcionNormal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getImgSubir() {
        return imgSubir;
    }

    public void setImgSubir(String imgSubir) {
        this.imgSubir = imgSubir;
    }
}
