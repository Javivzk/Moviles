package com.svalero.phonostore.domain;

public class Empleado extends Usuario{
    String dni;
    Integer idUsuario;
    String departamento;
    Integer salario;
    String direccion;
    String codigoEmpleado;

    public Empleado() {
    }

    public Empleado(Integer idUsuario, String nombreUsuario, String contraseña, String nombre, String apellidos1, String apellidos2, String telefono, String email, String rol, String dni, String departamento, Integer salario, String direccion, String codigoEmpleado) {
        super(idUsuario, nombreUsuario, contraseña, nombre, apellidos1, apellidos2, telefono, email, rol);
        this.dni = dni;
        this.idUsuario = idUsuario;
        this.departamento = departamento;
        this.salario = salario;
        this.direccion = direccion;
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado -> DNI=" + dni +
                ", Nombre: " + nombre +
                ", Apellidos1: " + apellidos1 +
                ", Apellidos2: " + apellidos2;
    }
}
