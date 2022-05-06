package com.svalero.phonostore;

import com.svalero.phonostore.domain.Movil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

public class TestMovil {
    String referencia = "TestReferencia";
    String marca = "TestMarca";
    String modelo = "TestModelo";
    String color = "Negro";
    Integer precioBase = 500;

    Movil movil1 = new Movil(referencia, marca, modelo, color, precioBase);
    Movil movil2 = new Movil(marca, modelo, color, precioBase);

    @Test
    public void testGeneracionMovil1(){
        boolean res = false;
        if(Objects.equals(movil1.getReferencia(), referencia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil2(){
        boolean res = false;
        if(Objects.equals(movil1.getMarca(), marca)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil3(){
        boolean res = false;
        if(Objects.equals(movil1.getModelo(), modelo)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil4(){
        boolean res = false;
        if(Objects.equals(movil1.getColor(), color)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil5(){
        boolean res = false;
        if(Objects.equals(movil1.getPrecioBase(), precioBase)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil6(){
        boolean res = false;
        if(Objects.equals(movil2.getMarca(), marca)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil7(){
        boolean res = false;
        if(Objects.equals(movil2.getModelo(), modelo)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil8(){
        boolean res = false;
        if(Objects.equals(movil2.getColor(), color)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionMovil9(){
        boolean res = false;
        if(Objects.equals(movil2.getPrecioBase(), precioBase)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUUID1(){
        boolean res = true;
        try {
            UUID uuid = UUID.fromString(movil2.getReferencia());
        } catch (IllegalArgumentException iae){
            res = false;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUUID2(){
        boolean res = true;
        try {
            UUID uuid = UUID.fromString(movil1.getReferencia());
        } catch (IllegalArgumentException iae){
            res = false;
        }
        Assertions.assertFalse(res);
    }
}
