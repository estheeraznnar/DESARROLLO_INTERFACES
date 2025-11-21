package com.example.ejercicio14;

public class Piezas {

    private int id;
    private String nombre;
    private Float precio;

    public Piezas(int id, String nombre, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Piezas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
