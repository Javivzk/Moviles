package com.svalero.phonostore;

import com.svalero.phonostore.domain.Cliente;
import com.svalero.phonostore.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestCliente {
    Integer idUsuario = 0;
    String nombreUsuario = "TestUsuario";
    String contrasenia = "TestContrasenia";
    String nombre = "TestNombre";
    String apellido1 = "TestApellido1";
    String apellido2 = "TestApellido2";
    String telefono = "TestTelefono";
    String email = "TestEmail";
    String rol = "TestRol";
    String dni = "TestDNI";
    String direccion = "TestDireccion";
    String poblacion = "TestPoblacion";
    String pais = "TestPais";
    String provincia = "TestProvincia";
    String codigoPostal = "TestCodigoPostal";

    Usuario usuario = new Usuario(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, rol);
    Cliente cliente1 = new Cliente(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, rol, dni, direccion, poblacion, pais, provincia, codigoPostal);
    Cliente cliente2 = new Cliente(usuario, dni, direccion, provincia, codigoPostal);

    @Test
    public void testGeneracionCliente1(){
        boolean res = false;
        if(Objects.equals(cliente1.getIdUsuario(), idUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente2(){
        boolean res = false;
        if(Objects.equals(cliente1.getNombreUsuario(), nombreUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente3(){
        boolean res = false;
        if(Objects.equals(cliente1.getContraseña(), contrasenia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente4(){
        boolean res = false;
        if(Objects.equals(cliente1.getNombre(), nombre)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente5(){
        boolean res = false;
        if(Objects.equals(cliente1.getApellidos1(), apellido1)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente6(){
        boolean res = false;
        if(Objects.equals(cliente1.getApellidos2(), apellido2)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente7(){
        boolean res = false;
        if(Objects.equals(cliente1.getTelefono(), telefono)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente8(){
        boolean res = false;
        if(Objects.equals(cliente1.getEmail(), email)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente9(){
        boolean res = false;
        if(Objects.equals(cliente1.getRol(), rol)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente10(){
        boolean res = false;
        if(Objects.equals(cliente1.getProvincia(), provincia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente11(){
        boolean res = false;
        if(Objects.equals(cliente1.getDireccion(), direccion)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente12(){
        boolean res = false;
        if(Objects.equals(cliente1.getDni(), dni)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente13(){
        boolean res = false;
        if(Objects.equals(cliente1.getCodigoPostal(), codigoPostal)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente14(){
        boolean res = false;
        if(Objects.equals(cliente2.getIdUsuario(), idUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente15(){
        boolean res = false;
        if(Objects.equals(cliente2.getNombreUsuario(), nombreUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente16(){
        boolean res = false;
        if(Objects.equals(cliente2.getContraseña(), contrasenia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente17(){
        boolean res = false;
        if(Objects.equals(cliente2.getNombre(), nombre)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente18(){
        boolean res = false;
        if(Objects.equals(cliente2.getApellidos1(), apellido1)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente19(){
        boolean res = false;
        if(Objects.equals(cliente2.getApellidos2(), apellido2)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente20(){
        boolean res = false;
        if(Objects.equals(cliente2.getTelefono(), telefono)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente21(){
        boolean res = false;
        if(Objects.equals(cliente2.getEmail(), email)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente22(){
        boolean res = false;
        if(Objects.equals(cliente2.getRol(), rol)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente23(){
        boolean res = false;
        if(Objects.equals(cliente2.getProvincia(), provincia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente24(){
        boolean res = false;
        if(Objects.equals(cliente2.getDireccion(), direccion)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente25(){
        boolean res = false;
        if(Objects.equals(cliente2.getDni(), dni)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionCliente26(){
        boolean res = false;
        if(Objects.equals(cliente2.getCodigoPostal(), codigoPostal)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

}
