package com.svalero.phonostore.dao;

import com.svalero.phonostore.domain.Movil;
import com.svalero.phonostore.exception.YaExisteMovil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class MovilDao {

    private Connection connection;

    public MovilDao(Connection connection){
        this.connection = connection;
    }

    /**
     * Da de alta un movil nuevo en la BD pasado por parametro
     * @param movil Movil nuevo a añadir
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws YaExisteMovil Error lanzado si intentas añadir un vehiculo ya existente en la BD
     */
    public void altaMovil(Movil movil) throws SQLException, YaExisteMovil {
        if(existeMovil(movil)){
            throw new YaExisteMovil();
        }

        String sql = "INSERT INTO MOVIL (referencia, marca, modelo, color, precioBase) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, movil.getReferencia());
        st.setString(2, movil.getMarca());
        st.setString(3, movil.getModelo());
        st.setString(4, movil.getColor());
        st.setInt(5, movil.getPrecioBase());
        st.executeUpdate();

        st.close();
    }

    /**
     * Da de baja un movil en la BD pasado por parametro
     * @param referencia Campo de la BD por el que se quiere buscar
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public boolean eliminarMovil(String referencia) throws SQLException{
        String sql = "DELETE FROM MOVIL WHERE referencia = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, referencia);
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }

    /**
     * Comprueba si existe un movil en la BD con la misma marca y modelo
     * @param movil Movil a comprobar si existe en BD
     * @return true si existe en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    private boolean existeMovil(Movil movil) throws SQLException{
        Optional<Movil> movil1 = findBy("marca", movil.getMarca());
        Optional<Movil> movil2 = findBy("modelo", movil.getModelo());

        if(movil1.isPresent() && movil2.isPresent()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para buscar un movil por el campo y valor pasado por parametros
     * @param campo Campo de la base de datos por el que se quiere buscar en la BD
     * @param valor Valor a buscsar en la BD
     * @return Optional con los moviles encontrados, si los hubiera
     * @throws SQLException Si hay algun error no especifico lanzado por la BD.
     */
    public Optional<Movil> findBy(String campo, String valor) throws SQLException{
        String sql = "SELECT * FROM MOVIL WHERE " + campo.trim() + " = ?";
        Movil movil = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, valor);
        ResultSet res = st.executeQuery();
        if(res.next()){
            movil = new Movil();
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
        }
        st.close();
        return Optional.ofNullable(movil);
    }

    /**
     * Devuelve la lista de todos los moviles en la BD
     * @return ArrayList con todos los moviles encontrados en la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Movil> findAll() throws SQLException {
        String sql = "SELECT * FROM MOVIL ORDER BY marca";
        ArrayList<Movil> moviles = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Movil movil = new Movil();
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
            moviles.add(movil);
        }
        st.close();
        return moviles;
    }

    /**
     * Devuelve el Movil pasado por parametro
     * @param referencia Referencia del Movil a buscar en la BD
     * @return Movil de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Movil getMovil(String referencia) throws SQLException {
        String sql = "SELECT * FROM MOVIL WHERE referencia = ?";
        Movil movil = new Movil();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, referencia);
        ResultSet res = st.executeQuery();
        while (res.next()){
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
        }
        st.close();
        return movil;
    }

    /**
     * Modifica los valores de los campos de un Movil
     * @param movil El movil anterior pero con los datos modificados
     * @return true si se ha modificado correctamente
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public boolean modificaMovil(Movil movil) throws SQLException {
        String sql = "UPDATE MOVIL SET marca = ?, modelo = ?, color = ?, precioBase = ? WHERE referencia = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, movil.getMarca());
        st.setString(2, movil.getModelo());
        st.setString(3, movil.getColor());
        st.setInt(4, movil.getPrecioBase());
        st.setString(5, movil.getReferencia());
        int rows = st.executeUpdate();
        return rows == 1;
    }

    /**
     * Devuelve todas las marcas distintas que hay guardadas en la BD
     * @return ArrayList con todas las marcas distintas de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<String> getMarcas() throws SQLException{
        String sql = "SELECT marca FROM MOVIL GROUP BY marca";
        ArrayList<String> marcas = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while(res.next()){
            marcas.add(res.getString("marca"));
        }

        return marcas;
    }

    /**
     * Devuelve todos los moviles de la marca que se introduce por parametro
     * @param marca Marca a buscar en la BD
     * @return ArrayList de Movil que contiene todos los Moviles encontrados
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Movil> findByMarca(String marca) throws SQLException{
        String sql = "SELECT * FROM MOVIL WHERE marca = ?";
        ArrayList<Movil> moviles = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, marca);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Movil movil = new Movil();
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
            moviles.add(movil);
        }
        st.close();
        return moviles;
    }

    /**
     * Devuelve todos los moviles encontrados por el valor introducido por paramatro
     * @param modelo Modelo del movil a buscar en la base de datos, no necesita ser exacta
     * @return ArrayList de Movil que contiene todos los Moviles encontrados
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Movil> findByModelo(String modelo) throws SQLException{
        String sql = "SELECT * FROM MOVIL WHERE INSTR(modelo, ?) != 0";
        ArrayList<Movil> moviles = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, modelo);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Movil movil = new Movil();
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
            moviles.add(movil);
        }
        st.close();
        return moviles;
    }

    /**
     * Devuelve todos los moviles encontrados por los valores de marca y modelo introducidos por parametro
     * @param marca Marca del movil a mostrar, necesita ser exacta.
     * @param modelo Modelo del movil a buscar en la base de datos, no necesita ser exacta
     * @return ArrayList de Movil que contiene todos los Moviles encontrados
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Movil> findByMarcaModelo(String marca, String modelo) throws SQLException{
        String sql = "SELECT * FROM MOVIL WHERE marca = ? AND INSTR(modelo, ?) != 0";
        ArrayList<Movil> moviles = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, marca);
        st.setString(2, modelo);
        ResultSet res = st.executeQuery();
        while (res.next()){
            Movil movil = new Movil();
            movil.setReferencia(res.getString("referencia"));
            movil.setMarca(res.getString("marca"));
            movil.setModelo(res.getString("modelo"));
            movil.setColor(res.getString("color"));
            movil.setPrecioBase(res.getInt("precioBase"));
            moviles.add(movil);
        }
        st.close();
        return moviles;
    }

    public Optional<Movil> findByReferencia (String referencia) throws SQLException {
        String sql = "SELECT * FROM movil WHERE referencia = ?";
        Movil movil = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "referencia");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            movil = fromResultSet(resultSet);
        }

        return Optional.ofNullable(movil);
    }

    private Movil fromResultSet(ResultSet resultSet) throws SQLException {
        Movil movil = new Movil();
        movil.setReferencia(resultSet.getString("referencia"));
        movil.setMarca(resultSet.getString("marca"));
        movil.setModelo(resultSet.getString("modelo"));
        movil.setColor(resultSet.getString("color"));
        movil.setPrecioBase(Integer.valueOf(resultSet.getString("precioBase")));
        return movil;
    }

    public Optional<Movil> findById(String referencia) throws SQLException {
        String sql = "SELECT * FROM MOVIL WHERE referencia = ?";
        Movil movil = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, referencia);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            movil = fromResultSet(resultSet);
        }

        return Optional.ofNullable(movil);
    }
}
