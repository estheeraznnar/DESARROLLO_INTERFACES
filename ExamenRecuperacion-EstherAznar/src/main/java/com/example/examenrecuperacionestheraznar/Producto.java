package com.example.examenrecuperacionestheraznar;

public class Producto {

    private String producto;
    private Double precio;
    private String categoria;
    private String marca;
    private String  estado;

    public Producto() {
    }

    public Producto(String producto, Double precio, String categoria, String marca, String estado) {
        this.producto = producto;
        this.precio = precio * (precio * 1.21);
        this.categoria = categoria;
        this.marca = marca;
        this.estado = estado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "producto='" + producto + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", marca=" + marca +
                ", estado=" + estado +
                '}';
    }
}
