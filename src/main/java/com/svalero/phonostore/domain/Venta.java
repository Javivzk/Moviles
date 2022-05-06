package com.svalero.phonostore.domain;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    Integer idVenta;
    String dni_empleado;
    String dni_cliente;
    String referencia;
    String imei;
    String color;
    Integer precioTotal;

    private List<Movil>moviles;

    public Venta(){ moviles = new ArrayList<>();
    }

    public Venta(String dni_empleado, String dni_cliente, String referencia, String imei, String color, Integer precioTotal) {
        this.dni_empleado = dni_empleado;
        this.dni_cliente = dni_cliente;
        this.referencia = referencia;
        this.imei = imei;
        this.color = color;
        this.precioTotal = precioTotal;
        moviles = new ArrayList<>();
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getDni_empleado() {
        return dni_empleado;
    }

    public void setDni_empleado(String dni_empleado) {
        this.dni_empleado = dni_empleado;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }
    public List<Movil> getBooks() {
        return moviles;
    }

    public void setBooks(List<Movil> moviles) {
        this.moviles = moviles;
    }
    @Override
    public String toString() {
        return "IDVenta: " + idVenta +
                ", Empleado: " + dni_empleado +
                ", Cliente: " + dni_cliente +
                ", Imei: " + imei +
                ", Precio: " + precioTotal +
                " &euro;";
    }
}
