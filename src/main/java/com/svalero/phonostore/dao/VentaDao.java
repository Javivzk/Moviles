package com.svalero.phonostore.dao;

import com.svalero.phonostore.domain.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentaDao {

    private Connection connection;

    public VentaDao(Connection connection){
        this.connection = connection;
    }

    /**
     * Inserta en la BD una Venta pasada por parametro
     * @param venta Venta a introducir en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public void altaVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO VENTAS (dni_empleado, dni_cliente, referencia, imei, color, precioTotal) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, venta.getDni_empleado());
        st.setString(2, venta.getDni_cliente());
        st.setString(3, venta.getReferencia());
        st.setString(4, venta.getImei());
        st.setString(5, venta.getColor());
        st.setInt(6,venta.getPrecioTotal());
        st.executeUpdate();
        st.close();
    }

    /**
     * Busca las ventas que tenga el DNI del Cliente pasado por parametro
     * @param dni del Cliente a buscar
     * @return ArrayList con las ventas que tengan ese DNI
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Venta> findVenta(String dni) throws SQLException{
        String sql = "SELECT * FROM VENTAS WHERE dni_cliente = ?";
        ArrayList<Venta> ventas = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, dni);
        ResultSet res = st.executeQuery();
        while (res.next()) {
            Venta venta = new Venta();
            venta.setIdVenta(res.getInt("id_venta"));
            venta.setDni_empleado(res.getString("dni_empleado"));
            venta.setDni_cliente(res.getString("dni_cliente"));
            venta.setReferencia(res.getString("referencia"));
            venta.setImei(res.getString("imei"));
            venta.setColor(res.getString("color"));
            venta.setPrecioTotal(res.getInt("precioTotal"));
            ventas.add(venta);
        }
        st.close();
        return ventas;
    }

    /**
     * Devulve un ArrayList con todas las ventas de la BD
     * @return ArrayList de Venta en la que contiene todas las ventas encontradas en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Venta> findAll() throws SQLException{
        String sql = "SELECT * FROM VENTAS ORDER BY id_venta";
        ArrayList<Venta> ventas = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Venta venta = new Venta();
            venta.setIdVenta(res.getInt("id_venta"));
            venta.setDni_empleado(res.getString("dni_empleado"));
            venta.setDni_cliente(res.getString("dni_cliente"));
            venta.setReferencia(res.getString("referencia"));
            venta.setImei(res.getString("imei"));
            venta.setColor(res.getString("color"));
            venta.setPrecioTotal(res.getInt("precioTotal"));
            ventas.add(venta);
        }
        st.close();
        return ventas;
    }

    /**
     * Borra una venta de la BD
     * @param id Identificador de la Venta a borrar
     * @return true si se ha borrado correctamente
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public boolean borraVenta(Integer id) throws SQLException{
        String sql = "DELETE FROM VENTAS WHERE id_venta = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id);
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }

    /**
     * Busca la venta que tiene el ID pasado por parametro
     * @param id ID de la venta a buscar
     * @return ArrayList con las ventas que tengan ese ID
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Venta> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM VENTAS WHERE id_venta = ?";
        ArrayList<Venta> ventas = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        while (res.next()) {
            Venta venta = new Venta();
            venta.setIdVenta(res.getInt("id_venta"));
            venta.setDni_empleado(res.getString("dni_empleado"));
            venta.setDni_cliente(res.getString("dni_cliente"));
            venta.setReferencia(res.getString("referencia"));
            venta.setImei(res.getString("imei"));
            venta.setColor(res.getString("color"));
            venta.setPrecioTotal(res.getInt("precioTotal"));
            ventas.add(venta);
        }
        st.close();
        return ventas;
    }
}
