package com.ejemplo.analisis.modelo;

import java.util.List;

public class ResumenEstadistico {
    // variables de conteo
    private int totalCasos;
    private int pasados;
    private int fallados;

    // Estadísticas de tiempo de ejecución
    private double tiempoTotalEjecucion;
    private double tiempoPromedio;
    private int tiempoMaximo;
    private int tiempoMinimo;

    // Lista para el log de errores
    private List<CasoDePrueba> casosFallados;

    // Constructor, Getters y Setters
    public ResumenEstadistico(int totalCasos, int pasados, int fallados, double tiempoTotalEjecucion,
            double tiempoPromedio, int tiempoMaximo, int tiempoMinimo, List<CasoDePrueba> casosFallados) {
        this.totalCasos = totalCasos;
        this.pasados = pasados;
        this.fallados = fallados;
        this.tiempoTotalEjecucion = tiempoTotalEjecucion;
        this.tiempoPromedio = tiempoPromedio;
        this.tiempoMaximo = tiempoMaximo;
        this.tiempoMinimo = tiempoMinimo;
        this.casosFallados = casosFallados;
    }

    public int getTotalCasos() {
        return totalCasos;
    }

    public int getPasados() {
        return pasados;
    }

    public int getFallados() {
        return fallados;
    }

    public double getTiempoPromedio() {
        return tiempoPromedio;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public List<CasoDePrueba> getCasosFallados() {
        return casosFallados;
    }

    public double getTiempoTotalEjecucion() {
        return tiempoTotalEjecucion;
    }
}