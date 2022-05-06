package com.svalero.phonostore.dao;

import com.svalero.phonostore.domain.Cliente;
import com.svalero.phonostore.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ClienteDao {

    private Connection connection;

    public ClienteDao(Connection connection){
        this.connection= connection;
    }

    /**
     * Devuelve todos los clientes de la BD
     * @return ArrayList con todos los clientes de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Cliente> findAll() throws SQLException {
        String sql = "SELECT * FROM CLIENTE ORDER BY dni";
        ArrayList<Cliente> clientes = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while(res.next()){
            UsuarioDao usuarioDAO = new UsuarioDao(connection);
            Cliente cliente = new Cliente();
            cliente.setDni(res.getString("dni"));
            cliente.setIdUsuario(res.getInt("id_usuario"));
            cliente.setDireccion(res.getString("direccion"));
            cliente.setCodigoPostal(res.getString("codigopostal"));
            Usuario usuario = usuarioDAO.getUsuario(cliente);
            cliente.setNombreUsuario(usuario.getNombreUsuario());
            cliente.setContraseña(usuario.getContraseña());
            cliente.setNombre(usuario.getNombre());
            cliente.setApellidos1(usuario.getApellidos1());
            cliente.setApellidos2(usuario.getApellidos2());
            cliente.setTelefono(usuario.getTelefono());
            cliente.setEmail(usuario.getTelefono());
            cliente.setRol(usuario.getRol());
            clientes.add(cliente);
        }
        st.close();
        return clientes;
    }

    /**
     * Devuelve un Cliente si lo encuentra en la BD
     * @param usuario Usuario del que consigue el ID para buscarlo en Cliente
     * @return Optional de Cliente
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Optional<Cliente> getCliente(Usuario usuario) throws  SQLException{
        String sql = "SELECT * FROM CLIENTE WHERE id_usuario = "+usuario.getIdUsuario();
        Cliente cliente = null;

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while(res.next()){
            cliente = new Cliente();
            cliente.setDni(res.getString("dni"));
            cliente.setIdUsuario(res.getInt("id_usuario"));
            cliente.setDireccion(res.getString("direccion"));
            cliente.setPoblacion(res.getString("poblacion"));
            cliente.setProvincia(res.getString("provincia"));
            cliente.setPais(res.getString("pais"));
            cliente.setCodigoPostal(res.getString("codigopostal"));
            cliente.setNombreUsuario(usuario.getNombreUsuario());
            cliente.setContraseña(usuario.getContraseña());
            cliente.setNombre(usuario.getNombre());
            cliente.setApellidos1(usuario.getApellidos1());
            cliente.setApellidos2(usuario.getApellidos2());
            cliente.setTelefono(usuario.getTelefono());
            cliente.setEmail(usuario.getTelefono());
            cliente.setRol(usuario.getRol());
        }
        st.close();
        return Optional.ofNullable(cliente);
    }

    /**
     * Devuelve el Cliente pasado por parametro
     * @param dni DNI del cliente a buscar en la BD
     * @return Cliente de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Cliente getCliente(String dni) throws SQLException{
        String sql = "SELECT * FROM CLIENTE WHERE dni = ?";
        Cliente cliente = new Cliente();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, dni);
        ResultSet res = st.executeQuery();
        while(res.next()){
            UsuarioDao usuarioDAO = new UsuarioDao(connection);
            cliente.setDni(res.getString("dni"));
            cliente.setIdUsuario(res.getInt("id_usuario"));
            cliente.setDireccion(res.getString("direccion"));
            cliente.setPoblacion(res.getString("poblacion"));
            cliente.setCodigoPostal(res.getString("codigopostal"));
            cliente.setPais(res.getString("pais"));
            cliente.setProvincia(res.getString("provincia"));
            Usuario usuario = usuarioDAO.getUsuario(cliente);
            cliente.setNombreUsuario(usuario.getNombreUsuario());
            cliente.setContraseña(usuario.getContraseña());
            cliente.setNombre(usuario.getNombre());
            cliente.setApellidos1(usuario.getApellidos1());
            cliente.setApellidos2(usuario.getApellidos2());
            cliente.setTelefono(usuario.getTelefono());
            cliente.setEmail(usuario.getTelefono());
            cliente.setRol(usuario.getRol());
        }
        st.close();
        return cliente;
    }

    /**
     * Modifica el valor de un campo de un Cliente pasados por parametros
     * @param campo El campo del valor que se quiere modificar
     * @param valor El valor nuevo que se va a introducir
     * @param id El id del Cliente a modificar
     * @return true si se ha modificado el campo correctamente
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws NumberFormatException Si al modificar el campo codigoPostal el valor introducido no es un numero
     */
    public boolean modificaCliente(String campo, String valor, Integer id) throws SQLException, NumberFormatException{
        String sql = "UPDATE CLIENTE SET " + campo + " = ? WHERE id_usuario = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        if(campo.equals("codigoPostal")){
            st.setInt(1, Integer.parseInt(valor));
        } else {
            st.setString(1, valor);
        }
        st.setInt(2,id);
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }

    public boolean modificaCliente(Cliente cliente) throws SQLException, NumberFormatException{
        String sql = "UPDATE CLIENTE SET direccion = ?, poblacion = ?, provincia = ?, pais = ?, codigopostal = ? WHERE id_usuario = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, cliente.getDireccion());
        st.setString(2, cliente.getPoblacion());
        st.setString(3, cliente.getProvincia());
        st.setString(4, cliente.getPais());
        st.setInt(5,Integer.parseInt(cliente.getCodigoPostal()));
        st.setInt(6, cliente.getIdUsuario());
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }
    //TODO Añadir los campos de la BD restantes
}
