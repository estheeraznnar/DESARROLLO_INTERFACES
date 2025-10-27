package com.example.ejerciciocontrolfx;


public class Capitales {
    String Provincia;
    String Autonomía;
    String Población;

    public Capitales() {
    }

    public Capitales(String provincia, String autonomia, String poblacion) {
        this.Provincia = provincia;
        this.Autonomía = autonomia;
        this.Población = poblacion;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        this.Provincia = provincia;
    }

    public String getAutonomía() {
        return Autonomía;
    }

    public void setAutonomía(String autonomía) {
        this.Autonomía = autonomía;
    }

    public String getPoblación() {
        return Población;
    }

    public void setPoblación(String población) {
        this.Población = población;
    }

    @Override
    public String toString() {
        return "Capitales{" +
                "Provincia='" + Provincia + '\'' +
                ", Autonomía='" + Autonomía + '\'' +
                ", Población='" + Población + '\'' +
                '}';
    }
}
