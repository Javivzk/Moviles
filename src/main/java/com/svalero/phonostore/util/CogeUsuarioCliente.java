package com.svalero.phonostore.util;

import com.svalero.phonostore.dao.ClienteDao;
import com.svalero.phonostore.dao.UsuarioDao;
import com.svalero.phonostore.domain.Cliente;
import com.svalero.phonostore.domain.Usuario;
import com.svalero.phonostore.exception.ClienteNoEncontrado;
import com.svalero.phonostore.exception.UsuarioNoEncontrado;

import java.sql.Connection;
import java.sql.SQLException;

public class CogeUsuarioCliente {

    public static Usuario getUsuario(String nombre, String contrasenia, Connection connection){
        UsuarioDao usuarioDao = new UsuarioDao(connection);
        Usuario usuario = null;
        try {
            usuario = usuarioDao.getUsuario(nombre, contrasenia).orElseThrow(UsuarioNoEncontrado::new);
        } catch (UsuarioNoEncontrado usuarioNoEncontrado) {
            usuarioNoEncontrado.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return usuario;
    }

    public static Cliente getCliente(Usuario usuario, Connection connection){
        ClienteDao clienteDao = new ClienteDao(connection);
        Cliente cliente = null;
        try {
            cliente = clienteDao.getCliente(usuario).orElseThrow(ClienteNoEncontrado::new);
        } catch (SQLException | ClienteNoEncontrado throwables) {
            System.out.println("No hay usuario");
        }

        return cliente;
    }

}
