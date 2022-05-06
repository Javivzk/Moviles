package com.svalero.phonostore.dao;

import com.svalero.phonostore.domain.Cliente;
import com.svalero.phonostore.domain.Empleado;
import com.svalero.phonostore.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(Connection connection){
        this.connection=connection;
    }

    /**
     * Devuelve todos los usuarios de la BD
     * @return ArrayList con todos los usuarios
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM USUARIO ORDER BY id_usuario";
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        if(res.next()){
            Usuario usuario = new Usuario();

            usuario.setIdUsuario(res.getInt("id_usuario"));
            usuario.setNombreUsuario(res.getString("nombreusuario"));
            usuario.setContraseña(res.getString("contraseña"));
            usuario.setNombre(res.getString("nombre"));
            usuario.setApellidos1(res.getString("apellido1"));
            usuario.setApellidos2(res.getString("apellido2"));
            usuario.setTelefono(res.getString("telefono"));
            usuario.setEmail(res.getString("email"));
            usuario.setRol(res.getString("rol"));

            usuarios.add(usuario);
        }
        st.close();
        return usuarios;
    }

    /**
     * Devuelve el Usuario que tenga el id pasado por parametro
     * @param id id_usuario de la tabla Usuario a buscar
     * @return Optional de Usuario
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    private Optional<Usuario> getUsuario(Integer id) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        Usuario usuario = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,id);
        ResultSet res = st.executeQuery();
        while(res.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(res.getInt("id_usuario"));
            usuario.setNombreUsuario(res.getString("nombreUsuario"));
            usuario.setContraseña(res.getString("contraseña"));
            usuario.setNombre(res.getString("nombre"));
            usuario.setApellidos1(res.getString("apellido1"));
            usuario.setApellidos2(res.getString("apellido2"));
            usuario.setTelefono(res.getString("telefono"));
            usuario.setEmail(res.getString("email"));
            usuario.setRol(res.getString("rol"));
        }
        st.close();
        return Optional.ofNullable(usuario);
    }

    /**
     * Devuelve el Usuario del Empleado pasado por parametro
     * @param empleado Empleado a buscar en la tabla de Usuario
     * @return Devuelve el Usuario, si lo ha encontrado
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws NoSuchElementException Si no existe el Usuario del Empleado
     */
    public Usuario getUsuario(Empleado empleado) throws SQLException, NoSuchElementException {
        return getUsuario(empleado.getIdUsuario()).orElseThrow();
    }

    /**
     * Devuelve el Usuario del Cliente pasado por parametro
     * @param cliente Cliente a buscar en la tabla de Usuario
     * @return Devuelve el Usuario, si lo ha encontrado
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     * @throws NoSuchElementException Si no existe el Usuario del Cliente
     */
    public Usuario getUsuario(Cliente cliente) throws SQLException, NoSuchElementException {
        return getUsuario(cliente.getIdUsuario()).orElseThrow();
    }

    /**
     * Devuelve el Usuario si el nombre de usuario y la contraseña, introducidos por parametro, son correctos
     * @param nombeUsuario Nombre de Usuario a comprobar
     * @param contrseña Contraseña de Usuario a comprobar
     * @return Optional de Usuario
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Optional<Usuario> getUsuario(String nombeUsuario, String contrseña) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE nombreUsuario = ? AND contraseña = ?";
        Usuario usuario = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, nombeUsuario);
        st.setString(2, contrseña);
        ResultSet res = st.executeQuery();
        while(res.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(res.getInt("id_usuario"));
            usuario.setNombreUsuario(res.getString("nombreUsuario"));
            usuario.setContraseña(res.getString("contraseña"));
            usuario.setNombre(res.getString("nombre"));
            usuario.setApellidos1(res.getString("apellido1"));
            usuario.setApellidos2(res.getString("apellido2"));
            usuario.setTelefono(res.getString("telefono"));
            usuario.setEmail(res.getString("email"));
            usuario.setRol(res.getString("rol"));
        }
        st.close();
        return Optional.ofNullable(usuario);
    }

    /**
     * Modifica el valor de campo del Usuario pasado por parametros
     * @param campo El campo de Usuario que se quiere modificar
     * @param valor El valor nuevo que se quiere introducir
     * @param id El identificador del Usuario que se quiere modificar
     * @return true si el valor del campo se ha modificado correctamente
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public boolean modificaUsuario(String campo, String valor, Integer id) throws  SQLException{
        String sql = "UPDATE USUARIO SET " + campo + " = ? WHERE id_usuario = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, valor);
        st.setInt(2, id);
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }

    public boolean modificaUsuario(Usuario usuario) throws  SQLException{
        String sql = "UPDATE USUARIO SET nombre = ?, apellido1 = ?, apellido2 = ?, telefono = ?, email = ? WHERE id_usuario = ?";

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, usuario.getNombre());
        st.setString(2, usuario.getApellidos1());
        st.setString(3, usuario.getApellidos2());
        st.setString(4, usuario.getTelefono());
        st.setString(5, usuario.getEmail());
        st.setInt(6, usuario.getIdUsuario());
        int rows = st.executeUpdate();
        st.close();
        return rows == 1;
    }
}
