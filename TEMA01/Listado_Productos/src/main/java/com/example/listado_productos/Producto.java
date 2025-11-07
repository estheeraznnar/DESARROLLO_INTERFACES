package com.example.listado_productos;

public class Producto {
    private String nombre;
    private Float unidades;
    private Float precio;
    private Float subtotal;

    public Producto() {
    }

    public Producto(String nombre, Float unidades, Float precio, Float subtotal) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getUnidades() {
        return unidades;
    }

    public void setUnidades(Float unidades) {
        this.unidades = unidades;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", unidades=" + unidades +
                ", precio=" + precio +
                ", subtotal=" + subtotal +
                '}';
    }
}
