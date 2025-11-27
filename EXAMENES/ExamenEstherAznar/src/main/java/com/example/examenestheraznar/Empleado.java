package com.example.examenestheraznar;

import javafx.scene.control.DatePicker;

import java.util.Date;

public class Empleado {

    private int id;
    private String nombre;
    private Date fechaNac;
    private Double salario;
    private int sexo;
    private Integer departamento;

    public Empleado() {
    }

    public Empleado(int id, String nombre, Date fechaNac, Double salario, int sexo, Integer departamento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.salario = salario;
        this.sexo = sexo;
        this.departamento = departamento;
    }

    public Empleado(String nombre, String departamento, int salario, Double salarioneto) {
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

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", salario=" + salario +
                ", sexo=" + sexo +
                ", departamento=" + departamento +
                '}';
    }
}
