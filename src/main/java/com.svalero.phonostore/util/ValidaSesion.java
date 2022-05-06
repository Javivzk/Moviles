package com.svalero.phonostore.util;

import com.svalero.phonostore.dao.Database;
import com.svalero.phonostore.dao.UsuarioDao;
import com.svalero.phonostore.domain.Usuario;
import com.svalero.phonostore.exception.UsuarioNoEncontrado;

import java.sql.SQLException;

public class ValidaSesion {
    public static boolean validar(String nombre, String password) {
        Database database = new Database();
        UsuarioDao usuarioDao = new UsuarioDao(database.getConnection());
        Usuario usuarioActual = null;
        try {
            System.out.println(nombre + " " + password);
            usuarioActual = usuarioDao.getUsuario(nombre, password).orElseThrow(UsuarioNoEncontrado::new);
            System.out.println(usuarioActual.getNombreUsuario());
            System.out.println(usuarioActual.getRol());
            database.close();
        } catch (SQLException sqle) {
            System.out.println("Ha habido un error con la base de datos");
            sqle.printStackTrace();
            database.close();
            return false;
        } catch (UsuarioNoEncontrado une) {
            System.out.println(une.getMessage());
            database.close();
            return false;
        } finally {
            database.close();
        }
        return true;
    }
}
