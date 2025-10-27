package com.ejemplo.reporting;

import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class GeneradorReporteTXT implements GeneradorDeReporte {
    private final String rutaArchivo;

    public GeneradorReporteTXT(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void generar(ResumenEstadistico resumen) throws ReporteException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("=== Resumen de Reporte de Automatizacion ===");
            writer.println("Fecha de generacion: " + LocalDate.now());
            writer.println("==========================================");
            writer.println("Total de pruebas ejecutadas: " + resumen.getTotalCasos());
            writer.println("Pruebas exitosas (PASSED): " + resumen.getPasados());
            writer.println("Pruebas fallidas (FAILED): " + resumen.getFallados());
            writer.println("==========================================");
            writer.println(
                    "Tiempo de ejecucion promedio: " + String.format("%.2f", resumen.getTiempoPromedio()) + " segs");
            writer.println("Tiempo de ejecucion maximo: " + resumen.getTiempoMaximo() + " segs");
            writer.println("Tiempo de ejecucion minimo: " + resumen.getTiempoMinimo() + " segs");

            System.out.println("Archivo de resumen .txt creado exitosamente en: " + rutaArchivo);
        } catch (Exception e) {
            throw new ReporteException("Error al generar el resumen .txt.", e);
        }
    }
}