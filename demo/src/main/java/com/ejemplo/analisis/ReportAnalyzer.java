package com.ejemplo.analisis;

import com.ejemplo.analisis.modelo.CasoDePrueba;
import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportAnalyzer {

    public ResumenEstadistico analizar(String ruta) throws ReporteException {
        List<CasoDePrueba> casos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(ruta);
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet hoja = workbook.getSheetAt(0);
            boolean primeraFila = true;
            for (Row fila : hoja) {
                if (primeraFila) {
                    primeraFila = false;
                    continue;
                }

                // Extraemos los datos de cada celda
                String nombre = aString(fila.getCell(0));
                java.util.Date fechaUtil = aDate(fila.getCell(1));
                int tiempo = aInt(fila.getCell(2));
                String estado = aString(fila.getCell(3));

                if (nombre.isEmpty() || estado.isEmpty())
                    continue;

                casos.add(new CasoDePrueba(nombre, fechaUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        tiempo, estado));
            }
        } catch (IOException e) {
            throw new ReporteException("Error al leer el archivo Excel en la ruta: " + ruta, e);
        }

        return calcularEstadisticas(casos);
    }

    private ResumenEstadistico calcularEstadisticas(List<CasoDePrueba> casos) {
        int total = casos.size();
        int pasados = (int) casos.stream().filter(c -> "PASSED".equalsIgnoreCase(c.getEstado())).count();
        int fallados = total - pasados;

        List<CasoDePrueba> casosFallados = casos.stream()
                .filter(c -> "FAILED".equalsIgnoreCase(c.getEstado()))
                .collect(Collectors.toList());

        // Cálculo de estadísticas de tiempo
        int tiempoTotal = casos.stream().mapToInt(CasoDePrueba::getTiempoEjecucion).sum();
        double tiempoPromedio = casos.stream().mapToInt(CasoDePrueba::getTiempoEjecucion).average().orElse(0);
        int tiempoMax = casos.stream().mapToInt(CasoDePrueba::getTiempoEjecucion).max().orElse(0);
        int tiempoMin = casos.stream().mapToInt(CasoDePrueba::getTiempoEjecucion).min().orElse(0);

        return new ResumenEstadistico(total, pasados, fallados, tiempoTotal, tiempoPromedio, tiempoMax, tiempoMin,
                casosFallados);
    }

    // Métodos de utilidad para convertir celdas a tipos de datos seguros
    private String aString(Cell celda) {
        if (celda == null)
            return "";
        return celda.toString().trim();
    }

    private int aInt(Cell celda) {
        if (celda == null || celda.getCellType() != CellType.NUMERIC)
            return 0;
        return (int) celda.getNumericCellValue();
    }

    private java.util.Date aDate(Cell celda) {
        if (celda == null || celda.getCellType() != CellType.NUMERIC)
            return new java.util.Date();
        return celda.getDateCellValue();
    }
}