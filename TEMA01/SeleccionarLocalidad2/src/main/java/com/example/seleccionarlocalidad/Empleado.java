package com.example.seleccionarlocalidad;

public class Empleado {
    String nombre;
    String apellido;
    String localidad;
    Integer salario;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String localidad, Integer salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", localidad='" + localidad + '\'' +
                ", salario=" + salario +
                '}';
    }
}
