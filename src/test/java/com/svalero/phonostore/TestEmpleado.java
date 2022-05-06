package com.svalero.phonostore;

import com.svalero.phonostore.domain.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestEmpleado {
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
    String departamento = "TestDepartamento";
    Integer salario = 10000;
    String direccion = "TestDireccion";
    String codigoEmpleado = "TestCodigoEmpleado";

    Empleado empleado = new Empleado(idUsuario, nombreUsuario, contrasenia, nombre, apellido1, apellido2, telefono, email, rol, dni, departamento, salario, direccion, codigoEmpleado);

    @Test
    public void testGeneracionUsuario1(){
        boolean res = false;
        if(Objects.equals(empleado.getIdUsuario(), idUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario2(){
        boolean res = false;
        if(Objects.equals(empleado.getNombreUsuario(), nombreUsuario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario3(){
        boolean res = false;
        if(Objects.equals(empleado.getContraseña(), contrasenia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario4(){
        boolean res = false;
        if(Objects.equals(empleado.getNombre(), nombre)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario5(){
        boolean res = false;
        if(Objects.equals(empleado.getApellidos1(), apellido1)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario6(){
        boolean res = false;
        if(Objects.equals(empleado.getApellidos2(), apellido2)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario7(){
        boolean res = false;
        if(Objects.equals(empleado.getTelefono(), telefono)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario8(){
        boolean res = false;
        if(Objects.equals(empleado.getEmail(), email)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario9(){
        boolean res = false;
        if(Objects.equals(empleado.getRol(), rol)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario10(){
        boolean res = false;
        if(Objects.equals(empleado.getDni(), dni)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario11(){
        boolean res = false;
        if(Objects.equals(empleado.getCodigoEmpleado(), codigoEmpleado)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario12(){
        boolean res = false;
        if(Objects.equals(empleado.getSalario(), salario)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario13(){
        boolean res = false;
        if(Objects.equals(empleado.getDireccion(), direccion)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUsuario14(){
        boolean res = false;
        if(Objects.equals(empleado.getDepartamento(), departamento)){
            res = true;
        }
        Assertions.assertTrue(res);
    }
}
