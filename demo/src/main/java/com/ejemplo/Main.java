package com.ejemplo;

import com.ejemplo.analisis.ReportAnalyzer;
import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;
import com.ejemplo.reporting.GeneradorDeReporte;
import com.ejemplo.reporting.GeneradorLogErrores;
import com.ejemplo.reporting.GeneradorReportePDF;
import com.ejemplo.reporting.GeneradorReporteTXT;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Configuración de rutas
        String rutaExcel = "reporte_automatizacion.xlsx"; 
        String rutaPDF = "resumen_resultados.pdf";
        String rutaTXT = "resumen_resultados.txt";
        String rutaLogErrores = "errores.log";

        try {
            // Analizamos el Excel y obtenemos las estadísticas
            ReportAnalyzer analyzer = new ReportAnalyzer();
            ResumenEstadistico resumen = analyzer.analizar(rutaExcel);
            System.out.println("Analisis completado con exito.");

            // Creamos una lista de generadores de reportes
            List<GeneradorDeReporte> generadores = Arrays.asList(
                    new GeneradorReportePDF(rutaPDF),
                    new GeneradorReporteTXT(rutaTXT),
                    new GeneradorLogErrores(rutaLogErrores));

            // Generamos todos los reportes
            for (GeneradorDeReporte generador : generadores) {
                generador.generar(resumen);
            }

        } catch (ReporteException e) {
            System.err.println("Ha ocurrido un error en el proceso:");
            System.err.println(e.getMessage());
            // habilitar o apagar el imprimir stack trace para depuración
            // e.printStackTrace();
        }
    }
}