package com.ejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class OperadoresDemoTest {

    OperadoresDemo demo = new OperadoresDemo();

    @Test
    public void testClasificarNota() {
        assertEquals("Aprobado", demo.clasificarNota(8));
        assertEquals("Desaprobado", demo.clasificarNota(5));
        assertEquals("Desaprobado", demo.clasificarNota(0));
    }
    
    @Test
    void testPuedeConducir(){
        assertTrue(demo.puedeConducir(20, true));
        assertFalse(demo.puedeConducir(16, true));
        assertFalse(demo.puedeConducir(16, false));
    }

    @Test
    void testContieneNumero(){
        int[] numeros = {1,3,5,7};
        assertTrue(demo.contieneNumero(numeros, 5));
        assertFalse(demo.contieneNumero(numeros, 2));
    }

    @Test
    void testContarPares(){
        int[] numeros = {1,2,3,4,6};
        assertEquals(3, demo.contarPares(numeros));
    }
}
