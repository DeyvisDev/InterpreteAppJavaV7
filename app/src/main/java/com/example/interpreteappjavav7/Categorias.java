package com.example.interpreteappjavav7;

public class Categorias {
    String id;
    String categoria;

    public Categorias(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
