package org.iesch.examen_ud1;


public class Empleado {
    private String nombre;
    private Double salario;
    private Double salarioNeto;
    private String departamento;

    public Empleado() {
    }

    public Empleado(String nombre, Double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.salarioNeto = salario * 0.85;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getSalarioNeto() {
        return salarioNeto;
    }

    public void setSalarioNeto(Double salarioNeto) {
        this.salarioNeto = salarioNeto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", salarioNeto=" + salarioNeto +
                ", departamento='" + departamento + '\'' +
                '}';
    }

}