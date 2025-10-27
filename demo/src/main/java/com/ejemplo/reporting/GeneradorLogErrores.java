package com.ejemplo.reporting;

import com.ejemplo.analisis.modelo.CasoDePrueba;
import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class GeneradorLogErrores implements GeneradorDeReporte {
    private final String rutaArchivo;

    public GeneradorLogErrores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void generar(ResumenEstadistico resumen) throws ReporteException {
        if (resumen.getCasosFallados().isEmpty()) {
            System.out.println("No hubo errores, no se genero log de errores.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("=== Log de Errores - Casos Fallidos ===");
            writer.println("Fecha de generacion: " + LocalDate.now());
            writer.println("=======================================");

            for (CasoDePrueba caso : resumen.getCasosFallados()) {
                writer.printf("Caso: %s | Tiempo: %d segs | Estado: %s%n",
                        caso.getNombre(), caso.getTiempoEjecucion(), caso.getEstado());
            }

            System.out.println("Log de errores creado exitosamente en: " + rutaArchivo);
        } catch (Exception e) {
            throw new ReporteException("Error al generar el log de errores.", e);
        }
    }
}