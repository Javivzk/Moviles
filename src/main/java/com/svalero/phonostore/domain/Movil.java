package com.svalero.phonostore.domain;

import com.svalero.phonostore.util.GeneradorID;

import java.util.ArrayList;
import java.util.List;

public class Movil {
    String referencia;
    String marca;
    String modelo;
    String color;

    String imei;
    Integer precioBase;

    private List<Venta> ventas;
    public Movil(){ ventas = new ArrayList<>();

    }
    public Movil(String marca, String modelo, String color, Integer precioBase) {
        referencia = GeneradorID.generarCodigoAleatorio();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioBase = precioBase;
        ventas = new ArrayList<>();
    }

    public Movil(String referencia, String marca, String modelo, String color, Integer precioBase) {
        this.referencia = referencia;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioBase = precioBase;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Integer precioBase) {
        this.precioBase = precioBase;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "Marca: " + marca +
                " , Modelo: " + modelo +
                " , Color: " + color +
                " , Precio: " + precioBase +
                " &euro;";
    }
}
