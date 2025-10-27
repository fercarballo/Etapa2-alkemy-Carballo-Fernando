package com.ejemplo.analisis.modelo;

import java.time.LocalDate;

public class CasoDePrueba {
    private String nombre;
    private LocalDate fechaEjecucion;
    private int tiempoEjecucion;
    private String estado;

    // Constructor, Getters y Setters
    public CasoDePrueba(String nombre, LocalDate fechaEjecucion, int tiempoEjecucion, String estado) {
        this.nombre = nombre;
        this.fechaEjecucion = fechaEjecucion;
        this.tiempoEjecucion = tiempoEjecucion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "CasoDePrueba{" +
                "nombre='" + nombre + '\'' +
                ", fechaEjecucion=" + fechaEjecucion +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", estado='" + estado + '\'' +
                '}';
    }
}