package com.svalero.phonostore;

import com.svalero.phonostore.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestUsuario {
    Integer idUsuario = 0;
    String nombreUsuario = "TestUsuario";
    String contrasenia = "TestContrasenia";
    String nombre = "TestNombre";
    String apellido1 = "TestApellido1";
    String apellido2 = "TestApellido2";
    String telefono = "TestTelefono";
    String email = "TestEmail";
    String rol = "TestRol";

    Usuario usuario = new Usuario(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, rol);
    Usuario usuarioCopia1 = new Usuario(usuario.getIdUsuario(), usuario.getNombreUsuario(), usuario.getContraseña(), usuario.getNombre(), usuario.getApellidos1(), usuario.getApellidos2(), usuario.getTelefono(), usuario.getEmail(), usuario.getRol());
    Usuario usuarioCopia2 = usuario;

    @Test
    public void testGeneracionUsuario1(){
        boolean res = false;
        if(Objects.equals(usuario.getIdUsuario(), idUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario2(){
        boolean res = false;
        if(Objects.equals(usuario.getNombreUsuario(), nombreUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario3(){
        boolean res = false;
        if(Objects.equals(usuario.getContraseña(), contrasenia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario4(){
        boolean res = false;
        if(Objects.equals(usuario.getNombre(), nombre)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario5(){
        boolean res = false;
        if(Objects.equals(usuario.getApellidos1(), apellido1)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario6(){
        boolean res = false;
        if(Objects.equals(usuario.getApellidos2(), apellido2)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario7(){
        boolean res = false;
        if(Objects.equals(usuario.getTelefono(), telefono)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario8(){
        boolean res = false;
        if(Objects.equals(usuario.getEmail(), email)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario9(){
        boolean res = false;
        if(Objects.equals(usuario.getRol(), rol)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testEqualsUsuario1(){
        boolean res = false;
        if(Objects.equals(usuario, usuarioCopia1)){
            res = true;
        }
        Assertions.assertFalse(res);
    }
    @Test
    public void testEqualsUsuario2(){
        boolean res = false;
        if(Objects.equals(usuario, usuarioCopia2)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

}
