package com.ejemplo.reporting;

//... (imports de itextpdf y otros)
import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDate;

public class GeneradorReportePDF implements GeneradorDeReporte {
    private final String rutaArchivo;

    public GeneradorReportePDF(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void generar(ResumenEstadistico resumen) throws ReporteException {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            Document doc = new Document();
            PdfWriter.getInstance(doc, fos);
            doc.open();

            doc.add(new Paragraph("Resumen de Reporte de Automatizacion"));
            doc.add(new Paragraph("Fecha de generacion: " + LocalDate.now()));
            doc.add(new Paragraph("----------------------------------------"));
            doc.add(new Paragraph("Total de pruebas ejecutadas: " + resumen.getTotalCasos()));
            doc.add(new Paragraph("Total de PASSED: " + resumen.getPasados()));
            doc.add(new Paragraph("Total de FAILED: " + resumen.getFallados()));
            doc.add(new Paragraph("----------------------------------------"));
            doc.add(new Paragraph(
                    "Tiempo de ejecucion promedio: " + String.format("%.2f", resumen.getTiempoPromedio()) + " segs"));
            doc.add(new Paragraph("Tiempo de ejecucion maximo: " + resumen.getTiempoMaximo() + " segs"));
            doc.add(new Paragraph("Tiempo de ejecucion minimo: " + resumen.getTiempoMinimo() + " segs"));

            doc.close();
            System.out.println("PDF de resumen creado exitosamente en: " + rutaArchivo);
        } catch (Exception e) {
            throw new ReporteException("Error al generar el reporte PDF.", e);
        }
    }
}