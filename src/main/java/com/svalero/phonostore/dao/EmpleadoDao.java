package com.svalero.phonostore.dao;

import com.svalero.phonostore.domain.Empleado;
import com.svalero.phonostore.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoDao {

    private Connection connection;

    public EmpleadoDao(Connection connection){
       this.connection=connection;
    }

    /**
     * Devuelve todos los empleados de la BD
     * @return ArrayList con todos los empleados
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public ArrayList<Empleado> findAll() throws SQLException{
        String sql = "SELECT * FROM EMPLEADO ORDER BY dni";
        ArrayList<Empleado> empleados = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet res = st.executeQuery();
        while(res.next()){
            UsuarioDao usuarioDAO = new UsuarioDao(connection);
            Empleado empleado = new Empleado();
            empleado.setDni(res.getString("dni"));
            empleado.setIdUsuario(res.getInt("id_usuario"));
            empleado.setDepartamento(res.getString("departamento"));
            empleado.setSalario(res.getInt("salario"));
            empleado.setDireccion(res.getString("direccion"));
            empleado.setCodigoEmpleado(res.getString("codigoEmpleado"));
            Usuario usuario = usuarioDAO.getUsuario(empleado);
            empleado.setNombreUsuario(usuario.getNombreUsuario());
            empleado.setContraseña(usuario.getContraseña());
            empleado.setNombre(usuario.getNombre());
            empleado.setApellidos1(usuario.getApellidos1());
            empleado.setApellidos2(usuario.getApellidos2());
            empleado.setTelefono(usuario.getTelefono());
            empleado.setEmail(usuario.getTelefono());
            empleado.setRol(usuario.getRol());
            empleados.add(empleado);
        }
        st.close();
        return empleados;
    }

    /**
     * Devuelve el Empleado pasado por parametro
     * @param dni DNI del Empleado a buscar en la BD
     * @return Empleado de la BD
     * @throws SQLException Si hay algun error no especifico lanzado por la BD
     */
    public Empleado getEmpleado(String dni) throws SQLException{
        String sql = "SELECT * FROM EMPLEADO WHERE dni = ?";
        Empleado empleado = new Empleado();

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, dni);
        ResultSet res = st.executeQuery();
        while(res.next()){
            UsuarioDao usuarioDAO = new UsuarioDao(connection);
            empleado.setDni(res.getString("dni"));
            empleado.setIdUsuario(res.getInt("id_usuario"));
            empleado.setDepartamento(res.getString("departamento"));
            empleado.setSalario(res.getInt("salario"));
            empleado.setDireccion(res.getString("direccion"));
            empleado.setCodigoEmpleado(res.getString("codigoEmpleado"));
            Usuario usuario = usuarioDAO.getUsuario(empleado);
            empleado.setNombreUsuario(usuario.getNombreUsuario());
            empleado.setContraseña(usuario.getContraseña());
            empleado.setNombre(usuario.getNombre());
            empleado.setApellidos1(usuario.getApellidos1());
            empleado.setApellidos2(usuario.getApellidos2());
            empleado.setTelefono(usuario.getTelefono());
            empleado.setEmail(usuario.getTelefono());
            empleado.setRol(usuario.getRol());
        }
        st.close();
        return empleado;
    }
}
