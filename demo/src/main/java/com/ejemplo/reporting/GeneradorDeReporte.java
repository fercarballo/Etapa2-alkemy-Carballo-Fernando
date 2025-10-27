package com.ejemplo.reporting;

import com.ejemplo.analisis.modelo.ResumenEstadistico;
import com.ejemplo.excepciones.ReporteException;

public interface GeneradorDeReporte {
    void generar(ResumenEstadistico resumen) throws ReporteException;
}